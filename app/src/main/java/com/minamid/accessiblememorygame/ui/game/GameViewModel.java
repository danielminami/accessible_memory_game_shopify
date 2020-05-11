package com.minamid.accessiblememorygame.ui.game;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.minamid.accessiblememorygame.model.Announcements;
import com.minamid.accessiblememorygame.model.Image;
import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.model.Product;
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GameViewModel extends ViewModel {

    private ImageService imageService;
    private MutableLiveData<Boolean> isGameStarted;
    private MutableLiveData<Boolean> isWinnerLiveData = new MutableLiveData<>();
    private List<MutableLiveData<MemoryCard>> cardListLiveData = new ArrayList<>();
    private MutableLiveData<Boolean> isScreenLock = new MutableLiveData<>();
    private MutableLiveData<Boolean> isResetEnabled = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkError = new MutableLiveData<>();
    private MutableLiveData<Announcements> announceableLiveData = new MutableLiveData<>();
    private List<MemoryCard> previousCardRevealed = new ArrayList<>();
    private MutableLiveData<Integer> playerMoves = new MutableLiveData<>();
    private MutableLiveData<Integer> remainingPairs = new MutableLiveData<>();
    private MutableLiveData<Boolean> isMatchPlaySound = new MutableLiveData<>();
    private long gameTimeInSeconds;

    /********************************************************************************************/
    /* Getters                                                                                  */
    /********************************************************************************************/
    public List<MutableLiveData<MemoryCard>> getCardListLiveData() { return cardListLiveData; }
    public MutableLiveData<Integer> getPlayerMoves() { return playerMoves; }
    public MutableLiveData<Integer> getRemainingPairs() { return remainingPairs; }
    public MutableLiveData<Boolean> getIsWinnerLiveData() { return isWinnerLiveData; }
    public MutableLiveData<Boolean> getIsResetEnabled() { return isResetEnabled; }
    public MutableLiveData<Boolean> getIsScreenLock() { return isScreenLock; }
    public MutableLiveData<Announcements> getAnnounceableLiveData() { return announceableLiveData; }
    public MutableLiveData<Boolean> getIsGameStarted() { return isGameStarted; }
    public MutableLiveData<Boolean> getIsMatchPlaySoundLiveData() { return isMatchPlaySound; }
    public long getGameTimeInSeconds() { return gameTimeInSeconds; }
    public MutableLiveData<Boolean> getIsNetworkError() { return isNetworkError; }


    public void setBoard(List<MemoryCard> board, int numOfColumns, ImageService imageService) {
        if (isGameStarted == null) {
            this.imageService = imageService;
            isWinnerLiveData.setValue(false);
            setLiveData(board);
            fetchCardImages();
        }
    }

    /**
     * Set LiveData from the MemoryCards Object
     *
     * @param board
     */
    private void setLiveData(List<MemoryCard> board) {
        for (int i=0; i < board.size(); i++) {
            MutableLiveData<MemoryCard> card = new MutableLiveData<MemoryCard>();
            card.setValue(board.get(i));
            cardListLiveData.add(card);
        }

        if (playerMoves.getValue() == null) {
            playerMoves.setValue(0);
        }

        if (remainingPairs.getValue() == null) {
            remainingPairs.setValue(Config.getInstance().getPairsToMatchToCompleteGame());
        }

    }

    // TODO: Write test for it
    // TODO: Refactor this function and split in small units to make it more testable
    /**
     * This is called by the UI when a Card is clicked
     * @param v View Clicked
     */
    public void onClick(View v) {

        MemoryCard memoryCard = (MemoryCard) v;

        if (memoryCard.isFound() || memoryCard.isRevealed()) {
            return;
        } else {
            memoryCard.setRevealed(true);
        }

        if (previousCardRevealed.size() == Config.getInstance().getNumOfCardsToMakeMatch() - 1) {
            boolean isMatch = true;
            isResetEnabled.setValue(false);
            for (int i = 0; i < previousCardRevealed.size(); i++) {
                if (!memoryCard.getImageId().equals(previousCardRevealed.get(i).getImageId())){
                    isMatch = false;
                }
            }

            if (isMatch) {

                for (MemoryCard previousCard : previousCardRevealed) {
                    previousCard.setFound(true);
                    previousCard.setShouldAnnounce(false);
                    updateObservable(previousCard);
                }

                memoryCard.setShouldAnnounce(false);
                memoryCard.setFound(true);
                memoryCard.setRevealed(true);
                updateObservable(memoryCard);

                if (checkIsWinner()) {
                    updateObservableEnableClick(false);
                    isResetEnabled.setValue(true);
                    playerMoves.setValue(playerMoves.getValue() + 1);
                    remainingPairs.setValue(remainingPairs.getValue() - 1);
                    return;
                }

                if (!Config.getInstance().isAccessibilityEnabled()) { isMatchPlaySound.setValue(isWinnerLiveData.getValue()); }

                remainingPairs.setValue(remainingPairs.getValue() - 1);
                isResetEnabled.setValue(true);
                updateObservable(memoryCard);
                previousCardRevealed.clear();

            } else {
                memoryCard.setRevealed(true);
                updateObservable(memoryCard);
                for (MemoryCard previousCard : previousCardRevealed) {
                    previousCard.setShouldAnnounce(false);
                }
                updateObservableEnableClick(false);

                mismatchHandler(memoryCard);
            }
            playerMoves.setValue(playerMoves.getValue() + 1);
        } else {
            memoryCard.setShouldAnnounce(true);
            previousCardRevealed.add(memoryCard);
            updateObservable(memoryCard);
        }
        Log.d("onClick", "Card " + memoryCard.getRowPosition() + " " +  memoryCard.getColPosition());
    }

    /**
     * This method handles user's mismatch. After a given time, cards are turned face down.
     * @param m last card clicked
     */
    private void mismatchHandler(MemoryCard m) {
        final MemoryCard memoryCardHandler = m;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                memoryCardHandler.setRevealed(false);
                memoryCardHandler.setShouldAnnounce(false);
                isResetEnabled.postValue(true);
                updateObservable(memoryCardHandler);
                for (MemoryCard previousCards : previousCardRevealed) {
                    previousCards.setRevealed(false);
                    updateObservable(previousCards);
                }
                previousCardRevealed.clear();
                updateObservableEnableClick(true);
            }
        }, 3000);
    }


    //TODO: Mock Service to write automated test
    private void fetchCardImages() {
        isResetEnabled.setValue(false);
        isScreenLock.setValue(true);
        imageService.fetchImageList(new ImageService.FetchImageCallBack() {
            @Override
            public void onSuccess(ImageResponse imageResponse) {
                List<Image> imageList = duplicateAndShuffleCards(imageResponse.getProducts(),
                        Config.getInstance().getPairsToMatchToCompleteGame(),
                        Config.getInstance().getNumOfCardsToMakeMatch());
                for (int i = 0; i < cardListLiveData.size() && i < imageList.size(); i++) {
                    cardListLiveData.get(i).getValue().setImageId(imageList.get(i).getId());
                    cardListLiveData.get(i).getValue().setDescription(imageList.get(i).getDescription());
                    cardListLiveData.get(i).getValue().setSrc(imageList.get(i).getSrc());
                    cardListLiveData.get(i).getValue().setRevealed(true);
                    cardListLiveData.get(i).getValue().setFound(false);
                    cardListLiveData.get(i).setValue(cardListLiveData.get(i).getValue());
                }
                isScreenLock.setValue(false);
                isGameStarted = new MutableLiveData<>();
                isGameStarted.setValue(true);
                updateObservableEnableClick(false);
                updateObservableAnnounceable(Announcements.TIME_TO_EXPLORE);

                startGameHandler();
            }

            @Override
            //TODO: Handle the error code
            public void onFailure(ResponseStatusCode responseStatusCode) {
                isScreenLock.setValue(false);
                isNetworkError.postValue(true);
            }
        });
    }

    /**
     * Handles the time cards are revealed for the user, before game starts.
     *
     */
    private void startGameHandler() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                updateObservableEnableClick(true);
                updateObservableAnnounceable(Announcements.START_GAME);
                isResetEnabled.postValue(true);
                turnAllCardsFacedDown(cardListLiveData);
                Date date = new Date();
                gameTimeInSeconds = date.getTime();
            }
        }, Config.getInstance().getTimeBoardRevealed() * 1000);
    }

    /**
     * This method multiplies the number of cards according to the user Settings.
     * Also Shuffles the array.
     *
     * @param productList
     * @param numPairs
     * @param matchSize
     * @return
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    private List<Image> duplicateAndShuffleCards(List<Product> productList, int numPairs, int matchSize) {
        List<Image> tempImageList = new ArrayList<>();
        for (int i=0; tempImageList.size() < numPairs; i++) {
            boolean shouldAddImage = true;
            for (Image image : tempImageList) {
                if (image.getDescription().equals(productList.get(i).getTitle())) {
                    shouldAddImage = false;
                }
            }
            if (shouldAddImage) {
                productList.get(i).getImage().setDescription(productList.get(i).getTitle());
                tempImageList.add(productList.get(i).getImage());
            }
        }
        List<Image> completeImageList = new ArrayList<>();

        for (int i = 0; i < matchSize; i++) {
            completeImageList.addAll(tempImageList);
        }
        Collections.shuffle(completeImageList);
        return completeImageList;
    }

    /**
     * Check for the winning condition.
     *
     * @return
     */
    private boolean checkIsWinner() {
        for (MutableLiveData<MemoryCard> memoryCardMutableLiveData : cardListLiveData) {
            if (!memoryCardMutableLiveData.getValue().isFound()) {
                return false;
            }
        }
        Date date = new Date();
        gameTimeInSeconds = (date.getTime() - gameTimeInSeconds) / 1000;
        this.isWinnerLiveData.setValue(true);
        return true;
    }

    //TODO: Write a Test for this Method
    private void updateObservable(MemoryCard... memoryCardList) {
        for (MemoryCard memoryCard : memoryCardList) {
            for (MutableLiveData<MemoryCard> card : cardListLiveData) {
                if (card.getValue().getRowPosition() == memoryCard.getRowPosition() &&
                        card.getValue().getColPosition() == memoryCard.getColPosition()) {
                    card.setValue(memoryCard);
                }
            }
        }
    }

    //TODO: Make it testable by making it accept the arrayOfCards
    /**
     * Enable / Disable click in the whole board.
     * @param shouldEnable
     */
    private void updateObservableEnableClick(boolean shouldEnable) {
        for (MutableLiveData<MemoryCard> card : cardListLiveData) {
            card.getValue().setEnabled(shouldEnable);
            card.setValue(card.getValue());
        }
    }

    // TODO: Write Test
    /**
     * Updates the announceable LiveData for Accessibility
     * @param announcements
     */
    private void updateObservableAnnounceable(Announcements announcements) {
        announceableLiveData.setValue(announcements);
    }

    // TODO: Write Test
    public void refreshBoard() {
        for (MutableLiveData<MemoryCard> cardMutableLiveData : cardListLiveData) {
            cardMutableLiveData.setValue(cardMutableLiveData.getValue());
        }
    }

    /**
     * This method turn all cards faced down.
     *
     * @param cardListLiveData
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    private void turnAllCardsFacedDown(List<MutableLiveData<MemoryCard>> cardListLiveData) {
        for (MutableLiveData<MemoryCard> card : cardListLiveData) {
            card.getValue().setRevealed(false);
            card.postValue(card.getValue());
        }
    }

    public void resetGame() {
        cardListLiveData.clear();
        previousCardRevealed.clear();
        isGameStarted = null;
    }
}

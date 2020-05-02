package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.minamid.accessiblememorygame.model.Announcements;
import com.minamid.accessiblememorygame.model.Board;
import com.minamid.accessiblememorygame.model.Image;
import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.model.Product;
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GameViewModel extends ViewModel {

    private ImageService imageService;
    private MutableLiveData<Boolean> isGameStarted;
    private MutableLiveData<Boolean> isWinnerLiveData = new MutableLiveData<>();
    private List<MutableLiveData<MemoryCard>> cardListLiveData = new ArrayList<>();
    private MutableLiveData<Boolean> isScreenLock = new MutableLiveData<>();
    private MutableLiveData<Announcements> announceableLiveData = new MutableLiveData<>();
    private List<MemoryCard> previousCardRevealed = new ArrayList<>();
    private MutableLiveData<Integer> playerMoves = new MutableLiveData<>();
    private MutableLiveData<Integer> remainingPairs = new MutableLiveData<>();
    private long gameTimeInSeconds;
    //private MutableLiveData<Board> boardMutableLiveData = new MutableLiveData<>();

    public List<MutableLiveData<MemoryCard>> getCardListLiveData() { return cardListLiveData; }
    public MutableLiveData<Integer> getPlayerMoves() { return playerMoves; }
    public MutableLiveData<Integer> getRemainingPairs() { return remainingPairs; }
    public MutableLiveData<Boolean> getIsWinnerLiveData() { return isWinnerLiveData; }
    public MutableLiveData<Boolean> getIsScreenLock() { return isScreenLock; }
    public MutableLiveData<Announcements> getAnnounceableLiveData() { return announceableLiveData; }
    public MutableLiveData<Boolean> getIsGameStarted() { return isGameStarted; }
    public long getGameTimeInSeconds() { return gameTimeInSeconds; }

    public void setBoard(List<MemoryCard> board, int numOfColumns, ImageService imageService) {
        if (isGameStarted == null) {
            this.imageService = imageService;
            this.isScreenLock.setValue(true);
            this.isWinnerLiveData.setValue(false);
            setPositions(board, numOfColumns);
            setLiveData(board);
            fetchCardImages();
        }
    }

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

    public void onClick(View v) {

        final MemoryCard memoryCard = (MemoryCard) v;

        if (memoryCard.isFound() || memoryCard.isRevealed()) {
            return;
        } else {
            memoryCard.setRevealed(true);
        }

        if (previousCardRevealed.size() == Config.getInstance().getNumOfCardsToMakeMatch() - 1) {
            boolean isMatch = true;
            for (int i = 0; i < previousCardRevealed.size(); i++) {
                if (!memoryCard.getImageId().equals(previousCardRevealed.get(i).getImageId())){
                    isMatch = false;
                }
            }

            if (isMatch) {

                for (MemoryCard previousCards : previousCardRevealed) {
                    previousCards.setFound(true);
                    updateObservable(previousCards);
                }

                memoryCard.setFound(true);
                memoryCard.setRevealed(true);
                updateObservable(memoryCard);

                if (checkIsWinner()) {
                    updateObservableEnableClick(false);
                    playerMoves.setValue(playerMoves.getValue() + 1);
                    remainingPairs.setValue(remainingPairs.getValue() - 1);
                    return;
                }
                for (MemoryCard previousCards : previousCardRevealed) {
                    updateObservable(previousCards);
                }
                remainingPairs.setValue(remainingPairs.getValue() - 1);
                updateObservable(memoryCard);
                previousCardRevealed.clear();
            } else {
                memoryCard.setRevealed(true);
                updateObservable(memoryCard);
                updateObservableEnableClick(false);
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        memoryCard.setRevealed(false);
                        updateObservable(memoryCard);
                        for (MemoryCard previousCards : previousCardRevealed) {
                            previousCards.setRevealed(false);
                            updateObservable(previousCards);
                        }
                        previousCardRevealed.clear();
                        updateObservableEnableClick(true);
                    }
                }, 3000);
            }
            playerMoves.setValue(playerMoves.getValue() + 1);
        } else {
            previousCardRevealed.add(memoryCard);
            updateObservable(memoryCard);
        }

        Log.d("onClick", "Card " + memoryCard.getRowPosition() + " " +  memoryCard.getColPosition());

    }

    private void setPositions(List<MemoryCard> cardList, int numOfColumns) {
        int i = 1, j = 1;
        for (MemoryCard card : cardList) {
            card.setRowPosition(i);
            card.setColPosition(j);
            i = j == numOfColumns ? ++i : i;
            j = j == numOfColumns ? 1 : ++j;
        }
    }

    private void fetchCardImages() {
        // TODO: Create a screen lock while service call is not complete
        imageService.fetchImageList(new ImageService.FetchImageCallBack() {
            @Override
            public void onSuccess(ImageResponse imageResponse) {
                List<Image> imageList = duplicateAndShuffleCards(imageResponse.getProducts());
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
                // TODO: Announce the game start

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // TODO: Announce all cards are turned face down
                        updateObservableEnableClick(true);
                        updateObservableAnnounceable(Announcements.START_GAME);
                        turnAllCardsFacedDown();
                        Date date = new Date();
                        gameTimeInSeconds = date.getTime();
                    }
                }, Config.getInstance().getTimeBoardRevealed() * 1000);

                Log.d("CallBack", "OnSuccess");
            }

            @Override
            public void onFailure(ResponseStatusCode responseStatusCode) {
                // TODO: Unlock screen
                // TODO: Handle service call Error
            }
        });
    }

    private List<Image> duplicateAndShuffleCards(List<Product> productList) {
        //TODO: replace for a configuration setting
        List<Image> tempImageList = new ArrayList<>();
        for (int i = 0; i < Config.getInstance().getPairsToMatchToCompleteGame() + 1; i++) {
            if (i != 10) {
                productList.get(i).getImage().setDescription(productList.get(i).getTitle());
                tempImageList.add(productList.get(i).getImage());
            }
        }

        List<Image> completeImageList = new ArrayList<>();

        for (int i = 0; i < Config.getInstance().getNumOfCardsToMakeMatch(); i++) {
            completeImageList.addAll(tempImageList);
        }
        Collections.shuffle(completeImageList);
        return completeImageList;
    }

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

    private void updateObservableEnableClick(boolean shouldEnable) {
        for (MutableLiveData<MemoryCard> card : cardListLiveData) {
            card.getValue().setEnabled(shouldEnable);
            card.setValue(card.getValue());
        }
    }

    private void updateObservableAnnounceable(Announcements announcements) {
        announceableLiveData.setValue(announcements);
    }

    public void refreshBoard() {
        for (MutableLiveData<MemoryCard> cardMutableLiveData : cardListLiveData) {
            cardMutableLiveData.setValue(cardMutableLiveData.getValue());
        }
    }

    private void turnAllCardsFacedDown() {
        for (MutableLiveData<MemoryCard> card : cardListLiveData) {
            card.getValue().setRevealed(false);
            card.setValue(card.getValue());
        }
    }

}

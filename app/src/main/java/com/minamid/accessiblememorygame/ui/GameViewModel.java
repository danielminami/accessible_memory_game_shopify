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
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GameViewModel extends ViewModel {

    private ImageService imageService;
    private MutableLiveData<Boolean> isGameStarted;
    private MutableLiveData<Boolean> isWinnerLiveData = new MutableLiveData<>();
    private List<MutableLiveData<MemoryCard>> cardListLiveData;
    private MutableLiveData<Boolean> isScreenLock = new MutableLiveData<>();
    private MutableLiveData<Announcements> announceableLiveData = new MutableLiveData<>();
    private MemoryCard previousCardRevealed;
    private long gameTimeInSeconds;
    private MutableLiveData<MemoryCard> card11LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card12LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card13LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card14LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card21LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card22LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card23LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card24LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card31LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card32LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card33LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card34LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card41LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card42LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card43LiveData = new MutableLiveData<>();
    private MutableLiveData<MemoryCard> card44LiveData = new MutableLiveData<>();

    public MutableLiveData<MemoryCard> getCard11LiveData() { return card11LiveData; }
    public MutableLiveData<MemoryCard> getCard12LiveData() { return card12LiveData; }
    public MutableLiveData<MemoryCard> getCard13LiveData() { return card13LiveData; }
    public MutableLiveData<MemoryCard> getCard14LiveData() { return card14LiveData; }
    public MutableLiveData<MemoryCard> getCard21LiveData() { return card21LiveData; }
    public MutableLiveData<MemoryCard> getCard22LiveData() { return card22LiveData; }
    public MutableLiveData<MemoryCard> getCard23LiveData() { return card23LiveData; }
    public MutableLiveData<MemoryCard> getCard24LiveData() { return card24LiveData; }
    public MutableLiveData<MemoryCard> getCard31LiveData() { return card31LiveData; }
    public MutableLiveData<MemoryCard> getCard32LiveData() { return card32LiveData; }
    public MutableLiveData<MemoryCard> getCard33LiveData() { return card33LiveData; }
    public MutableLiveData<MemoryCard> getCard34LiveData() { return card34LiveData; }
    public MutableLiveData<MemoryCard> getCard41LiveData() { return card41LiveData; }
    public MutableLiveData<MemoryCard> getCard42LiveData() { return card42LiveData; }
    public MutableLiveData<MemoryCard> getCard43LiveData() { return card43LiveData; }
    public MutableLiveData<MemoryCard> getCard44LiveData() { return card44LiveData; }

    public MutableLiveData<Boolean> getIsWinnerLiveData() { return isWinnerLiveData; }
    public MutableLiveData<Boolean> getIsScreenLock() { return isScreenLock; }
    public MutableLiveData<Announcements> getAnnounceableLiveData() { return announceableLiveData; }
    public MutableLiveData<Boolean> getIsGameStarted() { return isGameStarted; }
    public long getGameTimeInSeconds() { return gameTimeInSeconds; }

    public void setBoard(Board board, ImageService imageService) {
        if (isGameStarted == null) {
            this.imageService = imageService;
            this.isScreenLock.setValue(true);
            this.isWinnerLiveData.setValue(false);
            setPositions(board);
            cardListLiveData = Arrays.asList(
                    card11LiveData, card12LiveData, card13LiveData, card14LiveData,
                    card21LiveData, card22LiveData, card23LiveData, card24LiveData,
                    card31LiveData, card32LiveData, card33LiveData, card34LiveData,
                    card41LiveData, card42LiveData, card43LiveData, card44LiveData);
            setLiveData(board, cardListLiveData);
            fetchCardImages();
        }
    }

    private void setLiveData(Board board, List<MutableLiveData<MemoryCard>> boardLiveData) {
        for (int i=0; i < board.size() && i < boardLiveData.size(); i++) {
            boardLiveData.get(i).setValue(board.get(i));
        }
    }

    public void onClick(View v) {
        final MemoryCard memoryCard = (MemoryCard) v;

        if (memoryCard.isFound() || memoryCard.isRevealed()) {
            return;
        } else {
            memoryCard.setRevealed(true);
        }

        boolean isMatch = false;

        // TODO: `previousCardRevealed` can cause memory leak. This needs to be fixed.

        if (previousCardRevealed != null) {
            isMatch = previousCardRevealed.getImageId().equals(memoryCard.getImageId());
            if (isMatch) {
                previousCardRevealed.setFound(true);
                memoryCard.setFound(true);
                memoryCard.setRevealed(true);
                updateObservable(previousCardRevealed, memoryCard);
                if (checkIsWinner()) {
                    updateObservableEnableClick(false);
                    return;
                }
                updateObservable(memoryCard, previousCardRevealed);
                previousCardRevealed = null;
            } else {
                memoryCard.setRevealed(true);
                updateObservable(memoryCard);
                updateObservableEnableClick(false);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        previousCardRevealed.setRevealed(false);
                        memoryCard.setRevealed(false);
                        updateObservable(memoryCard, previousCardRevealed);
                        previousCardRevealed = null;
                        updateObservableEnableClick(true);
                    }
                }, 3000);
            }
        } else {
            previousCardRevealed = memoryCard;
            updateObservable(memoryCard);
        }

        Log.d("onClick", "Card " + memoryCard.getRowPosition() + " " +  memoryCard.getColPosition());

    }

    private void setPositions(List<MemoryCard> cardList) {
        int i = 1, j = 1;
        for (MemoryCard card : cardList) {
            card.setRowPosition(i);
            card.setColPosition(j);
            i = j == 4 ? ++i : i;
            j = j == 4 ? 1 : ++j;
        }
    }

    private void fetchCardImages() {
        // TODO: Create a screen lock while service call is not complete
        imageService.fetchImageList(new ImageService.FetchImageCallBack() {
            @Override
            public void onSuccess(ImageResponse imageResponse) {
                List<Image> imageList = duplicateAndShuffleCards(imageResponse.getAlbum().getImages());
                for (int i = 0; i < cardListLiveData.size() && i < imageList.size(); i++) {
                    cardListLiveData.get(i).getValue().setImageId(imageList.get(i).getImageId());
                    cardListLiveData.get(i).getValue().setDescription(imageList.get(i).getDescription());
                    cardListLiveData.get(i).getValue().setSrc(imageList.get(i).getLink());
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
                }, Config.timeBoardRevealed * 1000);
            }

            @Override
            public void onFailure(ResponseStatusCode responseStatusCode) {
                // TODO: Unlock screen
                // TODO: Handle service call Error
            }
        });
    }

    private List<Image> duplicateAndShuffleCards(List<Image> imageList) {
        imageList.addAll(imageList);
        Collections.shuffle(imageList);
        return imageList;
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

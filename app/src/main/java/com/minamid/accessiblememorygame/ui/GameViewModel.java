package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.minamid.accessiblememorygame.model.Board;
import com.minamid.accessiblememorygame.model.Image;
import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameViewModel extends ViewModel {

    private ImageService imageService;
    private MutableLiveData<Boolean> isWinnerLiveData = new MutableLiveData<>();
    private List<MutableLiveData<MemoryCard>> cardListLiveData;
    private MutableLiveData<Boolean> isScreenLock = new MutableLiveData<>();
    private MemoryCard previousCardRevealed;
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

    public MutableLiveData<MemoryCard> getCard11LiveData() {
        if (card11LiveData == null) {
            card11LiveData = new MutableLiveData<>();
        }
        return card11LiveData;
    }

    public MutableLiveData<MemoryCard> getCard12LiveData() {
        if (card12LiveData == null) {
            card12LiveData = new MutableLiveData<>();
        }
        return card12LiveData;
    }

    public MutableLiveData<MemoryCard> getCard13LiveData() {
        if (card13LiveData == null) {
            card13LiveData = new MutableLiveData<>();
        }
        return card13LiveData;
    }

    public MutableLiveData<MemoryCard> getCard14LiveData() {
        if (card14LiveData == null) {
            card14LiveData = new MutableLiveData<>();
        }
        return card14LiveData;
    }

    public MutableLiveData<MemoryCard> getCard21LiveData() {
        if (card21LiveData == null) {
            card21LiveData = new MutableLiveData<>();
        }
        return card21LiveData;
    }

    public MutableLiveData<MemoryCard> getCard22LiveData() {
        if (card22LiveData == null) {
            card22LiveData = new MutableLiveData<>();
        }
        return card22LiveData;
    }

    public MutableLiveData<MemoryCard> getCard23LiveData() {
        if (card23LiveData == null) {
            card23LiveData = new MutableLiveData<>();
        }
        return card23LiveData;
    }

    public MutableLiveData<MemoryCard> getCard24LiveData() {
        if (card24LiveData == null) {
            card24LiveData = new MutableLiveData<>();
        }
        return card24LiveData;
    }

    public MutableLiveData<MemoryCard> getCard31LiveData() {
        if (card31LiveData == null) {
            card31LiveData = new MutableLiveData<>();
        }
        return card31LiveData;
    }

    public MutableLiveData<MemoryCard> getCard32LiveData() {
        if (card32LiveData == null) {
            card32LiveData = new MutableLiveData<>();
        }
        return card32LiveData;
    }

    public MutableLiveData<MemoryCard> getCard33LiveData() {
        if (card33LiveData == null) {
            card33LiveData = new MutableLiveData<>();
        }
        return card33LiveData;
    }

    public MutableLiveData<MemoryCard> getCard34LiveData() {
        if (card34LiveData == null) {
            card34LiveData = new MutableLiveData<>();
        }
        return card34LiveData;
    }

    public MutableLiveData<MemoryCard> getCard41LiveData() {
        if (card41LiveData == null) {
            card41LiveData = new MutableLiveData<>();
        }
        return card41LiveData;
    }

    public MutableLiveData<MemoryCard> getCard42LiveData() {
        if (card42LiveData == null) {
            card42LiveData = new MutableLiveData<>();
        }
        return card42LiveData;
    }

    public MutableLiveData<MemoryCard> getCard43LiveData() {
        if (card43LiveData == null) {
            card43LiveData = new MutableLiveData<>();
        }
        return card43LiveData;
    }

    public MutableLiveData<MemoryCard> getCard44LiveData() {
        if (card44LiveData == null) {
            card44LiveData = new MutableLiveData<>();
        }
        return card44LiveData;
    }

    public MutableLiveData<Boolean> getIsWinnerLiveData() {
        if (isWinnerLiveData == null) {
            isWinnerLiveData = new MutableLiveData<>();
        }
        return isWinnerLiveData;
    }

    public MutableLiveData<Boolean> getIsScreenLock() {
        if (isScreenLock == null) {
            isScreenLock = new MutableLiveData<>();
        }
        return isScreenLock;
    }

    public void setBoard(Board board, ImageService imageService) {
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

    public void setLiveData(Board board, List<MutableLiveData<MemoryCard>> boardLiveData) {
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

        if (previousCardRevealed != null) {
            isMatch = previousCardRevealed.getImageId().equals(memoryCard.getImageId());
            if (isMatch) {
                previousCardRevealed.setFound(true);
                memoryCard.setFound(true);
                memoryCard.setRevealed(true);
                updateObservable(previousCardRevealed, memoryCard);
                if (checkIsWinner()) {
                    updateObservableEnableClick(false);
                    isWinnerLiveData.setValue(true);
                    return;
                }
                updateObservable(memoryCard, previousCardRevealed);
                previousCardRevealed = null;
            } else {
                memoryCard.setRevealed(true);
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
        //Lock Screen
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
                updateObservableEnableClick(false);

                // TODO: Announce the game start

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // TODO: Announce all cards are turned face down
                        updateObservableEnableClick(true);
                        turnAllCardsFacedDown();
                    }
                }, Config.timeBoardRevealed * 100);
            }

            @Override
            public void onFailure(ResponseStatusCode responseStatusCode) {
                //Unlock screen
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

    private void turnAllCardsFacedDown() {
        for (MutableLiveData<MemoryCard> card : cardListLiveData) {
            card.getValue().setRevealed(false);
            card.setValue(card.getValue());
        }
    }

}

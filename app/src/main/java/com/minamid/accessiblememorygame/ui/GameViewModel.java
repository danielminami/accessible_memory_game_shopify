package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.minamid.accessiblememorygame.model.Board;
import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import java.util.Arrays;
import java.util.List;

public class GameViewModel extends ViewModel {

    private ImageService imageService;
    private List<MutableLiveData<MemoryCard>> cardListLiveData;
    private MutableLiveData<MemoryCard> card11LiveData;
    private MutableLiveData<MemoryCard> card12LiveData;
    private MutableLiveData<MemoryCard> card13LiveData;
    private MutableLiveData<MemoryCard> card14LiveData;
    private MutableLiveData<MemoryCard> card21LiveData;
    private MutableLiveData<MemoryCard> card22LiveData;
    private MutableLiveData<MemoryCard> card23LiveData;
    private MutableLiveData<MemoryCard> card24LiveData;
    private MutableLiveData<MemoryCard> card311iveData;
    private MutableLiveData<MemoryCard> card32LiveData;
    private MutableLiveData<MemoryCard> card33LiveData;
    private MutableLiveData<MemoryCard> card34LiveData;
    private MutableLiveData<MemoryCard> card41LiveData;
    private MutableLiveData<MemoryCard> card42LiveData;
    private MutableLiveData<MemoryCard> card43LiveData;
    private MutableLiveData<MemoryCard> card44LiveData;

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


    public void setBoard(Board board, ImageService imageService) {
        this.imageService = imageService;
        setPositions(board);
        cardListLiveData = Arrays.asList(
                card11LiveData, card12LiveData, card13LiveData, card14LiveData,
                card21LiveData, card22LiveData, card23LiveData, card24LiveData,
                card311iveData, card32LiveData, card33LiveData, card34LiveData,
                card41LiveData, card42LiveData, card43LiveData, card44LiveData);
        fetchCardImages();
    }

    public void onClick(View v) {
        MemoryCard memoryCard = (MemoryCard) v;
        Log.d("onClick", "Card " + memoryCard.getRowPosition() + " " +  memoryCard.getColPosition());
        if (!memoryCard.isRevealed()) {
            memoryCard.setRevealed(true);
        } else {
            memoryCard.setRevealed(false);
        }
        updateObservable(memoryCard);
        //card11LiveData.setValue(memoryCard);
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
                for (int i = 0; i < cardListLiveData.size() && i < imageResponse.getAlbum().getImages().size(); i++) {
                    cardListLiveData.get(i).getValue().setImageId(imageResponse.getAlbum().getImages().get(i).getImageId());
                    cardListLiveData.get(i).getValue().setDescription(imageResponse.getAlbum().getImages().get(i).getDescription());
                    cardListLiveData.get(i).getValue().setSrc(imageResponse.getAlbum().getImages().get(i).getLink());
                    cardListLiveData.get(i).setValue(cardListLiveData.get(i).getValue());
                }
            }

            @Override
            public void onFailure(ResponseStatusCode responseStatusCode) {
                //Unlock screen
            }
        });

        //TODO: Create service call and service call back

        //TODO: load live data in the service call back
    }

    private void updateObservable(MemoryCard memoryCard) {
        for (MutableLiveData<MemoryCard> card : cardListLiveData) {
            if (card.getValue().getColPosition() == memoryCard.getColPosition() &&
            card.getValue().getRowPosition() == memoryCard.getColPosition()) {
                card.setValue(memoryCard);
            }
        }
    }

}

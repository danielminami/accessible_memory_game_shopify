package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.minamid.accessiblememorygame.model.Board;
import com.minamid.accessiblememorygame.model.ImageResponse;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.ResponseStatusCode;

import java.util.List;

public class GameViewModel extends ViewModel {

    ImageService imageService;
    MutableLiveData<Board> board = new MutableLiveData<>();

    public void setBoard(List<MemoryCard> cardList, ImageService imageService) {
        this.imageService = imageService;
        setPositions(cardList);
        fetchCardImages();
    }

    private void setPositions(List<MemoryCard> cardList) {
        int i = 1, j = 1;
        for (MemoryCard card : cardList) {
            card.setRowPosition(i);
            card.setColPosition(j);
            i = i == 2 ? 1 : ++i;
            j = j == 2 ? 1 : ++j;
        }
    }

    private void fetchCardImages() {
        //Lock Screen
        imageService.fetchImageList(new ImageService.FetchImageCallBack() {
            @Override
            public void onSuccess(ImageResponse imageResponse) {
                Log.d("stop", " here");
                //Unlock screen
            }

            @Override
            public void onFailure(ResponseStatusCode responseStatusCode) {
                //Unlock screen
            }
        });

        //TODO: Create service call and service call back

        //TODO: load live data in the service call back
    };

}

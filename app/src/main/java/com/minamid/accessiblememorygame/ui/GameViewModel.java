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

import java.util.List;

public class GameViewModel extends ViewModel {

    private ImageService imageService;
    private MutableLiveData<Board> boardMutableLiveData;

    public MutableLiveData<Board> getBoardMutableLiveData() {
        if (boardMutableLiveData == null) {
            boardMutableLiveData = new MutableLiveData<>();
        }
        return boardMutableLiveData;
    }

    public void setBoard(Board board, ImageService imageService) {
        getBoardMutableLiveData().setValue(board);
        this.imageService = imageService;
        setPositions(board);
        fetchCardImages();
    }

    public void onClick(View v) {
        MemoryCard memoryCard = (MemoryCard) v;
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
                Log.d("stop", " here");
                Board board = getBoardMutableLiveData().getValue();
                for (int i = 0; i < board.size(); i ++) {
                    board.get(i).setId(i);
                    board.get(i).setSrc(imageResponse.getCardsList().get(i).getImage().getSrc());
                }
                getBoardMutableLiveData().setValue(board);
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

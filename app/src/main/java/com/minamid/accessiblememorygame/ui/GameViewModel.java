package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.minamid.accessiblememorygame.model.Board;
import com.minamid.accessiblememorygame.model.MemoryCard;

import java.util.List;

public class GameViewModel extends ViewModel {

    MutableLiveData<Board> board = new MutableLiveData<>();

    public void setBoard(List<MemoryCard> cardList) {
        setPositions(cardList);

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
        //load live data in the service call back
    };

}

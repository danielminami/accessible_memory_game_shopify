package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.base.CustomFragment;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameBoardFragment extends CustomFragment {

    @BindView(R.id.game_grid_layout) GridLayout gameGridLayout;

    private GameViewModel mViewModel;

    public static GameBoardFragment newInstance() {
        return new GameBoardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment_grid, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        // TODO: get this int from ViewModel
        int[] boardSize = calculateRowsAndColumns(10);

        setupBoard(boardSize[0], boardSize[1]);
    }

    public void setupBoard(int rows, int cols){
        gameGridLayout.setRowCount(rows);
        gameGridLayout.setColumnCount(cols);
        GridLayout.Spec row;
        GridLayout.Spec colspan;
        GridLayout.LayoutParams gridLayoutParam;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                MemoryCard memoryCard = new MemoryCard(getContext());
                row = GridLayout.spec(i, 1);
                colspan = GridLayout.spec(j, 1);
                gridLayoutParam = new GridLayout.LayoutParams(row, colspan);
                gameGridLayout.addView(memoryCard, gridLayoutParam);
            }
        }

//        Button button = new Button(getContext());
//        button.setText("OK");
//        GridLayout.Spec row = GridLayout.spec(1, 1);
//        GridLayout.Spec colspan = GridLayout.spec(1, 1);
//        GridLayout.LayoutParams gridLayoutParam = new GridLayout.LayoutParams(row, colspan);
//        gameGridLayout.addView(button, gridLayoutParam);
//
//        button = new Button(getContext());
//        button.setText("OK");
//        row = GridLayout.spec(1, 1);
//        colspan = GridLayout.spec(2, 1);
//        gridLayoutParam = new GridLayout.LayoutParams(row, colspan);
//        gameGridLayout.addView(button, gridLayoutParam);
//
//        button = new Button(getContext());
//        button.setText("OK");
//        row = GridLayout.spec(2, 1);
//        colspan = GridLayout.spec(1, 1);
//        gridLayoutParam = new GridLayout.LayoutParams(row, colspan);
//        gameGridLayout.addView(button, gridLayoutParam);
//
//        button = new Button(getContext());
//        button.setText("OK");
//        row = GridLayout.spec(2, 1);
//        colspan = GridLayout.spec(2, 1);
//        gridLayoutParam = new GridLayout.LayoutParams(row, colspan);
//        gameGridLayout.addView(button, gridLayoutParam);
    }

    private int[] calculateRowsAndColumns(int numberOfCards) {
        int[] boardSize = {4, Utils.calculateNoOfColumns(getContext())};


        // TODO: make calculations based on Cards and Screen size. Maybe create some kind of Util
        // Function to return the size of the screen or how many it fits by line


        return boardSize;
    }

    private int calculateImageSize() {
        return 0;
    }

}

package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.base.CustomFragment;
import com.minamid.accessiblememorygame.model.MemoryCard;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameFragment extends CustomFragment {

    @BindView(R.id.card11) MemoryCard card11;
    @BindView(R.id.card12) MemoryCard card12;
    @BindView(R.id.card13) MemoryCard card13;
    @BindView(R.id.card14) MemoryCard card14;
    private GameViewModel mViewModel;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        ButterKnife.bind(getActivity());
        Arrays.asList(card11, card12, card13, card14);
    }

}

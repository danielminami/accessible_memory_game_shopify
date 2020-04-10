package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.base.CustomFragment;
import com.minamid.accessiblememorygame.util.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends CustomFragment {

    @BindView(R.id.welcomeMessage) TextView welcomeMessage;
    @BindView(R.id.button_start_game) Button button_start_game;
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        welcomeMessage.setContentDescription(getString(R.string.contentDescription_text_welcome, Config.timeBoardRevealed));

        button_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(GameFragment.newInstance(), true);
            }
        });

    }

}

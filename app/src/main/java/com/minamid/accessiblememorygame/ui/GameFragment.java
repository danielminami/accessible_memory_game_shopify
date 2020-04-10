package com.minamid.accessiblememorygame.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.base.CustomFragment;
import com.minamid.accessiblememorygame.model.Board;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.service.ImageService;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameFragment extends CustomFragment {

    @BindView(R.id.card11) MemoryCard card11;
    @BindView(R.id.card12) MemoryCard card12;
    @BindView(R.id.card13) MemoryCard card13;
    @BindView(R.id.card14) MemoryCard card14;
    @BindView(R.id.card21) MemoryCard card21;
    @BindView(R.id.card22) MemoryCard card22;
    @BindView(R.id.card23) MemoryCard card23;
    @BindView(R.id.card24) MemoryCard card24;
    @BindView(R.id.card31) MemoryCard card31;
    @BindView(R.id.card32) MemoryCard card32;
    @BindView(R.id.card33) MemoryCard card33;
    @BindView(R.id.card34) MemoryCard card34;
    @BindView(R.id.card41) MemoryCard card41;
    @BindView(R.id.card42) MemoryCard card42;
    @BindView(R.id.card43) MemoryCard card43;
    @BindView(R.id.card44) MemoryCard card44;

    private GameViewModel mViewModel;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GameViewModel.class);


        Board board = new Board();
        board.addAll(Arrays.asList(
                card11, card12, card13, card14,
                card21, card22, card23, card24,
                card31, card32, card33, card34,
                card41, card42, card43, card44));

        mViewModel.setBoard(board, new ImageService());

        for (MemoryCard memoryCard : board) {
            memoryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.onClick(v);
                }
            });
        }

        setObservers();

        //TODO: Add navigation, backbutton, and apply layout style.
    }

    private void updateUI(Board board) {
        //TODO: implement method body
    }

    private void setObservers() {
        mViewModel.getCard11LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard12LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard13LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard14LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard21LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard22LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard23LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard24LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard31LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard32LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard33LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard34LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard41LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard42LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard43LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

        mViewModel.getCard44LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                bindImage(memoryCard);
                Log.d("onChanged", "YAY");
            }
        });

    }

    private void bindImage(MemoryCard... memoryCard) {

        for (MemoryCard card : memoryCard) {
            if (card != null) {
                if (!card.isFound()) {
                    if (card.isRevealed()) {
                        Glide.with(getContext())
                                .load(R.drawable.ic_question_mark)
                                .into(card);
                        card.setContentDescription(getString(R.string.card_faced_down,
                                card.getRowPosition(),
                                card.getColPosition()));
                    } else {
                        Glide.with(getContext())
                                .load(card.getSrc())
                                .into(card);
                        card.setContentDescription(getString(R.string.card_revealed,
                                card.getRowPosition(),
                                card.getColPosition(),
                                card.getDescription()));
                    }
                } else {
                    Glide.with(getContext())
                            .load(R.drawable.ic_launcher_background)
                            .into(card);
                    card.setContentDescription(getString(R.string.card_found,
                            card.getRowPosition(),
                            card.getColPosition()));
                    card.setEnabled(false);
                }
            }
        }
    }

}

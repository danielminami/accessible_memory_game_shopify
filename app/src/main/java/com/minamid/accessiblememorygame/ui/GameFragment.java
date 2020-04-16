package com.minamid.accessiblememorygame.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
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
import com.minamid.accessiblememorygame.model.Announcements;
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

        // TODO: block screen until service call returns

        Board board = new Board();
        board.addAll(Arrays.asList(
                card11, card12, card13, card14,
                card21, card22, card23, card24,
                card31, card32, card33, card34,
                card41, card42, card43, card44));

        setObservers();

        if (mViewModel.getIsGameStarted() == null) {
            mViewModel.setBoard(board, new ImageService());
        } else {
            mViewModel.refreshBoard();
        }

        for (MemoryCard memoryCard : board) {
            memoryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.onClick(v);
                }
            });
        }

        //TODO: Add navigation, backbutton, and apply layout style.
    }

    private void updateUI(Board board) {
        //TODO: implement method body
    }

    private void setObservers() {
        mViewModel.getCard11LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card11, memoryCard);
                bindImage(card11);
                Log.d("onChanged", "Card11");
            }
        });

        mViewModel.getCard12LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card12, memoryCard);
                bindImage(card12);
                Log.d("onChanged", "Card12");
            }
        });

        mViewModel.getCard13LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card13, memoryCard);
                bindImage(card13);
                Log.d("onChanged", "Card13");
            }
        });

        mViewModel.getCard14LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card14, memoryCard);
                bindImage(card14);
                Log.d("onChanged", "Card14");
            }
        });

        mViewModel.getCard21LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card21, memoryCard);
                bindImage(card21);
                Log.d("onChanged", "Card21");
            }
        });

        mViewModel.getCard22LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card22, memoryCard);
                bindImage(card22);
                Log.d("onChanged", "Card22");
            }
        });

        mViewModel.getCard23LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card23, memoryCard);
                bindImage(card23);
                Log.d("onChanged", "Card23");
            }
        });

        mViewModel.getCard24LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card24, memoryCard);
                bindImage(card24);
                Log.d("onChanged", "Card24");
            }
        });

        mViewModel.getCard31LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card31, memoryCard);
                bindImage(card31);
                Log.d("onChanged", "Card31");
            }
        });

        mViewModel.getCard32LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card32, memoryCard);
                bindImage(card32);
                Log.d("onChanged", "Card32");
            }
        });

        mViewModel.getCard33LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card33, memoryCard);
                bindImage(card33);
                Log.d("onChanged", "Card33");
            }
        });

        mViewModel.getCard34LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card34, memoryCard);
                bindImage(card34);
                Log.d("onChanged", "Card34");
            }
        });

        mViewModel.getCard41LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card41, memoryCard);
                bindImage(card41);
                Log.d("onChanged", "Card41");
            }
        });

        mViewModel.getCard42LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card42, memoryCard);
                bindImage(card42);
                Log.d("onChanged", "Card42");
            }
        });

        mViewModel.getCard43LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card43, memoryCard);
                bindImage(card43);
                Log.d("onChanged", "Card43");
            }
        });

        mViewModel.getCard44LiveData().observe(this, new Observer<MemoryCard>() {
            @Override
            public void onChanged(@Nullable MemoryCard memoryCard) {
                setLiveDataIntoView(card44, memoryCard);
                bindImage(card44);
                Log.d("onChanged", "Card44");
            }
        });

        mViewModel.getIsWinnerLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    String message = String.format("You Win with XX movements!");
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Good Job!");
                    alert.setMessage(message);
                    alert.setPositiveButton("Return to Lobby", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().onBackPressed();
                        }
                    });
                    alert.show();
                }
                Log.d("onChanged", "Winner Observer: " + aBoolean.toString());
            }
        });

        mViewModel.getIsScreenLock().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                // TODO: Unlock Screen Here
                Log.d("onChanged", "ScreenLock Observer: " + aBoolean.toString());
            }
        });

        mViewModel.getAnnounceableLiveData().observe(this, new Observer<Announcements>() {
            @Override
            public void onChanged(@Nullable Announcements announcements) {
                // TODO: Create a enum class for this accessibility announcement
                if (announcements.equals("START_GAME")) {
                    getView().announceForAccessibility("GAME WILL START");
                }
                Log.d("onChanged", "ScreenLock Observer: " + announcements.toString());
            }
        });

    }
    
    private void setLiveDataIntoView(MemoryCard viewMemoryCard, MemoryCard liveDataMemoryCard) {
        viewMemoryCard.setRevealed(liveDataMemoryCard.isRevealed());
        viewMemoryCard.setSrc(liveDataMemoryCard.getSrc());
        viewMemoryCard.setEnabled(liveDataMemoryCard.isEnabled());
        viewMemoryCard.setFound(liveDataMemoryCard.isFound());
        viewMemoryCard.setImageId(liveDataMemoryCard.getImageId());
        viewMemoryCard.setColPosition(liveDataMemoryCard.getColPosition());
        viewMemoryCard.setRowPosition(liveDataMemoryCard.getRowPosition());
        viewMemoryCard.setDescription(liveDataMemoryCard.getDescription());
    }

    private void bindImage(MemoryCard... memoryCard) {
        // TODO: Refactor to remove Glide Repetition
        for (MemoryCard card : memoryCard) {
            if (card != null) {
                if (!card.isFound()) {
                    if (card.isRevealed()) {
                        Glide.with(getContext())
                                .load(card.getSrc())
                                .into(card);
                        card.setContentDescription(getString(R.string.card_revealed,
                                card.getRowPosition(),
                                card.getColPosition(),
                                card.getDescription()));
                    } else {
                        Glide.with(getContext())
                                .load(R.drawable.ic_question_mark)
                                .into(card);
                        card.setContentDescription(getString(R.string.card_faced_down,
                                card.getRowPosition(),
                                card.getColPosition()));
                    }
                } else {
                    Glide.with(getContext())
                            .load(R.drawable.ic_match)
                            .into(card);
                    card.setContentDescription(getString(R.string.card_found, card.getDescription()));
                    card.setEnabled(false);
                }
            }
        }
    }

}

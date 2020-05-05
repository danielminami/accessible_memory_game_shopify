package com.minamid.accessiblememorygame.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.base.CustomFragment;
import com.minamid.accessiblememorygame.model.Announcements;
import com.minamid.accessiblememorygame.model.BoardSize;
import com.minamid.accessiblememorygame.model.MemoryCard;
import com.minamid.accessiblememorygame.service.ImageService;
import com.minamid.accessiblememorygame.util.AccessibilityUtils;
import com.minamid.accessiblememorygame.util.Config;
import com.minamid.accessiblememorygame.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameBoardFragment extends CustomFragment{

    @BindView(R.id.game_grid_layout) GridView gameGridView;
    @BindView(R.id.txt_player_moves) TextView txt_player_moves;
    @BindView(R.id.btn_restart_game) Button btn_restart_game;
    @BindView(R.id.txt_remaining_pairs) TextView txt_remaining_pairs;
    @BindView(R.id.loading_progress_bar_container) ConstraintLayout loading_progress_bar_container;

    private GameViewModel mViewModel;
    private List<MemoryCard> board = new ArrayList<>();
    private BoardAdapter customAdapter;
    private final int NO_CARD_FOUND = 99;

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
        startGame();
    }

    private void startGame() {

        int numOfColumns = Utils.getBoardSize(Config.getInstance().getNumberOfCards());
        BoardSize boardSize = BoardSize.getBoardSize(Config.getInstance().getNumberOfCards());

        for (int i = 0; i < Config.getInstance().getNumberOfCards(); i++) {
            board.add(new MemoryCard(getContext()));
        }

        setPositions(board, numOfColumns);

        if (mViewModel.getIsGameStarted() == null) {
            mViewModel.setBoard(board, numOfColumns, new ImageService());
        } else {
            mViewModel.refreshBoard();
            gameGridView.setAdapter(null);
        }

        setObservers();

        txt_player_moves.setText(getString(R.string.txt_user_moves, mViewModel.getPlayerMoves().getValue().intValue()));
        txt_remaining_pairs.setText(getString(R.string.txt_remaining_pairs, mViewModel.getRemainingPairs().getValue().intValue()));
        customAdapter = new BoardAdapter(getContext(), board, this, Utils.getGridViewSize(getActivity(), boardSize));

        gameGridView.setVerticalSpacing(Utils.getGridViewVerticalSpacing(boardSize));
        gameGridView.setNumColumns(numOfColumns);
        gameGridView.setAdapter(customAdapter);

        setListeners();
    }

    private void resetGame() {
        board.clear();
        gameGridView.setAdapter(null);
        mViewModel.resetGame();
        startGame();
    }

    private void setPositions(List<MemoryCard> cardList, int numOfColumns) {
        int i = 1, j = 1;
        for (MemoryCard card : cardList) {
            card.setRowPosition(i);
            card.setColPosition(j);
            i = j == numOfColumns ? ++i : i;
            j = j == numOfColumns ? 1 : ++j;
            card.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);
        }
    }

    private void setListeners() {
        gameGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
                    View child = ((ViewGroup)view).getChildAt(i);
                    MemoryCard card = (MemoryCard)child;
                    if (card.isEnabled()) {
                        mViewModel.onClick(child);
                    }
                    break;
                }
            }
        });

        btn_restart_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private void setObservers() {

        for (MutableLiveData<MemoryCard> memoryCardMutableLiveData : mViewModel.getCardListLiveData()) {
            memoryCardMutableLiveData.observe(this, new Observer<MemoryCard>() {
                @Override
                public void onChanged(@Nullable MemoryCard memoryCard) {
                    int index = findMatchingCardIndex(memoryCard);
                    if (index != NO_CARD_FOUND) {
                        setLiveDataIntoView(board.get(index), memoryCard);
                        bindImage(board.get(index));
                        synchronized (customAdapter) {
                            customAdapter.notifyDataSetChanged();
                        }
                        if (memoryCard.isShouldAnnounce()) {
                            AccessibilityUtils.announceForAccessibility(memoryCard,
                                    getString(R.string.card_revealed,
                                            memoryCard.getRowPosition(),
                                            memoryCard.getColPosition(),
                                            memoryCard.getDescription()));
                        }

                        Log.d("onChanged_OBSERVER", memoryCard.getRowPosition() + " - " + memoryCard.getColPosition());
                    }
                }
            });
        }

        mViewModel.getIsWinnerLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean winner) {
                // TODO: Create a share with Friends
                // TODO: Create a Rank and integrate with a Service
                if (winner) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle(getString(R.string.winner_header));
                    alert.setMessage(getString(R.string.winner_body_message, mViewModel.getGameTimeInSeconds()));
                    alert.setPositiveButton(getString(R.string.winner_button_text), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().onBackPressed();
                        }
                    });
                    alert.show();
                }
                Log.d("onChanged", "Winner Observer: " + winner.toString());
            }
        });

        mViewModel.getIsScreenLock().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isVisible) {
                if (isVisible) {
                    loading_progress_bar_container.setVisibility(View.VISIBLE);
                } else {
                    loading_progress_bar_container.setVisibility(View.GONE);
                }
                Log.d("onChanged", "ScreenLock Observer");
            }
        });

        mViewModel.getIsResetEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean shouldResetButtonEnable) {
                btn_restart_game.setEnabled(shouldResetButtonEnable);
            }
        });

        mViewModel.getAnnounceableLiveData().observe(this, new Observer<Announcements>() {
            @Override
            public void onChanged(@Nullable Announcements announcements) {
                String textToBeAnnounced = "";
                switch (announcements) {
                    case START_GAME:
                        textToBeAnnounced = getString(R.string.game_start);
                        break;
                    case TIME_TO_EXPLORE:
                        textToBeAnnounced = getString(R.string.time_to_explore, Config.getInstance().getTimeBoardRevealed());
                        break;
                }
                AccessibilityUtils.announceForAccessibility(getView(),textToBeAnnounced);
                Log.d("onChanged", "ScreenLock Observer: " + announcements.toString());
            }
        });

        mViewModel.getPlayerMoves().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer moves) {
                txt_player_moves.setText(getString(R.string.txt_user_moves, moves));
            }
        });

        mViewModel.getRemainingPairs().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer remainingPairs) {
                txt_remaining_pairs.setText(getString(R.string.txt_remaining_pairs, remainingPairs));
            }
        });
    }

    private int findMatchingCardIndex(MemoryCard memoryCard) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).getRowPosition() == memoryCard.getRowPosition() &&
                    board.get(i).getColPosition() == memoryCard.getColPosition()) {
                return i;
            }
        }
        return NO_CARD_FOUND;
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

    public void bindImage(MemoryCard... memoryCard) {
        // TODO: Refactor to remove Glide Repetition
        for (MemoryCard card : memoryCard) {
            if (card != null) {
                if (!card.isFound()) {
                    if (card.isRevealed()) {
                        Glide.with(getContext())
                                .load(card.getSrc())
                                .into(card);
                        card.setContentDescription(card.getDescription());
                    } else {
                        Glide.with(getContext())
                                .load(R.drawable.ic_question_mark)
                                .into(card);
                        card.setContentDescription(getString(R.string.card_faced_down));
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
        synchronized (customAdapter) {
            customAdapter.notifyDataSetChanged();
        }
    }
}

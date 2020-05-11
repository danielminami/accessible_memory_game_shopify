package com.minamid.accessiblememorygame.ui.game;

import android.app.AlertDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.minamid.accessiblememorygame.MainActivity;
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
    private MediaPlayer matchSound;
    private MediaPlayer winnerSound;

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

    /**
     * onResume here is to show the Activity's Action Bar Back Button.
     */
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setSupportActionBar(true, "");
    }

    /**
     * This method starts the game. It calls a chain of functions the create UI and setup LiveData.
     *
     */
    private void startGame() {

        int numOfCards = Config.getInstance().getNumberOfCards();
        int numOfColumns = Utils.getBoardSize(numOfCards);

        setBoard(numOfCards);

        setPositions(board, numOfColumns);

        setViewModel(numOfColumns);

        setObservers();

        setMediaResources();

        setUI(numOfCards, numOfColumns);

        setListeners();
    }

    /**
     * Restarts the game. This resets the arrays, the adapter and the ViewModel
     */
    private void resetGame() {
        board.clear();
        gameGridView.setAdapter(null);
        mViewModel.resetGame();
        startGame();
    }

    /**
     * Set positions X and Y for each MemoryCard
     *
     * @param cardList
     * @param numOfColumns
     */
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

    /**
     * Set GridView Click Listeners and Reset Button
     *
     */
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

    /**
     * Attaches observers to LiveData
     *
     */
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
                    }
                }
            });
        }

        mViewModel.getIsWinnerLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean winner) {
                if (winner != null && winner) {
                    if (Config.getInstance().isAccessibilityEnabled()) {
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
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putLong(getString(R.string.winner_bundle_value_winning_seconds_key),
                                mViewModel.getGameTimeInSeconds());
                        winnerSound.start();
                        DialogFragment winnerFragment = new WinnerFragment();
                        winnerFragment.setArguments(bundle);
                        winnerFragment.show(getFragmentManager(), "");
                    }
                }
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

        mViewModel.getIsMatchPlaySoundLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean shouldSkipSound) {
                if (!shouldSkipSound) matchSound.start();
            }
        });

        mViewModel.getIsNetworkError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isNetworkError) {
                if (isNetworkError) {
                    Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Setup UI components
     *
     * @param numOfCards
     * @param numOfColumns
     */
    private void setUI(int numOfCards, int numOfColumns) {
        BoardSize boardSize = BoardSize.getBoardSize(numOfCards);
        txt_player_moves.setText(getString(R.string.txt_user_moves, mViewModel.getPlayerMoves().getValue().intValue()));
        txt_remaining_pairs.setText(getString(R.string.txt_remaining_pairs, mViewModel.getRemainingPairs().getValue().intValue()));
        customAdapter = new BoardAdapter(getContext(), board, this, Utils.getGridViewSize(getActivity(), boardSize));

        gameGridView.setVerticalSpacing(Utils.getGridViewVerticalSpacing(boardSize));
        gameGridView.setNumColumns(numOfColumns);
        gameGridView.setAdapter(customAdapter);
    }

    /**
     * Setup Game Sounds
     */
    private void setMediaResources() {
        matchSound = MediaPlayer.create(getContext(), R.raw.match);
        winnerSound = MediaPlayer.create(getContext(), R.raw.win);
    }

    /**
     * Create the UI MemoryCard elements
     *
     * @param numOfCards
     */
    private void setBoard(int numOfCards) {
        for (int i = 0; i < numOfCards; i++) {
            MemoryCard memoryCard = new MemoryCard(getContext());
            memoryCard.setImageResource(R.drawable.ic_question_mark);
            board.add(memoryCard);
        }
    }

    /**
     * Starts the View Model by calling setBoard.
     * Also used to reset the game
     *
     * @param numOfColumns
     */
    private void setViewModel(int numOfColumns) {
        if (mViewModel.getIsGameStarted() == null) {
            mViewModel.setBoard(board, numOfColumns, new ImageService());
        } else {
            mViewModel.refreshBoard();
            gameGridView.setAdapter(null);
        }
    }

    /**
     * Finds the UI component related to the LiveData
     *
     * @param memoryCard
     * @return
     */
    private int findMatchingCardIndex(MemoryCard memoryCard) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).getRowPosition() == memoryCard.getRowPosition() &&
                    board.get(i).getColPosition() == memoryCard.getColPosition()) {
                return i;
            }
        }
        return NO_CARD_FOUND;
    }

    /**
     * Updates the View with data coming from the ViewModel
     *
     * @param viewMemoryCard
     * @param liveDataMemoryCard
     */
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

    /**
     * Binds data in the MemoryCard Element. Notify's the Adapter about data changes.
     *
     * @param memoryCard
     */
    public void bindImage(MemoryCard... memoryCard) {
        // TODO: Think how can I make this better...
        for (MemoryCard card : memoryCard) {
            if (card != null) {
                if (!card.isFound()) {
                    if (card.isRevealed()) {
                        Glide.with(getContext())
                                .load(card.getSrc())
                                .transition(GenericTransitionOptions.with(R.anim.slide_in_left))
                                .into(card);
                        card.setContentDescription(card.getDescription());
                    } else {
                        Glide.with(getContext())
                                .load(R.drawable.ic_question_mark)
                                .transition(GenericTransitionOptions.with(R.anim.slide_in_left))
                                .into(card);
                        card.setContentDescription(getString(R.string.card_faced_down));
                    }
                } else {
                    if (Config.getInstance().isAccessibilityEnabled()) {
                        Glide.with(getContext())
                                .load(R.drawable.ic_match)
                                .into(card);
                        card.setContentDescription(getString(R.string.card_found, card.getDescription()));
                    } else {
                        card.setVisibility(View.GONE);
                    }
                    card.setEnabled(false);
                }
            }
        }
        synchronized (customAdapter) {
            customAdapter.notifyDataSetChanged();
        }
    }
}

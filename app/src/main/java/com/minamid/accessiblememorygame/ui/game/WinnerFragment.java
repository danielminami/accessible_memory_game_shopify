package com.minamid.accessiblememorygame.ui.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.minamid.accessiblememorygame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WinnerFragment extends DialogFragment {

    @BindView(R.id.txt_winner_message) TextView txt_winner_message;
    @BindView(R.id.btn_main_menu) Button btn_main_menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.winner_layout, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        txt_winner_message.setText(getString(R.string.winner_body_message,bundle.getLong(
                getString(R.string.winner_bundle_value_winning_seconds_key))));
        btn_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().onBackPressed();
    }
}

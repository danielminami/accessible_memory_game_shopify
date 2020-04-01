package com.minamid.accessiblememorygame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.minamid.accessiblememorygame.R;
import com.minamid.accessiblememorygame.util.NavigateTo;

public class CustomFragment extends Fragment implements NavigateTo {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", getClassName(this));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", getClassName(this));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause", getClassName(this));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", getClassName(this));
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop", getClassName(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", getClassName(this));
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);
        if (addToBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    private String getClassName(CustomFragment customFragment) {
        if (customFragment == null) return "no_name";
        return customFragment.getClass().getSimpleName();
    }

}

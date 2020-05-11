package com.minamid.accessiblememorygame.ui.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.minamid.accessiblememorygame.MainActivity;
import com.minamid.accessiblememorygame.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setSupportActionBar(true,
                getString(R.string.title_activity_settings));
    }
}

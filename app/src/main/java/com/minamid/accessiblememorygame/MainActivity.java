package com.minamid.accessiblememorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minamid.accessiblememorygame.ui.MainFragment;
import com.minamid.accessiblememorygame.util.Config;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Config.getInstance().CACHE_LOCATION = getApplicationContext().getCacheDir();

        // TODO: Authenticate using Google
        // TODO: Authenticate using Facebook
        // TODO: Authenticate using Firebase with email account
        // TODO: Save authentication in Firebase
        // TODO: How can I write some tests for this app?
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}

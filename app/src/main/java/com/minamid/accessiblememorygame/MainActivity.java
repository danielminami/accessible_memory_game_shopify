package com.minamid.accessiblememorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minamid.accessiblememorygame.ui.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Authenticate using Google
        // TODO: Authenticate using Facebook
        // TODO: Authenticate using Firebase with email account
        // TODO: Save authentication in Firebase
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}

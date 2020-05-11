package com.minamid.accessiblememorygame;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.minamid.accessiblememorygame.ui.MainFragment;
import com.minamid.accessiblememorygame.util.Config;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Config.getInstance().CACHE_LOCATION = getApplicationContext().getCacheDir();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setSupportActionBar(boolean shouldDisplayTopBackButton, String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(shouldDisplayTopBackButton);
        actionBar.setDisplayShowHomeEnabled(shouldDisplayTopBackButton);
        actionBar.setTitle(title);
    }
}

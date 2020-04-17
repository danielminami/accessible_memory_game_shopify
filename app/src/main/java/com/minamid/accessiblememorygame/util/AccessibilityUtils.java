package com.minamid.accessiblememorygame.util;

import android.view.View;

public class AccessibilityUtils {

    public static void announceForAccessibility(final View view, final CharSequence announcement) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.announceForAccessibility(announcement);
            }
        }, 200);
    }

}

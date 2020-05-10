package com.minamid.accessiblememorygame.util;

import android.view.View;

public class AccessibilityUtils {

    /**
     * This method was created because some devices does not play the announcement without a
     * handler.
     *
     * @param view
     * @param announcement
     */
    public static void announceForAccessibility(final View view, final CharSequence announcement) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.announceForAccessibility(announcement);
            }
        }, 200);
    }

}

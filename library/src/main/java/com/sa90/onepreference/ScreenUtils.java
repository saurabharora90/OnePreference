package com.sa90.onepreference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * Created by Saurabh Arora on 20/11/16.
 */

class ScreenUtils {

    private static final double PHONE_THRESHOLD = 6.5;
    private static int TABLET_THRESHOLD = 600;

    @SuppressLint("NewApi")
    static boolean isPhone(Activity activity) {
        if (isSDKAbove(Build.VERSION_CODES.HONEYCOMB_MR2)) {
            if (activity != null && activity.getResources() != null) {
                Configuration config = activity.getResources().getConfiguration();
                return config.smallestScreenWidthDp < TABLET_THRESHOLD;
            }
            return getScreenSize(activity) < PHONE_THRESHOLD;
        } else {
            return getScreenSize(activity) < PHONE_THRESHOLD;
        }
    }

    private static boolean isSDKAbove(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

    private static double getScreenSize(Activity activity) {
        if (activity == null) return 0;
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }
}

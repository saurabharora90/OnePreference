package com.sa90.onepreference;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.annotation.XmlRes;

import com.sa90.onepreference.OnePreferencePhoneActivity;
import com.sa90.onepreference.OnePreferenceTabletActivity;
import com.sa90.onepreference.utils.ScreenUtils;

/**
 * Created by Saurabh Arora on 20/11/16.
 */

public class OnePreferenceHelper {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_SHOW_BACK = "extra_show_back";
    public static final String EXTRA_OVERRIDE_BACK_ICON = "extra_override_back_icon";
    public static final String EXTRA_HEADER_RES = "extra_header_res";

    /**
     * Launches the default {@link OnePreferencePhoneActivity} or {@link OnePreferenceTabletActivity} depending on the screen size
     * @param headerRes
     * @param title
     * @param callingActivity
     */
    public static void startActivity(@XmlRes int headerRes, String title, Activity callingActivity) {
        startActivity(headerRes, title, false, callingActivity);
    }

    /**
     * Launches the default {@link OnePreferencePhoneActivity} or {@link OnePreferenceTabletActivity} depending on the screen size
     * @param headerRes
     * @param title
     * @param showBack if the back icon should be shown next to the title in the toolbar
     * @param callingActivity
     */
    public static void startActivity(@XmlRes int headerRes, String title, boolean showBack, Activity callingActivity) {
        startActivity(headerRes, title, showBack, -1, callingActivity);
    }

    /**
     * Launches the default {@link OnePreferencePhoneActivity} or {@link OnePreferenceTabletActivity} depending on the screen size
     * @param headerRes
     * @param title
     * @param showBack if the back icon should be shown next to the title in the toolbar
     * @param upIcon can be -1 if you want to use the default icon as the back icon
     * @param callingActivity
     */
    public static void startActivity(@XmlRes int headerRes, String title, boolean showBack, @DrawableRes int upIcon, Activity callingActivity) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HEADER_RES, headerRes);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_SHOW_BACK, showBack);
        if (upIcon != -1)
            intent.putExtra(EXTRA_OVERRIDE_BACK_ICON, upIcon);

        if (ScreenUtils.isPhone(callingActivity))
            intent.setClass(callingActivity, OnePreferencePhoneActivity.class);
        else
            intent.setClass(callingActivity, OnePreferenceTabletActivity.class);

        callingActivity.startActivity(intent);
    }
}

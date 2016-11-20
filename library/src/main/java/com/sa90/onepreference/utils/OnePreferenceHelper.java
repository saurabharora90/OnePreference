package com.sa90.onepreference.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.annotation.XmlRes;

import com.sa90.onepreference.OnePreferencePhoneActivity;
import com.sa90.onepreference.OnePreferenceTabletActivity;

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
        startActivity(headerRes, title, showBack, upIcon, callingActivity, OnePreferencePhoneActivity.class, OnePreferenceTabletActivity.class);
    }

    /**
     * Allows you to specify a custom Activity to be used for Tablet or Phone mode.
     * @param headerRes
     * @param title
     * @param callingActivity
     * @param phoneActivityClass Activity to be used when in phone mode. Note: The Activity should inherit from {@link OnePreferencePhoneActivity}
     * @param tabletActivityClass Activity to be used when in tablet mode. Note: The Activity should inherit from {@link OnePreferenceTabletActivity}
     */
    public static void startActivity(@XmlRes int headerRes, String title, Activity callingActivity,
                                     Class<?> phoneActivityClass, Class<?> tabletActivityClass) {
        startActivity(headerRes, title, false, callingActivity, phoneActivityClass, tabletActivityClass);
    }

    /**
     * Allows you to specify a custom Activity to be used for Tablet or Phone mode.
     * @param headerRes
     * @param title
     * @param showBack if the back icon should be shown next to the title in the toolbar
     * @param callingActivity
     * @param phoneActivityClass Activity to be used when in phone mode. Note: The Activity should inherit from {@link OnePreferencePhoneActivity}
     * @param tabletActivityClass Activity to be used when in tablet mode. Note: The Activity should inherit from {@link OnePreferenceTabletActivity}
     */
    public static void startActivity(@XmlRes int headerRes, String title, boolean showBack, Activity callingActivity,
                                     Class<?> phoneActivityClass, Class<?> tabletActivityClass) {
        startActivity(headerRes, title, showBack, -1, callingActivity, phoneActivityClass, tabletActivityClass);
    }

    /**
     * Allows you to specify a custom Activity to be used for Tablet or Phone mode.
     * @param headerRes
     * @param title
     * @param showBack if the back icon should be shown next to the title in the toolbar
     * @param upIcon can be -1 if you want to use the default icon as the back icon
     * @param callingActivity
     * @param phoneActivityClass Activity to be used when in phone mode. Note: The Activity should inherit from {@link OnePreferencePhoneActivity}
     * @param tabletActivityClass Activity to be used when in tablet mode. Note: The Activity should inherit from {@link OnePreferenceTabletActivity}
     */
    public static void startActivity(@XmlRes int headerRes, String title, boolean showBack, @DrawableRes int upIcon, Activity callingActivity,
                                     Class<?> phoneActivityClass, Class<?> tabletActivityClass) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HEADER_RES, headerRes);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_SHOW_BACK, showBack);
        if (upIcon != -1)
            intent.putExtra(EXTRA_OVERRIDE_BACK_ICON, upIcon);

        if (ScreenUtils.isPhone(callingActivity))
            intent.setClass(callingActivity, phoneActivityClass);
        else
            intent.setClass(callingActivity, tabletActivityClass);

        callingActivity.startActivity(intent);
    }
}

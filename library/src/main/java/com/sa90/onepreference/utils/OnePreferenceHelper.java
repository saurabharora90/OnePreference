package com.sa90.onepreference.utils;

import android.app.Activity;
import android.content.Intent;

import com.sa90.onepreference.PhonePreferenceActivity;

/**
 * Created by Saurabh Arora on 20/11/16.
 */

public class OnePreferenceHelper {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_SHOW_BACK = "extra_show_back";
    public static final String EXTRA_OVERRIDE_BACK_ICON = "extra_override_back_icon";
    public static final String EXTRA_HEADER_RES = "extra_header_res";

    /**
     * Instead a launching the correct activity, we return an Intent to allow the user to add any IntentFlags or use startActivityForResult or anything else as needed by the calling activity.
     *
     * @param context
     * @return intent pointing to the correct activity, i.e {@link PhonePreferenceActivity} for phone and {@link com.viki.android.settings.TabletPreferenceActivity} for tablets
     */
    public static Intent getPreferenceActivityIntent(Activity context) {
        if (ScreenUtils.isPhone(context))
            return new Intent(context, PhonePreferenceActivity.class);
        else
            return new Intent(context, TabletPreferenceActivity.class);
    }

}

package com.sa90.onepreferencedemo.fragment;

import android.os.Bundle;

import com.sa90.onepreference.fragment.BasePreferenceFragment;
import com.sa90.onepreferencedemo.R;

/**
 * Created by saurabharora on 20/11/16.
 */

public class Header2Fragment extends BasePreferenceFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_header2, rootKey);
    }
}
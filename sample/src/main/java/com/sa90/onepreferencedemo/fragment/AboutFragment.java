package com.sa90.onepreferencedemo.fragment;

import android.os.Bundle;

import com.sa90.onepreference.fragment.BaseOnePreferenceFragment;
import com.sa90.onepreferencedemo.R;

public class AboutFragment extends BaseOnePreferenceFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_about, rootKey);
    }
}

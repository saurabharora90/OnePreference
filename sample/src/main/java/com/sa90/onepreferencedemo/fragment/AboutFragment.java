package com.sa90.onepreferencedemo.fragment;

import android.os.Bundle;

import com.sa90.onepreference.OnePreferenceFragmentCompat;
import com.sa90.onepreferencedemo.R;

public class AboutFragment extends OnePreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_about, rootKey);
    }
}

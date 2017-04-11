package com.sa90.onepreference.fragment;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

/**
 * Created by Saurabh Arora on 20/11/16.
 *
 * Base class for {@link PreferenceFragmentCompat} to be used for anyone working with OnePreference
 */

public abstract class BaseOnePreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setNestedScrollingEnabled(false);
    }
}

package com.sa90.onepreference.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.preference.PreferenceFragmentCompat;

import org.jetbrains.annotations.NotNull;

/**
 * Base class for {@link PreferenceFragmentCompat} to be used for anyone working with OnePreference
 */

public abstract class BaseOnePreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setNestedScrollingEnabled(false);
    }
}

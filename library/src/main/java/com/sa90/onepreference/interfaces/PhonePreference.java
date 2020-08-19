package com.sa90.onepreference.interfaces;

import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public interface PhonePreference extends OnePreference {

    /**
     * Returns the id of the {@link android.widget.LinearLayout} that will act as a container for the Fragments.
     * The orientation of the LinearLayout should be set to {@link android.widget.LinearLayout#VERTICAL}.
     *
     * @return the {@link android.widget.LinearLayout} that will hold the fragments
     */
    @Nullable
    LinearLayout getFragmentContainerForPhone();
}

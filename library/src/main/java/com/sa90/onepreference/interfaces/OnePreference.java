package com.sa90.onepreference.interfaces;

import android.support.annotation.LayoutRes;
import android.support.annotation.XmlRes;

/**
 * Created by Saurabh Arora on 20/11/16.
 */

public interface OnePreference {

    /**
     * Get the id of the header file that contains the information about the preference.
     * Have a look at the sample to understand how to create the Header File.
     *
     * @return
     */
    @XmlRes
    int getHeaderFile();

    /**
     * Returns the id of the {@link android.widget.LinearLayout} that will act as a container for the Fragments.
     * The orientation of the LinearLayout should be set to {@link android.widget.LinearLayout#VERTICAL}.
     * The value will be ignored for tablets as tablet activity will be built on {@link android.preference.PreferenceActivity}
     *
     * @return the id of the {@link android.widget.LinearLayout} that will hold the fragments
     */
    int getFragmentContainerId();

    /**
     * Gets the layout id for the layout to be used by this activity.
     * For tablet this value will be ignored as tablet activity will be built on {@link android.preference.PreferenceActivity}
     *
     * @return
     */
    @LayoutRes
    int getLayoutRes();
}

package com.sa90.onepreference.interfaces;

import android.support.annotation.Nullable;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public interface TabletPreference extends OnePreference{

    /**
     * Returns the id of the {@link FrameLayout} that will act as a container for the Fragments.
     *
     * @return the {@link FrameLayout} that will hold the fragments
     */
    @Nullable FrameLayout getFragmentContainerForTablet();

    /**
     * Returns the {@link ListView} that will hold the Header List.
     *
     * @return
     */
    @Nullable ListView getHeaderListview();

    /**
     * Returns the adapter that will be attached to the header ListView
     * @return
     */
    @Nullable BaseAdapter getHeaderListAdapter();
}
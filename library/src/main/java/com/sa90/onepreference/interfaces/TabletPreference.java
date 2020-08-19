package com.sa90.onepreference.interfaces;

import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sa90.onepreference.model.Header;

public interface TabletPreference extends OnePreference {

    /**
     * Returns the id of the {@link FrameLayout} that will act as a container for the Fragments.
     *
     * @return the {@link FrameLayout} that will hold the fragments
     */
    @Nullable
    FrameLayout getFragmentContainerForTablet();

    /**
     * Returns the {@link RecyclerView} that will hold the Header List in tablet mode.
     * In Phone mode, we don't display a separate header list.
     */
    @Nullable
    RecyclerView getHeaderRecyclerView();

    /**
     * Returns the adapter that will be attached to the header RecyclerView.
     * If you want to use the default adapter, then pass an instance to {@link com.sa90.onepreference.adapter.HeaderAdapter}
     *
     * @return
     */
    @Nullable
    ListAdapter<Header, ? extends RecyclerView.ViewHolder> getHeaderAdapter();
}

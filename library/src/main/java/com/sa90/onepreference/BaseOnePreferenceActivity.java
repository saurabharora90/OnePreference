package com.sa90.onepreference;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sa90.onepreference.fragment.BaseOnePreferenceFragment;
import com.sa90.onepreference.helper.PhonePreferenceHelper;
import com.sa90.onepreference.helper.PreferenceHelper;
import com.sa90.onepreference.helper.TabletPreferenceHelper;
import com.sa90.onepreference.interfaces.PhonePreference;
import com.sa90.onepreference.interfaces.TabletPreference;
import com.sa90.onepreference.model.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseOnePreferenceActivity extends AppCompatActivity
        implements PhonePreference, TabletPreference {

    PhonePreferenceHelper mPhonePreferenceHelper;
    TabletPreferenceHelper mTabletPreferenceHelper;
    ListAdapter<Header, ? extends RecyclerView.ViewHolder> mHeaderAdapter;

    /**
     * Returns true if the the preferences are being displayed in tablet mode.
     *
     * @return
     */
    public boolean isInTabletMode() {
        return getFragmentContainerForPhone() == null;
    }

    /**
     * Provides the subclasses with an opportunity to alter (remove/add/edit) the list of {@link Header}
     * and the corresponding {@link BaseOnePreferenceFragment} that will displayed.
     * <p>
     * This is a good place to add or remove any items that should be rendered depending on the app state.
     *
     * @param targets
     */
    public void touchUpHeadersBeforeDisplay(List<Header> targets) {

    }

    /**
     * Call when you need to change the headers being displayed. Will result
     * in the list being repopulated and {{@link #touchUpHeadersBeforeDisplay(List)}} being called before being displayed again
     */
    public void invalidateHeaders() {
        init();
    }

    /**
     * Displays the PreferenceFragmentCompat associated with the header
     *
     * @param clickedHeader
     */
    public void showFragmentForHeader(Header clickedHeader) {
        mTabletPreferenceHelper.switchFragment(clickedHeader);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        init();
    }

    private void init() {
        LinearLayout llContainer = getFragmentContainerForPhone();
        FrameLayout flContainer = getFragmentContainerForTablet();

        if (llContainer != null)
            setupForPhone();
        else if (flContainer != null)
            setupForTablet();
    }

    private void setupForPhone() {
        if (mPhonePreferenceHelper == null)
            mPhonePreferenceHelper = new PhonePreferenceHelper(this, this);

        mPhonePreferenceHelper.setupScreen(getHeadersList());
    }

    private void setupForTablet() {
        RecyclerView rv = getHeaderRecyclerView();
        Objects.requireNonNull(rv);
        if (mTabletPreferenceHelper == null) {
            mTabletPreferenceHelper = new TabletPreferenceHelper(this, this);
            mHeaderAdapter = getHeaderAdapter();
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(mHeaderAdapter);
        }
        List<Header> headerList = getHeadersList();
        mHeaderAdapter.submitList(headerList);
    }

    private List<Header> getHeadersList() {
        List<Header> headerList = new ArrayList<>();
        PreferenceHelper.loadHeadersFromResource(getHeaderFile(), headerList, this);
        touchUpHeadersBeforeDisplay(headerList);
        return headerList;
    }
}

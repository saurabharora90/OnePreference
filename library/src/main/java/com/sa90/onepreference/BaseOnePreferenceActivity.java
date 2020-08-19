package com.sa90.onepreference;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sa90.onepreference.fragment.BaseOnePreferenceFragment;
import com.sa90.onepreference.helper.PhonePreferenceHelper;
import com.sa90.onepreference.helper.PreferenceHelper;
import com.sa90.onepreference.helper.TabletPreferenceHelper;
import com.sa90.onepreference.interfaces.PhonePreference;
import com.sa90.onepreference.interfaces.TabletPreference;
import com.sa90.onepreference.model.Header;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseOnePreferenceActivity extends AppCompatActivity
        implements PhonePreference, TabletPreference, AdapterView.OnItemClickListener {

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
     * Provides the subclasses with an opportunity to handle a click on a header item
     * @param clickedHeader
     * @param position
     */
    public void headerClicked(Header clickedHeader, int position) {
        mTabletPreferenceHelper.switchFragment(clickedHeader);
    }

    PhonePreferenceHelper mPhonePreferenceHelper;
    TabletPreferenceHelper mTabletPreferenceHelper;
    ArrayAdapter<Header> mHeaderAdapter;

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
        ListView lv = getHeaderListView();
        if(mTabletPreferenceHelper == null) {
            mTabletPreferenceHelper = new TabletPreferenceHelper(this, this);
            mHeaderAdapter = getHeaderListAdapter(getHeadersList());
            lv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            lv.setAdapter(mHeaderAdapter);
            lv.setOnItemClickListener(this);
        }
        else {
            mHeaderAdapter.clear();
            mHeaderAdapter.addAll(getHeadersList());
            mHeaderAdapter.notifyDataSetChanged();
        }
        Header clickedHeader = mHeaderAdapter.getItem(0);
        headerClicked(clickedHeader, 0);
        lv.setItemChecked(0,true);
    }

    private List<Header> getHeadersList() {
        List<Header> headerList = new ArrayList<>();
        PreferenceHelper.loadHeadersFromResource(getHeaderFile(), headerList, this);
        touchUpHeadersBeforeDisplay(headerList);
        return headerList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Header clickedHeader = mHeaderAdapter.getItem(position);
        headerClicked(clickedHeader, position);
    }
}

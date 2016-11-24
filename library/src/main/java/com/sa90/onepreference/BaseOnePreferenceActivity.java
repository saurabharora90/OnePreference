package com.sa90.onepreference;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sa90.onepreference.helper.PhonePreferenceHelper;
import com.sa90.onepreference.helper.PreferenceHelper;
import com.sa90.onepreference.interfaces.PhonePreference;
import com.sa90.onepreference.interfaces.TabletPreference;
import com.sa90.onepreference.model.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public abstract class BaseOnePreferenceActivity extends AppCompatActivity
        implements PhonePreference, TabletPreference {

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
     * and the corresponding {@link com.sa90.onepreference.fragment.BasePreferenceFragment} that will displayed.
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

    PhonePreferenceHelper mPhonePreferenceHelper;

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

        List<Header> headerList = new ArrayList<>();
        PreferenceHelper.loadHeadersFromResource(getHeaderFile(), headerList, this);
        touchUpHeadersBeforeDisplay(headerList);
        mPhonePreferenceHelper.setupScreen(headerList);
    }

    private void setupForTablet() {

    }
}

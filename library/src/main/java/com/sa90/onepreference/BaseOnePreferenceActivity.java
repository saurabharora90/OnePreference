package com.sa90.onepreference;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sa90.onepreference.helper.PhonePreferenceHelper;
import com.sa90.onepreference.interfaces.PhonePreference;
import com.sa90.onepreference.interfaces.TabletPreference;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public abstract class BaseOnePreferenceActivity extends AppCompatActivity
        implements PhonePreference, TabletPreference {

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout llContainer = getFragmentContainerForPhone();
        FrameLayout flContainer = getFragmentContainerForTablet();

        if(flContainer == null)
            setupForPhone(llContainer.getId());
        else
            setupForTablet(flContainer.getId());
    }

    private void setupForPhone(@IdRes int containerId) {
        PhonePreferenceHelper phonePreferenceHelper = new PhonePreferenceHelper(this, this);
        phonePreferenceHelper.setupScreen();
    }

    private void setupForTablet(@IdRes int containerId) {

    }
}

package com.sa90.onepreference.helper;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sa90.onepreference.interfaces.TabletPreference;
import com.sa90.onepreference.model.Header;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public class TabletPreferenceHelper {

    private TabletPreference mTabletPreference;
    private AppCompatActivity mActivity;

    public TabletPreferenceHelper(TabletPreference phonePreference, AppCompatActivity activity) {
        this.mTabletPreference = phonePreference;
        this.mActivity = activity;
    }

    public void switchFragment(Header clickedHeader) {
        PreferenceFragmentItem preferenceFragmentItem = new PreferenceFragmentItem(clickedHeader.fragment,
                clickedHeader.fragment, clickedHeader.fragmentArguments);

        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        transaction.replace(mTabletPreference.getFragmentContainerForTablet().getId(),
                preferenceFragmentItem.createFragment(mActivity), preferenceFragmentItem.getTag());
        transaction.commit();
    }
}

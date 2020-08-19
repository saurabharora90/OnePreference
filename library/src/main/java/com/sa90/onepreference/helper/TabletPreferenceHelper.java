package com.sa90.onepreference.helper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.sa90.onepreference.interfaces.TabletPreference;
import com.sa90.onepreference.model.Header;

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

        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(mTabletPreference.getFragmentContainerForTablet().getId(),
                preferenceFragmentItem.createFragment(mActivity), preferenceFragmentItem.getTag());
        transaction.commit();
    }
}

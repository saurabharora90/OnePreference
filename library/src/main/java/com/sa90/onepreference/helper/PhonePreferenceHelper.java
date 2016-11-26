package com.sa90.onepreference.helper;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.sa90.onepreference.interfaces.PhonePreference;
import com.sa90.onepreference.model.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public class PhonePreferenceHelper {

    private PhonePreference mPhonePreference;
    private AppCompatActivity mActivity;

    public PhonePreferenceHelper(PhonePreference phonePreference, AppCompatActivity activity) {
        this.mPhonePreference = phonePreference;
        this.mActivity = activity;
    }

    public void setupScreen(List<Header> headerList) {
        setupFragments(mPhonePreference.getFragmentContainerForPhone(), getPreferenceFragmentList(headerList));
    }

    private void setupFragments(LinearLayout container, List<PreferenceFragmentItem> fragmentItemList) {
        if (container.getOrientation() != LinearLayout.VERTICAL)
            throw new IllegalArgumentException("The LinearLayout container should have a vertical orientation");

        container.removeAllViews();

        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        for (PreferenceFragmentItem item : fragmentItemList) {
            transaction.add(container.getId(), item.createFragment(mActivity), item.getTag());
        }
        transaction.commit();
    }

    private List<PreferenceFragmentItem> getPreferenceFragmentList(List<Header> headerList) {
        List<PreferenceFragmentItem> fragmentItems = new ArrayList<>(headerList.size());
        for (Header header : headerList) {
            fragmentItems.add(new PreferenceFragmentItem(header.fragment, header.fragment, header.fragmentArguments));
        }
        return fragmentItems;
    }
}

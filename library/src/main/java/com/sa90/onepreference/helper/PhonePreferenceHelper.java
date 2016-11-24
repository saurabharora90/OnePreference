package com.sa90.onepreference.helper;

import android.app.FragmentTransaction;
import android.support.annotation.XmlRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.sa90.onepreference.interfaces.PhonePreference;
import com.sa90.onepreference.model.Header;
import com.sa90.onepreference.utils.PreferenceHelper;

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

    public void setupScreen() {
        setupFragments(mPhonePreference.getFragmentContainerForPhone());
    }

    /**
     * Parses the header file to return a list of fragments referred to by each header item.
     *
     * @param headerResource
     * @return
     */
    private List<PreferenceFragmentItem> getPreferenceFragmentList(@XmlRes int headerResource) {
        List<Header> headerList = new ArrayList<>();
        PreferenceHelper.loadHeadersFromResource(headerResource, headerList, this);

        List<PreferenceFragmentItem> fragmentItems = new ArrayList<>(headerList.size());
        for (Header header : headerList) {
            fragmentItems.add(new PreferenceFragmentItem(header.fragment, header.fragment, header.fragmentArguments));
        }
        return fragmentItems;
    }

    private void setupFragments(LinearLayout container) {
        if (container.getOrientation() != LinearLayout.VERTICAL)
            throw new IllegalArgumentException("The LinearLayout container should have a vertical orientation");

        container.removeAllViews();
        List<PreferenceFragmentItem> fragmentItemList = getPreferenceFragmentList(mPhonePreference.getHeaderFile());

        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        for (PreferenceFragmentItem item : fragmentItemList) {
            transaction.add(container.getId(), item.createFragment(mActivity), item.getTag());
        }
        transaction.commit();
    }
}

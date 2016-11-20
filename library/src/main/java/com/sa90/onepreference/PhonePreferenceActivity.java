package com.sa90.onepreference;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.XmlRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.sa90.onepreference.interfaces.OnePreference;
import com.sa90.onepreference.utils.OnePreferenceHelper;
import com.sa90.onepreference.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class PhonePreferenceActivity extends AppCompatActivity implements OnePreference {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        View container = findViewById(getFragmentContainerId());
        if(container instanceof LinearLayout) {
            setupFragments((LinearLayout) container);
        }
        else {
            throw new IllegalArgumentException("The holding container for Fragment should be a LinearLayout");
        }
    }

    private void setupFragments(LinearLayout container) {
        container.removeAllViews();
        List<PreferenceFragmentItem> fragmentItemList = getPreferenceFragmentList(getHeaderFile());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        for (PreferenceFragmentItem item : fragmentItemList) {
            transaction.add(container.getId(), item.createFragment(this), item.getTag());
        }
        transaction.commit();
    }

    /**
     * Parses the header file to return a list of fragments referred to by each header item.
     *
     * @param headerResource
     * @return
     */
    private List<PreferenceFragmentItem> getPreferenceFragmentList(@XmlRes int headerResource) {
        List<PreferenceActivity.Header> headerList = new ArrayList<>();
        PreferenceHelper.loadHeadersFromResource(headerResource, headerList, this);

        List<PreferenceFragmentItem> fragmentItems = new ArrayList<>(headerList.size());
        for (PreferenceActivity.Header header : headerList) {
            fragmentItems.add(new PreferenceFragmentItem(header.fragment, header.fragment, header.fragmentArguments));
        }
        return fragmentItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeaderFile() {
        return getIntent().getIntExtra(OnePreferenceHelper.EXTRA_HEADER_RES, -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFragmentContainerId() {
        return R.id.container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_phone_preference;
    }
}

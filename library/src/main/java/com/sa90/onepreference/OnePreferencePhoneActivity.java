package com.sa90.onepreference;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.XmlRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sa90.onepreference.interfaces.OnePreference;
import com.sa90.onepreference.utils.OnePreferenceHelper;
import com.sa90.onepreference.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class OnePreferencePhoneActivity extends AppCompatActivity implements OnePreference {

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

        setupToolbar();
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

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null && getIntent().getBooleanExtra(OnePreferenceHelper.EXTRA_SHOW_BACK, false)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            int backResId = getIntent().getIntExtra(OnePreferenceHelper.EXTRA_OVERRIDE_BACK_ICON, -1);
            if(backResId!=-1)
                actionBar.setHomeAsUpIndicator(backResId);
        }

        actionBar.setTitle(getIntent().getStringExtra(OnePreferenceHelper.EXTRA_TITLE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

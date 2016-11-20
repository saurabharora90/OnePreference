package com.sa90.onepreference;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sa90.onepreference.interfaces.OnePreference;
import com.sa90.onepreference.utils.OnePreferenceHelper;
import com.sa90.onepreference.utils.PreferenceHelper;

import java.util.List;

public class OnePreferenceTabletActivity extends PreferenceActivity implements OnePreference {

    @Override
    public void onBuildHeaders(List<Header> target) {
        PreferenceHelper.loadHeadersFromResource(getHeaderFile(), target, this);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    @Override
    public int getHeaderFile() {
        return getIntent().getIntExtra(OnePreferenceHelper.EXTRA_HEADER_RES, -1);
    }

    @Override
    public int getFragmentContainerId() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    public void showBreadCrumbs(CharSequence title, CharSequence shortTitle) {
    }

    public void initToolbar() {
        LinearLayout root = getRootView();
        Toolbar toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.include_toolbar, root, false);
        root.addView(toolbar, 0);

        toolbar.setTitle(getIntent().getStringExtra(OnePreferenceHelper.EXTRA_TITLE));
        if(getIntent().getBooleanExtra(OnePreferenceHelper.EXTRA_SHOW_BACK, false)) {
            int backResId = getIntent().getIntExtra(OnePreferenceHelper.EXTRA_OVERRIDE_BACK_ICON, -1);
            if(backResId!=-1)
                toolbar.setNavigationIcon(backResId);
            else
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected final LinearLayout getRootView() {
        return (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
    }
}

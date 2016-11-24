package com.sa90.onepreference;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public class OnePreferenceActivity extends BaseOnePreferenceActivity {

    LinearLayout llContainer;
    FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_preference);

        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        flContainer = (FrameLayout) findViewById(R.id.flContainer);

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.onePrefToolbar);
        if (toolbar == null)
            return;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getIntent().getStringExtra(OnePreferenceHelper.EXTRA_TITLE));

            if (getIntent().getBooleanExtra(OnePreferenceHelper.EXTRA_SHOW_BACK, false)) {
                actionBar.setDisplayHomeAsUpEnabled(true);

                int backResId = getIntent().getIntExtra(OnePreferenceHelper.EXTRA_OVERRIDE_BACK_ICON, -1);
                if (backResId != -1)
                    actionBar.setHomeAsUpIndicator(backResId);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public LinearLayout getFragmentContainerForPhone() {
        return llContainer;
    }

    @Nullable
    @Override
    public FrameLayout getFragmentContainerForTablet() {
        return flContainer;
    }

    @Nullable
    @Override
    public ListView getHeaderListView() {
        return null;
    }

    @Nullable
    @Override
    public BaseAdapter getHeaderListAdapter() {
        return null;
    }

    @NonNull
    @Override
    public int getHeaderFile() {
        return getIntent().getIntExtra(OnePreferenceHelper.EXTRA_HEADER_RES, -1);
    }
}

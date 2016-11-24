package com.sa90.onepreference;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sa90.onepreference.helper.PreferenceFragmentItem;
import com.sa90.onepreference.interfaces.OnePreference;
import com.sa90.onepreference.utils.OnePreferenceHelper;

import java.util.List;

public class OnePreferencePhoneActivity extends AppCompatActivity implements OnePreference {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_preference);

        View container = findViewById(getFragmentContainerId());
        if(container instanceof LinearLayout) {
            setupFragments((LinearLayout) container);
        }
        else {
            throw new IllegalArgumentException("The holding container for Fragment should be a LinearLayout");
        }

        setupToolbar();
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.onePrefToolbar);
        if(toolbar == null)
            return;
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
}

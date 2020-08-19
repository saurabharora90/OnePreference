package com.sa90.onepreference;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.sa90.onepreference.adapter.HeaderAdapter;
import com.sa90.onepreference.model.Header;

import java.util.List;

public class OnePreferenceActivity extends BaseOnePreferenceActivity {

    LinearLayout llContainer;
    FrameLayout flContainer;
    ListView lvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_preference);

        llContainer = findViewById(R.id.llContainer);
        flContainer = findViewById(R.id.flContainer);
        lvHeader = findViewById(R.id.lvHeader);

        setupToolbar();
    }

    /**
     * Setup the toolbar according to the Intent Extras.
     *
     * If this activity acts as a base class, then make sure that the intent extras are setup before starting the base class.
     */
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
        return lvHeader;
    }

    @Nullable
    @Override
    public ArrayAdapter<Header> getHeaderListAdapter(List<Header> headerList) {
        return new HeaderAdapter(this, headerList);
    }

    @NonNull
    @Override
    public int getHeaderFile() {
        return getIntent().getIntExtra(OnePreferenceHelper.EXTRA_HEADER_RES, -1);
    }
}

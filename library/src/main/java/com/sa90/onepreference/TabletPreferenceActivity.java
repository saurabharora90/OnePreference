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

public class TabletPreferenceActivity extends PreferenceActivity implements OnePreference {

    @Override
    public void onBuildHeaders(List<Header> target) {
        PreferenceHelper.loadHeadersFromResource(getHeaderFile(), target, this);
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

    public void initToolbar() {
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar toolbar = new Toolbar(this);
        root.addView(toolbar, 0);

        toolbar.setTitle(getString(R.string.settings));
        toolbar.setBackgroundResource(R.color.material_gray_toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

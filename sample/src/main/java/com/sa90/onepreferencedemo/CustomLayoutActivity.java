package com.sa90.onepreferencedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sa90.onepreference.BaseOnePreferenceActivity;
import com.sa90.onepreference.adapter.HeaderAdapter;
import com.sa90.onepreference.model.Header;

import java.util.List;

public class CustomLayoutActivity extends BaseOnePreferenceActivity {

    LinearLayout llContainer;
    FrameLayout flContainer;
    ListView lvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llContainer = findViewById(com.sa90.onepreference.R.id.llContainer);
        flContainer = findViewById(com.sa90.onepreference.R.id.flContainer);
        lvHeader = findViewById(com.sa90.onepreference.R.id.lvHeader);
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
        return R.xml.pref_headers;
    }
}

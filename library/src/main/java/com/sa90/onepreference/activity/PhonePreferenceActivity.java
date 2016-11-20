package com.sa90.onepreference.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sa90.onepreference.R;
import com.sa90.onepreference.interfaces.OnePreference;

public class PhonePreferenceActivity extends AppCompatActivity implements OnePreference {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeaderFile() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFragmentContainerId() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLayoutRes() {
        return R.layout.activity_phone_preference;
    }
}

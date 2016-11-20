package com.sa90.onepreference;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by saurabharora on 20/11/16.
 */

public abstract class BasePreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @return
     */
    public abstract @XmlRes int getHeaderFile();
    public abstract int getFragmentContainerId();
}

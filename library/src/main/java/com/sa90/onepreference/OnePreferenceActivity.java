package com.sa90.onepreference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Saurabh Arora on 24/11/16.
 */

public class OnePreferenceActivity extends AppCompatActivity {

    LinearLayout llContainer;
    FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_preference);

        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        flContainer = (FrameLayout) findViewById(R.id.flContainer);
    }
}

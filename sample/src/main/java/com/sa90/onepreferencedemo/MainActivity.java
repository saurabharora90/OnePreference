package com.sa90.onepreferencedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sa90.onepreference.OnePreferenceActivity;
import com.sa90.onepreference.OnePreferenceHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNormal).setOnClickListener(this);
        findViewById(R.id.btnCustom).setOnClickListener(this);
        findViewById(R.id.btnHeader).setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNormal:
                OnePreferenceHelper.startActivity(R.xml.pref_headers, "Settings", true, this);
                break;
            case R.id.btnCustom:
                startActivity(new Intent(MainActivity.this, CustomLayoutActivity.class));
                break;
            case R.id.btnHeader:
                Intent intent = new Intent(MainActivity.this, HeaderManipulationActivity.class);
                intent.putExtra(OnePreferenceHelper.EXTRA_HEADER_RES, R.xml.pref_headers);
                intent.putExtra(OnePreferenceHelper.EXTRA_TITLE, "Custom Settings");
                intent.putExtra(OnePreferenceHelper.EXTRA_SHOW_BACK, true);
                startActivity(intent);
                break;
        }
    }
}

package com.sa90.onepreferencedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNormal).setOnClickListener(this);
        findViewById(R.id.btnCustom).setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNormal:
                startActivity(new Intent(MainActivity.this, SimpleDemoActivity.class));
                break;
            case R.id.btnCustom:
                startActivity(new Intent(MainActivity.this, CustomDemoActivity.class));
                break;
        }
    }
}

package com.sa90.onepreferencedemo;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wv = (WebView) findViewById(R.id.wv);

        wv.loadUrl(getIntent().getStringExtra("extra_url"));
    }
}

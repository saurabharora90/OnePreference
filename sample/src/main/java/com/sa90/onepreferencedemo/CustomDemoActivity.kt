package com.sa90.onepreferencedemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sa90.onepreference.BaseOnePreferenceFragment
import com.sa90.onepreference.helper.OnePreferenceHelper
import com.sa90.onepreference.model.Header

class CustomDemoActivity : AppCompatActivity() {

    private lateinit var onePreferenceFragment: BaseOnePreferenceFragment

    private val headerList: MutableList<Header> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_demo)

        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            if (headerList.size > 0) {
                headerList.removeAt(0)
                onePreferenceFragment.setHeaders(headerList)
            }

            it.visibility = View.GONE
        }

        onePreferenceFragment =
            supportFragmentManager.findFragmentByTag("my_tag") as BaseOnePreferenceFragment

        OnePreferenceHelper.loadHeadersFromResource(R.xml.pref_headers, headerList, this)
        onePreferenceFragment.setHeaders(headerList)
    }
}
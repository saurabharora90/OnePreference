package com.sa90.onepreference

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat

abstract class OnePreferenceFragmentCompat : PreferenceFragmentCompat() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.overScrollMode = View.OVER_SCROLL_NEVER
    }
}
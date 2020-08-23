package com.sa90.onepreference

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.annotation.XmlRes
import androidx.core.content.withStyledAttributes
import androidx.fragment.app.Fragment
import com.sa90.onepreference.helper.OnePreferenceHelper
import com.sa90.onepreference.model.Header

abstract class BaseOnePreferenceFragment : Fragment() {

    @XmlRes
    private var headerFile: Int = -1

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.BaseOnePreferenceFragment
        ) {
            headerFile =
                getResourceId(R.styleable.BaseOnePreferenceFragment_onepref_headerFile, -1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (headerFile != -1) {
            val headerList: MutableList<Header> = mutableListOf()
            OnePreferenceHelper.loadHeadersFromResource(headerFile, headerList, requireContext())
            addFragments(headerList)
        }
    }

    internal abstract fun addFragments(headerList: List<Header>)

    fun setHeaders(headersList: List<Header>) {
        addFragments(headersList)
    }
}
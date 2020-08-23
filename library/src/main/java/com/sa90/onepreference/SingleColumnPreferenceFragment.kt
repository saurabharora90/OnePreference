package com.sa90.onepreference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.commit
import com.sa90.onepreference.model.Header

class SingleColumnPreferenceFragment : BaseOnePreferenceFragment() {

    private val linearLayout by lazy {
        LinearLayout(requireContext())
            .apply {
                orientation = LinearLayout.VERTICAL
                id = ViewCompat.generateViewId()
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return linearLayout
    }

    override fun addFragments(headerList: List<Header>) {
        linearLayout.removeAllViews()

        childFragmentManager.commit {
            for (header in headerList) {
                val fragment = childFragmentManager.fragmentFactory.instantiate(
                    ClassLoader.getSystemClassLoader(),
                    header.fragment
                )
                fragment.arguments = header.fragmentArguments
                add(linearLayout.id, fragment, header.fragment)
            }
        }
    }
}
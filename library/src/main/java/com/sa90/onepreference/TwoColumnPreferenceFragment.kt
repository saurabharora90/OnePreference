package com.sa90.onepreference

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.XmlRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sa90.onepreference.adapter.HeaderAdapter
import com.sa90.onepreference.model.Header

private const val DEFAULT_FIRST_COLUMN_WEIGHT = 0.3f
private const val DEFAULT_SECOND_COLUMN_WEIGHT = 0.3f

class TwoColumnPreferenceFragment : BaseOnePreferenceFragment() {

    private val recyclerView: RecyclerView by lazy {
        RecyclerView(requireContext())
            .apply {
                layoutManager = LinearLayoutManager(activity)
            }
    }

    private val fragmentContainerView: FragmentContainerView by lazy {
        FragmentContainerView(requireContext())
            .apply {
                id = ViewCompat.generateViewId()
            }
    }

    private var firstColumnWeight = DEFAULT_FIRST_COLUMN_WEIGHT
    private var secondColumnWeight = DEFAULT_SECOND_COLUMN_WEIGHT

    @XmlRes
    private var headerFile: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LinearLayout(requireContext())
            .apply {
                orientation = LinearLayout.HORIZONTAL
                addView(
                    recyclerView,
                    LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        firstColumnWeight
                    )
                )
                addView(
                    fragmentContainerView,
                    LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        secondColumnWeight
                    )
                )
            }
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.TwoColumnPreferenceFragment
        ) {
            firstColumnWeight = getFloat(
                R.styleable.TwoColumnPreferenceFragment_onepref_firstColumnWeight,
                DEFAULT_FIRST_COLUMN_WEIGHT
            )
            secondColumnWeight = getFloat(
                R.styleable.TwoColumnPreferenceFragment_onepref_secondColumnWeight,
                DEFAULT_SECOND_COLUMN_WEIGHT
            )
        }
    }

    override fun addFragments(headerList: List<Header>) {
        recyclerView.adapter = HeaderAdapter {
            switchFragment(it)
        }
            .apply {
                submitList(headerList) {
                    switchFragment(headerList[0])
                }
            }
    }

    private fun switchFragment(header: Header) {
        childFragmentManager.commit {
            val fragment = childFragmentManager.fragmentFactory.instantiate(
                ClassLoader.getSystemClassLoader(),
                header.fragment
            )
            fragment.arguments = header.fragmentArguments
            replace(fragmentContainerView.id, fragment, header.fragment)
        }
    }
}
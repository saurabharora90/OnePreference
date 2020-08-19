package com.sa90.onepreference.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sa90.onepreference.R
import com.sa90.onepreference.model.Header

class HeaderAdapter(private val itemClick: (Header) -> Unit) : ListAdapter<Header, HeaderViewHolder>(
    HeaderDiffUtil()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.preference_header_item, parent, false)
        return HeaderViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(
        holder: HeaderViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}
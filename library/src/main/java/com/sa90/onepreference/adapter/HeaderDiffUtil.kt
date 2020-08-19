package com.sa90.onepreference.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sa90.onepreference.model.Header

class HeaderDiffUtil : DiffUtil.ItemCallback<Header>() {

    override fun areItemsTheSame(oldItem: Header, newItem: Header): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Header, newItem: Header): Boolean {
        return false
    }
}
package com.sa90.onepreference.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sa90.onepreference.R
import com.sa90.onepreference.model.Header

class HeaderViewHolder(itemView: View, itemClick: (Header) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val summary: TextView = itemView.findViewById(R.id.tvSummary)
    private val title: TextView = itemView.findViewById(R.id.tvTitle)
    private val icon: ImageView = itemView.findViewById(R.id.icon)

    init {
        itemView.setOnClickListener { itemClick(it.tag as Header) }
    }

    fun bind(header: Header) {
        itemView.tag = header
        if (header.iconRes == 0) {
            icon.visibility = View.GONE
        } else {
            icon.visibility = View.VISIBLE
            icon.setImageResource(header.iconRes)
        }
        title.text = header.getTitle(title.context.resources)
        val headerSummary = header.getSummary(title.context.resources)
        if (!headerSummary.isNullOrEmpty()) {
            summary.visibility = View.VISIBLE
            summary.text = headerSummary
        } else {
            summary.visibility = View.GONE
        }
    }

}
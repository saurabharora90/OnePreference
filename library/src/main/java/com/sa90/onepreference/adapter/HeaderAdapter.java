package com.sa90.onepreference.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sa90.onepreference.R;
import com.sa90.onepreference.adapter.viewholder.HeaderViewHolder;
import com.sa90.onepreference.model.Header;

import java.util.List;

/**
 * Created by Saurabh Arora on 21/11/16.
 */

public class HeaderAdapter extends RecyclerView.Adapter<HeaderViewHolder>{

    private List<Header> headerList;

    public HeaderAdapter(List<Header> headerList) {
        this.headerList = headerList;
    }

    @Override
    public HeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.preference_header_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, int position) {
        Header header = headerList.get(position);
        if (header.iconRes == 0) {
            holder.icon.setVisibility(View.GONE);
        } else {
            holder.icon.setVisibility(View.VISIBLE);
            holder.icon.setImageResource(header.iconRes);
        }
        holder.title.setText(header.getTitle(holder.title.getContext().getResources()));
        CharSequence summary = header.getSummary(holder.title.getContext().getResources());
        if (!TextUtils.isEmpty(summary)) {
            holder.summary.setVisibility(View.VISIBLE);
            holder.summary.setText(summary);
        } else {
            holder.summary.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(headerList == null)
            return 0;
        return headerList.size();
    }
}

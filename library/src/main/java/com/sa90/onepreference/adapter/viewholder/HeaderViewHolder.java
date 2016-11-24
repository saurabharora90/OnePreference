package com.sa90.onepreference.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sa90.onepreference.R;

/**
 * Created by Saurabh Arora on 21/11/16.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    public TextView summary, title;
    public ImageView icon;

    public HeaderViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tvTitle);
        summary = (TextView) itemView.findViewById(R.id.tvSummary);
        icon = (ImageView) itemView.findViewById(R.id.icon);
    }
}

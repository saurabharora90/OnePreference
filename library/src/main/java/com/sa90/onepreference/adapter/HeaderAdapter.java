package com.sa90.onepreference.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sa90.onepreference.R;
import com.sa90.onepreference.model.Header;

import java.util.List;

/**
 * Adopted from AOSP
 */

public class HeaderAdapter extends ArrayAdapter<Header> {

    public HeaderAdapter(Context context, List<Header> objects) {
        super(context, -1, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.preference_header_item, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(convertView);
            convertView.setTag(headerViewHolder);
        }

        HeaderViewHolder holder = (HeaderViewHolder) convertView.getTag();
        Header header = getItem(position);
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
        return convertView;
    }

    private static class HeaderViewHolder {

        TextView summary, title;
        ImageView icon;

        HeaderViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            summary = (TextView) itemView.findViewById(R.id.tvSummary);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}

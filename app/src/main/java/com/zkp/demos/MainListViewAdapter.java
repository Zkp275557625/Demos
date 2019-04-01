package com.zkp.demos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos
 * @time: 2019/4/1 14:32
 * @description:
 */
public class MainListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mTitles;

    public MainListViewAdapter(Context mContext, List<String> mTitles) {
        this.mContext = mContext;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public String getItem(int position) {
        return mTitles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(getItem(position));

        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
    }
}

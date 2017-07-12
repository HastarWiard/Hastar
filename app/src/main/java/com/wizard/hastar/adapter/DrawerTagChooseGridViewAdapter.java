package com.wizard.hastar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wizard.hastar.R;
import com.wizard.hastar.ui.money_manager.util.RecordManager;
import com.wizard.hastar.util.HaStarUtil;

/**
 * Created by 伟平 on 2015/11/10.
 */

public class DrawerTagChooseGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    public DrawerTagChooseGridViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return RecordManager.TAGS.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.item_tag_choose, null);
            holder.tagImage = (ImageView) convertView.findViewById(R.id.tag_image);
            holder.tagName = (TextView) convertView.findViewById(R.id.tag_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tagImage.setImageResource(
                HaStarUtil.GetTagIcon(RecordManager.TAGS.get(position).getId()));
        holder.tagName.setText(HaStarUtil.GetTagName(RecordManager.TAGS.get(position).getId()));
        holder.tagName.setTypeface(HaStarUtil.GetTypeface());

        return convertView;
    }

    private class ViewHolder {
        ImageView tagImage;
        TextView tagName;
    }
}

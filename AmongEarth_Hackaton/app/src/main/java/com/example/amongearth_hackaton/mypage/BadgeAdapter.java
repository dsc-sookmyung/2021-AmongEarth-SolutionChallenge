package com.example.amongearth_hackaton.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amongearth_hackaton.R;

import java.util.ArrayList;

public class BadgeAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater = null;
    ArrayList<BadgeData> badge;

    public BadgeAdapter(Context context, ArrayList<BadgeData> data) {
        mContext = context;
        badge = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return badge.size();
    }

    @Override
    public Object getItem(int position)
    {
        return badge.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.mybadge_item, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.badge_image);
        TextView badge_name = (TextView)view.findViewById(R.id.badge_name);
        TextView get_Date = (TextView)view.findViewById(R.id.get_Date);

        imageView.setImageResource(badge.get(position).getBadge_image());
        badge_name.setText(badge.get(position).getBadge_name());
        get_Date.setText(badge.get(position).getDate());

        return view;
    }

}

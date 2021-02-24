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

public class PostAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater = null;
    ArrayList<PostData> sample;

    public PostAdapter(Context context, ArrayList<PostData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Object getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.mycontent_item, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.mypost_image);
        TextView title = (TextView)view.findViewById(R.id.textView1);
        TextView contents = (TextView)view.findViewById(R.id.textView2);

        imageView.setImageResource(sample.get(position).getPost_image());
        title.setText(sample.get(position).getTitle());
        contents.setText(sample.get(position).getContents());

        return view;
    }

}

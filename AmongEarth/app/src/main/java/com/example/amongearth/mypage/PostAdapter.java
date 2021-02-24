package com.example.amongearth.mypage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amongearth.community.Community_Page3;
import com.example.amongearth.R;

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
        //View view = mLayoutInflater.inflate(R.layout.mycontent_item, null);
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mycontent_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.mypost_image);
        TextView title = (TextView)convertView.findViewById(R.id.textView1);
        TextView contents = (TextView) convertView.findViewById(R.id.textView2);

        imageView.setImageResource(sample.get(position).getPost_image());
        title.setText(sample.get(position).getTitle());
        contents.setText(sample.get(position).getContents());

        LinearLayout my_content_item = (LinearLayout) convertView.findViewById(R.id.my_contents_item);
        my_content_item.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(v.getContext(), sample.get(position).getContents(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Community_Page3.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

}

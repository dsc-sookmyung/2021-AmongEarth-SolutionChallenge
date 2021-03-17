package com.example.amongearth.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amongearth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewAdapter2 extends BaseAdapter {
    private Context cont2;
    private ArrayList<Community_Page1_List2> listViewItemList2 = new ArrayList<Community_Page1_List2>() ;
    LayoutInflater mLayoutInflater = null;

    public ListViewAdapter2(Context mContext) {
        cont2 = mContext;
    }

    public ListViewAdapter2(Context m, ArrayList<Community_Page1_List2> data){
        cont2 = m;
        listViewItemList2 = data;
        mLayoutInflater = LayoutInflater.from(cont2);
    }

    @Override
    public int getCount() {
        return listViewItemList2.size() ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();




        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.community_page1_list2, parent, false);
        }

        ImageView iconImageView2 = (ImageView) convertView.findViewById(R.id.page1_list2_icon);
        ImageView photoImageView2 = (ImageView) convertView.findViewById(R.id.page1_list2_photo);
        TextView idTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_id);
        TextView dateTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_date);
        TextView contentTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_content);
        TextView heartTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_heart);
        ImageView heartView = (ImageView) convertView.findViewById(R.id.heart_image);
        Community_Page1_List2 listViewItem2 = listViewItemList2.get(position);
        iconImageView2.setImageDrawable(listViewItem2.getImage2_icon());

        Glide.with(convertView).load(listViewItem2.getImage2_photo()).override(photoImageView2.getWidth(), photoImageView2.getHeight()).into(photoImageView2);
        idTextView2.setText(listViewItem2.getId2());
        dateTextView2.setText(listViewItem2.getDate2());
        contentTextView2.setText(listViewItem2.getContent2());
        heartTextView2.setText(listViewItem2.getHeart_number2());

        if (listViewItem2.getHeartflag().equals("1")){
            heartView.setImageDrawable(convertView.getResources().getDrawable(R.drawable.love_button));
        }
        else if (listViewItem2.getHeartflag().equals("0")){
            heartView.setImageDrawable(convertView.getResources().getDrawable(R.drawable.notclick_heart));
        }
        return convertView;
    }

    public void addItem(Drawable icon, String photo, String id, String date, String content, String heartnum, String userId) {
        Community_Page1_List2 item = new Community_Page1_List2();

        item.setImage2_icon(icon);
        item.setImage2_photo(photo);
        item.setId2(id);
        item.setDate2(date);
        item.setContent2(content);
        item.setHeart_number2(heartnum);
        item.setUserid(userId);

        listViewItemList2.add(item);
    }
}

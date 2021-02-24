package com.example.amongearth.community;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amongearth.R;

import java.util.ArrayList;

public class ListViewAdapter3 extends BaseAdapter {
    // Adapter 에 추가된 데이터를 저장하기 위한 ArrayList

    private Context const3;
    private ArrayList<Community_Page3_List1> listViewItemList3 = new ArrayList<Community_Page3_List1>();

    //ListViewAdapter의 생성자

    public ListViewAdapter3(Context mContext){
        const3 = mContext;
    }
    @Override
    public int getCount() { return listViewItemList3.size(); }

    @Override
    public Object getItem(int position) { return listViewItemList3.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.community_page3_list1, parent, false);
        }

        ImageView iconImageView3 = (ImageView) convertView.findViewById(R.id.page3_list1_icon);
        TextView idTextView3 = (TextView) convertView.findViewById(R.id.page3_list1_id);
        TextView dateTextView3 = (TextView) convertView.findViewById(R.id.page3_list1_date);
        TextView contentTextView3 = (TextView) convertView.findViewById(R.id.page3_list1_content);

        Community_Page3_List1 listViewItem3 = listViewItemList3.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView3.setImageDrawable(listViewItem3.getImage_icon3());
        idTextView3.setText(listViewItem3.getId3());
        dateTextView3.setText(listViewItem3.getDate3());
        contentTextView3.setText(listViewItem3.getContent3());


        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon, String id, String date, String content) {
        Community_Page3_List1 item = new Community_Page3_List1();

        item.setImage_icon3(icon);
        item.setId3(id);
        item.setDate3(date);
        item.setContent3(content);

        listViewItemList3.add(item);
    }
}
package com.example.amongearth.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amongearth.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Context cont;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Community_Page1_List1> listViewItemList = new ArrayList<Community_Page1_List1>() ;


    // ListViewAdapter의 생성자
    public ListViewAdapter(Context mContext) {
        cont = mContext;

    }
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
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
            convertView = inflater.inflate(R.layout.community_page1_list1, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.page1_list1_icon);
        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.page1_list1_photo);
        TextView idTextView = (TextView) convertView.findViewById(R.id.page1_list1_id);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.page1_list1_title);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.page1_list1_contents);

        Community_Page1_List1 listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getImage_icon());
        photoImageView.setImageDrawable(listViewItem.getImage_photo());
        idTextView.setText(listViewItem.getId());
        titleTextView.setText(listViewItem.getTitle());
        contentTextView.setText(listViewItem.getContent());

        RelativeLayout Page1_List1 = (RelativeLayout) convertView.findViewById(R.id.Page1_List1);
        Page1_List1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(v.getContext(), listViewItemList.get(position).getContent(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cont, Community_Page3.class);
                cont.startActivity(intent);
            }
        });

        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon, Drawable photo, String id, String title, String content) {
        Community_Page1_List1 item = new Community_Page1_List1();

        item.setImage_icon(icon);
        item.setImage_photo(photo);
        item.setId(id);
        item.setTitle(title);
        item.setContent(content);

        listViewItemList.add(item);
    }
}

package com.example.amongearth.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList

    private Context cont2;
    private ArrayList<Community_Page1_List2> listViewItemList2 = new ArrayList<Community_Page1_List2>() ;
    LayoutInflater mLayoutInflater = null;

    private DatabaseReference databaseReference;
    String userId; // 21.02.25 Firebase 내용 생성
    HashMap<String, Object > updatechild;


    // ListViewAdapter의 생성자
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



        databaseReference = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        updatechild = new HashMap<>();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.community_page1_list2, parent, false);
            // 일단 해보기

        }

        ImageView iconImageView2 = (ImageView) convertView.findViewById(R.id.page1_list2_icon);
        ImageView photoImageView2 = (ImageView) convertView.findViewById(R.id.page1_list2_photo);
        TextView idTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_id);
        TextView dateTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_date);
        TextView contentTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_content);
        TextView heartTextView2 = (TextView) convertView.findViewById(R.id.page1_list2_heart);

        ImageView heartView = (ImageView) convertView.findViewById(R.id.heart_image);

        Community_Page1_List2 listViewItem2 = listViewItemList2.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView2.setImageDrawable(listViewItem2.getImage2_icon());

        // 여기가 관건
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


        // 지우지 마세요 -> 중요한 코드
        /*RelativeLayout Page1_List2 = (RelativeLayout) convertView.findViewById(R.id.Page1_List2);
        Page1_List2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(v.getContext(), listViewItemList2.get(position).getContent2(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cont2, Community_Page4.class);
                cont2.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });*/


        // 내가 눌렀었는지 안눌었는지에 대한 정보

        heartTextView2.setClickable(true);
        heartTextView2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), listViewItemList2.get(position).getHeart_number2(), Toast.LENGTH_SHORT).show();
                updatechild.put(userId, userId); /* 아래 child 값 바꾸기 */
                databaseReference.child("challenge_board").child(listViewItemList2.get(position).getUserid()+"").child(listViewItemList2.get(position).getDate2()).child("likes").updateChildren(updatechild);
                // 한번 db에서 읽어와보기
                databaseReference.child("challenge_board").child(listViewItemList2.get(position).getUserid()).child(listViewItemList2.get(position).getDate2()).child("likes").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String, Object> td = (HashMap<String, Object>) snapshot.getValue();
                        Log.d("나 여기지롱",td+"");

                        if (td.containsKey(userId)){
                            Log.d("콩콩콩", "콩");
                            heartTextView2.setText((snapshot.getChildrenCount()-1)+"");
                            heartView.setImageDrawable(v.getResources().getDrawable(R.drawable.love_button));
                            notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



        return convertView;
    }


    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
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

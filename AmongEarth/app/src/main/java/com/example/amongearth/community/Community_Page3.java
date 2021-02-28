package com.example.amongearth.community;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.amongearth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Community_Page3 extends AppCompatActivity {

    ListViewAdapter3 adapter3;
    ListView listView;
    private DatabaseReference mDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_page_3);

        adapter3 = new ListViewAdapter3(Community_Page3.this);
        listView = (ListView) findViewById(R.id.page3_listview);

        // 2가지를 해야함
        // 1. Firebase에서 해당 내용을 가져와야 하는 것
        // 2. 댓글을 작성했을때 그게 리스트에 추가되야하는 것

        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase.child("genearl_board").child("12345678").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 그 아래 엄청 많이 있을 거임
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Log.d("라라라", dataSnapshot+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/





        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.person3), "Jack","Wed", getResources().getString(R.string.community_page3_note2));
        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.person3), "Jack","Thu", getResources().getString(R.string.community_page3_note2));
        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.person3), "Jack","Fri", getResources().getString(R.string.community_page3_note2));

        listView.setAdapter(adapter3);
    }

    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Community_Page3_List1 item = (Community_Page3_List1) l.getItemAtPosition(position) ;

        Drawable iconDrawble3 = item.getImage_icon3();
        String idStr3 = item.getId3();
        String dateStr3 = item.getDate3();
        String contentStr3 = item.getContent3();

        // TODO : use item data.
    }
}

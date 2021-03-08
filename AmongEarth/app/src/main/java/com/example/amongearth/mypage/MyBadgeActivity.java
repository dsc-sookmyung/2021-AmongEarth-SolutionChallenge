package com.example.amongearth.mypage;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amongearth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyBadgeActivity extends AppCompatActivity {

    private TextView nickName;
    private TextView gotBadge;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManger;
    private ArrayList<Badge> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybadge);

        recyclerView = findViewById(R.id.my_badges);
        recyclerView.setHasFixedSize(true);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);
        //Badge 객체 담는 arrayList
        arrayList = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        database =FirebaseDatabase.getInstance();

        if(user != null) {


            databaseReference = database.getReference("user").child(uid).child("my_badge"); //db 연결

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    arrayList.clear(); // 기존 배열리스트 초기화
                    long numChildren = dataSnapshot.getChildrenCount();
                    gotBadge = (TextView)findViewById(R.id.BadgeNumber);
                    gotBadge.setText("You've received "+numChildren +" badges");

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        databaseReference.orderByKey();
                        Badge badge = snapshot.getValue(Badge.class); //만들어 두었던 Badge객체에 서버에서 받아온 데이터를 담기
                        arrayList.add(badge); // 배열리스트에 담아둔 데이터를 넣어
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("MyBadgeActivity",String.valueOf(error.toException()));
                }
            });
            adapter = new BadgeAdapter(arrayList, this);
            recyclerView.setAdapter(adapter);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.getReference("user").child(uid).addValueEventListener(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        if(dataSnapshot.getKey().equals("nickname")){
                        String nickname = dataSnapshot.getValue().toString();
                        nickName = (TextView)findViewById(R.id.NickName);
                        nickName.setText(nickname);
                        }
                }
                @Override
            public void onCancelled(@NonNull DatabaseError error){
                    Log.e("BadgeActivity:닉네임변경실패",String.valueOf(error.toException()));
                }
                });
            }
        }
    }
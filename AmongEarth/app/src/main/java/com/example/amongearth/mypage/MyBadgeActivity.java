package com.example.amongearth.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amongearth.MainActivity;
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
    private ImageView profile;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.my_badges);
        recyclerView.setHasFixedSize(true);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);
        //Badge 객체 담는 arrayList
        arrayList = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        database = FirebaseDatabase.getInstance();

        if (user != null) {


            databaseReference = database.getReference("user").child(uid).child("my_badge"); //db 연결

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    arrayList.clear(); // 기존 배열리스트 초기화
                    long numChildren = dataSnapshot.getChildrenCount();
                    gotBadge = (TextView) findViewById(R.id.BadgeNumber);
                    gotBadge.setText("You've received " + numChildren + "badges");

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //databaseReference.orderBy("getDate");
                        Badge badge = snapshot.getValue(Badge.class); //만들어 두었던 Badge객체에 서버에서 받아온 데이터를 담기
                        arrayList.add(badge); // 배열리스트에 담아둔 데이터를 넣어
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("MyBadgeActivity", String.valueOf(error.toException()));
                }
            });
            adapter = new BadgeAdapter(arrayList, this);
            recyclerView.setAdapter(adapter);
            profile = findViewById(R.id.profile);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.getReference("user").child(uid).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        if (dataSnapshot.getKey().equals("nickname")) {
                            String nickname = dataSnapshot.getValue().toString();
                            nickName = (TextView) findViewById(R.id.NickName);
                            nickName.setText(nickname+",");
                        }
                        if (dataSnapshot.getKey().equals("profile")) {
                        String num = dataSnapshot.getValue().toString();
                        Log.d("profile num 값 ", num);
                            if (num.equals("1")) {
                             profile.setImageResource(R.drawable.person1);

                        }
                        else if (num.equals("2")) {
                                profile.setImageResource(R.drawable.person2);
                            }
                        else if (num.equals("3")) {
                            profile.setImageResource(R.drawable.person3);

                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("BadgeActivity:닉네임변경실패", String.valueOf(error.toException()));
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
            case R.id.BtnHome: { // 오른쪽 상단 버튼 눌렀을 때
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
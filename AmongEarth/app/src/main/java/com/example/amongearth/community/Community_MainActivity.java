package com.example.amongearth.community;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.amongearth.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

@Deprecated
public class Community_MainActivity extends FragmentActivity {

    TabLayout tabs;

    Fragment1 fragment1;
    Fragment2 fragment2;
    int position;
    private DatabaseReference mDatabase; // 21.02.25 Firebase 내용 생성
    String userId; // 21.02.25 Firebase 내용 생성
    ArrayList<ArrayList<String>> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        arr = new ArrayList<>();


        /* Firebase 내용 생성 21.02.25 3:22 am */


        mDatabase = FirebaseDatabase.getInstance().getReference(); // 21.02.25 Firebase 내용 생성
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("UserID느느느느느는",userId);

        mDatabase.child("challenge_board").child("12345678").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // Data가 변경되면
                Log.d("MainActivity","ValueEventListener :"+snapshot.child("20210225").child("likes").getChildrenCount());
                Log.d("라라라라라",snapshot.child("20210225").getValue()+"");
                arr.add(new ArrayList<>(Arrays.asList(snapshot.child("20210225").child("nickname").getValue()+"",snapshot.getKey()+"",snapshot.child("20210225").child("content").getValue()+"",snapshot.child("20210225").child("likes").getChildrenCount()+"")));
                Log.d("통통통통",arr.size()+"");
                Log.d("메인메인",arr.get(0)+"");
                //adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number1), ContextCompat.getDrawable(getActivity(), R.drawable.trashphoto1),
                //        arr.get(0).get(0),arr.get(0).get(1),arr.get(0).get(2),arr.get(0).get(3));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("General"));
        tabs.addTab(tabs.newTab().setText("Zero Waste"));


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                Fragment selected = null;
                if(position == 0)
                    selected = fragment1;
                else if(position == 1)
                    selected = fragment2;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
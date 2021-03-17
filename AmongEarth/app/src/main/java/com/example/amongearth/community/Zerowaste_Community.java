package com.example.amongearth.community;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.amongearth.MainActivity;
import com.example.amongearth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class Zerowaste_Community extends AppCompatActivity {
    ArrayList<Community_Page1_List2> zero_waste_list;
    ListView zero_waste_board;
    private Context mContext;
    Drawable[] icon;
    TreeMap<Integer, ArrayList<ArrayList<String>>> treeMap;

    private DatabaseReference databaseReference;
    String userId;
    HashMap<String, Object > updatechild;

    ListViewAdapter2 listViewAdapter2;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_zerowaste_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        updatechild = new HashMap<>();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this.getApplicationContext();
        zero_waste_board = (ListView) findViewById(R.id.zero_waste_list);

        icon = new Drawable[]{this.getResources().getDrawable(R.drawable.person1), this.getResources().getDrawable(R.drawable.person2), this.getResources().getDrawable(R.drawable.person3)};
        this.InitializeZeroData();
        listViewAdapter2 = new ListViewAdapter2(this, zero_waste_list);
        zero_waste_board.setAdapter(listViewAdapter2);

        zero_waste_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (zero_waste_list.get(position).getHeartflag().equals("0")){
                    updatechild.put(userId, userId);
                    String s = zero_waste_list.get(position).getDate2().substring(0,4)+zero_waste_list.get(position).getDate2().substring(5,7)+zero_waste_list.get(position).getDate2().substring(8,10);
                    databaseReference.child("challenge_board").child(zero_waste_list.get(position).getUserid()+"").child(s).child("likes").updateChildren(updatechild);
                    zero_waste_list.get(position).setHeartflag("1");
                    zero_waste_list.get(position).setHeart_number2((Integer.parseInt(zero_waste_list.get(position).getHeart_number2())+1)+"");
                    listViewAdapter2.notifyDataSetChanged();
                }
            }
        });



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
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.BtnHome: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void InitializeZeroData(){
        zero_waste_list = new ArrayList<>();
        Intent intent = getIntent();
        HashMap<Integer, ArrayList<ArrayList<String>>> hashmap = (HashMap<Integer, ArrayList<ArrayList<String>>>) intent.getSerializableExtra("hashmap");
        treeMap = new TreeMap<>(hashmap);

        Iterator<Integer> iterator = treeMap.descendingKeySet().iterator();
        Integer key;
        ArrayList<ArrayList<String>> value;
        ListViewAdapter2 k = (ListViewAdapter2) zero_waste_board.getAdapter();

        zero_waste_list.clear();
        int count=0;
        while(iterator.hasNext()){
            key = Integer.parseInt(iterator.next()+"");
            value = treeMap.get(key);
            for (int i=0; i<value.size(); i++){
                String date = value.get(i).get(1).substring(0,4)+"."+value.get(i).get(1).substring(4,6)+"."+value.get(i).get(1).substring(6,8);
                zero_waste_list.add(new Community_Page1_List2( icon[Integer.parseInt(value.get(i).get(5))-1], value.get(i).get(4),
                        value.get(i).get(0), date ,value.get(i).get(2),value.get(i).get(3), value.get(i).get(6), value.get(i).get(7)));
                count++;
            }
            if (k!=null){
                k.notifyDataSetChanged();
            }

        }
    }

}

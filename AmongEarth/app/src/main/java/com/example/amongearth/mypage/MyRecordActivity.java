package com.example.amongearth.mypage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.amongearth.MainActivity;
import com.example.amongearth.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.ListIterator;

public class MyRecordActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    StorageReference storageRef;
    String userId;
    private ArrayList<MyRecord> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecord);

        final GridView gv = (GridView) findViewById(R.id.gridView);
        MyRecordActivity.MyGridAdapter gAdapter = new MyRecordActivity.MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // db에서 값 가져오기?!
        arrayList = new ArrayList<>();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("challenge_board").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot postSnapshot:snapshot.getChildren()){
                    MyRecord myRecord = postSnapshot.getValue(MyRecord.class);
                    Log.d("myRecord", myRecord.getContent()+"");
                    arrayList.add(0, myRecord);
                    Log.d("my_record_arrayList", arrayList+"");
                }
                gAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        public MyGridAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return (arrayList != null ? arrayList.size():0);
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5,25,5,25);

            String img_uri = arrayList.get(i).getUpload_file();
            Log.d("img_uri1", img_uri+"");
            Glide.with(getApplicationContext())
                    .load(img_uri)
                    .into(imageView);

//            String img_path = "images/"+userId+"/"+arrayList.get(i).getUpload_file();
//            Log.d("img_path", img_path);
//
//            storageRef.child(img_path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    Glide.with(getApplicationContext())
//                            .load(uri)
//                            .into(imageView);
//                }
//            });

//            imageView.setImageResource(arrayList.get(i).getUpload_file());

            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Dialog dialog = new Dialog(MyRecordActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                    ImageButton btnClose = (ImageButton) dialog.findViewById(R.id.btnClose);
                    ImageButton btnDelete = (ImageButton) dialog.findViewById(R.id.btnDelete);
                    TextView userName = (TextView) dialog.findViewById(R.id.username);
                    TextView date = (TextView) dialog.findViewById(R.id.date);
                    ImageView userProfile = (ImageView) dialog.findViewById(R.id.userProfile);
                    ImageView ivRecord = (ImageView) dialog.findViewById(R.id.imageView);
                    TextView content = (TextView) dialog.findViewById(R.id.content);

                    userName.setText(arrayList.get(i).getNickname());
                    date.setText(arrayList.get(i).getDate());

                    mDatabase.child("user").child(userId).child("profile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Object profile_num = snapshot.getValue().toString();
                            Log.d("profile_num", profile_num.toString());
                            if (profile_num.equals("1")) userProfile.setImageResource(R.drawable.person1);
                            else if (profile_num.equals("2")) userProfile.setImageResource(R.drawable.person2);
                            else userProfile.setImageResource(R.drawable.person3);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Glide.with(getApplicationContext())
                            .load(img_uri)
                            .into(ivRecord);


//                    storageRef.child(img_path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
////                            Log.d("img_uri2", img_uri+"");
//                            Glide.with(getApplicationContext())
//                                    .load(uri)
//                                    .into(ivRecord);
//                        }
//                    });

                    content.setText(arrayList.get(i).getContent());


                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    // ivRecord.setImageResource(recordID[pos]);


                    //해상도 비율에 맞춰 dialog 크기 조절
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    Window window = dialog.getWindow();
                    int x = (int)(size.x * 0.9f);
                    int y = (int)(size.y * 0.8f);
                    window.setLayout(x,y);

                    dialog.show();
                }
            });
            return imageView;
        }
    }

}


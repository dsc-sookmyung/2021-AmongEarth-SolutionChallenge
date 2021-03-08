package com.example.amongearth.mypage;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.amongearth.MainActivity;
import com.example.amongearth.R;

public class MyRecordActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecord);

        final GridView gv = (GridView) findViewById(R.id.gridView);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            return recordID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        // 그리드뷰에 보일 이미지 파일의 아이디 배열로 저장
        Integer[] recordID = {
                R.drawable.paper, R.drawable.mat, R.drawable.notebook, R.drawable.plastic, R.drawable.pringles,
                R.drawable.scissors, R.drawable.trash, R.drawable.wine, R.drawable.paper, R.drawable.mat, R.drawable.notebook, R.drawable.plastic, R.drawable.pringles,
                R.drawable.scissors, R.drawable.trash, R.drawable.wine, R.drawable.paper, R.drawable.mat, R.drawable.notebook, R.drawable.plastic, R.drawable.pringles,
                R.drawable.scissors, R.drawable.trash, R.drawable.wine
        };

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,30,5,30);
            imageView.setImageResource(recordID[i]);

            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Dialog dialog = new Dialog(MyRecordActivity2.this);
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
                    ivRecord.setImageResource(recordID[pos]);


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

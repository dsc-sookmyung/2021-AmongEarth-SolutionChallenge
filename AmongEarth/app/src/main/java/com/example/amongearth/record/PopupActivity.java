package com.example.amongearth.record;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth.MainActivity;
import com.example.amongearth.community.Fragment2;
import com.example.amongearth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PopupActivity extends AppCompatActivity {

    Button okBtn, cancleBtn;
    String upload_file, nickname, content;
    Integer collected_photo, number_zero, visibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상태바 제거 ( 전체화면 모드 )
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_popup);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        upload_file = intent.getExtras().getString("upload_file");
        nickname = intent.getExtras().getString("nickname");
        content = intent.getExtras().getString("content");
        collected_photo = intent.getExtras().getInt("collected_photo");
        number_zero = intent.getExtras().getInt("number_zero");

        Log.d("countvalue2", collected_photo + "  " + number_zero + "");

        visibility = 1;

        okBtn = (Button) findViewById(R.id.btn_yes);
        cancleBtn = (Button) findViewById(R.id.btn_no);

    }

    //동작 버튼 클릭
    public void mYes(View v){
        visibility = 1;
        collected_photo += 1;
        number_zero += 1;
        //////// 뱃지 이벤트!!! ////////
        badgeUpdate(collected_photo, number_zero);
        addWriteBoard(content, nickname, upload_file, visibility);
        Intent moveToMain = new Intent(this, MainActivity.class);
        startActivity(moveToMain);
        finish();
    }

    //취소 버튼 클릭
    public void mNo(View v){
        visibility = 0;
        collected_photo += 1;
        //////// 뱃지 이벤트!!! ////////
        badgeUpdate(collected_photo, number_zero);
        addWriteBoard(content, nickname, upload_file, visibility);
        Intent moveToMain = new Intent(this, MainActivity.class);
        startActivity(moveToMain);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    private DatabaseReference mDatabase;

    @IgnoreExtraProperties
    public class WritePost {
        public String content, nickname, upload_file;
        public Integer visibility;

        public WritePost(){
            // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        }

        public WritePost(String content, String nickname, String upload_file, Integer visibility) {
            this.content = content;
            this.nickname = nickname;
            this.upload_file = upload_file;
            this.visibility = visibility;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("content", content);
            result.put("nickname", nickname);
            result.put("upload_file", upload_file);
            result.put("visibility", visibility);
            return result;
        }
    }

    public void addWriteBoard(String content, String nickname, String upload_file, Integer visibility) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("userId", userId);
        // 현재시간을 msec 으로 구한다.
        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd");
        // nowDate 변수에 값을 저장한다.
        String formatDate = sdfNow.format(date);
        Log.d("formatDate", formatDate);

        Log.d("AllData", content + nickname + upload_file + visibility.toString());

        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        WritePost post = new WritePost(content, nickname, upload_file, visibility);
        postValues = post.toMap();
        childUpdates.put("/challenge_board/" + userId + "/" + formatDate, postValues);
        mDatabase.updateChildren(childUpdates);
    }

    @IgnoreExtraProperties
    public class BadgePost {
        public Integer collected_photo, number_zero;

        public BadgePost(){
            // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        }

        public BadgePost(Integer collected_photo, Integer number_zero) {
            this.collected_photo = collected_photo;
            this.number_zero = number_zero;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("collected_photo", collected_photo);
            result.put("number_zero", number_zero);
            return result;
        }
    }

    public void badgeUpdate(Integer collected_photo, Integer number_zero) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("userId", userId);

        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        BadgePost post = new BadgePost(collected_photo, number_zero);
        postValues = post.toMap();
        childUpdates.put("/badge/" + userId, postValues);
        mDatabase.updateChildren(childUpdates);
    }
}

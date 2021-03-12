package com.example.amongearth.record;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth.MainActivity;
import com.example.amongearth.community.Fragment2;
import com.example.amongearth.R;
import com.example.amongearth.env.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PopupActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
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
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("user");
        SimpleDateFormat format = new SimpleDateFormat ( "yy.MM.dd");
        Date time = new Date();
        String currentTime = format.format(time);


        visibility = 1;
        collected_photo += 1;
        number_zero += 1;
        //////// 뱃지 이벤트!!! ////////
        /////// 기준 숫자들은 변경 가능! //////
        ////// 테스트를 위해 작은 숫자들로 설정해놓음 ///////
        ////// Q) 두 개 겹치면 어떡하지? collected_photo, number_zero
        // user의 my_badge에도 추가하기!
        if (collected_photo==5) {

            Map<String, Object> UserUpdates = new HashMap<>();
            UserUpdates.put(currentUser.getUid()+"/my_badge/ActiveUser/getDate", currentTime);
            UserUpdates.put(currentUser.getUid()+"/my_badge/ActiveUser/badgeName", "Active User");
            UserUpdates.put(currentUser.getUid()+"/my_badge/ActiveUser/badgeImage", "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/ActiveUser.png?alt=media&token=0f4882f4-e473-4609-801d-bde87f83f2b9");
            UserRef.updateChildren(UserUpdates);


            Toast toastView = new Toast(getApplicationContext());
            ImageView img = new ImageView(getApplicationContext());
            img.setImageResource(R.drawable.toast_active_user);
            toastView.setView(img);
            toastView.setDuration(Toast.LENGTH_LONG);
            toastView.setGravity(Gravity.CENTER, 10, 5);
            toastView.show();
        }

        Toast toastView = new Toast(getApplicationContext());
        ImageView img = new ImageView(getApplicationContext());
        Boolean get_img = false;

        if (number_zero==1) {
            img.setImageResource(R.drawable.toast_start_challenge);
            get_img = true;

            Map<String, Object> UserUpdates = new HashMap<>();
            UserUpdates.put(currentUser.getUid()+"/my_badge/StartChallenge/getDate", currentTime);
            UserUpdates.put(currentUser.getUid()+"/my_badge/StartChallenge/badgeName", "Start Challenge");
            UserUpdates.put(currentUser.getUid()+"/my_badge/StartChallenge/badgeImage", "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/startchallenge.png?alt=media&token=1516b626-de54-419e-b25f-d92a9916c059");
            UserRef.updateChildren(UserUpdates);

        }
        else if(number_zero==3) {
            img.setImageResource(R.drawable.toast_challenge15);
            get_img = true;

            Map<String, Object> UserUpdates = new HashMap<>();
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge15/getDate", currentTime);
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge15/badgeName", "Challenge 15");
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge15/badgeImage", "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/challenge15.png?alt=media&token=0e7562e7-d0b4-4141-9761-19be6be97070");
            UserRef.updateChildren(UserUpdates);

        }
        else if(number_zero==6) {
            img.setImageResource(R.drawable.toast_challenge30);
            get_img = true;

            Map<String, Object> UserUpdates = new HashMap<>();
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge30/getDate", currentTime);
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge30/badgeName", "Challenge 30");
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge30/badgeImage", "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/challenge30.png?alt=media&token=f2d7c9d5-4f2d-4d39-b97d-bd5b99fef6e8");
            UserRef.updateChildren(UserUpdates);
        }
        else if(number_zero==9) {
            img.setImageResource(R.drawable.toast_challenge60);
            get_img = true;

            Map<String, Object> UserUpdates = new HashMap<>();
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge60/getDate", currentTime);
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge60/badgeName", "Challenge 60");
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge60/badgeImage", "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/challenge60.png?alt=media&token=cd6913bc-7c90-416d-823e-f9357607f09f");
            UserRef.updateChildren(UserUpdates);

        }
        else if(number_zero==12) {
            img.setImageResource(R.drawable.toast_challenge90);
            get_img = true;
            Map<String, Object> UserUpdates = new HashMap<>();
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge90/getDate", currentTime);
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge90/badgeName", "Challenge 90");
            UserUpdates.put(currentUser.getUid()+"/my_badge/Challenge90/badgeImage", "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/challenge90.png?alt=media&token=728a4794-98a0-4abc-9c80-64395084c4f3");
            UserRef.updateChildren(UserUpdates);
        }
        if (get_img) {
                toastView.setView(img);
                toastView.setDuration(Toast.LENGTH_LONG);
                toastView.setGravity(Gravity.CENTER, 10, 5);
                toastView.show();
        }
        // User의 my_badge에도 추가!
        badgeUpdate(collected_photo, number_zero);
        addWriteBoard(content, nickname, upload_file, visibility);
        Log.d("addWriteBoard 끝","");
//        Intent moveToMain = new Intent(this, MainActivity.class);
//        startActivity(moveToMain);
//        finish();
    }

    //취소 버튼 클릭
    public void mNo(View v){
        visibility = 0;
        collected_photo += 1;
        //////// 뱃지 이벤트!!! ////////
        if (collected_photo==5) {
            Toast toastView = new Toast(getApplicationContext());
            ImageView img = new ImageView(getApplicationContext());
            img.setImageResource(R.drawable.toast_active_user);
            toastView.setView(img);
            toastView.setDuration(Toast.LENGTH_LONG);
            toastView.setGravity(Gravity.CENTER, 10, 5);
            toastView.show();
        }
        badgeUpdate(collected_photo, number_zero);
        addWriteBoard(content, nickname, upload_file, visibility);
//        Intent moveToMain = new Intent(this, MainActivity.class);
//        startActivity(moveToMain);
//        finish();
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
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private Bitmap sourceBitmap;
    private Bitmap cropBitmap;
    public static final int TF_OD_API_INPUT_SIZE = 416;

    public StorageReference ImageUploadStorage(String userId) {
        // Create a child reference
        // imagesRef now points to "images"
        // StorageReference imagesRef = storageRef.child("images").child(userId);

        // Child references can also take paths
        // spaceRef now points to "images/space.jpg
        // imagesRef still points to "images"
        Log.d("upload_file_substring", upload_file.substring(84));
        StorageReference spaceRef = storageRef.child("images/"+userId+"/"+upload_file.substring(84));
        return spaceRef;
    }

    @IgnoreExtraProperties
    public class WritePost {
        public String content, nickname, upload_file, date;
        public Uri upload_uri;
        public Integer visibility;

        public WritePost(){
            // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        }

        public WritePost(String content, String nickname, String upload_file, Integer visibility, String date) {
            this.content = content;
            this.nickname = nickname;
            this.upload_file = upload_file;
            this.visibility = visibility;
            this.date = date;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("content", content);
            result.put("nickname", nickname);
            result.put("upload_file", upload_file);
            result.put("visibility", visibility);
            result.put("date", date);
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
        SimpleDateFormat sdfNow2 = new SimpleDateFormat("yyyy.MM.dd");
        // nowDate 변수에 값을 저장한다.
        String formatDate = sdfNow.format(date);
        String formatDate2 = sdfNow2.format(date);
        Log.d("formatDate", formatDate);

        Log.d("AllData", content + nickname + upload_file + visibility.toString());

        // Storage에 참조 만들기
        StorageReference spaceRef = ImageUploadStorage(userId);
        // 이미지 비트맵
        this.sourceBitmap = BitmapFactory.decodeFile(upload_file);
        // this.cropBitmap = Utils.processBitmap(sourceBitmap, TF_OD_API_INPUT_SIZE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.sourceBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = spaceRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                // 사진은 올라가는데 DB에 반영이 안돼 아아아아아아아아아아악!!!!!!!!!!!!!!!!!!!!
                // storageRef.child("images").child(userId).child(upload_file.substring(84)).getDownloadUri().toString()
                storageRef.child("images").child(userId).child(upload_file.substring(84)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri upload_uri = uri;

                        // String imgurl = uri.toString();

                        Log.d("upload_uri", upload_uri + "");

//                        Map<String, Object> childUpdates = new HashMap<>();
//                        Map<String, Object> postValues = null;
//                        WritePost post = new WritePost(content, nickname, upload_uri.toString(), visibility, formatDate2);
//                        postValues = post.toMap();

                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("likes").child(userId).setValue(userId);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("content").setValue(content);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("nickname").setValue(nickname);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("upload_file").setValue(upload_uri.toString());
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("visibility").setValue(visibility);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("date").setValue(formatDate2);

//                        childUpdates.put("/challenge_board/" + userId + "/" + formatDate + "/likes/" + userId, userId);
//                        childUpdates.put("/challenge_board/" + userId + "/" + formatDate, postValues);
//                        mDatabase.updateChildren(childUpdates);

                        // likes에 자기 자신 추가!
                        
                        Log.d("메인으로가!","");
                        Intent moveToMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(moveToMain);
                        finish();

                    }
                });
            }
        });



//        Map<String, Object> childUpdates = new HashMap<>();
//        Map<String, Object> postValues = null;
//        WritePost post = new WritePost(content, nickname, upload_file.substring(84), visibility, formatDate2);
//        postValues = post.toMap();
//        childUpdates.put("/challenge_board/" + userId + "/" + formatDate, postValues);
//        mDatabase.updateChildren(childUpdates);
//        // likes에 자기 자신 추가!
//        mDatabase.child("challenge_board").child(userId).child(formatDate).child("likes").child(userId).setValue(userId);

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

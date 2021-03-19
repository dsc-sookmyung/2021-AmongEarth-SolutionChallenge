package com.example.amongearth.record;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import com.example.amongearth.R;
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
        visibility = 1;
        okBtn = (Button) findViewById(R.id.btn_yes);
        cancleBtn = (Button) findViewById(R.id.btn_no);

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation){
        if(Build.VERSION.SDK_INT != Build.VERSION_CODES.O){
            super.setRequestedOrientation(requestedOrientation);
        }
    }

    public void mYes(View v){
        okBtn.setEnabled(false);
        cancleBtn.setEnabled(false);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("user");
        SimpleDateFormat format = new SimpleDateFormat ( "yy.MM.dd");
        Date time = new Date();
        String currentTime = format.format(time);

        visibility = 1;
        collected_photo += 1;
        number_zero += 1;
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
        badgeUpdate(collected_photo, number_zero);
        addWriteBoard(content, nickname, upload_file, visibility);
    }

    public void mNo(View v){
        okBtn.setEnabled(false);
        cancleBtn.setEnabled(false);
        visibility = 0;
        collected_photo += 1;
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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }

    private DatabaseReference mDatabase;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private Bitmap sourceBitmap;
    public static final int TF_OD_API_INPUT_SIZE = 416;

    public StorageReference ImageUploadStorage(String userId) {
        StorageReference spaceRef = storageRef.child("images/"+userId+"/"+upload_file.substring(84));
        return spaceRef;
    }

    @IgnoreExtraProperties
    public class WritePost {
        public String content, nickname, upload_file, date;
        public Uri upload_uri;
        public Integer visibility;

        public WritePost(){
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
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfNow2 = new SimpleDateFormat("yyyy.MM.dd");
        String formatDate = sdfNow.format(date);
        String formatDate2 = sdfNow2.format(date);

        StorageReference spaceRef = ImageUploadStorage(userId);
        this.sourceBitmap = BitmapFactory.decodeFile(upload_file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.sourceBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = spaceRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child("images").child(userId).child(upload_file.substring(84)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri upload_uri = uri;

                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("likes").child(userId).setValue(userId);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("content").setValue(content);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("nickname").setValue(nickname);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("upload_file").setValue(upload_uri.toString());
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("visibility").setValue(visibility);
                        mDatabase.child("challenge_board").child(userId).child(formatDate).child("date").setValue(formatDate2);

                        Intent moveToMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(moveToMain);
                        finish();

                    }
                });
            }
        });
    }

    @IgnoreExtraProperties
    public class BadgePost {
        public Integer collected_photo, number_zero;

        public BadgePost(){
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

        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        BadgePost post = new BadgePost(collected_photo, number_zero);
        postValues = post.toMap();
        childUpdates.put("/badge/" + userId, postValues);
        mDatabase.updateChildren(childUpdates);
    }
}

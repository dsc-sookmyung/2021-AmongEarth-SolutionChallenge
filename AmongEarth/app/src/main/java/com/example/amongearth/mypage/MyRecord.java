package com.example.amongearth.mypage;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyRecord {
    private String date;
    private String content;
    private String nickname;
    private String upload_file;
    //Uri img_uri;

    private DatabaseReference mDatabase;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef= storage.getReference();
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUpload_file() {
        // 이렇게 실행하면 delay가 생겨서 값이 안담긴채로 return 됨
//        String img_path = "images/"+userId+"/"+upload_file;
//        Log.d("img_path", img_path);
//
//        storageRef.child(img_path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            private Uri local_uri;
//            @Override
//            public void onSuccess(Uri uri) {
//                local_uri = uri;
//            }
//        });
//        Log.d("local_uri", local_uri);
//        return img_uri;
        return upload_file;
    }

    public void setUpload_file(String upload_file) {
        this.upload_file = upload_file;
    }
}

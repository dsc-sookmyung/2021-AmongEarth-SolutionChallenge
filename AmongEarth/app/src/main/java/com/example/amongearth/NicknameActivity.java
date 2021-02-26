package com.example.amongearth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class NicknameActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        EditText nickname = (EditText) findViewById(R.id.nickname);
        ImageButton start  = (ImageButton) findViewById(R.id.start_btn);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat ( "yy.MM.dd");
                Date time = new Date();
                String currentTime = format.format(time);

                DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("user");
                Map<String, Object> UserUpdates = new HashMap<>();
                UserUpdates.put(currentUser.getUid()+"/nickname", nickname.getText().toString());
                UserUpdates.put(currentUser.getUid()+"/my_badge/Welcome", currentTime);
                UserRef.updateChildren(UserUpdates);

                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

    }
}
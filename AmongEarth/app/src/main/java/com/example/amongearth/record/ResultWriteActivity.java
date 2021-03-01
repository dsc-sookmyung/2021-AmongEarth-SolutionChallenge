package com.example.amongearth.record;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth.R;
import com.example.amongearth.env.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResultWriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_write);

        result_image = findViewById(R.id.result_icon);
        result_text = findViewById(R.id.icon_text);
        origin_image = findViewById(R.id.origin_img);

        Intent intent = getIntent();
        String origin_path = intent.getExtras().getString("originPath");
        Integer sum_total = intent.getExtras().getInt("sum_total"); // 전체 쓰레기 개수

        //////////// 전체 쓰레기 개수에 따른 결과 아이콘 //////////////
        // 부정/보통/긍정 기준 점수
        Integer bad_score, normal_score, good_score;
        bad_score = 10; normal_score = 5; good_score = 3;

        // 각각의 랜덤 결과 아이콘 개수  -> 나머지 인덱스로 리스트 접근하게 하면 코드 더 깔끔할듯!
        Integer bad_case, normal_case, good_case;
        bad_case = 3; normal_case = 2; good_case = 3;

        if (sum_total > bad_score) {  // 부정
            if(sum_total % bad_case == 0) {
                result_image.setImageResource(R.drawable.result_fever_earth);
                result_text.setText(getString(R.string.result_fever_earth));
            }
            else if(sum_total % bad_case == 1) {
                result_image.setImageResource(R.drawable.result_sad_earth);
                result_text.setText(getString(R.string.result_sad_earth));
            }
            else {
                result_image.setImageResource(R.drawable.result_polar_bear);
                result_text.setText(getString(R.string.result_polar_bear));
            }
        }
        else if (sum_total > normal_score) {  // 보통
            if(sum_total % normal_case == 0) {
                result_image.setImageResource(R.drawable.result_healthy_earth);
                result_text.setText(getString(R.string.result_healthy_earth));
            }
            else {
                result_image.setImageResource(R.drawable.result_change_earth);
                result_text.setText(getString(R.string.result_change_earth));
            }
        }
        else {
            if(sum_total % good_case == 0) {  // 긍정
                result_image.setImageResource(R.drawable.result_save_lover);
                result_text.setText(getString(R.string.result_save_lover));
            }
            else if(sum_total % good_case == 1) {
                result_image.setImageResource(R.drawable.result_penguin);
                result_text.setText(getString(R.string.result_penguin));
            }
            else {
                result_image.setImageResource(R.drawable.result_orangutan);
                result_text.setText(getString(R.string.result_orangutan));
            }
        }

        // 사용자가 찍은 원본 이미지 보여주기
        this.sourceBitmap = BitmapFactory.decodeFile(origin_path);
        this.cropBitmap = Utils.processBitmap(sourceBitmap, TF_OD_API_INPUT_SIZE);
        origin_image.setImageBitmap(this.cropBitmap);


        // 뱃지 상태 확인하기
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase.child("badge").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object value = snapshot.child(userId).getValue();
                if (ObjectUtils.isEmpty(value)) {
                    addBadge(0, 0);
                }
                collected_photo = (int) (long) snapshot.child(userId).child("collected_photo").getValue();
                number_zero = (int) (long) snapshot.child(userId).child("number_zero").getValue();
                Log.d("count", collected_photo + "  " + number_zero + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        write = findViewById(R.id.user_write); // 사용자가 적은 글

        Log.d("write", String.valueOf(write.getText()));

        saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("countvalue", collected_photo + "  " + number_zero + "");
                Intent intent2 = new Intent(ResultWriteActivity.this, PopupActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("upload_file", origin_path);
                bundle.putString("nickname", "omocomo");
                bundle.putString("content", String.valueOf(write.getText()));
                bundle.putInt("collected_photo", collected_photo);
                bundle.putInt("number_zero", number_zero);
                intent2.putExtras(bundle);
                setResult(1, intent2);
                startActivity(intent2);
            }
        });
    }

    public static final int TF_OD_API_INPUT_SIZE = 416;

    private Bitmap sourceBitmap;
    private Bitmap cropBitmap;

    private ImageView result_image, origin_image;
    private TextView result_text;
    private Button saveButton;
    private EditText write;

    private DatabaseReference mDatabase;
    String userId;
    Integer collected_photo, number_zero;

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

    public void addBadge(Integer collected_photo, Integer number_zero) {
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

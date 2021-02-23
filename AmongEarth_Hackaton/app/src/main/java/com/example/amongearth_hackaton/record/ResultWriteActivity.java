package com.example.amongearth_hackaton.record;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth_hackaton.R;
import com.example.amongearth_hackaton.env.Utils;

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

        write = findViewById(R.id.user_write); // 사용자가 적은 글

        Log.d("write", String.valueOf(write.getText()));

        saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ResultWriteActivity.this, PopupActivity.class);
                startActivityForResult(intent2, 1);
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

}

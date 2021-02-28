package com.example.amongearth.record;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth.R;
import com.example.amongearth.env.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        imageView = findViewById(R.id.detect_img);
        okButton = findViewById(R.id.btn_ok);

        paper_minus = findViewById(R.id.paper_minus);
        paper_plus = findViewById(R.id.paper_plus);
        metal_minus = findViewById(R.id.metal_minus);
        metal_plus = findViewById(R.id.metal_plus);
        glass_minus = findViewById(R.id.glass_minus);
        glass_plus = findViewById(R.id.glass_plus);
        plastic_minus = findViewById(R.id.plastic_minus);
        plastic_plus = findViewById(R.id.plastic_plus);
        waste_minus = findViewById(R.id.waste_minus);
        waste_plus = findViewById(R.id.waste_plus);
        nothing_minus = findViewById(R.id.nothing_minus);
        nothing_plus = findViewById(R.id.nothing_plus);

        paper_num = findViewById(R.id.paper_num);
        metal_num = findViewById(R.id.metal_num);
        glass_num = findViewById(R.id.glass_num);
        plastic_num = findViewById(R.id.plastic_num);
        waste_num = findViewById(R.id.waste_num);
        nothing_num = findViewById(R.id.nothing_num);
        sum_num = findViewById(R.id.sum_num);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        String origin_path = intent.getExtras().getString("originPath");
        String imgPath = intent.getExtras().getString("imgPath");
        Integer num_paper = intent.getExtras().getInt("num_paper");
        Integer num_metal = intent.getExtras().getInt("num_metal");
        Integer num_glass = intent.getExtras().getInt("num_glass");
        Integer num_plastic = intent.getExtras().getInt("num_plastic");
        Integer num_waste = intent.getExtras().getInt("num_waste");
        Integer num_nothing = intent.getExtras().getInt("num_nothing");

        Log.d("originPath", origin_path);

        this.sourceBitmap = BitmapFactory.decodeFile(imgPath);
        this.cropBitmap = Utils.processBitmap(sourceBitmap, TF_OD_API_INPUT_SIZE);
        this.imageView.setImageBitmap(cropBitmap);

        paper_num.setText(num_paper.toString());
        metal_num.setText(num_metal.toString());
        glass_num.setText(num_glass.toString());
        plastic_num.setText(num_plastic.toString());
        waste_num.setText(num_waste.toString());
        nothing_num.setText(num_nothing.toString());

        num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
        sum_num.setText(num_sum.toString());

        ////////////// +,- 쓰레기 양 조절, 전체 개수 계속 바뀜 /////////////////
        paper_minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)paper_num.getText())-1;
                if(num < 0) num = 0;
                paper_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        paper_plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)paper_num.getText())+1;
                paper_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        metal_minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)metal_num.getText())-1;
                if(num < 0) num = 0;
                metal_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        metal_plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)metal_num.getText())+1;
                metal_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        glass_minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)glass_num.getText())-1;
                if(num < 0) num = 0;
                glass_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        glass_plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)glass_num.getText())+1;
                glass_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        plastic_minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)plastic_num.getText())-1;
                if(num < 0) num = 0;
                plastic_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        plastic_plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)plastic_num.getText())+1;
                plastic_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        waste_minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)waste_num.getText())-1;
                if(num < 0) num = 0;
                waste_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        waste_plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)waste_num.getText())+1;
                waste_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        nothing_minus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)nothing_num.getText())-1;
                if(num < 0) num = 0;
                nothing_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        nothing_plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Integer num = Integer.parseInt((String)nothing_num.getText())+1;
                nothing_num.setText(num.toString());
                num_sum = Integer.parseInt((String)paper_num.getText())+ Integer.parseInt((String)metal_num.getText())+ Integer.parseInt((String)glass_num.getText())+ Integer.parseInt((String)plastic_num.getText())+ Integer.parseInt((String)waste_num.getText())+ Integer.parseInt((String)nothing_num.getText());
                sum_num.setText(num_sum.toString());
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///////////// Firebase num 값 //////////////
                Integer glass = Integer.parseInt((String)glass_num.getText());
                Integer metal = Integer.parseInt((String)metal_num.getText());
                Integer none = Integer.parseInt((String)nothing_num.getText());
                Integer paper = Integer.parseInt((String)paper_num.getText());
                Integer plastic = Integer.parseInt((String)plastic_num.getText());
                Integer total = Integer.parseInt((String)sum_num.getText());
                Integer waste = Integer.parseInt((String)waste_num.getText());
                addWasteRecord(glass, metal, none, paper, plastic, total, waste);

                Intent intent2 = new Intent(CountActivity.this, ResultWriteActivity.class);
                Log.d("originPath", origin_path);
                Bundle bundle = new Bundle();
                bundle.putString("originPath", origin_path);
                bundle.putInt("sum_total", num_sum);
                intent2.putExtras(bundle);
                setResult(1, intent2);
                startActivity(intent2);
            }
        });
    }

    public static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.5f;
    public static final int TF_OD_API_INPUT_SIZE = 416;

    private Bitmap sourceBitmap;
    private Bitmap cropBitmap;

    private Button okButton;
    private ImageView imageView;

    private Button paper_minus, paper_plus, metal_minus, metal_plus, glass_minus, glass_plus, plastic_minus, plastic_plus, waste_minus, waste_plus, nothing_minus, nothing_plus;
    private TextView paper_num, metal_num, glass_num, plastic_num, waste_num, nothing_num, sum_num;
    private Integer num_sum;

    private DatabaseReference mDatabase;

    @IgnoreExtraProperties
    public class CountPost {
        public Integer glass, metal, none, paper, plastic, total, waste;

        public CountPost(){
            // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
        }

        public CountPost(Integer glass, Integer metal, Integer none, Integer paper, Integer plastic, Integer total, Integer waste) {
            this.glass = glass;
            this.metal = metal;
            this.none = none;
            this.paper = paper;
            this.plastic = plastic;
            this.total = total;
            this.waste = waste;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("glass", glass);
            result.put("metal", metal);
            result.put("none", none);
            result.put("paper", paper);
            result.put("plastic", plastic);
            result.put("total", total);
            result.put("waste", waste);
            return result;
        }
    }

    public void addWasteRecord(Integer glass, Integer metal, Integer none, Integer paper, Integer plastic, Integer total, Integer waste) {
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

        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        CountPost post = new CountPost(glass, metal, none, paper, plastic, total, waste);
        postValues = post.toMap();
        childUpdates.put("/waste_record/" + userId + "/" + formatDate, postValues);
        mDatabase.updateChildren(childUpdates);
    }
}

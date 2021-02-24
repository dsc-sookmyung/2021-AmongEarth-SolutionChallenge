package com.example.amongearth_hackaton.record;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth_hackaton.Community_Page.Fragment2;
import com.example.amongearth_hackaton.LoadingActivity;
import com.example.amongearth_hackaton.R;

public class PopupActivity extends AppCompatActivity {

    Button okBtn, cancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상태바 제거 ( 전체화면 모드 )
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_popup);

        okBtn = (Button) findViewById(R.id.btn_yes);
        cancleBtn = (Button) findViewById(R.id.btn_no);

    }

    //동작 버튼 클릭
    public void mYes(View v){
        Intent moveToChallenge = new Intent(this, Fragment2.class);
        startActivity(moveToChallenge);
        finish();
        // 챌린지 게시판!
    }

    //취소 버튼 클릭
    public void mNo(View v){
        finish();
        // 메인 화면으로 가기?
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
}

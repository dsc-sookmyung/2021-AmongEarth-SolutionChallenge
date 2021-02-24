package com.example.amongearth_hackaton.Community_Page;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.amongearth_hackaton.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Community_Page1_TabLayout extends TabLayout {
    private List<String> titles;

    public Community_Page1_TabLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public Community_Page1_TabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Community_Page1_TabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        titles = new ArrayList<>(); // 이곳에 Table 목록들이 있음


        this.addOnTabSelectedListener(new OnTabSelectedListener(){

            @Override
            public void onTabSelected(Tab tab) {
                /*
                * Set the currently selected Tab to a special highlight style. // 즉 탭이 선택되면
                * 1. 탭 색도 변해야 하고
                * 2. 새로운 Fragment도 나와야한다
                 */

                int position = tab.getPosition();
                Fragment selected = null;

                if (tab!=null&&tab.getCustomView()!=null){ // 이곳이 중요함
                    Log.d("여기가 클릭되어지구 있는가?","ㄹ알아");
                    TextView tab_layout_text = tab.getCustomView().findViewById(R.id.tab_layout_text);

                    //tab_layout_text.setTextColor(Color.WHITE);
                    tab_layout_text.setBackgroundResource(R.drawable.community_page1_tab_layout_item_clicked);

                }
            }

            @Override
            public void onTabUnselected(Tab tab) {
                /**
                 * Reset all unselected Tab colors, fonts, background to normal (unselected state).
                 */
                if (tab != null && tab.getCustomView() != null) {
                    TextView tab_layout_text = tab.getCustomView().findViewById(R.id.tab_layout_text);

                    //tab_layout_text.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    tab_layout_text.setBackgroundResource(R.drawable.community_page1_tab_layout_item);
                }

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }
    public void setTitle(List<String> titles) {
        this.titles = titles;

        /**
         * Start adding tabs for switching.
         */
        Log.d("여기는 들어왔나???","콩콩콩");
        for (String title : this.titles) {
            Tab tab = newTab();
            tab.setCustomView(R.layout.community_page1_tab_layout);

            if (tab.getCustomView() != null) {
                TextView text = tab.getCustomView().findViewById(R.id.tab_layout_text);
                text.setText(title);
            }

            this.addTab(tab);
        }
    }

}

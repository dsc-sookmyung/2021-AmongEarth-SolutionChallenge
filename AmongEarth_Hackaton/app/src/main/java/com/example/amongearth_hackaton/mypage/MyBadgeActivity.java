package com.example.amongearth_hackaton.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.amongearth_hackaton.MainActivity;
import com.example.amongearth_hackaton.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MyBadgeActivity extends AppCompatActivity {
    ArrayList<BadgeData> badgeDataList;
    ListView my_badges;
    private Context mcontext =this;
    DrawerLayout drawerLayout2;
    NavigationView navigationView2;
    View navHeader2;
    ImageView close_nav2;
    TextView view_all2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybadge);

        //Toolbar 설정
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView2 = findViewById(R.id.navigationView);
        drawerLayout2 = findViewById(R.id.drawer_layout);
        close_nav2 = findViewById(R.id.close_nav);
        /*navHeader2 = navigationView2.getHeaderView(0);
            view_all2 = (TextView) navHeader2.findViewById(R.id.view_all);
          view_all2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myBadge = new Intent(getApplicationContext(), MyBadgeActivity.class);
                startActivity(myBadge);
            }
        });
        close_nav2 = (ImageView) navHeader2.findViewById(R.id.close_nav);
        close_nav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout2.closeDrawer(Gravity.RIGHT);
            }
        });

       navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                menuItem.setChecked(true);
                drawerLayout2.closeDrawer(Gravity.RIGHT);

                int id = menuItem.getItemId();

                switch(id){
                    case R.id.my_posts:
                        Intent myPosts = new Intent(getApplicationContext(), MyPostsActivity.class);
                        startActivity(myPosts);
                        break;
                }
                return true;

            }
        });

*/
        this.getApplicationContext();
        ListView badge_list = findViewById(R.id.my_badges);
        this.InitializeBadgeData();
        final BadgeAdapter badgeAdapter = new BadgeAdapter(this,badgeDataList);

        badge_list.setAdapter(badgeAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
            case R.id.BtnHome: { // 오른쪽 상단 버튼 눌렀을 때
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void InitializeBadgeData() {
        badgeDataList = new ArrayList<BadgeData>();
        badgeDataList.add(new BadgeData(R.drawable.tree, "Start Among Earth","2021/02/02"));
        badgeDataList.add(new BadgeData(R.drawable.challenge15, "Zero Waste Challenge Day15 Success","2021/03/04"));
        badgeDataList.add(new BadgeData(R.drawable.challenge30, "Zero Waste Challenge 1Month Success","2021/03/20"));
        badgeDataList.add(new BadgeData(R.drawable.first_join, "Zero  Success","2021/03/20"));

    }
}

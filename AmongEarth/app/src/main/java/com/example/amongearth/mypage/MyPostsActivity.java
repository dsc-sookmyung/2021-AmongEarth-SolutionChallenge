package com.example.amongearth.mypage;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amongearth.R;

import java.util.ArrayList;

public class MyPostsActivity extends AppCompatActivity {
    ArrayList<PostData> postDataList;
    ListView my_contents;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycontents);
        this.getApplicationContext();
        ListView listView = (ListView)findViewById(R.id.my_contents);
        this.InitializePostData();
        final PostAdapter postAdapter = new PostAdapter(this,postDataList);

        listView.setAdapter(postAdapter);

    }

    private void InitializePostData() {
        postDataList = new ArrayList<PostData>();
        postDataList.add(new PostData(R.drawable.trash, "How to get rid of it?","It's really honor to give you this useful information"));
        postDataList.add(new PostData(R.drawable.trash, "[Hot Issue] Eco Friendly -1","It's really honor to give you this useful information"));
        postDataList.add(new PostData(R.drawable.wine, "[Hot Issue] Eco Friendly -3","It's really honor to give you this useful information"));
    }
}

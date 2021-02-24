package com.example.amongearth_hackaton.Community_Page;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.amongearth_hackaton.R;

public class Community_Page3 extends AppCompatActivity {

    ListViewAdapter3 adapter3;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_page_3);

        adapter3 = new ListViewAdapter3(Community_Page3.this);
        listView = (ListView) findViewById(R.id.page3_listview);

        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.person3), "Jack","Wed", getResources().getString(R.string.community_page3_note2));
        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.person3), "Jack","Thu", getResources().getString(R.string.community_page3_note2));
        adapter3.addItem(ContextCompat.getDrawable(this, R.drawable.person3), "Jack","Fri", getResources().getString(R.string.community_page3_note2));

        listView.setAdapter(adapter3);
    }

    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Community_Page3_List1 item = (Community_Page3_List1) l.getItemAtPosition(position) ;

        Drawable iconDrawble3 = item.getImage_icon3();
        String idStr3 = item.getId3();
        String dateStr3 = item.getDate3();
        String contentStr3 = item.getContent3();

        // TODO : use item data.
    }
}

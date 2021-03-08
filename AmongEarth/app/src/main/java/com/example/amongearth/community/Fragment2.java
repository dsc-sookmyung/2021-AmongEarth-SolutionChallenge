package com.example.amongearth.community;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.ListFragment;

import com.example.amongearth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Fragment2 extends ListFragment {

    ListViewAdapter2 adapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.community_page_2, container, false);

        // Adapter 생성 및 Adapter 지정
        adapter2 = new ListViewAdapter2(this.getContext());
        setListAdapter(adapter2);






        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number1), "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/images%2F1QxE4XKq4tdh5j42fayOv3uW0pf2%2F21-03-06%20202404.jpg?alt=media&token=7cd6334f-6ca8-44a1-8e7a-85f128f97b35",
                "number1","Wed","Hi, I'm Number1","1", "shpark0308");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number2), "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/images%2F1QxE4XKq4tdh5j42fayOv3uW0pf2%2F21-03-06%20202404.jpg?alt=media&token=7cd6334f-6ca8-44a1-8e7a-85f128f97b35",
                "number2","Thu","Hi, I'm Number2","2","shpark0308");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/images%2F1QxE4XKq4tdh5j42fayOv3uW0pf2%2F21-03-06%20202404.jpg?alt=media&token=7cd6334f-6ca8-44a1-8e7a-85f128f97b35",
                "number3","Fri","Hi, I'm Number3","3","shpark0308");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/images%2F1QxE4XKq4tdh5j42fayOv3uW0pf2%2F21-03-06%20202404.jpg?alt=media&token=7cd6334f-6ca8-44a1-8e7a-85f128f97b35",
                "number3","Mon","Hi, I'm Number3","3","shpark0308");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), "https://firebasestorage.googleapis.com/v0/b/dsc-among-earth.appspot.com/o/images%2F1QxE4XKq4tdh5j42fayOv3uW0pf2%2F21-03-06%20202404.jpg?alt=media&token=7cd6334f-6ca8-44a1-8e7a-85f128f97b35",
                "number3","Sun","Hi, I'm Number3","3","shpark0308");

        return rootView;
    }
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Community_Page1_List2 item = (Community_Page1_List2) l.getItemAtPosition(position) ;

        Drawable iconDrawble2 = item.getImage2_icon();
        String photoDrawable2 = item.getImage2_photo();
        String idStr2 = item.getId2();
        String dateStr2 = item.getDate2();
        String contentStr2 = item.getContent2();
        String heartnumStr2 = item.getHeart_number2();

        // TODO : use item data.
    }
}
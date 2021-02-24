package com.example.amongearth.community;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.ListFragment;

import com.example.amongearth.R;

public class Fragment2 extends ListFragment {

    ListViewAdapter2 adapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.community_page_2, container, false);

        // Adapter 생성 및 Adapter 지정
        adapter2 = new ListViewAdapter2(this.getContext());
        setListAdapter(adapter2);

        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number1), ContextCompat.getDrawable(getActivity(), R.drawable.trashphoto1),
                "number1","Wed","Hi, I'm Number1","1");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number2), ContextCompat.getDrawable(getActivity(), R.drawable.trashphoto1),
                "number2","Thu","Hi, I'm Number2","2");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), ContextCompat.getDrawable(getActivity(), R.drawable.trashphoto1),
                "number3","Fri","Hi, I'm Number3","3");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), ContextCompat.getDrawable(getActivity(), R.drawable.trashphoto1),
                "number3","Mon","Hi, I'm Number3","3");
        adapter2.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), ContextCompat.getDrawable(getActivity(), R.drawable.trashphoto1),
                "number3","Sun","Hi, I'm Number3","3");

        return rootView;
    }
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Community_Page1_List2 item = (Community_Page1_List2) l.getItemAtPosition(position) ;

        Drawable iconDrawble2 = item.getImage2_icon();
        Drawable photoDrawable2 = item.getImage2_photo();
        String idStr2 = item.getId2();
        String dateStr2 = item.getDate2();
        String contentStr2 = item.getContent2();
        String heartnumStr2 = item.getHeart_number2();

        // TODO : use item data.
    }
}
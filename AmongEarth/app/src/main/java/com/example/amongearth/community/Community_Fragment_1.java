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

@Deprecated
public class Community_Fragment_1 extends ListFragment {

    /* 일단 하는데까지 해보기 */

    ListViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.community_page_1, container, false);

        // Adapter 생성 및 Adapter 지정
        adapter = new ListViewAdapter(this.getContext());
        setListAdapter(adapter);

        /* 여기서 item을 추가하는 것같음 */
        // 첫번째 아이템을 추가
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number1), ContextCompat.getDrawable(getActivity(), R.drawable.photo1),"Number1", "I'm Number1","Number1 is the best");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number2), ContextCompat.getDrawable(getActivity(), R.drawable.photo1),"Number2", "I'm Number2","Number2 is the best");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), ContextCompat.getDrawable(getActivity(), R.drawable.photo1),"Number3", "I'm Number3","Number3 is the best");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), ContextCompat.getDrawable(getActivity(), R.drawable.photo1),"Number3", "I'm Number3","Number3 is the best");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.number3), ContextCompat.getDrawable(getActivity(), R.drawable.photo1),"Number3", "I'm Number3","Number3 is the best");
        return rootView;

    }

    /* 클릭되면 ... */
    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Community_Page1_List1 item = (Community_Page1_List1) l.getItemAtPosition(position) ;

        Drawable iconDrawble = item.getImage_icon();
        Drawable photoDrawable = item.getImage_photo();
        String idStr = item.getId();
        String titleStr = item.getTitle();
        String contentStr = item.getContent();

        // TODO : use item data.


    }

}
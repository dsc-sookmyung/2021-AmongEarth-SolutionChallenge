package com.example.amongearth_hackaton.Community_Page;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

@Deprecated
public class TabFragment extends Fragment {

    Fragment1 fragment1; // 21.02.20
    Fragment2 fragment2; // 21.02.20 : 예시
    Community_Fragment_1 fragment_1;
    Community_Fragment_2 fragment_2;
    Community_Fragment_3 fragment_3;
    Community_Fragment_4 fragment_4;


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.community_page_1_tabpage, container, false);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2(); // 21.02.20
        fragment_1 = new Community_Fragment_1();
        fragment_2 = new Community_Fragment_2();
        fragment_3 = new Community_Fragment_3();
        fragment_4 = new Community_Fragment_4();
        getFragmentManager().beginTransaction().add(R.id.container2, fragment_1).commit();



        String[] str = {"전체보기","분리수거","미술놀이","생활꿀팁"};

        List<String> titles = Arrays.asList(str);

        Community_Page1_TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.setTitle(titles);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position==0){
                    selected = fragment_1;
                }
                else if (position==1){
                    selected = fragment_2;
                }
                else if (position==2){
                    selected = fragment_3;
                }
                else if (position==3){
                    selected = fragment_4;
                }
                getFragmentManager().beginTransaction().replace(R.id.container2, selected).commit();
                Log.d("콩콩콩","콩콩콩");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return rootView;
    }
}
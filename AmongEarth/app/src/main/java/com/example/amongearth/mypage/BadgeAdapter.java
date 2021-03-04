

package com.example.amongearth.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amongearth.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {

    private ArrayList<Badge> arrayList;
    private Context context;

    public BadgeAdapter(ArrayList<Badge> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybadge_item, parent, false);
        BadgeViewHolder holder = new BadgeViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
           Glide.with(holder.itemView)
                    .load(arrayList.get(position).getBadgeImage())
                    .override(60, 60)
                    .into(holder.badge_image);
            holder.badge_name.setText(arrayList.get(position).getBadgeName());
            holder.get_Date.setText(arrayList.get(position).getGetDate());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size():0) ;
    }

    public class BadgeViewHolder extends RecyclerView.ViewHolder {


       ImageView badge_image;
        TextView badge_name;
        TextView get_Date;

        public BadgeViewHolder(@NonNull View itemView) {
            super(itemView);

           this.badge_image = itemView.findViewById(R.id.badge_image);
            this.badge_name = itemView.findViewById(R.id.badge_name);
            this.get_Date = itemView.findViewById(R.id.get_Date);
        }
    }
}

package com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.FoodInfoActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Food;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirzo-golibsuvonberdiev on 12/20/18.
 */

public class MenuAdapter extends RecyclerView.Adapter<SpesicifViewHolder> {

    private List<Food> listOfFood = new ArrayList<>();
    private Context context;

    public MenuAdapter(List<Food> listOfFood, Context context) {
        this.listOfFood = listOfFood;
        this.context = context;
    }

    @NonNull
    @Override
    public SpesicifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_item_specific, parent, false);
        return new SpesicifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpesicifViewHolder holder, int position) {

        holder.textViewName.setText(listOfFood.get(position).getName());

        Glide.with(this.context).load(listOfFood.get(position).getImage())
                .into(holder.imageViewItem);
        holder.favouriteButton.setImageResource(R.drawable.ic_favorite_white);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                String[] seperated = listOfFood.get(position).getFoodId().split("/");
                String instanceId = seperated[3];
                String parentId= seperated[1];
                Intent intent = new Intent(view.getContext(), FoodInfoActivity.class);
                intent.putExtra("foodId", instanceId);
                intent.putExtra("generalId", parentId);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfFood.size();
    }
}

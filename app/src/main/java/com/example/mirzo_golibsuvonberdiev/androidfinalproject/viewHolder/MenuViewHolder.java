package com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mirzo-golibsuvonberdiev on 12/17/18.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_name)
    public TextView textViewName;
    @BindView(R.id.item_image)
    public ImageView imageViewItem;


    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }


}

package com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mirzo-golibsuvonberdiev on 12/21/18.
 */

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;

    @BindView(R.id.order_number)
    public TextView orderNumber;
    @BindView(R.id.ordered_status)
    public TextView orderStatus;
    @BindView(R.id.ordered_phone)
    public TextView orderPhone;
    @BindView(R.id.ordered_address)
    public TextView orderAddress;
    @BindView(R.id.quantity_ordered)
    public ImageView coloredButton;



    public OrderHistoryViewHolder(View itemView) {
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

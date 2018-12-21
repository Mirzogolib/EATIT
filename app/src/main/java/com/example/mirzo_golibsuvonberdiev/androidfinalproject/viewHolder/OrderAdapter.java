package com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mirzo-golibsuvonberdiev on 12/20/18.
 */

class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.ordered_item_name)
    public TextView orderedItemName;
    @BindView(R.id.ordered_item_price)
    public TextView orderedItemPrice;
    @BindView(R.id.quantity_ordered)
    public ImageView quantity;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setOrderedItemName(TextView orderedItemName) {
        this.orderedItemName = orderedItemName;
    }

    @Override
    public void onClick(View view) {

    }
}

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private List<Order> listOfOrder = new ArrayList<>();
    private Context context;

    public OrderAdapter(List<Order> listOfOrder, Context context) {
        this.listOfOrder = listOfOrder;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + listOfOrder.get(position).getQuantity(), Color.RED);
        holder.quantity.setImageDrawable(drawable);


        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listOfOrder.get(position).getPrice()))*(Integer.parseInt(listOfOrder.get(position).getQuantity()));
        holder.orderedItemPrice.setText(format.format(price));
        holder.orderedItemName.setText(listOfOrder.get(position).getProductName());


    }

    @Override
    public int getItemCount() {
        return listOfOrder.size();
    }
}

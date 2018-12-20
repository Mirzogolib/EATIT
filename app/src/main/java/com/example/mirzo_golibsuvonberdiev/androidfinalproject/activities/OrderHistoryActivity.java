package com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.common.Common;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.TotalOrders;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.OrderHistoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryActivity extends AppCompatActivity {

    @BindView(R.id.list_orders)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase fb;
    DatabaseReference dr;
    FirebaseRecyclerAdapter<TotalOrders, OrderHistoryViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        ButterKnife.bind(this);

        fb = FirebaseDatabase.getInstance();
        dr = fb.getReference("Requests");

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getPhone());

    }

    private void loadOrders(String phone) {
        adapter = new FirebaseRecyclerAdapter<TotalOrders, OrderHistoryViewHolder>(
                TotalOrders.class,
                R.layout.order_history_item,
                OrderHistoryViewHolder.class,
                dr.orderByChild("phone").equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrderHistoryViewHolder viewHolder, TotalOrders model, int position) {
                viewHolder.orderNumber.setText(adapter.getRef(position).getKey());
                viewHolder.orderStatus.setText(convertStatus(model.getStatus()));
                viewHolder.orderAddress.setText(model.getAddress());
                viewHolder.orderPhone.setText(model.getPhone());

            }

            private String convertStatus(String status) {
                if (status.equals("0")){
                    return "Order uploaded";
                }else if (status.equals("1")){
                    return "On the way";
                }else{
                    return "Delivered";
                }


            }
        };

        recyclerView.setAdapter(adapter);
    }
}

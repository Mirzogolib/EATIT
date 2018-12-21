package com.example.mirzo_golibsuvonberdiev.androidfinalproject.admin;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.common.Common;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.TotalOrders;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.OrderHistoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mirzo-golibsuvonberdiev on 12/21/18.
 */

public class AdminOrderUpdate extends AppCompatActivity {

    @BindView(R.id.list_orders)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase fb;
    DatabaseReference dr, databaseReferenceInFunction;
    FirebaseRecyclerAdapter<TotalOrders, OrderHistoryViewHolder> adapter;
    String[] spinner = {"0. Order uploaded", "1. On the way", "2. Delivered"};

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

        loadOrders();

    }

    private void loadOrders() {
        adapter = new FirebaseRecyclerAdapter<TotalOrders, OrderHistoryViewHolder>(
                TotalOrders.class,
                R.layout.order_history_item,
                OrderHistoryViewHolder.class,
                dr
        ) {
            @Override
            protected void populateViewHolder(OrderHistoryViewHolder viewHolder, final TotalOrders model, int position) {
                viewHolder.orderNumber.setText(adapter.getRef(position).getKey());
                viewHolder.orderStatus.setText(convertStatus(model.getStatus()));
                viewHolder.orderAddress.setText(model.getAddress());
                viewHolder.orderPhone.setText(model.getPhone());
                changeColor(viewHolder, model.getStatus());
                TotalOrders totalOrder = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Log.d("MYTAG", "Clickeeeed");
                        dialogShow(adapter.getRef(position).getKey(), model);
                    }
                });
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

    private void dialogShow(final String key, final TotalOrders model) {

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);


        final Spinner sp = new Spinner(AdminOrderUpdate.this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Status of order");
        ad.setMessage("Verify status of order");
        ad.setView(sp);
        Log.d("MYTAG", key);


        ad.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String selected = sp.getSelectedItem().toString();
                Log.d("MYTAG", selected);
                StringTokenizer stringTokenizer = new StringTokenizer(selected, ".");
                final String aa = stringTokenizer.nextToken();
                Log.d("MYTAG", aa);
                databaseReferenceInFunction = fb.getReference("Requests");
                databaseReferenceInFunction.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        model.setStatus(aa);
                        databaseReferenceInFunction.child(key).setValue(model);
                        Toast.makeText(AdminOrderUpdate.this, "Status changed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        ad.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();     
            }
        });
        ad.show();


    }

    private void changeColor(OrderHistoryViewHolder viewHolder, String status) {
        TextDrawable drawable;
        if (status.equals("0")) {
            drawable = TextDrawable.builder()
                    .buildRound(" ", Color.RED);

        } else if (status.equals("1")) {
            drawable = TextDrawable.builder()
                    .buildRound(" ", Color.BLUE);
        } else {
            drawable = TextDrawable.builder()
                    .buildRound(" ", Color.GREEN);
        }
        viewHolder.coloredButton.setImageDrawable(drawable);

    }
}

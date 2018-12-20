package com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.database.Database;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Food;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodInfoActivity extends AppCompatActivity {

    @BindView(R.id.food_name)
    TextView foodName;
    @BindView(R.id.food_price)
    TextView foodPrice;
    @BindView(R.id.food_descp)
    TextView description;
    @BindView(R.id.collapse_image)
    ImageView foodImage;
    @BindView(R.id.elegant_number)
    ElegantNumberButton elegantNumberButton;

    @BindView(R.id.btn_cart)
    FloatingActionButton fab;

    @BindView(R.id.collapsing_header)
    CollapsingToolbarLayout collapsingToolbarLayout;


    String foodId, newFoodId, nameID;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        ButterKnife.bind(this);

        foodId = getIntent().getStringExtra("foodId");
        nameID = getIntent().getStringExtra("generalId");
        newFoodId = "Food/" + nameID + "/result/" + foodId;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(newFoodId);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        loadContent();


    }

    @OnClick(R.id.btn_cart)
    public void onClickCart(View view) {
        new Database(this).addOrders(new Order(
                foodId,
                food.getName(),
                elegantNumberButton.getNumber(),
                food.getPrice()
        ));

        Toast.makeText(this, "Order added to cart", Toast.LENGTH_SHORT).show();

    }


    private void loadContent() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                food = dataSnapshot.getValue(Food.class);
                Glide.with(FoodInfoActivity.this).load(food.getImage())
                        .into(foodImage);
                collapsingToolbarLayout.setTitle(food.getName());
                foodPrice.setText(food.getPrice()+" sums");
                foodName.setText(food.getName());
                description.setText(food.getDescription());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

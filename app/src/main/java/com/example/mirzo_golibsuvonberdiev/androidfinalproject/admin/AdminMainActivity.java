package com.example.mirzo_golibsuvonberdiev.androidfinalproject.admin;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.LoginActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments.MenuFragment;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.MenuAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminMainActivity extends AppCompatActivity {


    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_phone_number)
    TextView userPhone;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_food_button)
    public void addFood(){
        MenuFragment menuFragment= MenuFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.admin_main, menuFragment);
        fragmentTransaction.addToBackStack("TAG");
        fragmentTransaction.commit();


    }

    @OnClick(R.id.order_history_button)
    public void orderUpdate(View view){
        Intent intent = new Intent(this, AdminOrderUpdate.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign_out)
    public void signOut(){
        Intent intent  = new Intent(AdminMainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

}

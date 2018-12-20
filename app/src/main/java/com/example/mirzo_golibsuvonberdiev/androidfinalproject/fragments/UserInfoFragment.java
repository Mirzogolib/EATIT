package com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.LoginActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.OrderHistoryActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.OrderListActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserInfoFragment extends Fragment {

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_phone_number)
    TextView phoneNumber;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public static UserInfoFragment newInstance() {
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        return userInfoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        loadInfo();
        return view;
    }

    private void loadInfo() {
        userName.setText(Common.currentUser.getName());
        phoneNumber.setText(Common.currentUser.getPhone());
    }

    @OnClick(R.id.order_button)
    public void openOrderList() {
        Intent intent = new Intent(getActivity(), OrderListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign_out)
    public void signOut(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.order_history_button)
    public void history(){
        Intent intent = new Intent(getActivity(), OrderHistoryActivity.class);
        startActivity(intent);
    }

}

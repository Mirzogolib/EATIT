package com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.OrderListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {


    public static UserInfoFragment newInstance() {
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        return userInfoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.order_button)
    public void openOrderList(View view) {
        Intent intent = new Intent(getActivity(), OrderListActivity.class);
        startActivity(intent);

    }

}

package com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.database.Database;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Food;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Order;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.MenuAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavouriteFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<Food> favourites = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    MenuAdapter adapter;

    public static FavouriteFragment newInstance() {
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        return favouriteFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        ButterKnife.bind(this, view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadFavourites();
        return view;
    }

    private void loadFavourites() {
        favourites = new Database(getActivity()).getFavourite();
        adapter = new MenuAdapter(favourites, getActivity());
        recyclerView.setAdapter(adapter);



    }

}

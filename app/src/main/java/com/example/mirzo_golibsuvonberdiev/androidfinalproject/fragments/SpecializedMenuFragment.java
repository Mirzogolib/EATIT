package com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.FoodInfoActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.Food;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecializedMenuFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String name;


    public static SpecializedMenuFragment newInstance(String name) {
        SpecializedMenuFragment specializedMenuFragment = new SpecializedMenuFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        specializedMenuFragment.setArguments(args);
        return specializedMenuFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialized_menu, container, false);
        ButterKnife.bind(this, view);
        name = getArguments().getString("name");
        Log.d("CHECK", name);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Food/"+name+"/result");
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        loadMenu();
        return view;
    }

    private void loadMenu() {
        final FirebaseRecyclerAdapter<Food, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Food, MenuViewHolder>(Food.class, R.layout.menu_item, MenuViewHolder.class, reference) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Food model, int position) {
                viewHolder.textViewName.setText(model.getName());
                Glide.with(getActivity()).load(model.getImage())
                        .into(viewHolder.imageViewItem);
                final Food clickedItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        String key= getRef(position).getKey();
                        Log.d("CHECK", "KEY is "+key);
                        Intent intent = new Intent(getActivity(), FoodInfoActivity.class);
                        intent.putExtra("foodId", "Food/"+name+"/result/"+ key);
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }

}

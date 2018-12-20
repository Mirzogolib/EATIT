package com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities.BaseActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.GeneralMeal;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FragmentTransaction fragmentTransaction;

    public static MenuFragment newInstance() {
        MenuFragment menuFragment = new MenuFragment();
        return menuFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Food");

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        // Inflate the layout for this fragment
        loadMenu();

        Toast.makeText(getActivity(), "You are in menu fragment", Toast.LENGTH_SHORT).show();
        return view;
    }

    private void loadMenu() {
        FirebaseRecyclerAdapter<GeneralMeal, MenuViewHolder> adapter1 = new FirebaseRecyclerAdapter<GeneralMeal, MenuViewHolder>(GeneralMeal.class, R.layout.menu_item, MenuViewHolder.class, reference) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, GeneralMeal model, int position) {
                viewHolder.textViewName.setText(model.getName());
                Log.d("TAGG", model.getName());
                Glide.with(getActivity()).load(model.getImage())
                        .into(viewHolder.imageViewItem);
                final GeneralMeal clickedMeal = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    SpecializedMenuFragment specializedMenuFragment = SpecializedMenuFragment.newInstance(clickedMeal.getName());

                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, specializedMenuFragment);
                        fragmentTransaction.addToBackStack("TAG");
                        fragmentTransaction.commit();
                        Toast.makeText(getActivity(), "" + clickedMeal.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter1);
    }

}

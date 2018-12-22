package com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.common.Common;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments.FavouriteFragment;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments.MenuFragment;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.fragments.UserInfoFragment;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.interfaces.ItemClickListener;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.GeneralMeal;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.viewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.main_menu)
    ImageButton menuButton;
    @BindView(R.id.favourite)
    ImageButton favouriteButton;
    @BindView(R.id.user_info)
    ImageButton userButton;

    FragmentTransaction fragmentTransaction;
    MenuFragment menuFragment;
    FavouriteFragment favouriteFragment;
    UserInfoFragment userInfoFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initFragments();
        showFragment(menuFragment);

    }

    private void initFragments() {
        menuFragment= MenuFragment.newInstance();
        favouriteFragment = FavouriteFragment.newInstance();
        userInfoFragment = UserInfoFragment.newInstance();
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();

    }



    @OnClick({R.id.main_menu, R.id.favourite, R.id.user_info})
    public void checkFragment(View view) {
        switch (view.getId()) {
            case R.id.main_menu:
                showFragment(menuFragment);
                Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favourite:
                showFragment(favouriteFragment);
                Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_info:
                showFragment(userInfoFragment);

                Toast.makeText(this, "User Info", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}

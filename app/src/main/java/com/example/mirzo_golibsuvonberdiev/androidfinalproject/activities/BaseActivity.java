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


public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

//    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.main_menu)
    ImageButton menuButton;
    @BindView(R.id.favourite)
    ImageButton favouriteButton;
    @BindView(R.id.user_info)
    ImageButton userButton;

    TextView textViewFullName;
    NavigationView navigationView;


    FragmentTransaction fragmentTransaction;
    MenuFragment menuFragment;
    FavouriteFragment favouriteFragment;
    UserInfoFragment userInfoFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setNameUser();
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


    private void setNameUser() {
        View header = navigationView.getHeaderView(0);
        textViewFullName = header.findViewById(R.id.txt_full_name);
        textViewFullName.setText(Common.currentUser.getName());
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.base, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

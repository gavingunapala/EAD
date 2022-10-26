package com.example.ead.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ead.FuelStatus;
import com.example.ead.R;
import com.example.ead.VehicleOwnerProfile;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initiate the drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //new ActionBarDrawerToggle variable creation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //switch for the main navigation pages
        switch (item.getItemId()) {
//            case R.id.splash:
//                Intent intent = new Intent(MainActivity.this, HomeFragment.class);
//                startActivity(intent);
            case R.id.home_frag:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_home:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.fuel_status:
                Intent intent6 = new Intent(getApplicationContext(), FuelStatus.class);
                startActivity(intent6);
                break;
//            case R.id.explore_station:
//                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent3);
//                break;
//            case R.id.nav_payment:
//                Intent intent4 = new Intent(getApplicationContext(), CreditActivity.class);
//                startActivity(intent4);
//                break;
            case R.id.nav_profile:
                Intent intent5 = new Intent(getApplicationContext(), VehicleOwnerProfile.class);
                startActivity(intent5);
                break;
//            case R.id.nav_logout:
//                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent2);
//                break;
//            case R.id.nav_share:
//                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.nav_rate:
//                Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();
//                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//go back from the navigation
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
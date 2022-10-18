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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (MainActivity.this, drawer, int openDrawerContentDescRes, int closeDrawerContentDescRes);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
package com.example.ead.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.FuelStatus;
import com.example.ead.R;
import com.example.ead.VehicleOwnerProfile;
import com.example.ead.ViewMembers;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the toolbar
        //url and get data from database
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetStations";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                ENDPOINTURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("userId", "63581220bc5baef989b97d1e");//put your token here
                return headers;
            }
        };
        requestQueue.add(objectRequest);
        /////////////


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

        //Onclick method for view members
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewMembers.class);
                startActivityForResult(myIntent, 0);
            }
        });

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
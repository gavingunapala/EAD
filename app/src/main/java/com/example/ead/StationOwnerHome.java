package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StationOwnerHome extends AppCompatActivity {

    private Button buttonAddMem, buttonViewMem, buttonFuelStatus, buttonFuelTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_home);

        buttonAddMem = (Button) findViewById(R.id.buttonFuelTime);
        buttonViewMem = (Button) findViewById(R.id.buttonFuelTime);
        buttonFuelStatus = (Button) findViewById(R.id.buttonFuelTime);
        buttonFuelTime = (Button) findViewById(R.id.buttonFuelTime);

        buttonAddMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddStationWorker.class);
                startActivityForResult(myIntent, 0);
            }
        });

        buttonViewMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewMembers.class);
                startActivityForResult(myIntent, 0);
            }
        });

        buttonFuelStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FuelStatus.class);
                startActivityForResult(myIntent, 0);
            }
        });

        buttonFuelTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FuelArrivalTime.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
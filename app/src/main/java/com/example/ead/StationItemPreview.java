package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ead.Models.FuelStationModel;

public class StationItemPreview extends AppCompatActivity {
    TextView textView;
    FuelStationModel sModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_item_preview);

        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            sModel = (FuelStationModel) intent.getSerializableExtra("items");
            textView.setText(sModel.getStationName());
        }
    }
}
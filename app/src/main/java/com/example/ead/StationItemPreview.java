package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ead.Models.FuelStationModel;

public class StationItemPreview extends AppCompatActivity {
    TextView textView;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_item_preview);

        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            id = (String) intent.getSerializableExtra("items");
            Log.e("ViewStation","msg : "+id);
            textView.setText(id);
        }
    }
}
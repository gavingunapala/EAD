package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ead.Models.FuelStationModel;

public class StationItemPreview extends AppCompatActivity {
    TextView textView;
    String id;
    //Initialize variables
    private Button Owner_Profile_button;

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

        //Onclick method
        Owner_Profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewStation.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
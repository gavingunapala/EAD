package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead.common.MainActivity;

public class FuelStatus extends AppCompatActivity {

    private Button buttonSubmitStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_status);

        //Assign variables
        buttonSubmitStatus = (Button) findViewById(R.id.buttonReg);

        //onclick for the submit fuel status
        buttonSubmitStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create the navigation for Main Activity
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
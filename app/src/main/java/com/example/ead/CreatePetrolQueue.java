package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CreatePetrolQueue extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Initialize variables
    private Button buttonReg;
    //spinner - vehicle type & fuel type, user selection
    Spinner PQSpinner;
    public static String DieselQType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_petrol_queue);

        //Assign variables
        buttonReg = (Button) findViewById(R.id.buttonReg);

        // type Selection
        // spinner element
        PQSpinner = (Spinner) findViewById(R.id.PQSpinner);

        // Spinner click listener
        PQSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements - User types
        List<String> DieselQ = new ArrayList<String>();
        DieselQ.add("New");
        DieselQ.add("Pending");
        DieselQ.add("In-progress");
        DieselQ.add("End");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForDieselQ = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, DieselQ);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForDieselQ.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        PQSpinner.setAdapter(dataAdapterTypeForDieselQ);

        //Onclick method for add members
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StationOwnerHome.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        DieselQType = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
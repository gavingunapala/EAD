package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button buttonRegister;
    EditText name , NIC , mobileNumber , vehicleNumber , VehicleType , password , FuelType;
    //spinner - vehicle type & fuel type
    Spinner vSpinner, fuelTypeSpinner;
    public static String vehicleType, fuelType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_owner_register);

        buttonRegister = (Button) findViewById(R.id.btnSignup);

        //Edit Texts
        name=findViewById( R.id.userName);
        NIC=findViewById( R.id.editTextNICNumber);
        mobileNumber=findViewById( R.id.editTextMobile);
        vehicleNumber=findViewById( R.id.vehicleNumber);
//        VehicleType=findViewById( R.id.editTextVehicleNumber);
        password=findViewById( R.id.password);
//        FuelType=findViewById( R.id.);

        //vehicle type & fuel type Selection
        // spinner element
        vSpinner = (Spinner) findViewById(R.id.usersSpinner);
        fuelTypeSpinner = (Spinner) findViewById(R.id.fuelType);
        // Spinner click listener
        vSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        fuelTypeSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements - vehicle type
        List<String> vehicle = new ArrayList<String>();
        vehicle.add("Car/Van/Jeep");
        vehicle.add("Bike/Three Wheel");
        vehicle.add("Bus/Lorry");

        // Spinner Drop down elements - fuel type
        List<String> fuel = new ArrayList<String>();
        fuel.add("Petrol");
        fuel.add("Diesel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForVehicle = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, vehicle);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForVehicle.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        vSpinner.setAdapter(dataAdapterTypeForVehicle);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForFuel = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, fuel);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForFuel.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        fuelTypeSpinner.setAdapter(dataAdapterTypeForFuel);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String nic = NIC.getText().toString();
                String mobilenumber = mobileNumber.getText().toString();
                String vehiclenumber = vehicleNumber.getText().toString();
                String pass = password.getText().toString();

                Toast.makeText(VehicleOwnerRegister.this, "Registration Success "+username + nic + mobilenumber +
                        vehiclenumber + pass +"", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        vehicleType = parent.getItemAtPosition(position).toString();
        fuelType = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
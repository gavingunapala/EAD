package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VehicleOwnerRegister extends AppCompatActivity {

    private Button buttonRegister;
    EditText name , NIC , mobileNumber , vehicleNumber , VehicleType , password , FuelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_owner_register);

        buttonRegister = (Button) findViewById(R.id.buttonUpdate);

        //Edit Texts
        name=findViewById( R.id.editTextTextOwnerUsername);
        NIC=findViewById( R.id.editTextTextOwnerNIC);
        mobileNumber=findViewById( R.id.editTextOwnerPhone);
        vehicleNumber=findViewById( R.id.editTextStationREGNo);
//        VehicleType=findViewById( R.id.editTextVehicleNumber);
        password=findViewById( R.id.editTextTextPassword2);
//        FuelType=findViewById( R.id.);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String nic = NIC.getText().toString();
                String mobilenumber = mobileNumber.getText().toString();
                String vehiclenumber = vehicleNumber.getText().toString();
                String pass = password.getText().toString();

                Toast.makeText(VehicleOwnerRegister.this, "Registration sucess "+username + nic + mobilenumber +
                        vehiclenumber + pass +"", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
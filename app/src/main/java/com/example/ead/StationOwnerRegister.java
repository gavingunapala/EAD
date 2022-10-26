package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class StationOwnerRegister extends AppCompatActivity {

    //Initialize variables
    private Button ButtonReg;
    EditText editTextTextOwnerUsername , editTextTextOwnerNIC , editTextOwnerPhone , editTextStationREGNo , editTextTextLocation,editTextTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Assign variables
        ButtonReg = (Button) findViewById(R.id.buttonReg);
        //editTexts
        editTextTextOwnerUsername=findViewById( R.id.editTextTextProvince);
        editTextTextOwnerNIC=findViewById( R.id.editTextTextlocation);
        editTextOwnerPhone=findViewById( R.id.remaingPetrol);
        editTextStationREGNo=findViewById( R.id.editTextStationName);
        editTextTextLocation=findViewById( R.id.editTextTextLocation);
        editTextTextPassword2=findViewById( R.id.remaingDiesel);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_register);
    }
}
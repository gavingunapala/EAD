package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class StationOwnerRegister extends AppCompatActivity {

    private Button ButtonReg;
    EditText editTextTextOwnerUsername , editTextTextOwnerNIC , editTextOwnerPhone , editTextStationREGNo , editTextTextLocation,editTextTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ButtonReg = (Button) findViewById(R.id.buttonReg);
        //editTexts
        editTextTextOwnerUsername=findViewById( R.id.editTextTextOwnerUsername);
        editTextTextOwnerNIC=findViewById( R.id.editTextTextOwnerNIC);
        editTextOwnerPhone=findViewById( R.id.editTextOwnerPhone);
        editTextStationREGNo=findViewById( R.id.editTextStationREGNo);
        editTextTextLocation=findViewById( R.id.editTextTextLocation);
        editTextTextPassword2=findViewById( R.id.editTextTextPassword2);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_register);
    }
}
package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StationOwnerProfile extends AppCompatActivity {
    EditText editTextTextOwnerUsername , editTextTextOwnerNIC , editTextOwnerPhone , editTextStationREGNo , editTextTextLocation , editTextTextPassword2;
    Button buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_profile);
        //Buttons
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        //Edit Texts
        editTextTextOwnerUsername=findViewById( R.id.editTextTextOwnerUsername);
        editTextTextOwnerNIC=findViewById( R.id.editTextTextOwnerNIC);
        editTextOwnerPhone=findViewById( R.id.editTextOwnerPhone);
        editTextStationREGNo=findViewById( R.id.editTextStationREGNo);
        editTextTextLocation=findViewById( R.id.editTextTextLocation);
        editTextTextPassword2=findViewById( R.id.editTextTextPassword2);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OwnerUsername = editTextTextOwnerUsername.getText().toString();
                String OwnerNIC = editTextTextOwnerNIC.getText().toString();
                String OwnerPhone = editTextOwnerPhone.getText().toString();
                String StationREGNo = editTextStationREGNo.getText().toString();
                String Location = editTextTextLocation.getText().toString();
                String Password = editTextTextPassword2.getText().toString();

                Toast.makeText(StationOwnerProfile.this, "Update success ", Toast.LENGTH_SHORT).show();

//                Intent myIntent = new Intent(view.getContext(), Login.class);
//                startActivityForResult(myIntent, 0);
            }
        });
    }
}
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

public class UserSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //spinner -user selection
    Spinner usersSpinner;
    public static String userType;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        btnGo = findViewById(R.id.btnGo);

        //User Selection
        // spinner element
        usersSpinner = (Spinner) findViewById(R.id.userSpinner);
        // Spinner click listener
        usersSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        // Spinner Drop down elements
        List<String> users = new ArrayList<String>();
        users.add("Vehicle Owner");
        users.add("Station Owner");
        users.add("Station Worker");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, users);
        // Drop down layout style - list view with radio button
        dataAdapterType.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        usersSpinner.setAdapter(dataAdapterType);

        //go to the create user profile activity
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( userType.equals("Vehicle Owner") ) {
                    Intent intent = new Intent(UserSelection.this, Login.class);
                    startActivity(intent);
                }
                if ( userType.equals("Station Owner") ) {
                    Intent intent = new Intent(UserSelection.this, Login.class);
                    startActivity(intent);
                }
                if ( userType.equals("Station Owner") ) {
                    Intent intent = new Intent(UserSelection.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        userType = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
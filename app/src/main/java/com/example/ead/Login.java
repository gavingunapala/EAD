package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ead.common.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button btnLogin, btnSignup;
    EditText userName , password;
    //spinner -user selection
    Spinner usersSpinner;
    public static String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.buttonUpdate);

        userName=findViewById( R.id.userName);
        password = findViewById(R.id.editTextTextPassword2);

        //User Selection
        // spinner element
        usersSpinner = (Spinner) findViewById(R.id.usersSpinner);
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( userType.equals("Vehicle Owner") ) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                if ( userType.equals("Station Owner") ) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                if ( userType.equals("Station Owner") ) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( userType.equals("Vehicle Owner") ) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                if ( userType.equals("Station Owner") ) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                if ( userType.equals("Station Owner") ) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String username = userName.getText().toString();
//                String pass = password.getText().toString();
//
//                Intent myIntent = new Intent(view.getContext(), VehicleOwnerProfile.class);
//                startActivityForResult(myIntent, 0);
//            }
//        });
//
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), VehicleOwnerRegister.class);
//                startActivityForResult(myIntent, 0);
//            }
//        });

        //remove this
//        remove_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
//                startActivityForResult(myIntent, 0);
//            }
//        });
//        remove_station_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), StationOwnerHome.class);
//                startActivityForResult(myIntent, 0);
//            }
//        });
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
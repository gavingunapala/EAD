package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ead.common.MainActivity;

public class Login extends AppCompatActivity {
    private Button btnLogin, btnSignup;
    EditText userName , password;
    // remove this items
//    private Button remove_home, remove_station_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.buttonReg);

        userName=findViewById( R.id.userName);
        password = findViewById(R.id.editTextTextPassword2);
        //remove this
//        remove_home = (Button) findViewById(R.id.remove_home);
//        remove_station_home = (Button) findViewById(R.id.remove_station_home);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = userName.getText().toString();
                String pass = password.getText().toString();

                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), VehicleOwnerRegister.class);
                startActivityForResult(myIntent, 0);
            }
        });

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
}
package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.common.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button btnLogin, btnSignup;
    EditText userName , password;
    //spinner -user selection
    Spinner usersSpinner;
    public static String userType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.buttonReg);

        userName=findViewById( R.id.userName);
        password = findViewById(R.id.editTextTextPassword2);

        //url
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/Login";
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        //go to the create user profile activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = userName.getText().toString();
                String pwd = password.getText().toString();


                    JSONObject object = new JSONObject();
                    try {
                        object.put("userName",uname);
                        object.put("password",pwd);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("Vehicle user Loged in",object.toString());

                    Toast.makeText(Login.this, "Registration Success "+ object +"", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest objectRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            ENDPOINTURL,
                            object,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("Rest Response", response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("Rest Response", error.toString());
                                }
                            }
                    );
                    requestQueue.add(objectRequest);

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), VehicleOwnerRegister.class);
                startActivityForResult(myIntent, 0);
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
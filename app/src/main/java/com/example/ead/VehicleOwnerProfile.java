package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //Initialize variables
    private Button buttonUpdateProfile;
    EditText name , NIC , mobileNumber , vehicleNumber , VehicleType , password , FuelType;
    //spinner - vehicle type & fuel type
    Spinner vehicleSpinner, fuelSpinner, userSpinner;
    public static String vehicleType, fuelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_owner_profile);

        //Assign variables
        buttonUpdateProfile = (Button) findViewById(R.id.buttonReg);

        //Edit Texts
        name=findViewById( R.id.userName);
        mobileNumber=findViewById( R.id.editTextOwnerPhone);
        vehicleNumber=findViewById( R.id.vehicleNumber);
//        VehicleType=findViewById( R.id.editTextVehicleNumber);
        password=findViewById( R.id.editTextTextPassword2);
//        FuelType=findViewById( R.id.);

        //vehicle type & fuel type Selection
        // spinner element
        vehicleSpinner = (Spinner) findViewById(R.id.vehicleSpinner);
        fuelSpinner = (Spinner) findViewById(R.id.fuelType);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);

        // Spinner click listener
        vehicleSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        fuelSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        userSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        //url
        String userid = "cce50903e943fb5273acc05a";
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetVehicleOwnerById/"+userid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                ENDPOINTURL,
                null,
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
        /////////////

        //set all textviews to default values
        name.setText((CharSequence)"nam");

        // Spinner Drop down elements - User types
        List<String> users = new ArrayList<String>();
        users.add("Vehicle Owner");
        users.add("Station Owner");
        users.add("Station Worker");

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
        ArrayAdapter<String> dataAdapterTypeForUsers = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, fuel);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForUsers.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        userSpinner.setAdapter(dataAdapterTypeForUsers);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForVehicle = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, vehicle);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForVehicle.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        vehicleSpinner.setAdapter(dataAdapterTypeForVehicle);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForFuel = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, fuel);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForFuel.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        fuelSpinner.setAdapter(dataAdapterTypeForFuel);

        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String nic = NIC.getText().toString();
                String mobilenumber = mobileNumber.getText().toString();
                String vehiclenumber = vehicleNumber.getText().toString();
                String pass = password.getText().toString();

                Toast.makeText(VehicleOwnerProfile.this, "Profile Successfully Updated!"+username + nic + mobilenumber +
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
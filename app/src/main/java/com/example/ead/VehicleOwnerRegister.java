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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button buttonRegister;
    EditText name , FuelAmount , mobileNumber , vehicleNumber , password;
    //spinner - vehicle type & fuel type, user selection
    Spinner vSpinner, fuelTypeSpinner, userSpinner;
    public static String vehicleType, fuelType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_owner_register);

        buttonRegister = (Button) findViewById(R.id.buttonReg);

        //Edit Texts
        name=findViewById( R.id.userName);
        FuelAmount=findViewById( R.id.editTextFuelAmount);
        mobileNumber=findViewById( R.id.editTextOwnerPhone);
        vehicleNumber=findViewById( R.id.vehicleNumber);
        password=findViewById( R.id.editTextTextPassword2);

        //vehicle type & fuel type Selection
        // spinner element
        vSpinner = (Spinner) findViewById(R.id.vehicleSpinner);
        fuelTypeSpinner = (Spinner) findViewById(R.id.fuelType);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);

        // Spinner click listener
        vSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        fuelTypeSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        userSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

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
        ArrayAdapter<String> dataAdapterTypeForUsers = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, users);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForUsers.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        userSpinner.setAdapter(dataAdapterTypeForUsers);

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

        //url
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/Register";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String fuelamount = FuelAmount.getText().toString();
                String mobilenumber = mobileNumber.getText().toString();
                String vehiclenumber = vehicleNumber.getText().toString();
                String pass = password.getText().toString();
                String userspinner = userSpinner.getSelectedItem().toString();
                String vspinner = vSpinner.getSelectedItem().toString();
                String fueltypespinner = fuelTypeSpinner.getSelectedItem().toString();



                JSONObject object = new JSONObject();
                try {
                    object.put("userRole",userspinner);
                    object.put("userName",username);
                    object.put("mobileNumber",mobilenumber);
                    object.put("vehicleNumber",vehiclenumber);
                    object.put("vehicleType",vspinner);
                    object.put("fuelType",fueltypespinner);
                    object.put("fuelAmount",fuelamount);
                    object.put("password",pass);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("User Register",object.toString());

                Toast.makeText(VehicleOwnerRegister.this, "Registration Success "+ object +"", Toast.LENGTH_SHORT).show();
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
                /////////////
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
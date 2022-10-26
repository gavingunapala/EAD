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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleOwnerProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //Initialize variables
    private Button buttonUpdateProfile;
    EditText name , FuelAmount , mobileNumber , vehicleNumber , password;
    //spinner - vehicle type & fuel type
    Spinner vehicleSpinner, fuelSpinner, userSpinner;
    public static String vehicleType, fuelType;
    String id  = "63581220bc5baef989b97d1e";
    //data add into the text-https://gist.github.com/codinginflow/aae66a1da7d3d243e03772633770b65e
    String uRole,uName,mn,vn,vt,ft,fa;

    Gson json=new Gson();
    JSONObject obj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_owner_profile);

        //Assign variables
        buttonUpdateProfile = (Button) findViewById(R.id.buttonReg);

        //Edit Texts
        name=findViewById( R.id.userName);
        FuelAmount=findViewById( R.id.editTextFuelAmount);
        mobileNumber=findViewById( R.id.remaingPetrol);
        vehicleNumber=findViewById( R.id.vehicleNumber);
        password=findViewById( R.id.remaingDiesel);

        //vehicle type & fuel type Selection
        // spinner element
        vehicleSpinner = (Spinner) findViewById(R.id.vehicleSpinner);
        fuelSpinner = (Spinner) findViewById(R.id.fuelType);
        userSpinner = (Spinner) findViewById(R.id.userSpinner);

        // Spinner click listener
        vehicleSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        fuelSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        userSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


//        JSONObject object = new JSONObject();
//        try {
//            object.put("userId","63581220bc5baef989b97d1e");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.e("User Id",object.toString());

        //url
//        String userid = "63581220bc5baef989b97d1e";
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetAuthUser";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ENDPOINTURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            uRole= response.getString("userRole");
                            uName= response.getString("userName");
                            mn= response.getString("mobileNumber");
                            vn= response.getString("vehicleNumber");
                            vt= response.getString("vehicleType");
                            ft= response.getString("fuelType");
                            fa= response.getString("fuelAmount");

                                Log.e("user name", uName + uName + mn );

                            //set all textviews to default values
//        name.setText((CharSequence)uRole);
                            name.setText((CharSequence)uName);
                            mobileNumber.setText((CharSequence)mn);
                            vehicleNumber.setText((CharSequence)vn);
//        name.setText((CharSequence)vt);
//        name.setText((CharSequence)ft);
                            FuelAmount.setText((CharSequence)fa);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("userId", id);//put your token here
                return headers;
            }
        };
        requestQueue.add(request);
        /////////////



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
                String fuelamount = FuelAmount.getText().toString();
                String mobilenumber = mobileNumber.getText().toString();
                String vehiclenumber = vehicleNumber.getText().toString();
                String pass = password.getText().toString();
                String userspinner = userSpinner.getSelectedItem().toString();
                String vspinner = vehicleSpinner.getSelectedItem().toString();
                String fueltypespinner = fuelSpinner.getSelectedItem().toString();

//                Toast.makeText(VehicleOwnerProfile.this, "Profile Successfully Updated!"+username + mobilenumber +
//                        vehiclenumber + pass +"", Toast.LENGTH_SHORT).show();

                Log.e("update data", username + fuelamount + mobilenumber );
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
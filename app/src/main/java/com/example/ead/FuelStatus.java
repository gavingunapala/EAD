package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuelStatus extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Initialize variables
    private Button pUpdate, dUpdate;
    //spinner - fuel types
    Spinner DieselSpinner, PetrolSpinner;
    public static String petrolType, dieselType;
    // //Assign the id
    String Stationid = "6358cd88ee232c8194e2f5c6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_status);

        //Assign variables
        pUpdate= (Button) findViewById(R.id.button2);
        dUpdate= (Button) findViewById(R.id.button3);

        // spinner element
        DieselSpinner = (Spinner) findViewById(R.id.PetrolSpinner);
        PetrolSpinner = (Spinner) findViewById(R.id.DieselSpinner);

        // Spinner click listener
        DieselSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        PetrolSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements - Petrol types
        List<String> petrol = new ArrayList<String>();
        petrol.add("Available");
        petrol.add("Not Available");

        // Spinner Drop down elements - Diesel type
        List<String> diesel = new ArrayList<String>();
        diesel.add("Available");
        diesel.add("Not Available");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForPetrol = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, petrol);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForPetrol.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        PetrolSpinner.setAdapter(dataAdapterTypeForPetrol);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForDiesel = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, diesel);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForDiesel.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        DieselSpinner.setAdapter(dataAdapterTypeForDiesel);

        final String[] ptypespinner = {null};
        //url
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/UpdatePetrolQStatus/"+ ptypespinner[0];
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        pUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ptypespinner[0] = PetrolSpinner.getSelectedItem().toString();

//                String ptypespinner = PetrolSpinner.getSelectedItem().toString();
//
                JSONObject object = new JSONObject();
                try {
                    object.put("QueueStatus",ptypespinner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("User Register",object.toString());

                Toast.makeText(FuelStatus.this, "Registration Success "+ object +"", Toast.LENGTH_SHORT).show();
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.PATCH,
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
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("stationId", Stationid);//put your token here
                        return headers;
                    }
                };
                requestQueue.add(objectRequest);
                /////////////

                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);

            }
        });
        dUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        petrolType = parent.getItemAtPosition(position).toString();
        dieselType = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
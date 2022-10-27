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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateDieselQueue extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //Initialize variables
    private Button buttonReg;
    //spinner - vehicle type & fuel type, user selection
    Spinner PQSpinner;
    public static String PetrolQType;
    String stationid = "6358d446ad4ca779e1eaabe5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diesel_queue);

        //Assign variables
        buttonReg = (Button) findViewById(R.id.buttonReg);

        // type Selection
        // spinner element
        PQSpinner = (Spinner) findViewById(R.id.PQSpinner);

        // Spinner click listener
        PQSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements - User types
        List<String> PetrolQ = new ArrayList<String>();
        PetrolQ.add("New");
        PetrolQ.add("Pending");
        PetrolQ.add("In-progress");
        PetrolQ.add("End");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTypeForPetrolQ = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, PetrolQ);
        // Drop down layout style - list view with radio button
        dataAdapterTypeForPetrolQ.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        PQSpinner.setAdapter(dataAdapterTypeForPetrolQ);

        //url
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/AddDieselQ";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Onclick method for add members
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dqspinner = PQSpinner.getSelectedItem().toString();
                Log.e("Rest pqspinner", dqspinner);
                JSONObject object = new JSONObject();
                try {
                    object.put("queueStatus",dqspinner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.PATCH,
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
                                Log.e("Rest err", error.toString());
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("stationId", stationid);//put your token here
                        return headers;
                    }
                };
                requestQueue.add(objectRequest);

                Intent myIntent = new Intent(view.getContext(), StationOwnerHome.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        PetrolQType = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
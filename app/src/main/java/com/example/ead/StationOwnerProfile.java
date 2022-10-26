package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StationOwnerProfile extends AppCompatActivity {
    //Initialize variables
    EditText editTextTextOwnerUsername , editTextTextOwnerNIC , editTextOwnerPhone , editTextStationREGNo , editTextTextLocation , editTextTextPassword2;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_profile);

        //Assign variables
        //Buttons
        buttonUpdate = (Button) findViewById(R.id.buttonReg);
        //Edit Texts
        editTextTextOwnerUsername=findViewById( R.id.editTextTextProvince);
        editTextTextOwnerNIC=findViewById( R.id.editTextTextlocation);
        editTextOwnerPhone=findViewById( R.id.remaingPetrol);
        editTextStationREGNo=findViewById( R.id.editTextStationName);
        editTextTextLocation=findViewById( R.id.editTextTextLocation);
        editTextTextPassword2=findViewById( R.id.remaingDiesel);

//        //url
//        String userid = "35112512A5C78DCB837C7B53";
//
//        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/api/FuelPass/GetStationOwners/";
//        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //onclick for update profile details
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String OwnerUsername = editTextTextOwnerUsername.getText().toString();
//                String OwnerNIC = editTextTextOwnerNIC.getText().toString();
//                String OwnerPhone = editTextOwnerPhone.getText().toString();
//                String StationREGNo = editTextStationREGNo.getText().toString();
//                String Location = editTextTextLocation.getText().toString();
//                String Password = editTextTextPassword2.getText().toString();

//                JSONObject stationOwnerUpdate = new JSONObject();

//                Toast.makeText(StationOwnerProfile.this, "Update Success "+ stationOwnerUpdate +"", Toast.LENGTH_SHORT).show();
//                JsonObjectRequest objectRequest = new JsonObjectRequest(
//                        Request.Method.GET,
//                        ENDPOINTURL,
//                        null,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                Log.e("Rest Response", response.toString());
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("Rest Response", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest);


//                Intent myIntent = new Intent(view.getContext(), Login.class);
//                startActivityForResult(myIntent, 0);
            }
        });
    }
}
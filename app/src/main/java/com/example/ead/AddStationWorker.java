package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddStationWorker extends AppCompatActivity {

    private Button buttonRegWorker;
    EditText editTextTextWorkerId , editTextTextWorkerName , editTextNICNumber , editTextPhoneNumber , editTextTextWorkerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station_worker);

        buttonRegWorker = (Button) findViewById(R.id.buttonReg);
        //editTexts
        editTextTextWorkerId=findViewById( R.id.editTextStationName);
        editTextTextWorkerName=findViewById( R.id.editTextTextProvince);
        editTextNICNumber=findViewById( R.id.editTextTextlocation);
        editTextPhoneNumber=findViewById( R.id.remaingPetrol);
        editTextTextWorkerPassword=findViewById( R.id.remaingDiesel);


        //url
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/CreateStationWorkers";
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        buttonRegWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String WorkerId = editTextTextWorkerId.getText().toString();
                String WorkerName = editTextTextWorkerName.getText().toString();
                String NICNumber = editTextNICNumber.getText().toString();
                String PhoneNumber = editTextPhoneNumber.getText().toString();
                String WorkerPassword = editTextTextWorkerPassword.getText().toString();


                JSONObject stationWorker = new JSONObject();
                try {
                    stationWorker.put("id","21E0CB9935A9F200A7ED42CA");
                    stationWorker.put("workerName",WorkerName);
                    stationWorker.put("nicNumber",NICNumber);
                    stationWorker.put("mobileNumber",PhoneNumber);
                    stationWorker.put("password",WorkerPassword);
                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(AddStationWorker.this, "Registration Success "+ stationWorker +"", Toast.LENGTH_SHORT).show();
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        ENDPOINTURL,
                        stationWorker,
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

                Intent myIntent = new Intent(view.getContext(), StationOwnerHome.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
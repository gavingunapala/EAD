package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddStation extends AppCompatActivity {
    //Initialize variables
    Button buttonRegister;
    EditText name , province , location , remaingpetrol , remaingdiesel;
    String id = "63581220bc5baef989b97d1e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);

        //Assign variables
        buttonRegister = (Button) findViewById(R.id.buttonReg);

        //Edit Texts
        name=findViewById( R.id.editTextStationName);
        province=findViewById( R.id.editTextTextProvince);
        location=findViewById( R.id.editTextTextlocation);
        remaingpetrol=findViewById( R.id.remaingPetrol);
        remaingdiesel=findViewById( R.id.remaingDiesel);

        //url
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/CreateFuelStation";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String pro = province.getText().toString();
                String loc = location.getText().toString();
                String rmp = remaingpetrol.getText().toString();
                String rmd = remaingdiesel.getText().toString();

                JSONObject object = new JSONObject();
                try {
                    object.put("stationName",username);
                    object.put("province",pro);
                    object.put("location",loc);
                    object.put("remaingPetrol",rmp);
                    object.put("remaingDiesel",rmd);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("User Register",object.toString());

                Toast.makeText(AddStation.this, "Registration Success "+ object +"", Toast.LENGTH_SHORT).show();
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
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("userId", id);//put your token here
                        return headers;
                    }
                };
                requestQueue.add(objectRequest);
                /////////////
                Intent myIntent = new Intent(view.getContext(), StationOwnerHome.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class UpdateFuelStation extends AppCompatActivity {
    private Button buttonReg;
    EditText editTextStationname , editTextTextprovince , editTexttextlocation , remaingpetrol , remaingdiesel;
    String sn,pro,loc,rp,rd;
    String id  = "6358cd88ee232c8194e2f5c6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fuel_station);

        buttonReg = (Button) findViewById(R.id.buttonReg);

        //Edit Texts
        editTextStationname=findViewById( R.id.editTextStationName);
        editTextTextprovince=findViewById( R.id.editTextTextProvince);
        editTexttextlocation=findViewById( R.id.editTextTextlocation);
        remaingpetrol=findViewById( R.id.remaingPetrol);
        remaingdiesel=findViewById( R.id.remaingDiesel);

        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetStationsById";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ENDPOINTURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            sn= response.getString("stationName");
                            pro= response.getString("province");
                            loc= response.getString("location");
                            rp= response.getString("remaingPetrol");
                            rd= response.getString("remaingDiesel");

                            //set all textviews to default values
                            editTextStationname.setText((CharSequence)sn);
                            editTextTextprovince.setText((CharSequence)pro);
                            editTexttextlocation.setText((CharSequence)loc);
                            remaingpetrol.setText((CharSequence)rp);
                            remaingdiesel.setText((CharSequence)rd);
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
                headers.put("Id", id);//put your token here
                return headers;
            }
        };
        requestQueue.add(request);
        /////////////

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StationOwnerHome.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.Models.FuelStationModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StationItemPreview extends AppCompatActivity {
    TextView textView;
    String id;
//    String stationid  = "6358cd88ee232c8194e2f5c6";
    String sn,pro,loc,rp,rd;
    TextView editTextStationname , editTextTextprovince ;
    //Initialize variables
    private Button Owner_Profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_item_preview);

        editTextStationname=findViewById( R.id.editTextStationName);
        editTextTextprovince=findViewById( R.id.editTextTextLocation);
        Owner_Profile_button=findViewById( R.id.Owner_Profile_button);

        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            id = (String) intent.getSerializableExtra("items");
            Log.e("ViewStation","msg : "+id);


            String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetStationsById";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ENDPOINTURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                sn= response.getString("stationName");
                                pro= response.getString("province");

                                //set all textviews to default values
                                Log.e("ViewStation","msg : "+sn);
                                editTextStationname.setText((CharSequence)sn);
                                editTextTextprovince.setText((CharSequence)pro);
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

        }

        //Onclick method
        Owner_Profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewStation.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
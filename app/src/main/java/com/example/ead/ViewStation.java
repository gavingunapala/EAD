package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ViewStation extends AppCompatActivity {

    String userid = "63581220bc5baef989b97d1e";
    TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_station);
        mTextViewResult = findViewById(R.id.text_view_result);


        //url and get data from database
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetStations";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest objectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                ENDPOINTURL,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
////                        //            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                final Map<String, String> headers = new HashMap<>();
////                headers.put("userId", userid);//put your token here
////                return headers;
////            }
//
//                        JSONObject obj = new JSONObject();
//                        try {
//                            obj.put("employees",response);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Log.e("obj", obj.toString());
//                        try {
//                            JSONArray jsonArray = obj.getJSONArray("employees");
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject employee = jsonArray.getJSONObject(i);
//
//                                String firstName = employee.getString("stationName");
////                                int age = employee.getInt("age");
////                                String mail = employee.getString("mail");
//
////                                mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
//                                Log.e("stationName", firstName);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Rest err", error.toString());
//                    }
//                }
//        ){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                final Map<String, String> headers = new HashMap<>();
//                headers.put("userId", userid);//put your token here
//                return headers;
//            }
//        };
//        requestQueue.add(objectRequest);
//        /////////////
        requestQueue.add(
                new JsonRequest<JSONArray>(Request.Method.GET, ENDPOINTURL, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
//                                Log.e("obj", response.toString());

                                JSONObject obj = new JSONObject();
                                    try {
                                        obj.put("employees",response);
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
                                    Log.e("obj", obj.toString());
//                                try {
                                    JSONArray jsonArray = obj.getJSONArray("employees");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject employee = jsonArray.getJSONObject(i);

                                        String stationName = employee.getString("stationName");
                                        String slocation = employee.getString("location");

                                        Log.e("test", stationName);
                                        mTextViewResult.append("Station Name : " + stationName +" \n\nStation Location : "+ slocation +"\n\n");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }) {
                    @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("userId", userid);//put your token here
                        return headers;
                    }

                    @Override
                    protected Response<JSONArray> parseNetworkResponse(
                            NetworkResponse response) {
                        try {
                            String jsonString = new String(response.data,
                                    HttpHeaderParser
                                            .parseCharset(response.headers));
                            return Response.success(new JSONArray(jsonString),
                                    HttpHeaderParser
                                            .parseCacheHeaders(response));
                        } catch (UnsupportedEncodingException e) {
                            return Response.error(new ParseError(e));
                        } catch (JSONException je) {
                            return Response.error(new ParseError(je));
                        }
                    }
                });

    }
}
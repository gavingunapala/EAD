package com.example.ead;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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
import com.example.ead.Models.FuelStationModel;
import com.example.ead.common.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewStation extends AppCompatActivity {

    String userid = "63581220bc5baef989b97d1e";
    //imports

    List<FuelStationModel> stationsModelList = new ArrayList<>();
    ListView listView;
    CustomAdapter customAdapter;
    Button leave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_station);

        leave = (Button) findViewById(R.id.button);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewStation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //url and get data from database
        String ENDPOINTURL = "http://192.168.43.90:8088/api/FuelPass/GetStations";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(
                new JsonRequest<JSONArray>(Request.Method.GET, ENDPOINTURL, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                JSONObject obj = new JSONObject();
                                try {
                                    obj.put("employees",response);
                                    Log.e("response not sure", response.getJSONObject(0).toString());
                                    JSONArray jsonArray = obj.getJSONArray("employees");

                                    //loop
                                    listView = findViewById(R.id.prListView);

                                    for(int i = 0;i < response.length();i++){
                                        String stationName = response.getJSONObject(i).getString("stationName");
                                        String stationlocation = response.getJSONObject(i).getString("location");
                                        String sid = response.getJSONObject(i).getString("id");
                                        FuelStationModel itemsModel = new FuelStationModel(stationName,stationlocation ,sid);
                                        stationsModelList.add(itemsModel);
                                    }
                                    customAdapter = new CustomAdapter(stationsModelList,ViewStation.this);
                                    listView.setAdapter(customAdapter);
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


//    //rest
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu,menu);
//        MenuItem menuItem = menu.findItem(R.id.searchView);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.e("Main"," data search"+newText);
//                customAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.searchView){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //Adapter
    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<FuelStationModel> fuelModel;
        private List<FuelStationModel> fuelModelListFiltered;
        private Context context;

        public CustomAdapter(List<FuelStationModel> fuelModel, Context context) {
            this.fuelModel = fuelModel;
            this.fuelModelListFiltered = fuelModel;
            this.context = context;
        }

        @Override
        public int getCount() {
            return fuelModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return fuelModelListFiltered.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.activity_view_station_row,null);


            TextView snames = view.findViewById(R.id.stationName);
            TextView sLocation = view.findViewById(R.id.stationLocation);
            Button btn = view.findViewById(R.id.btn);

            snames.setText(fuelModelListFiltered.get(position).getStationName());
            sLocation.setText(fuelModelListFiltered.get(position).getLocation());
            String id = fuelModelListFiltered.get(position).getId();
            Log.e("id",id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ViewStation","item clicked");
                    startActivity(new Intent(ViewStation.this,StationItemPreview.class).putExtra("items",id));

                }
            });

            return view;
        }



        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    int resultcount = 0;
                    FilterResults filterResults = new FilterResults();
                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = fuelModel.size();
                        filterResults.values = fuelModel;

                    }else{
                        List<FuelStationModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(FuelStationModel itemsModel:fuelModel){
                            if(itemsModel.getStationName().contains(searchStr) || itemsModel.getLocation().contains(searchStr)){
                                resultsModel.add(itemsModel);
                                filterResults.count = resultsModel.size();
                                filterResults.values = resultsModel;
                                resultcount++;
                            }
                        }
                        if(resultcount == 0){
                            filterResults.count = fuelModel.size();
                            filterResults.values = fuelModel;
                        }
                    }
                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    fuelModelListFiltered = (List<FuelStationModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }
}
package com.example.ead;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.*;
import com.example.ead.Models.FuelStation;

import java.util.List;

public class FuelStationAdapter extends ArrayAdapter<FuelStation> {

    private Activity context;
    List<FuelStation> fuelDetails;
//    DatabaseReference Reference;
    Button favDelete, addToCart;
    String user;

    public FuelStationAdapter(Activity context, List<FuelStation> fuelDetails) {
        super(context, R.layout.activity_view_station_row, fuelDetails);
        this.context = context;
        this.fuelDetails = fuelDetails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_view_station_row, null, true);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageV);
        TextView stationName = (TextView) listViewItem.findViewById(R.id.stationName);
        TextView stationLocation = (TextView) listViewItem.findViewById(R.id.stationLocation);

//        fuelDetails favr = fuelDetails.get(position);
//        stationName.setText("Rs. " + String.valueOf(favr.getPrice())+ ".00");


        favDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Reference = FirebaseDatabase.getInstance().getReference();
////              Reference.removeValue();
//                Reference.child("favourites").child(favr.getId()).removeValue();
//
//                Toast.makeText(context.getApplicationContext(), "Successfully Deleted From Favourites!", Toast.LENGTH_SHORT).show();

            }
        });

//        addToCart.setOnClickListener(new View.OnClickListener() {



//            @Override
//            public void onClick(View view) {
//                if (LoginActivity.loggedUser == null){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle(R.string.app_name);
//                    builder.setMessage("Please login to add items to cart");
//                    builder.setIcon(R.drawable.lehesi);
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.dismiss();
//                            Intent intent = new Intent(context, LoginActivity.class);
//                            context.startActivity(intent);
//                        }
//                    });
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog alert = builder.create();
//                    alert.show();
//                }
//                else {
//                    Toast.makeText(context.getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


//        return listViewItem;
        return listViewItem;
    }
}

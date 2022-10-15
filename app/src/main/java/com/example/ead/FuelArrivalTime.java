package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuelArrivalTime extends AppCompatActivity {

    //Initialize variable
    TextView textViewArriaval, textViewFinished;
    int t1Hour, t1Minute, t2Hour, t2Minute;
    private Button buttonUpdateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_arrival_time);

        //Assign variables
        textViewArriaval = findViewById(R.id.textViewArriaval);
        textViewFinished = findViewById(R.id.textViewFinished);
        buttonUpdateTime = findViewById(R.id.buttonUpdateTime);

        buttonUpdateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), FuelStatus.class);
                startActivityForResult(myIntent, 0);
            }
        });

        textViewArriaval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        FuelArrivalTime.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                //Initialize hour & minute
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                //store in a string
                                String time = t1Hour + ":" + t1Minute;

                                //Initialize 24h format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try{
                                    Date date = f24Hours.parse(time);

                                    //Initialize 12h format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    //set selected time on the Text view
                                    textViewArriaval.setText(f12Hours.format(date));
                                } catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //display previous selected time
                timePickerDialog.updateTime(t1Hour, t1Minute);

                //show dialog
                timePickerDialog.show();
            }
        });

        textViewFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize time picker dialog

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        FuelArrivalTime.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                //Initialize hour & minute
                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                //store in a string
                                String time = t2Hour + ":" + t2Minute;

                                //Initialize 24h format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try{
                                    Date date = f24Hours.parse(time);

                                    //Initialize 12h format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    //set selected time on the Text view
                                    textViewFinished.setText(f12Hours.format(date));
                                } catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //display previous selected time
                timePickerDialog.updateTime(t2Hour, t2Minute);

                //show dialog
                timePickerDialog.show();
            }
        });
    }
}
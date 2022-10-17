package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStationWorker extends AppCompatActivity {

    private Button buttonRegWorker;
    EditText editTextTextWorkerId , editTextTextWorkerName , editTextNICNumber , editTextPhoneNumber , editTextTextWorkerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station_worker);

        buttonRegWorker = (Button) findViewById(R.id.buttonRegWorker);
        //editTexts
        editTextTextWorkerId=findViewById( R.id.editTextTextWorkerId);
        editTextTextWorkerName=findViewById( R.id.editTextTextWorkerName);
        editTextNICNumber=findViewById( R.id.editTextNICNumber);
        editTextPhoneNumber=findViewById( R.id.editTextPhoneNumber);
        editTextTextWorkerPassword=findViewById( R.id.editTextTextWorkerPassword);

        buttonRegWorker.setOnClickListener(new View.OnClickListener() {

            String WorkerId = editTextTextWorkerId.getText().toString();
            String WorkerName = editTextTextWorkerName.getText().toString();
            String NICNumber = editTextNICNumber.getText().toString();
            String PhoneNumber = editTextPhoneNumber.getText().toString();
            String WorkerPassword = editTextTextWorkerPassword.getText().toString();

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewMembers.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
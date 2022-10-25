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

        buttonRegWorker = (Button) findViewById(R.id.buttonUpdate);
        //editTexts
        editTextTextWorkerId=findViewById( R.id.editTextStationREGNo);
        editTextTextWorkerName=findViewById( R.id.editTextTextOwnerUsername);
        editTextNICNumber=findViewById( R.id.editTextTextOwnerNIC);
        editTextPhoneNumber=findViewById( R.id.editTextOwnerPhone);
        editTextTextWorkerPassword=findViewById( R.id.editTextTextPassword2);

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
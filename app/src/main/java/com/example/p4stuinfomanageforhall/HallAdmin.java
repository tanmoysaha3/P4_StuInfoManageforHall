package com.example.p4stuinfomanageforhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HallAdmin extends AppCompatActivity {

    Button mSeatCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_admin);

        mSeatCreateButton=findViewById(R.id.seatCreateButton);

        mSeatCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateSeat.class));
            }
        });
    }
}
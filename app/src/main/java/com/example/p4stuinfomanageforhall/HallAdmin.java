package com.example.p4stuinfomanageforhall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HallAdmin extends AppCompatActivity {

    Button mSeatCreateButton, mOfficialAssignButton, mSeatAssignButton, mSeeEmptyStudentsButton,
            mSeeAssignedStudentsButton, mFilterStudentListButton, mSearchStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_admin);

        mSeatCreateButton=findViewById(R.id.seatCreateButton);
        mOfficialAssignButton=findViewById(R.id.officialAssignButton);
        mSeatAssignButton=findViewById(R.id.seatAssignButton);
        mSeeEmptyStudentsButton=findViewById(R.id.seeEmptyStudentsButton);
        mSeeAssignedStudentsButton=findViewById(R.id.seeAssignedStudentsButton);
        mFilterStudentListButton=findViewById(R.id.filterStudentListButton);
        mSearchStudentButton=findViewById(R.id.searchStudentButton);

        mSeatCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateSeat.class));
            }
        });

        mOfficialAssignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OfficialAssign.class));
            }
        });

        mSeatAssignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SeatAssign.class));
            }
        });

        mSeeEmptyStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SeeStudentList.class);
                intent.putExtra("Value","0");
                startActivity(intent);
            }
        });

        mSeeAssignedStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SeeStudentList.class);
                intent.putExtra("Value","1");
                startActivity(intent);
            }
        });

        mFilterStudentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FilterStudents.class));
            }
        });

        mSearchStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SearchStudent.class));
            }
        });
    }

    public void logoutAdmin(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), AdminLogin.class));
        finish();
    }
}
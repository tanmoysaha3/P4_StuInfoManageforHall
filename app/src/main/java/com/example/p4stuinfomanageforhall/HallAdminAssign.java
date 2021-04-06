package com.example.p4stuinfomanageforhall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class HallAdminAssign extends AppCompatActivity {

    Spinner mHallIdHallAdminSpinner, mAdminIdHallAdminSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_admin_assign);
    }
}
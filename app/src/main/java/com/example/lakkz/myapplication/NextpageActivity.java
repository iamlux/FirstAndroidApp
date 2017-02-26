package com.example.lakkz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lakkz on 26-02-2017.
 */

public class NextpageActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_view_all_employee);
    }
}

package com.example.lakkz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonView;
    private Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonView = (Button) findViewById(R.id.view_data);
        buttonCreate = (Button) findViewById(R.id.create_data);
        buttonCreate.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == buttonView) {
            startActivity(new Intent(this, ViewAllData.class));
        }else if(v == buttonCreate) {
            startActivity(new Intent(this, CreateDataActivity.class));
        }
    }
}

package com.example.lakkz.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Lakkz on 26-02-2017.
 */

public class CreateDataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputName;
    private EditText inputAge;
    private EditText inputDescription;
    private Button createData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);
        inputName = (EditText) findViewById(R.id.name);
        inputAge = (EditText) findViewById(R.id.age);
        inputDescription = (EditText) findViewById(R.id.description);

        createData = (Button) findViewById(R.id.btnCreateData);

        createData.setOnClickListener(this);
    }

    private void addInsertData() {
        final String dataName = inputName.getText().toString().trim();
        final String dataAge = inputAge.getText().toString().trim();
        final String dataDescription = inputDescription.getText().toString().trim();

        Log.d("create_data", dataName);
        class AddInsertData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(CreateDataActivity.this, "Thank you", "Inserting data.....");
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.TAG_NAME, dataName);
                params.put(Config.TAG_AGE, dataAge);
                params.put(Config.TAG_DESCRIPTION, dataDescription);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_POST_ALL, params);
                return res;
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Intent intent_name = new Intent();
                intent_name.setClass(getApplicationContext(),ViewAllData.class);
                startActivity(intent_name);
            }
        }
        AddInsertData ae = new AddInsertData();
        ae.execute();
    }

    public void onClick(View v) {
        if (v == createData) {
            addInsertData();
        }
    }
}
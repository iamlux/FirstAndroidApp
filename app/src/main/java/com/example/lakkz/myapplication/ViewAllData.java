package com.example.lakkz.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllData extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        buttonDelete = (Button) findViewById(R.id.deleteList);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String name = jo.getString(Config.TAG_NAME);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_NAME,name);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllData.this, list, R.layout.list_item,
                new String[]{Config.TAG_NAME},
                new int[]{R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllData.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void deleteRecord() {
        class DeleteRec extends AsyncTask<Void, Void, String> {
            ProgressDialog load;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                load = ProgressDialog.show(ViewAllData.this, "Delete Recored", "Wait....", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                load.dismiss();
                Toast.makeText(ViewAllData.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE, id);
                return s;
            }
        }
        DeleteRec del = new DeleteRec();
        del.execute();
    }

    public void myClickHandler(View v) {
            confirmDeleteRecord();
    }

    private void confirmDeleteRecord() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this record");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteRecord();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, ViewAllData.class);
//        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
//        String empId = map.get(Config.TAG_ID).toString();
//        intent.putExtra(Config.TAG_ID,empId);
//        startActivity(intent);
//    }
}

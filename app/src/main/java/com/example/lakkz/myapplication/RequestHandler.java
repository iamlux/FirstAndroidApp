package com.example.lakkz.myapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Belal on 10/24/2015.
 */

public class RequestHandler {

    //Method to send httpPostRequest
    //This method is taking two arguments
    //First argument is the URL of the script to which we will send the request
    //Other is an HashMap with name value pairs containing the data to be send with the request
    public String sendGetRequest(String requestURL) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }
}

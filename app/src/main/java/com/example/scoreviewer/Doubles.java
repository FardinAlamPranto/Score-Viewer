package com.example.scoreviewer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class Doubles extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubles);


        Button syncBtn = findViewById(R.id.sync_players);
        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable())
                    getItems();
                else
                    Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_LONG).show();

            }
        });

        Button viewBtn = findViewById(R.id.view_all);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewActivity = new Intent(getApplicationContext(), ShowPlayers.class);
                startActivity(viewActivity);
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getItems(){

        final ProgressDialog loading = ProgressDialog.show(this,"Getting Data","Please wait");
        String url = "https://script.google.com/macros/s/AKfycbyfdQalTuX_aUtrbfJPqb9L133xkk_HI5OHuCoydauuu6A6NGg/exec";
        loading.create();

        Map<String, String> params = new HashMap<>();
        params.put("sheetname", "Players");

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Players");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject player = jsonArray.getJSONObject(i);

                                Integer ID = player.getInt("id");
                                String firstname = player.getString("firstname");
                                String surname = player.getString("surname");
                                String mobile = player.getString("mobile");
                                String matchlist = player.getString("matchlist");

                                MainActivity.myDb.insertPlayer(ID, firstname, surname, mobile, matchlist);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(Doubles.this, "Error Getting Data", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }
        );

        int socketTimeOut = 10000;// u can change this .. here it is 10 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }


}

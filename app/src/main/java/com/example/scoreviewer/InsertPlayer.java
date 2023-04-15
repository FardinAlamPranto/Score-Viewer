package com.example.scoreviewer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertPlayer extends AppCompatActivity {

    private static String firstname, lastname, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_player);
    }

    public void makePlayer(){
        EditText fname = (EditText) findViewById(R.id.fname);
        EditText sname = (EditText) findViewById(R.id.sname);
        EditText mobil = (EditText) findViewById(R.id.mnum);

        firstname = fname.getText().toString();
        lastname = sname.getText().toString();
        mobile = mobil.getText().toString();

        if(firstname.length() == 0 || mobile.length() == 0) {
            Toast.makeText(this, "First name and Mobile no. are mandatory", Toast.LENGTH_LONG).show();
        }
        else if(isNetworkAvailable()){
            // sync from cloud
            // code to save to sheets
        }
        else {
            // save locally here
            Toast.makeText(this, "No internet. Saved locally only", Toast.LENGTH_LONG).show();
        }
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}

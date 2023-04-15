package com.example.scoreviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb= new DataBaseHelper(getApplicationContext());
    }

    public void startDoubles(View view) {
        Intent intent = new Intent(this, Doubles.class);
        startActivity(intent);
    }
}

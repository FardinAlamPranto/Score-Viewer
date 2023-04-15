package com.example.scoreviewer;

import android.util.Log;

public class MinPlayers {
    private String firstname;
    private String surname;

    private static final String TAG = "MinPlayers";

    public MinPlayers(String firstname, String surname) {
        Log.d(TAG, "MinPlayers: " + firstname + " " + surname);
        this.firstname = firstname;
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }
}

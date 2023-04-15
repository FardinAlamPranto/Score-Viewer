package com.example.scoreviewer;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowPlayers extends AppCompatActivity {


    private static final String TAG = "ShowPlayers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doubles_players);

        ArrayList<MinPlayers> playerList = new ArrayList<>();




        try (Cursor c = MainActivity.myDb.getMinPlayerList();) {

            while (c.moveToNext()) {
                playerList.add(new MinPlayers(c.getString(c.getColumnIndex(DataBaseHelper.P_COL_2)), c.getString(c.getColumnIndex(DataBaseHelper.P_COL_3))));
            }
        }

        ListView mListView = findViewById(R.id.player_list);
        PlayerListAdapter adapter = new PlayerListAdapter(this, R.layout.player_list_item, playerList);
        mListView.setAdapter(adapter);


    }


}

package com.example.antho.mastermind.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.antho.mastermind.R;
import com.example.antho.mastermind.database.DAO;
import com.example.antho.mastermind.adapter.GameHistoryAdapter;

public class GameHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        DAO dao = new DAO(this);
        ListView listView = (ListView) findViewById(R.id.listviewHistory);

        GameHistoryAdapter adapter = new GameHistoryAdapter(this, R.layout.game_history_row, dao.getPartita(), getString(R.string.tentativi));

        listView.setAdapter(adapter);
    }
}

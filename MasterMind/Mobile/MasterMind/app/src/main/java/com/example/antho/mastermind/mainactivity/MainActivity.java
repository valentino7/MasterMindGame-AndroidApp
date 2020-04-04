package com.example.antho.mastermind.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.antho.mastermind.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEasy;
    private Button btnMedium;
    private Button btnHard;
    private Button btnInstruction;
    private Button btnHistory;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEasy = (Button)findViewById(R.id.btnEasy);
        btnMedium = (Button)findViewById(R.id.btnMedium);
        btnHard= (Button)findViewById(R.id.btnHard);
        btnInstruction = (Button)findViewById(R.id.btnInst);
        btnHistory = (Button)findViewById(R.id.btnHist);

        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnHard.setOnClickListener(this);
        btnInstruction.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btnEasy.getId()) {
            Intent i=new Intent(this,Main2Activity.class);
            difficulty = "Easy";
            i.putExtra("code", difficulty);
            startActivity(i);
        }
        if (view.getId() == btnMedium.getId()) {
            Intent i=new Intent(this,Main2Activity.class);
            difficulty ="Medium";
            i.putExtra("code", difficulty);
            startActivity(i);
        }
        if (view.getId() == btnHard.getId()) {
            Intent i=new Intent(this,Main2Activity.class);
            difficulty = "Hard";
            i.putExtra("code", difficulty);
            startActivity(i);
        }
        if (view.getId() == btnInstruction.getId()) {
            Intent i=new Intent(this,InstructionActivity.class);
            startActivity(i);
        }
        if (view.getId() == btnHistory.getId()) {
            Intent i=new Intent(this,GameHistoryActivity.class);
            startActivity(i);
        }
    }
}

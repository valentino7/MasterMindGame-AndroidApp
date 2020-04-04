package com.example.antho.mastermind.mainactivity;

import android.content.Intent;
import android.graphics.Color;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.antho.mastermind.adapter.CustomAdapter;
import com.example.antho.mastermind.adapter.CustomSpinnerAdapter;
import com.example.antho.mastermind.adapter.MasterMind;
import com.example.antho.mastermind.R;
import com.example.antho.mastermind.database.DAO;
import com.example.antho.mastermind.database.Partita;
import com.example.antho.mastermind.fragment.MyDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    private ListView listView;
    private Button btnOk;
    private ArrayList<MasterMind> myList;
    private ControllerSolution ctrl;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private ArrayList<Integer> solution;
    private String difficulty;
    private ArrayList<Integer> colorList;
    private TextView twTentativi;
    private String data;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dao = new DAO(this);
        // set data
        data="";
        GregorianCalendar gc = new GregorianCalendar();
        data=data+gc.get(Calendar.YEAR);
        int mese = gc.get(Calendar.MONTH ) +1 ;
        data=data+"/"+ mese;
        data=data+"/"+gc.get(Calendar.DAY_OF_MONTH);

        // get difficult from mainActivity
        Intent intent = getIntent();
        difficulty = intent.getExtras().getString("code");

        // new controller
        this.ctrl = new ControllerSolution(this.difficulty);

        // genera la soluzione
        solution = ctrl.generateSolution();


        //listview adapter
        listView = (ListView) findViewById(R.id.lista);
        myList = new ArrayList<>();

        // spinner adapter
        colorList = new ArrayList<>();
        colorList.add(Color.rgb(9,21,186));
        colorList.add(Color.RED);
        colorList.add(Color.rgb(39,126,39));
        colorList.add(Color.rgb(61,43,31));
        colorList.add(Color.MAGENTA);
        colorList.add(Color.rgb(11,154,244));
        colorList.add(Color.rgb(255,128,0));
        colorList.add(Color.rgb(240,230,140));
        if (difficulty.equals("Hard") || difficulty.equals("Medium")) {
            colorList.add(Color.rgb(255,192,203));
            colorList.add(Color.YELLOW);
        }
        CustomSpinnerAdapter myAdapter = new CustomSpinnerAdapter(this, R.layout.customspinner, colorList);


        //spinner1
        spinner1 = (Spinner) findViewById(R.id.spinner1_1);
        spinner1.setOnItemSelectedListener(this);
        spinner1.setAdapter(myAdapter);

        //spinner2
        spinner2 = (Spinner) findViewById(R.id.spinner1_2);
        spinner2.setOnItemSelectedListener(this);
        spinner2.setAdapter(myAdapter);

        //spinner3
        spinner3 = (Spinner) findViewById(R.id.spinner1_3);
        spinner3.setOnItemSelectedListener(this);
        spinner3.setAdapter(myAdapter);

        //spinner4
        spinner4 = (Spinner) findViewById(R.id.spinner1_4);
        spinner4.setOnItemSelectedListener(this);
        spinner4.setAdapter(myAdapter);

        //spinner5
        spinner5 = (Spinner) findViewById(R.id.spinner1_5);
        spinner5.setOnItemSelectedListener(this);
        spinner5.setAdapter(myAdapter);

        //bottone OK
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        //tentativi rimasti
        twTentativi= (TextView) findViewById(R.id.twTentativi);

        twTentativi.setText(getString(R.string.tentativi)+ String.valueOf(10) );

        if (difficulty.equals("Easy") || difficulty.equals("Medium")) {
            spinner5.setVisibility(View.GONE);
        } else {
            spinner5.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btnOk.getId()) {

            ArrayList<Integer> spinnerSelection = new ArrayList<>();
            spinnerSelection.add(spinner1.getSelectedItemPosition());
            spinnerSelection.add(spinner2.getSelectedItemPosition());
            spinnerSelection.add(spinner3.getSelectedItemPosition());
            spinnerSelection.add(spinner4.getSelectedItemPosition());
            if (difficulty.equals("Hard")) {
                spinnerSelection.add(spinner5.getSelectedItemPosition());
            }
            /*System.out.println(spinnerSelection.get(0));
            System.out.println(spinnerSelection.get(1));
            System.out.println(spinnerSelection.get(2));
            System.out.println(spinnerSelection.get(3));*/
            Integer[] validate = ctrl.verify(solution, spinnerSelection);
            myList.add(new MasterMind((Integer) spinner1.getSelectedItem(), (Integer) spinner2.getSelectedItem(), (Integer) spinner3.getSelectedItem()
                    , (Integer) spinner4.getSelectedItem(), (Integer) spinner5.getSelectedItem(), validate));
            CustomAdapter adapter = new CustomAdapter(this, R.layout.customadapter, myList, this.difficulty);
            listView.setAdapter(adapter);


            twTentativi.setText(getString(R.string.tentativi) + String.valueOf(10 - myList.size()));
            if (10 - myList.size() < 0)
                twTentativi.setText(getString(R.string.tentativi) + String.valueOf(0));
            // bundle del fragment
            Bundle b = new Bundle();
            b.putString("difficulty", difficulty);
            b.putIntegerArrayList("solution", ctrl.createColorSolution(this.colorList, this.solution));
            FragmentManager fm = getFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment();
            dialogFragment.setCancelable(false);
            dialogFragment.setArguments(b);

            if (difficulty.equals("Hard")) {
                // hai perso
                if (myList.size() == 10 && validate[0] != 5) {
                    b.putString("result", getString(R.string.fineGiocoPerso));
                    Partita p = new Partita(getResources().getIdentifier("difficile","string",getPackageName()), data, R.string.storicoSconfitta, myList.size());
                    dao.insert(p);
                    dialogFragment.show(fm, "Sample Fragment");

                }
                // hai vinto
                if (myList.size() <= 10 && (validate[0] == 5)) {
                    b.putString("result", getString(R.string.fineGiocoVinto));
                    Partita p = new Partita(getResources().getIdentifier("difficile","string",getPackageName()), data, R.string.storicoVittoria, myList.size());
                    dao.insert(p);
                    dialogFragment.show(fm, "Sample Fragment");

                }
            } else if (difficulty.equals("Medium")) {
                // hai Vinto
                if (myList.size() <= 10 && (validate[0] == 4)) {
                    b.putString("result", getString(R.string.fineGiocoVinto));
                    Partita p = new Partita(getResources().getIdentifier("Medio","string",getPackageName()), data, R.string.storicoVittoria, myList.size());
                    dao.insert(p);
                    dialogFragment.show(fm, "Sample Fragment");

                }
                // hai perso
                if (myList.size() == 10 && validate[0] != 4) {
                    b.putString("result", getString(R.string.fineGiocoPerso));
                    Partita p = new Partita(getResources().getIdentifier("Medio","string",getPackageName()), data, R.string.storicoSconfitta, myList.size());
                    dao.insert(p);
                    dialogFragment.show(fm, "Sample Fragment");
                }
            } else {
                // hai Vinto
                if (myList.size() <= 10 && (validate[0] == 4)) {
                    b.putString("result", getString(R.string.fineGiocoVinto));
                    Partita p = new Partita(getResources().getIdentifier("facile","string",getPackageName()), data, R.string.storicoVittoria, myList.size());
                    dao.insert(p);
                    dialogFragment.show(fm, "Sample Fragment");

                }
                // hai perso
                if (myList.size() == 10 && validate[0] != 4) {
                    b.putString("result", getString(R.string.fineGiocoPerso));
                    Partita p = new Partita(getResources().getIdentifier("facile","string",getPackageName()), data, R.string.storicoSconfitta, myList.size());
                    dao.insert(p);
                    dialogFragment.show(fm, "Sample Fragment");

                }

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if ( difficulty.equals("Easy")){
            Partita p = new Partita(getResources().getIdentifier("facile","string",getPackageName()), data,  R.string.ritirato, myList.size());
            dao.insert(p);
        }
        if ( difficulty.equals("Medium")){
            Partita p = new Partita(getResources().getIdentifier("Medio","string",getPackageName()), data,  R.string.ritirato, myList.size());
            dao.insert(p);
        }
        if ( difficulty.equals("Hard")){
            Partita p = new Partita(getResources().getIdentifier("difficile","string",getPackageName()), data,  R.string.ritirato, myList.size());
            dao.insert(p);
        }
    }

}

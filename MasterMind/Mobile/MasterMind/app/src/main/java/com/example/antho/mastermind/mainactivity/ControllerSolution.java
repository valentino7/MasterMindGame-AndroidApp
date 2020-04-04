package com.example.antho.mastermind.mainactivity;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class ControllerSolution {

    private String difficulty;

    public ControllerSolution(String d){
        this.difficulty=d;
    }

    public ArrayList<Integer> generateSolution(){
        ArrayList<Integer> mySolution= new ArrayList<>();
        int n;
        int MAX;
        if (difficulty.equals("Easy")  ){
            n = 4;
            MAX = 8;
        }
        else if (difficulty.equals("Medium")){
            n= 4;
            MAX = 10;
        }
        else{
            n = 5;
            MAX = 10;
        }
        for ( int i = 0; i< n;i++){
            int r = new Random().nextInt(MAX);
            Log.d("codice", "value: " + r);
            mySolution.add(r);
        }




        /*
        mySolution= new Vector<>();
        mySolution.add(0);
        mySolution.add(0);
        mySolution.add(1);
        mySolution.add(1);
        mySolution.add(1);
        */





        return mySolution;
    }

    public Integer[] verify(ArrayList<Integer> solution,ArrayList<Integer> userAttempt){
        // vettore di interi relativi alle palline bianche e nere
        Integer[] validate = new Integer[2];
        ArrayList<Integer> v = copia(solution);
        ArrayList<Integer> userSolution =copia(userAttempt);
        validate[0]=validate[1]=0;
        for(int i = 0 ; i < userSolution.size(); i++) {

            if (userSolution.get(i).equals(v.get(i))) {
                //nero
                v.set(i, -1);
                userSolution.set(i,-2);
                validate[0] = validate[0] + 1;
            }
        }
        for(int i = 0 ; i < userSolution.size(); i++){
            if ( contiene(v,userSolution.get(i))){
                //bianco
                validate[1]=validate[1]+1;
            }
        }
        return validate;
    }

    private boolean contiene(ArrayList<Integer> v, Integer integer) {
        for( int i = 0 ; i< v.size();i++){
            if (v.get(i).equals(integer)){
                v.set(i, -1 );
                return true;
            }
        }
        return false;
    }

    private ArrayList<Integer> copia(ArrayList<Integer> v){
        ArrayList<Integer> copia = new ArrayList<>();
        for ( int i = 0; i< v.size(); i++){
            copia.add(v.get(i));
        }
        return copia;
    }

    public ArrayList<Integer> createColorSolution (ArrayList<Integer> color, ArrayList<Integer> number ){
        ArrayList<Integer> colorSolution = new ArrayList<>();
        for ( int i = 0 ; i< number.size(); i++){
            colorSolution.add(color.get(number.get(i)));
        }
        return colorSolution;
    }
}

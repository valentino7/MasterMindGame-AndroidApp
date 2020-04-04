package com.example.antho.mastermind.adapter;

import android.widget.Button;
import android.widget.TextView;

import com.example.antho.mastermind.CircleView;


public class Holder {
    public CircleView btnColore1,btnColore2,btnColore3,btnColore4,btnColore5;
    public CircleView[] btnTest;

    public Holder(){
        btnTest = new CircleView[5];
    }
}
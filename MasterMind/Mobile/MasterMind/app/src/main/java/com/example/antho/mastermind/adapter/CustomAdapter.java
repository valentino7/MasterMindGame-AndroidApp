package com.example.antho.mastermind.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.antho.mastermind.CircleView;
import com.example.antho.mastermind.R;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<MasterMind> {

    private String difficulty;
    private Integer[] test;
    private boolean flag = false;

    public CustomAdapter(Context context, int resource, ArrayList<MasterMind> objects, String difficulty) {
        super(context, resource, objects);
        this.difficulty = difficulty;

    }
    public CustomAdapter(Context context, int resource, ArrayList<MasterMind> objects, String difficulty,boolean t) {
        super(context, resource, objects);
        this.difficulty = difficulty;
        this.flag = t;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder viewHolder = null;

        if ( convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customadapter,parent,false);
            viewHolder = new Holder();
            viewHolder.btnColore1 = (CircleView) convertView.findViewById(R.id.circle1);
            viewHolder.btnColore2 = (CircleView)convertView.findViewById(R.id.circle2);
            viewHolder.btnColore3 = (CircleView) convertView.findViewById(R.id.circle3);
            viewHolder.btnColore4 = (CircleView)convertView.findViewById(R.id.circle4);
            viewHolder.btnColore5 = (CircleView)convertView.findViewById(R.id.circle5);
            viewHolder.btnTest[0] = (CircleView) convertView.findViewById(R.id.test1);
            viewHolder.btnTest[1]= (CircleView)convertView.findViewById(R.id.test2);
            viewHolder.btnTest[2] = (CircleView) convertView.findViewById(R.id.test3);
            viewHolder.btnTest[3] = (CircleView)convertView.findViewById(R.id.test4);
            viewHolder.btnTest[4] = (CircleView)convertView.findViewById(R.id.test5);
            if (difficulty.equals("Easy") || difficulty.equals("Medium") ) {
                viewHolder.btnColore5.setVisibility(View.GONE);
                viewHolder.btnTest[4].setVisibility(View.GONE);
            }
            else{
                viewHolder.btnColore5.setVisibility(View.VISIBLE);
                viewHolder.btnTest[4].setVisibility(View.VISIBLE);
            }
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (Holder) convertView.getTag();
        }
        MasterMind c = getItem(position);
        test = new Integer[2];
        test[0]= c.getBlack();
        test[1]= c.getWhite();
        viewHolder.btnColore1.setCircleColor(c.getColore1());
        viewHolder.btnColore2.setCircleColor(c.getColore2());
        viewHolder.btnColore3.setCircleColor(c.getColore3());
        viewHolder.btnColore4.setCircleColor(c.getColore4());
        //neri
        for( int i = 0; i<test[0]; i++) {
            viewHolder.btnTest[i].setFill();
            viewHolder.btnTest[i].setCircleColor(Color.BLACK);
        }
        //bianchi
        for( int i = test[0]; i < test[0]+test[1] ;i++){
            viewHolder.btnTest[i].setFill();
            viewHolder.btnTest[i].setCircleColor(Color.WHITE);
        }
        for( int i =test[0]+test[1] ; i < 5; i++){
            viewHolder.btnTest[i].setStroke();
            viewHolder.btnTest[i].setCircleColor(Color.BLACK);
        }
        if ( difficulty.equals("Hard") ) {
            viewHolder.btnColore5.setCircleColor(c.getColore5());
        }

        if (this.flag){
            viewHolder.btnTest[0].setVisibility(View.GONE);
            viewHolder.btnTest[1].setVisibility(View.GONE);
            viewHolder.btnTest[2].setVisibility(View.GONE);
            viewHolder.btnTest[3].setVisibility(View.GONE);
            viewHolder.btnTest[4].setVisibility(View.GONE);
        }

        return convertView;

    }
}

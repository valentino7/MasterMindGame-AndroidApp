package com.example.antho.mastermind.adapter;


import java.util.ArrayList;
import java.util.Vector;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.antho.mastermind.CircleView;
import com.example.antho.mastermind.R;


public class CustomSpinnerAdapter extends ArrayAdapter<Integer>{

    LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, int resource, ArrayList<Integer> objects) {
        super(context,resource,objects);
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    public View getCustomView(int position, View convertView, ViewGroup parent) {
        CircleView cerchio;
        if ( convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customspinner,parent,false);
            cerchio = (CircleView) convertView.findViewById(R.id.circlespinner);
            convertView.setTag(cerchio);
        }else {
            cerchio = (CircleView) convertView.getTag();
        }
        int c = getItem(position);
        cerchio.setCircleColor(c);
        return convertView;

    }
}
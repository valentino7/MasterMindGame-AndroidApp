package com.example.antho.mastermind.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.antho.mastermind.R;
import com.example.antho.mastermind.database.Partita;

import java.util.ArrayList;

public class GameHistoryAdapter extends ArrayAdapter<Partita> {

    private Context activity;
    private String ten;

    public GameHistoryAdapter(Context context, int resource, ArrayList<Partita> objects, String t) {
        super(context, resource, objects);
        this.activity = context;
        this.ten = t;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GameHistoryHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new GameHistoryHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.game_history_row,parent,false);
            viewHolder.twData = (TextView) convertView.findViewById(R.id.twData);
            viewHolder.twTentativi= (TextView) convertView.findViewById(R.id.twTent);
            viewHolder.twdifficolta = (TextView) convertView.findViewById(R.id.twDiff);
            viewHolder.twRisultato= (TextView) convertView.findViewById(R.id.twRis);
            viewHolder.twId= (TextView) convertView.findViewById(R.id.twId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GameHistoryHolder) convertView.getTag();
        }
        Partita p = getItem(position);
        viewHolder.twData.setText(p.getData());
        viewHolder.twdifficolta.setText(parent.getContext().getString(p.getDifficolta()) );
        viewHolder.twRisultato.setText(parent.getContext().getString(p.getRisultsato()));
        viewHolder.twTentativi.setText(ten + String.valueOf(p.getTentativi()));
        viewHolder.twId.setText(String.valueOf(p.getId()));
        return convertView;
    }

}

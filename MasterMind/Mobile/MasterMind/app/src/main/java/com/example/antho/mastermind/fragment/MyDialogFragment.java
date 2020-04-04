package com.example.antho.mastermind.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antho.mastermind.adapter.CustomAdapter;
import com.example.antho.mastermind.adapter.MasterMind;
import com.example.antho.mastermind.R;

import java.util.ArrayList;


public class MyDialogFragment extends DialogFragment implements View.OnClickListener {

    private Button btnOk;
    private TextView twRisultato;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // prende i parametri dal bundle impostato nella main2Activity
        savedInstanceState = this.getArguments();
        ArrayList<Integer> solutionList = savedInstanceState.getIntegerArrayList("solution");
        String difficulty = savedInstanceState.getString("difficulty");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.fragment_sample_layout, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        twRisultato = (TextView) rootView.findViewById(R.id.twRisultato);
        btnOk = (Button) rootView.findViewById(R.id.btnOk2);
        btnOk.setOnClickListener(this);
        ListView cardList = (ListView) rootView.findViewById(R.id.listViewFragment);

        ArrayList<MasterMind> list = new ArrayList<>();
        if ( difficulty.equals("Hard")){
            list.add( new MasterMind(solutionList.get(0), solutionList.get(1), solutionList.get(2) , solutionList.get(3) , solutionList.get(4)));
        } else {
            list.add( new MasterMind(solutionList.get(0), solutionList.get(1), solutionList.get(2) , solutionList.get(3) , -10));
        }
        twRisultato.setText(savedInstanceState.getString("result"));
        CustomAdapter adapter = new CustomAdapter(getActivity(), R.layout.customadapter, list, difficulty,true);
        cardList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if ( view.getId() == btnOk.getId()){
            getActivity().finish();
        }
    }

}

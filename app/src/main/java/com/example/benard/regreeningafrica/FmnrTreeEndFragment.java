package com.example.benard.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrTreeEndFragment extends Fragment {
    private DbAccess dbAccess;
    public FmnrTreeEndFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_tree_end, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //add another tree from the cohort
        Button button_addtree = (Button) view.findViewById(R.id.add_tree);
        button_addtree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_tree:
                        //saveMeasurement();
                        //dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), FmnrTreeMeasureMainActivity.class);
                        startActivity(intent);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        Button button_addnewfarmerinst = (Button) view.findViewById(R.id.add_new_farmerinst);
        button_addnewfarmerinst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_new_farmerinst:
                        //saveMeasurement();
                        //dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), TPFarmInstiMainAcivity.class);
                        startActivity(intent);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //finish survey
        Button button_next = (Button) view.findViewById(R.id.finish);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.finish:
                        //saveMeasurement();
                        //dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;
    }

    public  void saveMeasurement(){
        EditText height=(EditText) getActivity().findViewById(R.id.height);
        g.setheight(height.getText().toString());
        EditText rcd = (EditText) getActivity().findViewById(R.id.RCD);
        g.setrcd(rcd.getText().toString());
        EditText dbh = (EditText) getActivity().findViewById(R.id.dbh);
        g.setdbh(dbh.getText().toString());
        //get Gps get it from global
        //get Photo get it from global
    }
}

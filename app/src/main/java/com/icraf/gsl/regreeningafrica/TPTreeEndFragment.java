package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by benard on 1/18/19.
 *
 */

public class TPTreeEndFragment extends Fragment {
    private DbAccess dbAccess;
    public TPTreeEndFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_tree_planting, container,
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
                        saveMeasurement();
                        dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), TPTreeMeasureMainAcivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //add new cohort of the same farmer or institution
        Button button_addcohort = (Button) view.findViewById(R.id.add_cohort);
        button_addcohort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_cohort:
                        saveMeasurement();
                        dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), TPCohortMainAcivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
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
                        saveMeasurement();
                        dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), TPFarmInstiMainAcivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
                        saveMeasurement();
                        dbAccess.insertMeasurent();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;
    }

    public  void saveMeasurement(){
       /* EditText height=(EditText) getActivity().findViewById(R.id.height);
        g.setheight(height.getText().toString());*/
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.height);
        //check whether it is checked
        if(group.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FarmerDetails.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = group.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton height = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setheight(height.getText().toString());
        }
        /*EditText rcd = (EditText) getActivity().findViewById(R.id.RCD);
        g.setrcd(rcd.getText().toString());*/
        EditText dbh = (EditText) getActivity().findViewById(R.id.db_rc);
        g.setdbh(dbh.getText().toString());
        //get Gps get it from global
        //get Photo get it from global
        g.setuploaded("no");//set uploaded to no on insert
    }
}

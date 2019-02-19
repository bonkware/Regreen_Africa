package com.example.benard.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by benard on 1/18/19.
 */

public class TPCohortUsageFragment extends Fragment {

    private DbAccess dbAccess;
    public TPCohortUsageFragment() {
        // Required empty public constructor
    }
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tree_usage, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //check for usage
        final CheckBox check1 = (CheckBox) view.findViewById(R.id.usg_other) ;
        final EditText text1=(EditText) view.findViewById(R.id.us_other);

        check1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(check1.isChecked()){
                    text1.setVisibility(View.VISIBLE);
                }else{
                    text1.setVisibility(View.GONE);
                }
            }
        });

        //proceed to tree measurement
        Button button_next = (Button) view.findViewById(R.id.tomeasurement);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tomeasurement:
                        saveCohort();
                        dbAccess.insertCohort();
                        Intent intent = new Intent(getActivity(), TPTreeMeasureMainAcivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;
    }

    public  void saveCohort(){
        //generate unique id for the cohort
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);//random number generator
        String cid = "cohort_" + n;
        g.setcid(cid);
        TextView fid = (TextView) getActivity().findViewById(R.id.fid);
        g.setfid(fid.getText().toString());
        EditText species = (EditText) getActivity().findViewById(R.id.speciesname);
        g.setspecies_name(species.getText().toString());
        EditText date_planted = (EditText) getActivity().findViewById(R.id.p_date);
        g.setdate_planted(date_planted.getText().toString());
        EditText planted = (EditText) getActivity().findViewById(R.id.number_planted);
        g.setnumber_planted(date_planted.getText().toString());
        EditText survived = (EditText) getActivity().findViewById(R.id.number_survived);
        g.setnumber_survived(date_planted.getText().toString());

        //managements
        CheckBox mg1 = (CheckBox) getActivity().findViewById(R.id.mg1) ;
        if(mg1.isChecked()) {
            g.setmg1("yes");
        }else {
            g.setmg1("no");
        }

        CheckBox mg2 = (CheckBox) getActivity().findViewById(R.id.mg2) ;
        if(mg2.isChecked()) {
            //g.setmg2(mg2.getText().toString());
            g.setmg2("yes");
        }else {
            g.setmg2("no");
        }

        CheckBox mg3 = (CheckBox) getActivity().findViewById(R.id.mg3) ;
        if(mg3.isChecked()) {
            //g.setmg3(mg3.getText().toString());
            g.setmg3("yes");
        }else {
            g.setmg3("no");
        }

        CheckBox mg4 = (CheckBox) getActivity().findViewById(R.id.mg4) ;
        if(mg4.isChecked()) {
            //g.setmg4(mg4.getText().toString());
            g.setmg4("yes");
        }else {
            g.setmg4("no");
        }

        CheckBox mg5 = (CheckBox) getActivity().findViewById(R.id.mg5) ;
        if(mg5.isChecked()) {
            //g.setmg5(mg5.getText().toString());
            g.setmg5("yes");
        }else {
            g.setmg5("no");
        }

        //CheckBox mg_other = (CheckBox) view.findViewById(R.id.mg_others) ;
        CheckBox  check = (CheckBox) getActivity().findViewById(R.id.mg_others) ;
        if(check.isChecked()) {
            g.setmg_others(check.getText().toString());
        }else {
            g.setmg_others("no");
        }

        //get the edit text value for other
        EditText text=(EditText) getActivity().findViewById(R.id.mg_other);
        g.setmg_other(text.getText().toString());

        //usage
        //get EditText by id and store it
        CheckBox usage1 = (CheckBox) getActivity().findViewById(R.id.usage1) ;
        if(usage1.isChecked()) {
            //g.setusage1(usage1.getText().toString());
            g.setusage1("yes");
        }else {
            g.setusage1("no");
        }

        CheckBox usage2 = (CheckBox) getActivity().findViewById(R.id.usage2) ;
        if(usage2.isChecked()) {
            //g.setusage2(usage2.getText().toString());
            g.setusage2("yes");
        }else {
            g.setusage2("no");
        }

        CheckBox usage3 = (CheckBox) getActivity().findViewById(R.id.usage3) ;
        if(usage3.isChecked()) {
            //g.setusage3(usage3.getText().toString());
            g.setusage3("yes");
        }else {
            g.setusage3("no");
        }

        CheckBox usage4 = (CheckBox) getActivity().findViewById(R.id.usage4) ;
        if(usage4.isChecked()) {
            //g.setusage4(usage4.getText().toString());
            g.setusage4("yes");
        }else {
            g.setusage4("no");
        }

        CheckBox usage5 = (CheckBox) getActivity().findViewById(R.id.usage5) ;
        if(usage5.isChecked()) {
            //g.setusage5(usage5.getText().toString());
            g.setusage5("yes");
        }else {
            g.setusage5("no");
        }
        CheckBox check1 = (CheckBox) getActivity().findViewById(R.id.usg_other) ;
        if(check1.isChecked()) {
            g.setusg_other(check1.getText().toString());
        }else {
            g.setusg_other("no");
        }

        //get the edit text value for other
        EditText text1=(EditText) getActivity().findViewById(R.id.us_other);
        g.setus_other(text1.getText().toString());



    }
}
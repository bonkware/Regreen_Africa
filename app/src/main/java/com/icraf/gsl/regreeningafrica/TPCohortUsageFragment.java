package com.icraf.gsl.regreeningafrica;

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
 *
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
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
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
        g.setnumber_planted(planted.getText().toString());
        EditText survived = (EditText) getActivity().findViewById(R.id.number_survived);
        g.setnumber_survived(survived.getText().toString());

        //tree planting area
        CheckBox woodlot = (CheckBox) getActivity().findViewById(R.id.woodlot);
        if(woodlot.isChecked()) {
            g.setwoodlot("yes");
        }else {
            g.setwoodlot("no");
        }
        CheckBox iboundary = (CheckBox) getActivity().findViewById(R.id.iboundary);
        if(iboundary.isChecked()) {
            g.setiboundary("yes");
        }else {
            g.setiboundary("no");
        }
        CheckBox eboundary = (CheckBox) getActivity().findViewById(R.id.eboundary);
        if(eboundary.isChecked()) {
            g.seteboundary("yes");
        }else {
            g.seteboundary("no");
        }

        CheckBox garden = (CheckBox) getActivity().findViewById(R.id.garden);
        if(garden.isChecked()) {
            g.setgarden("yes");
        }else {
            g.setgarden("no");
        }

        CheckBox crop_field = (CheckBox) getActivity().findViewById(R.id.crop_field);
        if(crop_field.isChecked()) {
            g.setcrop_field("yes");
        }else {
            g.setcrop_field("no");
        }
        CheckBox pasture_grassland = (CheckBox) getActivity().findViewById(R.id.pasture_grassland);
        if(pasture_grassland.isChecked()) {
            g.setpasture_grassland("yes");
        }else {
            g.setpasture_grassland("no");
        }
        CheckBox fallow_pushland = (CheckBox) getActivity().findViewById(R.id.fallow_pushland);
        if(fallow_pushland.isChecked()) {
            g.setfallow_pushland("yes");
        }else {
            g.setfallow_pushland("no");
        }
        EditText others=(EditText) getActivity().findViewById(R.id.other_sites);
        g.setother_sites(others.getText().toString());

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
        CheckBox usage6 = (CheckBox) getActivity().findViewById(R.id.usage6) ;
        if(usage6.isChecked()) {
            g.setusage6("yes");
        }else {
            g.setusage6("no");
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
        g.setuploaded("no");//set uploaded to no on insert

    }
}
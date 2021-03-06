package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrTreeEndFragment extends Fragment {
    private DbAccess dbAccess;
    int clickcount=0;
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
        final Button button_addtree = (Button) view.findViewById(R.id.add_tree);
        button_addtree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_tree:
                        saveMeasurements();
                        dbAccess.insertFmnrSpecies();//insert into db
                        //hide button if clicked more than five
                        /*clickcount=clickcount+1;
                            //check how many times clicked and so on
                            if (clickcount >= 5){
                                button_addtree.setEnabled(false);//enable this button after 4 polygon point are saved
                            }
                            Toast.makeText(FmnrTreeEndFragment.this.getActivity(),"Point "+clickcount+" saved", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), FmnrTreeMeasureMainActivity.class);
                            startActivity(intent);*/
                        Intent intent = new Intent(getActivity(), FmnrTreeMeasureMainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
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
                        saveMeasurements();
                        dbAccess.insertFmnrSpecies();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }

    public  void saveMeasurements(){
        TextView fid = (TextView) getActivity().findViewById(R.id.fid);
        g.setfid(fid.getText().toString());
        EditText species = (EditText) getActivity().findViewById(R.id.speciesname);
        g.setspecies_name(species.getText().toString());
        EditText local = (EditText) getActivity().findViewById(R.id.localname);
        g.setlocal_name(local.getText().toString());

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

        CheckBox mg6 = (CheckBox) getActivity().findViewById(R.id.mg6) ;
        if(mg6.isChecked()) {
            //g.setusage5(usage5.getText().toString());
            g.setmg6("yes");
        }else {
            g.setmg6("no");
        }
        CheckBox mg7 = (CheckBox) getActivity().findViewById(R.id.mg7) ;
        if(mg7.isChecked()) {
            //g.setusage5(usage5.getText().toString());
            g.setmg7("yes");
        }else {
            g.setmg7("no");
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
        CheckBox usage7 = (CheckBox) getActivity().findViewById(R.id.usage7) ;
        if(usage7.isChecked()) {
            g.setusage7("yes");
        }else {
            g.setusage7("no");
        }
        CheckBox usage8 = (CheckBox) getActivity().findViewById(R.id.usage8) ;
        if(usage8.isChecked()) {
            g.setusage8("yes");
        }else {
            g.setusage8("no");
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

        EditText stems=(EditText) getActivity().findViewById(R.id.stems);
        g.setstems(stems.getText().toString());
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
        /*EditText height=(EditText) getActivity().findViewById(R.id.height);
        g.setheight(height.getText().toString());
        EditText rcd = (EditText) getActivity().findViewById(R.id.RCD);
        g.setrcd(rcd.getText().toString());*/
        EditText dbh = (EditText) getActivity().findViewById(R.id.db_rc);
        g.setdbh(dbh.getText().toString());
        //get Gps get it from global
        //get Photo get it from global
        EditText notes = (EditText) getActivity().findViewById(R.id.notes);
        g.setnotes(notes.getText().toString());
        g.setuploaded("no");//set uploaded to no on insert
    }
}

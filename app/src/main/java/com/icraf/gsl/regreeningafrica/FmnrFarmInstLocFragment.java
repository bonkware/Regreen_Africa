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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrFarmInstLocFragment extends Fragment {
    private DbAccess dbAccess;
    public FmnrFarmInstLocFragment() {
        // Required empty public constructor
    }
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_tree_location, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();
        //validate check boxes
        final CheckBox owner = (CheckBox) view.findViewById(R.id.own);
        final CheckBox community = (CheckBox) view.findViewById(R.id.comm_land);
        final CheckBox govt = (CheckBox) view.findViewById(R.id.govt_land);
        final CheckBox mchurch_mosque = (CheckBox) view.findViewById(R.id.mosque_church);
        final CheckBox schools = (CheckBox) view.findViewById(R.id.schools);
        final CheckBox other = (CheckBox) view.findViewById(R.id.others);

        //proceed to plot information
        Button button_next = (Button) view.findViewById(R.id.tospeciesnumber);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tospeciesnumber:
                        if(owner.isChecked() || community.isChecked() || govt.isChecked() || mchurch_mosque.isChecked() || schools.isChecked() || other.isChecked()){
                            //do some validation
                            saveFmnrFarmerInst();//save inputs
                            dbAccess.insertFmnrFarmerInst();//insert details to db
                            g.setMultiplot(false);
                            Intent intent = new Intent(getActivity(), FmnrPlotMainActivity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        }
                        else{
                            Toast.makeText(FmnrFarmInstLocFragment.this.getActivity(),"Please select land ownership", Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //check if other is checked
        final CheckBox check_other = (CheckBox) view.findViewById(R.id.others) ;
        final EditText text=(EditText) view.findViewById(R.id.other_locations);
        check_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_other.isChecked()){
                    text.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
    public void  saveFmnrFarmerInst(){
        //generate unique id for farmer/institution
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);//random number generator
        String fid = "fgi_" + n;
        g.setfid(fid);
        //edittexts
        EditText e_name = (EditText) getActivity().findViewById(R.id.ename);
        g.setename(e_name.getText().toString());
        EditText date = (EditText) getActivity().findViewById(R.id.in_date);
        g.setin_date(date.getText().toString());

        Spinner survey_name = (Spinner) getActivity().findViewById(R.id.survey_name);
        //check if spinner is selected
        if(survey_name != null && survey_name.getSelectedItem() !=null ) {
            g.setfsurvey_name(survey_name.getSelectedItem().toString());
        }//added for survey project selection
        //EditText other_survey = (EditText) getActivity().findViewById(R.id.survey_other);
        //g.setfsurvey_name(other_survey.getText().toString());//added for other project option

        EditText farmer_institution_name = (EditText) getActivity().findViewById(R.id.fnames);
        g.setfname(farmer_institution_name.getText().toString());
        Spinner country = (Spinner) getActivity().findViewById(R.id.spinner1);
        //check if spinner is selected
        if(country != null && country.getSelectedItem() !=null ) {
            g.setcountry(country.getSelectedItem().toString());
        }
        EditText country_new = (EditText) getActivity().findViewById(R.id.country);//added for the new country
        g.setcountry(country_new.getText().toString());


        Spinner county_region = (Spinner) getActivity().findViewById(R.id.spinner2);
        //check if spinner is selected
        if(county_region != null && county_region.getSelectedItem() !=null ) {
            g.setcounty_region(county_region.getSelectedItem().toString());
        }
        EditText county_new = (EditText) getActivity().findViewById(R.id.county);//added for new county that is not on the list
        g.setcounty_region(county_new.getText().toString());

        /*EditText district = (EditText) getActivity().findViewById(R.id.district);
        g.setdistricts(district.getText().toString());*/
        Spinner district = (Spinner) getActivity().findViewById(R.id.spinner3);
        //check if spinner is selected
        if(district != null && district.getSelectedItem() !=null ) {
            g.setdistricts(district.getSelectedItem().toString());
        }

        EditText district_new = (EditText) getActivity().findViewById(R.id.district);//added for new district option
        g.setdistricts(district_new.getText().toString());

       /* RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.planting_location);
        //check whether it is checked
        if(radioGroup.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FarmerDetails.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton select = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setselect_location(select.getText().toString());
        }*/
        CheckBox owner = (CheckBox) getActivity().findViewById(R.id.own);
        if(owner.isChecked()) {
            g.setindividual_ownership("yes");
        }else {
            g.setindividual_ownership("no");
        }
        CheckBox community = (CheckBox) getActivity().findViewById(R.id.comm_land);
        if(community.isChecked()) {
            g.setcommunity_ownership("yes");
        }else {
            g.setcommunity_ownership("no");
        }
        CheckBox govt = (CheckBox) getActivity().findViewById(R.id.govt_land);
        if(govt.isChecked()) {
            g.setgovt_land_ownership("yes");
        }else {
            g.setgovt_land_ownership("no");
        }

        CheckBox mchurch_mosque = (CheckBox) getActivity().findViewById(R.id.mosque_church);
        if(mchurch_mosque.isChecked()) {
            g.setmosque_church_ownership("yes");
        }else {
            g.setmosque_church_ownership("no");
        }

        CheckBox schools = (CheckBox) getActivity().findViewById(R.id.schools);
        if(schools.isChecked()) {
            g.setschools_ownership("yes");
        }else {
            g.setschools_ownership("no");
        }
        EditText n_type=(EditText) getActivity().findViewById(R.id.other_locations);
        g.setother_ownership(n_type.getText().toString());

        g.setuploaded("no");//set uploaded to no on insert
        g.setmodule("FMNR");//set which module is this on insert
    }
}

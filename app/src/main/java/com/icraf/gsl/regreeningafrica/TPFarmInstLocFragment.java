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

import java.util.Random;

/**
 * Created by benard on 1/18/19.
 *
 */

public class TPFarmInstLocFragment extends Fragment {
    private DbAccess dbAccess;
    public TPFarmInstLocFragment() {
        // Required empty public constructor
    }
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tree_planting_location, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //proceed to polygon land size recording
        Button button_next = (Button) view.findViewById(R.id.toPlotinfo);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.toPlotinfo:
                        saveFarmerInst();//save
                        dbAccess.insertFarmerInst();//insert details to db
                        g.setMultiplot(false);//set it true
                        //Intent intent = new Intent(getActivity(), TPCohortMainAcivity.class);
                        Intent intent = new Intent(getActivity(), TPPlotMainActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //check if other is clicked
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
    public void saveFarmerInst() {
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
        if (survey_name != null && survey_name.getSelectedItem() != null) {
            g.settpsurvey_name(survey_name.getSelectedItem().toString());
        }//added for survey project selection
        //EditText other_survey = (EditText) getActivity().findViewById(R.id.survey_other);
        //g.settpsurvey_name(other_survey.getText().toString());//added for other project option

        EditText farmer_institution_name = (EditText) getActivity().findViewById(R.id.fnames);
        g.setfname(farmer_institution_name.getText().toString());
        Spinner country = (Spinner) getActivity().findViewById(R.id.spinner1);
        //check if spinner is selected
        if (country != null && country.getSelectedItem() != null) {
            g.setcountry(country.getSelectedItem().toString());
        }
        EditText country_new = (EditText) getActivity().findViewById(R.id.country);//added for the new country
        g.setcountry(country_new.getText().toString());
         /*EditText county_region = (EditText) getActivity().findViewById(R.id.county);
        g.setcounty_region(county_region.getText().toString());*/
        Spinner county_region = (Spinner) getActivity().findViewById(R.id.spinner2);
        //check if spinner is selected
        if (county_region != null && county_region.getSelectedItem() != null) {
            g.setcounty_region(county_region.getSelectedItem().toString());
        }
        EditText county_new = (EditText) getActivity().findViewById(R.id.county);//added for new county that is not on the list
        g.setcounty_region(county_new.getText().toString());

        /*EditText district = (EditText) getActivity().findViewById(R.id.district);
        g.setdistricts(district.getText().toString());*/
        Spinner district = (Spinner) getActivity().findViewById(R.id.spinner3);
        //check if spinner is selected
        if (district != null && district.getSelectedItem() != null) {
            g.setdistricts(district.getSelectedItem().toString());
        }
        EditText district_new = (EditText) getActivity().findViewById(R.id.district);//added for new district option
        g.setdistricts(district_new.getText().toString());
        /*RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.planting_location);
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
        if (owner.isChecked()) {
            g.setindividual_ownership("yes");
        } else {
            g.setindividual_ownership("no");
        }
        CheckBox community = (CheckBox) getActivity().findViewById(R.id.comm_land);
        if (community.isChecked()) {
            g.setcommunity_ownership("yes");
        } else {
            g.setcommunity_ownership("no");
        }
        CheckBox govt = (CheckBox) getActivity().findViewById(R.id.govt_land);
        if (govt.isChecked()) {
            g.setgovt_land_ownership("yes");
        } else {
            g.setgovt_land_ownership("no");
        }

        CheckBox mchurch_mosque = (CheckBox) getActivity().findViewById(R.id.mosque_church);
        if (mchurch_mosque.isChecked()) {
            g.setmosque_church_ownership("yes");
        } else {
            g.setmosque_church_ownership("no");
        }

        CheckBox schools = (CheckBox) getActivity().findViewById(R.id.schools);
        if (schools.isChecked()) {
            g.setschools_ownership("yes");
        } else {
            g.setschools_ownership("no");
        }
        EditText n_type = (EditText) getActivity().findViewById(R.id.other_locations);
        g.setother_ownership(n_type.getText().toString());

        /*RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.planting_site);
        //check whether it is checked
        if(group.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FarmerDetails.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = group.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton select = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setselect_site(select.getText().toString());
        }*/
        /*RadioGroup crop = (RadioGroup) getActivity().findViewById(R.id.crop);
        //check whether it is checked
        if(crop.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FmnrFarmInstLandsizeFragment.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = crop.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton select = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setcrops(select.getText().toString());//yes or no if there are crops
        }
        EditText croplist = (EditText) getActivity().findViewById(R.id.crops);
        g.setcroplist(croplist.getText().toString());//crop or list of crops

        EditText regreen_size = (EditText) getActivity().findViewById(R.id.landestimate);
        g.setlandsize(regreen_size.getText().toString());

        Spinner units = (Spinner) getActivity().findViewById(R.id.units);
        //check if spinner is selected
        if (units != null && units.getSelectedItem() != null) {
            g.setunits(units.getSelectedItem().toString());
        }*/
        g.setuploaded("no");//set uploaded to no on insert
        g.setmodule("TreePlanting");//set which module is this on insert
    }
}

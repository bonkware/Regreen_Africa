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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Random;

/**
 * Created by benard on 1/18/19.
 */

public class TPFarmInstLandsizeFragment extends Fragment {
    private DbAccess dbAccess;
    public TPFarmInstLandsizeFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_size_regreen, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //proceed to polygon land size recording
        Button button_next = (Button) view.findViewById(R.id.tocohort);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tocohort:
                        saveFarmerInst();//save
                        dbAccess.insertFarmerInst();//insert details to db
                        //Intent intent = new Intent(getActivity(), TPCohortMainAcivity.class);
                        Intent intent = new Intent(getActivity(), TPLandSizeMainActivity.class);
                        startActivity(intent);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        return view;
    }
    // save farmer/institution data when you go next
    public void  saveFarmerInst(){
        //generate unique id for farmer/institution
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);//random number generator
        String fid = "farm_Insti_" + n;
        g.setfid(fid);
        //edittexts
        EditText e_name = (EditText) getActivity().findViewById(R.id.ename);
        g.setename(e_name.getText().toString());
        EditText date = (EditText) getActivity().findViewById(R.id.in_date);
        g.setin_date(date.getText().toString());
        EditText farmer_institution_name = (EditText) getActivity().findViewById(R.id.fnames);
        g.setfname(farmer_institution_name.getText().toString());
        Spinner country = (Spinner) getActivity().findViewById(R.id.spinner1);
        //check if spinner is selected
        if(country != null && country.getSelectedItem() !=null ) {
            g.setcountry(country.getSelectedItem().toString());
        }
        EditText county_region = (EditText) getActivity().findViewById(R.id.county);
        //if(county_region != null && county_region.getSelectedItem() !=null ) {
            g.setcounty_region(county_region.getText().toString());
       // }
        EditText district = (EditText) getActivity().findViewById(R.id.district);
        //if(district != null && district.getSelectedItem() !=null ) {
            g.setdistricts(district.getText().toString());
        //}
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

        EditText regreen_size = (EditText) getActivity().findViewById(R.id.landestimate);
        g.setlandsize(regreen_size.getText().toString());

    }
}

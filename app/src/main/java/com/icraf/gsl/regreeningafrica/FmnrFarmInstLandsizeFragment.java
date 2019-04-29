package com.icraf.gsl.regreeningafrica;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrFarmInstLandsizeFragment extends Fragment {
    private DbAccess dbAccess;
    Button btnDatePicker,btnDatePicker1;
    EditText txtDate,txtDate1;
    private int mYear, mMonth, mDay;
    public FmnrFarmInstLandsizeFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_land_size_regreen, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //proceed to land size polygon
        Button button_next = (Button) view.findViewById(R.id.topolygon);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.topolygon:
                        saveFmnrFarmerInst();//save
                        dbAccess.insertFmnrFarmerInst();//insert details to db
                        Intent intent = new Intent(getActivity(), FmnrLandSizeMainActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //if other option is selected
        //if other is selected
        final Spinner s1 = (Spinner)view.findViewById(R.id.units);
        final EditText text1=(EditText) view.findViewById(R.id.other_units);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String sp1= String.valueOf(s1.getSelectedItem());
                if(sp1.contentEquals("Other")) {
                    text1.setVisibility(View.VISIBLE);  //if other is chosen then show
                }
                else {
                    text1.setVisibility(View.GONE); //otherwise hide
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end of spinner selection 1
        //set date the farmer started the FMNR
        btnDatePicker=(Button)view.findViewById(R.id.btn_date);
        txtDate=(EditText)view.findViewById(R.id.fmnr_date);

        //btnDatePicker.setOnClickListener(this);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //call photo on button click
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                txtDate.setText((monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                // hide future dates
                //check if calendar is greater than current date
                if (c.getTimeInMillis() < System.currentTimeMillis()) {
                    // Toast.makeText(getApplicationContext(),"Current time is big",Toast.LENGTH_SHORT).show();
                    datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                } else {
                    //  Toast.makeText(getApplicationContext(),"Current time is small",Toast.LENGTH_SHORT).show();
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                }
                //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        return view;
    }
    // save farmer/institution data when you go next
    public void  saveFmnrFarmerInst(){
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

        Spinner survey_name = (Spinner) getActivity().findViewById(R.id.survey_name);
        //check if spinner is selected
        if(survey_name != null && survey_name.getSelectedItem() !=null ) {
            g.setsurvey_name(survey_name.getSelectedItem().toString());
        }//added for survey project selection
        EditText other_survey = (EditText) getActivity().findViewById(R.id.survey_other);
        g.setsurvey_name(other_survey.getText().toString());//added for other project option

        EditText farmer_institution_name = (EditText) getActivity().findViewById(R.id.fnames);
        g.setfname(farmer_institution_name.getText().toString());
        Spinner country = (Spinner) getActivity().findViewById(R.id.spinner1);
        //check if spinner is selected
        if(country != null && country.getSelectedItem() !=null ) {
            g.setcountry(country.getSelectedItem().toString());
        }
        /*EditText county_region = (EditText) getActivity().findViewById(R.id.county);
        g.setcounty_region(county_region.getText().toString());*/
        Spinner county_region = (Spinner) getActivity().findViewById(R.id.spinner2);
        //check if spinner is selected
        if(county_region != null && county_region.getSelectedItem() !=null ) {
            g.setcounty_region(county_region.getSelectedItem().toString());
        }

        /*EditText district = (EditText) getActivity().findViewById(R.id.district);
        g.setdistricts(district.getText().toString());*/
        Spinner district = (Spinner) getActivity().findViewById(R.id.spinner3);
        //check if spinner is selected
        if(district != null && district.getSelectedItem() !=null ) {
            g.setdistricts(district.getSelectedItem().toString());
        }

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

        RadioGroup sn = (RadioGroup) getActivity().findViewById(R.id.species_number);
        //check whether it is checked
        if(sn.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FarmerDetails.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = sn.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton species_number = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setspecies_number(species_number.getText().toString());
        }
        //get photo from global
        EditText fmnr_date = (EditText) getActivity().findViewById(R.id.fmnr_date);
        g.setfmnr_date(fmnr_date.getText().toString());

        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.fenced);
        //check whether it is checked
        if(group.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FmnrFarmInstLandsizeFragment.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = group.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton select = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setfmnr_fenced(select.getText().toString());
        }
        EditText regreen_size = (EditText) getActivity().findViewById(R.id.landestimate);
        g.setlandsize(regreen_size.getText().toString());

        Spinner units = (Spinner) getActivity().findViewById(R.id.units);
        //check if spinner is selected
        if(units != null && units.getSelectedItem() !=null ) {
            g.setunits(units.getSelectedItem().toString());
        }
        EditText other_units = (EditText) getActivity().findViewById(R.id.other_units);
        g.setunits(other_units.getText().toString());//added for other option
    }
}

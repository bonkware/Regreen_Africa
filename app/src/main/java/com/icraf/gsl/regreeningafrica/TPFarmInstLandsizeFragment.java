package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by benard on 1/18/19.
 *
 */

public class TPFarmInstLandsizeFragment extends Fragment {
    private DbAccess dbAccess;
    //add items to spinner
    Spinner SPINNER;
    Button ADD;
    EditText EDITTEXT;
    String[] spinnerItems = new String[]{
            "Select unit",
            "Hectares",
            "Acres"

    };
    String GETTEXT;
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;//end of spinner items

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
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //add item to spinner from edittext
        SPINNER = (Spinner) view.findViewById(R.id.units);
        ADD = (Button) view.findViewById(R.id.button1);
        EDITTEXT = (EditText) view.findViewById(R.id.editText1);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));
        arrayadapter = new ArrayAdapter<String>(TPFarmInstLandsizeFragment.this.getActivity(), R.layout.textview, stringlist);
        arrayadapter.setDropDownViewResource(R.layout.textview);
        SPINNER.setAdapter(arrayadapter);

        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                GETTEXT = EDITTEXT.getText().toString();
                stringlist.add(GETTEXT);
                arrayadapter.notifyDataSetChanged();
                Toast.makeText(TPFarmInstLandsizeFragment.this.getActivity(), "Item Added", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    // save farmer/institution data when you go next
    public void saveFarmerInst() {
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
         /*EditText county_region = (EditText) getActivity().findViewById(R.id.county);
        g.setcounty_region(county_region.getText().toString());*/
        Spinner county_region = (Spinner) getActivity().findViewById(R.id.spinner2);
        //check if spinner is selected
        if (county_region != null && county_region.getSelectedItem() != null) {
            g.setcounty_region(county_region.getSelectedItem().toString());
        }

        /*EditText district = (EditText) getActivity().findViewById(R.id.district);
        g.setdistricts(district.getText().toString());*/
        Spinner district = (Spinner) getActivity().findViewById(R.id.spinner3);
        //check if spinner is selected
        if (district != null && district.getSelectedItem() != null) {
            g.setdistricts(district.getSelectedItem().toString());
        }
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

        EditText regreen_size = (EditText) getActivity().findViewById(R.id.landestimate);
        g.setlandsize(regreen_size.getText().toString());

        Spinner units = (Spinner) getActivity().findViewById(R.id.units);
        //check if spinner is selected
        if (units != null && units.getSelectedItem() != null) {
            g.setunits(units.getSelectedItem().toString());
        }
        //EditText other_units = (EditText) getActivity().findViewById(R.id.other_units);
        //g.setunits(other_units.getText().toString());//added for other project option

    }
}

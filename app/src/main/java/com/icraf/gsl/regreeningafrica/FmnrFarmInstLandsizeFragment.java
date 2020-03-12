package com.icraf.gsl.regreeningafrica;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
    public FmnrFarmInstLandsizeFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_land_size_regreen, container,
                false);

        final EditText date = (EditText) view.findViewById(R.id.fmnr_date);
        final EditText ed = (EditText) view.findViewById(R.id.landestimate);
        final Spinner unit = (Spinner) view.findViewById(R.id.units);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //get fid
        String farmer_id = g.getfid();
        TextView fid = (TextView) view.findViewById(R.id.fid);
        fid.setText(farmer_id);

        //for previous/back button
        /*final Button button_prev = (Button) view.findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g.getMultiplot()==true) {
                    Intent intent = new Intent(getActivity(), Select_Farmer_Institution_FMNR.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    button_prev.setEnabled(false);
                    //Intent intent = new Intent(getActivity(), TPFarmInstiMainActivity.class);
                    //startActivity(intent);
                    //getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }

            }
        });*/

        //proceed to land size polygon
        Button button_next = (Button) view.findViewById(R.id.topolygon);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.topolygon:
                        //validate before going next
                        boolean fail = false;
                        if (ed.getText().toString().trim().length() == 0) {
                            fail = true;
                            ed.requestFocus();
                            ed.setError("Enter land estimate");
                        }
                        if (date.getText().toString().trim().length() == 0) {
                            fail = true;
                            date.requestFocus();
                            date.setError("Select date");
                        }
                        if (unit.getSelectedItem().toString().trim().equals("Select unit")) {//validate the spinner not
                            fail = true;
                            unit.requestFocus();
                            TextView errorText = (TextView) unit.getSelectedView();
                            errorText.setError("");
                            errorText.setTextColor(Color.RED);//just to highlight that this is an error
                            errorText.setText("Please select the unit");//changes the selected item text to this
                        }
                        if (!fail) {
                        saveFmnrPlotinfo();//save inputs
                        plotid();//generate plot id for polygons
                        dbAccess.insertFmnrPlotInfo();//insert fmnr plot info details to db
                        //g.setMultiplot(false);
                        Intent intent = new Intent(getActivity(), FmnrLandSizeMainActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                }
                        break;
                }
            }
        });

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
        //add item to spinner from edittext
        SPINNER = (Spinner)view.findViewById(R.id.units);
        ADD = (Button)view.findViewById(R.id.button1);
        EDITTEXT = (EditText)view.findViewById(R.id.editText1);

        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));

        arrayadapter = new ArrayAdapter<String>(FmnrFarmInstLandsizeFragment.this.getActivity(),R.layout.textview,stringlist );

        arrayadapter.setDropDownViewResource(R.layout.textview);

        SPINNER.setAdapter(arrayadapter);

        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GETTEXT = EDITTEXT.getText().toString();

                stringlist.add(GETTEXT);

                arrayadapter.notifyDataSetChanged();

                Toast.makeText(FmnrFarmInstLandsizeFragment.this.getActivity(), "Item Added", Toast.LENGTH_LONG).show();
            }
        });
        //if yes radio button yes is clicked show edit text
        final RadioButton yes_crop = (RadioButton) view.findViewById(R.id.yes_crop) ;
        final RadioButton no_crop = (RadioButton) view.findViewById(R.id.no_crop) ;
        final EditText txtcrops=(EditText)view.findViewById(R.id.crops);
        yes_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yes_crop.isChecked()){
                    txtcrops.setVisibility(View.VISIBLE);
                }
            }
        });
        no_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(no_crop.isChecked()){
                    txtcrops.setVisibility(View.GONE);
                }
            }
        });//end

        return view;
    }
    // save farmer/institution data when you go next
    public void  saveFmnrPlotinfo(){
        //get unique id for farmer/institution
        TextView fid = (TextView) getActivity().findViewById(R.id.fid);
        g.setfid(fid.getText().toString());
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
        RadioGroup crop = (RadioGroup) getActivity().findViewById(R.id.crop);
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
            g.setcrops(select.getText().toString());
        }
        EditText croplist = (EditText) getActivity().findViewById(R.id.crops);
        g.setcroplist(croplist.getText().toString());//crop or list of crops
        EditText regreen_size = (EditText) getActivity().findViewById(R.id.landestimate);
        g.setlandsize(regreen_size.getText().toString());

        Spinner units = (Spinner) getActivity().findViewById(R.id.units);
        //check if spinner is selected
        if(units != null && units.getSelectedItem() !=null ) {
            g.setunits(units.getSelectedItem().toString());
        }
        g.setuploaded("no");//set uploaded to no on insert
        //g.setmodule("FMNR");//set which module is this on insert
    }
    //setting plot id for polygons
    public void plotid(){
        //generate unique id for farmer/institution
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);//random number generator
        String pid = "plot_" + n;
        g.setpid(pid);
    }
}

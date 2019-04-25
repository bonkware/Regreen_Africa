package com.icraf.gsl.regreeningafrica;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by benard on 2/22/19.
 */
public class NurseryOtherFragment extends Fragment {
    Button btnDatePicker,btnDatePicker1;
    EditText txtDate,txtDate1;
    private int mYear, mMonth, mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_other_info_fragment, container,
                false);
        //set date the data was collected
        btnDatePicker=(Button)view.findViewById(R.id.btn_date);
        txtDate=(EditText)view.findViewById(R.id.date_sown);

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

                                txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                //txtDate.setText((monthOfYear + 1) + "/" + year);

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
        //compare if total number of servived not to be more than the survived
        final EditText germ  = (EditText)view.findViewById(R.id.germinated);
        final EditText surv  = (EditText)view.findViewById(R.id.survived);

        surv.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                //

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //check if the number of trees survived is more than the planted
                // Toast.makeText(getActivity(),"Message" + new String(c.toString()), Toast.LENGTH_SHORT).show();
                try {
                    int t = Integer.valueOf(germ.getText().toString());
                    int m = Integer.valueOf(surv.getText().toString());

                    if (m > t) {
                        // myEditText2.setText("");  //this automatically sets the editText2 field back to empty
                        surv.setText("");
                        surv.setError("Cannot be more than the germinated number " + t);
                        //Toast.makeText(getActivity(), "Cannot be more than " + t, Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e){
                    //handle
                    //Toast.makeText(getActivity(),"Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });//end of text watcher1

        //if unit other is selected
        final Spinner s1 = (Spinner)view.findViewById(R.id.units);
        final EditText text1=(EditText) view.findViewById(R.id.unit_other);
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
        //if other is selected
        final Spinner s2 = (Spinner)view.findViewById(R.id.unit_sown);
        final EditText text2=(EditText) view.findViewById(R.id.unitsown_other);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String sp2= String.valueOf(s2.getSelectedItem());
                if(sp2.contentEquals("Other")) {
                    text2.setVisibility(View.VISIBLE);  //if other is chosen then show
                }
                else {
                    text2.setVisibility(View.GONE); //otherwise hide
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end of spinner selection 1
        return view;
    }
}


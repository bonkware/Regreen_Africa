package com.icraf.gsl.regreeningafrica;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrFarmInstEnumFragment extends Fragment {
    Spinner s1;
    Button btnDatePicker,btnDatePicker1;
    EditText txtDate,txtDate1;
    private int mYear, mMonth, mDay;
    //add items to spinner
    Spinner SPINNER;
    Button ADD;
    EditText EDITTEXT;
    String[] spinnerItems = new String[]{
            "Select project",
            "Regreening Africa",
            "Dry Dev",
            "USAID-Ethiopia"

    };
    String GETTEXT;
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;//end of spinner items
    public FmnrFarmInstEnumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_enumurator_fragment, container,
                false);
        //set date the data was collected
        btnDatePicker=(Button)view.findViewById(R.id.btn_date);
        txtDate=(EditText)view.findViewById(R.id.in_date);

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
                // hide previous dates
                //check if calendar is greater than current date
                if (c.getTimeInMillis() < System.currentTimeMillis()) {
                    // Toast.makeText(getApplicationContext(),"Current time is big",Toast.LENGTH_SHORT).show();
                    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                } else {
                    //  Toast.makeText(getApplicationContext(),"Current time is small",Toast.LENGTH_SHORT).show();
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                }
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        //for previous/back button
        Button button_next = (Button) view.findViewById(R.id.prev);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.prev:
                        Intent intent = new Intent(getActivity(), SelectSurvey.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //spinner add item
        SPINNER = (Spinner)view.findViewById(R.id.survey_name);
        ADD = (Button)view.findViewById(R.id.button1);
        EDITTEXT = (EditText)view.findViewById(R.id.editText1);

        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));

        arrayadapter = new ArrayAdapter<String>(FmnrFarmInstEnumFragment.this.getActivity(),R.layout.textview,stringlist );

        arrayadapter.setDropDownViewResource(R.layout.textview);

        SPINNER.setAdapter(arrayadapter);

        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(EDITTEXT.getText().toString().equals("")) {
                    Toast.makeText(FmnrFarmInstEnumFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                }
                else{
                    GETTEXT = EDITTEXT.getText().toString();
                    stringlist.add(GETTEXT);
                    arrayadapter.notifyDataSetChanged();
                    Toast.makeText(FmnrFarmInstEnumFragment.this.getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}

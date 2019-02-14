package com.example.benard.regreeningafrica;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by benard on 1/18/19.
 *
 */

public class TPFarmInstEnumFragment extends Fragment {
    Button btnDatePicker,btnDatePicker1;
    EditText txtDate,txtDate1;
    private int mYear, mMonth, mDay;
    public TPFarmInstEnumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enumurator, container,
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
                datePickerDialog.show();

            }
        });

        return view;
    }
}

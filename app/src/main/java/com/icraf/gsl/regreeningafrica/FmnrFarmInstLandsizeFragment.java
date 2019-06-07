package com.icraf.gsl.regreeningafrica;

import android.app.DatePickerDialog;
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
}

package com.icraf.gsl.regreeningafrica;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by benard on 2/22/19.
 */
public class NurseryOtherFragment extends Fragment {
    Button btnDatePicker,btnDatePicker1;
    EditText txtDate,txtDate1;
    private int mYear, mMonth, mDay;

    //add items to spinner
    Spinner SPINNER;
    Button ADD;
    EditText EDITTEXT;
    String[] spinnerItems = new String[]{
            "Select unit",
            "Kgs",
            "Grams",
            "Count"

    };
    String GETTEXT;
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;//end of spinner items
    //add items to spinner2
    Spinner SPINNER2;
    Button ADD2;
    EditText EDITTEXT2;
    String[] spinnerItems2 = new String[]{
            "Select unit",
            "Kgs",
            "Grams",
            "Count"

    };
    String GETTEXT2;
    List<String> stringlist2;
    ArrayAdapter<String> arrayadapter2;//end of spinner2 items
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

        //add item to spinner from edittext
        SPINNER = (Spinner)view.findViewById(R.id.spinner_units);
        ADD = (Button)view.findViewById(R.id.button1);
        EDITTEXT = (EditText)view.findViewById(R.id.editText1);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));
        arrayadapter = new ArrayAdapter<String>(NurseryOtherFragment.this.getActivity(),R.layout.textview,stringlist );
        arrayadapter.setDropDownViewResource(R.layout.textview);
        SPINNER.setAdapter(arrayadapter);
        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (EDITTEXT.getText().toString().equals("")) {
                    Toast.makeText(NurseryOtherFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                } else {
                    GETTEXT = EDITTEXT.getText().toString();
                    stringlist.add(GETTEXT);
                    arrayadapter.notifyDataSetChanged();
                    Toast.makeText(NurseryOtherFragment.this.getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                }
            }
        });
        //add item to spinner2 from edittext
        SPINNER2 = (Spinner)view.findViewById(R.id.spinner_unitsown);
        ADD2 = (Button)view.findViewById(R.id.button2);
        EDITTEXT2 = (EditText)view.findViewById(R.id.editText2);
        stringlist2 = new ArrayList<>(Arrays.asList(spinnerItems));
        arrayadapter2 = new ArrayAdapter<String>(NurseryOtherFragment.this.getActivity(),R.layout.textview,stringlist2 );
        arrayadapter2.setDropDownViewResource(R.layout.textview);
        SPINNER2.setAdapter(arrayadapter2);
        ADD2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (EDITTEXT2.getText().toString().equals("")) {
                    Toast.makeText(NurseryOtherFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                } else {
                    GETTEXT2 = EDITTEXT2.getText().toString();
                    stringlist2.add(GETTEXT2);
                    arrayadapter2.notifyDataSetChanged();
                    Toast.makeText(NurseryOtherFragment.this.getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}


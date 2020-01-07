package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by benard on 1/18/19.
 */

public class FmnrSpeciesFragment extends Fragment {
    Button btnDatePicker,btnDatePicker1;
    EditText txtDate,txtDate1;
    private int mYear, mMonth, mDay;
    public FmnrSpeciesFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_species, container,
                false);

        //get fid
        String farmer_id = g.getfid();
        TextView fid = (TextView) view.findViewById(R.id.fid);
        fid.setText(farmer_id);
        return view;
    }
}

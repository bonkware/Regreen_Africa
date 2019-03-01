package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrFarmInstLocFragment extends Fragment {
    public FmnrFarmInstLocFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_tree_location, container,
                false);

        //check if other is checked
        final RadioButton radio_other = (RadioButton) view.findViewById(R.id.others) ;
        final EditText text=(EditText) view.findViewById(R.id.other_locations);

        radio_other.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(radio_other.isChecked()){
                    text.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}

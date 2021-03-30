package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by benard on 1/18/19.
 */

public class TPFarmInstSiteFragment extends Fragment {
    public TPFarmInstSiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tree_planting_site, container,
                false);

        //check if other is checked
        final CheckBox radio_other = (CheckBox) view.findViewById(R.id.others) ;
        final EditText text=(EditText) view.findViewById(R.id.other_sites);

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

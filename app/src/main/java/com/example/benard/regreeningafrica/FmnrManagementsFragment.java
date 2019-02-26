package com.example.benard.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrManagementsFragment extends Fragment {
    public FmnrManagementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_tree_management, container,
                false);

        //check for other
        final CheckBox check = (CheckBox) view.findViewById(R.id.mg_others) ;
        final EditText text=(EditText) view.findViewById(R.id.mg_other);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(check.isChecked()){
                    text.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}

package com.example.benard.regreeningafrica;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by benard on 2/20/19.
 * contains nursery type selection information
 */

public class NurseryTypeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_type_fragment, container,
                false);

        //check if other is checked
        final CheckBox check_other = (CheckBox) view.findViewById(R.id.others) ;
        final EditText text=(EditText) view.findViewById(R.id.other_types);
        check_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_other.isChecked()){
                    text.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }
}

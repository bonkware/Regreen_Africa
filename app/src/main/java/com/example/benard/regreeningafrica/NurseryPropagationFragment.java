package com.example.benard.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

/**
 * Created by benard on 2/22/19.
 *
 */
public class NurseryPropagationFragment extends Fragment {
    CheckBox other_graft_source,other_seed_source,seed_option, graft_option;
    TableLayout seed,graft;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_propagation_fragment, container,
                false);
        //for source of seeds
        other_seed_source = (CheckBox) view.findViewById(R.id.other_sources) ;
        final EditText other_seed=(EditText) view.findViewById(R.id.other_seed_sources);
        other_seed_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(other_seed_source.isChecked()){
                    other_seed.setVisibility(View.VISIBLE);
                }else{
                    other_seed.setVisibility(View.GONE);
                }
            }
        });

        //for other source of graft/scion
        other_graft_source = (CheckBox) view.findViewById(R.id.other_graft) ;
        final EditText other_graft=(EditText) view.findViewById(R.id.other_graft_sources);
        other_graft_source.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(other_graft_source.isChecked()){
                    other_graft.setVisibility(View.VISIBLE);
                }else{
                    other_graft.setVisibility(View.GONE);
                }
            }
        });

        //if seed is selected  then show this options
        seed_option = (CheckBox) view.findViewById(R.id.seed) ;
        seed = (TableLayout) view.findViewById(R.id.sources) ;

        seed_option.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seed_option.isChecked()){
                    seed.setVisibility(View.VISIBLE);
                }else{
                    seed.setVisibility(View.GONE);
                }
            }
        });

        //if graft is selected  then show this options
        graft_option = (CheckBox) view.findViewById(R.id.graft) ;
        graft = (TableLayout) view.findViewById(R.id.grafted) ;

        graft_option.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(graft_option.isChecked()){
                    graft.setVisibility(View.VISIBLE);
                }else{
                    graft.setVisibility(View.GONE);
                }
            }
        });


        return view;
    }
}


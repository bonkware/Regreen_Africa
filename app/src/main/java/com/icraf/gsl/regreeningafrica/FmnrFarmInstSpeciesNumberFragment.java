package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrFarmInstSpeciesNumberFragment extends Fragment {
    public FmnrFarmInstSpeciesNumberFragment() {
        // Required empty public constructor
    }
    Button button_prev;
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_species_number, container,
                false);

        //for previous/back button
        button_prev = (Button) view.findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g.getMultiplot()) {
                    Intent intent = new Intent(getActivity(), Select_Farmer_Institution_FMNR.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    //button_prev.setEnabled(false);
                    Intent intent = new Intent(getActivity(), FmnrFarmInstMainActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }

            }
        });
        return view;
    }
}

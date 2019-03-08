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

public class FmnrFarmInstSpeciesNumberFragment extends Fragment {
    public FmnrFarmInstSpeciesNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_species_number, container,
                false);


        return view;
    }
}

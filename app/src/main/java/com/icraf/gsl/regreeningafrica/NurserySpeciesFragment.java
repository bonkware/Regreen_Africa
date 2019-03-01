package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by benard on 2/22/19.
 */
public class NurserySpeciesFragment extends Fragment {
    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_species_fragment, container,
                false);
        //get fid
        String nid = g.getnid();
        TextView nurseryid = (TextView) view.findViewById(R.id.nid);
        nurseryid.setText(nid);
        return view;
    }
}


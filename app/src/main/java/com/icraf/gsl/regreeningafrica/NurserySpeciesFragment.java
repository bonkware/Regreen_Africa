package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
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

        //for previous/back button
        /*Button button_next = (Button) view.findViewById(R.id.prev);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.prev:
                        //Intent intent = new Intent(getActivity(), NurseryInfoMain.class);
                        //startActivity(intent);
                        getActivity().onBackPressed();
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/
        return view;
    }
}


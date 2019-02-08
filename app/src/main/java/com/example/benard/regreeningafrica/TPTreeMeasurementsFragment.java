package com.example.benard.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by benard on 1/18/19.
 */

public class TPTreeMeasurementsFragment extends Fragment {
    public TPTreeMeasurementsFragment() {
        // Required empty public constructor
    }
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tree_measurements, container,
                false);


        final LinearLayout r1=(LinearLayout) view.findViewById(R.id.rcd_);
        //final LinearLayout   r2=(LinearLayout)view.findViewById(R.id.height_);
        final LinearLayout   r3=(LinearLayout)view.findViewById(R.id.dbh_);

        // Add edittext + the text watcher
        final EditText height  = (EditText)view.findViewById(R.id.height);

        height.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
               //Clear text when the edittext value is empty
                if (c.toString().length() == 0) {
                    r1.setVisibility(View.GONE);
                    r3.setVisibility(View.GONE);
                    height.setError("Enter the height");
                }

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // if height is more less than 3 then show the RCD layout otherwise show dbh
               // Toast.makeText(getActivity(),"Message" + new String(c.toString()), Toast.LENGTH_SHORT).show();
                try {
                    if (Integer.valueOf(c.toString().trim()) <= 3) {
                        r1.setVisibility(View.VISIBLE);
                        r3.setVisibility(View.GONE);
                    } else {
                        r3.setVisibility(View.VISIBLE);
                        r1.setVisibility(View.GONE);
                    }
                } catch (NumberFormatException e){
                    //handle
                    //Toast.makeText(getActivity(),"Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });//end of text watcher

        //get fid
        String cohort_id = g.getcid();
        TextView cid = (TextView) view.findViewById(R.id.cid);
        cid.setText(cohort_id);

        return view;
    }
}

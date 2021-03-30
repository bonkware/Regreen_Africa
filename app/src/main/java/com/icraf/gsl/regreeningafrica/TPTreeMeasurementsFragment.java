package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

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

        //show dbh
        final Button b = (Button) view.findViewById(R.id.d_rcd1) ;
        final TextView d1 = (TextView) view.findViewById(R.id.d1);
        final TextView r1 = (TextView) view.findViewById(R.id.r1);
        final LinearLayout   dhr=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        final LinearLayout   dhr2=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show edittext
                dhr.setVisibility(View.VISIBLE);
                r1.setVisibility(View.VISIBLE);
                d1.setVisibility(View.GONE);


            }
        });
        //show rcd
        final Button b2 = (Button) view.findViewById(R.id.d_rcd2) ;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show edittext
                dhr2.setVisibility(View.VISIBLE);
                d1.setVisibility(View.VISIBLE);
                r1.setVisibility(View.GONE);

            }
        });
    /*    final LinearLayout r1=(LinearLayout) view.findViewById(R.id.rcd_);
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
*/
        //for the radio buttons change
        //check if other is checked
       /* final RadioButton less_one = (RadioButton) view.findViewById(R.id.less15) ;
        final LinearLayout   text=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        less_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(less_one.isChecked()){
                    text.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.GONE);
                }
            }
        });
        final RadioButton less_three = (RadioButton) view.findViewById(R.id.less3) ;
        final LinearLayout   text1=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        less_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(less_three.isChecked()){
                    text1.setVisibility(View.VISIBLE);
                }else{
                    text1.setVisibility(View.GONE);
                }
            }
        });
        final RadioButton more_three = (RadioButton) view.findViewById(R.id.more3) ;
        final LinearLayout   text2=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        more_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(more_three.isChecked()){
                    text2.setVisibility(View.VISIBLE);
                }else{
                    text2.setVisibility(View.GONE);
                }
            }
        });*/
        /*final Button less_three = (Button) view.findViewById(R.id.d_rcd) ;
        final LinearLayout   text1=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        less_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.d_rcd:
                        text1.setVisibility(View.VISIBLE);
                        break;
                    default:
                        text1.setVisibility(View.GONE);
                        break;
                }
            }
        });*/
        //get fid
        String cohort_id = g.getcid();
        TextView cid = (TextView) view.findViewById(R.id.cid);
        cid.setText(cohort_id);

        //check on radio buttons
        final RadioButton less_one = (RadioButton) view.findViewById(R.id.less15) ;
        //final LinearLayout   text=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        less_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(less_one.isChecked()){
                    b.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.GONE);
                    dhr.setVisibility(View.GONE);
                }else{
                    b2.setVisibility(View.GONE);
                    b.setVisibility(View.VISIBLE);
                    dhr.setVisibility(View.GONE);
                }
            }
        });
        final RadioButton less_three = (RadioButton) view.findViewById(R.id.less3) ;
        //final LinearLayout   text1=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        less_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(less_three.isChecked()){
                    b2.setVisibility(View.VISIBLE);
                    b.setVisibility(View.GONE);
                    dhr.setVisibility(View.GONE);
                }else{
                    b.setVisibility(View.GONE);
                    b2.setVisibility(View.VISIBLE);
                    dhr.setVisibility(View.GONE);
                }
            }
        });
        final RadioButton more_three = (RadioButton) view.findViewById(R.id.more3) ;
        //final LinearLayout   text2=(LinearLayout)view.findViewById(R.id.dbh_rcd);
        more_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(more_three.isChecked()){
                    b2.setVisibility(View.VISIBLE);
                    b.setVisibility(View.GONE);
                    dhr.setVisibility(View.GONE);
                }else{
                    b.setVisibility(View.GONE);
                    b2.setVisibility(View.VISIBLE);
                    dhr.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }
}

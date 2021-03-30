package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by benard on 2/22/19.
 */
public class NurseryMethodsFragment extends Fragment {
    CheckBox other;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_methods_fragment, container,
                false);

        //for other method of seedlings production
         other = (CheckBox) view.findViewById(R.id.other_method) ;
        final EditText method=(EditText) view.findViewById(R.id.method_other);
        other.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(other.isChecked()){
                    method.setVisibility(View.VISIBLE);
                }else{
                    method.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }
}


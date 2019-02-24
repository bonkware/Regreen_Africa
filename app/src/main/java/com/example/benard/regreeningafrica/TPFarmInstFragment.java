package com.example.benard.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benard on 1/18/19.
 */

public class TPFarmInstFragment extends Fragment {
    Spinner s1,s2,s3;
    public TPFarmInstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farmer_institution_details, container,
                false);
        //populating the spinner based on the selection of the first spinner
        s1 = (Spinner)view.findViewById(R.id.spinner1);
    /*    s2 = (Spinner)view.findViewById(R.id.spinner2);
        s3 = (Spinner)view.findViewById(R.id.spinner3);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String sp1= String.valueOf(s1.getSelectedItem());
               // Toast.makeText(getActivity(), sp1, Toast.LENGTH_SHORT).show();
                if(sp1.contentEquals("Kenya")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select County/Region");
                    list.add("Nairobi");
                    list.add("Mombasa");
                    list.add("Kisumu");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter);
                }
                if(sp1.contentEquals("Uganda")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select County/Region");
                    list.add("Kampala");
                    list.add("Entebbe");
                    list.add("Lira");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter2);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end of spinner selection 1
        //start Spinner selection 2
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String sp2= String.valueOf(s2.getSelectedItem());
               // Toast.makeText(getActivity(), sp2, Toast.LENGTH_SHORT).show();
                if(sp2.contentEquals("Nairobi")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-county/Village");
                    list.add("Kasarani");
                    list.add("Embakasi");
                    list.add("Makadara");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Mombasa")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Kisauni");
                    list.add("Jomvi");
                    list.add("Nyali");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter2);
                }
                if(sp2.contentEquals("Kampala")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Masaka");
                    list.add("Rubaga");
                    list.add("Nakawa");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter2);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end of spinner selection2
*/

        return view;
    }
}

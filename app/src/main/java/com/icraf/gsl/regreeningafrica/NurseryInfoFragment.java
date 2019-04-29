package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benard on 2/19/19.
 * contains nursery information
 */

public class NurseryInfoFragment extends Fragment {
    Spinner s1,s2,s3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_info_fragment, container,
                false);

        //populating the spinner based on the selection of the first spinner
        s1 = (Spinner)view.findViewById(R.id.spinner1);
        s2 = (Spinner)view.findViewById(R.id.spinner2);
        s3 = (Spinner)view.findViewById(R.id.spinner3);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String sp1= String.valueOf(s1.getSelectedItem());
                // Toast.makeText(getActivity(), sp1, Toast.LENGTH_SHORT).show();
                if(sp1.contentEquals("Kenya")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select County");
                    list.add("Marsabit");
                    list.add("Samburu");
                    list.add("Isiolo");
                    list.add("Laikipia");
                    list.add("Baringo");
                    list.add("Marakwet");
                    list.add("Nakuru");
                    list.add("Homa Bay");
                    list.add("Migori");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter);
                }
                if(sp1.contentEquals("Ethiopia")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Region");
                    list.add("SNNPR");
                    list.add("Oromia");
                    list.add("Amharaa");
                    list.add("Tigray");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter2);
                }
                if(sp1.contentEquals("Rwanda")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Region");
                    list.add("Eastern");
                    ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter3.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter3);
                }
                if(sp1.contentEquals("Somalia")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select State");
                    list.add("Somaliland");
                    list.add("Puntland");
                    ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter4.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter4);
                }
                if(sp1.contentEquals("Niger")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Department");
                    list.add("Tillabéri");
                    ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter5.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter5);
                }
                if(sp1.contentEquals("Ghana")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Regions");
                    list.add("Upper East");
                    list.add("Northern");
                    ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter6.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter6);
                }
                if(sp1.contentEquals("Senegal")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Regions");
                    list.add("Kaffrine");
                    list.add("Kaolack");
                    list.add("Fatick");
                    ArrayAdapter<String> dataAdapter7 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter7.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter7);
                }
                if(sp1.contentEquals("Mali")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Regions");
                    list.add("Sikasso");
                    list.add("Segou");
                    ArrayAdapter<String> dataAdapter8 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter8.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter8);
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
                //Kenya starts
                if(sp2.contentEquals("Marsabit")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Samburu")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//
                if(sp2.contentEquals("Isiolo")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Laikipia")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Baringo")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Marakwet")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Nakuru")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Homa Bay")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Migori")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Sub-counties");
                    list.add("Not yet");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Kenya ends
                //ethiopia starts
                if(sp2.contentEquals("SNNPR")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Woredas");
                    list.add("Hulla");
                    list.add("Shashogo");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }

                if(sp2.contentEquals("Oromia")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Woredas");
                    list.add("Arsi Negele");
                    list.add("Ziway Dugda");
                    list.add("Hitosa");
                    list.add("Dodota");
                    list.add("Sire");
                    list.add("Boset");
                    list.add("Wenchi");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }

                if(sp2.contentEquals("Amharaa")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Woredas");
                    list.add("Gonder Zuria");
                    list.add("Chilga");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }

                if(sp2.contentEquals("Tigray")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Woredas");
                    list.add("Saharti Samre");
                    list.add("Degua Temben");
                    list.add("Emba'alage");
                    list.add("Hintalo Wejirat");
                    list.add("Enderta");
                    list.add("Saesie Tsaedaemba");
                    list.add("Kelete Awelallo");
                    list.add("Hawzen");
                    list.add("Erob");
                    list.add("Gulomekeda");
                    list.add("Ganta Afeshum");
                    list.add("Medebay Zana");
                    list.add("Asgede Tsimbila");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Ethiopia ends
                //Rwanda starts
                if(sp2.contentEquals("Eastern")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Districts");
                    list.add("Nyagatare");
                    list.add("Gatsibo");
                    list.add("Kayonza");
                    list.add("Bugesera");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Rwanda ends
                //somalia starts
                if(sp2.contentEquals("Somaliland")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Districts");
                    list.add("Dweyne");
                    list.add("Awdac");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Puntland")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Districts");
                    list.add("Sanaag");
                    list.add("Kakar");
                    list.add("Bari");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//somalia ends
                //Niger starts
                if(sp2.contentEquals("Tillabéri")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Communes");
                    list.add("Ouallam");
                    list.add("Simiri");
                    list.add("Hamdallaye");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Niger ends
                //Ghana starts
                if(sp2.contentEquals("Upper East")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Districts");
                    list.add("Bwaku West");
                    list.add("Garu Tempane");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Northern")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Districts");
                    list.add("Mion");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Ghana ends
                //Senegal starts
                if(sp2.contentEquals("Kaffrine")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select ");
                    list.add("Boulel");
                    list.add("Gniby");
                    list.add("Kahi");
                    list.add("Diamagadio");
                    list.add("Diokoul belbouk");
                    list.add("Medinatou salam 2");
                    list.add("Kathiote");
                    list.add("Keur M'bouki");
                    list.add("Touba M'bella");
                    list.add("Diamal");
                    list.add("Mabo");
                    list.add("N'diognick");
                    list.add("Mbeuleup");
                    list.add("Dianke Souf");
                    list.add("Darou Minam 2");
                    list.add("Ndiobene Samba Lamo");
                    list.add("Ndioum Ngainth");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Kaolack")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Keur Soce");
                    list.add("Ndiaffate");
                    list.add("Thiaré");
                    list.add("Latmingue");
                    list.add("Keur Baka");
                    list.add("Ndiago");
                    list.add("Ngathie Naoude");
                    list.add("Khelcom Birame");
                    list.add("Dara Mboss");
                    list.add("Ourour");
                    list.add("Gagnick");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//
                if(sp2.contentEquals("Fatick")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Diaoule");
                    list.add("Mbelacadiao");
                    list.add("Ndiob");
                    list.add("Thiare Ndialgui");
                    list.add("Fimela");
                    list.add("Loul");
                    list.add("Djilas");
                    list.add("Ngayokheme");
                    list.add("Niakhar");
                    list.add("Patar");
                    list.add("Diarere");
                    list.add("Diouroup");
                    list.add("Tattaguine");
                    list.add("Ndiene Lagane");
                    list.add("Ouadiour");
                    list.add("Patar Lia");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Senegal ends
                //Mali starts
                if(sp2.contentEquals("Sikasso")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Cercles");
                    list.add("Koutiala");
                    list.add("Yorosso");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }
                if(sp2.contentEquals("Segou")) {
                    List<String> list = new ArrayList<String>();
                    list.add("Select Cercles");
                    list.add("Tomininian");
                    list.add("Cercle of San");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                }//Mali ends

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end of spinner selection 2
        return view;
    }
}

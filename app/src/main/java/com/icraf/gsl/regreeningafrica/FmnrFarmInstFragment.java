package com.icraf.gsl.regreeningafrica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by benard on 1/18/19.
 */

public class FmnrFarmInstFragment extends Fragment {
    Spinner s1,s2,s3;
    //add items to spinner
    //add items to spinner
    Spinner SPINNER;
    Button ADD;
    EditText EDITTEXT;
    String[] spinnerItems = new String[]{
            "Select Country first",
            "Kenya",
            "Ethiopia",
            "Rwanda",
            "Somalia",
            "Niger",
            "Ghana",
            "Senegal",
            "Mali",
            "Chad"
    };
    String GETTEXT;
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;//end of spinner items
    //add county items to spinner
    Spinner SPINNER2;
    Button ADD2;
    EditText EDITTEXT2;
    String[] spinnerItems2 = new String[]{
            "Select Country first"
    };
    String GETTEXT2;
    List<String> stringlist2;
    ArrayAdapter<String> arrayadapter2;//end of spinner items
    //add district items to spinner
    Spinner SPINNER3;
    Button ADD3;
    EditText EDITTEXT3;
    String[] spinnerItems3 = new String[]{
            "Select County first"
    };
    String GETTEXT3;
    List<String> stringlist3;
    ArrayAdapter<String> arrayadapter3;//end of spinner items
    public FmnrFarmInstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmnr_farmer_institution_details, container,
                false);
        //buttons
        ADD2 = (Button)view.findViewById(R.id.button2);
        EDITTEXT2 = (EditText)view.findViewById(R.id.editText2);
        ADD3 = (Button)view.findViewById(R.id.button3);
        EDITTEXT3 = (EditText)view.findViewById(R.id.editText3);
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
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Marsabit");
                    list.add("Samburu");
                    list.add("Isiolo");
                    list.add("Laikipia");
                    list.add("Baringo");
                    list.add("Marakwet");
                    list.add("Nakuru");
                    list.add("Homa Bay");
                    list.add("Migori");
                    list.add("Machakos");
                    list.add("Kitui");
                    list.add("Makueni");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter);
                    //add item to the list
                    ADD2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT2.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT2 = EDITTEXT2.getText().toString();
                                list.add(GETTEXT2);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
                }
                if(sp1.contentEquals("Ethiopia")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("SNNPR");
                    list.add("Oromia");
                    list.add("Amhara");
                    list.add("Tigray");
                    final ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter2);
                    //add item to the list
                    ADD2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT2.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT2 = EDITTEXT2.getText().toString();
                                list.add(GETTEXT2);
                                dataAdapter2.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                 if(sp1.contentEquals("Rwanda")) {
                    final List<String> list = new ArrayList<String>();
                    //list.add("Select Region");
                    list.add("Eastern");
                    final ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter3.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter3);
                     //add item to the list
                     ADD2.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT2.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT2 = EDITTEXT2.getText().toString();
                                 list.add(GETTEXT2);
                                 dataAdapter3.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }
                 if(sp1.contentEquals("Somalia")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Somaliland");
                    list.add("Puntland");
                    final ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter4.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter4);
                     //add item to the list
                     ADD2.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT2.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT2 = EDITTEXT2.getText().toString();
                                 list.add(GETTEXT2);
                                 dataAdapter4.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }
                 if(sp1.contentEquals("Niger")) {
                   final  List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Tillabéri");
                    final ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter5.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter5);
                     //add item to the list
                     ADD2.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT2.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT2 = EDITTEXT2.getText().toString();
                                 list.add(GETTEXT2);
                                 dataAdapter5.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }
                if(sp1.contentEquals("Ghana")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Upper East");
                    list.add("Northern");
                    final ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter6.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter6);
                    //add item to the list
                    ADD2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT2.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT2 = EDITTEXT2.getText().toString();
                                list.add(GETTEXT2);
                                dataAdapter6.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp1.contentEquals("Senegal")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Kaffrine");
                    list.add("Kaolack");
                    list.add("Fatick");
                    final ArrayAdapter<String> dataAdapter7 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter7.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter7);
                    //add item to the list
                    ADD2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT2.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT2 = EDITTEXT2.getText().toString();
                                list.add(GETTEXT2);
                                dataAdapter7.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp1.contentEquals("Mali")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Sikasso");
                    list.add("Segou");
                    final ArrayAdapter<String> dataAdapter8 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter8.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter8);
                    //add item to the list
                    ADD2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT2.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT2 = EDITTEXT2.getText().toString();
                                list.add(GETTEXT2);
                                dataAdapter8.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
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
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Laisamis");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                 if(sp2.contentEquals("Samburu")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Samburu North");
                    list.add("Samburu West");
                    list.add("Samburu East");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                     //add item to the list
                     ADD3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT3.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT3 = EDITTEXT3.getText().toString();
                                 list.add(GETTEXT3);
                                 dataAdapter.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }//
                if(sp2.contentEquals("Isiolo")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Isiolo");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Laikipia")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Laikipia North");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Baringo")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Mogotio");
                    list.add("Baringo South");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Marakwet")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Keiyo South");
                    list.add("Keiyo North");
                    list.add("Marakwet West");
                    list.add("Marakwet East");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
                }
                if(sp2.contentEquals("Nakuru")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Rongai");
                    list.add("Gilgil");
                    list.add("Naivasha");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Homa Bay")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Suba North");
                    list.add("Suba South");
                    list.add("Lambwe");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Migori")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Nyatike");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if(EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            }
                            else{
                            GETTEXT3 = EDITTEXT3.getText().toString();
                            list.add(GETTEXT3);
                            dataAdapter.notifyDataSetChanged();
                            Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                        }
                    }
                    });
                }
                if(sp2.contentEquals("Machakos")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Mwala");
                    list.add("Yatta");
                    list.add("Masinga");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Kitui")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Mwingi central");
                    list.add("Kitui rural");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if(sp2.contentEquals("Makueni")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Mbooni East");
                    list.add("Kibwezi East");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }//Kenya ends
                //ethiopia starts
                if(sp2.contentEquals("SNNPR")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Hulla");
                    list.add("Shashogo");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                 if(sp2.contentEquals("Oromia")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Arsi Negele");
                    list.add("Ziway Dugda");
                    list.add("Heban Arsi");
                    list.add("Dodota");
                    list.add("Sire");
                    list.add("Boset");
                    list.add("Merti");
                    list.add("Dodola");
                    list.add("Jeju");
                    list.add("Chilga");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                     //add item to the list
                     ADD3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT3.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT3 = EDITTEXT3.getText().toString();
                                 list.add(GETTEXT3);
                                 dataAdapter.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }

                if(sp2.contentEquals("Amhara")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Ambasel");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                if(sp2.contentEquals("Tigray")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
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
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }//Ethiopia ends
                //Rwanda starts
                if(sp2.contentEquals("Eastern")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Nyagatare");
                    list.add("Gatsibo");
                    list.add("Kayonza");
                    list.add("Bugesera");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }//Rwanda ends
                //somalia starts
               if(sp2.contentEquals("Somaliland")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Owdeyne/Toghdeer");
                    list.add("Awdal");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                   //add item to the list
                   ADD3.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           // TODO Auto-generated method stub
                           if (EDITTEXT3.getText().toString().equals("")) {
                               Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                           } else {
                               GETTEXT3 = EDITTEXT3.getText().toString();
                               list.add(GETTEXT3);
                               dataAdapter.notifyDataSetChanged();
                               Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                           }
                       }
                   });
                }
                 if(sp2.contentEquals("Puntland")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Sanaag");
                    list.add("Bari");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                     //add item to the list
                     ADD3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT3.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT3 = EDITTEXT3.getText().toString();
                                 list.add(GETTEXT3);
                                 dataAdapter.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }//somalia ends
                //Niger starts
                if(sp2.contentEquals("Tillabéri")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Ouallam");
                    list.add("Simiri");
                    list.add("Hamdallaye");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }//Niger ends
                //Ghana starts
                if(sp2.contentEquals("Upper East")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Bwaku West");
                    list.add("Garu Tempane");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                 if(sp2.contentEquals("Northern")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Mion");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                     //add item to the list
                     ADD3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT3.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT3 = EDITTEXT3.getText().toString();
                                 list.add(GETTEXT3);
                                 dataAdapter.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }//Ghana ends
                //Senegal starts
                if(sp2.contentEquals("Kaffrine")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
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
                    list.add("Sagna");
                    list.add("Darou Minam 2");
                    list.add("Ndiobene Samba Lamo");
                    list.add("Ndioum Ngainth");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                 if(sp2.contentEquals("Kaolack")) {
                    final List<String> list = new ArrayList<String>();
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
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                     //add item to the list
                     ADD3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT3.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT3 = EDITTEXT3.getText().toString();
                                 list.add(GETTEXT3);
                                 dataAdapter.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }//
                if(sp2.contentEquals("Fatick")) {
                    final List<String> list = new ArrayList<String>();
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

                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }//Senegal ends
                //Mali starts
                if(sp2.contentEquals("Sikasso")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Koutiala");
                    list.add("Yorosso");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                    //add item to the list
                    ADD3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (EDITTEXT3.getText().toString().equals("")) {
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                            } else {
                                GETTEXT3 = EDITTEXT3.getText().toString();
                                list.add(GETTEXT3);
                                dataAdapter.notifyDataSetChanged();
                                Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                 if(sp2.contentEquals("Segou")) {
                    final List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Tomininian");
                     list.add("Cercle of San");
                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s3.setAdapter(dataAdapter);
                     //add item to the list
                     ADD3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             // TODO Auto-generated method stub
                             if (EDITTEXT3.getText().toString().equals("")) {
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                             } else {
                                 GETTEXT3 = EDITTEXT3.getText().toString();
                                 list.add(GETTEXT3);
                                 dataAdapter.notifyDataSetChanged();
                                 Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                }//Mali ends

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end of spinner selection 2

        //show add country when only the add button is pressed
        //spinner add country item
        SPINNER = (Spinner)view.findViewById(R.id.spinner1);
        ADD = (Button)view.findViewById(R.id.button1);
        EDITTEXT = (EditText)view.findViewById(R.id.editText1);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));
        arrayadapter = new ArrayAdapter<String>(FmnrFarmInstFragment.this.getActivity(),R.layout.textview,stringlist );
        arrayadapter.setDropDownViewResource(R.layout.textview);
        SPINNER.setAdapter(arrayadapter);
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (EDITTEXT.getText().toString().equals("")) {
                    Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                } else {
                    GETTEXT = EDITTEXT.getText().toString();
                    stringlist.add(GETTEXT);
                    arrayadapter.notifyDataSetChanged();
                    Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Country added to list", Toast.LENGTH_LONG).show();
                }
            }
        });
        //spinner add county item
        SPINNER2 = (Spinner)view.findViewById(R.id.spinner2);
        ADD2 = (Button)view.findViewById(R.id.button2);
        EDITTEXT2 = (EditText)view.findViewById(R.id.editText2);
        stringlist2 = new ArrayList<>(Arrays.asList(spinnerItems2));
        arrayadapter2 = new ArrayAdapter<String>(FmnrFarmInstFragment.this.getActivity(),R.layout.county,stringlist2 );
        arrayadapter2.setDropDownViewResource(R.layout.county);
        SPINNER2.setAdapter(arrayadapter2);
        ADD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (EDITTEXT2.getText().toString().equals("")) {
                    Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                } else {
                    GETTEXT2 = EDITTEXT2.getText().toString();
                    stringlist2.add(GETTEXT2);
                    arrayadapter2.notifyDataSetChanged();
                    Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "County added to the list", Toast.LENGTH_LONG).show();
                }
            }
        });
        //spinner add district item
        SPINNER3 = (Spinner)view.findViewById(R.id.spinner3);
        ADD3 = (Button)view.findViewById(R.id.button3);
        EDITTEXT3 = (EditText)view.findViewById(R.id.editText3);
        stringlist3 = new ArrayList<>(Arrays.asList(spinnerItems3));
        arrayadapter3 = new ArrayAdapter<String>(FmnrFarmInstFragment.this.getActivity(),R.layout.district,stringlist3 );
        arrayadapter3.setDropDownViewResource(R.layout.district);
        SPINNER3.setAdapter(arrayadapter3);
        ADD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (EDITTEXT3.getText().toString().equals("")) {
                    Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                } else {
                    GETTEXT3 = EDITTEXT3.getText().toString();
                    stringlist3.add(GETTEXT3);
                    arrayadapter3.notifyDataSetChanged();
                    Toast.makeText(FmnrFarmInstFragment.this.getActivity(), "District added to the list", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}

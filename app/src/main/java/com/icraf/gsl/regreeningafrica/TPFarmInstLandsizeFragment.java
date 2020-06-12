package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by benard on 1/18/19.
 *
 */

public class TPFarmInstLandsizeFragment extends Fragment {
    private DbAccess dbAccess;
    //add items to spinner
    Spinner SPINNER;
    Button ADD,button_prev;
    EditText EDITTEXT;
    String[] spinnerItems = new String[]{
            "Select unit",
            "Hectares",
            "Acres"

    };
    String GETTEXT;
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;//end of spinner items

    public TPFarmInstLandsizeFragment() {
        // Required empty public constructor
    }

    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_size_regreen, container,
                false);

        final EditText ed = (EditText) view.findViewById(R.id.landestimate);
        final Spinner unit = (Spinner) view.findViewById(R.id.units);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //get fid
        String farmer_id = g.getfid();
        TextView fid = (TextView) view.findViewById(R.id.fid);
        fid.setText(farmer_id);

        //proceed to polygon land size recording
        Button button_next = (Button) view.findViewById(R.id.tocohort);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tocohort:
                        //validate before going next
                        boolean fail = false;
                        if (ed.getText().toString().trim().length() == 0) {
                            fail = true;
                            ed.requestFocus();
                            ed.setError("Enter land estimate");
                        }
                        if (unit.getSelectedItem().toString().trim().equals("Select unit")) {//validate the spinner not
                            fail = true;
                            unit.requestFocus();
                            TextView errorText = (TextView) unit.getSelectedView();
                            errorText.setError("");
                            errorText.setTextColor(Color.RED);//just to highlight that this is an error
                            errorText.setText("Please select the unit");//changes the selected item text to this
                        }
                        if (!fail) {
                            saveTPPlotinfo();//save
                            plotid();//generate plot id for the polygons
                            dbAccess.insertPlotinfo();//insert details to db
                            //g.setMultiplot(false);//set it true
                            //Intent intent = new Intent(getActivity(), TPCohortMainAcivity.class);
                            Intent intent = new Intent(getActivity(), TPLandSizeMainActivity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                            //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        //for previous/back button
        button_prev = view.findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g.getMultiplot()) {
                    Intent intent = new Intent(getActivity(), Select_Farmer_Institution_TP.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    //button_prev.setEnabled(false);
                    Intent intent = new Intent(getActivity(), TPFarmInstiMainActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }

            }
        });
        //add item to spinner from edittext
        SPINNER = (Spinner) view.findViewById(R.id.units);
        ADD = (Button) view.findViewById(R.id.button1);
        EDITTEXT = (EditText) view.findViewById(R.id.editText1);
        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));
        arrayadapter = new ArrayAdapter<String>(TPFarmInstLandsizeFragment.this.getActivity(), R.layout.textview, stringlist);
        arrayadapter.setDropDownViewResource(R.layout.textview);
        SPINNER.setAdapter(arrayadapter);

        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (EDITTEXT.getText().toString().equals("")) {
                    Toast.makeText(TPFarmInstLandsizeFragment.this.getActivity(), "Empty save not allowed", Toast.LENGTH_LONG).show();
                } else {
                    GETTEXT = EDITTEXT.getText().toString();
                    stringlist.add(GETTEXT);
                    arrayadapter.notifyDataSetChanged();
                    Toast.makeText(TPFarmInstLandsizeFragment.this.getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                }
            }
        });
        //if yes radio button yes is clicked show edit text
        final RadioButton yes_crop = (RadioButton) view.findViewById(R.id.yes_crop) ;
        final RadioButton no_crop = (RadioButton) view.findViewById(R.id.no_crop) ;
        final EditText txtcrops=(EditText)view.findViewById(R.id.crops);
        yes_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yes_crop.isChecked()){
                    txtcrops.setVisibility(View.VISIBLE);
                }
            }
        });
        no_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(no_crop.isChecked()){
                    txtcrops.setVisibility(View.GONE);
                }
            }
        });//end


        return view;
    }

    // save farmer/institution data when you go next
    public void saveTPPlotinfo() {
        //get unique id for farmer/institution
        TextView fid = (TextView) getActivity().findViewById(R.id.fid);
        g.setfid(fid.getText().toString());
        RadioGroup crop = (RadioGroup) getActivity().findViewById(R.id.crop);
        //check whether it is checked
        if(crop.getCheckedRadioButtonId()==-1){
            //Toast.makeText(FmnrFarmInstLandsizeFragment.this.getActivity(),"Please select Radio Button!",Toast.LENGTH_SHORT).show();
        }
        else {
            // get selected radioButton from radioGroup
            int selectedId = crop.getCheckedRadioButtonId();
            // find the radioButton by returned id
            RadioButton select = (RadioButton) getActivity().findViewById(selectedId);
            // radioButton text
            g.setcrops(select.getText().toString());//yes or no if there are crops
        }
        EditText croplist = (EditText) getActivity().findViewById(R.id.crops);
        g.setcroplist(croplist.getText().toString());//crop or list of crops

        EditText regreen_size = (EditText) getActivity().findViewById(R.id.landestimate);
        g.setlandsize(regreen_size.getText().toString());

        Spinner units = (Spinner) getActivity().findViewById(R.id.units);
        //check if spinner is selected
        if (units != null && units.getSelectedItem() != null) {
            g.setunits(units.getSelectedItem().toString());
        }
        g.setuploaded("no");//set uploaded to no on insert
        //g.setmodule("TreePlanting");//set which module is this on insert
    }
    //setting plot id for polygons
    public void plotid(){
        //generate unique id for farmer/institution
        Random generator = new Random();
        int n = 1000000;
        Random generator2 = new Random();
        int n2 = 1000000;
        n = generator.nextInt(n);//random number generator
        n2 = generator2.nextInt(n2);//random number generator2
        String pid = "plot_" + n + "_" + n2;
        g.setpid(pid);
    }
}

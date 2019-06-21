package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by benard on 2/22/19.
 *
 */
public class NurseryEndFragment extends Fragment {
    private DbAccess dbAccess;

    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_end_fragment, container,
                false);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();
        //add other tree species on the same nursery
        Button button_addtree = (Button) view.findViewById(R.id.add_tree);
        button_addtree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_tree:
                        saveSpecies();
                        dbAccess.insertNurserySpecies();
                        Intent intent = new Intent(getActivity(), NurseryOtherInfoMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //add another nursery
        Button button_addnursery = (Button) view.findViewById(R.id.add_nursery);
        button_addnursery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_nursery:
                        saveSpecies();
                        dbAccess.insertNurserySpecies();
                        Intent intent = new Intent(getActivity(), NurseryInfoMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //finish nursery survey
        Button button_next = (Button) view.findViewById(R.id.finish);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.finish:
                        saveSpecies();
                        dbAccess.insertNurserySpecies();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }

    public void saveSpecies() {
        EditText nursery_species = (EditText) getActivity().findViewById(R.id.nursery_species);
        g.setnursery_species(nursery_species.getText().toString());
        EditText nursery_local = (EditText) getActivity().findViewById(R.id.nursery_local);
        g.setnursery_local(nursery_local.getText().toString());
        CheckBox bare_root = (CheckBox) getActivity().findViewById(R.id.bare_root);
        if (bare_root.isChecked()) {
            g.setbare_root("yes");
        } else {
            g.setbare_root("no");
        }
        CheckBox container = (CheckBox) getActivity().findViewById(R.id.container);
        if (container.isChecked()) {
            g.setcontainer("yes");
        } else {
            g.setcontainer("no");
        }
        EditText other_method=(EditText) getActivity().findViewById(R.id.method_other);
        g.setother_method(other_method.getText().toString());

        CheckBox seed = (CheckBox) getActivity().findViewById(R.id.seed);
        if (seed.isChecked()) {
            g.setseed("yes");
        } else {
            g.setseed("no");
        }
        CheckBox graft = (CheckBox) getActivity().findViewById(R.id.graft);
        if (graft.isChecked()) {
            g.setgraft("yes");
        } else {
            g.setgraft("no");
        }
        CheckBox cutting = (CheckBox) getActivity().findViewById(R.id.cutting);
        if (cutting.isChecked()) {
            g.setcutting("yes");
        } else {
            g.setcutting("no");
        }
        CheckBox marcotting = (CheckBox) getActivity().findViewById(R.id.marcotting);
        if (marcotting.isChecked()) {
            g.setmarcotting("yes");
        } else {
            g.setmarcotting("no");
        }
            //seeds
        CheckBox own_farm_seeds = (CheckBox) getActivity().findViewById(R.id.on_farm);
        if (own_farm_seeds.isChecked()) {
            g.setown_farm_seeds("yes");
        } else {
            g.setown_farm_seeds("no");
        }
        CheckBox local_dealer_seeds = (CheckBox) getActivity().findViewById(R.id.local_dealer);
        if (local_dealer_seeds.isChecked()) {
            g.setlocal_dealer_seeds("yes");
        } else {
            g.setlocal_dealer_seeds("no");
        }
        CheckBox govt_seed = (CheckBox) getActivity().findViewById(R.id.national_dealers);
        if (govt_seed.isChecked()) {
            g.setnational_seed("yes");
        } else {
            g.setnational_seed("no");
        }
        CheckBox ngos = (CheckBox) getActivity().findViewById(R.id.NGOs);
        if (ngos.isChecked()) {
            g.setngos_seed("yes");
        } else {
            g.setngos_seed("no");
        }
        EditText other_seed_sources=(EditText) getActivity().findViewById(R.id.other_seed_sources);
        g.setother_seed_source(other_seed_sources.getText().toString());
        //grafting
        CheckBox farmland = (CheckBox) getActivity().findViewById(R.id.farmland);
        if (farmland.isChecked()) {
            g.setfarmland("yes");
        } else {
            g.setfarmland("no");
        }
        CheckBox plantation = (CheckBox) getActivity().findViewById(R.id.plantation);
        if (plantation.isChecked()) {
            g.setplantation("yes");
        } else {
            g.setplantation("no");
        }
        CheckBox mother_blocks = (CheckBox) getActivity().findViewById(R.id.mother_blocks);
        if (mother_blocks.isChecked()) {
            g.setmother_blocks("yes");
        } else {
            g.setmother_blocks("no");
        }
        CheckBox prisons = (CheckBox) getActivity().findViewById(R.id.prisons);
        if (prisons.isChecked()) {
            g.setprisons("yes");
        } else {
            g.setprisons("no");
        }
        EditText other_graft_sources = (EditText) getActivity().findViewById(R.id.other_graft_sources);
        g.setother_graft_sources(other_graft_sources.getText().toString());
        //
        EditText qpurchased = (EditText) getActivity().findViewById(R.id.qpurchased);
        g.setqpurchased(qpurchased.getText().toString());
        Spinner qunits = (Spinner) getActivity().findViewById(R.id.spinner_units);
        //check if spinner is selected
        if(qunits != null && qunits.getSelectedItem() !=null ) {
            g.setqunits(qunits.getSelectedItem().toString());
        }//added for survey unit selection
        //EditText other_unit = (EditText) getActivity().findViewById(R.id.unit_other);
        //g.setqunits(other_unit.getText().toString());//added for other unit option
        EditText number_sowed = (EditText) getActivity().findViewById(R.id.seeds_sown);
        g.setseed_sown(number_sowed.getText().toString());//added
        Spinner unit_sown = (Spinner) getActivity().findViewById(R.id.spinner_unitsown);
        //check if spinner is selected
        if(unit_sown != null && unit_sown.getSelectedItem() !=null ) {
            g.setunitsown(unit_sown.getSelectedItem().toString());
        }//added for survey unit selection
       // EditText other_unitsown = (EditText) getActivity().findViewById(R.id.unitsown_other);
       // g.setunitsown(other_unitsown.getText().toString());//added for other unit option
        EditText date_sown = (EditText) getActivity().findViewById(R.id.date_sown);
        g.setdate_sown(date_sown.getText().toString());
        EditText germinated = (EditText) getActivity().findViewById(R.id.germinated);
        g.setgerminated(germinated.getText().toString());
        EditText survived = (EditText) getActivity().findViewById(R.id.survived);
        g.setsurvived(survived.getText().toString());
        EditText seedlings_age = (EditText) getActivity().findViewById(R.id.seedlings_age);
        g.setseedlings_age(seedlings_age.getText().toString());
        EditText price = (EditText) getActivity().findViewById(R.id.price);
        g.setprice(price.getText().toString());
        EditText notes = (EditText) getActivity().findViewById(R.id.notes);
        g.setnotes(notes.getText().toString());

        g.setuploaded("no");//set uploaded to no on insert
    }
}


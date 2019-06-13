package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 10/22/18.
 * Edit data from sqlite db and save the changes or delete the record
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NurseryEdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private EditText idTextView,nid,enume,date,survey,country,county,district,operator,contact,n_name,s_number,s_date,government,
            church_mosque,schools,women_groups,youth_groups,private_individual,communal_village,other_types,latitude,longitude,altitude,accuracy,image,
    nursery_species,nursery_local,bare_root,container,other,seed,graft,cutting,marcotting,own_farm,local_dealer,national_seed,ngos,other_s,farmland,plantation,m_blocks,prisons,
    other_graft,qpurchased,units,seed_sown,units_sown,date_sown,seedlings_germinated,seedlings_survived,seedlings_age,seedlings_price,note;
    private long _id;

    private DbAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.nursery_edit);
        //for previous/back button
        final Button button_prev = (Button) findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NurseryEdit.this, NurseryView.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();

            }
        });
        //DB access
        dbAccess = new DbAccess(this);
        dbAccess.open();

         idTextView = (EditText) findViewById(R.id.id);
         nid = (EditText) findViewById(R.id.nurseryID);
         enume = (EditText) findViewById(R.id.enume);
         date = (EditText) findViewById(R.id.in_date);
         survey = (EditText) findViewById(R.id.survey_name);
         country = (EditText) findViewById(R.id.country);
         county = (EditText) findViewById(R.id.county);
         district = (EditText) findViewById(R.id.district);
         operator = (EditText) findViewById(R.id.operator);
         contact = (EditText) findViewById(R.id.contact);
         n_name = (EditText) findViewById(R.id.nursery_name);
         s_number = (EditText) findViewById(R.id.species_number);
         s_date = (EditText) findViewById(R.id.start_date);
         government = (EditText) findViewById(R.id.government);
         church_mosque = (EditText) findViewById(R.id.mosque_church);
         schools = (EditText) findViewById(R.id.schools);
         women_groups = (EditText) findViewById(R.id.women_grps);
         youth_groups = (EditText) findViewById(R.id.youth_grps);
         private_individual = (EditText) findViewById(R.id.private_individual);
         communal_village = (EditText) findViewById(R.id.communal_village);
         other_types = (EditText) findViewById(R.id.other_type);
         latitude = (EditText) findViewById(R.id.latitude);
         longitude = (EditText) findViewById(R.id.longitude);
         altitude = (EditText) findViewById(R.id.altitude);
         accuracy = (EditText) findViewById(R.id.accuracy);
         image = (EditText) findViewById(R.id.path);
         nursery_species = (EditText) findViewById(R.id.nursery_species);
         nursery_local = (EditText) findViewById(R.id.nursery_local);
         bare_root = (EditText) findViewById(R.id.bare_root);
         container = (EditText) findViewById(R.id.container);
         other = (EditText) findViewById(R.id.other);
         seed = (EditText) findViewById(R.id.seed);
         graft = (EditText) findViewById(R.id.graft);
         cutting = (EditText) findViewById(R.id.cutting);
         marcotting = (EditText) findViewById(R.id.marcotting);
         own_farm = (EditText) findViewById(R.id.own_farm);
         local_dealer = (EditText) findViewById(R.id.local_dealer);
         national_seed = (EditText) findViewById(R.id.national_seed);
         ngos = (EditText) findViewById(R.id.ngos);
         other_s = (EditText) findViewById(R.id.other_s);
         farmland = (EditText) findViewById(R.id.farmland);
         plantation = (EditText) findViewById(R.id.plantation);
         m_blocks = (EditText) findViewById(R.id.mother_blocks);
         prisons = (EditText) findViewById(R.id.prisons);
         other_graft = (EditText) findViewById(R.id.other_graft_sources);

         qpurchased = (EditText) findViewById(R.id.quantity_purchased);
         units = (EditText) findViewById(R.id.units);
         seed_sown = (EditText) findViewById(R.id.seeds_sown);
         units_sown = (EditText) findViewById(R.id.units_sown);
         date_sown = (EditText) findViewById(R.id.date_sown);
         seedlings_germinated = (EditText) findViewById(R.id.germinated);
         seedlings_survived = (EditText) findViewById(R.id.survived);
         seedlings_age = (EditText) findViewById(R.id.age);
         seedlings_price = (EditText) findViewById(R.id.seedlings_price);
         note = (EditText) findViewById(R.id.notes);


        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        //send data to a new activity
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nurseryid = intent.getStringExtra("nid");
        String nursery_ename = intent.getStringExtra("enume");
        String nursery_date = intent.getStringExtra("date");
        String nursery_survey = intent.getStringExtra("survey_name");
        String nursery_country = intent.getStringExtra("nursery_country");
        String nursery_county = intent.getStringExtra("nursery_county");
        String nursery_district = intent.getStringExtra("nursery_village");
        String nursery_operator = intent.getStringExtra("nursery_operator");
        String nursery_contact = intent.getStringExtra("nursery_contact");
        String nursery_name = intent.getStringExtra("nursery_name");
        String nursery_snumber = intent.getStringExtra("species_number");
        String nursery_sdate = intent.getStringExtra("n_date");
        String nursery_government = intent.getStringExtra("government");
        String nursery_church_mosque = intent.getStringExtra("church_mosque");
        String nursery_schools = intent.getStringExtra("schools");
        String nursery_women_groups = intent.getStringExtra("women_groups");
        String nursery_youth_groups = intent.getStringExtra("youth_groups");
        String nursery_private_individual = intent.getStringExtra("private_individual");
        String nursery_communal_village = intent.getStringExtra("communal_village");
        String nursery_other_types = intent.getStringExtra("other_types");
        String nursery_latitude = intent.getStringExtra("latitude");
        String nursery_longitude = intent.getStringExtra("longitude");
        String nursery_altitude = intent.getStringExtra("altitude");
        String nursery_accuracy = intent.getStringExtra("accuracy");
        String nursery_path = intent.getStringExtra("image");

        String species = intent.getStringExtra("nursery_species");
        String local = intent.getStringExtra("nursery_local");
        String nursery_bare_root = intent.getStringExtra("bare_root");
        String nursery_container = intent.getStringExtra("container");
        String nursery_other = intent.getStringExtra("other");
        String nursery_seed = intent.getStringExtra("seed");
        String nursery_graft = intent.getStringExtra("graft");
        String nursery_cutting = intent.getStringExtra("cutting");
        String nursery_marcotting = intent.getStringExtra("marcotting");
        String nursery_own_farm = intent.getStringExtra("own_farm");
        String nursery_local_dealer = intent.getStringExtra("local_dealer");
        String nursery_national_seed = intent.getStringExtra("national_seed");
        String nursery_ngos = intent.getStringExtra("ngos");
        String nursery_other_s = intent.getStringExtra("other_s");
        String nursery_farmland = intent.getStringExtra("farmland");
        String nursery_plantation = intent.getStringExtra("plantation");
        String nursery_m_blocks = intent.getStringExtra("m_blocks");
        String nursery_prisons = intent.getStringExtra("prisons");
        String nursery_other_graft = intent.getStringExtra("other_graft");

        String purchased = intent.getStringExtra("qpurchased");
        String unit = intent.getStringExtra("units");
        String seedsown = intent.getStringExtra("seed_sown");
        String unitsown = intent.getStringExtra("units_sown");
        String datesown = intent.getStringExtra("date_sown");
        String sgerminated = intent.getStringExtra("seedlings_germinated");
        String ssurvived = intent.getStringExtra("seedlings_survived");
        String sage = intent.getStringExtra("seedlings_age");
        String sprice = intent.getStringExtra("seedlings_price");
        String notes = intent.getStringExtra("nursery_notes");


        _id = Long.parseLong(id);

        //
        idTextView.setText(id);
        nid.setText(nurseryid);
        enume.setText(nursery_ename);
        date.setText(nursery_date);
        survey.setText(nursery_survey);
        country.setText(nursery_country);
        county.setText(nursery_county);
        district.setText(nursery_district);
        operator.setText(nursery_operator);
        contact.setText(nursery_contact);
        n_name.setText(nursery_name);
        s_number.setText(nursery_snumber);
        s_date.setText(nursery_sdate);
        government.setText(nursery_government);
        church_mosque.setText(nursery_church_mosque);
        schools.setText(nursery_schools);
        women_groups.setText(nursery_women_groups);
        youth_groups.setText(nursery_youth_groups);
        private_individual.setText(nursery_private_individual);
        communal_village.setText(nursery_communal_village);
        other_types.setText(nursery_other_types);
        latitude.setText(nursery_latitude);
        longitude.setText(nursery_longitude);
        altitude.setText(nursery_altitude);
        accuracy.setText(nursery_accuracy);
        image.setText(nursery_path);

        nursery_species.setText(species);
        nursery_local.setText(local);
        bare_root.setText(nursery_bare_root);
        container.setText(nursery_container);
        other.setText(nursery_other);

        seed.setText(nursery_seed);
        graft.setText(nursery_graft);
        cutting.setText(nursery_cutting);
        marcotting.setText(nursery_marcotting);

        own_farm.setText(nursery_own_farm);
        local_dealer.setText(nursery_local_dealer);
        national_seed.setText(nursery_national_seed);
        ngos.setText(nursery_ngos);
        other_s.setText(nursery_other_s);
        farmland.setText(nursery_farmland);
        plantation.setText(nursery_plantation);
        m_blocks.setText(nursery_m_blocks);
        prisons.setText(nursery_prisons);
        other_graft.setText(nursery_other_graft);

        //select_hText.setText(select_h);
        qpurchased.setText(purchased);
        units.setText(unit);
        seed_sown.setText(seedsown);
        units_sown.setText(unitsown);
        date_sown.setText(datesown);
        seedlings_germinated.setText(sgerminated);
        seedlings_survived.setText(ssurvived);
        seedlings_age.setText(sage);
        seedlings_price.setText(sprice);
        note.setText(notes);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                //update values
                String ename = enume.getText().toString();
                String nursery_date = date.getText().toString();
                String survey_name = survey.getText().toString();
                String nursery_country = country.getText().toString();
                String nursery_county = county.getText().toString();
                String nursery_district = district.getText().toString();
                String nursery_operator = operator.getText().toString();
                String nursery_contact = contact.getText().toString();
                String nursery_name = n_name.getText().toString();
                String nursery_snumber = s_number.getText().toString();
                String nursery_sdate = s_date.getText().toString();
                String govt = government.getText().toString();
                String cm = church_mosque.getText().toString();
                String sch = schools.getText().toString();
                String wg = women_groups.getText().toString();
                String youth_group = youth_groups.getText().toString();
                String pi = private_individual.getText().toString();
                String cv = communal_village.getText().toString();
                String ot = other_types.getText().toString();
                String n_latitude = latitude.getText().toString();
                String n_longitude = longitude.getText().toString();
                String n_altitude = altitude.getText().toString();
                String n_accuracy = accuracy.getText().toString();
                String path = image.getText().toString();

                String species = nursery_species.getText().toString();
                String local = nursery_local.getText().toString();
                String bare= bare_root.getText().toString();
                String containerised = container.getText().toString();
                String other_method = other.getText().toString();
                String sd = seed.getText().toString();
                String gf = graft.getText().toString();
                String ct = cutting.getText().toString();
                String mt = marcotting.getText().toString();
                String ownfarm = own_farm.getText().toString();
                String localdealer = local_dealer.getText().toString();
                String nationalseed = national_seed.getText().toString();
                String ng = ngos.getText().toString();
                String other_source = other_s.getText().toString();
                String fm = farmland.getText().toString();
                String pt = plantation.getText().toString();
                String mb = m_blocks.getText().toString();
                String pr = prisons.getText().toString();
                String og = other_graft.getText().toString();

                String purchased = qpurchased.getText().toString();
                String unit = units.getText().toString();
                String seedsown = seed_sown.getText().toString();
                String sownunits = units_sown.getText().toString();
                String datesown = date_sown.getText().toString();
                String germ = seedlings_germinated.getText().toString();
                String surv = seedlings_survived.getText().toString();
                String sage = seedlings_age.getText().toString();
                String sprice = seedlings_price.getText().toString();
                String notes = note.getText().toString();

                //update the record
                dbAccess.updateNursery(_id, ename, nursery_date, survey_name, nursery_country,nursery_county,nursery_district,nursery_operator,nursery_contact,nursery_name,nursery_snumber,nursery_sdate,govt,cm,
                        sch,wg,youth_group,pi,cv,ot,n_latitude,n_longitude,n_altitude,n_accuracy,path);
                dbAccess.updateNurseryspecies(_id, species,local,bare,containerised,
                        other_method,sd,gf,ct,mt,ownfarm,localdealer,nationalseed,ng,other_source,fm,pt,mb,pr,og,purchased,unit,seedsown,sownunits,datesown,germ,surv,sage,sprice,notes);
                Toast.makeText(NurseryEdit.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            case R.id.btn_delete:
                //delete
                dbAccess.delete_Nursery(_id);
                dbAccess.delete_Nurseryspecies(_id);
                Toast.makeText(NurseryEdit.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), NurseryView.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
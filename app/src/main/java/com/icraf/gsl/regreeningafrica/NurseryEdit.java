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
import android.widget.TextView;
import android.widget.Toast;

public class NurseryEdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private long _id;

    private DbAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.nursery_edit);
        //DB access
        dbAccess = new DbAccess(this);
        dbAccess.open();

        EditText idTextView = (EditText) findViewById(R.id.id);
        EditText nid = (EditText) findViewById(R.id.nurseryID);
        EditText enume = (EditText) findViewById(R.id.enume);
        EditText date = (EditText) findViewById(R.id.in_date);
        EditText survey = (EditText) findViewById(R.id.survey_name);
        EditText country = (EditText) findViewById(R.id.country);
        TextView county = (EditText) findViewById(R.id.county);
        EditText district = (EditText) findViewById(R.id.district);
        EditText operator = (EditText) findViewById(R.id.operator);
        EditText contact = (EditText) findViewById(R.id.contact);
        EditText government = (EditText) findViewById(R.id.government);
        EditText church_mosque = (EditText) findViewById(R.id.mosque_church);
        EditText schools = (EditText) findViewById(R.id.schools);
        EditText women_groups = (EditText) findViewById(R.id.women_grps);
        EditText youth_groups = (EditText) findViewById(R.id.youth_grps);
        EditText private_individual = (EditText) findViewById(R.id.private_individual);
        EditText communal_village = (EditText) findViewById(R.id.communal_village);
        EditText other_types = (EditText) findViewById(R.id.other_type);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        EditText altitude = (EditText) findViewById(R.id.altitude);
        EditText accuracy = (EditText) findViewById(R.id.accuracy);
        EditText image = (EditText) findViewById(R.id.path);
        EditText nursery_species = (EditText) findViewById(R.id.nursery_species);
        EditText nursery_local = (EditText) findViewById(R.id.nursery_local);
        EditText bare_root = (EditText) findViewById(R.id.bare_root);
        EditText container = (EditText) findViewById(R.id.container);
        EditText other = (EditText) findViewById(R.id.other);
        EditText seed = (EditText) findViewById(R.id.seed);
        EditText graft = (EditText) findViewById(R.id.graft);
        EditText cutting = (EditText) findViewById(R.id.cutting);
        EditText marcotting = (EditText) findViewById(R.id.marcotting);
        EditText own_farm = (EditText) findViewById(R.id.own_farm);
        EditText local_dealer = (EditText) findViewById(R.id.local_dealer);
        EditText national_seed = (EditText) findViewById(R.id.national_seed);
        EditText ngos = (EditText) findViewById(R.id.ngos);
        EditText other_s = (EditText) findViewById(R.id.other_s);
        EditText farmland = (EditText) findViewById(R.id.farmland);
        EditText plantation = (EditText) findViewById(R.id.plantation);
        EditText m_blocks = (EditText) findViewById(R.id.mother_blocks);
        EditText prisons = (EditText) findViewById(R.id.prisons);
        EditText other_graft = (EditText) findViewById(R.id.other_graft_sources);

        EditText qpurchased = (EditText) findViewById(R.id.quantity_purchased);
        EditText units = (EditText) findViewById(R.id.units);
        EditText seed_sown = (EditText) findViewById(R.id.seeds_sown);
        EditText units_sown = (EditText) findViewById(R.id.units_sown);
        EditText date_sown = (EditText) findViewById(R.id.date_sown);
        EditText seedlings_germinated = (EditText) findViewById(R.id.germinated);
        EditText seedlings_survived = (EditText) findViewById(R.id.survived);
        EditText seedlings_age = (EditText) findViewById(R.id.age);
        EditText seedlings_price = (EditText) findViewById(R.id.seedlings_price);


        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        //disable delete button
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        //send data to a new activity
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nurseryid = intent.getStringExtra("nid");
        String nursery_ename = intent.getStringExtra("ename");
        String nursery_date = intent.getStringExtra("date");
        String nursery_survey = intent.getStringExtra("survey_name");
        String nursery_country = intent.getStringExtra("nursery_country");
        String nursery_county = intent.getStringExtra("nursery_county");
        String nursery_district = intent.getStringExtra("nursery_village");
        String nursery_operator = intent.getStringExtra("nursery_operator");
        String nursery_contact = intent.getStringExtra("nursery_contact");
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

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                //update values
            /*    String project = projectText.getText().toString();
                String name = nameText.getText().toString();
                String village = villageText.getText().toString();
                String country = countryText.getText().toString();
                String select = selectText.getText().toString();
                String in_date = in_dateText.getText().toString();
                String latitude = latitudeText.getText().toString();
                String longitude = longitudeText.getText().toString();
                String altitude = altitudeText.getText().toString();
                String accuracy = accuracyText.getText().toString();
                String species = speciesText.getText().toString();
                String local = localText.getText().toString();
                //String select_h = select_hText.getText().toString();
                String dbh = dbhText.getText().toString();
                String height = heightText.getText().toString();
                String base_circum = base_circumText.getText().toString();
                String path = pathText.getText().toString();
                String mg1 = mg1Text.getText().toString();
                String mg2 = mg2Text.getText().toString();
                String mg3 = mg3Text.getText().toString();
                String mg4 = mg4Text.getText().toString();
                String mg5 = mg5Text.getText().toString();
                String mg_other = mg_otherText.getText().toString();
                String usage1 = usage1Text.getText().toString();
                String usage2 = usage2Text.getText().toString();
                String usage3 = usage3Text.getText().toString();
                String usage4 = usage4Text.getText().toString();
                String usage5 = usage5Text.getText().toString();
                String us_other = us_otherText.getText().toString();
                String livestock1 = livestock1Text.getText().toString();
                String livestock2 = livestock2Text.getText().toString();
                String livestock3 = livestock3Text.getText().toString();
                String livestock4 = livestock4Text.getText().toString();
                String livestock_other = livestock_otherText.getText().toString();
                String fodder1 = fodder1Text.getText().toString();
                String fodder2 = fodder2Text.getText().toString();
                String fodder3 = fodder3Text.getText().toString();
                String f_other = f_otherText.getText().toString();
                String farm = farmText.getText().toString();
                String market = marketText.getText().toString();
                String s_other = s_otherText.getText().toString();
                String date = dateText.getText().toString();
                String ename = enameText.getText().toString();

                //update the record
                dbAccess.updateFarmerTree(_id, project, name, village, country,select,in_date,latitude,longitude,altitude,accuracy,species,local,dbh,
                        height,base_circum,path,mg1,mg2,mg3,mg4,mg5,mg_other,usage1,usage2,usage3,usage4,usage5,us_other,
                        livestock1,livestock2,livestock3,livestock4,livestock_other,fodder1,fodder2,fodder3,f_other,farm,market,s_other,date,ename);
                Toast.makeText(ViewNurseryModifyActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            /*case R.id.btn_delete:
                //delete
                dbAccess.delete_farmer(_id);
                Toast.makeText(ModifyActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;*/
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), NurseryView.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
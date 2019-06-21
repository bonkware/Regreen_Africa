package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 10/22/18.
 * view all the data entered list from sqlite db
 */
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class NurseryView extends AppCompatActivity {
    private DbAccess dbAccess;
    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper._ID,DatabaseHelper.nursery_id,DatabaseHelper.nursery_enum_name,DatabaseHelper.nursery_date,DatabaseHelper.nursery_survey_name,
            DatabaseHelper.nursery_country,DatabaseHelper.nursery_county, DatabaseHelper.nursery_district, DatabaseHelper.nursery_operator,DatabaseHelper.nursery_contact,DatabaseHelper.nursery_name,DatabaseHelper.species_number,DatabaseHelper.n_date,DatabaseHelper.type_government,
            DatabaseHelper.type_church_mosque,DatabaseHelper.type_schools,DatabaseHelper.type_women_groups,DatabaseHelper.type_youth_groups,DatabaseHelper.type_private_individual,DatabaseHelper.type_communal_village,
            DatabaseHelper.other_nursery_types,DatabaseHelper.nursery_latitude,DatabaseHelper.nursery_longitude,DatabaseHelper.nursery_altitude,DatabaseHelper.nursery_accuracy,DatabaseHelper.nursery_image_path,
            DatabaseHelper.nursery_species,DatabaseHelper.nursery_local,DatabaseHelper.method_bare_root,DatabaseHelper.method_containerised,DatabaseHelper.other_methods,DatabaseHelper.propagation_seed,
            DatabaseHelper.propagation_graft,DatabaseHelper.propagation_cutting,DatabaseHelper.propagation_marcotting,DatabaseHelper.seed_source_onfarm,DatabaseHelper.seed_source_local_dealer,DatabaseHelper.seed_source_national_dealer,DatabaseHelper.seed_source_NGOs,DatabaseHelper.other_seed_sources,
            DatabaseHelper.graft_source_farmland,DatabaseHelper.graft_source_plantation,DatabaseHelper.graft_source_mother_blocks,DatabaseHelper.graft_source_prisons,DatabaseHelper.graft_source_others,
            DatabaseHelper.seeds_quantity_purchased,DatabaseHelper.qunits,DatabaseHelper.seed_sown,DatabaseHelper.unitsown,DatabaseHelper.date_seeds_sown,DatabaseHelper.seedlings_germinated,DatabaseHelper.seedlings_servived,DatabaseHelper.seedlings_age,DatabaseHelper.seedlings_price,DatabaseHelper.notes,
    };

    final int[] to = new int[] {R.id.id, R.id.nurseryID,R.id.ename,R.id.in_date,R.id.survey_name,R.id.country,R.id.county,R.id.district, R.id.operator, R.id.contact,R.id.nursery_name,R.id.species_number,R.id.n_date, R.id.government,R.id.schools,R.id.mosque_church,R.id.women_grps,
            R.id.youth_grps,R.id.private_individual,R.id.communal_village,R.id.other_type,R.id.latitude,R.id.longitude,
            R.id.altitude,R.id.accuracy,R.id.path,R.id.nursery_species,R.id.nursery_local,
            R.id.bare_root,R.id.container,R.id.other,R.id.seed,R.id.graft,R.id.cutting,R.id.marcotting,R.id.own_farm,R.id.local_dealer,R.id.national_seed,R.id.ngos,R.id.other_s,R.id.farmland,R.id.plantation,R.id.mother_blocks,R.id.prisons,R.id.other_graft_sources,
            R.id.quantity_purchased,R.id.units,R.id.seeds_sown,R.id.units_sown,R.id.date_sown,R.id.germinated,R.id.survived,
            R.id.age,R.id.seedlings_price,R.id.notes
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listnusery);
        //set title
        setTitle("Nursery");

        dbAccess = new DbAccess(this);
        dbAccess.open();
        Cursor cursor = dbAccess.fetchNursery();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.nursery_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nid = (TextView) view.findViewById(R.id.nurseryID);
                TextView ename = (TextView) view.findViewById(R.id.ename);
                TextView in_date = (TextView) view.findViewById(R.id.in_date);
                TextView survey = (TextView) view.findViewById(R.id.survey_name);
                TextView country = (TextView) view.findViewById(R.id.country);
                TextView county = (TextView) view.findViewById(R.id.county);
                TextView district = (TextView) view.findViewById(R.id.district);
                TextView operator = (TextView) view.findViewById(R.id.operator);
                TextView contact = (TextView) view.findViewById(R.id.contact);

                TextView nursery_name = (TextView) view.findViewById(R.id.nursery_name);
                TextView species_number = (TextView) view.findViewById(R.id.species_number);
                TextView start_date = (TextView) view.findViewById(R.id.n_date);

                TextView government = (TextView) view.findViewById(R.id.government);
                TextView church_mosque = (TextView) view.findViewById(R.id.mosque_church);
                TextView schools = (TextView) view.findViewById(R.id.schools);
                TextView women_groups = (TextView) view.findViewById(R.id.women_grps);
                TextView youth_groups = (TextView) view.findViewById(R.id.youth_grps);
                TextView private_individual = (TextView) view.findViewById(R.id.private_individual);
                TextView communal_village = (TextView) view.findViewById(R.id.communal_village);
                TextView other_types = (TextView) view.findViewById(R.id.other_type);
                TextView latitude = (TextView) view.findViewById(R.id.latitude);
                TextView longitude = (TextView) view.findViewById(R.id.longitude);
                TextView altitude = (TextView) view.findViewById(R.id.altitude);
                TextView accuracy = (TextView) view.findViewById(R.id.accuracy);
                TextView image = (TextView) view.findViewById(R.id.path);


                TextView nursery_species = (TextView) view.findViewById(R.id.nursery_species);
                TextView nursery_local = (TextView) view.findViewById(R.id.nursery_local);
                TextView bare_root = (TextView) view.findViewById(R.id.bare_root);
                TextView container = (TextView) view.findViewById(R.id.container);
                TextView other = (TextView) view.findViewById(R.id.other);
                TextView seed = (TextView) view.findViewById(R.id.seed);
                TextView graft = (TextView) view.findViewById(R.id.graft);
                TextView cutting = (TextView) view.findViewById(R.id.cutting);
                TextView marcotting = (TextView) view.findViewById(R.id.marcotting);
                TextView own_farm = (TextView) view.findViewById(R.id.own_farm);
                TextView local_dealer = (TextView) view.findViewById(R.id.local_dealer);
                TextView national_seed = (TextView) view.findViewById(R.id.national_seed);
                TextView ngos = (TextView) view.findViewById(R.id.ngos);
                TextView other_sources = (TextView) view.findViewById(R.id.other_s);
                TextView farmland = (TextView) view.findViewById(R.id.farmland);
                TextView plantation = (TextView) view.findViewById(R.id.plantation);
                TextView m_blocks = (TextView) view.findViewById(R.id.mother_blocks);
                TextView prisons = (TextView) view.findViewById(R.id.prisons);
                TextView other_graft = (TextView) view.findViewById(R.id.other_graft_sources);

                TextView qpurchased = (TextView) view.findViewById(R.id.quantity_purchased);
                TextView units = (TextView) view.findViewById(R.id.units);
                TextView seed_sown = (TextView) view.findViewById(R.id.seeds_sown);
                TextView units_sown = (TextView) view.findViewById(R.id.units_sown);
                TextView date_sown = (TextView) view.findViewById(R.id.date_sown);
                TextView seedlings_germinated = (TextView) view.findViewById(R.id.germinated);
                TextView seedlings_survived = (TextView) view.findViewById(R.id.survived);
                TextView seedlings_age = (TextView) view.findViewById(R.id.age);
                TextView seedlings_price = (TextView) view.findViewById(R.id.seedlings_price);
                TextView notes = (TextView) view.findViewById(R.id.notes);



                String id = idTextView.getText().toString();
                String nursery_id = nid.getText().toString();
                String nursery_country = country.getText().toString();
                String enume = ename.getText().toString();
                String date = in_date.getText().toString();
                String survey_name = survey.getText().toString();
                String nursery_county = county.getText().toString();
                String nursery_district = district.getText().toString();
                String nursery_operator = operator.getText().toString();
                String nursery_contact = contact.getText().toString();
                String n_name = nursery_name.getText().toString();
                String s_number = species_number.getText().toString();
                String s_date = start_date.getText().toString();
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
                String other_source = other_sources.getText().toString();
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
                String note = notes.getText().toString();


                Intent modify_intent = new Intent(getApplicationContext(), NurseryEdit.class);
                modify_intent.putExtra("nid", nursery_id);
                modify_intent.putExtra("enume", enume);
                modify_intent.putExtra("date", date);
                modify_intent.putExtra("survey_name", survey_name);
                modify_intent.putExtra("nursery_country", nursery_country);
                modify_intent.putExtra("nursery_county", nursery_county);
                modify_intent.putExtra("nursery_village", nursery_district);
                modify_intent.putExtra("nursery_operator", nursery_operator);
                modify_intent.putExtra("nursery_contact", nursery_contact);
                modify_intent.putExtra("nursery_name", n_name);
                modify_intent.putExtra("species_number", s_number);
                modify_intent.putExtra("n_date", s_date);
                modify_intent.putExtra("government", govt);
                modify_intent.putExtra("church_mosque", cm);
                modify_intent.putExtra("schools", sch);
                modify_intent.putExtra("women_groups", wg);
                modify_intent.putExtra("youth_groups", youth_group);
                modify_intent.putExtra("private_individual", pi);
                modify_intent.putExtra("communal_village", cv);
                modify_intent.putExtra("other_types", ot);
                modify_intent.putExtra("latitude", n_latitude);
                modify_intent.putExtra("longitude", n_longitude);
                modify_intent.putExtra("altitude", n_altitude);
                modify_intent.putExtra("accuracy", n_accuracy);
                modify_intent.putExtra("image", path);
                modify_intent.putExtra("nursery_species", species);
                modify_intent.putExtra("nursery_local", local);
                modify_intent.putExtra("bare_root", bare);
                modify_intent.putExtra("container", containerised);
                modify_intent.putExtra("other", other_method);
                modify_intent.putExtra("seed", sd);
                modify_intent.putExtra("graft", gf);
                modify_intent.putExtra("cutting", ct);
                modify_intent.putExtra("marcotting", mt);
                modify_intent.putExtra("own_farm", ownfarm);
                modify_intent.putExtra("local_dealer", localdealer);
                modify_intent.putExtra("national_seed", nationalseed);
                modify_intent.putExtra("ngos", ng);
                modify_intent.putExtra("other_s", other_source);
                modify_intent.putExtra("farmland", fm);
                modify_intent.putExtra("plantation", pt);
                modify_intent.putExtra("m_blocks", mb);
                modify_intent.putExtra("prisons", pr);
                modify_intent.putExtra("other_graft", og);
                modify_intent.putExtra("qpurchased", purchased);
                modify_intent.putExtra("units", unit);
                modify_intent.putExtra("seed_sown", seedsown);
                modify_intent.putExtra("units_sown", sownunits);
                modify_intent.putExtra("date_sown", datesown);
                modify_intent.putExtra("seedlings_germinated", germ);
                modify_intent.putExtra("seedlings_survived", surv);
                modify_intent.putExtra("seedlings_age", sage);
                modify_intent.putExtra("seedlings_price", sprice);
                modify_intent.putExtra("nursery_notes", note);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
        //for previous/back button
        Button button_next = (Button) findViewById(R.id.prev);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.prev:
                        Intent intent = new Intent(getApplicationContext(), ViewMainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add) {

            // Intent add_mem = new Intent(this, Index.class);
            // startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}

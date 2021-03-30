package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 9/5/19.
 * view all the data entered list from sqlite db
 */
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TPView extends AppCompatActivity {
    private DbAccess dbAccess;
    private ListView listView;
    EditText inputSearch;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,DatabaseHelper.farmer_id,
            DatabaseHelper.enum_name,DatabaseHelper.date,DatabaseHelper.survey_name, DatabaseHelper.farmer_inst_name, DatabaseHelper.country,DatabaseHelper.county_region,DatabaseHelper.district,DatabaseHelper.land_individual,DatabaseHelper.land_community,DatabaseHelper.land_government,DatabaseHelper.land_mosque_church,DatabaseHelper.land_schools,DatabaseHelper.land_other,DatabaseHelper.tp_crops,DatabaseHelper.tp_croplist,DatabaseHelper.landsize_regreen,DatabaseHelper.tp_units,
            DatabaseHelper.species_name,DatabaseHelper.date_planted,DatabaseHelper.number_planted,DatabaseHelper.number_survived,DatabaseHelper.woodlot,DatabaseHelper.iboundary,
            DatabaseHelper.eboundary,DatabaseHelper.garden,DatabaseHelper.crop_field,DatabaseHelper.pasture_grassland,DatabaseHelper.fallow_bushland,
            DatabaseHelper.other_sites,DatabaseHelper.management_pruning,DatabaseHelper.management_fencing,DatabaseHelper.management_weeding,DatabaseHelper.management_watering,DatabaseHelper.management_organic_fertilizer,DatabaseHelper.management_other,DatabaseHelper.use_firewood,DatabaseHelper.use_housing_construction,DatabaseHelper.use_animal_feed,DatabaseHelper.use_food,DatabaseHelper.use_mulching,DatabaseHelper.use_medicinal,DatabaseHelper.use_other,DatabaseHelper.tree_height,DatabaseHelper.tree_dbh,
            DatabaseHelper.tree_image_path,DatabaseHelper.tree_latitude,DatabaseHelper.tree_longitude,
            DatabaseHelper.tree_altitude,DatabaseHelper.tree_accuracy,DatabaseHelper.tp_notes,
    };

    final int[] to = new int[] { R.id.id, R.id.fid,R.id.ename, R.id.in_date, R.id.survey_name, R.id.fnames, R.id.country,R.id.county,R.id.district,R.id.own,
            R.id.comm_land,R.id.govt_land,R.id.mosque_church,R.id.schools,R.id.other_locations,R.id.crops,R.id.croplist,
            R.id.landestimate,R.id.units,R.id.species,R.id.date_planted,R.id.number_planted,R.id.number_survived,R.id.woodlot,R.id.iboundary,
            R.id.eboundary,R.id.garden,R.id.crop_field,R.id.pasture_grassland,R.id.fallow_pushland,R.id.other_sites,R.id.mg1,
            R.id.mg2,R.id.mg3,R.id.mg4,R.id.mg5,R.id.mg_other,
            R.id.usage1,R.id.usage2,R.id.usage3,R.id.usage4,R.id.usage5,R.id.usage6,R.id.us_other,R.id.height,R.id.db_rc,R.id.path,R.id.tree_latitude,R.id.tree_longitude,
            R.id.tree_altitude,R.id.tree_accuracy,R.id.notes,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listtp);
        //set title
        setTitle("Tree planting");

        dbAccess = new DbAccess(this);
        dbAccess.open();
        Cursor cursor = dbAccess.fetchTP();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.tp_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView fidTextView = (TextView) view.findViewById(R.id.fid);
                TextView enumTextView = (TextView) view.findViewById(R.id.ename);
                TextView in_dateTextView = (TextView) view.findViewById(R.id.in_date);
                TextView surveyTextView = (TextView) view.findViewById(R.id.survey_name);
                TextView nameTextView = (TextView) view.findViewById(R.id.fnames);
                TextView countryTextView = (TextView) view.findViewById(R.id.country);
                TextView countyTextView = (TextView) view.findViewById(R.id.county);
                TextView districtTextView = (TextView) view.findViewById(R.id.district);
                TextView ownTextView = (TextView) view.findViewById(R.id.own);
                TextView commTextView = (TextView) view.findViewById(R.id.comm_land);
                TextView govtTextView = (TextView) view.findViewById(R.id.govt_land);
                TextView m_sTextView = (TextView) view.findViewById(R.id.mosque_church);
                TextView schoolsTextView = (TextView) view.findViewById(R.id.schools);
                TextView otTextView = (TextView) view.findViewById(R.id.other_locations);
                TextView cropsTextView = (TextView) view.findViewById(R.id.crops);
                TextView croplistTextView = (TextView) view.findViewById(R.id.croplist);
                TextView landsizeTextView = (TextView) view.findViewById(R.id.landestimate);
                TextView unitTextView = (TextView) view.findViewById(R.id.units);
                TextView speciesTextView = (TextView) view.findViewById(R.id.species);
                TextView datepTextView = (TextView) view.findViewById(R.id.date_planted);
                TextView plantedTextView = (TextView) view.findViewById(R.id.number_planted);
                TextView survivedTextView = (TextView) view.findViewById(R.id.number_survived);
                TextView wTextView = (TextView) view.findViewById(R.id.woodlot);
                TextView ibTextView = (TextView) view.findViewById(R.id.iboundary);
                TextView ebTextView = (TextView) view.findViewById(R.id.eboundary);
                TextView gardenTextView = (TextView) view.findViewById(R.id.garden);
                TextView crop_fieldTextView = (TextView) view.findViewById(R.id.crop_field);
                TextView pgTextView = (TextView) view.findViewById(R.id.pasture_grassland);
                TextView fTextView = (TextView) view.findViewById(R.id.fallow_pushland);
                TextView oTextView = (TextView) view.findViewById(R.id.other_sites);
                TextView mg1TextView = (TextView) view.findViewById(R.id.mg1);
                TextView mg2TextView = (TextView) view.findViewById(R.id.mg2);
                TextView mg3TextView = (TextView) view.findViewById(R.id.mg3);
                TextView mg4TextView = (TextView) view.findViewById(R.id.mg4);
                TextView mg5TextView = (TextView) view.findViewById(R.id.mg5);
                TextView mg_otherTextView = (TextView) view.findViewById(R.id.mg_other);
                TextView usage1TextView = (TextView) view.findViewById(R.id.usage1);
                TextView usage2TextView = (TextView) view.findViewById(R.id.usage2);
                TextView usage3TextView = (TextView) view.findViewById(R.id.usage3);
                TextView usage4TextView = (TextView) view.findViewById(R.id.usage4);
                TextView usage5TextView = (TextView) view.findViewById(R.id.usage5);
                TextView usage6TextView = (TextView) view.findViewById(R.id.usage6);
                TextView us_otherTextView = (TextView) view.findViewById(R.id.us_other);
                TextView hTextView = (TextView) view.findViewById(R.id.height);
                TextView dbTextView = (TextView) view.findViewById(R.id.db_rc);
                TextView pathTextView = (TextView) view.findViewById(R.id.path);
                TextView latitudeTextView = (TextView) view.findViewById(R.id.tree_latitude);
                TextView longitudeTextView = (TextView) view.findViewById(R.id.tree_longitude);
                TextView altitudeTextView = (TextView) view.findViewById(R.id.tree_altitude);
                TextView accuracyTextView = (TextView) view.findViewById(R.id.tree_accuracy);
                TextView notesTextView = (TextView) view.findViewById(R.id.notes);

                String id = idTextView.getText().toString();
                String fid = fidTextView.getText().toString();
                String enume = enumTextView.getText().toString();
                String in_date = in_dateTextView.getText().toString();
                String survey = surveyTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String country = countryTextView.getText().toString();
                String county = countyTextView.getText().toString();
                String district = districtTextView.getText().toString();
                String own = ownTextView.getText().toString();
                String community_land = commTextView.getText().toString();
                String government_land = govtTextView.getText().toString();
                String mosque_church = m_sTextView.getText().toString();
                String school = schoolsTextView.getText().toString();
                String other_locations = otTextView.getText().toString();
                String crops = cropsTextView.getText().toString();
                String cropl = croplistTextView.getText().toString();
                String landsize = landsizeTextView.getText().toString();
                String unit = unitTextView.getText().toString();
                String species = speciesTextView.getText().toString();
                String date_planted = datepTextView.getText().toString();
                String planted = plantedTextView.getText().toString();
                String survived = survivedTextView.getText().toString();
                String woodlot = wTextView.getText().toString();
                String iboundary = ibTextView.getText().toString();
                String eboundary = ebTextView.getText().toString();
                String garden = gardenTextView.getText().toString();
                String crop = crop_fieldTextView.getText().toString();
                String pasture = pgTextView.getText().toString();
                String fallow = fTextView.getText().toString();
                String oth = oTextView.getText().toString();
                String mg1 = mg1TextView.getText().toString();
                String mg2 = mg2TextView.getText().toString();
                String mg3 = mg3TextView.getText().toString();
                String mg4 = mg4TextView.getText().toString();
                String mg5 = mg5TextView.getText().toString();
                String mg_other = mg_otherTextView.getText().toString();
                String usage1 = usage1TextView.getText().toString();
                String usage2 = usage2TextView.getText().toString();
                String usage3 = usage3TextView.getText().toString();
                String usage4 = usage4TextView.getText().toString();
                String usage5 = usage5TextView.getText().toString();
                String usage6 = usage6TextView.getText().toString();
                String us_other = us_otherTextView.getText().toString();
                String height = hTextView.getText().toString();
                String dcr = dbTextView.getText().toString();
                String image = pathTextView.getText().toString();
                String latitude = latitudeTextView.getText().toString();
                String longitude = longitudeTextView.getText().toString();
                String altitude = altitudeTextView.getText().toString();
                String accuracy = accuracyTextView.getText().toString();
                String notes = notesTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), TPEdit.class);
                modify_intent.putExtra("fid", fid);
                modify_intent.putExtra("enume", enume);
                modify_intent.putExtra("in_date", in_date);
                modify_intent.putExtra("survey", survey);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("country", country);
                modify_intent.putExtra("county", county);
                modify_intent.putExtra("district", district);
                modify_intent.putExtra("own", own);
                modify_intent.putExtra("community_land", community_land);
                modify_intent.putExtra("government_land", government_land);
                modify_intent.putExtra("mosque_church", mosque_church);
                modify_intent.putExtra("school", school);
                modify_intent.putExtra("other_locations", other_locations);
                modify_intent.putExtra("crops", landsize);
                modify_intent.putExtra("croplist", crops);
                modify_intent.putExtra("landsize", cropl);
                modify_intent.putExtra("unit", unit);
                modify_intent.putExtra("species", species);
                modify_intent.putExtra("date_planted", date_planted);
                modify_intent.putExtra("number_planted", planted);
                modify_intent.putExtra("number_survived", survived);
                //modify_intent.putExtra("height", height);
                modify_intent.putExtra("woodlot", woodlot);
                modify_intent.putExtra("iboundary", iboundary);
                modify_intent.putExtra("eboundary", eboundary);
                modify_intent.putExtra("garden", garden);
                modify_intent.putExtra("crop", crop);
                modify_intent.putExtra("pasture", pasture);
                modify_intent.putExtra("fallow", fallow);
                modify_intent.putExtra("oth", oth);
                modify_intent.putExtra("mg1", mg1);
                modify_intent.putExtra("mg2", mg2);
                modify_intent.putExtra("mg3", mg3);
                modify_intent.putExtra("mg4", mg4);
                modify_intent.putExtra("mg5", mg5);
                modify_intent.putExtra("mg_other", mg_other);
                modify_intent.putExtra("usage1", usage1);
                modify_intent.putExtra("usage2", usage2);
                modify_intent.putExtra("usage3", usage3);
                modify_intent.putExtra("usage4", usage4);
                modify_intent.putExtra("usage5", usage5);
                modify_intent.putExtra("usage6", usage6);
                modify_intent.putExtra("us_other", us_other);
                modify_intent.putExtra("height", height);
                modify_intent.putExtra("dcr", dcr);
                modify_intent.putExtra("path", image);
                modify_intent.putExtra("latitude", latitude);
                modify_intent.putExtra("longitude", longitude);
                modify_intent.putExtra("altitude", altitude);
                modify_intent.putExtra("accuracy", accuracy);
                modify_intent.putExtra("notes", notes);
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

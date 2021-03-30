package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 10/5/19.
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
import android.widget.ListView;
import android.widget.TextView;

public class FMNRView extends AppCompatActivity {
    private DbAccess dbAccess;
    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,DatabaseHelper.fmnr_farmer_id,
            DatabaseHelper.fmnr_enum_name,DatabaseHelper.fmnr_date,DatabaseHelper.famnr_survey_name, DatabaseHelper.fmnr_farmer_inst_name, DatabaseHelper.fmnr_country,DatabaseHelper.fmnr_county_region,DatabaseHelper.fmnr_district,DatabaseHelper.fmnr_land_individual,DatabaseHelper.fmnr_land_community,DatabaseHelper.fmnr_land_government,DatabaseHelper.fmnr_land_mosque_church,DatabaseHelper.fmnr_land_schools,DatabaseHelper.fmnr_land_other,DatabaseHelper.fmnr_species_number_start,DatabaseHelper.fmnr_started_date,DatabaseHelper.fmnr_fenced,DatabaseHelper.fmnr_crops,DatabaseHelper.fmnr_croplist,DatabaseHelper.fmnr_landsize_regreen,DatabaseHelper.fmnr_units,
            DatabaseHelper.fmnr_species_name,DatabaseHelper.fmnr_local_name,DatabaseHelper.fmnr_management_pruning,DatabaseHelper.fmnr_management_fencing,DatabaseHelper.fmnr_management_weeding,DatabaseHelper.fmnr_management_thinning,DatabaseHelper.fmnr_management_organic_fertilizer,DatabaseHelper.fmnr_management_pollarding_lopping,DatabaseHelper.fmnr_management_coppicing,DatabaseHelper.fmnr_management_other,
            DatabaseHelper.fmnr_use_firewood,DatabaseHelper.fmnr_use_housing_construction,DatabaseHelper.fmnr_use_fodder,DatabaseHelper.fmnr_use_fruits,DatabaseHelper.fmnr_use_soil_fertility,DatabaseHelper.fmnr_use_leafy_vegetables,DatabaseHelper.fmnr_use_nuts,DatabaseHelper.fmnr_use_medicinal,DatabaseHelper.fmnr_use_other,DatabaseHelper.fmnr_tree_stems, DatabaseHelper.fmnr_tree_height,DatabaseHelper.fmnr_tree_dbh,DatabaseHelper.fmnr_tree_image_path,DatabaseHelper.fmnr_tree_latitude,DatabaseHelper.fmnr_tree_longitude,
            DatabaseHelper.fmnr_tree_altitude,DatabaseHelper.fmnr_tree_accuracy,DatabaseHelper.fmnr_notes
    };

    final int[] to = new int[] { R.id.id, R.id.fid,R.id.ename,R.id.in_date, R.id.survey_name, R.id.fnames, R.id.country,R.id.county,R.id.district,R.id.own,
            R.id.comm_land,R.id.govt_land,R.id.mosque_church,R.id.schools,R.id.other_locations,R.id.number_start,R.id.start_date,R.id.fenced,R.id.crops,R.id.croplist,
            R.id.landsize,R.id.units,R.id.species,R.id.local_name,R.id.mg1,
            R.id.mg2,R.id.mg3,R.id.mg4,R.id.mg5,R.id.mg6,R.id.mg7,R.id.mg_other,R.id.usage1,R.id.usage2,R.id.usage3,R.id.usage4,R.id.usage5,R.id.usage6,R.id.usage7,R.id.usage8,R.id.us_other,R.id.stems,R.id.height,R.id.dbh,R.id.path,R.id.tree_latitude,R.id.tree_longitude,
            R.id.tree_altitude,R.id.tree_accuracy,R.id.notes
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listfmnr);
        //set title
        setTitle("FMNR");

        dbAccess = new DbAccess(this);
        dbAccess.open();
        Cursor cursor = dbAccess.fetchFMNR();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.fmnr_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView fidTextView = (TextView) view.findViewById(R.id.fid);
                TextView enumTextView = (TextView) view.findViewById(R.id.ename);
                TextView dateTextView = (TextView) view.findViewById(R.id.in_date);
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

                TextView nstartTextView = (TextView) view.findViewById(R.id.number_start);
                TextView sdateTextView = (TextView) view.findViewById(R.id.start_date);
                TextView fencedTextView = (TextView) view.findViewById(R.id.fenced);
                TextView cropsTextView = (TextView) view.findViewById(R.id.crops);
                TextView croplistTextView = (TextView) view.findViewById(R.id.croplist);
                TextView landsizeTextView = (TextView) view.findViewById(R.id.landsize);
                TextView unitTextView = (TextView) view.findViewById(R.id.units);
                TextView speciesTextView = (TextView) view.findViewById(R.id.species);
                TextView localTextView = (TextView) view.findViewById(R.id.local_name);
                TextView mg1TextView = (TextView) view.findViewById(R.id.mg1);
                TextView mg2TextView = (TextView) view.findViewById(R.id.mg2);
                TextView mg3TextView = (TextView) view.findViewById(R.id.mg3);
                TextView mg4TextView = (TextView) view.findViewById(R.id.mg4);
                TextView mg5TextView = (TextView) view.findViewById(R.id.mg5);
                TextView mg6TextView = (TextView) view.findViewById(R.id.mg6);
                TextView mg7TextView = (TextView) view.findViewById(R.id.mg7);
                TextView mg_otherTextView = (TextView) view.findViewById(R.id.mg_other);
                TextView usage1TextView = (TextView) view.findViewById(R.id.usage1);
                TextView usage2TextView = (TextView) view.findViewById(R.id.usage2);
                TextView usage3TextView = (TextView) view.findViewById(R.id.usage3);
                TextView usage4TextView = (TextView) view.findViewById(R.id.usage4);
                TextView usage5TextView = (TextView) view.findViewById(R.id.usage5);
                TextView usage6TextView = (TextView) view.findViewById(R.id.usage6);
                TextView usage7TextView = (TextView) view.findViewById(R.id.usage7);
                TextView usage8TextView = (TextView) view.findViewById(R.id.usage8);
                TextView us_otherTextView = (TextView) view.findViewById(R.id.us_other);
                TextView stemsTextView = (TextView) view.findViewById(R.id.stems);
                TextView heightTextView = (TextView) view.findViewById(R.id.height);
                TextView dbhTextView = (TextView) view.findViewById(R.id.dbh);
                TextView pathTextView = (TextView) view.findViewById(R.id.path);
                TextView latitudeTextView = (TextView) view.findViewById(R.id.tree_latitude);
                TextView longitudeTextView = (TextView) view.findViewById(R.id.tree_longitude);
                TextView altitudeTextView = (TextView) view.findViewById(R.id.tree_altitude);
                TextView accuracyTextView = (TextView) view.findViewById(R.id.tree_accuracy);
                TextView notesTextView = (TextView) view.findViewById(R.id.notes);

                String id = idTextView.getText().toString();
                String fid = fidTextView.getText().toString();
                String enume = enumTextView.getText().toString();
                String in_date = dateTextView.getText().toString();
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
                String number_start = nstartTextView.getText().toString();
                String start_date = sdateTextView.getText().toString();
                String fenced = fencedTextView.getText().toString();
                String crop = cropsTextView.getText().toString();
                String cropl = croplistTextView.getText().toString();
                String landsize = landsizeTextView.getText().toString();
                String unit = unitTextView.getText().toString();
                String species = speciesTextView.getText().toString();
                String local_name = localTextView.getText().toString();
                String mg1 = mg1TextView.getText().toString();
                String mg2 = mg2TextView.getText().toString();
                String mg3 = mg3TextView.getText().toString();
                String mg4 = mg4TextView.getText().toString();
                String mg5 = mg5TextView.getText().toString();
                String mg6 = mg6TextView.getText().toString();
                String mg7 = mg7TextView.getText().toString();
                String mg_other = mg_otherTextView.getText().toString();
                String usage1 = usage1TextView.getText().toString();
                String usage2 = usage2TextView.getText().toString();
                String usage3 = usage3TextView.getText().toString();
                String usage4 = usage4TextView.getText().toString();
                String usage5 = usage5TextView.getText().toString();
                String usage6 = usage6TextView.getText().toString();
                String usage7 = usage7TextView.getText().toString();
                String usage8 = usage8TextView.getText().toString();
                String us_other = us_otherTextView.getText().toString();
                String stems = stemsTextView.getText().toString();
                String height = heightTextView.getText().toString();
                String dbh = dbhTextView.getText().toString();
                String image = pathTextView.getText().toString();
                String latitude = latitudeTextView.getText().toString();
                String longitude = longitudeTextView.getText().toString();
                String altitude = altitudeTextView.getText().toString();
                String accuracy = accuracyTextView.getText().toString();
                String note = notesTextView.getText().toString();


                Intent modify_intent = new Intent(getApplicationContext(), FMNREdit.class);
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
                modify_intent.putExtra("number_start", number_start);
                modify_intent.putExtra("start_date", start_date);
                modify_intent.putExtra("fenced", fenced);
                modify_intent.putExtra("crops", crop);
                modify_intent.putExtra("croplist", cropl);
                modify_intent.putExtra("landsize", landsize);
                modify_intent.putExtra("unit", unit);
                modify_intent.putExtra("species", species);
                modify_intent.putExtra("local_name", local_name);

                modify_intent.putExtra("mg1", mg1);
                modify_intent.putExtra("mg2", mg2);
                modify_intent.putExtra("mg3", mg3);
                modify_intent.putExtra("mg4", mg4);
                modify_intent.putExtra("mg5", mg5);
                modify_intent.putExtra("mg6", mg6);
                modify_intent.putExtra("mg7", mg7);
                modify_intent.putExtra("mg_other", mg_other);
                modify_intent.putExtra("usage1", usage1);
                modify_intent.putExtra("usage2", usage2);
                modify_intent.putExtra("usage3", usage3);
                modify_intent.putExtra("usage4", usage4);
                modify_intent.putExtra("usage5", usage5);
                modify_intent.putExtra("usage6", usage6);
                modify_intent.putExtra("usage7", usage7);
                modify_intent.putExtra("usage8", usage8);
                modify_intent.putExtra("us_other", us_other);

                modify_intent.putExtra("stems", stems);
                modify_intent.putExtra("height", height);
                modify_intent.putExtra("dbh", dbh);
                modify_intent.putExtra("path", image);
                modify_intent.putExtra("latitude", latitude);
                modify_intent.putExtra("longitude", longitude);
                modify_intent.putExtra("altitude", altitude);
                modify_intent.putExtra("accuracy", accuracy);
                modify_intent.putExtra("notes", note);
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

package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 5/12/19.
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

public class TrainingView extends AppCompatActivity {
    private DbAccess dbAccess;
    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._id,DatabaseHelper.training_enum_name,
            DatabaseHelper.training_record_date,DatabaseHelper.training_survey_name,DatabaseHelper.training_country, DatabaseHelper.training_region, DatabaseHelper.training_district,DatabaseHelper.training_topic,DatabaseHelper.training_date,DatabaseHelper.training_venue,DatabaseHelper.training_partners,DatabaseHelper.training_participants,DatabaseHelper.male_participants,DatabaseHelper.female_participants,DatabaseHelper.youth_participants
    };

    final int[] to = new int[] { R.id.id,R.id.ename,R.id.in_date, R.id.survey_name,R.id.country,R.id.county,
            R.id.district,R.id.training_topic,R.id.training_date,R.id.training_venue,R.id.training_partners,
            R.id.total_participants,R.id.male_participants,R.id.female_participants,
            R.id.youth_participants
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listtraining);

        dbAccess = new DbAccess(this);
        dbAccess.open();
        Cursor cursor = dbAccess.fetchTrainings();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.training_view, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView enumTextView = (TextView) view.findViewById(R.id.ename);
                TextView dateTextView = (TextView) view.findViewById(R.id.in_date);
                TextView surveyTextView = (TextView) view.findViewById(R.id.survey_name);
                TextView countryTextView = (TextView) view.findViewById(R.id.country);
                TextView countyTextView = (TextView) view.findViewById(R.id.county);
                TextView districtTextView = (TextView) view.findViewById(R.id.district);
                TextView topicTextView = (TextView) view.findViewById(R.id.training_topic);
                TextView tdateTextView = (TextView) view.findViewById(R.id.training_date);
                TextView tvenueTextView = (TextView) view.findViewById(R.id.training_venue);
                TextView tpartnersTextView = (TextView) view.findViewById(R.id.training_partners);
                TextView participantTextView = (TextView) view.findViewById(R.id.total_participants);
                TextView maleTextView = (TextView) view.findViewById(R.id.male_participants);
                TextView femaleTextView = (TextView) view.findViewById(R.id.female_participants);
                TextView youthTextView = (TextView) view.findViewById(R.id.youth_participants);

                String id = idTextView.getText().toString();
                String enume = enumTextView.getText().toString();
                String in_date = dateTextView.getText().toString();
                String survey = surveyTextView.getText().toString();
                String country = countryTextView.getText().toString();
                String county = countyTextView.getText().toString();
                String district = districtTextView.getText().toString();
                String topic = topicTextView.getText().toString();
                String tdate = tdateTextView.getText().toString();
                String tvenue = tvenueTextView.getText().toString();
                String tpartners = tpartnersTextView.getText().toString();
                String tparticipants = participantTextView.getText().toString();
                String male = maleTextView.getText().toString();
                String female = femaleTextView.getText().toString();
                String youth = youthTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), TrainingEdit.class);
                modify_intent.putExtra("enume", enume);
                modify_intent.putExtra("in_date", in_date);
                modify_intent.putExtra("survey", survey);
                modify_intent.putExtra("country", country);
                modify_intent.putExtra("county", county);
                modify_intent.putExtra("district", district);
                modify_intent.putExtra("topic", topic);
                modify_intent.putExtra("tdate", tdate);
                modify_intent.putExtra("tvenue", tvenue);
                modify_intent.putExtra("tpartners", tpartners);
                modify_intent.putExtra("tparticipants", tparticipants);
                modify_intent.putExtra("male", male);
                modify_intent.putExtra("female", female);
                modify_intent.putExtra("youth", youth);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
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

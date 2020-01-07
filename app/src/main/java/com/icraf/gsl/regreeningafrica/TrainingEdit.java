package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 5/12/19.
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

public class TrainingEdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private EditText enumText,in_dateText,surveyText,countryText,cText,dText,topicText,typeText,dateText,venueText,partnersText,participantsText,
            maleText,femaleText,youthText,notesText;

    private long _id;

    private DbAccess dbAccess;
    // ICRAF_FMNR_global g = ICRAF_FMNR_global.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.training_edit);

        //for previous/back button
        final Button button_prev = (Button) findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(TrainingEdit.this, TrainingView.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();

            }
        });
        //DB access
        dbAccess = new DbAccess(this);
        dbAccess.open();

        enumText = (EditText) findViewById(R.id.enume);
        in_dateText = (EditText) findViewById(R.id.in_date);
        surveyText = (EditText) findViewById(R.id.survey_name);
        countryText = (EditText) findViewById(R.id.country);
        cText = (EditText) findViewById(R.id.county);
        dText = (EditText) findViewById(R.id.district);
        topicText = (EditText) findViewById(R.id.topic);
        typeText = (EditText) findViewById(R.id.type);
        dateText = (EditText) findViewById(R.id.date);
        venueText = (EditText) findViewById(R.id.venue);
        partnersText = (EditText) findViewById(R.id.partners);
        participantsText = (EditText) findViewById(R.id.participants);
        maleText = (EditText) findViewById(R.id.male);
        femaleText = (EditText) findViewById(R.id.female);
        youthText = (EditText) findViewById(R.id.youth);
        notesText = (EditText) findViewById(R.id.notes);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        //disable delete button
        updateBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        //send data to a new activity
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String enume = intent.getStringExtra("enume");
        String in_date = intent.getStringExtra("in_date");
        String survey = intent.getStringExtra("survey");
        String country = intent.getStringExtra("country");
        String county = intent.getStringExtra("county");
        String district = intent.getStringExtra("district");
        String topic = intent.getStringExtra("topic");
        String type = intent.getStringExtra("type");
        String tdate = intent.getStringExtra("tdate");
        String tvenue = intent.getStringExtra("tvenue");
        String tpartners = intent.getStringExtra("tpartners");
        String tparticipants = intent.getStringExtra("tparticipants");
        String male = intent.getStringExtra("male");
        String female = intent.getStringExtra("female");
        String youth = intent.getStringExtra("youth");
        String note = intent.getStringExtra("notes");
        _id = Long.parseLong(id);

        // farmeridText.setText(farmerid);
        enumText.setText(enume);
        in_dateText.setText(in_date);
        surveyText.setText(survey);
        countryText.setText(country);
        cText.setText(county);
        dText.setText(district);
        topicText.setText(topic);
        typeText.setText(type);
        dateText.setText(tdate);
        venueText.setText(tvenue);
        partnersText.setText(tpartners);
        participantsText.setText(tparticipants);
        maleText.setText(male);
        femaleText.setText(female);
        youthText.setText(youth);
        notesText.setText(note);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                //update after edit
                String enume = enumText.getText().toString();
                String in_date = in_dateText.getText().toString();
                String survey = surveyText.getText().toString();
                String country = countryText.getText().toString();
                String county = cText.getText().toString();
                String district = dText.getText().toString();
                String topic = topicText.getText().toString();
                String type = typeText.getText().toString();
                String tdate = dateText.getText().toString();
                String tvenue = venueText.getText().toString();
                String tpartners = partnersText.getText().toString();
                String tparticipants = participantsText.getText().toString();
                String male = maleText.getText().toString();
                String female = femaleText.getText().toString();
                String youth = youthText.getText().toString();
                String note = notesText.getText().toString();

                //update the record
                dbAccess.updateTraining(_id, enume, in_date, survey, country,county,district,topic,type,tdate,tvenue,tpartners,tparticipants,male,
                        female,youth,note);
                Toast.makeText(TrainingEdit.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            case R.id.btn_delete:
                //delete
                dbAccess.delete_Training(_id);
                Toast.makeText(TrainingEdit.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), TrainingView.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
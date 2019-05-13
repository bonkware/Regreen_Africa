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

public class TrainingEdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private EditText enumText,in_dateText,surveyText,countryText,cText,dText,topicText,dateText,venueText,partnersText,participantsText,
            maleText,femaleText,youthText;

    private long _id;

    private DbAccess dbAccess;
    // ICRAF_FMNR_global g = ICRAF_FMNR_global.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.training_edit);
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
        dateText = (EditText) findViewById(R.id.date);
        venueText = (EditText) findViewById(R.id.venue);
        partnersText = (EditText) findViewById(R.id.partners);
        participantsText = (EditText) findViewById(R.id.participants);
        maleText = (EditText) findViewById(R.id.male);
        femaleText = (EditText) findViewById(R.id.female);
        youthText = (EditText) findViewById(R.id.youth);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        //disable delete button
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
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
        String tdate = intent.getStringExtra("tdate");
        String tvenue = intent.getStringExtra("tvenue");
        String tpartners = intent.getStringExtra("tpartners");
        String tparticipants = intent.getStringExtra("tparticipants");
        String male = intent.getStringExtra("male");
        String female = intent.getStringExtra("female");
        String youth = intent.getStringExtra("youth");
        _id = Long.parseLong(id);

        // farmeridText.setText(farmerid);
        enumText.setText(enume);
        in_dateText.setText(in_date);
        surveyText.setText(survey);
        countryText.setText(country);
        cText.setText(county);
        dText.setText(district);
        topicText.setText(topic);
        dateText.setText(tdate);
        venueText.setText(tvenue);
        partnersText.setText(tpartners);
        participantsText.setText(tparticipants);
        maleText.setText(male);
        femaleText.setText(female);
        youthText.setText(youth);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                //update values
               /* String project = projectText.getText().toString();
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
                Toast.makeText(TPEdit.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;*/

            /*case R.id.btn_delete:
                //delete
                dbAccess.delete_farmer(_id);
                Toast.makeText(TPEdit.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;*/
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), TPView.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
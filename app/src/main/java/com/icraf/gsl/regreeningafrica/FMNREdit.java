package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 10/5/19.
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

public class FMNREdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private EditText enumText,nameText,surveyText,countryText,cText,dText,oText,clText,glText,mText,sText,
            owText,lsText,unsText,spText,localText,mg1Text,mg2Text,mg3Text,mg4Text,mg5Text,mg6Text,mg7Text,mg_otherText,
            usage1Text,usage2Text,usage3Text,usage4Text,usage5Text,usage6Text,usage7Text,us_otherText,
            in_dateText,nstartText,dateText,fencedText,cropsText,croplistText,notesText,usage8Text,
            cfText,pgText,fbText,osText,stemsText,heightText,dbhText,latText,lonText,altText,accText;

    private long _id;

    private DbAccess dbAccess;
    // ICRAF_FMNR_global g = ICRAF_FMNR_global.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.fmnr_edit);
        //for previous/back button
        final Button button_prev = (Button) findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FMNREdit.this, FMNRView.class);
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
        nameText = (EditText) findViewById(R.id.fnames);
        countryText = (EditText) findViewById(R.id.country);
        cText = (EditText) findViewById(R.id.county);
        dText = (EditText) findViewById(R.id.district);
        oText = (EditText) findViewById(R.id.own);
        clText = (EditText) findViewById(R.id.c_land);
        glText = (EditText) findViewById(R.id.g_land);
        mText = (EditText) findViewById(R.id.ms);
        sText = (EditText) findViewById(R.id.schools);
        owText = (EditText) findViewById(R.id.other_owner);
        nstartText = (EditText) findViewById(R.id.number_start);
        dateText = (EditText) findViewById(R.id.start_date);
        fencedText = (EditText) findViewById(R.id.fenced);
        cropsText = (EditText) findViewById(R.id.crops);
        croplistText = (EditText) findViewById(R.id.croplist);
        lsText = (EditText) findViewById(R.id.landsize);
        unsText = (EditText) findViewById(R.id.units);
        spText = (EditText) findViewById(R.id.species);
        localText = (EditText) findViewById(R.id.local);

        mg1Text = (EditText) findViewById(R.id.mg1);
        mg2Text = (EditText) findViewById(R.id.mg2);
        mg3Text = (EditText) findViewById(R.id.mg3);
        mg4Text = (EditText) findViewById(R.id.mg4);
        mg5Text = (EditText) findViewById(R.id.mg5);
        mg6Text = (EditText) findViewById(R.id.mg6);
        mg7Text = (EditText) findViewById(R.id.mg7);
        mg_otherText = (EditText) findViewById(R.id.mg_other);
        usage1Text = (EditText) findViewById(R.id.usage1);
        usage2Text = (EditText) findViewById(R.id.usage2);
        usage3Text = (EditText) findViewById(R.id.usage3);
        usage4Text = (EditText) findViewById(R.id.usage4);
        usage5Text = (EditText) findViewById(R.id.usage5);
        usage6Text = (EditText) findViewById(R.id.usage6);
        usage7Text = (EditText) findViewById(R.id.usage7);
        usage8Text = (EditText) findViewById(R.id.usage8);
        us_otherText = (EditText) findViewById(R.id.us_other);

        stemsText = (EditText) findViewById(R.id.stems);
        heightText = (EditText) findViewById(R.id.height);
        dbhText = (EditText) findViewById(R.id.dbh);
        latText = (EditText) findViewById(R.id.tree_latitude);
        lonText = (EditText) findViewById(R.id.tree_longitude);
        altText = (EditText) findViewById(R.id.tree_altitude);
        accText = (EditText) findViewById(R.id.tree_accuracy);
        notesText = (EditText) findViewById(R.id.notes);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        //send data to a new activity
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String enume = intent.getStringExtra("enume");
        String in_date = intent.getStringExtra("in_date");
        String survey = intent.getStringExtra("survey");
        String name = intent.getStringExtra("name");
        String country = intent.getStringExtra("country");
        String county = intent.getStringExtra("county");
        String district = intent.getStringExtra("district");
        String own = intent.getStringExtra("own");
        String comm_land = intent.getStringExtra("community_land");
        String gov_land = intent.getStringExtra("government_land");
        String ms = intent.getStringExtra("mosque_church");
        String schools = intent.getStringExtra("school");
        String other_owner = intent.getStringExtra("other_owner");
        String number_start = intent.getStringExtra("number_start");
        String start_date = intent.getStringExtra("start_date");
        String fenced = intent.getStringExtra("fenced");
        String crops = intent.getStringExtra("crops");
        String croplist = intent.getStringExtra("croplist");
        String landsize = intent.getStringExtra("landsize");
        String units = intent.getStringExtra("unit");
        String species = intent.getStringExtra("species");
        String local = intent.getStringExtra("local");
        String mg1 = intent.getStringExtra("mg1");
        String mg2 = intent.getStringExtra("mg2");
        String mg3 = intent.getStringExtra("mg3");
        String mg4 = intent.getStringExtra("mg4");
        String mg5 = intent.getStringExtra("mg5");
        String mg6 = intent.getStringExtra("mg6");
        String mg7 = intent.getStringExtra("mg7");
        String mg_other = intent.getStringExtra("mg_other");
        String usage1 = intent.getStringExtra("usage1");
        String usage2 = intent.getStringExtra("usage2");
        String usage3 = intent.getStringExtra("usage3");
        String usage4 = intent.getStringExtra("usage4");
        String usage5 = intent.getStringExtra("usage5");
        String usage6 = intent.getStringExtra("usage6");
        String usage7 = intent.getStringExtra("usage7");
        String usage8 = intent.getStringExtra("usage8");
        String us_other = intent.getStringExtra("us_other");
        String stem = intent.getStringExtra("stems");
        String height = intent.getStringExtra("height");
        String dbh = intent.getStringExtra("dbh");
        String lat = intent.getStringExtra("latitude");
        String lon = intent.getStringExtra("longitude");
        String alt = intent.getStringExtra("altitude");
        String acc = intent.getStringExtra("accuracy");
        String note = intent.getStringExtra("notes");
        _id = Long.parseLong(id);

        // farmeridText.setText(farmerid);
        enumText.setText(enume);
        in_dateText.setText(in_date);
        surveyText.setText(survey);
        nameText.setText(name);
        countryText.setText(country);
        cText.setText(county);
        dText.setText(district);
        oText.setText(own);
        clText.setText(comm_land);
        glText.setText(gov_land);
        mText.setText(ms);
        sText.setText(schools);
        owText.setText(other_owner);
        nstartText.setText(number_start);
        dateText.setText(start_date);
        fencedText.setText(fenced);
        cropsText.setText(crops);
        croplistText.setText(croplist);
        lsText.setText(landsize);
        unsText.setText(units);
        spText.setText(species);
        localText.setText(local);

        mg1Text.setText(mg1);
        mg2Text.setText(mg2);
        mg3Text.setText(mg3);
        mg4Text.setText(mg4);
        mg5Text.setText(mg5);
        mg6Text.setText(mg6);
        mg7Text.setText(mg7);
        mg_otherText.setText(mg_other);
        usage1Text.setText(usage1);
        usage2Text.setText(usage2);
        usage3Text.setText(usage3);
        usage4Text.setText(usage4);
        usage5Text.setText(usage5);
        usage6Text.setText(usage6);
        usage7Text.setText(usage7);
        usage8Text.setText(usage8);
        us_otherText.setText(us_other);
        stemsText.setText(stem);
        heightText.setText(height);
        dbhText.setText(dbh);
        latText.setText(lat);
        lonText.setText(lon);
        altText.setText(alt);
        accText.setText(acc);
        notesText.setText(note);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                //update values
                String ename = enumText.getText().toString();
                String date = in_dateText.getText().toString();
                String survey = surveyText.getText().toString();
                String name = nameText.getText().toString();
                String country = countryText.getText().toString();
                String county = cText.getText().toString();
                String district = dText.getText().toString();
                String own = oText.getText().toString();
                String c_land = clText.getText().toString();
                String g_land = glText.getText().toString();
                String m_c = mText.getText().toString();
                String schools = sText.getText().toString();
                String other_own = owText.getText().toString();
                String n_start = nstartText.getText().toString();
                String s_date = dateText.getText().toString();
                String fence = fencedText.getText().toString();
                String crop = cropsText.getText().toString();
                String cropl = croplistText.getText().toString();
                String landsize = lsText.getText().toString();
                String units = unsText.getText().toString();
                String species = spText.getText().toString();
                String local = localText.getText().toString();
                String mg1 = mg1Text.getText().toString();
                String mg2 = mg2Text.getText().toString();
                String mg3 = mg3Text.getText().toString();
                String mg4 = mg4Text.getText().toString();
                String mg5 = mg5Text.getText().toString();
                String mg6 = mg6Text.getText().toString();
                String mg7 = mg7Text.getText().toString();
                String mg_other = mg_otherText.getText().toString();
                String usage1 = usage1Text.getText().toString();
                String usage2 = usage2Text.getText().toString();
                String usage3 = usage3Text.getText().toString();
                String usage4 = usage4Text.getText().toString();
                String usage5 = usage5Text.getText().toString();
                String usage6 = usage6Text.getText().toString();
                String usage7 = usage7Text.getText().toString();
                String usage8 = usage8Text.getText().toString();
                String us_other = us_otherText.getText().toString();

                String stems = stemsText.getText().toString();
                String height = heightText.getText().toString();
                String dbh = dbhText.getText().toString();
                String lat = latText.getText().toString();
                String lon = lonText.getText().toString();
                String alt = altText.getText().toString();
                String acc = accText.getText().toString();
                String note = notesText.getText().toString();
                //update the record
                dbAccess.updateFMNRinfo(_id, ename, date, survey,name,country,county,district,own,c_land,g_land,m_c,schools,
                        other_own);
                dbAccess.updateFMNRplotinfo(_id, n_start,s_date,fence,crop,cropl,landsize,units);
                dbAccess.updateFMNRspecies(_id, species,local,mg1,mg2,mg3,mg4,mg5,mg6,mg7,mg_other,usage1,usage2,usage3,usage4,usage5,usage6,usage7,usage8,us_other,stems,
                        height,dbh,lat,lon,alt,acc,note);

                Toast.makeText(FMNREdit.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            case R.id.btn_delete:
                //delete
                dbAccess.delete_FMNRinfo(_id);
                dbAccess.delete_FMNRplotinfo(_id);
                dbAccess.delete_FMNRspecies(_id);
                Toast.makeText(FMNREdit.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), FMNRView.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
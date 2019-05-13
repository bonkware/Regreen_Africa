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

public class FMNREdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private EditText enumText,nameText,surveyText,countryText,cText,dText,oText,clText,glText,mText,sText,
            owText,lsText,unsText,spText,localText,mg1Text,mg2Text,mg3Text,mg4Text,mg5Text,mg6Text,mg7Text,mg_otherText,
            usage1Text,usage2Text,usage3Text,usage4Text,usage5Text,usage6Text,usage7Text,us_otherText,
            in_dateText,nstartText,dateText,fencedText,wText,ibText,ebText,gText,
            cfText,pgText,fbText,osText,stemsText,heightText,dbhText,latText,lonText,altText,accText;

    private long _id;

    private DbAccess dbAccess;
    // ICRAF_FMNR_global g = ICRAF_FMNR_global.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.fmnr_edit);
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
        us_otherText = (EditText) findViewById(R.id.us_other);

        stemsText = (EditText) findViewById(R.id.stems);
        heightText = (EditText) findViewById(R.id.height);
        dbhText = (EditText) findViewById(R.id.dbh);
        latText = (EditText) findViewById(R.id.tree_latitude);
        lonText = (EditText) findViewById(R.id.tree_longitude);
        altText = (EditText) findViewById(R.id.tree_altitude);
        accText = (EditText) findViewById(R.id.tree_accuracy);

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
        String us_other = intent.getStringExtra("us_other");
        String stem = intent.getStringExtra("stems");
        String height = intent.getStringExtra("height");
        String dbh = intent.getStringExtra("dbh");
        String lat = intent.getStringExtra("latitude");
        String lon = intent.getStringExtra("longitude");
        String alt = intent.getStringExtra("altitude");
        String acc = intent.getStringExtra("accuracy");
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
        us_otherText.setText(us_other);
        stemsText.setText(stem);
        heightText.setText(height);
        dbhText.setText(dbh);
        latText.setText(lat);
        lonText.setText(lon);
        altText.setText(alt);
        accText.setText(acc);

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
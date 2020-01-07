package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 1/6/18.
 * Edit data from sqlite db and save the changes or delete the record
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class TPEdit extends Activity implements OnClickListener {
    //initialize
    private Button updateBtn, deleteBtn;
    private EditText enumText,nameText,surveyText,countryText,cText,dText,oText,clText,glText,mText,sText,
            owText,cropsText,croplistText,lsText,unsText,mg1Text,mg2Text,mg3Text,mg4Text,mg5Text,mg_otherText,
            usage1Text,usage2Text,usage3Text,usage4Text,usage5Text,usage6Text,us_otherText,
            speciesText,pText,numberpText,numbersText,wText,ibText,ebText,gText,
            cfText,pgText,fbText,osText,hText,drText,in_dateText,pathText,latText,lonText,altText,accText,notesText;

    private long _id;

    private DbAccess dbAccess;
   // ICRAF_FMNR_global g = ICRAF_FMNR_global.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setTitle("Edit Data");
        setContentView(R.layout.tp_edit);
        //for previous/back button
        final Button button_prev = (Button) findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TPEdit.this, TPView.class);
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
        cropsText = (EditText) findViewById(R.id.crops);
        croplistText = (EditText) findViewById(R.id.croplist);
        lsText = (EditText) findViewById(R.id.landsize);
        unsText = (EditText) findViewById(R.id.units);
        speciesText = (EditText) findViewById(R.id.species);
        pText = (EditText) findViewById(R.id.date_planted);
        //select_hText = (EditText) findViewById(R.id.select_h);
        numberpText = (EditText) findViewById(R.id.number_planted);
        numbersText = (EditText) findViewById(R.id.number_survived);
        wText = (EditText) findViewById(R.id.woodlot);
        ibText = (EditText) findViewById(R.id.iboundary);
        ebText = (EditText) findViewById(R.id.eboundary);
        gText = (EditText) findViewById(R.id.garden);
        cfText = (EditText) findViewById(R.id.c_field);
        pgText = (EditText) findViewById(R.id.p_grassland);
        fbText = (EditText) findViewById(R.id.f_bushland);
        osText = (EditText) findViewById(R.id.other_sites);
        mg1Text = (EditText) findViewById(R.id.mg1);
        mg2Text = (EditText) findViewById(R.id.mg2);
        mg3Text = (EditText) findViewById(R.id.mg3);
        mg4Text = (EditText) findViewById(R.id.mg4);
        mg5Text = (EditText) findViewById(R.id.mg5);
        mg_otherText = (EditText) findViewById(R.id.mg_other);
        usage1Text = (EditText) findViewById(R.id.usage1);
        usage2Text = (EditText) findViewById(R.id.usage2);
        usage3Text = (EditText) findViewById(R.id.usage3);
        usage4Text = (EditText) findViewById(R.id.usage4);
        usage5Text = (EditText) findViewById(R.id.usage5);
        usage6Text = (EditText) findViewById(R.id.usage6);
        us_otherText = (EditText) findViewById(R.id.us_other);
        hText = (EditText) findViewById(R.id.height);
        drText = (EditText) findViewById(R.id.d_r);
        pathText = (EditText) findViewById(R.id.path);
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
        String other_owner = intent.getStringExtra("other_locations");
        String crops = intent.getStringExtra("crops");
        String cropl = intent.getStringExtra("croplist");
        String landsize = intent.getStringExtra("landsize");
        String units = intent.getStringExtra("unit");
        String species = intent.getStringExtra("species");
        //String select_h = intent.getStringExtra("select_h");
        String date_planted = intent.getStringExtra("date_planted");
        String number_planted = intent.getStringExtra("number_planted");
        String number_survived = intent.getStringExtra("number_survived");
        String woodlot = intent.getStringExtra("woodlot");
        String iboundary = intent.getStringExtra("iboundary");
        String eboundary = intent.getStringExtra("eboundary");
        String garden = intent.getStringExtra("garden");
        String c_field = intent.getStringExtra("crop");
        String p_grassland = intent.getStringExtra("pasture");
        String f_bushland = intent.getStringExtra("fallow");
        String other_sites = intent.getStringExtra("oth");
        String mg1 = intent.getStringExtra("mg1");
        String mg2 = intent.getStringExtra("mg2");
        String mg3 = intent.getStringExtra("mg3");
        String mg4 = intent.getStringExtra("mg4");
        String mg5 = intent.getStringExtra("mg5");
        String mg_other = intent.getStringExtra("mg_other");
        String usage1 = intent.getStringExtra("usage1");
        String usage2 = intent.getStringExtra("usage2");
        String usage3 = intent.getStringExtra("usage3");
        String usage4 = intent.getStringExtra("usage4");
        String usage5 = intent.getStringExtra("usage5");
        String usage6 = intent.getStringExtra("usage6");
        String us_other = intent.getStringExtra("us_other");
        String height = intent.getStringExtra("height");
        String d_r = intent.getStringExtra("dcr");
        String image = intent.getStringExtra("path");
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
        cropsText.setText(crops);
        croplistText.setText(cropl);
        lsText.setText(landsize);
        unsText.setText(units);
        speciesText.setText(species);

        pText.setText(date_planted);
        numberpText.setText(number_planted);
        numbersText.setText(number_survived);
        wText.setText(woodlot);
        ibText.setText(iboundary);
        ebText.setText(eboundary);
        gText.setText(garden);
        cfText.setText(c_field);
        pgText.setText(p_grassland);
        fbText.setText(f_bushland);
        osText.setText(other_sites);
        mg1Text.setText(mg1);
        mg2Text.setText(mg2);
        mg3Text.setText(mg3);
        mg4Text.setText(mg4);
        mg5Text.setText(mg5);
        mg_otherText.setText(mg_other);
        usage1Text.setText(usage1);
        usage2Text.setText(usage2);
        usage3Text.setText(usage3);
        usage4Text.setText(usage4);
        usage5Text.setText(usage5);
        usage6Text.setText(usage6);
        us_otherText.setText(us_other);
        hText.setText(height);
        drText.setText(d_r);
        pathText.setText(image);
        latText.setText(lat);
        lonText.setText(lon);
        altText.setText(alt);
        accText.setText(acc);
        notesText.setText(note);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        //show image
        File imgFile = new File(image);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.image);
            myImage.setImageBitmap(myBitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                //update values after editing
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
                String ms = mText.getText().toString();
                String school = sText.getText().toString();
                String other_owner = owText.getText().toString();
                String crops = cropsText.getText().toString();
                String croplist = croplistText.getText().toString();
                String landsize = lsText.getText().toString();
                String unit = unsText.getText().toString();
                String species = speciesText.getText().toString();
                String dplanted = pText.getText().toString();
                String nplanted = numberpText.getText().toString();
                String nsurvived = numbersText.getText().toString();
                String woodlot = wText.getText().toString();
                String iboundary = ibText.getText().toString();
                String eboundary = ebText.getText().toString();
                String garden = gText.getText().toString();
                String field = cfText.getText().toString();
                String grassland = pgText.getText().toString();
                String bushland = fbText.getText().toString();
                String other_sites = osText.getText().toString();
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
                String usage6 = usage6Text.getText().toString();
                String us_other = us_otherText.getText().toString();
                String height = hText.getText().toString();
                String dbh = drText.getText().toString();
                String image = pathText.getText().toString();
                String lat = latText.getText().toString();
                String lon = lonText.getText().toString();
                String alt = altText.getText().toString();
                String acc = accText.getText().toString();
                String notes = notesText.getText().toString();

                //update the record
                dbAccess.updateTPinfo(_id, ename, date, survey, name,country,county,district,own,c_land,g_land,ms,school,other_owner);
                dbAccess.updateTPplotinfo(_id,crops,croplist,landsize,unit);
                dbAccess.updateTPcohort(_id,species,dplanted,nplanted,nsurvived,woodlot,iboundary,eboundary,garden,field,grassland,bushland,other_sites,
                                          mg1,mg2,mg3,mg4,mg5,mg_other,usage1,usage2,usage3,usage4,usage5,usage6,us_other);
                dbAccess.updateTPmeasurements(_id,height,dbh,image,lat,lon,alt,acc,notes);
                Toast.makeText(TPEdit.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            case R.id.btn_delete:
                //delete
                dbAccess.delete_TPinfo(_id);
                dbAccess.delete_TPplotinfo(_id);
                dbAccess.delete_TPcohort(_id);
                dbAccess.delete_TPmeasurement(_id);
                Toast.makeText(TPEdit.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), TPView.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
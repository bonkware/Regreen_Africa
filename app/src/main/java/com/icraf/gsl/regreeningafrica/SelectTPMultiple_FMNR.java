package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by benard on 5/7/19.
 *
 */

public class SelectTPMultiple_FMNR extends AppCompatActivity {
    Button Button1, Button2;
    DbAccess dbAccess;

    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_multiplot);
        //read and write permission
        getStoragepermission();

        //hide main menu action
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        Button1 = (Button) findViewById(R.id.b1);
        Button2 = (Button) findViewById(R.id.b2);

        dbAccess = new DbAccess(this);
        dbAccess.open();

        //add new profile
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectTPMultiple_FMNR.this, FmnrFarmInstMainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
        //previous profile
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectTPMultiple_FMNR.this, Select_Farmer_Institution_FMNR.class);
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        //previous/back button
        Button button_next = (Button) findViewById(R.id.prev);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.prev:
                        Intent intent = new Intent(SelectTPMultiple_FMNR.this, SelectSurvey.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    // check external write permision in android 6+
    int MY_PERMISSIONS_STORAGE;

    public void getStoragepermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
    }
    //on back pressed close the app
    /*public void onBackPressed() {
        moveTaskToBack(true);
    }*/
}

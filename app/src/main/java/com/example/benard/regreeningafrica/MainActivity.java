package com.example.benard.regreeningafrica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addButton, sendButton, dataButton, helpButton, aboutButton, fqsButton, sendDataButton, sendNursery;
      DbAccess dbAccess;

      RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //read and write permission
        getStoragepermission();

        //hide main menu action
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        addButton = (Button) findViewById(R.id.button_add);
        dataButton = (Button) findViewById(R.id.data);
        helpButton = (Button) findViewById(R.id.help);
        // sendButton = (Button) findViewById(R.id.send);
        aboutButton = (Button) findViewById(R.id.about);
        fqsButton = (Button) findViewById(R.id.fqs);
        //sendNursery = (Button) findViewById(R.id.send_n);
        //  sendDataButton = (Button) findViewById(R.id.send);

        dbAccess = new DbAccess(this);
        dbAccess.open();
        //get row count and display number of records in send button
       /* int count = dbAccess.getcount();
        //also show number of records in view button
        String v = "Total Records " +"("+ count +")";
        countButton = (Button) findViewById(R.id.count);
        countButton.setText(String.valueOf(v));

        int count_training = dbAccess.getcount_trainings();
        //also show number of records in view button
        String t = "Total Records " +"("+ count_training +")";
        countButton = (Button) findViewById(R.id.c);
        countButton.setText(String.valueOf(t));*/

        //add data
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Index.this, FarmerMainActivity.class);
                Intent i = new Intent(MainActivity.this, SelectSurvey.class);
                startActivity(i);
            }
        });
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OtherMainActivities.class);
                startActivity(i);
            }
        });
        //view all
     /*   viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Index.this, ViewDataActivity.class);
                startActivity(i);
            }
        });
        //help
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Index.this, HelpActivity.class);
                startActivity(i);
            }
        });
        //about us
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Index.this, AboutActivity.class);
                startActivity(i);
            }
        });
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Index.this, SendDataActivity.class);
                startActivity(i);
            }
        });
        //fqs
        fqsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Index.this, FqsActivity.class);
                Intent i = new Intent(Index.this, FqsActivity.class);
                startActivity(i);
            }
        });*/
    }

    // check external write permision in android 6+
    int MY_PERMISSIONS_STORAGE;

    public void getStoragepermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
    }

}


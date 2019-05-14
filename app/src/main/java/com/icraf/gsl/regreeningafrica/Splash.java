package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class Splash extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //hide main menu action
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        //create folder regreening on start
        File fileName = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        File mainfolder = new File(fileName + "/RegreenAfrica");
        if (!mainfolder.exists()) {
            mainfolder.mkdirs();
        }//end of folder creation
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}


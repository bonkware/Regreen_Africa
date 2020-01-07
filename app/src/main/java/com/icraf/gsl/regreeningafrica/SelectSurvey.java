package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by benard on 10/20/18.
 * This contains select survey type
 */

public class SelectSurvey extends AppCompatActivity {
    private CheckBox button_add, button_fmnr, button_addnursery, button_addtrainings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_survey);

        //for previous/back button
        final Button button_prev = (Button) findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSurvey.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();

            }
        });

        //logo
        ActionBar logo = getSupportActionBar();
        logo.setDisplayUseLogoEnabled(true);
        logo.setDisplayShowHomeEnabled(true);
        logo.setDisplayShowHomeEnabled(false);
        logo.setHomeAsUpIndicator(R.drawable.ic_regreeningafrica_launcher);
        logo.setDisplayHomeAsUpEnabled(true);

        button_add = (CheckBox) findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_add:
                        Intent intent = new Intent(SelectSurvey.this, SelectTPMultiple_TP.class);
                        startActivity(intent);
                        ////For animation of the next activity
                        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        button_fmnr = (CheckBox) findViewById(R.id.button_fmnr);
        button_fmnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_fmnr:
                        Intent intent = new Intent(SelectSurvey.this, SelectTPMultiple_FMNR.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        button_addnursery = (CheckBox) findViewById(R.id.button_nursery);
        button_addnursery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_nursery:
                         Intent intent = new Intent(SelectSurvey.this, NurseryInfoMain.class);
                         startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        button_addtrainings = (CheckBox) findViewById(R.id.button_trainings);
        button_addtrainings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_trainings:
                        Intent intent = new Intent(SelectSurvey.this, TrainingsMainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}

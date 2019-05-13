package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 2/11/19.
 *
 */
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewMainActivity extends AppCompatActivity {
    Button TPButton,TrainingButton,NurseryButton,FMNRButton;
    DbAccess dbAccess;
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_main_activities);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = ViewMainActivity.this;

        //read and write permission
        getStoragepermission();

        //hide main menu action
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();


        TPButton = (Button) findViewById(R.id.tree_planting);
        TrainingButton = (Button) findViewById(R.id.trainings);
        NurseryButton = (Button) findViewById(R.id.nursery);
        FMNRButton = (Button) findViewById(R.id.fmnr);
        //for sending tree planting
        TPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int count = dbAccess.getcount();
                if(count==0) {
                    Toast.makeText(ViewMainActivity.this, "No data to view", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    //uploadTP();
                    Intent i = new Intent(ViewMainActivity.this, TPView.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
            }
        });
        //for sending training data
        TrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int countTrainings = dbAccess.getcount_trainings();
                if(countTrainings==0) {
                    Toast.makeText(ViewMainActivity.this, "No data to view", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    //uploadTrainings();
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ViewMainActivity.this, TrainingView.class);
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
        //for sending nursery data
        NurseryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int countNursery = dbAccess.getnurserycount();
                if(countNursery==0) {
                    Toast.makeText(ViewMainActivity.this, "No data to view", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    //uploadNursery();
                    Intent i = new Intent(ViewMainActivity.this, NurseryView.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
            }
        });
        //for sending FMNR data
        FMNRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int countNursery = dbAccess.getfmnrcount();
                if(countNursery==0) {
                    Toast.makeText(ViewMainActivity.this, "No data to view", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    //uploadFMNR();
                    Intent i = new Intent(ViewMainActivity.this, FMNRView.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }

            }
        });
        dbAccess = new DbAccess(this);
        dbAccess.open();
        //get row count and display number of records in send button
        int count = dbAccess.getcount();
        String s = "View" +"("+ count +")";
        int tcount = dbAccess.getcount_trainings();
        String sn = "View" +"("+ tcount +")";
        int ncount = dbAccess.getnurserycount();
        String nursery = "View" +"("+ ncount +")";
        int fmnrcount = dbAccess.getfmnrcount();
        String fmnr = "View" +"("+ fmnrcount +")";
        //Toast.makeText(Index.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        TPButton = (Button) findViewById(R.id.tree_planting);
        TPButton.setText(String.valueOf(s));
        //for trainings
        TrainingButton = (Button) findViewById(R.id.trainings);
        TrainingButton.setText(String.valueOf(sn));
        //for nursery
        NurseryButton = (Button) findViewById(R.id.nursery);
        NurseryButton.setText(String.valueOf(nursery));
        //for fmnr
        FMNRButton = (Button) findViewById(R.id.fmnr);
        FMNRButton.setText(String.valueOf(fmnr));
    }
    // check external write permision in android 6+
    int MY_PERMISSIONS_STORAGE;
    public  void getStoragepermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
    }

}

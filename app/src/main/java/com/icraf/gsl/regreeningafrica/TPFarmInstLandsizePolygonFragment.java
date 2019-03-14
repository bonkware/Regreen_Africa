package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by benard on 1/18/19.
 *
 */

public class TPFarmInstLandsizePolygonFragment extends Fragment implements LocationListener {
    private static final int REQUEST_LOCATION = 1;
    Button b,record,update,save;
    TableRow button_update;
    TextView result;
    TableLayout t;
    TextView lattext,lontext,alttext,acctext;
    LocationManager locationManager;
    String mprovider;
    ProgressDialog myDialog;
    String loc_lat,loc_lon, loc_alt,loc_acc;
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    private DbAccess dbAccess;
    int clickcount=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tree_landsize_polygon, container,
                false);
        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();
        //get fid
        String farmer_id = g.getfid();
        TextView fid = (TextView) view.findViewById(R.id.fid);
        fid.setText(farmer_id);
        //check permission first on nadroid 6++
        getGpspermission();
        //show locations in edittext
        lattext =  view.findViewById(R.id.latitude);
        lontext =  view.findViewById(R.id.longitude);
        alttext =  view.findViewById(R.id.altitude);
        acctext =  view.findViewById(R.id.accuraccy);

        //buttons
        record = (Button) view.findViewById(R.id.record);
        update = (Button) view.findViewById(R.id.update);
        save = (Button) view.findViewById(R.id.save);


        //proceed to tree recording after recording five points
        final Button button_next = (Button) view.findViewById(R.id.tospecies);
        button_next.setEnabled(false);//disable button
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tospecies:
                        //savePolygon();//save
                        //dbAccess.insertPolygon();//insert details to db
                        Intent intent = new Intent(getActivity(), TPCohortMainAcivity.class);
                        startActivity(intent);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //Get the location
        record.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gpsfix();
                record.setVisibility(View.GONE);//click only once and hide it
                save.setVisibility(View.VISIBLE);//show the save button

            }
        });
        //update the location
        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gpsfix();
                //show update button
                update.setVisibility(View.GONE);//hide the update button on save
                save.setVisibility(View.VISIBLE);//show the save button after location update.

            }
        });
        //keep saving the locations
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                savePolygon();//save
                dbAccess.insertLandsizepolygon();//insert details to db
                clickcount=clickcount+1;
                if(clickcount==1)
                {
                    //first time clicked to do this
                    Toast.makeText(TPFarmInstLandsizePolygonFragment.this.getActivity(),"First point is saved!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //check how many times clicked and so on
                    if (clickcount >= 4){
                        button_next.setEnabled(true);//enable this button after 4 polygon point are saved
                    }
                    Toast.makeText(TPFarmInstLandsizePolygonFragment.this.getActivity(),"Point "+clickcount+" saved", Toast.LENGTH_LONG).show();
                }
                //show update button
                update.setVisibility(View.VISIBLE);//show update button after save.
                save.setVisibility(View.GONE);//hide save button to update location

            }
        });

        return view;
    }
    public  void getGpspermission() {
        if (ActivityCompat.checkSelfPermission(TPFarmInstLandsizePolygonFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (TPFarmInstLandsizePolygonFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TPFarmInstLandsizePolygonFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        }
    }
    public  void gpsfix(){
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //getLocation();

            Criteria criteria = new Criteria();

            //start the progress dialog until gps fixes
            myDialog = ProgressDialog.show(this.getActivity(), "GPS getting the location", "Please wait for GPS to fix location.", true);//please wait
            myDialog.setCancelable(true);
            myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Log.i("inside on cancel","Cancel Called");
                    //finish(); //If you want to finish the activity.
                }
            });

            mprovider = locationManager.getBestProvider(criteria, true);

            if (mprovider != null && !mprovider.equals("")) {
                if (ActivityCompat.checkSelfPermission(TPFarmInstLandsizePolygonFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (TPFarmInstLandsizePolygonFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(TPFarmInstLandsizePolygonFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

                }
                Location location = locationManager.getLastKnownLocation(mprovider);
                locationManager.requestLocationUpdates(mprovider, 1500, 1, this);
                if (location != null) {
                    onLocationChanged(location);
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //global variables
        g.setcurrentlat_long(location.getLatitude(),location.getLongitude(),location.getAltitude(),location.getAccuracy());
        //display loc
        double lat = g.getLatitude();
        double lon = g.getLongitude();
        double alt = g.getAltitude();
        double acc = g.getAccuracy();
        loc_lat = String.valueOf(lat);
        loc_lon = String.valueOf(lon);
        loc_alt = String.valueOf(alt);
        loc_acc = String.valueOf(acc);
        lattext.setText("Latitude: " + loc_lat);
        lontext.setText("Longitude: " + loc_lon);
        alttext.setText("Altitude: " + loc_alt);
        acctext.setText("Accuracy: " +loc_acc);

        myDialog.dismiss();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    protected void buildAlertMessageNoGps() {

        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this.getActivity());
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final android.app.AlertDialog alert = builder.create();
        alert.show();
    }
    public  void savePolygon() {
        TextView farmerid = (TextView) getActivity().findViewById(R.id.fid);
        g.setfid(farmerid.getText().toString());
        //get points from global
    }
}


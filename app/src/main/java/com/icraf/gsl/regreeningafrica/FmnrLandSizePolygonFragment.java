package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by benard on 1/18/19.
 *
 */

public class FmnrLandSizePolygonFragment extends Fragment {
    private static final int REQUEST_LOCATION = 1;
    Button b,fixgps,updategps,getlocation,save;
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
        final View view = inflater.inflate(R.layout.fmnr_landsize_polygon, container,
                false);
        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //get fid
        String farmer_id = g.getfid();
        TextView fid = (TextView) view.findViewById(R.id.fid);
        fid.setText(farmer_id);
        //get the generated id for polygons
        String plot_id = g.getpid();
        TextView pid = (TextView) view.findViewById(R.id.pid);
        pid.setText(plot_id);
        //check permission first on nadroid 6++
        getGpspermission();
        //show locations in edittext
        lattext =  view.findViewById(R.id.latitude);
        lontext =  view.findViewById(R.id.longitude);
        alttext =  view.findViewById(R.id.altitude);
        acctext =  view.findViewById(R.id.accuraccy);

        //buttons
        fixgps = (Button) view.findViewById(R.id.record);
        getlocation = (Button) view.findViewById(R.id.getlocation);
        updategps = (Button) view.findViewById(R.id.updategps);
        save = (Button) view.findViewById(R.id.save);
        //save.setEnabled(false);//disable
        //for previous/back button
        final Button button_prev = (Button) view.findViewById(R.id.prev);
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g.getMultiplot()==true) {
                    Intent intent = new Intent(getActivity(), Select_Farmer_Institution_FMNR.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    button_prev.setEnabled(false);
                    //Intent intent = new Intent(getActivity(), TPFarmInstiMainAcivity.class);
                    //startActivity(intent);
                    //getActivity().overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }

            }
        });

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
                        Intent intent = new Intent(getActivity(), FmnrTreeMeasureMainActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //fix the gps location
        fixgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if GPS enabled first before start fixing the gps
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();
                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    GPSfix();//fix the gps
                    fixgps.setVisibility(View.GONE);//click only once and hide it
                    getlocation.setVisibility(View.VISIBLE);//show the save button
                }
            }
        });

        //get location to labels at first
        getlocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setLocation();//get the location
                getlocation.setVisibility(View.GONE);//click only once and hide it
                updategps.setVisibility(View.VISIBLE);//show the update button
                save.setVisibility(View.VISIBLE);//show the save button

            }
        });
        //update the location
        updategps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setLocation();//get the location
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
                // if(clickcount==1)
                //check how many times clicked and so on
                if (clickcount >= 5){
                    button_next.setEnabled(true);//enable this button after 4 polygon point are saved
                }
                Toast.makeText(FmnrLandSizePolygonFragment.this.getActivity(),"Point "+clickcount+" saved", Toast.LENGTH_LONG).show();
               /* {
                    //first time clicked to do this
                    Toast.makeText(FmnrLandSizePolygonFragment.this.getActivity(),"First point is saved!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //check how many times clicked and so on
                    if (clickcount >= 5){
                        button_next.setEnabled(true);//enable this button after 4 polygon point are saved
                    }
                    Toast.makeText(FmnrLandSizePolygonFragment.this.getActivity(),"Point "+clickcount+" saved", Toast.LENGTH_LONG).show();
                }*/
                //show update button
                //updategps.setVisibility(View.VISIBLE);//show update button after save.
                //save.setVisibility(View.GONE);//hide save button to update location

            }
        });

        return view;
    }
    public  void getGpspermission() {
        if (ActivityCompat.checkSelfPermission(FmnrLandSizePolygonFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (FmnrLandSizePolygonFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FmnrLandSizePolygonFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        }
    }
    // call gps fix method from gps class
    public void GPSfix() {
            GPS gps = GPS.getInstance();
            gps.getGPSFix(getContext());

            if (g.getGPS_fix() == false) {
                ProgressDialog progDailog;
                progDailog = ProgressDialog.show(getActivity(), "GPS fix", "Please wait for GPS to fix location.", true);//please wait
                gps.setProgDailog(progDailog);
            } else {
                //setLocation();//call this if the gps has already been fixed
                getlocation.setVisibility(View.VISIBLE);//show the get location button

            }
    }
    // set location points on the labels
    public void setLocation(){

        GPS gps  = GPS.getInstance();
        //global variables
        g.setcurrentlat_long(gps.getLatitude(),gps.getLongitude(),gps.getAltitude(),gps.getAccuracy());
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

    }
    protected void buildAlertMessageNoGps() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this.getActivity());
        builder.setMessage("Please Turn ON your GPS")
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
        TextView fid = (TextView) getActivity().findViewById(R.id.fid);
        g.setfid(fid.getText().toString());
        TextView pid = (TextView) getActivity().findViewById(R.id.pid);
        g.setpid(pid.getText().toString());
        //get points from global
    }
}


package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;


/**
 * Created by benard on 1/18/19.
 *
 */

public class TPTreeGpsFragment extends Fragment {
    private static final int REQUEST_LOCATION = 1;
    ImageButton b;
    Button fixgps,updategps,getlocation,save;
    TableLayout t;
    EditText lattext,lontext,alttext,acctext;
    LocationManager locationManager;
    String mprovider;
    ProgressDialog myDialog;
    String loc_lat,loc_lon, loc_alt,loc_acc;
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tree_gps, container,
                false);
        //check permission first on nadroid 6++
        getGpspermission();
        //show locations in edittext
        lattext =  view.findViewById(R.id.latitude);
        lontext =  view.findViewById(R.id.longitude);
        alttext =  view.findViewById(R.id.altitude);
        acctext =  view.findViewById(R.id.accuraccy);

        //set next button disabled
        final Button bnext = (Button) view.findViewById(R.id.next);
        bnext.setEnabled(false);//disable button

        b = (ImageButton) view.findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //show table when this button is clicked
                t = (TableLayout) view.findViewById(R.id.gpscoord);
                t.setVisibility(View.VISIBLE);
                //and start the gps fixing
                //setLocation();
                GPSfix();
                bnext.setEnabled(true);


            }
        });
        return view;
    }
    public  void getGpspermission() {
        if (ActivityCompat.checkSelfPermission(TPTreeGpsFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (TPTreeGpsFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(TPTreeGpsFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
            setLocation();//call this if the gps has already been fixed
            //getlocation.setVisibility(View.VISIBLE);//show the get location button

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
        lattext.setText(loc_lat);
        lontext.setText(loc_lon);
        alttext.setText(loc_alt);
        acctext.setText(loc_acc);
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
}


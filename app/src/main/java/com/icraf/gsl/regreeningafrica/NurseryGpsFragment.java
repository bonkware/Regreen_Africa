package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
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

public class NurseryGpsFragment extends Fragment {
    private static final int REQUEST_LOCATION = 1;
    ImageButton b;
    Button fixgps;
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
        final View view = inflater.inflate(R.layout.nursery_gps, container,
                false);
        //check permission first on nadroid 6++
        getGpspermission();
        //show locations in edittext
        lattext =  view.findViewById(R.id.latitude);
        lontext =  view.findViewById(R.id.longitude);
        alttext =  view.findViewById(R.id.altitude);
        acctext =  view.findViewById(R.id.accuraccy);

        //set next button disabled
        final Button button_next = (Button) view.findViewById(R.id.next);
        button_next.setEnabled(false);//disable button
        button_next.setAlpha(0.5f);

        //fix the gps location
        fixgps = (Button) view.findViewById(R.id.fix);
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
                    b.setVisibility(View.VISIBLE);//show the save button
                }
            }
        });

        b = (ImageButton) view.findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //show table when this button is clicked
                t = (TableLayout) view.findViewById(R.id.gpscoord);
                t.setVisibility(View.VISIBLE);
                button_next.setEnabled(true);
                button_next.setBackgroundColor(Color.parseColor("#966648"));//change color of button
                //and start the gps fixing
                setLocation();//get the location
            }
        });

        return view;
    }
    public  void getGpspermission() {
        if (ActivityCompat.checkSelfPermission(NurseryGpsFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (NurseryGpsFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(NurseryGpsFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
            //setLocation();
        } else {
            //setLocation();//call this if the gps has already been fixed
            b.setVisibility(View.VISIBLE);//show the get location button

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


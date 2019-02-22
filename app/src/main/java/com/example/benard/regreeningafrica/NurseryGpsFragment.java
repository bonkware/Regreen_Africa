package com.example.benard.regreeningafrica;

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
import android.widget.ImageButton;
import android.widget.TableLayout;


/**
 * Created by benard on 1/18/19.
 *
 */

public class NurseryGpsFragment extends Fragment implements LocationListener {
    private static final int REQUEST_LOCATION = 1;
    ImageButton b;
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
                gpsfix();

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
    public  void gpsfix(){
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //getLocation();

            Criteria criteria = new Criteria();

            //start the progress dialog until gps fixes
            myDialog = ProgressDialog.show(this.getActivity(), "GPS fix", "Please wait for GPS to fix location.", true);//please wait
            myDialog.setCancelable(true);
            myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Log.i("inside on cancel","Cancel Called");
                    //finish(); //If you want to finish the activity.
                }
            });


            mprovider = locationManager.getBestProvider(criteria, true);

            if (mprovider != null && !mprovider.equals("")) {
                if (ActivityCompat.checkSelfPermission(NurseryGpsFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (NurseryGpsFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(NurseryGpsFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
        lattext.setText(loc_lat);
        lontext.setText(loc_lon);
        alttext.setText(loc_alt);
        acctext.setText(loc_acc);
        //remove updates
       /* if (ActivityCompat.checkSelfPermission(GPSFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }*/
        //locationManager.removeUpdates(this);//remove updates

        //dismiss dialog
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
}


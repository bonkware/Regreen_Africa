package com.icraf.gsl.regreeningafrica;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * Created on 21/05/19.
 *
 */

public class GPS {

    private static GPS instance;

    // location manager for GPS
    private LocationManager locationManager;


    private double latitude, longitude, accuracy, altitude;
    ProgressDialog progDailog;
    RegreeningGlobal g = RegreeningGlobal.getInstance();

    public GPS(){


    }

    public static synchronized GPS getInstance(){
        if(instance==null){
            instance=new GPS();
        }
        return instance;
    }
    // starts gps fix
    public void getGPSFix(Context app){

        locationManager =(LocationManager) app.getSystemService(app.LOCATION_SERVICE);
        // refereence = http://stackoverflow.com/questions/4870667/how-can-i-use-getsystemservice-in-a-non-activity-class-locationmanager

        // use try catch here
        if(ContextCompat.checkSelfPermission(app, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {


            locationManager.addGpsStatusListener(mGPSStatusListener);
            LocationListener gpslocationListener = new LocationListener() {
                public void onLocationChanged(Location loc) {
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };

            long MIN_TIME_BW_UPDATES_GPS = 1000;
            float MIN_DISTANCE_CHANGE_FOR_UPDATES_GPS = 2;

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    MIN_TIME_BW_UPDATES_GPS, MIN_DISTANCE_CHANGE_FOR_UPDATES_GPS,
                    gpslocationListener);

        }// end if GPS permission
    }// end getGPSFix


    // Note
    // I am using this code from ref= http://stackoverflow.com/questions/15453576/android-check-if-gps-is-searching-has-fix-or-is-not-in-use
    public GpsStatus.Listener mGPSStatusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {

            Location gpslocation;

            switch(event) {
                case GpsStatus.GPS_EVENT_STARTED:

                    //Toast.makeText(getBaseContext(), "GPS seraching",Toast.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(), "GPS_SEARCHING", Toast.LENGTH_SHORT).show();
                    System.out.println("TAG - GPS searching: ");


                    break;
                case GpsStatus.GPS_EVENT_STOPPED:
                    System.out.println("TAG - GPS Stopped");
                    break;
                case GpsStatus.GPS_EVENT_FIRST_FIX:

                /*
                 * GPS_EVENT_FIRST_FIX Event is called when GPS is locked
                 */

                    // make sure user has givern permisison to emable GPS
                    try {
                        //Toast.makeText(getBaseContext(), "GPS_LOCKED", Toast.LENGTH_SHORT).show();
                        gpslocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                        if (gpslocation != null) {
                            /*if (accuracy <= 5.5) {
                                System.out.println("GPS Info:" + gpslocation.getLatitude() + ":" + gpslocation.getLongitude());
                                latitude=gpslocation.getLatitude();
                                longitude=gpslocation.getLongitude();
                                altitude = gpslocation.getAltitude();
                                accuracy = gpslocation.getAccuracy();
                                break;
                            }*/
                            System.out.println("GPS Info:" + gpslocation.getLatitude() + ":" + gpslocation.getLongitude());
                            latitude=gpslocation.getLatitude();
                            longitude=gpslocation.getLongitude();
                            altitude = gpslocation.getAltitude();
                            accuracy = gpslocation.getAccuracy();

                            // show dialog till fix achieved
                            if(progDailog != null) {
                                progDailog.dismiss();
                            }
                            progDailog=null;

                            /*
                            * Removing the GPS status listener once GPS is locked
                            */
                            //locationManager.removeGpsStatusListener(mGPSStatusListener);
                            g.setGPS_fix(true);//set it true once gps is fixed
                        }
                        //}// end GPS user permission if statement
                    } catch (SecurityException e) {
                        // handle exception
                        //Toast.makeText(getBaseContext(), "GPS not enabled", Toast.LENGTH_LONG).show();
                    }

                    break;
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    //                 System.out.println("TAG - GPS_EVENT_SATELLITE_STATUS");
                    break;
                //ref=http://stackoverflow.com/questions/15453576/android-check-if-gps-is-searching-has-fix-or-is-not-in-use
            }

                if (progDailog == null) {

                    // make sure user has givern permisison to emable GPS
                    try {
                        gpslocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                        if (gpslocation != null) {


                            latitude = gpslocation.getLatitude();
                            longitude = gpslocation.getLongitude();
                            altitude = gpslocation.getAltitude();
                            accuracy = gpslocation.getAccuracy();

                        }
                    } catch (SecurityException e) {
                        // handle exception
                        //Toast.makeText(getBaseContext(), "GPS not enabled", Toast.LENGTH_LONG).show();
                    }

                }
        }
    };

    public double getLatitude(){

        return latitude;

    }

    public double getLongitude(){

        return longitude;

    }
    public double getAccuracy(){
        return  accuracy;
    }

    public  double getAltitude(){
        return altitude;
    }
    public void setProgDailog(ProgressDialog pD){
        progDailog = pD;

    }

}


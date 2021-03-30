package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 6/24/19.
 *
 */

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FmnrLandsizeArea extends Fragment {
    public FmnrLandsizeArea() {
        // Required empty public constructor
    }
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    private DbAccess dbAccess;

    double x_UTM[] ;
    double y_UTM[] ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tp_landsize_area, container,
                false);
        //disable back button Once points are collected for polgyon
        Button prev = (Button) view.findViewById(R.id.prev);
        prev.setEnabled(false);
        prev.setAlpha(0.5f);

        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();
        //to next
        final Button button_next = (Button) view.findViewById(R.id.tospecies);
        button_next.setEnabled(false);//disable button
        button_next.setAlpha(0.5f);
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
        // get the total area and enable the next button
        //fix the gps location
        final Button getArea = (Button) view.findViewById(R.id.record);
        getArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateArea();//get the area
                //calculateAreaTest();
                button_next.setEnabled(true);
                button_next.setBackgroundColor(Color.parseColor("#966648"));//change color of button
            }
        });

        return view;
    }
    //calculate the area from the points
    public  void calculateArea(){
        String plotID = g.getpid();
        Cursor cursor = dbAccess.getxypointsfmnr(plotID);
        int length = cursor.getCount();//
        length+=1;
        //long[] array = new long[length];//
        //declare and initialize
        double x[]= new double[length];
        double y[]=new double[length];
        double area =0;

        if (cursor.moveToFirst()) {

            //int j=0;
            //iterate over rows
            for (int i = 0; i < cursor.getCount(); i++) {
                x[i]=cursor.getDouble(cursor.getColumnIndex("longitude"));//get double
                y[i]=cursor.getDouble(cursor.getColumnIndex("latitude"));
                cursor.moveToNext();
                //j=i;
            }
            //due to GPS error it is not possible to get exact reading of the first point when user walks back to it
            //in order to make sure that first point is the last point, we pushed first point in the last index
            x[length-1]=x[0];
            y[length-1]=y[0];

            //resizing the array according to lenght of the cursor +1 (+1 is the last point explained above)
            x_UTM = new double[length];
            y_UTM = new double[length];
            // convert a polygon to UTM of the last x and y arrays
            for(int i=0; i< x.length; i++){
                dd2UTM(y[i],x[i],i);
            }

            area_calcUTM(x_UTM,y_UTM);
        }
    }
    //calculate the area
    public void area_calcUTM(double [] x,double [] y){
        // recevies UTM coordinates and calculate area in meters.
        System.out.println("UTM Area in sq sqmeters");
        double area=0;
        for (int i = 1; i < x.length; i++) {
            //System.out.println(x[i]);
            //System.out.println(y[i]);
            double temp_area = ((x[i-1]*y[i]) - (y[i-1]*x[i]))/2;
            //System.out.println(temp_area);
            area += temp_area;

        }
        System.out.println(area);

        double area_sq= Math.abs(area);//use of Math.abs to convert negative result to positive

        TextView plotareasq= (TextView) getActivity().findViewById(R.id.sq);
        plotareasq.setText(area_sq + " m²");

        double a = area_sq * 0.00024711;
        TextView plotareaacre= (TextView) getActivity().findViewById(R.id.acres);
        plotareaacre.setText(a + " acre");
        //System.out.println(String.valueOf(a));

        double h = area_sq / 10000;
        TextView plotareaha= (TextView) getActivity().findViewById(R.id.ha);
        plotareaha.setText(h + " ha");

    }// end method

    //utm conversion
    public void dd2UTM(double Lat,double Lon, int i){
        // convert latlong to UTM easting northing
        double Easting;
        double Northing;
        int Zone;
        char Letter;

        //https://stackoverflow.com/questions/176137/java-convert-lat-lon-to-utm

        Zone= (int) Math.floor(Lon/6+31);
        if (Lat<-72)
            Letter='C';
        else if (Lat<-64)
            Letter='D';
        else if (Lat<-56)
            Letter='E';
        else if (Lat<-48)
            Letter='F';
        else if (Lat<-40)
            Letter='G';
        else if (Lat<-32)
            Letter='H';
        else if (Lat<-24)
            Letter='J';
        else if (Lat<-16)
            Letter='K';
        else if (Lat<-8)
            Letter='L';
        else if (Lat<0)
            Letter='M';
        else if (Lat<8)
            Letter='N';
        else if (Lat<16)
            Letter='P';
        else if (Lat<24)
            Letter='Q';
        else if (Lat<32)
            Letter='R';
        else if (Lat<40)
            Letter='S';
        else if (Lat<48)
            Letter='T';
        else if (Lat<56)
            Letter='U';
        else if (Lat<64)
            Letter='V';
        else if (Lat<72)
            Letter='W';
        else
            Letter='X';
        Easting=0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180))/(1-Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180)))*0.9996*6399593.62/Math.pow((1+Math.pow(0.0820944379, 2)*Math.pow(Math.cos(Lat*Math.PI/180), 2)), 0.5)*(1+ Math.pow(0.0820944379,2)/2*Math.pow((0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180))/(1-Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180)))),2)*Math.pow(Math.cos(Lat*Math.PI/180),2)/3)+500000;
        Easting=Math.round(Easting*100)*0.01;
        Northing = (Math.atan(Math.tan(Lat*Math.PI/180)/Math.cos((Lon*Math.PI/180-(6*Zone -183)*Math.PI/180)))-Lat*Math.PI/180)*0.9996*6399593.625/Math.sqrt(1+0.006739496742*Math.pow(Math.cos(Lat*Math.PI/180),2))*(1+0.006739496742/2*Math.pow(0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin((Lon*Math.PI/180-(6*Zone -183)*Math.PI/180)))/(1-Math.cos(Lat*Math.PI/180)*Math.sin((Lon*Math.PI/180-(6*Zone -183)*Math.PI/180)))),2)*Math.pow(Math.cos(Lat*Math.PI/180),2))+0.9996*6399593.625*(Lat*Math.PI/180-0.005054622556*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+4.258201531e-05*(3*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2))/4-1.674057895e-07*(5*(3*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2))/4+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2)*Math.pow(Math.cos(Lat*Math.PI/180),2))/3);
        if (Letter<'M')
            Northing = Northing + 10000000;
        Northing=Math.round(Northing*100)*0.01;

       /*System.out.println(Easting);
       System.out.println(Northing);
       System.out.println(Zone);
       System.out.println(Letter);*/

        x_UTM[i] = Easting;
        y_UTM[i] = Northing;

    }
}


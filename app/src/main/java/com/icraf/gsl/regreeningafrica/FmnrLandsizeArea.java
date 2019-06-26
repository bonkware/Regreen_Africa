package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 6/24/19.
 *
 */

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tp_landsize_area, container,
                false);
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
            x[length-1]=x[0];
            y[length-1]=y[0];

            // since array starts from 0, er had to jump to 1 to avoid null pointer exception and x2-x1 expression
            for(int i=1; i < x.length;i++){
                double temp_area = Math.toRadians(x[i] - x[i-1]) * (2 + Math.sin(Math.toRadians(y[i-1])) + Math.sin(Math.toRadians(y[i])));
                area += temp_area;
            }

            double final_area = Math.abs(area * 6378137.0 * 6378137.0 / 2.0);
            //System.out.println(final_area);
            g.setplotarea(final_area);
            //show the area in the text
            double area_sq= g.getplotarea();
            //int square = (int)area_sq;//convert double to int
            //String square = String.valueOf(area_sq);//convert double to string
            TextView plotareasq= (TextView) getActivity().findViewById(R.id.sq);
            plotareasq.setText(area_sq + " m²");

            double a = area_sq * 0.00024711;
            TextView plotareaacre= (TextView) getActivity().findViewById(R.id.acres);
            plotareaacre.setText(a + " acre");
            System.out.println(String.valueOf(a));

            double h = area_sq / 10000;
            TextView plotareaha= (TextView) getActivity().findViewById(R.id.ha);
            plotareaha.setText(h + " ha");
        }
    }
    public  void calculateAreaTest(){
        // points in array
        double x[] = {36.818007425342, 36.818345588179, 36.8190422326501, 36.818708423841, 36.818634405366, 36.818634405366, 36.818007425342};

        double y[] = {-1.23997432653317,-1.23906599863355, -1.23929380613924, -1.2402064869687, -1.24023115401378, -1.24023115401378, -1.23997432653317};
        double area=0;
        for (int i = 1; i < 7; i++) {
            //System.out.println(x[i]);

            //System.out.println(y[i]);
            double temp_area = Math.toRadians(x[i] - x[i-1]) * (2 + Math.sin(Math.toRadians(y[i-1])) + Math.sin(Math.toRadians(y[i])));
            area += temp_area;
        }
        double final_area = Math.abs(area * 6378137.0 * 6378137.0 / 2.0);
        g.setplotarea(final_area);
        System.out.println(final_area);
        //show the area in the text
        double area_sq= g.getplotarea();
        //String square = String.valueOf(area_sq);
        TextView plotareasq= (TextView) getActivity().findViewById(R.id.sq);
        plotareasq.setText(area_sq + " m²");

        double a = area_sq * 0.00024711;
        TextView plotareaacre= (TextView) getActivity().findViewById(R.id.acres);
        plotareaacre.setText(a + " acre");
        System.out.println(String.valueOf(a));

        double h = area_sq / 10000;
        TextView plotareaha= (TextView) getActivity().findViewById(R.id.ha);
        plotareaha.setText(h + " ha");
    }
}


package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 7/22/19.
 *
 */

public class AreaCalc {

   /* This class will calculate area based on wgs84 in sqmeters
   to compile just run the following commands
    1)  javac area.java
    2)  java area
    */

    public static void main(String []args) {

        // Senegal
        double x[]= new double[] {-14.8226004142653, -14.8055352923075, -14.8066578735347, -14.8219834666585, -14.8226004142653};
        double y[]= new double[] {13.9039704814605 ,13.9041118356398 ,13.8842011032729 ,13.8842127464403 ,13.9039704814605};

        // convert a polygon to UTM of the last x and y arrays
        for(int i=0; i< x.length; i++){
            dd2UTM(y[i],x[i]);

        }// end for

        //Senegal
        x= new double[] {519165.47000000003, 521009.11, 520889.61, 519233.76, 519165.47000000003};
        y= new double[] {1537113.08 ,1537130.1500000001, 1534928.03 ,1534928.03, 1537113.08};
        area_calcUTM(x,y);

        //output = 9053.841791574367

    } // main method

    public static void area_calcUTM(double [] x,double [] y){
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

    }// end method



    public static void dd2UTM(double Lat,double Lon){
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

        System.out.println(Easting);
        System.out.println(Northing);
        System.out.println(Zone);
        System.out.println(Letter);
    }


} //class

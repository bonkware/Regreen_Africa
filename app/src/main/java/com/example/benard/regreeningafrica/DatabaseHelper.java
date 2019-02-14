package com.example.benard.regreeningafrica;
/**
 * Created by benard on 1/6/18.
 * sqlite db helper class
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tables for tree planting
    public static final String TABLE_FARMER_INST = "farmer_institution";
    public static final String TABLE_COHORT = "cohort";
    public static final String TABLE_Measurement = "tree_measurements";
    public static final String TABLE_Trainings = "trainings";//trainings table

    // Farmer institution columns
    public static final String _ID = "_id";
    public static final String farmer_id = "farmerID";
    public static final String enum_name = "ename";
    public static final String date = "in_date";
    public static final String farmer_inst_name = "name";
    public static final String country = "country";
    public static final String county_region = "county_region";
    public static final String district = "district";
    public static final String planting_location = "planting_location";
    public static final String planting_site = "planting_site";
    public static final String landsize_regreen = "landsize_regreen";

    //Cohort columns
    public static final String farmerID = "farmerID";
    public static final String cohort_id = "cohortID";
    public static final String species_name = "species";
    public static final String date_planted = "date_planted";
    public static final String number_planted = "number_planted";
    public static final String number_survived = "number_survived";
    public static final String management_pruning = "mg1";
    public static final String management_fencing = "mg2";
    public static final String management_weeding = "mg3";
    public static final String management_watering = "mg4";
    public static final String management_organic_fertilizer = "mg5";
    public static final String management_other = "mg_other";
    public static final String use_firewood = "usage1";
    public static final String use_housing_construction = "usage2";
    public static final String use_animal_feed = "usage3";
    public static final String use_food = "usage4";
    public static final String use_mulching = "usage5";
    public static final String use_other = "us_other";

    //tree measurement
    public static final String cohortID = "cohortID";
    public static final String tree_height = "height";
    public static final String tree_rcd = "rcd";
    public static final String tree_dbh = "dbh";
    public static final String tree_latitude = "latitude";
    public static final String tree_longitude = "longitude";
    public static final String tree_altitude = "altitude";
    public static final String tree_accuracy = "accuracy";
    public static final String tree_image_path = "path";

    //table for trainings
    public static final String _id = "_id";
    public  static  final String training_country = "training_country";
    public  static  final String training_region = "training_region";
    public  static  final String training_district = "training_district";
    public  static  final String training_topic = "training_topic";
    public  static  final String training_date = "training_date";
    public  static  final String training_venue = "training_venue";
    public  static  final String training_partners = "training_partners";
    public  static  final String training_participants = "total_participants";
    public  static  final String male_participants = "male_participants";
    public  static  final String female_participants = "female_participants";
    public  static  final String youth_participants = "youth_participants";


    // Database name
    static final String DB_NAME = "regreen_africa.sqlite";

    // database version
    static final int DB_VERSION = 1;

    // Creating farmer/institution table
    private static final String CREATE_TABLE_FARMER_INST =
            "create table " +
                    TABLE_FARMER_INST + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + enum_name + " TEXT NOT NULL, " + date + " TEXT NOT NULL, " + farmer_inst_name + " TEXT,"+
                    country + " TEXT,"+county_region +  " TEXT,"+district + " TEXT,"+planting_location + " TEXT,"+planting_site + " TEXT,"+landsize_regreen + " TEXT,"+
                    farmer_id+" TEXT);";

    //creating cohort table
    private static final String CREATE_TABLE_COHORT =
            "create table " +
                    TABLE_COHORT + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ species_name + " TEXT,"+date_planted + " TEXT,"+number_planted + " TEXT,"+number_survived + " TEXT,"+management_pruning+" TEXT,"+management_fencing + " TEXT,"+
                    management_weeding + " TEXT,"+management_watering + " TEXT,"+ management_organic_fertilizer + " TEXT,"+management_other + " TEXT,"+use_firewood+" TEXT,"+use_housing_construction +
                    " TEXT,"+use_animal_feed +  " TEXT,"+use_food + " TEXT,"+use_mulching + " TEXT,"+use_other+" TEXT,"+farmerID+" TEXT,"+cohort_id+" TEXT);";
    //creating tree measurement table
    private static final String CREATE_TABLE_TREE =
            "create table " +
                    TABLE_Measurement + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ tree_height + " TEXT,"+tree_rcd + " TEXT,"+tree_dbh + " TEXT,"+tree_latitude + " TEXT,"+tree_longitude+" TEXT,"+tree_altitude + " TEXT,"+
                    tree_accuracy +" TEXT,"+tree_image_path + " TEXT,"+cohortID+" TEXT);";
    //creating training table
    private static final String CREATE_TABLE_TRAININGS =
            "create table " +
                    TABLE_Trainings + "(" +
                    _id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ training_country + " TEXT,"+training_region + " TEXT,"+training_district + " TEXT,"+training_topic + " TEXT,"+training_date+" TEXT,"+training_venue + " TEXT,"+
                    training_partners +" TEXT,"+training_participants + " TEXT,"+male_participants+" TEXT,"+female_participants+" TEXT,"+youth_participants+" TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating tables
        db.execSQL(CREATE_TABLE_FARMER_INST);
        db.execSQL(CREATE_TABLE_COHORT);
        db.execSQL(CREATE_TABLE_TREE);
        db.execSQL(CREATE_TABLE_TRAININGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop old ones
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FARMER_INST);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_COHORT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TREE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TRAININGS);
        //create new tables
        onCreate(db);
    }
}


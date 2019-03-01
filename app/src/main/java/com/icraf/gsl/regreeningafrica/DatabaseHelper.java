package com.icraf.gsl.regreeningafrica;
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

    //tables for nursery information
    // nursery info
    public static final String TABLE_NURSERY = "nursery_info";
    // nursery species
    public static final String TABLE_NURSERY_SPECIES = "nursery_species";
    //columns nursery profile
    public static final String id = "id";
    public static final String nursery_id = "nurseryID";
    public static final String nursery_country = "country";
    public static final String nursery_county = "county";
    public static final String nursery_district = "district";
    public static final String nursery_operator = "operator";
    public static final String nursery_contact = "contact";
    public static final String type_government = "government";
    public static final String type_church_mosque = "church_mosque";
    public static final String type_schools = "schools";
    public static final String type_women_groups = "women_groups";
    public static final String type_youth_groups = "youth_groups";
    public static final String type_private_individual = "private_individual";
    public static final String type_communal_village = "communal_village";
    public static final String other_nursery_types = "other_types";
    public static final String nursery_latitude = "latitude";
    public static final String nursery_longitude = "longitude";
    public static final String nursery_altitude = "altitude";
    public static final String nursery_accuracy = "accuracy";
    public static final String nursery_image_path = "image";
    //nursery trees
    public static final String _nid = "_id";
    public static final String nurseryID = "nurseryID";
    public static final String nursery_species = "species";
    public static final String nursery_local = "local";
    public static final String method_bare_root = "bare_root";
    public static final String method_containerised = "containerised";
    public static final String other_methods = "other_methods";
    public static final String propagation_seed = "seed";
    public static final String propagation_graft = "graft";
    public static final String propagation_cutting = "cutting";
    public static final String propagation_marcotting = "marcotting";
    public static final String seed_source_onfarm = "onfarm";
    public static final String seed_source_local_dealer = "local_dealer";
    public static final String seed_source_national_dealer = "national_dealer";
    public static final String seed_source_NGOs = "NGOs";
    public static final String other_seed_sources = "other_sources";
    public static final String graft_source_farmland = "farmland";
    public static final String graft_source_plantation = "plantation";
    public static final String graft_source_mother_blocks = "mother_blocks";
    public static final String graft_source_prisons = "prisons";
    public static final String graft_source_others = "other_graft_sources";
    public static final String seeds_quantity_purchased = "quantity_purchased";
    public static final String date_seeds_sown = "date_sown";
    public static final String seedlings_germinated = "seedlings_germinated";
    public static final String seedlings_servived = "seedlings_survived";
    public static final String seedlings_age = "seedlings_age";
    public static final String seedlings_price = "seedlings_price";
    //end of nursery columns

    // fmnr farmer/institution info table
    public static final String TABLE_FMNR_FARMER_INST = "fmnr_farmer_inst";
    // fmnr species table
    public static final String TABLE_FMNR_SPECIES = "fmnr_species";
    //columns for farmer/institution info
    public static final String _ID_fmnr = "_id";
    public static final String fmnr_farmer_id = "farmerID";
    public static final String fmnr_enum_name = "ename";
    public static final String fmnr_date = "in_date";
    public static final String fmnr_farmer_inst_name = "name";
    public static final String fmnr_country = "country";
    public static final String fmnr_county_region = "county_region";
    public static final String fmnr_district = "district";
    public static final String fmnr_planting_location = "planting_location";
    public static final String fmnr_landsize_regreen = "landsize_regreen";
    //columns for fmnr species
    public static final String fmnrfarmer_id = "farmerID";
    public static final String fmnr_species_name = "species";
    public static final String fmnr_local_name = "local_name";
    public static final String fmnr_management_pruning = "mg1";
    public static final String fmnr_management_fencing = "mg2";
    public static final String fmnr_management_weeding = "mg3";
    public static final String fmnr_management_watering = "mg4";
    public static final String fmnr_management_organic_fertilizer = "mg5";
    public static final String fmnr_management_other = "mg_other";
    public static final String fmnr_use_firewood = "usage1";
    public static final String fmnr_use_housing_construction = "usage2";
    public static final String fmnr_use_animal_feed = "usage3";
    public static final String fmnr_use_food = "usage4";
    public static final String fmnr_use_mulching = "usage5";
    public static final String fmnr_use_other = "us_other";
    public static final String fmnr_tree_stems = "stems";
    public static final String fmnr_tree_height = "height";
    public static final String fmnr_tree_rcd = "rcd";
    public static final String fmnr_tree_dbh = "dbh";
    public static final String fmnr_tree_latitude = "latitude";
    public static final String fmnr_tree_longitude = "longitude";
    public static final String fmnr_tree_altitude = "altitude";
    public static final String fmnr_tree_accuracy = "accuracy";
    public static final String fmnr_tree_image_path = "path";
    // Database name
    static final String DB_NAME = "regreen_africa.sqlite";

    // database version
    static final int DB_VERSION = 1;

    // Creating farmer/institution table for tree planting
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
    //nursery info table
    private static final String CREATE_TABLE_NURSERY =
            "create table " +
                    TABLE_NURSERY + "(" +
                    id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nursery_id + " TEXT NOT NULL," + nursery_country + " TEXT NOT NULL, " + nursery_county + " TEXT NOT NULL, " + nursery_district + " TEXT,"+
                    nursery_operator + " TEXT,"+nursery_contact + " TEXT,"+type_government + " TEXT,"+type_church_mosque + " TEXT,"+type_schools + " TEXT,"+type_women_groups + " TEXT,"+type_youth_groups + " TEXT,"+type_private_individual + " TEXT,"+type_communal_village + " TEXT,"+
                    other_nursery_types + " TEXT,"+ nursery_latitude + " TEXT,"+nursery_longitude + " TEXT,"+nursery_altitude + " TEXT,"+nursery_accuracy+" TEXT,"+nursery_image_path + " TEXT);";
    //create nursery species
    private static final String CREATE_TABLE_NURSERY_SPECIES =
            "create table " +
                    TABLE_NURSERY_SPECIES + "(" +
                    _nid + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nurseryID + " TEXT NOT NULL," + nursery_species + " TEXT NOT NULL, " + nursery_local + " TEXT NOT NULL, " + method_bare_root + " TEXT,"+
                    method_containerised + " TEXT,"+other_methods + " TEXT,"+propagation_seed + " TEXT,"+propagation_graft + " TEXT,"+propagation_cutting + " TEXT,"+propagation_marcotting + " TEXT,"+seed_source_onfarm + " TEXT,"+seed_source_local_dealer + " TEXT," +
                    ""+seed_source_national_dealer + " TEXT,"+seed_source_NGOs + " TEXT,"+other_seed_sources + " TEXT,"+graft_source_farmland + " TEXT,"+graft_source_plantation + " TEXT,"+graft_source_mother_blocks + " TEXT,"+graft_source_prisons + " TEXT,"+graft_source_others + " TEXT," +
                    ""+seeds_quantity_purchased + " TEXT,"+date_seeds_sown + " TEXT,"+seedlings_germinated + " TEXT,"+seedlings_servived + " TEXT,"+seedlings_age + " TEXT,"+seedlings_price + " TEXT);";
    // Creating farmer/institution table for fmnr
    private static final String CREATE_TABLE_FMNR_FARMER_INST =
            "create table " +
                    TABLE_FMNR_FARMER_INST + "(" +
                    _ID_fmnr + " INTEGER PRIMARY KEY AUTOINCREMENT, " + fmnr_enum_name + " TEXT NOT NULL, " + fmnr_date + " TEXT NOT NULL, " + fmnr_farmer_inst_name + " TEXT,"+
                    fmnr_country + " TEXT,"+fmnr_county_region +  " TEXT,"+fmnr_district + " TEXT,"+fmnr_planting_location + " TEXT,"+fmnr_landsize_regreen + " TEXT,"+
                    fmnr_farmer_id+" TEXT);";
    //creating table for measurement fmnr
    private static final String CREATE_TABLE_FMNR_SPECIES =
            "create table " +
                    TABLE_FMNR_SPECIES + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ fmnr_species_name + " TEXT,"+fmnr_local_name + " TEXT,"+fmnr_management_pruning + " TEXT,"+fmnr_management_fencing + " TEXT,"+fmnr_management_weeding+" TEXT,"+fmnr_management_watering + " TEXT,"+
                    fmnr_management_organic_fertilizer + " TEXT,"+fmnr_management_other + " TEXT,"+ fmnr_use_firewood + " TEXT,"+fmnr_use_housing_construction + " TEXT,"+fmnr_use_animal_feed+" TEXT,"+fmnr_use_food +
                    " TEXT,"+fmnr_use_mulching +  " TEXT,"+fmnr_use_other + " TEXT,"+fmnr_tree_stems + " TEXT,"+fmnr_tree_height+" TEXT,"+fmnr_tree_rcd +  " TEXT,"+fmnr_tree_dbh + " TEXT,"+fmnr_tree_latitude + " TEXT,"+fmnr_tree_longitude+" TEXT,"+
                    fmnr_tree_altitude +  " TEXT,"+fmnr_tree_accuracy + " TEXT,"+fmnr_tree_image_path + " TEXT,"+fmnrfarmer_id+" TEXT);";

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
        db.execSQL(CREATE_TABLE_NURSERY);
        db.execSQL(CREATE_TABLE_NURSERY_SPECIES);
        db.execSQL(CREATE_TABLE_FMNR_FARMER_INST);
        db.execSQL(CREATE_TABLE_FMNR_SPECIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop old ones
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FARMER_INST);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_COHORT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TREE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TRAININGS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_NURSERY);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_NURSERY_SPECIES);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FMNR_FARMER_INST);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FMNR_SPECIES);
        //create new tables
        onCreate(db);
    }
}

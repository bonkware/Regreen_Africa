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
    public static final String survey_name = "survey_name";
    public static final String farmer_inst_name = "name";
    public static final String country = "country";
    public static final String county_region = "county_region";
    public static final String district = "district";
    //public static final String planting_location = "planting_location";
    public static final String land_individual = "land_individual";
    public static final String land_community = "land_community";
    public static final String land_government = "land_government";
    public static final String land_mosque_church = "land_mosque_church";
    public static final String land_schools = "land_schools";
    public static final String land_other = "land_other";
    //public static final String planting_site = "planting_site";
    public static final String landsize_regreen = "landsize_regreen";
    public static final String tp_units = "units";

    //Cohort columns
    public static final String farmerID = "farmerID";
    public static final String cohort_id = "cohortID";
    public static final String species_name = "species";
    public static final String date_planted = "date_planted";
    public static final String number_planted = "number_planted";
    public static final String number_survived = "number_survived";
    public static final String woodlot = "woodlot";
    public static final String iboundary = "iboundary";
    public static final String eboundary = "eboundary";
    public static final String garden = "garden";
    public static final String crop_field = "crop_field";
    public static final String pasture_grassland = "pasture_grassland";
    public static final String fallow_bushland = "fallow_bushland";
    public static final String other_sites = "other_sites";
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
    //public static final String tree_rcd = "rcd";
    public static final String tree_dbh = "dbh";
    public static final String tree_latitude = "tree_latitude";
    public static final String tree_longitude = "tree_longitude";
    public static final String tree_altitude = "tree_altitude";
    public static final String tree_accuracy = "tree_accuracy";
    public static final String tree_image_path = "path";

    //table for trainings
    public static final String _id = "_id";
    public  static  final String training_enum_name = "enum_name";
    public  static  final String training_record_date = "date";
    public  static  final String training_survey_name = "survey_name";
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
    public static final String nursery_enum_name = "ename";
    public static final String nursery_date = "in_date";
    public static final String nursery_survey_name = "survey_name";
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
    public static final String units = "units";
    public static final String seed_sown = "seed_sown";
    public static final String unitsown = "units_sown";
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
    public static final String famnr_survey_name = "survey_name";
    public static final String fmnr_farmer_inst_name = "name";
    public static final String fmnr_country = "country";
    public static final String fmnr_county_region = "county_region";
    public static final String fmnr_district = "district";
    //public static final String fmnr_planting_location = "planting_location";
    public static final String fmnr_land_individual = "fmnr_land_individual";
    public static final String fmnr_land_community = "fmnr_land_community";
    public static final String fmnr_land_government = "fmnr_land_government";
    public static final String fmnr_land_mosque_church = "fmnr_land_mosque_church";
    public static final String fmnr_land_schools = "fmnr_land_schools";
    public static final String fmnr_land_other = "fmnr_land_other";
    public static final String fmnr_species_number_start = "fmnr_species_number_start";
    //public static final String fmnr_restoration_photo = "fmnr_restoration_photo";
    public static final String fmnr_started_date = "fmnr_started_date";
    public static final String fmnr_fenced = "fmnr_fenced";
    public static final String fmnr_landsize_regreen = "landsize_regreen";
    public static final String fmnr_units = "units";

    //columns for fmnr species
    public static final String fmnrfarmer_id = "farmerID";
    public static final String fmnr_species_name = "species";
    public static final String fmnr_local_name = "local_name";
    public static final String fmnr_management_pruning = "mg1";
    public static final String fmnr_management_fencing = "mg2";
    public static final String fmnr_management_weeding = "mg3";
    public static final String fmnr_management_thinning = "mg4";
    public static final String fmnr_management_organic_fertilizer = "mg5";
    public static final String fmnr_management_pollarding_lopping = "mg6";
    public static final String fmnr_management_other = "mg_other";
    public static final String fmnr_use_firewood = "usage1";
    public static final String fmnr_use_housing_construction = "usage2";
    public static final String fmnr_use_fodder = "usage3";
    public static final String fmnr_use_fruits = "usage4";
    public static final String fmnr_use_soil_fertility = "usage5";
    public static final String fmnr_use_leafy_vegetables = "usage6";
    public static final String fmnr_use_nuts = "usage7";
    public static final String fmnr_use_other = "us_other";
    public static final String fmnr_tree_stems = "stems";
    public static final String fmnr_tree_height = "height";
    //public static final String fmnr_tree_rcd = "rcd";
    public static final String fmnr_tree_dbh = "dbh";
    public static final String fmnr_tree_latitude = "tree_latitude";
    public static final String fmnr_tree_longitude = "tree_longitude";
    public static final String fmnr_tree_altitude = "tree_altitude";
    public static final String fmnr_tree_accuracy = "tree_accuracy";
    public static final String fmnr_tree_image_path = "path";

    //table for fmnr and tree planting landsize polygon
    public static final String TABLE_LANDSIZEPOLYGON = "landsizepolygon";
    //columns for both fmnr and tree planting landsize polygons
    public static final String fid = "farmerID";
    public static final String landsize_polygon_latitude = "latitude";
    public static final String landsize_polygon_longitude = "longitude";
    public static final String landsize_polygon_altitude = "altitude";
    public static final String landsize_polygon_accuracy = "accuracy";
    // Database name
    static final String DB_NAME = "regreen_africa.sqlite";

    // database version
    static final int DB_VERSION = 1;

    // Creating farmer/institution table for tree planting
    private static final String CREATE_TABLE_FARMER_INST =
            "create table " +
                    TABLE_FARMER_INST + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + enum_name + " TEXT NOT NULL, " + date + " TEXT NOT NULL,  " + survey_name + " TEXT, " + farmer_inst_name + " TEXT,"+
                    country + " TEXT,"+county_region +  " TEXT,"+district + " TEXT,"+land_individual + " TEXT,"+land_community + " TEXT,"+land_government + " TEXT,"+land_mosque_church + " TEXT,"+land_schools + " TEXT,"+land_other + " TEXT,"+landsize_regreen + " TEXT,"+tp_units + " TEXT,"+
                    farmer_id+" TEXT);";

    //creating cohort table
    private static final String CREATE_TABLE_COHORT =
            "create table " +
                    TABLE_COHORT + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ species_name + " TEXT,"+date_planted + " TEXT,"+number_planted + " TEXT,"+number_survived + " TEXT,"+woodlot + " TEXT,"+iboundary + " TEXT,"+eboundary + " TEXT,"
                    +garden + " TEXT,"+crop_field + " TEXT,"+pasture_grassland + " TEXT,"+fallow_bushland + " TEXT,"+other_sites + " TEXT,"+management_pruning+" TEXT,"+management_fencing + " TEXT,"+
                    management_weeding + " TEXT,"+management_watering + " TEXT,"+ management_organic_fertilizer + " TEXT,"+management_other + " TEXT,"+use_firewood+" TEXT,"+use_housing_construction +
                    " TEXT,"+use_animal_feed +  " TEXT,"+use_food + " TEXT,"+use_mulching + " TEXT,"+use_other+" TEXT,"+farmerID+" TEXT,"+cohort_id+" TEXT);";
    //creating tree measurement table
    private static final String CREATE_TABLE_TREE =
            "create table " +
                    TABLE_Measurement + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ tree_height + " TEXT,"+tree_dbh + " TEXT,"+tree_latitude + " TEXT,"+tree_longitude+" TEXT,"+tree_altitude + " TEXT,"+
                    tree_accuracy +" TEXT,"+tree_image_path + " TEXT,"+cohortID+" TEXT);";
    //creating training table
    private static final String CREATE_TABLE_TRAININGS =
            "create table " +
                    TABLE_Trainings + "(" +
                    _id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ training_enum_name + " TEXT,"+ training_record_date + " TEXT,"+ training_survey_name + " TEXT,"+ training_country + " TEXT,"+training_region + " TEXT,"+training_district + " TEXT,"+training_topic + " TEXT,"+training_date+" TEXT,"+training_venue + " TEXT,"+
                    training_partners +" TEXT,"+training_participants + " TEXT,"+male_participants+" TEXT,"+female_participants+" TEXT,"+youth_participants+" TEXT);";
    //nursery info table
    private static final String CREATE_TABLE_NURSERY =
            "create table " +
                    TABLE_NURSERY + "(" +
                    id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nursery_id + " TEXT NOT NULL," + nursery_enum_name + " TEXT," + nursery_date + " TEXT," + nursery_survey_name + " TEXT NOT NULL," + nursery_country + " TEXT NOT NULL, " + nursery_county + " TEXT NOT NULL, " + nursery_district + " TEXT,"+
                    nursery_operator + " TEXT,"+nursery_contact + " TEXT,"+type_government + " TEXT,"+type_church_mosque + " TEXT,"+type_schools + " TEXT,"+type_women_groups + " TEXT,"+type_youth_groups + " TEXT,"+type_private_individual + " TEXT,"+type_communal_village + " TEXT,"+
                    other_nursery_types + " TEXT,"+ nursery_latitude + " TEXT,"+nursery_longitude + " TEXT,"+nursery_altitude + " TEXT,"+nursery_accuracy+" TEXT,"+nursery_image_path + " TEXT);";
    //create nursery species
    private static final String CREATE_TABLE_NURSERY_SPECIES =
            "create table " +
                    TABLE_NURSERY_SPECIES + "(" +
                    _nid + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nurseryID + " TEXT NOT NULL," + nursery_species + " TEXT NOT NULL, " + nursery_local + " TEXT NOT NULL, " + method_bare_root + " TEXT,"+
                    method_containerised + " TEXT,"+other_methods + " TEXT,"+propagation_seed + " TEXT,"+propagation_graft + " TEXT,"+propagation_cutting + " TEXT,"+propagation_marcotting + " TEXT,"+seed_source_onfarm + " TEXT,"+seed_source_local_dealer + " TEXT," +
                    ""+seed_source_national_dealer + " TEXT,"+seed_source_NGOs + " TEXT,"+other_seed_sources + " TEXT,"+graft_source_farmland + " TEXT,"+graft_source_plantation + " TEXT,"+graft_source_mother_blocks + " TEXT,"+graft_source_prisons + " TEXT,"+graft_source_others + " TEXT," +
                    ""+seeds_quantity_purchased + " TEXT,"+units + " TEXT,"+seed_sown + " TEXT,"+unitsown + " TEXT,"+date_seeds_sown + " TEXT,"+seedlings_germinated + " TEXT,"+seedlings_servived + " TEXT,"+seedlings_age + " TEXT,"+seedlings_price + " TEXT);";
    // Creating farmer/institution table for fmnr
    private static final String CREATE_TABLE_FMNR_FARMER_INST =
            "create table " +
                    TABLE_FMNR_FARMER_INST + "(" +
                    _ID_fmnr + " INTEGER PRIMARY KEY AUTOINCREMENT, " + fmnr_enum_name + " TEXT NOT NULL, " + fmnr_date + " TEXT NOT NULL, " + famnr_survey_name + " TEXT, " + fmnr_farmer_inst_name + " TEXT,"+
                    fmnr_country + " TEXT,"+fmnr_county_region +  " TEXT,"+fmnr_district + " TEXT,"+fmnr_land_individual + " TEXT,"+fmnr_land_community + " TEXT,"+fmnr_land_government + " TEXT,"+fmnr_land_mosque_church + " TEXT,"+fmnr_land_schools + " TEXT,"+fmnr_land_other + " TEXT,"+fmnr_species_number_start + " TEXT,"+fmnr_started_date + " TEXT,"+fmnr_fenced + " TEXT,"+fmnr_landsize_regreen + " TEXT,"+fmnr_units + " TEXT,"+
                    fmnr_farmer_id+" TEXT);";
    //creating table for measurement fmnr
    private static final String CREATE_TABLE_FMNR_SPECIES =
            "create table " +
                    TABLE_FMNR_SPECIES + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT,"+ fmnr_species_name + " TEXT,"+fmnr_local_name + " TEXT,"+fmnr_management_pruning + " TEXT,"+fmnr_management_fencing + " TEXT,"+fmnr_management_weeding+" TEXT,"+fmnr_management_thinning + " TEXT,"+
                    fmnr_management_organic_fertilizer + " TEXT,"+fmnr_management_pollarding_lopping + " TEXT,"+fmnr_management_other + " TEXT,"+ fmnr_use_firewood + " TEXT,"+fmnr_use_housing_construction + " TEXT,"+fmnr_use_fodder+" TEXT,"+fmnr_use_fruits +
                    " TEXT,"+fmnr_use_soil_fertility +  " TEXT,"+fmnr_use_leafy_vegetables +  " TEXT,"+fmnr_use_nuts +  " TEXT,"+fmnr_use_other + " TEXT,"+fmnr_tree_stems + " TEXT,"+fmnr_tree_height+" TEXT,"+fmnr_tree_dbh + " TEXT,"+fmnr_tree_latitude + " TEXT,"+fmnr_tree_longitude+" TEXT,"+
                    fmnr_tree_altitude +  " TEXT,"+fmnr_tree_accuracy + " TEXT,"+fmnr_tree_image_path + " TEXT,"+fmnrfarmer_id+" TEXT);";
    //creating columns for polygon
    private static final String CREATE_TABLE_LANDSIZEPOLYGON =
            "create table " +
                    TABLE_LANDSIZEPOLYGON + "(" +
                    id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + fid + " TEXT NOT NULL,"+ landsize_polygon_latitude + " TEXT,"+landsize_polygon_longitude + " TEXT,"+landsize_polygon_altitude + " TEXT,"+landsize_polygon_accuracy+" TEXT);";

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
        db.execSQL(CREATE_TABLE_LANDSIZEPOLYGON);
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


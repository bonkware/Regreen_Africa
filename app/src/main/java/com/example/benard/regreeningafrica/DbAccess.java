package com.example.benard.regreeningafrica;

/**
 * Created by benard on 7/01/18.
 * contains all queries
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.benard.regreeningafrica.DatabaseHelper.TABLE_COHORT;
import static com.example.benard.regreeningafrica.DatabaseHelper.TABLE_FARMER_INST;
import static com.example.benard.regreeningafrica.DatabaseHelper.TABLE_Measurement;
import static com.example.benard.regreeningafrica.DatabaseHelper.TABLE_NURSERY;
import static com.example.benard.regreeningafrica.DatabaseHelper.TABLE_NURSERY_SPECIES;
import static com.example.benard.regreeningafrica.DatabaseHelper.TABLE_Trainings;

public class DbAccess {
    RegreeningGlobal g = RegreeningGlobal.getInstance();

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DbAccess(Context c) {
        context = c;
    }

    public DbAccess open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    //close db
    public void close() {
        dbHelper.close();
    }
    //insert farmer details
    public void insertFarmerInst() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.enum_name, g.getename());
        contentValue.put(DatabaseHelper.date, g.getin_date());
        contentValue.put(DatabaseHelper.farmer_inst_name, g.getfname());
        contentValue.put(DatabaseHelper.country, g.getcountry());
        contentValue.put(DatabaseHelper.county_region, g.getcounty_region());
        contentValue.put(DatabaseHelper.district, g.getdistricts());
        contentValue.put(DatabaseHelper.planting_location, g.getselect_location());
        contentValue.put(DatabaseHelper.planting_site, g.getselect_site());
        contentValue.put(DatabaseHelper.landsize_regreen, g.getlandsize());
        contentValue.put(DatabaseHelper.farmerID, g.getfid());
        //insert
        database.insert(TABLE_FARMER_INST, null, contentValue);
    }
    //insert tree cohort
    public void insertCohort() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.farmerID, g.getfid());
        contentValue.put(DatabaseHelper.cohortID, g.getcid());
        contentValue.put(DatabaseHelper.species_name, g.getspecies_name());
        contentValue.put(DatabaseHelper.date_planted, g.getdate_planted());
        contentValue.put(DatabaseHelper.number_planted, g.getnumber_planted());
        contentValue.put(DatabaseHelper.number_survived, g.getnumber_survived());
        contentValue.put(DatabaseHelper.management_pruning, g.getmg1());
        contentValue.put(DatabaseHelper.management_fencing, g.getmg2());
        contentValue.put(DatabaseHelper.management_weeding, g.getmg3());
        contentValue.put(DatabaseHelper.management_watering, g.getmg4());
        contentValue.put(DatabaseHelper.management_organic_fertilizer, g.getmg5());
        contentValue.put(DatabaseHelper.management_other, g.getmg_other());
        contentValue.put(DatabaseHelper.use_firewood, g.getusage1());
        contentValue.put(DatabaseHelper.use_housing_construction, g.getusage2());
        contentValue.put(DatabaseHelper.use_animal_feed, g.getusage3());
        contentValue.put(DatabaseHelper.use_food, g.getusage4());
        contentValue.put(DatabaseHelper.use_mulching, g.getusage5());
        contentValue.put(DatabaseHelper.use_other, g.getus_other());
        //insert
        database.insert(TABLE_COHORT, null, contentValue);
    }

    //insert tree measurement
    public void insertMeasurent() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.cohortID, g.getcid());
        contentValue.put(DatabaseHelper.tree_height, g.getheight());
        contentValue.put(DatabaseHelper.tree_rcd, g.getrcd());
        contentValue.put(DatabaseHelper.tree_dbh, g.getdbh());
        contentValue.put(DatabaseHelper.tree_latitude, g.getLatitude());
        contentValue.put(DatabaseHelper.tree_longitude, g.getLongitude());
        contentValue.put(DatabaseHelper.tree_altitude, g.getAltitude());
        contentValue.put(DatabaseHelper.tree_accuracy, g.getAccuracy());
        contentValue.put(DatabaseHelper.tree_image_path, g.getpath());
        //insert
        database.insert(TABLE_Measurement, null, contentValue);
    }

    //insert trainings
    public void insertTraining() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.training_country, g.getc_name());
        contentValue.put(DatabaseHelper.training_region, g.getcr_name());
        contentValue.put(DatabaseHelper.training_district, g.getdcw_name());
        contentValue.put(DatabaseHelper.training_topic, g.gettraining_topic());
        contentValue.put(DatabaseHelper.training_date, g.gettraining_date());
        contentValue.put(DatabaseHelper.training_venue, g.gettraining_venue());
        contentValue.put(DatabaseHelper.training_partners, g.gettraining_partners());
        contentValue.put(DatabaseHelper.training_participants, g.getnumber_participants());
        contentValue.put(DatabaseHelper.male_participants, g.getmale_participants());
        contentValue.put(DatabaseHelper.female_participants, g.getfemale_participants());
        contentValue.put(DatabaseHelper.youth_participants, g.getyouth_participants());
        //insert
        database.insert(TABLE_Trainings, null, contentValue);
    }
    //insert nursery info
    public void insertNurseryInfo() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.nursery_country, g.getnursery_country());
        contentValue.put(DatabaseHelper.nursery_county, g.getnursery_county());
        contentValue.put(DatabaseHelper.nursery_district, g.getnursery_district());
        contentValue.put(DatabaseHelper.nursery_operator, g.getnursery_operator());
        contentValue.put(DatabaseHelper.nursery_contact, g.getnursery_contact());
        contentValue.put(DatabaseHelper.type_government, g.getgovt());
        contentValue.put(DatabaseHelper.type_church_mosque, g.getchurch_mosque());
        contentValue.put(DatabaseHelper.type_schools, g.getschools());
        contentValue.put(DatabaseHelper.type_women_groups, g.getwomen());
        contentValue.put(DatabaseHelper.type_youth_groups, g.getyouth());
        contentValue.put(DatabaseHelper.type_private_individual, g.getprivate_individual());
        contentValue.put(DatabaseHelper.type_communal_village, g.getcommunal_village());
        contentValue.put(DatabaseHelper.other_nursery_types, g.getother_type());
        contentValue.put(DatabaseHelper.nursery_latitude, g.getLatitude());
        contentValue.put(DatabaseHelper.nursery_longitude, g.getLongitude());
        contentValue.put(DatabaseHelper.nursery_altitude, g.getAltitude());
        contentValue.put(DatabaseHelper.nursery_accuracy, g.getAccuracy());
        contentValue.put(DatabaseHelper.nursery_image_path, g.getimage());
        contentValue.put(DatabaseHelper.nursery_id, g.getnid());
        //insert
        database.insert(TABLE_NURSERY, null, contentValue);
    }
    public void insertNurserySpecies() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.nurseryID, g.getnid());
        contentValue.put(DatabaseHelper.nursery_species, g.getnursery_species());
        contentValue.put(DatabaseHelper.nursery_local, g.getnursery_local());
        contentValue.put(DatabaseHelper.method_bare_root, g.getbare_root());
        contentValue.put(DatabaseHelper.method_containerised, g.getcontainer());
        contentValue.put(DatabaseHelper.other_methods, g.getother_method());
        contentValue.put(DatabaseHelper.propagation_seed, g.getseed());
        contentValue.put(DatabaseHelper.propagation_graft, g.getgraft());
        contentValue.put(DatabaseHelper.propagation_cutting, g.getcutting());
        contentValue.put(DatabaseHelper.propagation_marcotting, g.getMarcotting());
        contentValue.put(DatabaseHelper.seed_source_onfarm, g.getown_farm_seeds());
        contentValue.put(DatabaseHelper.seed_source_local_dealer, g.getlocal_dealer_seeds());
        contentValue.put(DatabaseHelper.seed_source_national_dealer, g.getnational_seed());
        contentValue.put(DatabaseHelper.seed_source_NGOs, g.getngos_seed());
        contentValue.put(DatabaseHelper.other_seed_sources, g.getother_seed_source());
        contentValue.put(DatabaseHelper.graft_source_farmland, g.getfarmland());
        contentValue.put(DatabaseHelper.graft_source_plantation, g.getplantation());
        contentValue.put(DatabaseHelper.graft_source_mother_blocks, g.getmother_blocks());
        contentValue.put(DatabaseHelper.graft_source_prisons, g.getprisons());
        contentValue.put(DatabaseHelper.graft_source_others, g.getother_graft_sources());
        contentValue.put(DatabaseHelper.seeds_quantity_purchased, g.getqpurchased());
        contentValue.put(DatabaseHelper.date_seeds_sown, g.getdate_sown());
        contentValue.put(DatabaseHelper.seedlings_germinated, g.getgerminated());
        contentValue.put(DatabaseHelper.seedlings_servived, g.getsurvived());
        contentValue.put(DatabaseHelper.seedlings_age, g.getseedlings_age());
        contentValue.put(DatabaseHelper.seedlings_price, g.getprice());
        //insert
        database.insert(TABLE_NURSERY_SPECIES, null, contentValue);
    }

    //get records in view
    public Cursor fetch() {
        String selectQuery = "Select * from farmer_data,tree_data WHERE farmer_data.farmerID = tree_data.farmerID";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    public Cursor fetch_nursery() {
        String selectQuery = "Select * from nursery_profile,nursery_trees WHERE nursery_profile.nurseryID = nursery_trees.nurseryID";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    public void deleteFarmer_Inst(){
        database.execSQL("delete from "+ TABLE_FARMER_INST);
    }
    public void deleteCohort(){
        database.execSQL("delete from "+ TABLE_COHORT);
    }
    public void deleteMeasurements(){
        database.execSQL("delete from "+ TABLE_Measurement);
    }
    public void deleteTrainings(){
        database.execSQL("delete from "+ TABLE_Trainings);
    }
    //get all records from db for sending
    //fetch from all tables where farmer ID match
    public Cursor getTP() {
        //String selectQuery = "SELECT * FROM farmer_institution,cohort,tree_measurements";
        String selectQuery = "SELECT * FROM farmer_institution,cohort,tree_measurements WHERE farmer_institution.farmerID = cohort.farmerID and cohort.cohortID = tree_measurements.cohortID ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getTrainings() {
        String selectQuery = "SELECT * FROM trainings";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    //count no. of records tree planting
    public int getcount(){
        //Cursor cur = database.rawQuery(" SELECT Count(*) FROM " + TABLE_FARMER_INST, null);
        Cursor cur = database.rawQuery("SELECT count(*) from farmer_institution,cohort,tree_measurements WHERE farmer_institution.farmerID = cohort.farmerID and cohort.cohortID = tree_measurements.cohortID ", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
    //count no. of records trainings
    public int getcount_trainings(){
        Cursor cur = database.rawQuery(" SELECT Count(*) FROM " + TABLE_Trainings, null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }

    //count no. of records tree planting
    public int getnurserycount(){
        //Cursor cur = database.rawQuery(" SELECT Count(*) FROM " + TABLE_FARMER_INST, null);
        Cursor cur = database.rawQuery("SELECT count(*) from nursery_info,nursery_species WHERE nursery_info.nurseryID = nursery_species.nurseryID ", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
}

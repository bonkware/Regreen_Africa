package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 7/01/18.
 * contains all queries
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_COHORT;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_FARMER_INST;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_FMNR_FARMER_INST;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_FMNR_SPECIES;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_LANDSIZEPOLYGON;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_Measurement;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_NURSERY;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_NURSERY_SPECIES;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_Trainings;

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
        contentValue.put(DatabaseHelper.survey_name, g.gettpsurvey_name());
        contentValue.put(DatabaseHelper.farmer_inst_name, g.getfname());
        contentValue.put(DatabaseHelper.country, g.getcountry());
        contentValue.put(DatabaseHelper.county_region, g.getcounty_region());
        contentValue.put(DatabaseHelper.district, g.getdistricts());
        //contentValue.put(DatabaseHelper.planting_location, g.getselect_location());
        contentValue.put(DatabaseHelper.land_individual, g.getindividual_ownership());
        contentValue.put(DatabaseHelper.land_community, g.getcommunity_ownership());
        contentValue.put(DatabaseHelper.land_government, g.getgovt_land_ownership());
        contentValue.put(DatabaseHelper.land_mosque_church, g.getmosque_church_ownership());
        contentValue.put(DatabaseHelper.land_schools, g.getschools_ownership());
        contentValue.put(DatabaseHelper.land_other, g.getother_ownership());
        //contentValue.put(DatabaseHelper.planting_site, g.getselect_site());
        contentValue.put(DatabaseHelper.landsize_regreen, g.getlandsize());
        contentValue.put(DatabaseHelper.tp_units, g.getunits());
        contentValue.put(DatabaseHelper.tp_uploaded, g.getuploaded());
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
        contentValue.put(DatabaseHelper.woodlot, g.getwoodlot());
        contentValue.put(DatabaseHelper.iboundary, g.getiboundary());
        contentValue.put(DatabaseHelper.eboundary, g.geteboundary());
        contentValue.put(DatabaseHelper.garden, g.getgarden());
        contentValue.put(DatabaseHelper.crop_field, g.getcrop_field());
        contentValue.put(DatabaseHelper.pasture_grassland, g.getpasture_grassland());
        contentValue.put(DatabaseHelper.fallow_bushland, g.getfallow_pushland());
        contentValue.put(DatabaseHelper.other_sites, g.getother_sites());
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
        //contentValue.put(DatabaseHelper.tree_rcd, g.getrcd());
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
        contentValue.put(DatabaseHelper.training_enum_name, g.getename());//added
        contentValue.put(DatabaseHelper.training_record_date, g.getin_date());//added
        contentValue.put(DatabaseHelper.training_survey_name, g.gettsurvey_name());
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
        contentValue.put(DatabaseHelper.uploaded, g.getuploaded());
        //insert
        database.insert(TABLE_Trainings, null, contentValue);
    }
    //insert nursery info
    public void insertNurseryInfo() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.nursery_enum_name, g.getename());//added
        contentValue.put(DatabaseHelper.nursery_date, g.getin_date());//added
        contentValue.put(DatabaseHelper.nursery_survey_name, g.getnsurvey_name());//added
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
        contentValue.put(DatabaseHelper.nursery_image_path, g.getpath());
        contentValue.put(DatabaseHelper.nursery_id, g.getnid());
        contentValue.put(DatabaseHelper.nursery_uploaded, g.getuploaded());
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
        contentValue.put(DatabaseHelper.qunits, g.getqunits());
        contentValue.put(DatabaseHelper.seed_sown, g.getseed_sown());
        contentValue.put(DatabaseHelper.unitsown, g.getunitsown());
        contentValue.put(DatabaseHelper.date_seeds_sown, g.getdate_sown());
        contentValue.put(DatabaseHelper.seedlings_germinated, g.getgerminated());
        contentValue.put(DatabaseHelper.seedlings_servived, g.getsurvived());
        contentValue.put(DatabaseHelper.seedlings_age, g.getseedlings_age());
        contentValue.put(DatabaseHelper.seedlings_price, g.getprice());
        //insert
        database.insert(TABLE_NURSERY_SPECIES, null, contentValue);
    }
    //insert farmer details
    public void insertFmnrFarmerInst() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.farmerID, g.getfid());
        contentValue.put(DatabaseHelper.fmnr_enum_name, g.getename());
        contentValue.put(DatabaseHelper.fmnr_date, g.getin_date());
        contentValue.put(DatabaseHelper.famnr_survey_name, g.getfsurvey_name());
        contentValue.put(DatabaseHelper.fmnr_farmer_inst_name, g.getfname());
        contentValue.put(DatabaseHelper.fmnr_country, g.getcountry());
        contentValue.put(DatabaseHelper.fmnr_county_region, g.getcounty_region());
        contentValue.put(DatabaseHelper.fmnr_district, g.getdistricts());
        //contentValue.put(DatabaseHelper.fmnr_planting_location, g.getselect_location());
        contentValue.put(DatabaseHelper.fmnr_land_individual, g.getindividual_ownership());
        contentValue.put(DatabaseHelper.fmnr_land_community, g.getcommunity_ownership());
        contentValue.put(DatabaseHelper.fmnr_land_government, g.getgovt_land_ownership());
        contentValue.put(DatabaseHelper.fmnr_land_mosque_church, g.getmosque_church_ownership());
        contentValue.put(DatabaseHelper.fmnr_land_schools, g.getschools_ownership());
        contentValue.put(DatabaseHelper.fmnr_land_other, g.getother_ownership());

        contentValue.put(DatabaseHelper.fmnr_species_number_start, g.getspecies_number());
       // contentValue.put(DatabaseHelper.fmnr_restoration_photo, g.getpath());
        contentValue.put(DatabaseHelper.fmnr_started_date, g.getfmnr_date());
        contentValue.put(DatabaseHelper.fmnr_fenced, g.getfmnr_fenced());
        contentValue.put(DatabaseHelper.fmnr_landsize_regreen, g.getlandsize());
        contentValue.put(DatabaseHelper.fmnr_units, g.getunits());
        contentValue.put(DatabaseHelper.fmnr_uploaded, g.getuploaded());
        //insert
        database.insert(TABLE_FMNR_FARMER_INST, null, contentValue);
    }
    //insert polygon points
    public void insertLandsizepolygon() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fid, g.getfid());
        contentValue.put(DatabaseHelper.pid, g.getpid());
        contentValue.put(DatabaseHelper.landsize_polygon_latitude, g.getLatitude());
        contentValue.put(DatabaseHelper.landsize_polygon_longitude, g.getLongitude());
        contentValue.put(DatabaseHelper.landsize_polygon_altitude, g.getAltitude());
        contentValue.put(DatabaseHelper.landsize_polygon_accuracy, g.getAccuracy());
        //insert
        database.insert(TABLE_LANDSIZEPOLYGON, null, contentValue);
    }
    //insert fmnr species
    public void insertFmnrSpecies() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.farmerID, g.getfid());
        contentValue.put(DatabaseHelper.fmnr_species_name, g.getspecies_name());
        contentValue.put(DatabaseHelper.fmnr_local_name, g.getlocal_name());
        contentValue.put(DatabaseHelper.fmnr_management_pruning, g.getmg1());
        contentValue.put(DatabaseHelper.fmnr_management_fencing, g.getmg2());
        contentValue.put(DatabaseHelper.fmnr_management_weeding, g.getmg3());
        contentValue.put(DatabaseHelper.fmnr_management_thinning, g.getmg4());
        contentValue.put(DatabaseHelper.management_organic_fertilizer, g.getmg5());
        contentValue.put(DatabaseHelper.fmnr_management_pollarding_lopping, g.getmg6());
        contentValue.put(DatabaseHelper.fmnr_management_coppicing, g.getmg7());
        contentValue.put(DatabaseHelper.fmnr_management_other, g.getmg_other());
        contentValue.put(DatabaseHelper.fmnr_use_firewood, g.getusage1());
        contentValue.put(DatabaseHelper.fmnr_use_housing_construction, g.getusage2());
        contentValue.put(DatabaseHelper.fmnr_use_fodder, g.getusage3());
        contentValue.put(DatabaseHelper.fmnr_use_fruits, g.getusage4());
        contentValue.put(DatabaseHelper.fmnr_use_soil_fertility, g.getusage5());
        contentValue.put(DatabaseHelper.fmnr_use_leafy_vegetables, g.getusage6());
        contentValue.put(DatabaseHelper.fmnr_use_nuts, g.getusage7());
        contentValue.put(DatabaseHelper.fmnr_use_other, g.getus_other());
        contentValue.put(DatabaseHelper.fmnr_tree_stems, g.getstems());
        contentValue.put(DatabaseHelper.fmnr_tree_height, g.getheight());
        //contentValue.put(DatabaseHelper.fmnr_tree_rcd, g.getrcd());
        contentValue.put(DatabaseHelper.fmnr_tree_dbh, g.getdbh());
        contentValue.put(DatabaseHelper.fmnr_tree_latitude, g.getLatitude());
        contentValue.put(DatabaseHelper.fmnr_tree_longitude, g.getLongitude());
        contentValue.put(DatabaseHelper.fmnr_tree_altitude, g.getAltitude());
        contentValue.put(DatabaseHelper.fmnr_tree_accuracy, g.getAccuracy());
        contentValue.put(DatabaseHelper.fmnr_tree_image_path, g.getpath());
        //insert
        database.insert(TABLE_FMNR_SPECIES, null, contentValue);
    }
    //delete
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
    public void deletenurseryinfo(){
        database.execSQL("delete from "+ TABLE_NURSERY);
    }
    public void deletenurseryspecies(){
        database.execSQL("delete from "+ TABLE_NURSERY_SPECIES);
    }

    public void deleteFmnrFarmer_Inst(){
        database.execSQL("delete from "+ TABLE_FMNR_FARMER_INST);
    }
    public void deleteFmnrSpecies(){
        database.execSQL("delete from "+ TABLE_FMNR_SPECIES);
    }
    public void deleteLandsizePolygon(){
        database.execSQL("delete from "+ TABLE_LANDSIZEPOLYGON);
    }
    //get all records from db for uploading
    public Cursor getTP() {
        String selectQuery = "SELECT * FROM farmer_institution,cohort,tree_measurements,landsizepolygon WHERE farmer_institution.farmerID = cohort.farmerID and cohort.cohortID = tree_measurements.cohortID and farmer_institution.farmerID=landsizepolygon.farmerID and farmer_institution.uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getTrainings() {
        String selectQuery = "SELECT * FROM trainings where uploaded='no'";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    //count no. of records tree planting
    public int getcount(){
        Cursor cur = database.rawQuery("SELECT count(*) from farmer_institution,cohort,tree_measurements WHERE farmer_institution.farmerID = cohort.farmerID and cohort.cohortID = tree_measurements.cohortID and farmer_institution.uploaded='no' ", null);
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
        Cursor cur = database.rawQuery(" SELECT Count(*) FROM trainings where uploaded='no' ", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
    public Cursor getNursery() {
        String selectQuery = "SELECT * FROM nursery_info,nursery_species WHERE nursery_info.nurseryID = nursery_species.nurseryID and nursery_info.uploaded='no'";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }

    //count no. of records tree planting
    public int getnurserycount(){
        Cursor cur = database.rawQuery("SELECT count(*) from nursery_info,nursery_species WHERE nursery_info.nurseryID = nursery_species.nurseryID and nursery_info.uploaded='no' ", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
    //get fmnr data
    public Cursor getFMNR() {
       String selectQuery = "SELECT * FROM fmnr_farmer_inst,fmnr_species,landsizepolygon WHERE fmnr_farmer_inst.farmerID = fmnr_species.farmerID and fmnr_farmer_inst.farmerID=landsizepolygon.farmerID and fmnr_farmer_inst.uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    //count number of records in fmnr
    public int getfmnrcount(){
        Cursor cur = database.rawQuery("SELECT count(*) from fmnr_farmer_inst,fmnr_species WHERE fmnr_farmer_inst.farmerID = fmnr_species.farmerID and fmnr_farmer_inst.uploaded='no' ", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
    //get the list of farmer/institution with their farmer ids
    public List<String> getFI_names() {
        List<String> list = new ArrayList<>();
        String selectQuery = "SELECT farmerID,name FROM farmer_institution";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0) + cursor.getString(1));
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    //select all farmers/institutions from db
    public Cursor fetch_FInames() {
        String selectQuery = "SELECT * FROM farmer_institution where uploaded='no' ORDER BY _id desc  ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    //select all farmers/institutions from db
    public Cursor fetch_FInamesFMNR() {
        String selectQuery = "SELECT * FROM fmnr_farmer_inst where uploaded='no' ORDER BY _id desc  ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    //fetch data by search from editttext on tree planting
    public Cursor fetchdatabyfilterTP(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+TABLE_FARMER_INST;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = database.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+TABLE_FARMER_INST+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = database.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
    //fetch data by search from edittext on FMNR
    public Cursor fetchdatabyfilterFMNR(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+TABLE_FMNR_FARMER_INST;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = database.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+TABLE_FMNR_FARMER_INST+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = database.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
    //update uploaded status to yes once data is uploaded
    public void uploadStatusTP() {
        String updateQuery = "Update farmer_institution set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusFMNR() {
        String updateQuery = "Update fmnr_farmer_inst set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusNursery() {
        String updateQuery = "Update nursery_info set uploaded='yes' where nurseryID=nurseryID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusTraining() {
        String updateQuery = "Update trainings set uploaded='yes' where _id=_id";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    //select all farmers/institutions from db and view
    public Cursor fetchTP() {
        //String selectQuery = "Select * from cohort,farmer_institution,tree_measurements";
        String selectQuery = "SELECT * FROM farmer_institution,cohort,tree_measurements WHERE farmer_institution.farmerID = cohort.farmerID and cohort.cohortID = tree_measurements.cohortID and farmer_institution.uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    public Cursor fetchFMNR() {
        //String selectQuery = "Select * from farmer_data,tree_data WHERE farmer_data.farmerID = tree_data.farmerID";
        String selectQuery = "SELECT * FROM fmnr_farmer_inst,fmnr_species WHERE fmnr_farmer_inst.farmerID = fmnr_species.farmerID and fmnr_farmer_inst.uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    public Cursor fetchNursery() {
        String selectQuery = "SELECT * FROM nursery_info,nursery_species WHERE nursery_info.nurseryID = nursery_species.nurseryID and nursery_info.uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    public Cursor fetchTrainings() {
        String selectQuery = "SELECT * FROM trainings WHERE uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }

}

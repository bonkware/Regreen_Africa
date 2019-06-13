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
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_FMNR_PLOT_INFO;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_FMNR_SPECIES;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_LANDSIZEPOLYGONFMNR;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_LANDSIZEPOLYGONTP;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_Measurement;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_NURSERY;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_NURSERY_SPECIES;
import static com.icraf.gsl.regreeningafrica.DatabaseHelper.TABLE_PLOT_INFO;
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
        contentValue.put(DatabaseHelper.tp_module, g.getmodule());
        contentValue.put(DatabaseHelper.tp_uploaded, g.getuploaded());
        contentValue.put(DatabaseHelper.farmerID, g.getfid());
        //insert
        database.insert(TABLE_FARMER_INST, null, contentValue);
    }
    //insert farmer details
    public void insertPlotinfo() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.tp_crops, g.getcrops());
        contentValue.put(DatabaseHelper.tp_croplist, g.getcroplist());
        contentValue.put(DatabaseHelper.landsize_regreen, g.getlandsize());
        contentValue.put(DatabaseHelper.tp_units, g.getunits());
        contentValue.put(DatabaseHelper.tpplot_uploaded, g.getuploaded());
        contentValue.put(DatabaseHelper.farmerID, g.getfid());
        //insert
        database.insert(TABLE_PLOT_INFO, null, contentValue);
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
        contentValue.put(DatabaseHelper.use_medicinal, g.getusage6());
        contentValue.put(DatabaseHelper.use_other, g.getus_other());
        contentValue.put(DatabaseHelper.tp_cohort_uploaded, g.getuploaded());
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
        contentValue.put(DatabaseHelper.tp_notes, g.getnotes());
        contentValue.put(DatabaseHelper.tp_measurement_uploaded, g.getuploaded());
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
        contentValue.put(DatabaseHelper.training_type, g.gettraining_type());
        contentValue.put(DatabaseHelper.training_date, g.gettraining_date());
        contentValue.put(DatabaseHelper.training_venue, g.gettraining_venue());
        contentValue.put(DatabaseHelper.training_partners, g.gettraining_partners());
        contentValue.put(DatabaseHelper.training_participants, g.getnumber_participants());
        contentValue.put(DatabaseHelper.male_participants, g.getmale_participants());
        contentValue.put(DatabaseHelper.female_participants, g.getfemale_participants());
        contentValue.put(DatabaseHelper.youth_participants, g.getyouth_participants());
        contentValue.put(DatabaseHelper.uploaded, g.getuploaded());
        contentValue.put(DatabaseHelper.notes, g.getnotes());
        contentValue.put(DatabaseHelper.training_module, g.getmodule());
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
        contentValue.put(DatabaseHelper.nursery_name, g.getnursery_name());
        contentValue.put(DatabaseHelper.species_number, g.getnspecies_number());
        contentValue.put(DatabaseHelper.n_date, g.getndate());

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
        contentValue.put(DatabaseHelper.nursery_module, g.getmodule());
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
        contentValue.put(DatabaseHelper.nursery_notes, g.getnotes());
        contentValue.put(DatabaseHelper.nursery_species_uploaded, g.getuploaded());
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
        contentValue.put(DatabaseHelper.fmnr_uploaded, g.getuploaded());
        contentValue.put(DatabaseHelper.fmnr_module, g.getmodule());
        //insert
        database.insert(TABLE_FMNR_FARMER_INST, null, contentValue);
    }
    public void insertFmnrPlotInfo() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fmnrplotfarmer_id, g.getfid());
        contentValue.put(DatabaseHelper.fmnr_species_number_start, g.getspecies_number());
        contentValue.put(DatabaseHelper.fmnr_started_date, g.getfmnr_date());
        contentValue.put(DatabaseHelper.fmnr_fenced, g.getfmnr_fenced());
        contentValue.put(DatabaseHelper.fmnr_crops, g.getcrops());
        contentValue.put(DatabaseHelper.fmnr_croplist, g.getcroplist());
        contentValue.put(DatabaseHelper.fmnr_landsize_regreen, g.getlandsize());
        contentValue.put(DatabaseHelper.fmnr_units, g.getunits());
        contentValue.put(DatabaseHelper.fmnr_plot_uploaded, g.getuploaded());
        //insert
        database.insert(TABLE_FMNR_PLOT_INFO, null, contentValue);
    }
    //insert polygon points for tree planting
    public void insertLandsizepolygontp() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fidtp, g.getfid());
        contentValue.put(DatabaseHelper.pidtp, g.getpid());
        contentValue.put(DatabaseHelper.landsize_polygon_latitudetp, g.getLatitude());
        contentValue.put(DatabaseHelper.landsize_polygon_longitudetp, g.getLongitude());
        contentValue.put(DatabaseHelper.landsize_polygon_altitudetp, g.getAltitude());
        contentValue.put(DatabaseHelper.landsize_polygon_accuracytp, g.getAccuracy());
        contentValue.put(DatabaseHelper.tp_polygon_uploaded, g.getuploaded());
        contentValue.put(DatabaseHelper.tpp_module, g.getmodule());
        //insert
        database.insert(TABLE_LANDSIZEPOLYGONTP, null, contentValue);
    }
    //insert polygon points for fmnr
    public void insertLandsizepolygonfmnr() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fidfmnr, g.getfid());
        contentValue.put(DatabaseHelper.pidfmnr, g.getpid());
        contentValue.put(DatabaseHelper.landsize_polygon_latitudefmnr, g.getLatitude());
        contentValue.put(DatabaseHelper.landsize_polygon_longitudefmnr, g.getLongitude());
        contentValue.put(DatabaseHelper.landsize_polygon_altitudefmnr, g.getAltitude());
        contentValue.put(DatabaseHelper.landsize_polygon_accuracyfmnr, g.getAccuracy());
        contentValue.put(DatabaseHelper.fmnr_polygon_uploaded, g.getuploaded());
        contentValue.put(DatabaseHelper.fmnrp_module, g.getmodule());
        //insert
        database.insert(TABLE_LANDSIZEPOLYGONFMNR, null, contentValue);
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
        contentValue.put(DatabaseHelper.fmnr_use_medicinal, g.getusage8());
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
        contentValue.put(DatabaseHelper.fmnr_notes, g.getnotes());
        contentValue.put(DatabaseHelper.fmnr_species_uploaded, g.getuploaded());
        //insert
        database.insert(TABLE_FMNR_SPECIES, null, contentValue);
    }
  //tree planting queries
    public Cursor getTPinfo() {
        String selectQuery = "SELECT * FROM farmer_institution WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getTPplotinfo() {
        String selectQuery = "SELECT * FROM plot_info WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getTPcohort() {
        String selectQuery = "SELECT * FROM cohort WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getTPmeasurements() {
        String selectQuery = "SELECT * FROM tree_measurements WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getTPpolygon() {
        String selectQuery = "SELECT * FROM landsizepolygontp WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    //count no. of records tree planting
    public int getcount(){
       /* Cursor cur = database.rawQuery("SELECT count(*) from farmer_institution,plot_info,cohort,tree_measurements " +
                "WHERE farmer_institution.farmerID=plot_info.farmerID and farmer_institution.farmerID=cohort.farmerID and cohort.cohortID=tree_measurements.cohortID " +
                "and farmer_institution.uploaded='no' and plot_info.uploaded='no' and cohort.uploaded='no' " +
                "and tree_measurements.uploaded='no' ", null);*/
        Cursor cur = database.rawQuery("SELECT count(*) from farmer_institution,plot_info where farmer_institution.farmerID=plot_info.farmerID and farmer_institution.uploaded='no'", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
    //fmnr queries
    public Cursor getFMNRinfo() {
        String selectQuery = "SELECT * FROM fmnr_farmer_inst WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getFMNRplotinfo() {
        String selectQuery = "SELECT * FROM fmnr_plot_info WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getFMNRspecies() {
        String selectQuery = "SELECT * FROM fmnr_species WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getFMNRpolygon() {
        String selectQuery = "SELECT * FROM landsizepolygonfmnr WHERE uploaded='no' ";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    //count number of records in fmnr
    public int getfmnrcount(){
        Cursor cur = database.rawQuery("SELECT count(*) from fmnr_farmer_inst,fmnr_plot_info where  fmnr_farmer_inst.farmerID=fmnr_plot_info.farmerID and fmnr_farmer_inst.uploaded='no'", null);
        //Cursor cur = database.rawQuery("SELECT count(*) from fmnr_farmer_inst,fmnr_plot_info,fmnr_species WHERE fmnr_farmer_inst.farmerID=fmnr_plot_info.farmerID and fmnr_farmer_inst.farmerID=fmnr_species.farmerID and fmnr_species.uploaded='no' and fmnr_plot_info.uploaded='no' and fmnr_farmer_inst.uploaded='no' ", null);
        int x = 0;
        if (cur.moveToFirst())
        {
            x = cur.getInt(0);
        }
        cur.close();
        return x;
    }
    //training queries
    public Cursor getTrainings() {
        String selectQuery = "SELECT * FROM trainings where uploaded='no'";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
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
   //nursery queries
    public Cursor getNurseryinfo() {
        String selectQuery = "SELECT * FROM nursery_info WHERE uploaded='no'";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getNurseryspecies(String nurseryID) {
        String selectQuery = "SELECT * FROM nursery_species WHERE nurseryID='"+nurseryID+"' and uploaded='no'";
        Cursor c = database.rawQuery(selectQuery, null);
        return c;
    }
    public int getnurserycount(){
        Cursor cur = database.rawQuery("SELECT count(*) from nursery_info,nursery_species WHERE nursery_info.nurseryID=nursery_species.nurseryID and nursery_info.uploaded='no' and nursery_species.uploaded='no' ", null);
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
    //select all farmers/institutions from db to be used on multi polygon
    public Cursor fetch_FInames() {
        String selectQuery = "SELECT * FROM farmer_institution where uploaded='no' ORDER BY _id desc  ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    //select all farmers/institutions from db for multi polygon use
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
    public void uploadStatusTPinfo() {
        String updateQuery = "Update farmer_institution set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusTPplotinfo() {
        String updateQuery = "Update plot_info set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusTPcohort() {
        String updateQuery = "Update cohort set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusTPmeasurement() {
        String updateQuery = "Update tree_measurements set uploaded='yes'";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusTPpolygon() {
        String updateQuery = "Update landsizepolygontp set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusFMNRinfo() {
        String updateQuery = "Update fmnr_farmer_inst set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusFMNRplotinfo() {
        String updateQuery = "Update fmnr_plot_info set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusFMNRspecies() {
        String updateQuery = "Update fmnr_species set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusFMNRpolygon() {
        String updateQuery = "Update landsizepolygonfmnr set uploaded='yes' where farmerID=farmerID";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }

    public void uploadStatusNurseryinfo(String nurseryID) {
        String updateQuery = "Update nursery_info set uploaded='yes' where nurseryID='"+nurseryID+"'";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusNurseryspecies(String nurseryID) {
        String updateQuery = "Update nursery_species set uploaded='yes' where nurseryID='"+nurseryID+"'";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    public void uploadStatusTraining() {
        String updateQuery = "Update trainings set uploaded='yes' where _id=_id";
        Cursor c = database.rawQuery(updateQuery, null);
        c.moveToFirst();
    }
    //select all farmers/institutions from db and view the saved data
    public Cursor fetchTP() {
        //String selectQuery = "Select * from cohort,farmer_institution,tree_measurements";
        String selectQuery = "SELECT * FROM farmer_institution,plot_info,cohort,tree_measurements WHERE farmer_institution.farmerID = plot_info.farmerID and farmer_institution.farmerID = cohort.farmerID and cohort.cohortID = tree_measurements.cohortID and farmer_institution.uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    public int updateTPinfo(long _id, String ename, String date, String survey,String name,String country, String county,String district,String own,String c_land,String g_land,String ms,String school,
                              String other_own){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.enum_name, ename);
        contentValue.put(DatabaseHelper.date, date);
        contentValue.put(DatabaseHelper.survey_name, survey);
        contentValue.put(DatabaseHelper.farmer_inst_name, name);
        contentValue.put(DatabaseHelper.country, country);
        contentValue.put(DatabaseHelper.county_region, county);
        contentValue.put(DatabaseHelper.district, district);
        contentValue.put(DatabaseHelper.land_individual, own);
        contentValue.put(DatabaseHelper.land_community, c_land);
        contentValue.put(DatabaseHelper.land_government, g_land);
        contentValue.put(DatabaseHelper.land_mosque_church, ms);
        contentValue.put(DatabaseHelper.land_schools, school);
        contentValue.put(DatabaseHelper.land_other, other_own);
        int nursery = database.update(TABLE_FARMER_INST, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public int updateTPplotinfo(long _id, String crops,String croplist, String landsize,String unit
    ){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.tp_crops, crops);
        contentValue.put(DatabaseHelper.tp_croplist, croplist);
        contentValue.put(DatabaseHelper.landsize_regreen, landsize);
        contentValue.put(DatabaseHelper.tp_units, unit);
        int nursery = database.update(TABLE_PLOT_INFO, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public int updateTPcohort(long _id, String species, String dplanted,String nplanted,String nsurvived,String woodlot,String iboundary,String eboundary,String garden,String field,String grassland,String bushland,String other_sites, String mg1,String mg2,String mg3,String mg4,String mg5,String mg_other,
                                 String usage1, String usage2,String usage3,String usage4,String usage5,String usage6,String us_other){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.species_name, species);
        contentValue.put(DatabaseHelper.date_planted, dplanted);
        contentValue.put(DatabaseHelper.number_planted, nplanted);
        contentValue.put(DatabaseHelper.number_survived, nsurvived);
        contentValue.put(DatabaseHelper.woodlot, woodlot);
        contentValue.put(DatabaseHelper.iboundary, iboundary);
        contentValue.put(DatabaseHelper.eboundary, eboundary);
        contentValue.put(DatabaseHelper.garden, garden);
        contentValue.put(DatabaseHelper.crop_field, field);
        contentValue.put(DatabaseHelper.pasture_grassland, grassland);
        contentValue.put(DatabaseHelper.fallow_bushland, bushland);
        contentValue.put(DatabaseHelper.other_sites, other_sites);
        contentValue.put(DatabaseHelper.management_pruning, mg1);
        contentValue.put(DatabaseHelper.management_fencing, mg2);
        contentValue.put(DatabaseHelper.management_weeding, mg3);
        contentValue.put(DatabaseHelper.management_watering, mg4);
        contentValue.put(DatabaseHelper.management_organic_fertilizer, mg5);
        contentValue.put(DatabaseHelper.management_other, mg_other);
        contentValue.put(DatabaseHelper.use_firewood, usage1);
        contentValue.put(DatabaseHelper.use_housing_construction, usage2);
        contentValue.put(DatabaseHelper.use_animal_feed, usage3);
        contentValue.put(DatabaseHelper.use_food, usage4);
        contentValue.put(DatabaseHelper.use_mulching, usage5);
        contentValue.put(DatabaseHelper.use_medicinal, usage6);
        contentValue.put(DatabaseHelper.use_other, us_other);
        int nursery = database.update(TABLE_COHORT, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public int updateTPmeasurements(long _id,String height,String dbh,String lat,String lon,String alt, String acc,
                                 String note){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.tree_height, height);
        contentValue.put(DatabaseHelper.tree_dbh, dbh);
        contentValue.put(DatabaseHelper.tree_latitude, lat);
        contentValue.put(DatabaseHelper.tree_longitude, lon);
        contentValue.put(DatabaseHelper.tree_altitude, alt);
        contentValue.put(DatabaseHelper.tree_accuracy, acc);
        contentValue.put(DatabaseHelper.tp_notes, note);
        int nursery = database.update(TABLE_FMNR_SPECIES, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public void delete_TPinfo(long _id) {
        database.delete(TABLE_FARMER_INST, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete_TPplotinfo(long _id) {
        database.delete(TABLE_PLOT_INFO, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete_TPcohort(long _id) {
        database.delete(TABLE_COHORT, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete_TPmeasurement(long _id) {
        database.delete(TABLE_Measurement, DatabaseHelper._ID + "=" + _id, null);
    }//end of delete & update TP

    public Cursor fetchFMNR() {
        //String selectQuery = "Select * from farmer_data,tree_data WHERE farmer_data.farmerID = tree_data.farmerID";
        String selectQuery = "SELECT * FROM fmnr_farmer_inst,fmnr_plot_info,fmnr_species WHERE fmnr_farmer_inst.farmerID = fmnr_species.farmerID and fmnr_farmer_inst.farmerID = fmnr_plot_info.farmerID and fmnr_farmer_inst.uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    //update the details in database
    public int updateFMNRinfo(long _id, String ename, String date, String survey,String name,String country, String county,String district,String own,String c_land,String g_land,String m_c,String schools,
                             String other_own){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fmnr_enum_name, ename);
        contentValue.put(DatabaseHelper.fmnr_date, date);
        contentValue.put(DatabaseHelper.famnr_survey_name, survey);
        contentValue.put(DatabaseHelper.fmnr_farmer_inst_name, name);
        contentValue.put(DatabaseHelper.fmnr_country, country);
        contentValue.put(DatabaseHelper.fmnr_county_region, county);
        contentValue.put(DatabaseHelper.fmnr_district, district);
        contentValue.put(DatabaseHelper.fmnr_land_individual, own);
        contentValue.put(DatabaseHelper.fmnr_land_community, c_land);
        contentValue.put(DatabaseHelper.fmnr_land_government, g_land);
        contentValue.put(DatabaseHelper.fmnr_land_mosque_church, m_c);
        contentValue.put(DatabaseHelper.fmnr_land_schools, schools);
        contentValue.put(DatabaseHelper.fmnr_land_other, other_own);
        int nursery = database.update(TABLE_FMNR_FARMER_INST, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public int updateFMNRplotinfo(long _id, String n_start, String s_date, String fence,String crop,String cropl, String landsize,String units
                              ){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fmnr_species_number_start, n_start);
        contentValue.put(DatabaseHelper.fmnr_started_date, s_date);
        contentValue.put(DatabaseHelper.fmnr_fenced, fence);
        contentValue.put(DatabaseHelper.fmnr_crops, crop);
        contentValue.put(DatabaseHelper.fmnr_croplist, cropl);
        contentValue.put(DatabaseHelper.fmnr_landsize_regreen, landsize);
        contentValue.put(DatabaseHelper.fmnr_units, units);
        int nursery = database.update(TABLE_FMNR_PLOT_INFO, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public int updateFMNRspecies(long _id, String species, String local, String mg1,String mg2,String mg3,String mg4,String mg5,String mg6,String mg7,String mg_other,
                                 String usage1, String usage2,String usage3,String usage4,String usage5,String usage6,String usage7,String usage8,String us_other,String stems, String height,String dbh,String lat,String lon,String alt, String acc,
                                 String note){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fmnr_species_name, species);
        contentValue.put(DatabaseHelper.fmnr_local_name, local);
        contentValue.put(DatabaseHelper.fmnr_management_pruning, mg1);
        contentValue.put(DatabaseHelper.fmnr_management_fencing, mg2);
        contentValue.put(DatabaseHelper.fmnr_management_weeding, mg3);
        contentValue.put(DatabaseHelper.fmnr_management_thinning, mg4);
        contentValue.put(DatabaseHelper.management_organic_fertilizer, mg5);
        contentValue.put(DatabaseHelper.fmnr_management_pollarding_lopping, mg6);
        contentValue.put(DatabaseHelper.fmnr_management_coppicing, mg7);
        contentValue.put(DatabaseHelper.fmnr_management_other, mg_other);
        contentValue.put(DatabaseHelper.fmnr_use_firewood, usage1);
        contentValue.put(DatabaseHelper.fmnr_use_housing_construction, usage2);
        contentValue.put(DatabaseHelper.fmnr_use_fodder, usage3);
        contentValue.put(DatabaseHelper.fmnr_use_fruits, usage4);
        contentValue.put(DatabaseHelper.fmnr_use_soil_fertility, usage5);
        contentValue.put(DatabaseHelper.fmnr_use_leafy_vegetables, usage6);
        contentValue.put(DatabaseHelper.fmnr_use_nuts, usage7);
        contentValue.put(DatabaseHelper.fmnr_use_medicinal, usage8);
        contentValue.put(DatabaseHelper.fmnr_use_other, us_other);
        contentValue.put(DatabaseHelper.fmnr_tree_stems, stems);
        contentValue.put(DatabaseHelper.fmnr_tree_height, height);
        contentValue.put(DatabaseHelper.fmnr_tree_dbh, dbh);
        contentValue.put(DatabaseHelper.fmnr_tree_latitude, lat);
        contentValue.put(DatabaseHelper.fmnr_tree_longitude, lon);
        contentValue.put(DatabaseHelper.fmnr_tree_altitude, alt);
        contentValue.put(DatabaseHelper.fmnr_tree_accuracy, acc);
        contentValue.put(DatabaseHelper.fmnr_notes, note);
        int nursery = database.update(TABLE_FMNR_SPECIES, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public void delete_FMNRinfo(long _id) {
        database.delete(TABLE_FMNR_FARMER_INST, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete_FMNRplotinfo(long _id) {
        database.delete(TABLE_FMNR_PLOT_INFO, DatabaseHelper._ID + "=" + _id, null);
    }
    public void delete_FMNRspecies(long _id) {
        database.delete(TABLE_FMNR_SPECIES, DatabaseHelper._ID + "=" + _id, null);
    }//end of delete & update FMNR
    public Cursor fetchNursery() {
        String selectQuery = "SELECT * FROM nursery_info,nursery_species WHERE nursery_info.nurseryID = nursery_species.nurseryID and nursery_info.uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    //update the details in database
    public int updateNursery(long _id, String ename, String nursery_date, String survey_name, String nursery_country, String nursery_county,String nursery_district,String nursery_operator,String nursery_contact,String nursery_name,String nursery_snumber,String nursery_sdate,
                              String govt,String cm,String sch,String wg,String youth_group,String pi,String cv,String ot,String n_latitude,String n_longitude,String n_altitude,String n_accuracy,String path){
        ContentValues contentValue = new ContentValues();
        //contentValue.put(DatabaseHelper.nurseryID, g.getnid());
        contentValue.put(DatabaseHelper.nursery_enum_name, ename);//added
        contentValue.put(DatabaseHelper.nursery_date, nursery_date);//added
        contentValue.put(DatabaseHelper.nursery_survey_name, survey_name);//added
        contentValue.put(DatabaseHelper.nursery_country, nursery_country);
        contentValue.put(DatabaseHelper.nursery_county, nursery_county);
        contentValue.put(DatabaseHelper.nursery_district, nursery_district);
        contentValue.put(DatabaseHelper.nursery_operator, nursery_operator);
        contentValue.put(DatabaseHelper.nursery_contact, nursery_contact);
        contentValue.put(DatabaseHelper.nursery_name, nursery_name);
        contentValue.put(DatabaseHelper.species_number, nursery_snumber);
        contentValue.put(DatabaseHelper.n_date, nursery_sdate);//
        contentValue.put(DatabaseHelper.type_government, govt);
        contentValue.put(DatabaseHelper.type_church_mosque, cm);
        contentValue.put(DatabaseHelper.type_schools, sch);
        contentValue.put(DatabaseHelper.type_women_groups, wg);
        contentValue.put(DatabaseHelper.type_youth_groups, youth_group);
        contentValue.put(DatabaseHelper.type_private_individual, pi);
        contentValue.put(DatabaseHelper.type_communal_village, cv);
        contentValue.put(DatabaseHelper.other_nursery_types, ot);
        contentValue.put(DatabaseHelper.nursery_latitude, n_latitude);
        contentValue.put(DatabaseHelper.nursery_longitude, n_longitude);
        contentValue.put(DatabaseHelper.nursery_altitude, n_altitude);
        contentValue.put(DatabaseHelper.nursery_accuracy, n_accuracy);
        contentValue.put(DatabaseHelper.nursery_image_path, path);
        int nursery = database.update(TABLE_NURSERY, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    public int updateNurseryspecies(long _id,String species,String local,String bare,String containerised,String other_method,String sd,String gf,String ct,
                             String mt,String ownfarm,String localdealer,String nationalseed,String ng,String other_source,String fm,String pt,String mb,String pr,String og,String purchased,String unit,String seedsown,String sownunits,String datesown,String germ,String surv,String sage,String sprice,String notes){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.nursery_species, species);
        contentValue.put(DatabaseHelper.nursery_local, local);
        contentValue.put(DatabaseHelper.method_bare_root, bare);
        contentValue.put(DatabaseHelper.method_containerised, containerised);
        contentValue.put(DatabaseHelper.other_methods, other_method);
        contentValue.put(DatabaseHelper.propagation_seed, sd);
        contentValue.put(DatabaseHelper.propagation_graft, gf);
        contentValue.put(DatabaseHelper.propagation_cutting, ct);
        contentValue.put(DatabaseHelper.propagation_marcotting, mt);
        contentValue.put(DatabaseHelper.seed_source_onfarm, ownfarm);
        contentValue.put(DatabaseHelper.seed_source_local_dealer, localdealer);
        contentValue.put(DatabaseHelper.seed_source_national_dealer, nationalseed);
        contentValue.put(DatabaseHelper.seed_source_NGOs, ng);
        contentValue.put(DatabaseHelper.other_seed_sources, other_source);
        contentValue.put(DatabaseHelper.graft_source_farmland, fm);
        contentValue.put(DatabaseHelper.graft_source_plantation, pt);
        contentValue.put(DatabaseHelper.graft_source_mother_blocks, mb);
        contentValue.put(DatabaseHelper.graft_source_prisons, pr);
        contentValue.put(DatabaseHelper.graft_source_others, og);
        contentValue.put(DatabaseHelper.seeds_quantity_purchased, purchased);
        contentValue.put(DatabaseHelper.qunits, unit);
        contentValue.put(DatabaseHelper.seed_sown, seedsown);
        contentValue.put(DatabaseHelper.unitsown, sownunits);
        contentValue.put(DatabaseHelper.date_seeds_sown, datesown);
        contentValue.put(DatabaseHelper.seedlings_germinated, germ);
        contentValue.put(DatabaseHelper.seedlings_servived, surv);
        contentValue.put(DatabaseHelper.seedlings_age, sage);
        contentValue.put(DatabaseHelper.seedlings_price, sprice);
        contentValue.put(DatabaseHelper.nursery_notes, notes);
        int nursery = database.update(TABLE_NURSERY_SPECIES, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return nursery;
    }
    //delete record
    public void delete_Nursery(long _id) {
        database.delete(TABLE_NURSERY, DatabaseHelper._ID + "=" + _id, null);
    }
    //delete record
    public void delete_Nurseryspecies(long _id) {
        database.delete(TABLE_NURSERY_SPECIES, DatabaseHelper._ID + "=" + _id, null);
    }//end of delete & update Nursery

    public Cursor fetchTrainings() {
        String selectQuery = "SELECT * FROM trainings WHERE uploaded='no' ";
        Cursor cursor = database.rawQuery(selectQuery, null);
        return cursor;
    }
    //update the details in database
    public int updateTraining(long _id, String enume, String in_date, String survey, String country, String county,String district,String topic,String type,
                                String tdate,String tvenue,String tpartners,String tparticipants,String male,String female,
                                String youth,String note){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.training_enum_name, enume);//added
        contentValue.put(DatabaseHelper.training_record_date, in_date);//added
        contentValue.put(DatabaseHelper.training_survey_name, survey);
        contentValue.put(DatabaseHelper.training_country, country);
        contentValue.put(DatabaseHelper.training_region, county);
        contentValue.put(DatabaseHelper.training_district, district);
        contentValue.put(DatabaseHelper.training_topic, topic);
        contentValue.put(DatabaseHelper.training_type, type);
        contentValue.put(DatabaseHelper.training_date, tdate);
        contentValue.put(DatabaseHelper.training_venue, tvenue);
        contentValue.put(DatabaseHelper.training_partners, tpartners);
        contentValue.put(DatabaseHelper.training_participants, tparticipants);
        contentValue.put(DatabaseHelper.male_participants, male);
        contentValue.put(DatabaseHelper.female_participants, female);
        contentValue.put(DatabaseHelper.youth_participants, youth);
        contentValue.put(DatabaseHelper.notes, note);
        int training = database.update(TABLE_Trainings, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return training;
    }
    //delete record Trainings
    public void delete_Training(long _id) {
        database.delete(TABLE_Trainings, DatabaseHelper._ID + "=" + _id, null);
    }

}

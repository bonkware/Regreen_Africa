package com.example.benard.regreeningafrica;

import android.graphics.Bitmap;

/**
 * Created by benard on 2/17/18.
 */

public class RegreeningGlobal {
    private static RegreeningGlobal instance;
    private double latitude=0.0, longitude=0.0, accuracy=0.0, altitude=0.0;
    private Boolean GPS_fix;
    private String districts,fname,county_region,country,select_location,select_site,enrich_planting,in_date,landsize,number_planted,number_survived,species_name,date_planted,select_measurement,less_than;
    private String mg1,mg2,mg3,mg4,mg5,mg_other,mg_others,usage1,usage2,usage3,usage4,usage5,us_other,usg_other;
    private String livestock1,livestock2,livestock3,livestock4,livestock5,livestock_other,c_name,cr_name,
            dcw_name,training_topic,training_partners,training_date,training_venue,number_participants,ename,male_participants,female_participants,youth_participants;
    private String dbh,height,rcd,path,fid,cid;
    //for nursery module
    private String nursery_country, nursery_county, nursery_village, nursery_operator, nursery_contact,
            select_radio, bare_root,container,other_method, other, own_farm, local_dealer, national_seed,
            other_source, other_s, nursery_species, nursery_local, seedlings_number, seedlings_age, hardening,
            select_graft,grafted_species_name, rootstock, own_farm1,local_dealer1, govt_seed, source,other_s1,observation,image,nid;
    private Bitmap photo;
    private Boolean save_add;

    //constructors
    public RegreeningGlobal(){

    }
    //Getter
    public String getdistricts() {
        return districts;
    }
    //Setting
    public void setdistricts(String districts) {
        this.districts = districts;
    }
    //Getter
    public String getfname() {
        return fname;
    }
    //Setting
    public void setfname(String fname) {
        this.fname = fname;
    }
    //Getter
    public String getcounty_region() {
        return county_region;
    }
    //Setting
    public void setcounty_region(String county_region) {
        this.county_region = county_region;
    }
    //Getter
    public String getcountry() {
        return country;
    }
    //Setting
    public void setcountry(String country) {
        this.country = country;
    }
    //Getter
    public String getselect_location() {
        return select_location;
    }
    //Setting
    public void setselect_location(String select_location) {
        this.select_location = select_location;
    }
    //Getter for select site
    public String getselect_site() {
        return select_site;
    }
    //Setting
    public void setselect_site(String select_site) {
        this.select_site = select_site;
    }
    //Getter
    public String getin_date() {
        return in_date;
    }
    //Setting
    public void setin_date(String in_date) {
        this.in_date = in_date;
    }
    //Getter est date
    public String getnumber_planted() {
        return number_planted;
    }
    //Setting
    public void setnumber_planted(String number_planted) {
        this.number_planted = number_planted;
    }
    //Getter est date
    public String getnumber_survived() {
        return number_survived;
    }
    //Setting
    public void setnumber_survived(String number_survived) {
        this.number_survived = number_survived;
    }
    //Getter area size estimate
    public String getlandsize() {
        return landsize;
    }
    //Setting
    public void setlandsize(String landsize) {
        this.landsize = landsize;
    }
    //Getter
    public String getspecies_name() {
        return species_name;
    }
    //Setting
    public void setspecies_name(String species_name) {
        this.species_name = species_name;
    }
    //Getter
    public String getdate_planted() {
        return date_planted;
    }
    //Setting
    public void setdate_planted(String date_planted) {
        this.date_planted = date_planted;
    }
    //Getter
    public String getselect_measurement() {
        return select_measurement;
    }
    //Setting
    public void setselect_measurement(String select_measurement) {
        this.select_measurement = select_measurement;
    }
    //Getter
    public String getless_than() {
        return less_than;
    }
    //Setting
    public void setless_than(String less_than) {
        this.less_than = less_than;
    }
    //Getting
    public String getdbh() {
        return dbh;
    }
    //Setting
    public void setdbh(String dbh) {
        this.dbh = dbh;
    }
    //Getting
    public String getheight() {
        return height;
    }
    //Setting
    public void setheight(String height) {
        this.height = height;
    }
    //Getting
    public String getrcd() {
        return rcd;
    }
    //Setting
    public void setrcd(String rcd) {
        this.rcd = rcd;
    }
    //Getting
    public String getpath() {
        return path;
    }
    //Setting
    public void setpath(String path) {
        this.path = path;
    }
    //Getting
    public Bitmap getphoto() {
        return photo;
    }
    //Setting
    public void setphoto(Bitmap photo) {
        this.photo = photo;
    }
    //Getting
    public String getmg1() {
        return mg1;
    }
    //Setting
    public void setmg1(String mg1) {
        this.mg1 = mg1;
    }
    //Getting
    public String getmg2() {
        return mg2;
    }
    //Setting
    public void setmg2(String mg2) {
        this.mg2 = mg2;
    }
    //Getting
    public String getmg3() {
        return mg3;
    }
    //Setting
    public void setmg3(String mg3) {
        this.mg3 = mg3;
    }
    //Getting
    public String getmg4() {
        return mg4;
    }
    //Setting
    public void setmg4(String mg4) {
        this.mg4 = mg4;
    }
    //Getting
    public String getmg5() {
        return mg5;
    }
    //Setting
    public void setmg5(String mg5) {
        this.mg5 = mg5;
    }
    //Getting
    public String getmg_others() {
        return mg_others;
    }
    //Setting
    public void setmg_others(String mg_others) {
        this.mg_others = mg_others;
    }
    //Getting
    public String getmg_other() {
        return mg_other;
    }
    //Setting
    public void setmg_other(String mg_other) {
        this.mg_other = mg_other;
    }
    //Getting
    public String getusage1() {
        return usage1;
    }
    //Setting
    public void setusage1(String usage1) {
        this.usage1 = usage1;
    }
    //Getting
    public String getusage2() {
        return usage2;
    }
    //Setting
    public void setusage2(String usage2) {
        this.usage2 = usage2;
    }
    //Getting
    public String getusage3() {
        return usage3;
    }
    //Setting
    public void setusage3(String usage3) {
        this.usage3 = usage3;
    }
    //Getting
    public String getusage4() {
        return usage4;
    }
    //Setting
    public void setusage4(String usage4) {
        this.usage4 = usage4;
    }
    //Getting
    public String getusage5() {
        return usage5;
    }
    //Setting
    public void setusage5(String usage5) {
        this.usage5 = usage5;
    }
    //Getting
    public String getus_other() {
        return us_other;
    }
    //Setting
    public void setus_other(String us_other) {
        this.us_other = us_other;
    }
    public String getusg_other() {
        return usg_other;
    }
    //Setting
    public void setusg_other(String usg_other) {
        this.usg_other = usg_other;
    }

    //Getting
    public String getlivestock1() {
        return livestock1;
    }
    //Setting
    public void setlivestock1(String livestock1) {
        this.livestock1 = livestock1;
    }
    //Getting
    public String getlivestock2() {
        return livestock2;
    }
    //Setting
    public void setlivestock2(String livestock2) {
        this.livestock2 = livestock2;
    }
    //Getting
    public String getlivestock3() {
        return livestock3;
    }
    //Setting
    public void setlivestock3(String livestock3) {
        this.livestock3 = livestock3;
    }
    //Getting
    public String getlivestock4() {
        return livestock4;
    }
    //Setting
    public void setlivestock4(String livestock4) {
        this.livestock4 = livestock4;
    }
    //Getting
    public String getlivestock5() {
        return livestock5;
    }
    //Setting
    public void setlivestock5(String livestock5) {
        this.livestock5 = livestock5;
    }
    //Getting
    public String getlivestock_other() {
        return livestock_other;
    }
    //Setting
    public void setlivestock_other(String livestock_other) {
        this.livestock_other = livestock_other;
    }

    //Getting
    public String getename() {
        return ename;
    }
    //Setting
    public void setename(String ename) {
        this.ename = ename;
    }

    //cohort id
    //Getting
    public String getcid() {
        return cid;
    }
    //Setting
    public void setcid(String cid) {
        this.cid = cid;
    }

    //farmer id
    //Getting
    public String getfid() {
        return fid;
    }
    //Setting
    public void setfid(String fid) {
        this.fid = fid;
    }

    //setter and getter for training module***************
    //Getting
    public String getc_name() {
        return c_name;
    }
    //Setting
    public void setc_name(String c_name) {
        this.c_name = c_name;
    }
    //Getting
    public String getcr_name() {
        return cr_name;
    }
    //Setting
    public void setcr_name(String cr_name) {
        this.cr_name = cr_name;
    }
    //Getting
    public String getdcw_name() {
        return dcw_name;
    }
    //Setting
    public void setdcw_name(String dcw_name) {
        this.dcw_name = dcw_name;
    }
    //Getting
    public String gettraining_topic() {
        return training_topic;
    }
    //Setting
    public void settraining_topic(String training_topic) {
        this.training_topic = training_topic;
    }
    //Getting
    public String gettraining_date() {
        return training_date;
    }
    //Setting
    public void settraining_date(String training_date) {
        this.training_date = training_date;
    }
    //Getting
    public String gettraining_venue() {
        return training_venue;
    }
    //Setting
    public void settraining_venue(String training_venue) {
        this.training_venue = training_venue;
    }
    //Getting
    public String gettraining_partners() {
        return training_partners;
    }
    //Setting
    public void settraining_partners(String training_partners) {
        this.training_partners = training_partners;
    }
    //Getting
    public String getnumber_participants() {
        return number_participants;
    }
    //Setting
    public void setnumber_participants(String number_participants) {
        this.number_participants = number_participants;
    }
    //Getting
    public String getmale_participants() {
        return male_participants;
    }
    //Setting
    public void setmale_participants(String male_participants) {
        this.male_participants = male_participants;
    }

    //Getting
    public String getfemale_participants() {
        return female_participants;
    }
    //Setting
    public void setfemale_participants(String female_participants) {
        this.female_participants = female_participants;
    }
    //Getting
    public String getyouth_participants() {
        return youth_participants;
    }
    //Setting
    public void setyouth_participants(String youth_participants) {
        this.youth_participants = youth_participants;
    }

    //********************************* for nursery module *****************************//
    //Getter and setter for all nursery assessment
    public String getnursery_country() {
        return nursery_country;
    }
    public void setnursery_country(String nursery_country) {
        this.nursery_country = nursery_country;
    }
    public String getnursery_county() {
        return nursery_county;
    }
    public void setnursery_county(String nursery_county) {
        this.nursery_county = nursery_county;
    }
    public String getnursery_village() {
        return nursery_village;
    }
    public void setnursery_village(String nursery_village) {
        this.nursery_village = nursery_village;
    }
    public String getnursery_operator() {
        return nursery_operator;
    }
    public void setnursery_operator(String nursery_operator) {
        this.nursery_operator = nursery_operator;
    }
    public String getnursery_contact() {
        return nursery_contact;
    }
    public void setnursery_contact(String nursery_contact) {
        this.nursery_contact = nursery_contact;
    }
    public String getselect_radio() {
        return select_radio;
    }
    public void setselect_radio(String select_radio) {
        this.select_radio = select_radio;
    }
    public String getbare_root() {
        return bare_root;
    }
    public void setbare_root(String bare_root) {
        this.bare_root = bare_root;
    }
    public String getcontainer() {
        return container;
    }
    public void setcontainer(String container) {
        this.container = container;
    }
    public String getother_method() {
        return other_method;
    }
    public void setother_method(String other_method) {
        this.other_method = other_method;
    }
    public String getother() {
        return other;
    }
    public void setother(String other) {
        this.other = other;
    }

    public String getown_farm() {
        return own_farm;
    }
    public void setown_farm(String own_farm) {
        this.own_farm = own_farm;
    }
    public String getlocal_dealer() {
        return local_dealer;
    }
    public void setlocal_dealer(String local_dealer) {
        this.local_dealer = local_dealer;
    }
    public String getnational_seed() {
        return national_seed;
    }
    public void setnational_seed(String national_seed) {
        this.national_seed = national_seed;
    }
    public String getother_source() {
        return other_source;
    }
    public void setother_source(String other_source) {
        this.other_source = other_source;
    }
    public String getother_s() {
        return other_s;
    }
    public void setother_s(String other_s) {
        this.other_s = other_s;
    }

    public String getnursery_species() {
        return nursery_species;
    }
    public void setnursery_species(String nursery_species) {
        this.nursery_species = nursery_species;
    }
    public String getnursery_local() {
        return nursery_local;
    }
    public void setnursery_local(String nursery_local) {
        this.nursery_local = nursery_local;
    }
    public String getseedlings_number() {
        return seedlings_number;
    }
    public void setseedlings_number(String seedlings_number) {
        this.seedlings_number = seedlings_number;
    }
    public String getseedlings_age() {
        return seedlings_age;
    }
    public void setseedlings_age(String seedlings_age) {
        this.seedlings_age = seedlings_age;
    }
    public String gethardening() {
        return hardening;
    }
    public void sethardening(String hardening) {
        this.hardening = hardening;
    }
    public String getselect_graft() {
        return select_graft;
    }
    public void setselect_graft(String select_graft) {
        this.select_graft = select_graft;
    }
    public String getgrafted_species_name() {
        return grafted_species_name;
    }
    public void setgrafted_species_name(String grafted_species_name) {
        this.grafted_species_name = grafted_species_name;
    }
    public String getrootstock() {
        return rootstock;
    }
    public void setrootstock(String rootstock) {
        this.rootstock = rootstock;
    }
    public String getown_farm1() {
        return own_farm1;
    }
    public void setown_farm1(String own_farm1) {
        this.own_farm1 = own_farm1;
    }
    public String getlocal_dealer1() {
        return local_dealer1;
    }
    public void setlocal_dealer1(String local_dealer1) {
        this.local_dealer1 = local_dealer1;
    }
    public String getgovt_seed() {
        return govt_seed;
    }
    public void setgovt_seed(String govt_seed) {
        this.govt_seed = govt_seed;
    }
    public String getsource() {
        return source;
    }
    public void setsource(String source) {
        this.source = source;
    }
    public String getother_s1() {
        return other_s1;
    }
    public void setother_s1(String other_s1) {
        this.other_s1 = other_s1;
    }
    public String getobservation() {
        return observation;
    }
    public void setobservation(String observation) {
        this.observation = observation;
    }
    public String getimage() {
        return image;
    }
    public void setimage(String image) {
        this.image = image;
    }
    public String getnid() {
        return nid;
    }
    public void setnid(String nid) {
        this.nid = nid;
    }


    //********************************* end nursery module *****************************//

    //Getting Lat/Long
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
    //Setting lat/lon
   /* public void setLatitude(Double lat) {
        this.latitude = lat;
    }
    public void setLongitude(Double lon) {
        this.longitude = lon;
    }
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }
    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }*/
    public  void setcurrentlat_long(Double latitude, Double longitude,Double altitude, float accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.accuracy = accuracy;
    }
    //Getting/setting key for GPS_button status
    public Boolean getGPS_fix(){
        return GPS_fix;
    }
    public  void setGPS_fix(Boolean gps_status){
        this.GPS_fix = gps_status;
    }


    public static synchronized RegreeningGlobal getInstance(){
        if(instance==null){
            instance=new RegreeningGlobal();
        }
        return instance;
    }


}
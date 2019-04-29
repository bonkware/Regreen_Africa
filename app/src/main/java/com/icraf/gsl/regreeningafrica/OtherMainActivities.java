package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 2/11/19.
 *
 */
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OtherMainActivities extends AppCompatActivity {
    Button TPButton,TrainingButton,NurseryButton,FMNRButton;
    DbAccess dbAccess;
    ProgressDialog progressDialog;
    //urls for data submission
    String treePlanting_url = "http://gsl.worldagroforestry.org/regreen_africa/insertTP.php";
    String trainings_url = "http://gsl.worldagroforestry.org/regreen_africa/insertTrainings.php";
    String nursery_url = "http://gsl.worldagroforestry.org/regreen_africa/insertNursery.php";
    String fmnr_url = "http://gsl.worldagroforestry.org/regreen_africa/insertFMNR.php";
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.other_main_activities);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = OtherMainActivities.this;

        //read and write permission
        getStoragepermission();

        //hide main menu action
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();


        TPButton = (Button) findViewById(R.id.tree_planting);
        TrainingButton = (Button) findViewById(R.id.trainings);
        NurseryButton = (Button) findViewById(R.id.nursery);
        FMNRButton = (Button) findViewById(R.id.fmnr);
        //for sending tree planting
        TPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int count = dbAccess.getcount();
                if(count==0) {
                    Toast.makeText(OtherMainActivities.this, "No data to send", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    uploadTP();
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
            }
        });
        //for sending training data
        TrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int countTrainings = dbAccess.getcount_trainings();
                if(countTrainings==0) {
                    Toast.makeText(OtherMainActivities.this, "No data to send", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    uploadTrainings();
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
            }
        });
        //for sending nursery data
        NurseryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int countNursery = dbAccess.getnurserycount();
                if(countNursery==0) {
                    Toast.makeText(OtherMainActivities.this, "No data to send", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    uploadNursery();
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
            }
        });
        //for sending FMNR data
        FMNRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the database is empty don't send
                int countNursery = dbAccess.getfmnrcount();
                if(countNursery==0) {
                    Toast.makeText(OtherMainActivities.this, "No data to send", Toast.LENGTH_SHORT).show();
                }else {//otherwise send
                    uploadFMNR();
                }

            }
        });
        dbAccess = new DbAccess(this);
        dbAccess.open();
        //get row count and display number of records in send button
        int count = dbAccess.getcount();
        String s = "Send" +"("+ count +")";
        int tcount = dbAccess.getcount_trainings();
        String sn = "Send" +"("+ tcount +")";
        int ncount = dbAccess.getnurserycount();
        String nursery = "Send" +"("+ ncount +")";
        int fmnrcount = dbAccess.getfmnrcount();
        String fmnr = "Send" +"("+ fmnrcount +")";
        //Toast.makeText(Index.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        TPButton = (Button) findViewById(R.id.tree_planting);
        TPButton.setText(String.valueOf(s));
        //for trainings
        TrainingButton = (Button) findViewById(R.id.trainings);
        TrainingButton.setText(String.valueOf(sn));
        //for nursery
        NurseryButton = (Button) findViewById(R.id.nursery);
        NurseryButton.setText(String.valueOf(nursery));
        //for fmnr
        FMNRButton = (Button) findViewById(R.id.fmnr);
        FMNRButton.setText(String.valueOf(fmnr));
    }
    // check external write permision in android 6+
    int MY_PERMISSIONS_STORAGE;
    public  void getStoragepermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_STORAGE);
    }

    //sending tree planting data from sqlite tables to mysql server
    public void uploadTP() {
        progressDialog = new ProgressDialog(OtherMainActivities.this);
        progressDialog.setMessage("Sending Tree planting data...");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //get data from sqlite in a loop
        try{//added try catch
            final Cursor cursor = dbAccess.getTP();//fetch all tree planting data
            if (cursor.moveToFirst()) {
                do {
                    //farmer/institution
                    final String farmerID = cursor.getString(cursor.getColumnIndex("farmerID"));
                    final String enum_name = cursor.getString(cursor.getColumnIndex("ename"));
                    final String date = cursor.getString(cursor.getColumnIndex("in_date"));
                    final String survey_name = cursor.getString(cursor.getColumnIndex("survey_name"));
                    final String farmer_inst_name = cursor.getString(cursor.getColumnIndex("name"));
                    final String country = cursor.getString(cursor.getColumnIndex("country"));
                    final String county_region = cursor.getString(cursor.getColumnIndex("county_region"));
                    final String district = cursor.getString(cursor.getColumnIndex("district"));
                    //final String planting_location = cursor.getString(cursor.getColumnIndex("planting_location"));
                    final String land_individual = cursor.getString(cursor.getColumnIndex("land_individual"));
                    final String land_community = cursor.getString(cursor.getColumnIndex("land_community"));
                    final String land_government = cursor.getString(cursor.getColumnIndex("land_government"));
                    final String land_mosque_church = cursor.getString(cursor.getColumnIndex("land_mosque_church"));
                    final String land_schools = cursor.getString(cursor.getColumnIndex("land_schools"));
                    final String land_other = cursor.getString(cursor.getColumnIndex("land_other"));

                    //final String planting_site = cursor.getString(cursor.getColumnIndex("planting_site"));
                    final String woodlot = cursor.getString(cursor.getColumnIndex("woodlot"));
                    final String iboundary = cursor.getString(cursor.getColumnIndex("iboundary"));
                    final String eboundary = cursor.getString(cursor.getColumnIndex("eboundary"));
                    final String garden = cursor.getString(cursor.getColumnIndex("garden"));
                    final String crop_field = cursor.getString(cursor.getColumnIndex("crop_field"));
                    final String pasture_grassland = cursor.getString(cursor.getColumnIndex("pasture_grassland"));
                    final String fallow_pushland = cursor.getString(cursor.getColumnIndex("fallow_bushland"));
                    final String other_sites = cursor.getString(cursor.getColumnIndex("other_sites"));

                    final String landsize_regreen = cursor.getString(cursor.getColumnIndex("landsize_regreen"));
                    final String tp_units = cursor.getString(cursor.getColumnIndex("units"));
                    //for cohort
                    final String cohort_id = cursor.getString(cursor.getColumnIndex("cohortID"));
                    final String farmer_id = cursor.getString(cursor.getColumnIndex("farmerID"));
                    final String species = cursor.getString(cursor.getColumnIndex("species"));
                    final String date_planted = cursor.getString(cursor.getColumnIndex("date_planted"));
                    final String number_planted = cursor.getString(cursor.getColumnIndex("number_planted"));
                    final String number_survived = cursor.getString(cursor.getColumnIndex("number_survived"));
                    final String management_pruning = cursor.getString(cursor.getColumnIndex("mg1"));
                    final String management_fencing = cursor.getString(cursor.getColumnIndex("mg2"));
                    final String management_weeding = cursor.getString(cursor.getColumnIndex("mg3"));
                    final String management_watering = cursor.getString(cursor.getColumnIndex("mg4"));
                    final String management_organic_fertilizer = cursor.getString(cursor.getColumnIndex("mg5"));
                    final String management_other = cursor.getString(cursor.getColumnIndex("mg_other"));
                    final String use_firewood = cursor.getString(cursor.getColumnIndex("usage1"));
                    final String use_housing_construction = cursor.getString(cursor.getColumnIndex("usage2"));
                    final String use_animal_feed = cursor.getString(cursor.getColumnIndex("usage3"));
                    final String use_food = cursor.getString(cursor.getColumnIndex("usage4"));
                    final String use_mulching = cursor.getString(cursor.getColumnIndex("usage5"));
                    final String use_other = cursor.getString(cursor.getColumnIndex("us_other"));
                    //for tree measurement
                    final String cohortID = cursor.getString(cursor.getColumnIndex("cohortID"));
                    final String tree_height = cursor.getString(cursor.getColumnIndex("height"));
                    //final String tree_rcd = cursor.getString(cursor.getColumnIndex("rcd"));
                    final String tree_dbh = cursor.getString(cursor.getColumnIndex("dbh"));
                    final String tree_latitude = cursor.getString(cursor.getColumnIndex("tree_latitude"));
                    final String tree_longitude = cursor.getString(cursor.getColumnIndex("tree_longitude"));
                    final String tree_altitude = cursor.getString(cursor.getColumnIndex("tree_altitude"));
                    final String tree_accuracy = cursor.getString(cursor.getColumnIndex("tree_accuracy"));
                    final String path = cursor.getString(cursor.getColumnIndex("path"));
                    //for tree planting plot polygon
                    final String farmerid = cursor.getString(cursor.getColumnIndex("farmerID"));
                    final String landsize_polygon_latitude = cursor.getString(cursor.getColumnIndex("latitude"));
                    final String landsize_polygon_longitude = cursor.getString(cursor.getColumnIndex("longitude"));
                    final String landsize_polygon_altitude = cursor.getString(cursor.getColumnIndex("altitude"));
                    final String landsize_polygon_accuracy = cursor.getString(cursor.getColumnIndex("accuracy"));

                    // create an object of volley request queue
                    RequestQueue queue = Volley.newRequestQueue(OtherMainActivities.this);
                    // Request a string response from the provided url above to server.
                    StringRequest request = new StringRequest(Request.Method.POST, treePlanting_url, new Response.Listener<String>() {
                        @Override
                        //successful response
                        public void onResponse(String response) {
                            //get the response if success get response that data has been received
                            Log.i("My success", "" + response);
                            //progressDialog.dismiss();
                            //notification
                            try{
                                //count records sent
                                final int count = dbAccess.getcount();
                                //Toast.makeText(Index.this, count + " record(s) " + response, Toast.LENGTH_LONG).show();
                                //refresh activity after dialog
                                final AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Data sent!").setMessage(count + " record(s) " + response)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //delete all records after send
                                                dbAccess.deleteFarmer_Inst();
                                                dbAccess.deleteCohort();
                                                dbAccess.deleteMeasurements();
                                                //dbAccess.deleteLandsizePolygon();
                                                //delete the images as well
                                                File fdelete = new File(path);
                                                if (fdelete.exists()) {
                                                    if (fdelete.delete()) {
                                                        System.out.println("file Deleted :" + path);
                                                    } else {
                                                        System.out.println("file not Deleted :" + path);
                                                    }
                                                }//end of image delete
                                                //dismiss dialog by intent
                                                Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(intent);
                                            }
                                        });
                                builder.create().show();
                                //progressDialog.dismiss();
                            }catch(Exception e){//added catch
                                Log.d("Upload failed", "Exception : "
                                        + e.getMessage(), e);
                            }

                        }
                        //get error response
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //error responses if failed to connect
                            if (error instanceof NetworkError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();
                            } else if (error instanceof ServerError) {
                                //Toast.makeText(Index.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Server not found!").setMessage("The server could not be found. Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof AuthFailureError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof ParseError) {
                                //Toast.makeText(Index.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Parsing Error!").setMessage("Parsing error! Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof TimeoutError) {
                                //Toast.makeText(Index.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Time Out!").setMessage("Connection TimeOut! Please check your internet connection").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            }
                            Log.i("My error", "" + error);
                            //dismiss dialog
                            progressDialog.dismiss();

                        }
                    }) {
                        @Override
                        //hashmap class in volley
                        //HashMap is a type of Collection that stores  data in a pair such that each element has a key associated with it.
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<String, String>();
                            //farmer/institution
                            param.put("farmerID", farmerID);
                            param.put("enum_name", enum_name);
                            param.put("date", date);
                            param.put("survey_name", survey_name);
                            param.put("farmer_inst_name", farmer_inst_name);
                            param.put("country", country);
                            param.put("county_region", county_region);
                            param.put("district", district);
                            //param.put("planting_location", planting_location);
                            param.put("land_individual", land_individual);
                            param.put("land_community", land_community);
                            param.put("land_government", land_government);
                            param.put("land_mosque_church", land_mosque_church);
                            param.put("land_schools", land_schools);
                            param.put("land_other", land_other);
                            //param.put("planting_site", planting_site);
                            param.put("woodlot", woodlot);
                            param.put("iboundary", iboundary);
                            param.put("eboundary", eboundary);
                            param.put("garden", garden);
                            param.put("crop_field", crop_field);
                            param.put("pasture_grassland", pasture_grassland);
                            param.put("fallow_bushland", fallow_pushland);
                            param.put("other_sites", other_sites);
                            param.put("landsize_regreen", landsize_regreen);
                            param.put("units", tp_units);
                            //cohort
                            param.put("cohortID", cohort_id);
                            param.put("farmerID", farmer_id);
                            param.put("species", species);
                            param.put("date_planted", date_planted);
                            param.put("number_planted", number_planted);
                            param.put("number_survived", number_survived);
                            param.put("management_pruning", management_pruning);
                            param.put("management_fencing", management_fencing);
                            param.put("management_weeding", management_weeding);
                            param.put("management_watering", management_watering);
                            param.put("management_organic_fertilizer", management_organic_fertilizer);
                            param.put("management_other", management_other);
                            param.put("use_firewood", use_firewood);
                            param.put("use_housing_construction", use_housing_construction);
                            param.put("use_animal_feed", use_animal_feed);
                            param.put("use_food", use_food);
                            param.put("use_mulching", use_mulching);
                            param.put("use_other", use_other);
                            //measurement
                            param.put("cohortID", cohortID);
                            param.put("tree_height", tree_height);
                            //param.put("tree_rcd", tree_rcd);
                            param.put("tree_dbh", tree_dbh);
                            param.put("tree_latitude", tree_latitude);
                            param.put("tree_longitude", tree_longitude);
                            param.put("tree_altitude", tree_altitude);
                            param.put("tree_accuracy", tree_accuracy);
                            //converting file path to bitmap
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 8;//compress file further to avoid out of memory error
                            Bitmap bitmap = BitmapFactory.decodeFile(path,options);
                            String image = getStringImage(bitmap);
                            param.put("path", image);
                            //landsize polygon
                            param.put("farmerID", farmerid);
                            param.put("latitude", landsize_polygon_latitude);
                            param.put("longitude", landsize_polygon_longitude);
                            param.put("altitude", landsize_polygon_altitude);
                            param.put("accuracy", landsize_polygon_accuracy);
                            //return param;
                            return checkParams(param);
                        }
                        //check empty for null values
                        private Map<String, String> checkParams(Map<String, String> map){
                            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                                if(pairs.getValue()==null){
                                    map.put(pairs.getKey(), "");
                                }
                            }
                            return map;
                        }
                    };

                    //add the request to the request queue
                    //retry policy to avoid crash
                    request.setRetryPolicy(new DefaultRetryPolicy( 0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    queue.add(request);
                    //VolleySingleton.getInstance(this).addToRequestQueue(request);
                    //add listener to the queue which is executed when the request ends
                    queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                        @Override
                        public void onRequestFinished(Request<String> request) {
                            if (progressDialog !=  null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });

                } while (cursor.moveToNext());
            }//end of cursor
        }catch(Exception e){//added catch
            Log.d("Upload failed", "Exception : "
                    + e.getMessage(), e);
        }
    }
    //sending nursery data from sqlite
    public void uploadNursery() {
        progressDialog = new ProgressDialog(OtherMainActivities.this);
        progressDialog.setMessage("Sending nursery data...");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //get data from sqlite in a loop
        try{//added try catch
            final Cursor cursor = dbAccess.getNursery();
            if (cursor.moveToFirst()) {
                do {
                    //nursery info
                    final String nurseryID = cursor.getString(cursor.getColumnIndex("nurseryID"));
                    final String enum_name = cursor.getString(cursor.getColumnIndex("ename"));
                    final String date = cursor.getString(cursor.getColumnIndex("in_date"));
                    final String survey_name = cursor.getString(cursor.getColumnIndex("survey_name"));
                    final String country = cursor.getString(cursor.getColumnIndex("country"));
                    final String county = cursor.getString(cursor.getColumnIndex("county"));
                    final String district = cursor.getString(cursor.getColumnIndex("district"));
                    final String operator = cursor.getString(cursor.getColumnIndex("operator"));
                    final String contact = cursor.getString(cursor.getColumnIndex("contact"));
                    final String type_government = cursor.getString(cursor.getColumnIndex("government"));
                    final String type_church_mosque = cursor.getString(cursor.getColumnIndex("church_mosque"));
                    final String type_schools = cursor.getString(cursor.getColumnIndex("schools"));
                    final String type_women_groups = cursor.getString(cursor.getColumnIndex("women_groups"));
                    final String type_youth_groups = cursor.getString(cursor.getColumnIndex("youth_groups"));
                    final String type_private_individual = cursor.getString(cursor.getColumnIndex("private_individual"));
                    final String type_communal_village = cursor.getString(cursor.getColumnIndex("communal_village"));
                    final String other_nursery_types = cursor.getString(cursor.getColumnIndex("other_types"));
                    final String latitude = cursor.getString(cursor.getColumnIndex("latitude"));
                    final String longitude = cursor.getString(cursor.getColumnIndex("longitude"));
                    final String altitude = cursor.getString(cursor.getColumnIndex("altitude"));
                    final String accuracy = cursor.getString(cursor.getColumnIndex("accuracy"));
                    final String path = cursor.getString(cursor.getColumnIndex("image"));
                    //nursery species
                    final String species = cursor.getString(cursor.getColumnIndex("species"));
                    final String local = cursor.getString(cursor.getColumnIndex("local"));
                    final String method_bare_root = cursor.getString(cursor.getColumnIndex("bare_root"));
                    final String method_containerised = cursor.getString(cursor.getColumnIndex("containerised"));
                    final String other_methods = cursor.getString(cursor.getColumnIndex("other_methods"));
                    final String propagation_seed = cursor.getString(cursor.getColumnIndex("seed"));
                    final String propagation_graft = cursor.getString(cursor.getColumnIndex("graft"));
                    final String propagation_cutting = cursor.getString(cursor.getColumnIndex("cutting"));
                    final String propagation_marcotting = cursor.getString(cursor.getColumnIndex("marcotting"));
                    final String seed_source_onfarm = cursor.getString(cursor.getColumnIndex("onfarm"));
                    final String seed_source_local_dealer = cursor.getString(cursor.getColumnIndex("local_dealer"));
                    final String seed_source_national_dealer = cursor.getString(cursor.getColumnIndex("national_dealer"));
                    final String seed_source_NGOs = cursor.getString(cursor.getColumnIndex("NGOs"));
                    final String other_seed_sources = cursor.getString(cursor.getColumnIndex("other_sources"));
                    final String graft_source_farmland = cursor.getString(cursor.getColumnIndex("farmland"));
                    final String graft_source_plantation = cursor.getString(cursor.getColumnIndex("plantation"));
                    final String graft_source_mother_blocks = cursor.getString(cursor.getColumnIndex("mother_blocks"));
                    final String graft_source_prisons = cursor.getString(cursor.getColumnIndex("prisons"));
                    final String graft_source_others = cursor.getString(cursor.getColumnIndex("other_graft_sources"));
                    final String seeds_quantity_purchased = cursor.getString(cursor.getColumnIndex("quantity_purchased"));
                    final String units = cursor.getString(cursor.getColumnIndex("units"));//
                    final String number_seed_sown = cursor.getString(cursor.getColumnIndex("seed_sown"));//
                    final String unitsown = cursor.getString(cursor.getColumnIndex("units_sown"));//
                    final String date_seeds_sown = cursor.getString(cursor.getColumnIndex("date_sown"));
                    final String seedlings_germinated = cursor.getString(cursor.getColumnIndex("seedlings_germinated"));
                    final String seedlings_servived = cursor.getString(cursor.getColumnIndex("seedlings_survived"));
                    final String seedlings_age = cursor.getString(cursor.getColumnIndex("seedlings_age"));
                    final String seedlings_price = cursor.getString(cursor.getColumnIndex("seedlings_price"));

                    // create an object of volley request queue
                    RequestQueue queue = Volley.newRequestQueue(OtherMainActivities.this);
                    // Request a string response from the provided url above to server.
                    StringRequest request = new StringRequest(Request.Method.POST, nursery_url, new Response.Listener<String>() {
                        @Override
                        //successful response
                        public void onResponse(String response) {
                            //get the response if success get response that data has been received
                            Log.i("My success", "" + response);
                            //progressDialog.dismiss();
                            //notification
                            try{
                                //count records sent
                                final int count = dbAccess.getnurserycount();
                                //Toast.makeText(Index.this, count + " record(s) " + response, Toast.LENGTH_LONG).show();
                                //refresh activity after dialog
                                final AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Data sent!").setMessage(count + " record(s) " + response)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //delete records after send
                                                dbAccess.deletenurseryinfo();
                                                dbAccess.deletenurseryspecies();
                                                //delete the images as well
                                                File fdelete = new File(path);
                                                if (fdelete.exists()) {
                                                    if (fdelete.delete()) {
                                                        System.out.println("file Deleted :" + path);
                                                    } else {
                                                        System.out.println("file not Deleted :" + path);
                                                    }
                                                }//end of image delete
                                                //dismiss dialog by intent
                                                Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(intent);
                                            }
                                        });
                                builder.create().show();
                                //progressDialog.dismiss();
                            }catch(Exception e){//added catch
                                Log.d("Upload failed", "Exception : "
                                        + e.getMessage(), e);
                            }

                        }
                        //get error response
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //error responses if failed to connect
                            if (error instanceof NetworkError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();
                            } else if (error instanceof ServerError) {
                                //Toast.makeText(Index.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Server not found!").setMessage("The server could not be found. Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof AuthFailureError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof ParseError) {
                                //Toast.makeText(Index.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Parsing Error!").setMessage("Parsing error! Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof TimeoutError) {
                                //Toast.makeText(Index.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Time Out!").setMessage("Connection TimeOut! Please check your internet connection").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            }
                            Log.i("My error", "" + error);
                            //dismiss dialog
                            progressDialog.dismiss();

                        }
                    }) {
                        @Override
                        //hashmap
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<String, String>();
                            //farmer data
                            param.put("nurseryID", nurseryID);
                            param.put("enum_name", enum_name);
                            param.put("date", date);
                            param.put("survey_name", survey_name);
                            param.put("country", country);
                            param.put("county", county);
                            param.put("district", district);
                            param.put("operator", operator);
                            param.put("contact", contact);
                            param.put("government", type_government);
                            param.put("church_mosque", type_church_mosque);
                            param.put("schools", type_schools);
                            param.put("women_groups", type_women_groups);
                            param.put("youth_groups", type_youth_groups);
                            param.put("private_individual", type_private_individual);
                            param.put("communal_village", type_communal_village);
                            param.put("other_types", other_nursery_types);
                            //param.put("youth_groups", type_youth_groups);
                            param.put("latitude", latitude);
                            param.put("longitude", longitude);
                            param.put("altitude", altitude);
                            param.put("accuracy", accuracy);
                            //converting file path to bitmap
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 8;//compress file further to avoid out of memory error
                            Bitmap bitmap = BitmapFactory.decodeFile(path,options);
                            String image = getStringImage(bitmap);
                            param.put("image", image);
                            //species data
                            param.put("nurseryID", nurseryID);
                            param.put("species", species);
                            param.put("local", local);
                            param.put("bare_root", method_bare_root);
                            param.put("container", method_containerised);
                            param.put("other_methods", other_methods);
                            param.put("seed", propagation_seed);
                            param.put("graft", propagation_graft);
                            param.put("cutting", propagation_cutting);
                            param.put("marcotting", propagation_marcotting);
                            param.put("onfarm", seed_source_onfarm);
                            param.put("local_dealer", seed_source_local_dealer);
                            param.put("national_dealer", seed_source_national_dealer);
                            param.put("NGOs", seed_source_NGOs);
                            param.put("other_sources", other_seed_sources);
                            param.put("farmland", graft_source_farmland);
                            param.put("plantation", graft_source_plantation);
                            param.put("mother_blocks", graft_source_mother_blocks);
                            param.put("prisons", graft_source_prisons);
                            param.put("other_graft_sources", graft_source_others);
                            param.put("quantity_purchased", seeds_quantity_purchased);
                            param.put("units", units);
                            param.put("seed_sown", number_seed_sown);
                            param.put("units_sown", unitsown);
                            param.put("date_sown", date_seeds_sown);
                            param.put("seedlings_germinated", seedlings_germinated);
                            param.put("seedlings_survived", seedlings_servived);
                            param.put("seedlings_age", seedlings_age);
                            param.put("seedlings_price", seedlings_price);

                            //return param;
                            return checkParams(param);
                        }
                        //check empty for null values
                        private Map<String, String> checkParams(Map<String, String> map){
                            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                                if(pairs.getValue()==null){
                                    map.put(pairs.getKey(), "");
                                }
                            }
                            return map;
                        }
                    };

                    //add the request to the request queue
                    //retry policy to avoid crash
                    request.setRetryPolicy(new DefaultRetryPolicy( 0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    queue.add(request);
                    //VolleySingleton.getInstance(this).addToRequestQueue(request);
                    //add listener to the queue which is executed when the request ends
                    queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                        @Override
                        public void onRequestFinished(Request<String> request) {
                            if (progressDialog !=  null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });

                } while (cursor.moveToNext());
            }//end of cursor
        }catch(Exception e){//added catch
            Log.d("Upload failed", "Exception : "
                    + e.getMessage(), e);
        }
    }
    //sending fmnr data from db
    public void uploadFMNR() {
        progressDialog = new ProgressDialog(OtherMainActivities.this);
        progressDialog.setMessage("Sending FMNR data...");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //get data from sqlite in a loop
        try{//added try catch
            final Cursor cursor = dbAccess.getFMNR();//fetch all fmnr data
            if (cursor.moveToFirst()) {
                do {
                    //farmer/institution
                    final String fmnr_farmer_id = cursor.getString(cursor.getColumnIndex("farmerID"));
                    final String fmnr_enum_name = cursor.getString(cursor.getColumnIndex("ename"));
                    final String fmnr_date = cursor.getString(cursor.getColumnIndex("in_date"));
                    final String fmnr_survey_name = cursor.getString(cursor.getColumnIndex("survey_name"));//
                    final String fmnr_farmer_inst_name = cursor.getString(cursor.getColumnIndex("name"));
                    final String fmnr_country = cursor.getString(cursor.getColumnIndex("country"));
                    final String fmnr_county_region = cursor.getString(cursor.getColumnIndex("county_region"));
                    final String fmnr_district = cursor.getString(cursor.getColumnIndex("district"));
                    //final String fmnr_planting_location = cursor.getString(cursor.getColumnIndex("planting_location"));
                    final String fmnr_land_individual = cursor.getString(cursor.getColumnIndex("fmnr_land_individual"));
                    final String fmnr_land_community = cursor.getString(cursor.getColumnIndex("fmnr_land_community"));
                    final String fmnr_land_government = cursor.getString(cursor.getColumnIndex("fmnr_land_government"));
                    final String fmnr_land_mosque_church = cursor.getString(cursor.getColumnIndex("fmnr_land_mosque_church"));
                    final String fmnr_land_schools = cursor.getString(cursor.getColumnIndex("fmnr_land_schools"));
                    final String fmnr_land_other = cursor.getString(cursor.getColumnIndex("fmnr_land_other"));

                    final String fmnr_species_number_start = cursor.getString(cursor.getColumnIndex("fmnr_species_number_start"));
                    //final String fmnr_restoration_photo = cursor.getString(cursor.getColumnIndex("fmnr_restoration_photo"));
                    final String fmnr_started_date = cursor.getString(cursor.getColumnIndex("fmnr_started_date"));
                    final String fmnr_fenced = cursor.getString(cursor.getColumnIndex("fmnr_fenced"));
                    final String fmnr_landsize_regreen = cursor.getString(cursor.getColumnIndex("landsize_regreen"));
                    final String fmnr_units = cursor.getString(cursor.getColumnIndex("units"));//

                    //fmnr species
                    final String fmnrfarmer_id = cursor.getString(cursor.getColumnIndex("farmerID"));
                    final String fmnr_species_name = cursor.getString(cursor.getColumnIndex("species"));
                    final String fmnr_local_name = cursor.getString(cursor.getColumnIndex("local_name"));
                    final String fmnr_management_pruning = cursor.getString(cursor.getColumnIndex("mg1"));
                    final String fmnr_management_fencing = cursor.getString(cursor.getColumnIndex("mg2"));
                    final String fmnr_management_weeding = cursor.getString(cursor.getColumnIndex("mg3"));
                    final String fmnr_management_thinning = cursor.getString(cursor.getColumnIndex("mg4"));
                    final String fmnr_management_organic_fertilizer = cursor.getString(cursor.getColumnIndex("mg5"));
                    final String fmnr_management_pollarding_lopping = cursor.getString(cursor.getColumnIndex("mg6"));
                    final String fmnr_management_other = cursor.getString(cursor.getColumnIndex("mg_other"));
                    final String fmnr_use_firewood = cursor.getString(cursor.getColumnIndex("usage1"));
                    final String fmnr_use_housing_construction = cursor.getString(cursor.getColumnIndex("usage2"));
                    final String fmnr_use_fodder = cursor.getString(cursor.getColumnIndex("usage3"));
                    final String fmnr_use_fruits = cursor.getString(cursor.getColumnIndex("usage4"));
                    final String fmnr_use_soil_fertility = cursor.getString(cursor.getColumnIndex("usage5"));
                    final String fmnr_use_leafy_vegetables = cursor.getString(cursor.getColumnIndex("usage6"));
                    final String fmnr_use_nuts = cursor.getString(cursor.getColumnIndex("usage7"));
                    final String fmnr_use_other = cursor.getString(cursor.getColumnIndex("us_other"));
                    final String fmnr_tree_stems = cursor.getString(cursor.getColumnIndex("stems"));
                    final String fmnr_tree_height = cursor.getString(cursor.getColumnIndex("height"));
                    //final String fmnr_tree_rcd = cursor.getString(cursor.getColumnIndex("rcd"));
                    final String fmnr_tree_dbh = cursor.getString(cursor.getColumnIndex("dbh"));
                    final String fmnr_tree_latitude = cursor.getString(cursor.getColumnIndex("tree_latitude"));
                    final String fmnr_tree_longitude = cursor.getString(cursor.getColumnIndex("tree_longitude"));
                    final String fmnr_tree_altitude = cursor.getString(cursor.getColumnIndex("tree_altitude"));
                    final String fmnr_tree_accuracy = cursor.getString(cursor.getColumnIndex("tree_accuracy"));
                    final String fmnr_tree_image_path = cursor.getString(cursor.getColumnIndex("path"));

                    //for fmnr plot polygon
                    final String fid = cursor.getString(cursor.getColumnIndex("farmerID"));
                    final String landsize_polygon_latitude = cursor.getString(cursor.getColumnIndex("latitude"));
                    final String landsize_polygon_longitude = cursor.getString(cursor.getColumnIndex("longitude"));
                    final String landsize_polygon_altitude = cursor.getString(cursor.getColumnIndex("altitude"));
                    final String landsize_polygon_accuracy = cursor.getString(cursor.getColumnIndex("accuracy"));

                    // create an object of volley request queue
                    RequestQueue queue = Volley.newRequestQueue(OtherMainActivities.this);
                    // Request a string response from the provided url above to server.
                    StringRequest request = new StringRequest(Request.Method.POST, fmnr_url, new Response.Listener<String>() {
                        @Override
                        //successful response
                        public void onResponse(String response) {
                            //get the response if success get response that data has been received
                            Log.i("My success", "" + response);
                            //progressDialog.dismiss();
                            //notification
                            try{
                                //count records sent
                                final int count = dbAccess.getfmnrcount();
                                //Toast.makeText(Index.this, count + " record(s) " + response, Toast.LENGTH_LONG).show();
                                //refresh activity after dialog
                                final AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Data sent!").setMessage(count + " record(s) " + response)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //delete all records after send
                                                dbAccess.deleteFmnrFarmer_Inst();
                                                dbAccess.deleteFmnrSpecies();
                                                //dbAccess.deleteLandsizePolygon();
                                                //delete the images as well
                                                /*File rdelete = new File(fmnr_restoration_photo);
                                                if (rdelete.exists()) {
                                                    if (rdelete.delete()) {
                                                        System.out.println("file Deleted :" + fmnr_restoration_photo);
                                                    } else {
                                                        System.out.println("file not Deleted :" + fmnr_restoration_photo);
                                                    }
                                                }*///end of restoration image delete
                                                File fdelete = new File(fmnr_tree_image_path);
                                                if (fdelete.exists()) {
                                                    if (fdelete.delete()) {
                                                        System.out.println("file Deleted :" + fmnr_tree_image_path);
                                                    } else {
                                                        System.out.println("file not Deleted :" + fmnr_tree_image_path);
                                                    }
                                                }//end of tree image delete
                                                //dismiss dialog by intent
                                                Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(intent);
                                            }
                                        });
                                builder.create().show();
                                //progressDialog.dismiss();
                            }catch(Exception e){//added catch
                                Log.d("Upload failed", "Exception : "
                                        + e.getMessage(), e);
                            }

                        }
                        //get error response
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //error responses if failed to connect
                            if (error instanceof NetworkError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();
                            } else if (error instanceof ServerError) {
                                //Toast.makeText(Index.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Server not found!").setMessage("The server could not be found. Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof AuthFailureError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof ParseError) {
                                //Toast.makeText(Index.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Parsing Error!").setMessage("Parsing error! Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof TimeoutError) {
                                //Toast.makeText(Index.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Time Out!").setMessage("Connection TimeOut! Please check your internet connection").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            }
                            Log.i("My error", "" + error);
                            //dismiss dialog
                            progressDialog.dismiss();

                        }
                    }) {
                        @Override
                        //hashmap class in volley
                        //HashMap is a type of Collection that stores  data in a pair such that each element has a key associated with it.
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<String, String>();
                            //farmer/institution
                            param.put("farmerID", fmnr_farmer_id);
                            param.put("enum_name", fmnr_enum_name);
                            param.put("date", fmnr_date);
                            param.put("survey_name", fmnr_survey_name);
                            param.put("farmer_inst_name", fmnr_farmer_inst_name);
                            param.put("country", fmnr_country);
                            param.put("county_region", fmnr_county_region);
                            param.put("district", fmnr_district);
                            //param.put("planting_location", fmnr_planting_location);
                            param.put("fmnr_land_individual", fmnr_land_individual);
                            param.put("fmnr_land_community", fmnr_land_community);
                            param.put("fmnr_land_government", fmnr_land_government);
                            param.put("fmnr_land_mosque_church", fmnr_land_mosque_church);
                            param.put("fmnr_land_schools", fmnr_land_schools);
                            param.put("fmnr_land_other", fmnr_land_other);
                            param.put("fmnr_species_number_start", fmnr_species_number_start);
                            //param.put("fmnr_restoration_photo", fmnr_restoration_photo);
                            param.put("fmnr_started_date", fmnr_started_date);
                            param.put("fmnr_fenced", fmnr_fenced);
                            param.put("landsize_regreen", fmnr_landsize_regreen);
                            param.put("units", fmnr_units);
                           /* BitmapFactory.Options option = new BitmapFactory.Options();
                            option.inSampleSize = 8;//compress file further to avoid out of memory error
                            Bitmap bitmap_rest = BitmapFactory.decodeFile(fmnr_restoration_photo,option);
                            String image_rest = getStringImage(bitmap_rest);
                            param.put("fmnr_restoration_photo", image_rest);*/
                            //species
                            param.put("farmerID", fmnrfarmer_id);
                            param.put("species", fmnr_species_name);
                            param.put("local", fmnr_local_name);
                            param.put("management_pruning", fmnr_management_pruning);
                            param.put("management_fencing", fmnr_management_fencing);
                            param.put("management_weeding", fmnr_management_weeding);
                            param.put("management_thinning", fmnr_management_thinning);
                            param.put("management_organic_fertilizer", fmnr_management_organic_fertilizer);
                            param.put("management_pollarding_lopping", fmnr_management_pollarding_lopping);
                            param.put("management_other", fmnr_management_other);
                            param.put("use_firewood", fmnr_use_firewood);
                            param.put("use_housing_construction", fmnr_use_housing_construction);
                            param.put("use_fodder", fmnr_use_fodder);
                            param.put("use_fruits", fmnr_use_fruits);
                            param.put("use_soil_fertility", fmnr_use_soil_fertility);
                            param.put("use_leafy_vegetables", fmnr_use_leafy_vegetables);
                            param.put("use_nuts", fmnr_use_nuts);
                            param.put("use_other", fmnr_use_other);
                            param.put("stems", fmnr_tree_stems);
                            param.put("tree_height", fmnr_tree_height);
                            //param.put("tree_rcd", fmnr_tree_rcd);
                            param.put("tree_dbh", fmnr_tree_dbh);
                            param.put("tree_latitude", fmnr_tree_latitude);
                            param.put("tree_longitude", fmnr_tree_longitude);
                            param.put("tree_altitude", fmnr_tree_altitude);
                            param.put("tree_accuracy", fmnr_tree_accuracy);
                            //converting file path to bitmap

                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 8;//compress file further to avoid out of memory error
                            Bitmap bitmap = BitmapFactory.decodeFile(fmnr_tree_image_path,options);
                            String image = getStringImage(bitmap);
                            param.put("path", image);
                            //param.put("path", path);
                            //landsize polygon
                            param.put("farmerID", fid);
                            param.put("latitude", landsize_polygon_latitude);
                            param.put("longitude", landsize_polygon_longitude);
                            param.put("altitude", landsize_polygon_altitude);
                            param.put("accuracy", landsize_polygon_accuracy);
                            //return param;
                            return checkParams(param);
                        }
                        //check empty for null values
                        private Map<String, String> checkParams(Map<String, String> map){
                            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                                if(pairs.getValue()==null){
                                    map.put(pairs.getKey(), "");
                                }
                            }
                            return map;
                        }
                    };
                    //add the request to the request queue
                    //retry policy to avoid crash
                    request.setRetryPolicy(new DefaultRetryPolicy( 0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    //queue.add(request);
                    Singleton.getInstance(mContext).addToRequestQueue(request);
                    //VolleySingleton.getInstance(this).addToRequestQueue(request);
                    //manage out of memory issues and cache clear
                   // RequestQueue volleyQueue = Volley.newRequestQueue(this);
                    DiskBasedCache cache = new DiskBasedCache(getCacheDir(), 16 * 1024 * 1024);
                    queue = new RequestQueue(cache, new BasicNetwork(new HurlStack()));
                    queue.start();
                    //queue.add(new ClearCacheRequest(cache, null));//end of cache clear
                    //VolleySingleton.getInstance(this).addToRequestQueue(request);
                    //add listener to the queue which is executed when the request ends
                    queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                        @Override
                        public void onRequestFinished(Request<String> request) {
                            if (progressDialog !=  null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });

                } while (cursor.moveToNext());
            }//end of cursor
        }catch(Exception e){//added catch
            Log.d("Upload failed", "Exception : "
                    + e.getMessage(), e);
        }
    }
    //convert bitmap to string
    public String getStringImage(Bitmap bitmap){
        try{
            ByteArrayOutputStream img=new  ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,50, img);
            byte [] b=img.toByteArray();
            String temp=Base64.encodeToString(b, Base64.DEFAULT);
            return temp;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    //upload trainings data
    public void uploadTrainings() {
        progressDialog = new ProgressDialog(OtherMainActivities.this);
        progressDialog.setMessage("Sending Trainings data...");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //get data from sqlite in a loop
        try{//added try catch
            final Cursor cursor = dbAccess.getTrainings();
            if (cursor.moveToFirst()) {
                do {
                    //
                    final String training_enum_name = cursor.getString(cursor.getColumnIndex("ename"));
                    final String training_record_date = cursor.getString(cursor.getColumnIndex("in_date"));
                    final String training_survey_name = cursor.getString(cursor.getColumnIndex("survey_name"));//
                    final String training_country = cursor.getString(cursor.getColumnIndex("training_country"));
                    final String training_region = cursor.getString(cursor.getColumnIndex("training_region"));
                    final String training_district = cursor.getString(cursor.getColumnIndex("training_district"));
                    final String training_topic = cursor.getString(cursor.getColumnIndex("training_topic"));
                    final String training_date = cursor.getString(cursor.getColumnIndex("training_date"));
                    final String training_venue = cursor.getString(cursor.getColumnIndex("training_venue"));
                    final String training_partners = cursor.getString(cursor.getColumnIndex("training_partners"));
                    final String training_participants = cursor.getString(cursor.getColumnIndex("total_participants"));
                    final String male_participants = cursor.getString(cursor.getColumnIndex("male_participants"));
                    final String female_participants = cursor.getString(cursor.getColumnIndex("female_participants"));
                    final String youth_participants = cursor.getString(cursor.getColumnIndex("youth_participants"));

                    // create an object of volley request queue
                    RequestQueue queue = Volley.newRequestQueue(OtherMainActivities.this);
                    // Request a string response from the provided url above to server.
                    StringRequest request = new StringRequest(Request.Method.POST, trainings_url, new Response.Listener<String>() {
                        @Override
                        //successful response
                        public void onResponse(String response) {
                            //get the response if success get response that data has been received
                            Log.i("My success", "" + response);
                            //progressDialog.dismiss();
                            //notification
                            try{
                                //count records sent
                                final int count = dbAccess.getcount_trainings();
                                //Toast.makeText(Index.this, count + " record(s) " + response, Toast.LENGTH_LONG).show();
                                //refresh activity after dialog
                                final AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Data sent!").setMessage(count + " record(s) " + response)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //delete records after send
                                                dbAccess.deleteTrainings();
                                                //dismiss dialog by intent
                                                Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(intent);
                                            }
                                        });
                                builder.create().show();
                                //progressDialog.dismiss();
                            }catch(Exception e){//added catch
                                Log.d("Upload failed", "Exception : "
                                        + e.getMessage(), e);
                            }

                        }
                        //get error response
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //error responses if failed to connect
                            if (error instanceof NetworkError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();
                            } else if (error instanceof ServerError) {
                                //Toast.makeText(Index.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Server not found!").setMessage("The server could not be found. Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof AuthFailureError) {
                                //Toast.makeText(Index.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("No Internet!").setMessage("Cannot connect to Internet...Please check your connection!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof ParseError) {
                                //Toast.makeText(Index.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Parsing Error!").setMessage("Parsing error! Please try again after some time!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            } else if (error instanceof TimeoutError) {
                                //Toast.makeText(Index.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(OtherMainActivities.this).setTitle("Time Out!").setMessage("Connection TimeOut! Please check your internet connection").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(OtherMainActivities.this, OtherMainActivities.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                });
                                builder.create().show();

                            }
                            Log.i("My error", "" + error);
                            //dismiss dialog
                            progressDialog.dismiss();

                        }
                    }) {
                        @Override
                        //hashmap
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<String, String>();
                            //farmer data
                            param.put("enum_name", training_enum_name);
                            param.put("date", training_record_date);
                            param.put("survey_name", training_survey_name);
                            param.put("training_country", training_country);
                            param.put("training_region", training_region);
                            param.put("training_district", training_district);
                            param.put("training_topic", training_topic);
                            param.put("training_date", training_date);
                            param.put("training_venue", training_venue);
                            param.put("training_partners", training_partners);
                            param.put("training_participants", training_participants);
                            param.put("male_participants", male_participants);
                            param.put("female_participants", female_participants);
                            param.put("youth_participants", youth_participants);
                            //return param;
                            return checkParams(param);
                        }
                        //check empty for null values
                        private Map<String, String> checkParams(Map<String, String> map){
                            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                                if(pairs.getValue()==null){
                                    map.put(pairs.getKey(), "");
                                }
                            }
                            return map;
                        }
                    };

                    //add the request to the request queue
                    //retry policy to avoid crash
                    request.setRetryPolicy(new DefaultRetryPolicy( 0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    queue.add(request);
                    //VolleySingleton.getInstance(this).addToRequestQueue(request);
                    //add listener to the queue which is executed when the request ends
                    queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                        @Override
                        public void onRequestFinished(Request<String> request) {
                            if (progressDialog !=  null && progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });

                } while (cursor.moveToNext());
            }//end of cursor
        }catch(Exception e){//added catch
            Log.d("Upload failed", "Exception : "
                    + e.getMessage(), e);
        }
    }

}

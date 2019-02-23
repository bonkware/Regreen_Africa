package com.example.benard.regreeningafrica;

/**
 * Created by benard on 2/11/19.
 */
import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.CheckBox;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OtherMainActivities extends AppCompatActivity {
    Button TPButton,TrainingButton,NurseryButton;
    DbAccess dbAccess;
    ProgressDialog progressDialog;
    //urls for data submission
    String treePlanting_url = "http://gsl.worldagroforestry.org/regreen_africa/insertTP.php";
    String trainings_url = "http://gsl.worldagroforestry.org/regreen_africa/insertTrainings.php";
    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.other_main_activities);
        //read and write permission
        getStoragepermission();

        //hide main menu action
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();


        TPButton = (Button) findViewById(R.id.tree_planting);
        TrainingButton = (Button) findViewById(R.id.trainings);
        NurseryButton = (Button) findViewById(R.id.nursery);
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
                   // uploadNursery();
                }
                //Toast.makeText(Index.this, "Data Send", Toast.LENGTH_SHORT).show();
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
        //Toast.makeText(Index.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        TPButton = (Button) findViewById(R.id.tree_planting);
        TPButton.setText(String.valueOf(s));
        //for trainings
        TrainingButton = (Button) findViewById(R.id.trainings);
        TrainingButton.setText(String.valueOf(sn));
        //for nursery
        NurseryButton = (Button) findViewById(R.id.nursery);
        NurseryButton.setText(String.valueOf(nursery));
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
        progressDialog.setMessage("Sending Tree plantning data...");
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
                    final String farmer_inst_name = cursor.getString(cursor.getColumnIndex("name"));
                    final String country = cursor.getString(cursor.getColumnIndex("country"));
                    final String county_region = cursor.getString(cursor.getColumnIndex("county_region"));
                    final String district = cursor.getString(cursor.getColumnIndex("district"));
                    final String planting_location = cursor.getString(cursor.getColumnIndex("planting_location"));
                    final String planting_site = cursor.getString(cursor.getColumnIndex("planting_site"));
                    final String landsize_regreen = cursor.getString(cursor.getColumnIndex("landsize_regreen"));
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
                    final String tree_rcd = cursor.getString(cursor.getColumnIndex("rcd"));
                    final String tree_dbh = cursor.getString(cursor.getColumnIndex("dbh"));
                    final String tree_latitude = cursor.getString(cursor.getColumnIndex("latitude"));
                    final String tree_longitude = cursor.getString(cursor.getColumnIndex("longitude"));
                    final String tree_altitude = cursor.getString(cursor.getColumnIndex("altitude"));
                    final String tree_accuracy = cursor.getString(cursor.getColumnIndex("accuracy"));
                    final String path = cursor.getString(cursor.getColumnIndex("path"));

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
                            param.put("farmer_inst_name", farmer_inst_name);
                            param.put("country", country);
                            param.put("county_region", county_region);
                            param.put("district", district);
                            param.put("planting_location", planting_location);
                            param.put("planting_site", planting_site);
                            param.put("landsize_regreen", landsize_regreen);
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
                            param.put("tree_rcd", tree_rcd);
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
                            //param.put("path", path);
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
                    //nursery profile
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

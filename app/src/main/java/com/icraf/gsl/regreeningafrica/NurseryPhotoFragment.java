package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.util.Random;


/**
 * Created by benard on 1/18/19.
 *
 */

public class NurseryPhotoFragment extends Fragment {
    private DbAccess dbAccess;
    public NurseryPhotoFragment() {

    }
    ImageView imgv1,imgv2;
    Bitmap bm =null;
    int REQUEST_CHECK = 0;
    String s;
    File imgpath;
    ImageButton upload;
    //private static final int PICK_IMAGE_REQUEST= 99;
    //Bitmap bitmap;
    RegreeningGlobal g = RegreeningGlobal.getInstance();
    //String myurl = "http://172.28.67.75/myimage/test.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nursery_photo_fragment, container,
                false);
        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();
        //allow camera permission and storage
        getCamerapermission();

        //set next button disabled
        final Button bnext = (Button) view.findViewById(R.id.tospecies);
        bnext.setEnabled(false);//disable button
        bnext.setAlpha(0.5f);

        imgv1 = (ImageView) view.findViewById(R.id.imageView1);
        upload = (ImageButton) view.findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //call photo on button click
                clickImage();
                bnext.setEnabled(true);//enable next button
                bnext.setBackgroundColor(Color.parseColor("#966648"));//change color of button

            }
        });

        //proceed to tree species list
        Button button_next = (Button) view.findViewById(R.id.tospecies);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tospecies:
                        savenurseryInfo();
                        dbAccess.insertNurseryInfo();//insert to db on click next
                        Intent intent = new Intent(getActivity(), NurseryOtherInfoMain.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        break;
                }
            }
        });

        return view;
    }
    //camera permissions check in android 6+
    int REQUEST_CAMERA;
    public  void getCamerapermission(){
        /*if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_CAMERA);*/
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);

        }
    }

    // taking a photo
    public void clickImage()
    {
        //fire intent to take the picture
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File fp = getFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fp));
        //block crash in android 7
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //end of block
        startActivityForResult(intent, REQUEST_CHECK);

    }

    private File getFile() {
        File fileName = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        File folder = new File(fileName + "/RegreenAfrica/Nursery");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //image random name
        Random generator = new Random();
        int n = 1000000;
        Random generator2 = new Random();
        int n2 = 1000000;
        n = generator.nextInt(n);//random number generator
        n2 = generator2.nextInt(n2);//random number generator2
        //Date date = new Date();
        //String dt = g.getfid() + "_" + DateFormat.getDateTimeInstance().format(date);
        String dt = "img_" + n + "_" + n2;
        imgpath = new File(folder, File.separator +
                dt + ".png");
        return imgpath;
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        try{
            s = imgpath.toString();
            //Toast.makeText(this.getActivity(), s, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();

        }
        //resize image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bmap = BitmapFactory.decodeFile(s,options);
        Drawable d=new BitmapDrawable(bmap);
        //set it on image view
        //imgv1.setImageDrawable(Drawable.createFromPath(s));
        imgv1.setImageDrawable(d);
        g.setpath(s);
    }

    //save data as you move next
    public void savenurseryInfo(){
        //nursery Random id
        Random generator = new Random();
        int n = 1000000;
        Random generator2 = new Random();
        int n2 = 1000000;
        n = generator.nextInt(n);//random number generator
        n2 = generator2.nextInt(n2);//random number generator2
        String nid = "nursery_" + n + "_" + n2;
        g.setnid(nid);
        //
        EditText e_name = (EditText) getActivity().findViewById(R.id.ename);
        g.setename(e_name.getText().toString());
        EditText date = (EditText) getActivity().findViewById(R.id.in_date);
        g.setin_date(date.getText().toString());

        /*Spinner survey_name = (Spinner) getActivity().findViewById(R.id.survey_name);
        //check if spinner is selected
        if(survey_name != null && survey_name.getSelectedItem() !=null ) {
            g.setproject_name(survey_name.getSelectedItem().toString());
        }//added for survey project selection
        */
        Spinner survey = (Spinner) getActivity().findViewById(R.id.survey_name);
        if(survey != null && survey.getSelectedItem() !=null ) {
            g.setnsurvey_name(survey.getSelectedItem().toString());
        }
       // EditText other_survey = (EditText) getActivity().findViewById(R.id.survey_other);
        //g.setnsurvey_name(other_survey.getText().toString());//added for other project option

        Spinner country = (Spinner) getActivity().findViewById(R.id.spinner1);
        //g.setnursery_country(country.getText().toString());
        if(country != null && country.getSelectedItem() !=null ) {
            g.setnursery_country(country.getSelectedItem().toString());
        }
        /*EditText country_new = (EditText) getActivity().findViewById(R.id.country);//added for the new country
        g.setnursery_country(country_new.getText().toString());*/

       /* EditText county_region = (EditText) getActivity().findViewById(R.id.nursery_county);
        g.setnursery_county(county_region.getText().toString());
        EditText district = (EditText) getActivity().findViewById(R.id.nursery_district);
        g.setnursery_district(district.getText().toString());*/
        Spinner county_region = (Spinner) getActivity().findViewById(R.id.spinner2);
        //check if spinner is selected
        if(county_region != null && county_region.getSelectedItem() !=null ) {
            g.setnursery_county(county_region.getSelectedItem().toString());
        }
        /*EditText county_new = (EditText) getActivity().findViewById(R.id.county);//added for new county that is not on the list
        g.setnursery_county(county_new.getText().toString());*/
        /*EditText district = (EditText) getActivity().findViewById(R.id.district);
        g.setdistricts(district.getText().toString());*/
        Spinner district = (Spinner) getActivity().findViewById(R.id.spinner3);
        //check if spinner is selected
        if(district != null && district.getSelectedItem() !=null ) {
            g.setnursery_district(district.getSelectedItem().toString());
        }

        /*EditText district_new = (EditText) getActivity().findViewById(R.id.district);//added for new district option
        g.setnursery_district(district_new.getText().toString());*/

        EditText operator = (EditText) getActivity().findViewById(R.id.nursery_operator);
        g.setnursery_operator(operator.getText().toString());
        EditText contact = (EditText) getActivity().findViewById(R.id.nursery_contact);
        g.setnursery_contact(contact.getText().toString());
        EditText nursery_name = (EditText) getActivity().findViewById(R.id.nursery_name);
        g.setnursery_name(nursery_name.getText().toString());
        EditText species_number = (EditText) getActivity().findViewById(R.id.species_number);
        g.setnspecies_number(species_number.getText().toString());
        EditText ndate = (EditText) getActivity().findViewById(R.id.n_date);
        g.setndate(ndate.getText().toString());
        CheckBox govt = (CheckBox) getActivity().findViewById(R.id.govt);
        if(govt.isChecked()) {
            g.setgovt("yes");
        }else {
            g.setgovt("no");
        }
        CheckBox church_mosque = (CheckBox) getActivity().findViewById(R.id.cm);
        if(church_mosque.isChecked()) {
            g.setchurch_mosque("yes");
        }else {
            g.setchurch_mosque("no");
        }
        CheckBox school = (CheckBox) getActivity().findViewById(R.id.school);
        if(school.isChecked()) {
            g.setschools("yes");
        }else {
            g.setschools("no");
        }
        CheckBox w_groups = (CheckBox) getActivity().findViewById(R.id.w_groups);
        if(w_groups.isChecked()) {
            g.setwomen("yes");
        }else {
            g.setwomen("no");
        }
        CheckBox y_groups = (CheckBox) getActivity().findViewById(R.id.y_groups);
        if(y_groups.isChecked()) {
            g.setyouth("yes");
        }else {
            g.setyouth("no");
        }
        CheckBox private_individual = (CheckBox) getActivity().findViewById(R.id.private_individual);
        if(private_individual.isChecked()) {
            g.setprivate_individual("yes");
        }else {
            g.setprivate_individual("no");
        }
        CheckBox c_village = (CheckBox) getActivity().findViewById(R.id.c_village);
        if(c_village.isChecked()) {
            g.setcommunal_village("yes");
        }else {
            g.setcommunal_village("no");
        }
        EditText n_type=(EditText) getActivity().findViewById(R.id.other_types);
        g.setother_type(n_type.getText().toString());
        //get locations from global
        //get photo
        g.setuploaded("no");//set uploaded to no on insert
        g.setmodule("Nursery");//set which module is this on insert
    }

}

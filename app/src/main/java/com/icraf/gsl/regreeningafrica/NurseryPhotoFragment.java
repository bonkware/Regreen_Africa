package com.icraf.gsl.regreeningafrica;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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

        imgv1 = (ImageView) view.findViewById(R.id.imageView1);
        upload = (ImageButton) view.findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //call photo on button click
                clickImage();
                bnext.setEnabled(true);//enable next button

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
        File folder = new File("sdcard/RegreenAfrica");
        if (!folder.exists()) {
            folder.mkdir();
        }
        //image random name
        Random generator = new Random();
        int n = 1000;
        n = generator.nextInt(n);//random number generator
        //Date date = new Date();
        //String dt = g.getfid() + "_" + DateFormat.getDateTimeInstance().format(date);
        String dt = "img_" + n;
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
        int n = 10000;
        n = generator.nextInt(n);//random number generator
        String nid = "nursery_" + n;
        g.setnid(nid);
        //
        EditText e_name = (EditText) getActivity().findViewById(R.id.ename);
        g.setename(e_name.getText().toString());
        EditText date = (EditText) getActivity().findViewById(R.id.in_date);
        g.setin_date(date.getText().toString());

        Spinner survey_name = (Spinner) getActivity().findViewById(R.id.survey_name);
        //check if spinner is selected
        if(survey_name != null && survey_name.getSelectedItem() !=null ) {
            g.setsurvey_name(survey_name.getSelectedItem().toString());
        }//added for survey project selection
        EditText other_survey = (EditText) getActivity().findViewById(R.id.survey_other);
        g.setsurvey_name(other_survey.getText().toString());//added for other project option

        Spinner country = (Spinner) getActivity().findViewById(R.id.nursery_country);
        //g.setnursery_country(country.getText().toString());
        if(country != null && country.getSelectedItem() !=null ) {
            g.setnursery_country(country.getSelectedItem().toString());
        }
        EditText county_region = (EditText) getActivity().findViewById(R.id.nursery_county);
        g.setnursery_county(county_region.getText().toString());
        EditText district = (EditText) getActivity().findViewById(R.id.nursery_district);
        g.setnursery_district(district.getText().toString());
        EditText operator = (EditText) getActivity().findViewById(R.id.nursery_operator);
        g.setnursery_operator(operator.getText().toString());
        EditText contact = (EditText) getActivity().findViewById(R.id.nursery_contact);
        g.setnursery_contact(contact.getText().toString());
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
    }

}

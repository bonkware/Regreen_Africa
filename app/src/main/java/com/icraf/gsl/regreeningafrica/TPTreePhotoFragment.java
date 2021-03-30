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
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.Random;


/**
 * Created by benard on 1/18/19.
 *
 */

public class TPTreePhotoFragment extends Fragment {
    public TPTreePhotoFragment() {

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
                View view = inflater.inflate(R.layout.tree_photo, container,
                        false);
                //allow camera permission and storage
                getCamerapermission();
                //set next button disabled
                final Button bnext = (Button) view.findViewById(R.id.next);
                bnext.setEnabled(false);//disable button
                bnext.setAlpha(0.5f);

                imgv1 = (ImageView) view.findViewById(R.id.imageView1);
                upload = (ImageButton) view.findViewById(R.id.upload);
                upload.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //call photo on button click
                        clickImage();
                        bnext.setEnabled(true);
                        bnext.setBackgroundColor(Color.parseColor("#966648"));//change color
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
                File folder = new File(fileName + "/RegreenAfrica/TP");
                //File folder = new File("sdcard/RegreenAfrica/TP");
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

        }

package com.example.benard.regreeningafrica;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.benard.regreeningafrica.R.id.pager;

public class NurseryMainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_planting);

        viewPager = (ViewPager) findViewById(pager);
        viewPager.setOffscreenPageLimit(12);//number of fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //All fragments
        /*adapter.addFragment(new TPFarmInstEnumFragment(), "enumurator");
        adapter.addFragment(new TPFarmInstFragment(), "farmer/institution details");
        adapter.addFragment(new TPFarmInstLocFragment(), "tree planting location");
        adapter.addFragment(new TPFarmInstSiteFragment(), "tree planting site");
        adapter.addFragment(new TPFarmInstLandsizeFragment(), "land size green");
        adapter.addFragment(new TPCohortRecordFragment(), "record tree cohort");
        adapter.addFragment(new TPTreeMeasurementsFragment(), "tree measurements");
        adapter.addFragment(new TPTreeGpsFragment(), "tree gps");
        adapter.addFragment(new TPTreePhotoFragment(), "tree photo");
        adapter.addFragment(new TPCohortManagementsFragment(), "managements");
        adapter.addFragment(new TPCohortUsageFragment(), "usage");
        adapter.addFragment(new TPTreeEndFragment(), "end tree planting");
        viewPager.setAdapter(adapter);*/

    }
    //for navigation buttons i.e move next if all fields are filled
    /*
    public void jumpToFI(View view){
        EditText ed = (EditText) findViewById(R.id.ename);
        EditText date = (EditText) findViewById(R.id.in_date);
        boolean fail = false;
        if (ed.getText().toString().trim().length() == 0) {
            fail = true;
            ed.requestFocus();
            ed.setError("Enter Enumulator name");
        }
        if (date.getText().toString().trim().length() == 0) {
            fail = true;
            date.requestFocus();
            date.setError("Select date");
        }
        if (!fail) {
            viewPager.setCurrentItem(1);
        }
    }
    //navigate back to enumerator name
    public void jumpToEnum(View view){
        viewPager.setCurrentItem(0);
    }
    public void jumpToLocation(View view){
        EditText fi = (EditText) findViewById(R.id.fnames);
        boolean fail = false;
        if (fi.getText().toString().trim().length() == 0) {
            fail = true;
            fi.requestFocus();
            fi.setError("Enter farmer/institution");
        }
        if (!fail) {
            viewPager.setCurrentItem(2);
        }
    }
    public void jumpBackFI(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpToSite(View view){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.planting_location);
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(TPFarmInstiMainAcivity.this,"Select one",Toast.LENGTH_SHORT).show();
        }
        else
        {
            viewPager.setCurrentItem(3);
        }


    }
    public void jumpBackLocation(View view){
        viewPager.setCurrentItem(2);
    }
    public void jumpToEst(View view){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.planting_site);
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(TPFarmInstiMainAcivity.this,"Select one",Toast.LENGTH_SHORT).show();
        }
        else
        {
            viewPager.setCurrentItem(4);
        }
    }
    public void jumpBackSite(View view){
        viewPager.setCurrentItem(3);
    }
    public void jumpToCohort(View view){
        EditText le = (EditText) findViewById(R.id.landestimate);
        boolean fail = false;
        if (le.getText().toString().trim().length() == 0) {
            fail = true;
            le.requestFocus();
            le.setError("Enter land estimate");
        }
        if (!fail) {
            viewPager.setCurrentItem(5);
        }
    }
    public void jumpBackEst(View view){
        viewPager.setCurrentItem(4);
    }
    public void jumpToMeasurement(View view){
        EditText ed = (EditText) findViewById(R.id.sl_name);
        EditText date = (EditText) findViewById(R.id.p_date);
        boolean fail = false;
        if (ed.getText().toString().trim().length() == 0) {
            fail = true;
            ed.requestFocus();
            ed.setError("Enter Tree name");
        }
        if (date.getText().toString().trim().length() == 0) {
            fail = true;
            date.requestFocus();
            date.setError("Select planted date");
        }
        if (!fail) {
            viewPager.setCurrentItem(6);
        }
        //viewPager.setCurrentItem(6);
    }
    public void jumpBackCohort(View view){
        viewPager.setCurrentItem(5);
    }
    public void jumpToGPS(View view){
        EditText h = (EditText) findViewById(R.id.height);
        boolean fail = false;
        if (h.getText().toString().trim().length() == 0) {
            fail = true;
            h.requestFocus();
            h.setError("Enter height");
        }
        if (!fail) {
            viewPager.setCurrentItem(7);
        }
    }
    public void jumpBackMeasurement(View view){
        viewPager.setCurrentItem(6);
    }
    public void jumpToPhoto(View view){

        viewPager.setCurrentItem(8);
    }
    public void jumpBackGPS(View view){
        viewPager.setCurrentItem(7);
    }
    public void jumpToMgt(View view){

        viewPager.setCurrentItem(9);
    }
    public void jumpBackPhoto(View view){
        viewPager.setCurrentItem(8);
    }
    public void jumpToUsage(View view){

        viewPager.setCurrentItem(10);
    }
    public void jumpBackMgt(View view){
        viewPager.setCurrentItem(9);
    }
    public void jumpToEnd(View view){

        viewPager.setCurrentItem(11);
    }
    public void jumpBackUsage(View view){
        viewPager.setCurrentItem(10);
    }
    */
    //end of nav buttons
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add) {

            // Intent add_mem = new Intent(this, Index.class);
            // startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

    //on back pressed navigate back to main selection
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Exit from recording nursery module?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        NurseryMainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}


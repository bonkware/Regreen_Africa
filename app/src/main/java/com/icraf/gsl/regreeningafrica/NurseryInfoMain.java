package com.icraf.gsl.regreeningafrica;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.R.id.pager;

public class NurseryInfoMain extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursery);

        //set title
        setTitle("Nursery");
        //logo
        ActionBar logo = getSupportActionBar();
        logo.setDisplayUseLogoEnabled(true);
        logo.setDisplayShowHomeEnabled(true);
        logo.setDisplayShowHomeEnabled(false);
        logo.setHomeAsUpIndicator(R.drawable.ic_nursery);
        logo.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(pager);
        viewPager.setOffscreenPageLimit(5);//number of fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //All fragments
        adapter.addFragment(new NurseryEnumFragment(), "nursery enum");
        adapter.addFragment(new NurseryInfoFragment(), "nursery info");
        adapter.addFragment(new NurserySpeciesinfoFragment(), "nursery species info");
        adapter.addFragment(new NurseryTypeFragment(), "nursery type");
        adapter.addFragment(new NurseryGpsFragment(), "nursery gps");
        adapter.addFragment(new NurseryPhotoFragment(), "nursery photo");
        viewPager.setAdapter(adapter);

    }
    public void jumpToFI(View view){
        EditText ed = (EditText) findViewById(R.id.ename);
        EditText date = (EditText) findViewById(R.id.in_date);
        Spinner project = (Spinner) findViewById(R.id.survey_name);
        boolean fail = false;
        if (ed.getText().toString().trim().length() == 0) {
            fail = true;
            ed.requestFocus();
            ed.setError("Enter data collector name");
        }
        if (date.getText().toString().trim().length() == 0) {
            fail = true;
            date.requestFocus();
            date.setError("Select date");
        }
        if (project.getSelectedItem().toString().trim().equals("Select project")) {//validate the spinner not
            fail = true;
            project.requestFocus();
            TextView errorText = (TextView)project.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Please select the project name");//changes the selected item text to this
        }
        if (!fail) {
            viewPager.setCurrentItem(1);
        }
    }
    public void jumpBackFI(View view){
        viewPager.setCurrentItem(0);
    }
    public void jumpToNurseryinfo(View view){
        //EditText ed = (EditText) findViewById(R.id.nursery_county);
        //EditText district = (EditText) findViewById(R.id.nursery_district);
        Spinner country = (Spinner) findViewById(R.id.spinner1);
        EditText c = (EditText) findViewById(R.id.country);
        Spinner county = (Spinner) findViewById(R.id.spinner2);
        EditText ct = (EditText) findViewById(R.id.county);
        Spinner district = (Spinner) findViewById(R.id.spinner3);
        EditText d = (EditText) findViewById(R.id.district);
        EditText operator = (EditText) findViewById(R.id.nursery_operator);
        EditText contact = (EditText) findViewById(R.id.nursery_contact);
        boolean fail = false;
       /* if (ed.getText().toString().trim().length() == 0) {
            fail = true;
            ed.requestFocus();
            ed.setError("Enter county");
        }
        if (district.getText().toString().trim().length() == 0) {
            fail = true;
            district.requestFocus();
            district.setError("Enter district");
        }*/
        if (country.getSelectedItem().toString().trim().equals("Select Country first")) {//validate the spinner not
            //if (c.getText().toString().trim().length() == 0) {
                fail = true;
                country.requestFocus();
                TextView errorText = (TextView) country.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Select or add new");//changes the selected item text to this
                //c.setError("Enter Country name");
            //}
        }
        if (county.getSelectedItem().toString().trim().equals("Select")) {//validate the spinner not
            //if (ct.getText().toString().trim().length() == 0) {
                fail = true;
                county.requestFocus();
                TextView errorText = (TextView) county.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Select or add new");//changes the selected item text to this
                //ct.setError("Enter County name");
            //}
        }
        if (district.getSelectedItem().toString().trim().equals("Select")) {//validate the spinner not
            //if (d.getText().toString().trim().length() == 0) {
                fail = true;
                district.requestFocus();
                TextView errorText = (TextView) district.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Select or add new");//changes the selected item text to this
                //d.setError("Enter district name");
            //}
        }

        if (operator.getText().toString().trim().length() == 0) {
            fail = true;
            operator.requestFocus();
            operator.setError("Enter name");
        }
        if (contact.getText().toString().trim().length() == 0) {
            fail = true;
            contact.requestFocus();
            contact.setError("Enter contacts");
        }
        if (!fail) {
            viewPager.setCurrentItem(2);
        }
        // viewPager.setCurrentItem(1);

    }
    public void jumpBackNurseryinfo(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpTotype(View view){
        viewPager.setCurrentItem(3);
    }
    public void jumpBackInfo(View view){
        viewPager.setCurrentItem(2);
    }
    public void jumpToLocation(View view){
        viewPager.setCurrentItem(4);
    }
    public void jumpBackType(View view){
        viewPager.setCurrentItem(3);
    }
    public void jumpToPhoto(View view){
        viewPager.setCurrentItem(5);
    }
    public void jumpBackLocation(View view){
        viewPager.setCurrentItem(4);
    }
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
                        NurseryInfoMain.super.onBackPressed();
                    }
                }).create().show();
    }
}


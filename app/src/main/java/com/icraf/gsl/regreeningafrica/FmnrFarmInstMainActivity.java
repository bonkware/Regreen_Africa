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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.R.id.pager;

public class FmnrFarmInstMainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmnr);

        //set title
        setTitle("FMNR");
        //logo
        ActionBar logo = getSupportActionBar();
        logo.setDisplayUseLogoEnabled(true);
        logo.setDisplayShowHomeEnabled(true);
        logo.setDisplayShowHomeEnabled(false);
        logo.setHomeAsUpIndicator(R.drawable.ic_fmnr);
        logo.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(pager);
        viewPager.setOffscreenPageLimit(5);//number of fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //All fragments
        adapter.addFragment(new FmnrFarmInstEnumFragment(), "enumurator");
        adapter.addFragment(new FmnrFarmInstFragment(), "farmer/institution details");
        adapter.addFragment(new FmnrFarmInstLocFragment(), "tree planting location");
        /*adapter.addFragment(new FmnrFarmInstSpeciesNumberFragment(), "species number");
        adapter.addFragment(new FmnrFarmInstLandsizeFragment(), "land size green");*/
        viewPager.setAdapter(adapter);

    }
    //for navigation buttons i.e move next if all fields are filled
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
    //navigate back to enumerator name
    public void jumpToEnum(View view){
        viewPager.setCurrentItem(0);
    }
    public void jumpToLocation(View view){
        EditText fi = (EditText) findViewById(R.id.fnames);
        Spinner country = (Spinner) findViewById(R.id.spinner1);
        EditText c = (EditText) findViewById(R.id.country);
        Spinner county = (Spinner) findViewById(R.id.spinner2);
        EditText ct = (EditText) findViewById(R.id.county);
        Spinner district = (Spinner) findViewById(R.id.spinner3);
        EditText d = (EditText) findViewById(R.id.district);
        boolean fail = false;
        if (fi.getText().toString().trim().length() == 0) {
            fail = true;
            fi.requestFocus();
            fi.setError("Enter farmer/institution");
        }
        if (country.getSelectedItem().toString().trim().equals("Select Country first")) {//validate the spinner not
            if (c.getText().toString().trim().length() == 0) {
                fail = true;
                c.requestFocus();
                TextView errorText = (TextView) country.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Select or add new");//changes the selected item text to this
                c.setError("Enter Country name");
            }
        }
        if (county.getSelectedItem().toString().trim().equals("Select")) {//validate the spinner not
            if (ct.getText().toString().trim().length() == 0) {
                fail = true;
                ct.requestFocus();
                TextView errorText = (TextView) county.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Select or add new");//changes the selected item text to this
                ct.setError("Enter County name");
            }
        }
        if (district.getSelectedItem().toString().trim().equals("Select")) {//validate the spinner not
            if (d.getText().toString().trim().length() == 0) {
                fail = true;
                d.requestFocus();
                TextView errorText = (TextView) district.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Select or add new");//changes the selected item text to this
                d.setError("Enter district name");
            }
        }
        if (!fail) {
            viewPager.setCurrentItem(2);
        }
    }
    public void jumpBackFI(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpTosnumber(View view){
        viewPager.setCurrentItem(3);
    }
    public void jumpBackLoc(View view){
        viewPager.setCurrentItem(2);
    }
  /*public void jumpTophoto(View view){
      viewPager.setCurrentItem(4);
  }*/
    public void jumpBackLocation(View view){
        viewPager.setCurrentItem(3);
    }

    public void jumpToEst(View view){
            viewPager.setCurrentItem(4);
    }
    public void jumpBackSnumber(View view){
        viewPager.setCurrentItem(3);
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
                .setMessage("Exit from recording tree farmer/institution?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        FmnrFarmInstMainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}

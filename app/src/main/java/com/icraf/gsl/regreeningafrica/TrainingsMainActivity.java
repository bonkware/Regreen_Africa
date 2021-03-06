package com.icraf.gsl.regreeningafrica;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.R.id.pager;

public class TrainingsMainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_planting);

        //set title
        setTitle("Training");
        //logo
        ActionBar logo = getSupportActionBar();
        logo.setDisplayUseLogoEnabled(true);
        logo.setDisplayShowHomeEnabled(true);
        logo.setDisplayShowHomeEnabled(false);
        logo.setHomeAsUpIndicator(R.drawable.ic_training);
        logo.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(pager);
        viewPager.setOffscreenPageLimit(4);//number of fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //All fragments
        adapter.addFragment(new TrainingsEnumFragment(), "trainings enum");
        adapter.addFragment(new TrainingsFragment(), "trainings");
        adapter.addFragment(new TrainingsDetailsFragment(), "trainings topics");
        adapter.addFragment(new TrainingsNoParticipantsFragment(), "Number of participants");
        viewPager.setAdapter(adapter);
    }
    public void jumpToFI(View view){
        EditText ed = (EditText) findViewById(R.id.ename);
        EditText date = (EditText) findViewById(R.id.in_date);
        Spinner project = (Spinner) findViewById(R.id.project);
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
        //viewPager.setCurrentItem(1);
    }
    public void jumpBackFI(View view){
        viewPager.setCurrentItem(0);
    }
    public void jumpToDetails(View view){
        EditText crs = (EditText) findViewById(R.id.crname);
        EditText dis = (EditText) findViewById(R.id.dcwname);
        Spinner country = (Spinner) findViewById(R.id.cname);
        boolean fail = false;
        if (country.getSelectedItem().toString().trim().equals("Select Country first")) {//validate the spinner not
            fail = true;
            country.requestFocus();
            TextView errorText = (TextView)country.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Please select the country name");//changes the selected item text to this
        }
        if (crs.getText().toString().trim().length() == 0) {
            fail = true;
            crs.requestFocus();
            crs.setError("Enter region/state/county");
        }
        if (dis.getText().toString().trim().length() == 0) {
            fail = true;
            dis.requestFocus();
            dis.setError("Enter district/woreda/commune");
        }
        if (!fail) {
            viewPager.setCurrentItem(2);
        }
        //viewPager.setCurrentItem(2);
    }
    //navigate back to enumerator name
    public void jumpBackTrainings(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpToParticipants(View view){
        EditText crs = (EditText) findViewById(R.id.topic);
        EditText date = (EditText) findViewById(R.id.training_date);
        EditText venue = (EditText) findViewById(R.id.venue);
        EditText partner = (EditText) findViewById(R.id.partners);
        boolean fail = false;
        if (crs.getText().toString().trim().length() == 0) {
            fail = true;
            crs.requestFocus();
            crs.setError("Enter topic");
        }
        if (date.getText().toString().trim().length() == 0) {
            fail = true;
            date.requestFocus();
            date.setError("Select date");
        }
        if (venue.getText().toString().trim().length() == 0) {
            fail = true;
            venue.requestFocus();
            venue.setError("Enter the venue");
        }
        if (partner.getText().toString().trim().length() == 0) {
            fail = true;
            partner.requestFocus();
            partner.setError("Enter the partners names");
        }
        if (!fail) {
            viewPager.setCurrentItem(3);
        }
        //viewPager.setCurrentItem(3);
    }
    //navigate back to enumerator name
    public void jumpBackDetails(View view){
        viewPager.setCurrentItem(2);
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
                .setMessage("Exit from recording Trainings module?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        TrainingsMainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}



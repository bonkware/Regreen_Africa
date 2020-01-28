package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 2/6/19.
 *
 */

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.R.id.pager;

public class TPTreeMeasureMainAcivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_planting);

        //set title
        setTitle("Tree planting");
        //logo
        ActionBar logo = getSupportActionBar();
        logo.setDisplayUseLogoEnabled(true);
        logo.setDisplayShowHomeEnabled(true);
        logo.setDisplayShowHomeEnabled(false);
        logo.setHomeAsUpIndicator(R.drawable.ic_tp);
        logo.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(pager);
        viewPager.setOffscreenPageLimit(4);//number of fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //All fragments
        adapter.addFragment(new TPTreeMeasurementsFragment(), "tree measurements");
        adapter.addFragment(new TPTreeGpsFragment(), "tree gps");
        adapter.addFragment(new TPTreePhotoFragment(), "tree photo");
        adapter.addFragment(new TPTreeEndFragment(), "end tree planting");
        viewPager.setAdapter(adapter);

    }
    //for navigation buttons i.e move next if all fields are filled
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
    public void jumpToGPS(View view){

        viewPager.setCurrentItem(1);
    }
    public void jumpBackMeasurement(View view){
        viewPager.setCurrentItem(0);
    }

    public void jumpToPhoto(View view){
        viewPager.setCurrentItem(2);
    }
    public void jumpBackGPS(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpToEnd(View view){

        viewPager.setCurrentItem(3);
    }
    public void jumpBackPhoto(View view){
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
                .setMessage("Exit from recording tree module?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        TPTreeMeasureMainAcivity.super.onBackPressed();
                    }
                }).create().show();
    }
}


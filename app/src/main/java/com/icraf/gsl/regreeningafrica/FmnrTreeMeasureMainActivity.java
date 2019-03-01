package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 2/6/19.
 */

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
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.R.id.pager;

public class FmnrTreeMeasureMainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_planting);

        viewPager = (ViewPager) findViewById(pager);
        viewPager.setOffscreenPageLimit(7);//number of fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //All fragments
        adapter.addFragment(new FmnrSpeciesFragment(), "species");
        adapter.addFragment(new FmnrManagementsFragment(), "managements");
        adapter.addFragment(new FmnrUsageFragment(), "usage");
        adapter.addFragment(new FmnrTreeMeasurementsFragment(), "tree measurements");
        adapter.addFragment(new FmnrTreeGpsFragment(), "tree gps");
        adapter.addFragment(new FmnrTreePhotoFragment(), "tree photo");
        adapter.addFragment(new FmnrTreeEndFragment(), "end tree planting");
        viewPager.setAdapter(adapter);

    }
    public void jumpToMgt(View view){
      /*  EditText ed = (EditText) findViewById(R.id.speciesname);
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
            viewPager.setCurrentItem(1);
        }*/

        viewPager.setCurrentItem(1);
    }
    public void jumpBackCohort(View view){
        viewPager.setCurrentItem(0);
    }

    public void jumpToUsage(View view){

        viewPager.setCurrentItem(2);
    }
    public void jumpBackMgt(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpBackUsage(View view){
        viewPager.setCurrentItem(2);
    }
    //for navigation buttons i.e move next if all fields are filled
    public void jumpToFI(View view){
      /*  EditText ed = (EditText) findViewById(R.id.ename);
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
            viewPager.setCurrentItem(3);
        }*/
        viewPager.setCurrentItem(3);
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
            viewPager.setCurrentItem(4);
        }
    }
    public void jumpBackMeasurement(View view){
        viewPager.setCurrentItem(3);
    }
    public void jumpToPhoto(View view){

        viewPager.setCurrentItem(5);
    }
    public void jumpBackGPS(View view){
        viewPager.setCurrentItem(4);
    }
    public void jumpToEnd(View view){

        viewPager.setCurrentItem(6);
    }
    public void jumpBackPhoto(View view){
        viewPager.setCurrentItem(5);
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
                        FmnrTreeMeasureMainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}

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
        adapter.addFragment(new NurseryInfoFragment(), "nursery info");
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


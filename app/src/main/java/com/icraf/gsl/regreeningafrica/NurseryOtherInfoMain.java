package com.icraf.gsl.regreeningafrica;

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

import java.util.ArrayList;
import java.util.List;

import static com.icraf.gsl.regreeningafrica.R.id.pager;

public class NurseryOtherInfoMain extends AppCompatActivity {
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
        adapter.addFragment(new NurserySpeciesFragment(), "nursery species");
        adapter.addFragment(new NurseryMethodsFragment(), "nursery production");
        adapter.addFragment(new NurseryPropagationFragment(), "nursery propagation");
        adapter.addFragment(new NurseryOtherFragment(), "other nursery info");
        adapter.addFragment(new NurseryEndFragment(), "end");
        viewPager.setAdapter(adapter);

    }
    public void jumpToMethod(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpBackSpecies(View view){
        viewPager.setCurrentItem(0);
    }
    public void jumpToPropagation(View view){
        viewPager.setCurrentItem(2);
    }
    public void jumpBackMethod(View view){
        viewPager.setCurrentItem(1);
    }
    public void jumpToOther(View view){
        viewPager.setCurrentItem(3);
    }
    public void jumpBackPropagation(View view){
        viewPager.setCurrentItem(2);
    }
    public void jumpToEnd(View view){
        viewPager.setCurrentItem(4);
    }
    public void jumpBackOther(View view){
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
                .setMessage("Exit from recording nursery module?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        NurseryOtherInfoMain.super.onBackPressed();
                    }
                }).create().show();
    }
}


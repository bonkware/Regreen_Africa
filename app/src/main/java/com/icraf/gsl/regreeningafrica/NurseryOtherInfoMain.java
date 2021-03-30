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
        EditText ed = (EditText) findViewById(R.id.nursery_species);
        EditText local = (EditText) findViewById(R.id.nursery_local);
        boolean fail = false;
        if (ed.getText().toString().trim().length() == 0) {
            if (local.getText().toString().trim().length() == 0) {
                fail = true;
                local.requestFocus();
                local.setError("Enter Scientific or local name");
            }
            ed.requestFocus();
            ed.setError("Enter Scientific or local name");
        }

        if (!fail) {
            viewPager.setCurrentItem(1);
        }
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
        EditText fi = (EditText) findViewById(R.id.qpurchased);
        Spinner country = (Spinner) findViewById(R.id.spinner_units);
        EditText sown = (EditText) findViewById(R.id.seeds_sown);
        Spinner county = (Spinner) findViewById(R.id.spinner_unitsown);
        EditText g = (EditText) findViewById(R.id.germinated);
        EditText s = (EditText) findViewById(R.id.survived);
        boolean fail = false;
        if (fi.getText().toString().trim().length() == 0) {
            fail = true;
            fi.requestFocus();
            fi.setError("Enter Quantity");
        }
        if (country.getSelectedItem().toString().trim().equals("Select unit")) {//validate the spinner not
            //if (c.getText().toString().trim().length() == 0) {
            fail = true;
            country.requestFocus();
            TextView errorText = (TextView) country.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Select or add new");//changes the selected item text to this
            // c.setError("Enter Country name");
            //}
        }
        if (sown.getText().toString().trim().length() == 0) {
            fail = true;
            sown.requestFocus();
            sown.setError("Enter sed sown");
        }
        if (county.getSelectedItem().toString().trim().equals("Select unit")) {//validate the spinner not
            //if (ct.getText().toString().trim().length() == 0) {
            fail = true;
            county.requestFocus();
            TextView errorText = (TextView) county.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Select or add new");//changes the selected item text to this
            // ct.setError("Enter County name");
            // }
        }
        if (g.getText().toString().trim().length() == 0) {
            fail = true;
            g.requestFocus();
            g.setError("Enter Quantity");
        }
        if (s.getText().toString().trim().length() == 0) {
            fail = true;
            s.requestFocus();
            s.setError("Enter Quantity");
        }
        if (!fail) {
            viewPager.setCurrentItem(4);
        }
        //viewPager.setCurrentItem(4);
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
                .setMessage("Exit from adding trees?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        NurseryOtherInfoMain.super.onBackPressed();
                    }
                }).create().show();
        //NurseryOtherInfoMain.super.onBackPressed();
        //overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}


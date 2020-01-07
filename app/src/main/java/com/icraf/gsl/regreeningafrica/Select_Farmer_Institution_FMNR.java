package com.icraf.gsl.regreeningafrica;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by benard on 5/7/19.
 *
 */

public class Select_Farmer_Institution_FMNR extends AppCompatActivity {
    private DbAccess dbAccess;
    private ListView listView;
    EditText inputSearch;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper.farmer_id,
            DatabaseHelper.farmer_inst_name
    };

    final int[] to = new int[] {R.id.farmerID,R.id.FIname
    };
    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_list_fmnr);
        //set title
        setTitle("FMNR");
        //logo
        ActionBar logo = getSupportActionBar();
        logo.setDisplayUseLogoEnabled(true);
        logo.setDisplayShowHomeEnabled(true);
        logo.setDisplayShowHomeEnabled(false);
        logo.setHomeAsUpIndicator(R.drawable.ic_fmnr);
        logo.setDisplayHomeAsUpEnabled(true);
        //for previous/back button
        Button button_next = (Button) findViewById(R.id.prev);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.prev:
                        Intent intent = new Intent(getApplicationContext(), SelectTPMultiple_FMNR.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        dbAccess = new DbAccess(this);
        dbAccess.open();
        Cursor cursor = dbAccess.fetch_FInamesFMNR();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        adapter = new SimpleCursorAdapter(this, R.layout.farmer_view_fmnr, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        //data search start on edittext
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return dbAccess.fetchdatabyfilterFMNR(constraint.toString(),"name");//filter farmer/instituition names
            }
        });
        //filter data in list view
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
            }
        });//end of filter

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView fid = (TextView) view.findViewById(R.id.farmerID);
                //TextView name = (TextView) view.findViewById(R.id.FIname);
                String farmer_id = fid.getText().toString();
                //String farmer_name = name.getText().toString();
                RegreeningGlobal g = RegreeningGlobal.getInstance();
                g.setfid(farmer_id);//pass farmerID to the next page
                g.setMultiplot(true);
               // plotid();//generate plot id for polygons

                Intent tp_intent = new Intent(getApplicationContext(), FmnrPlotMainActivity.class);
                startActivity(tp_intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
    }
    //setting plot id for polygons
    public void plotid(){
        //generate unique id for farmer/institution
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);//random number generator
        String pid = "plot_" + n;
        g.setpid(pid);
    }

}

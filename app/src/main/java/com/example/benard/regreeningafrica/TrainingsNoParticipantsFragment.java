package com.example.benard.regreeningafrica;

/**
 * Created by benard on 2/4/19.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TrainingsNoParticipantsFragment extends Fragment {
    private DbAccess dbAccess;
    public TrainingsNoParticipantsFragment() {
        // Required empty public constructor
    }
    RegreeningGlobal g = RegreeningGlobal.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_trainings_participants, container,
                false);
        dbAccess = new DbAccess(this.getActivity());
        dbAccess.open();

        //finish adding trainings
        Button button_next = (Button) view.findViewById(R.id.finish);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.finish:
                        saveTrainings();//save
                        dbAccess.insertTraining();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }
    public void saveTrainings(){
        Spinner c_name = (Spinner) getActivity().findViewById(R.id.cname);
        //g.setc_name(c_name.getText().toString());
        if(c_name != null && c_name.getSelectedItem() !=null ) {
            g.setc_name(c_name.getSelectedItem().toString());
        }
        EditText cr_name = (EditText) getActivity().findViewById(R.id.crname);
        g.setcr_name(cr_name.getText().toString());
        EditText dcw_name = (EditText) getActivity().findViewById(R.id.dcwname);
        g.setdcw_name(dcw_name.getText().toString());
        EditText topic = (EditText) getActivity().findViewById(R.id.topic);
        g.settraining_topic(topic.getText().toString());
        EditText training_date = (EditText) getActivity().findViewById(R.id.training_date);
        g.settraining_date(training_date.getText().toString());
        EditText training_venue = (EditText) getActivity().findViewById(R.id.venue);
        g.settraining_venue(training_venue.getText().toString());
        EditText training_partners = (EditText) getActivity().findViewById(R.id.partners);
        g.settraining_partners(training_partners.getText().toString());
        EditText number_participants = (EditText) getActivity().findViewById(R.id.participants);
        g.setnumber_participants(number_participants.getText().toString());
        EditText male_participants = (EditText) getActivity().findViewById(R.id.m_participants);
        g.setmale_participants(male_participants.getText().toString());
        EditText female_participants = (EditText) getActivity().findViewById(R.id.f_participants);
        g.setfemale_participants(female_participants.getText().toString());
        EditText youth_participants = (EditText) getActivity().findViewById(R.id.y_participants);
        g.setyouth_participants(youth_participants.getText().toString());
    }
}

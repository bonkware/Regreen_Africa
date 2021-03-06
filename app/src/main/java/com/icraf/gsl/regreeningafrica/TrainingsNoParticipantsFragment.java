package com.icraf.gsl.regreeningafrica;

/**
 * Created by benard on 2/4/19.
 *
 */
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

        //compare if total number of male,female or youth is higher than the total participants
        final EditText total  = (EditText)view.findViewById(R.id.participants);
        final EditText male  = (EditText)view.findViewById(R.id.m_participants);
        final EditText female  = (EditText)view.findViewById(R.id.f_participants);
        final EditText youth  = (EditText)view.findViewById(R.id.y_participants);

        male.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                //

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // Toast.makeText(getActivity(),"Message" + new String(c.toString()), Toast.LENGTH_SHORT).show();
                try {
                    int t = Integer.valueOf(total.getText().toString());
                    int m = Integer.valueOf(male.getText().toString());


                    if ( m > t ) {
                        // myEditText2.setText("");  //this automatically sets the editText2 field back to empty
                        male.setText("");
                        male.setError("Cannot be more than the total " + t);
                        //Toast.makeText(getActivity(), "Cannot be more than " + t, Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e){
                    //handle
                    //Toast.makeText(getActivity(),"Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });//end of text watcher1
        female.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                //

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //check if the number of trees survived is more than the planted
                // Toast.makeText(getActivity(),"Message" + new String(c.toString()), Toast.LENGTH_SHORT).show();
                try {
                    int t = Integer.valueOf(total.getText().toString());
                    int m = Integer.valueOf(male.getText().toString());
                    int f = Integer.valueOf(female.getText().toString());
                    //int y = Integer.valueOf(youth.getText().toString());

                    if (f + m > t) {
                        // myEditText2.setText("");  //this automatically sets the editText2 field back to empty
                        female.setText("");
                        female.setError("male and female cannot be more than the total number ");
                        //Toast.makeText(getActivity(), "Cannot be more than " + t, Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e){
                    //handle
                    //Toast.makeText(getActivity(),"Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });//end of text watcher2
        youth.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                //

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //check if the number of trees survived is more than the planted
                // Toast.makeText(getActivity(),"Message" + new String(c.toString()), Toast.LENGTH_SHORT).show();
                try {
                    int t = Integer.valueOf(total.getText().toString());
                    //int m = Integer.valueOf(male.getText().toString());
                    //int f = Integer.valueOf(female.getText().toString());
                    int y = Integer.valueOf(youth.getText().toString());


                    if (y > t) {
                        // myEditText2.setText("");  //this automatically sets the editText2 field back to empty
                        youth.setText("");
                        youth.setError("Cannot be more than the total of male and female participants ");
                        //Toast.makeText(getActivity(), "Cannot be more than " + t, Toast.LENGTH_SHORT).show();
                    }
                   /* if (m + f + y != t) {
                        // myEditText2.setText("");  //this automatically sets the editText2 field back to empty
                        youth.setText("");
                        youth.setError("Cannot be less than the total participant ");
                        //Toast.makeText(getActivity(), "Cannot be more than " + t, Toast.LENGTH_SHORT).show();
                    }*/
                } catch (NumberFormatException e){
                    //handle
                    //Toast.makeText(getActivity(),"Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });//end of text watcher3

        //finish adding trainings
        Button button_next = (Button) view.findViewById(R.id.finish);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fail = false;
                if (total.getText().toString().trim().length() == 0) {
                    fail = true;
                    total.requestFocus();
                    total.setError("Enter total number");
                }
                if (male.getText().toString().trim().length() == 0) {
                    fail = true;
                    male.requestFocus();
                    male.setError("Enter number of men");
                }
                if (female.getText().toString().trim().length() == 0) {
                    fail = true;
                    female.requestFocus();
                    female.setError("Enter number of women");
                }
                if (youth.getText().toString().trim().length() == 0) {
                    fail = true;
                    youth.requestFocus();
                    youth.setError("Enter number of youth");
                }
                if (!fail) {
                    saveTrainings();//save
                    dbAccess.insertTraining();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //Toast.makeText(SelectSurvey.this.getActivity(),"Saved! Add new tree",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    public void saveTrainings(){
        EditText e_name = (EditText) getActivity().findViewById(R.id.ename);
        g.setename(e_name.getText().toString());
        EditText date = (EditText) getActivity().findViewById(R.id.in_date);
        g.setin_date(date.getText().toString());

        Spinner cs_name = (Spinner) getActivity().findViewById(R.id.project);
        //g.setc_name(c_name.getText().toString());
        if(cs_name != null && cs_name.getSelectedItem() !=null ) {
            g.settsurvey_name(cs_name.getSelectedItem().toString());
        }
        //EditText other_survey = (EditText) getActivity().findViewById(R.id.survey_other);
        //g.settsurvey_name(other_survey.getText().toString());//added for other project option
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
        EditText type = (EditText) getActivity().findViewById(R.id.type);
        g.settraining_type(type.getText().toString());
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
        EditText notes = (EditText) getActivity().findViewById(R.id.notes);
        g.setnotes(notes.getText().toString());
        g.setuploaded("no");//set uploaded to no on insert
        g.setmodule("Training");//set which module is this on insert
    }
}

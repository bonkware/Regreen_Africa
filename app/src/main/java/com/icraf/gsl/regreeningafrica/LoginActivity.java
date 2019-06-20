package com.icraf.gsl.regreeningafrica;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by benard on 6/19/19.
 *
 */

public class LoginActivity extends AppCompatActivity {
    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the password entered matches one of the given choice
                if(ed1.getText().toString().equals("rafrica") &&
                        ed2.getText().toString().equals("Ra_Gsl@2019")
                        || ed1.getText().toString().equals("drydev") &&
                        ed2.getText().toString().equals("Dd_2019gsl")
                        ||ed1.getText().toString().equals("other") &&
                        ed2.getText().toString().equals("Ot_2019-gSl")) {
                    Toast.makeText(getApplicationContext(),"Signing...",Toast.LENGTH_SHORT).show();
                    //redirect to sending data activity
                    Intent i = new Intent(LoginActivity.this, OtherMainActivities.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }else{//if password does'nt match the given choices
                    Toast.makeText(getApplicationContext(), "Wrong Password",Toast.LENGTH_SHORT).show();
                    tx1.setVisibility(View.VISIBLE);
                    counter--;
                    String m =Integer.toString(counter) + " attempts left";
                    tx1.setText(m);//get the message counter
                    if (counter == 0) {
                        b1.setEnabled(false);
                        b1.setBackgroundColor(Color.parseColor("#FF0000"));//change color of button after 3 attempts
                        Toast.makeText(getApplicationContext(), "Try again later ",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //cancel and go back to main menu
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

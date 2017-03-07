package com.ergo.ppe_android11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ParametresActivity extends AppCompatActivity {

    private final String EXTRA_LOGIN = "undefined login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageButton exitButton = (ImageButton) findViewById(R.id.exit);
        final ImageButton coachingButton = (ImageButton) findViewById(R.id.coaching);
        final ImageButton bilanButton = (ImageButton) findViewById(R.id.bilan);
        final ImageButton ProfilButton = (ImageButton) findViewById(R.id.profil);
        final ImageButton parametresButton = (ImageButton) findViewById(R.id.parametres);

        final Button saveButton = (Button) findViewById(R.id.buttonsave);
        final Button helpButton = (Button) findViewById(R.id.buttonaide);
        final Button personnaliserButton = (Button) findViewById(R.id.buttonperso);
        Spinner spinnermorpho = (Spinner) findViewById(R.id.spinnermorpho);
        Spinner spinneractivite = (Spinner) findViewById(R.id.spinneractivite);
        Spinner spinnerlangue = (Spinner) findViewById(R.id.spinnerlangue);
        final UserBDD userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        final String mail=intent.getStringExtra(EXTRA_LOGIN);

        User u = new User();
        try {
            u = userBdd.getUserWithMail(mail);
        } catch (Exception e) {

        }
        if (u != null) {
            //requete avec mail=mail intent
        }

        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ParametresActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });
    }

}

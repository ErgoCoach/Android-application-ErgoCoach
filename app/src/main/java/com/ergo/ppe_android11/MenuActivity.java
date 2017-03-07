package com.ergo.ppe_android11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private String EXTRA_LOGIN = "undefined login";
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //BOUTONS MENU
        final ImageButton exitButton = (ImageButton) findViewById(R.id.exit);
        final ImageButton parametresButton = (ImageButton) findViewById(R.id.parametres);
        final ImageButton coachingButton = (ImageButton) findViewById(R.id.coaching);
        final ImageButton ProfilButton = (ImageButton) findViewById(R.id.profil);
        final ImageButton BilanButton = (ImageButton) findViewById(R.id.bilan);
        final ImageButton evolutionButton = (ImageButton) findViewById(R.id.evolution);
        final ImageButton tempsreelButton = (ImageButton) findViewById(R.id.reel);
        final ImageButton infoButton = (ImageButton) findViewById(R.id.info);

        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);

        BilanButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentB = new Intent(MenuActivity.this, BilansActivity.class);
                intentB.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentB);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentX = new Intent(MenuActivity.this, LoginActivity.class);
                intentX.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentX);
            }
        });
        BilanButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentB = new Intent(MenuActivity.this, BilansActivity.class);
                intentB.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentB);
            }
        });
        /*evolutionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentX = new Intent(MenuActivity.this, EvolutionActivity.class);
                intentX.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentX);
            }
        });*/
        tempsreelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentX = new Intent(MenuActivity.this, TempsReelActivity.class);
                intentX.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentX);
            }
        });
        /*infoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentX = new Intent(MenuActivity.this, InfoActivity.class);
                intentX.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentX);
            }
        });*/
        parametresButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentP = new Intent(MenuActivity.this, ParametresActivity.class);
                intentP.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentP);
            }
        });
        coachingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentC = new Intent(MenuActivity.this, CoachingActivity.class);
                intentC.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentC);
            }
        });
        ProfilButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentP = new Intent(MenuActivity.this, ProfilActivity.class);
                intentP.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentP);
            }
        });
    }
}

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
    UserBDD userBdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //BOUTONS MENU
        final ImageButton exitButton = (ImageButton) findViewById(R.id.exit);
        final ImageButton parametresButton = (ImageButton) findViewById(R.id.parametres);
        final ImageButton ProfilButton = (ImageButton) findViewById(R.id.profil);
        final ImageButton evolutionButton = (ImageButton) findViewById(R.id.evolution);
        final ImageButton tempsreelButton = (ImageButton) findViewById(R.id.reel);
        final ImageButton infoButton = (ImageButton) findViewById(R.id.info);

        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        userBdd = new UserBDD(this);
        userBdd.open();
        //userBdd.updateUserDate(mail,"1/3/2017");

       // userBdd.insertMesure(new Mesure("6/3/2017","zzsophie@free.fr",10,20,30,40,50,60,70,80,90 ));
       // userBdd.insertMesure(new Mesure("7/3/2017","zzsophie@free.fr",19,29,39,49,59,69,79,89,99 ));
       // userBdd.insertMesure(new Mesure("5/3/2017","zzsophie@free.fr",15,25,35,45,55,65,75,85,95 ));


        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentX = new Intent(MenuActivity.this, LoginActivity.class);
                intentX.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentX);
            }
        });

        evolutionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentX = new Intent(MenuActivity.this, EvolutionActivity.class);
                intentX.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentX);
            }
        });
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
        ProfilButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentP = new Intent(MenuActivity.this, ProfilActivity.class);
                intentP.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentP);
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentI = new Intent(MenuActivity.this, infoErgonomieActivity.class);
                intentI.putExtra(EXTRA_LOGIN, mail);
                startActivity(intentI);
            }
        });
    }
}

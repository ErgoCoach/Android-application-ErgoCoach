package com.ergo.ppe_android11;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class infoErgonomieActivity extends AppCompatActivity {

    UserBDD userBdd;
    String mail;
    private final String EXTRA_LOGIN = "undefined login";
    private static final String TAG = "Temps réel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ergonomie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        User currentUser = new User();

        try {
            currentUser = userBdd.getUserWithMail(mail);
        } catch (Exception e) {

        }
        final int idUser = currentUser.getId();

        //Bouton retour
        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(infoErgonomieActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });

        final VideoView videoview = (VideoView) findViewById(R.id.video);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ergo_video);
        videoview.setVideoURI(uri);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoview.start();
            }
        });

    }


}

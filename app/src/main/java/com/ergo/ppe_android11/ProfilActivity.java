package com.ergo.ppe_android11;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class ProfilActivity extends AppCompatActivity {

    private final String EXTRA_LOGIN = "undefined login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final UserBDD userBdd = new UserBDD(this);
        TextView loginDisplay = (TextView) findViewById(R.id.mail);
        TextView passwordDisplay = (TextView) findViewById(R.id.pass);
        TextView prenomDisplay = (TextView) findViewById(R.id.prenomview);
        TextView birthDisplay = (TextView) findViewById(R.id.birthview);
        TextView signupdateDisplay = (TextView) findViewById(R.id.signupdateview);

        userBdd.open();
        Intent intent = getIntent();
        final String mail=intent.getStringExtra(EXTRA_LOGIN);
        //userBdd.removeAll();
        User u = new User();
        try {
            u = userBdd.getUserWithMail(mail);
        }catch (Exception e){

        }
        if(u != null){

            loginDisplay.setText(mail);
            passwordDisplay.setText(u.getPassword());
            prenomDisplay.setText(u.getName());
            birthDisplay.setText(valueOf(u.getBirthyear()));
            signupdateDisplay.setText(u.getSignUpDate());

        }
        final ImageView mimageView  = (ImageView) findViewById(R.id.imageView);

        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.person)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 300, 300, mpaint);// Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);



        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfilActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });


    }

}

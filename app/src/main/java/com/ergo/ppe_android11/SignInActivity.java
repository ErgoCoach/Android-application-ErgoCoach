package com.ergo.ppe_android11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final Button signinButton = (Button) findViewById(R.id.signin);
        final Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(intent);
            }


        });

        signinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentI = new Intent(SignInActivity.this, InscriptionActivity.class);
                startActivity(intentI);
            }


        });
    }
}

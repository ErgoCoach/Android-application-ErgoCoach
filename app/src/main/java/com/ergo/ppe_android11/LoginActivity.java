package com.ergo.ppe_android11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private final String EXTRA_LOGIN = "undefined login";
    private final String EXTRA_PASSWORD = "undefined password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText login = (EditText) findViewById(R.id.email);
        final EditText pass = (EditText) findViewById(R.id.password);
        final UserBDD userBdd = new UserBDD(this);
        userBdd.open();
       // userBdd.removeAll();
       // userBdd.insertUser(new User("zzsophie@free.fr","soso","mdp",1995,"02032017"));
        //RETOUR A LA PAGE D ACCUEIL
        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirst);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);

                startActivity(intent);
            }


        });

        //BOUTON POUR SE CONNECTER A SON PROFIL
        final Button loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                final String loginTxt = login.getText().toString();
                final String passTxt = pass.getText().toString();

                // On verifie que le login est une adresse mail donc on déclare le pattern à vérifier
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                // On déclare un matcher, qui comparera le pattern avec la
                // string passée en argument
                Matcher m = p.matcher(loginTxt);
                // Si l’adresse mail saisie ne correspond au format d’une
                // adresse mail on un affiche un message à l'utilisateur
                if (!m.matches()) {
                    // Toast est une classe fournie par le SDK Android
                    // pour afficher les messages (indications) à l'intention de
                    // l'utilisateur. Ces messages ne possédent pas d'interaction avec l'utilisateur
                    // Le premier argument représente le contexte, puis
                    // le message et à la fin la durée d'affichage du Toast (constante
                    // LENGTH_SHORT ou LENGTH_LONG). Sans oublier d'appeler la méthode
                    //show pour afficher le Toast
                    Toast.makeText(LoginActivity.this, R.string.email,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loginTxt.equals("") || passTxt.equals("")) {
                    Toast.makeText(LoginActivity.this,
                            R.string.cvide,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

                intent.putExtra(EXTRA_LOGIN, loginTxt);
                intent.putExtra(EXTRA_PASSWORD, passTxt);
                if (userBdd.getUserWithMail(loginTxt)!=null && userBdd.getUserWithMail(loginTxt).getPassword().equals(passTxt)) {//si le compte existe et que le mot de passe est bon
                    startActivity(intent); //zzsophie@free.fr mdp
                }else{
                    Toast.makeText(LoginActivity.this, "Compte non existant ou mot de passe incorrect ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }


        });
    }

}

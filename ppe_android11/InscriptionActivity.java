package com.ergo.ppe_android11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
/*import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;*/

public class InscriptionActivity extends AppCompatActivity {


    private final String EXTRA_LOGIN = "undefined login";
    private final String EXTRA_PASSWORD = "undefined password";
    private final String EXTRA_PRENOM = "undefined first name";
    private final String EXTRA_BIRTH = "undefined year of birth";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        final UserBDD userBdd = new UserBDD(this);
        final EditText login = (EditText) findViewById(R.id.editmail);
        final EditText pass = (EditText) findViewById(R.id.editpass);
        final EditText passconfirm = (EditText) findViewById(R.id.editpassconfirm);
        final EditText prenom = (EditText) findViewById(R.id.editprenom);
        final EditText birth = (EditText) findViewById(R.id.editbirth);
        final Spinner morphoins = (Spinner) findViewById(R.id.spinnermorphoins);
        final Button loginButton = (Button) findViewById(R.id.buttonok);

        final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        final String date = sdf.format(new Date());             //date du jour

        userBdd.open();

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String loginTxt = login.getText().toString();
                final String passTxt = pass.getText().toString();
                final String prenomTxt = prenom.getText().toString();
                final String birthTxt = birth.getText().toString();
                final String passTxtconfirm = passconfirm.getText().toString();
                int birthInt=0;
                if(!birthTxt.equals("")){birthInt=parseInt(birthTxt);}

                // On déclare le pattern que l’on doit vérifier
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
                    Toast.makeText(InscriptionActivity.this, R.string.incorrect,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (valueOf(birthTxt).equals("") ) {
                    Toast.makeText(InscriptionActivity.this,
                            R.string.bvide,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passTxt.equals("")) {
                    Toast.makeText(InscriptionActivity.this,
                            R.string.pwvide,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passTxtconfirm.equals("")) {
                    Toast.makeText(InscriptionActivity.this,
                            R.string.cpwvide,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passTxtconfirm.equals(passTxt)){
                    Toast.makeText(InscriptionActivity.this,
                            R.string.cpwdifferent,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loginTxt.equals("") ) {
                    Toast.makeText(InscriptionActivity.this,
                            R.string.evide,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (prenomTxt.equals("")) {
                    Toast.makeText(InscriptionActivity.this,
                            R.string.pvide,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userBdd.getUserWithMail(loginTxt)!=null) {
                    Toast.makeText(InscriptionActivity.this,
                            "Un compte existe déjà avec cet email",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((birthInt > 2018)|| birthInt <1901 ){ //on verifie que la date de naissance est coherente
                    Toast.makeText(InscriptionActivity.this,
                            R.string.ageincorrect,
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                //On insère le livre que l'on vient de créer dans la bdd
                User user = new User(loginTxt,prenomTxt,passTxt,birthInt,date);
                userBdd.insertUser(user);
                Intent intent = new Intent(InscriptionActivity.this, MenuActivity.class);

                intent.putExtra(EXTRA_LOGIN, loginTxt);
                intent.putExtra(EXTRA_PASSWORD, passTxt);
                intent.putExtra(EXTRA_PRENOM, prenomTxt);
                intent.putExtra(EXTRA_BIRTH, birthTxt);


                startActivity(intent);

            }

        });
        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InscriptionActivity.this, SignInActivity.class);

                startActivity(intent);
            }


        });

    }
}

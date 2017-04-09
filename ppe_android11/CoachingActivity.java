package com.ergo.ppe_android11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.valueOf;

public class CoachingActivity extends AppCompatActivity {

    private final String EXTRA_LOGIN = "undefined login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaching);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageButton exitButton = (ImageButton) findViewById(R.id.exit);
        final ImageButton parametresButton = (ImageButton) findViewById(R.id.parametres);
        final ImageButton ProfilButton = (ImageButton) findViewById(R.id.profil);
        final TextView textview = (TextView) findViewById(R.id.titrecoachingdujour); //Coaching du : +date
        final TextView textviewcoaching = (TextView) findViewById(R.id.coachingdujour); //blabla coaching
        final CalendarView calendarCoaching = (CalendarView) findViewById(R.id.calendarCoaching);


        final UserBDD userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        final String mail = intent.getStringExtra(EXTRA_LOGIN);
        User currentUser = new User();
        try {
            currentUser = userBdd.getUserWithMail(mail);
        } catch (Exception e) {

        }

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String currentDate = sdf.format(new Date());             //date du jour

        final SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
        final String currentDate2 = sdf2.format(new Date());             //date du jour sans slash

        textview.setText("Bilan du : " + currentDate);

        final User finalCurrentUser = currentUser;
        calendarCoaching.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { //récupère la valeur du jour sélectionné

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String dt;
                Date ddt = new Date();
                Date ddt2 = new Date();
                Date today = new Date();
                Date SignUpDate = new Date();
                textview.setText("Coaching du : " + dayOfMonth + " / " + (month + 1) + " / " + year); //assuming you want 1..12, month return value is zero-based!
                dt = dayOfMonth + "/" + (month + 1) + "/" + year;
                try {
                    ddt = sdf.parse(dt); //conversion String to date : date demandée au format dd/MM/yyyy
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    SignUpDate = sdf2.parse(finalCurrentUser.getSignUpDate());//date d'inscription au format ddMMyyyy
                    ddt2 = sdf.parse(dt); //date demandée au format ddMMyyyy
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (ddt.compareTo(today) < 0) { //si date demandée < date actuelle (passé) et >= date inscription)
                    if (ddt2.compareTo(SignUpDate) >= 0) {
                        Mesure currentMesure = userBdd.getMesureWithMailAndDate(mail, dt);

                        if (currentMesure != null) {

                            String CoachingMessage = creerCoachingCorrespondantMesure(currentMesure);
                            textviewcoaching.setText(CoachingMessage);

                        } else {
                            textviewcoaching.setText("Pas de valeur enregistrée");
                        }
                    } else {
                        textviewcoaching.setText("Pas de valeur enregistrée avant votre date d'inscription : " + finalCurrentUser.getSignUpDate());
                    }

                } else {
                    textviewcoaching.setText("Date à venir : pas encore de valeur enregistrée" + finalCurrentUser.getSignUpDate());
                }
            }
        });

        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CoachingActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });
    }
    public String creerCoachingCorrespondantMesure(Mesure mesure){
        String message=" ";

            message = message+"Rapport Assise arriere/avant = "+mesure.getAssise_arriere()*100/(mesure.getAssise_avant()+mesure.getAssise_arriere())+" % arriere | "+mesure.getAssise_avant()*100/(mesure.getAssise_avant()+mesure.getAssise_arriere())+" % droite \n";
            message = message+"Rapport Assise gauche/droite = "+mesure.getAssise_gauche()*100/(mesure.getAssise_gauche()+mesure.getAssise_droite())+" % gauche | "+mesure.getAssise_droite()*100/(mesure.getAssise_gauche()+mesure.getAssise_droite())+" % droite \n";
            message = message+"Rapport Dos milieu-gauche/milieu-droite = "+mesure.getDos_milieu_gauche()*100/(mesure.getDos_milieu_gauche()+mesure.getDos_milieu_droite())+" % gauche | "+mesure.getDos_milieu_droite()*100/(mesure.getDos_milieu_droite()+mesure.getDos_milieu_gauche())+" % droite \n";
    //}
        return message;
    }

}

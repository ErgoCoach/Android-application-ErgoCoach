package com.ergo.ppe_android11;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public class BilansActivity extends AppCompatActivity {

    private final String EXTRA_LOGIN = "undefined login";
   /* private GoogleApiClient client;
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;
    BluetoothSocket mSocket;
    Button startButton;
    Button stopButton;
    Button communicationButton;
    final int MESSAGE_READ = 1;
    String transfert = "0";
    ListView communication;
    TextView connectionStatut;
    ConnectThread mConnectThread;
    ConnectedThread mConnectedThread;*/
    int capteur_dos_haut;
    int capteur_dos_milieu_droite;
    int capteur_dos_bas;
    int capteur_dos_milieu_gauche;
    int capteur_assise_gauche;
    int capteur_assise_droite;
    int capteur_assise_avant;
    int capteur_assise_arriere;
    UserBDD userBdd;
    String mail;
    TextView textview ;
    TextView textviewbilan ;
    TextView textviewhautdroite;
     TextView textviewhautgauche;
      TextView textviewmilieugauche;
      TextView textviewmilieudroite;
      TextView textviewbas;
    //CAPTEURS ASSISE
      TextView textviewarriere;
      TextView textviewavant;
      TextView textviewgauche;
      TextView textviewdroite;
    Mesure mesuretemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //BOUTONS MENU
        final ImageButton exitButton = (ImageButton) findViewById(R.id.exit);
        final ImageButton parametresButton = (ImageButton) findViewById(R.id.parametres);
        final ImageButton ProfilButton = (ImageButton) findViewById(R.id.profil);


        final CalendarView calendarBilan = (CalendarView) findViewById(R.id.calendarBilan);
        final TextView textview = (TextView) findViewById(R.id.titrebilandujour); //Bilan du : +date
        final TextView textviewbilan = (TextView) findViewById(R.id.bilandujour); //blabla bilans
       // final TextView textviewasup = (TextView) findViewById(R.id.asup); //blabla bilans
        //CAPTEURS DOSSIER
          textviewhautdroite = (TextView) findViewById(R.id.hd);
            textviewhautgauche = (TextView) findViewById(R.id.hg);
            textviewmilieugauche = (TextView) findViewById(R.id.mg);
            textviewmilieudroite = (TextView) findViewById(R.id.md);
            textviewbas= (TextView) findViewById(R.id.b);
        //CAPTEURS ASSISE
            textviewarriere= (TextView) findViewById(R.id.arriere);
            textviewavant= (TextView) findViewById(R.id.avant);
            textviewgauche= (TextView) findViewById(R.id.gauche);
            textviewdroite= (TextView) findViewById(R.id.droite);

        capteur_dos_haut=0;
        capteur_dos_milieu_droite=0;
        capteur_dos_bas=0;
        capteur_dos_milieu_gauche=0;
        capteur_assise_gauche=0;
        capteur_assise_droite=0;
        capteur_assise_avant=0;
        capteur_assise_arriere=0;
        mesuretemp = new Mesure();


        userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        final User currentUser = userBdd.getUserWithMail(mail);


        //userBdd.updateMesure(((userBdd.getAllMesures().size())),new Mesure("7/3/2017","zzsophie@free.fr",10,10,50,50,40,70,2,34,36 ));
        //textviewasup.setText(userBdd.getMesureWithId(0).getMailuser()+" instances de mesure dans la BDD");
        //textviewasup.setText(String.valueOf(userBdd.getAllMesures().size()) +" instances de mesure dans la BDD, id instance:" + userBdd.getMesureWithId(userBdd.getAllMesures().size()).toString()+" ____________________"+userBdd.getMesureWithId((userBdd.getAllMesures().size())-1).toString() +" ____________________"+userBdd.getMesureWithId((userBdd.getAllMesures().size())-2).toString());
        //textviewasup.setText(String.valueOf(userBdd.getAllMesures().size()) +" instances de mesure dans la BDD");

        String datetemp;
        Date datetemp2 = new Date() ;



        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String currentDate = sdf.format(new Date());             //date du jour
        
        final SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
        final String currentDate2 = sdf2.format(new Date());             //date du jour

        textview.setText("Bilan du : " + currentDate);
        Mesure currentMesure0 =userBdd.getMesureWithMailAndDate(mail,currentDate);
        textviewbilan.setText(" ");

        final User finalCurrentUser = currentUser; //variable finale intermediaire obligatoire car sans elle, on a une erreur "accessed from inner class needs to be declar final
        calendarBilan.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { //récupère la valeur du jour sélectionné

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String dt;
                Date ddt = new Date();
                Date ddt2 = new Date();
                Date today = new Date();
                Date SignUpDate = new Date();
                textview.setText("Bilan du : " + dayOfMonth + " / " + (month + 1) + " / " + year); //assuming you want 1..12, month return value is zero-based!
                dt = dayOfMonth + "/" + (month + 1) + "/" + year;
                Mesure currentMesure =userBdd.getMesureWithMailAndDate(mail,dt);
                try {
                    ddt= sdf.parse(dt); //conversion String to date : date demandée au format dd/MM/yyyy
                    SignUpDate = sdf.parse(finalCurrentUser.getSignUpDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(ddt.compareTo(today)<0 ){ //si date demandée < date actuelle (passé) et >= date inscription)
                    if(ddt.compareTo(SignUpDate)>=0){

                        if(currentMesure!=null){
                            textviewbilan.setText(" ");
                            updateModel(currentMesure);
                        }else{
                            textviewbilan.setText("Pas de valeur enregistrée");
                            updateModel(new Mesure("","",-1,-1,-1,-1,-1,-1,-1,-1)); //noir
                        }
                    }else{
                        textviewbilan.setText("Pas de valeur enregistrée avant votre date d'inscription : "+SignUpDate);
                        updateModel(new Mesure("","",-1,-1,-1,-1,-1,-1,-1,-1)); //noir
                    }

                }else{
                    textviewbilan.setText("Date à venir : pas encore de valeur enregistrée");
                    updateModel(new Mesure("","",-1,-1,-1,-1,-1,-1,-1,-1)); //noir
                }
            }
        });


        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BilansActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });


        /* //AFFECTER LES VALEURS AUX CAPTEURS
        capteur_dos_haut=;
        capteur_dos_milieu_droite=;
        capteur_dos_bas=;
        capteur_dos_milieu_gauche=;
        capteur_assise_gauche=;
        capteur_assise_droite=;
        capteur_assise_avant=;
        capteur_assise_arriere=;*/

    }

    //METTRE A JOUR LA COULEUR DES CAPTEURS
    public void updateModel(Mesure mesure){

        capteur_assise_arriere= mesure.getAssise_arriere();
        capteur_assise_avant= mesure.getAssise_avant();
        capteur_assise_gauche= mesure.getAssise_gauche();
        capteur_assise_droite= mesure.getAssise_droite();

        capteur_dos_bas=mesure.getDos_bas();
        capteur_dos_haut= mesure.getDos_haut();
        capteur_dos_milieu_gauche= mesure.getDos_milieu_gauche();
        capteur_dos_milieu_droite= mesure.getDos_milieu_droite();

        int capteurs[] = {capteur_assise_arriere,capteur_assise_avant,capteur_assise_droite,capteur_assise_gauche,capteur_dos_bas,capteur_dos_haut,capteur_dos_milieu_droite,capteur_dos_milieu_gauche};
        TextView textview[]={textviewarriere,textviewavant,textviewdroite,textviewgauche,textviewbas,textviewhautdroite,textviewhautgauche,textviewmilieudroite,textviewmilieugauche};
        for(int x=0;x<9;x++){
                if(capteurs[x]>90 && capteurs[x]<=100){
                    textview[x].setBackgroundColor(Color.argb(120,200,0,0)); //bordeau foncé
                }else if(capteurs[x]>80 && capteurs[x]<=90){
                    textview[x].setBackgroundColor(Color.argb(120,220,0,0)); //bordeau clair
                }else if(capteurs[x]>70 && capteurs[x]<=80){
                    textview[x].setBackgroundColor(Color.argb(120,255,0,0)); //rouge pur
                }else if(capteurs[x]>60 && capteurs[x]<=70){
                    textview[x].setBackgroundColor(Color.argb(120,255,40,0)); //rouge orangé
                }else if(capteurs[x]>50 && capteurs[x]<=60){
                    textview[x].setBackgroundColor(Color.argb(120,255,80,0)); //rouge très orangé
                }else if(capteurs[x]>40 && capteurs[x]<=50){
                    textview[x].setBackgroundColor(Color.argb(120,255,120,0)); //orange pur a 255,128,0
                }else if(capteurs[x]>30 && capteurs[x]<=40){
                    textview[x].setBackgroundColor(Color.argb(120,255,160,0)); //orange clair
                }else if(capteurs[x]>20 && capteurs[x]<=30){
                    textview[x].setBackgroundColor(Color.argb(120,255,200,0)); //jaune orangé
                }else if(capteurs[x]>10 && capteurs[x]<=20){
                    textview[x].setBackgroundColor(Color.argb(120,255,240,0)); //jaune orangé très jaune
                }else if(capteurs[x]>=0 && capteurs[x]<=10){
                    textview[x].setBackgroundColor(Color.argb(120,255,255,0)); //jaune pur
                }else {
                    textview[x].setBackgroundColor(Color.rgb(0, 0, 0)); //noir
                }//              }

        }
    }

}

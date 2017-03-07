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
    int capteur_dos_haut_droite;
    int capteur_dos_milieu_droite;
    int capteur_dos_bas;
    int capteur_dos_milieu_gauche;
    int capteur_dos_haut_gauche;
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
        final ImageButton coachingButton = (ImageButton) findViewById(R.id.coaching);
        final ImageButton ProfilButton = (ImageButton) findViewById(R.id.profil);
        final ImageButton BilanButton = (ImageButton) findViewById(R.id.bilan);


        final CalendarView calendarBilan = (CalendarView) findViewById(R.id.calendarBilan);
        final TextView textview = (TextView) findViewById(R.id.titrebilandujour); //Bilan du : +date
        final TextView textviewbilan = (TextView) findViewById(R.id.bilandujour); //blabla bilans
        final TextView textviewasup = (TextView) findViewById(R.id.asup); //blabla bilans
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

        capteur_dos_haut_droite=0;
        capteur_dos_milieu_droite=0;
        capteur_dos_bas=0;
        capteur_dos_milieu_gauche=0;
        capteur_dos_haut_gauche=0;
        capteur_assise_gauche=0;
        capteur_assise_droite=0;
        capteur_assise_avant=0;
        capteur_assise_arriere=0;
        mesuretemp = new Mesure();


        userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        User currentUser = new User();
        try {
            currentUser = userBdd.getUserWithMail(mail);
        } catch (Exception e) {

        }

        //userBdd.insertMesure(new Mesure("20:30 06/03/2017","sophiezecri@gmil.com",10,20,30,40,50,60,70,80,90 ));
        //userBdd.updateMesure(11,new Mesure("22:30 06/03/2017","zzsophie@free.fr",10,20,30,40,50,60,70,80,90 )); userBdd.getMesureWithId(0).toString()
        //textviewasup.setText(userBdd.getMesureWithId(0).getMailuser()+" instances de mesure dans la BDD");
        textviewasup.setText(String.valueOf(userBdd.getAllMesures().size()) +" instances de mesure dans la BDD, id instance:" + userBdd.getMesureWithMail("sophiezecri@gmil.com").toString());

        /*communication.setAdapter(arrayAdapter);
        communication.setTextFilterEnabled(true);
        connectionStatut = (TextView) findViewById(R.id.connectStatut);

        // Verifier si notre téléphone supporte le Bluetooth
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Pas de Bluetooth
            connectionStatut.setText("Bluetooth non disponible");
        }

        // Activer le Bluetooth si ce n'est pas fait
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        // Récupérer les appareils associés
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName().equals("HC-05")) {
                    mDevice = device;
                    break;
                }
            }
        }

        // Lorsqu'on appuie sur le bouton pour la connexion
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(connectionStatut.getText() != "Connecté au module HC-05") {
                    // Etablir la connexion en appellant la classe interne
                    mConnectThread = new ConnectThread(mDevice);
                    mConnectThread.start();
                    connectionStatut.setText("Connecté au module HC-05");
                }
            }
        });

        // Lorsqu'on appuie sur le bouton pour la déconnexion
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(connectionStatut.getText() == "Connecté au module HC-05") {
                    // Faire la déconnexion en appellant la classe interne

                    connectionStatut.setText("Déconnecté du module HC-05");
                }
            }
        });

        // Lorsqu'on appuie sur le bouton pour la communication
        communicationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (connectionStatut.getText() == "Connecté au module HC-05") {
                    // Si le transfert n'a pas commencé
                    if (transfert == "0") {
                        transfert = "1";
                        arrayAdapter.add("Envoyer les données");
                        mConnectedThread.write(transfert);
                    }

                    // Si on est en plein transfert
                    else {
                        arrayAdapter.add("Arrêter les données");
                        transfert = "0";
                        mConnectedThread.write(transfert);
                    }
                }
            }
        });*/

        String datetemp;
        Date datetemp2 = new Date() ;



        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String currentDate = sdf.format(new Date());             //date du jour
        
        final SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
        final String currentDate2 = sdf2.format(new Date());             //date du jour

        textview.setText("Bilan du : " + currentDate);

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
                try {
                    ddt= sdf.parse(dt); //date du jour au format dd/MM/yyyy
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    SignUpDate = sdf2.parse(finalCurrentUser.getSignUpDate());//date d'inscription au format ddMMyyyy
                    ddt2 = sdf.parse(dt); //date du jour au format ddMMyyyy
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(ddt.compareTo(today)<0 ){ //si date demandée < date actuelle (passé) et >= date inscription)
                    if(ddt2.compareTo(SignUpDate)>=0){
                        textviewbilan.setText("Nous recherchons dans la base de données vos valeurs enregistrées");
                    }else{
                        textviewbilan.setText("Pas de valeur enregistrée avant votre date d'inscription");
                    }

                }else{
                    textviewbilan.setText("Date à venir : pas encore de valeur enregistrée");
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
        capteur_dos_haut_droite=;
        capteur_dos_milieu_droite=;
        capteur_dos_bas=;
        capteur_dos_milieu_gauche=;
        capteur_dos_haut_gauche=;
        capteur_assise_gauche=;
        capteur_assise_droite=;
        capteur_assise_avant=;
        capteur_assise_arriere=;*/


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
/*
    *//**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *//*
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Bilans Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    // Classe interne pour la connexion entre l'Arduino et le téléphone
    private class ConnectThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        // Transfère les données en série : un byte à la fois
        private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {
            mBluetoothAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
                mConnectedThread = new ConnectedThread(mmSocket);
                mConnectedThread.start();
            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }
        }

        public void cancel() {
            try {
                mSocket.close();
            } catch (IOException e) { }
        }
    }

    // Classe interne pour la communication
    private class ConnectedThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        // Créer différents stream pour la réception et l'envoi
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int begin = 0;
            int bytes = 0;

            while (true) {
                try {
                    bytes += mmInStream.read(buffer, bytes, buffer.length - bytes);
                    for(int i = begin; i < bytes; i++) {
                        if(buffer[i] == "#".getBytes()[0]) {
                            mHandler.obtainMessage(MESSAGE_READ, begin, i, buffer).sendToTarget();
                            begin = i + 1;
                            if(i == bytes - 1) {
                                bytes = 0;
                                begin = 0;
                            }
                        }
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }

        public void write(String msg) {
            try {
                mmOutStream.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] writeBuf = (byte[]) msg.obj;
            int begin = (int)msg.arg1;
            int end = (int)msg.arg2;

            switch(msg.what) {
                case MESSAGE_READ:
                    String writeMessage = new String(writeBuf);
                    writeMessage = writeMessage.substring(begin, end);
                    arrayAdapter.add("Arduino: " + writeMessage);

                    //AFFECTER LES VALEURS AUX CAPTEURS
                    switch (writeMessage.substring(0,1)){
                        case "v1" :
                            capteur_dos_haut_droite=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setDos_haut_droite(capteur_dos_haut_droite);
                            break;
                        case "v2" :
                            capteur_dos_haut_gauche=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setDos_haut_gauche(capteur_dos_haut_gauche);
                            break;
                        case "v3" :
                            capteur_dos_milieu_droite=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setDos_milieu_droite(capteur_dos_milieu_droite);
                            break;
                        case "v4" :
                            capteur_dos_milieu_gauche=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setDos_milieu_gauche(capteur_dos_milieu_gauche);
                            break;
                        case "v5" :
                            capteur_dos_bas=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setDos_bas(capteur_dos_bas);
                            break;
                        case "v6" :
                            capteur_assise_avant=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setAssise_avant(capteur_assise_avant);
                            break;
                        case "v7" :
                            capteur_assise_arriere=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setAssise_arriere(capteur_assise_arriere);
                            break;
                        case "v8" :
                        capteur_assise_droite=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setAssise_droite(capteur_assise_droite);
                            break;
                        case "v9" :
                        capteur_assise_gauche=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                            mesuretemp.setAssise_gauche(capteur_assise_gauche);
                            final SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
                            mesuretemp.setDate(sdf2.format(new Date()));
                            mesuretemp.setMailuser(mail);
                            userBdd.insertMesure(mesuretemp);

                            break;
                    }

                break;

            }

        }
    };*/
    //METTRE A JOUR LA COULEUR DES CAPTEURS
    public void updateModel(int id){

        capteur_assise_arriere= userBdd.getMesureWithId(id).getAssise_arriere();
        capteur_assise_avant= userBdd.getMesureWithId(id).getAssise_avant();
        capteur_assise_gauche= userBdd.getMesureWithId(id).getAssise_gauche();
        capteur_assise_droite= userBdd.getMesureWithId(id).getAssise_droite();

        capteur_dos_bas= userBdd.getMesureWithId(id).getDos_bas();
        capteur_dos_haut_droite= userBdd.getMesureWithId(id).getDos_haut_droite();
        capteur_dos_haut_gauche= userBdd.getMesureWithId(id).getDos_haut_gauche();
        capteur_dos_milieu_gauche= userBdd.getMesureWithId(id).getDos_milieu_gauche();
        capteur_dos_milieu_droite= userBdd.getMesureWithId(id).getDos_milieu_droite();

        int capteurs[] = {capteur_assise_arriere,capteur_assise_avant,capteur_assise_droite,capteur_assise_gauche,capteur_dos_bas,capteur_dos_haut_droite,capteur_dos_haut_gauche,capteur_dos_milieu_droite,capteur_dos_milieu_gauche};
        for(int x : capteurs){
            if(x!=0){
                if(x>90 && x<=100){
                    textviewarriere.setBackgroundColor(Color.argb(120,200,0,0)); //bordeau foncé
                }else if(x>80 && x<=90){
                    textviewarriere.setBackgroundColor(Color.argb(120,220,0,0)); //bordeau clair
                }else if(x>70 && x<=80){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,0,0)); //rouge pur
                }else if(x>60 && x<=70){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,40,0)); //rouge orangé
                }else if(x>50 && x<=60){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,80,0)); //rouge très orangé
                }else if(x>40 && x<=50){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,120,0)); //orange pur a 255,128,0
                }else if(x>30 && x<=40){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,160,0)); //orange clair
                }else if(x>20 && x<=30){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,200,0)); //jaune orangé
                }else if(x>10 && x<=20){
                    textviewarriere.setBackgroundColor(Color.argb(120,255,240,0)); //jaune orangé très jaune
                }else{
                    textviewarriere.setBackgroundColor(Color.argb(120,255,255,0)); //jaune pur
                }
            }

        }
    }

}

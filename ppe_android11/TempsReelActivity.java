package com.ergo.ppe_android11;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class TempsReelActivity extends AppCompatActivity {
    private GoogleApiClient client;
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
    ConnectedThread mConnectedThread;
    int capteur_dos_milieu_droite;
    int capteur_dos_bas;
    int capteur_dos_milieu_gauche;
    int capteur_dos_haut;
    int capteur_assise_gauche;
    int capteur_assise_droite;
    int capteur_assise_avant;
    int capteur_assise_arriere;
    TextView textviewhaut;
    TextView textviewmilieugauche;
    TextView textviewmilieudroite;
    TextView textviewbas;
    //CAPTEURS ASSISE
    TextView textviewarriere;
    TextView textviewavant;
    TextView textviewgauche;
    TextView textviewdroite;
    //SON
    ImageButton sound_image;
    final int ON = 1;
    final int OFF = 0;
    int sound = ON;
    //AFFICHAGE
    TextView affichage_titre_dos;
    TextView affichage_titre_assise;
    TextView affichage_instructions;
    GridLayout dos;
    GridLayout assise;
    UserBDD userBdd;
    String mail;
    int idUser ;
    Mesure mesuretemp;

    final int ROUGE = Color.rgb(198,8,0);
    final int ORANGE = Color.rgb(231, 62, 1);
    final int VERT = Color.rgb(127, 221, 76);
    final int JAUNEFONCE = Color.rgb(255, 215, 0);
    final int JAUNECLAIR = Color.rgb(255,255,107);
    final int BLEUCLAIR = Color.rgb(116, 208, 241);
    final int BLEUFONCE = Color.rgb(1, 49, 180);
    final int NOIR = Color.rgb(0, 0, 0);

    int compteur_rouge = 0;
    int compteur_orange = 0;
    int compteur_vert = 0;
    int compteur_noir = 0;
    int compteur_veryhappy = 0;
    int compteur_happy = 0;
    int compteur_middle = 0;
    int compteur_nothappy = 0;
    int compteur_nothappyatall = 0;
    String smiley_bd;

    int[] nb_posture;
    int compteur_very_bad_posture_intervalle;
    int compteur_bad_posture_intervalle;
    int compteur_middle_posture_intervalle;
    int compteur_good_posture_intervalle;
    int compteur_very_good_posture_intervalle;
    int compteur_bonne;
    int compteur_droite;
    int compteur_gauche;
    int compteur_affalé;
    int compteur_courbé;
    int compteur_no_contact_dossier;
    int compteur_temps;
    int[] compteur_intervalle = {compteur_very_bad_posture_intervalle,compteur_bad_posture_intervalle,compteur_middle_posture_intervalle,compteur_good_posture_intervalle, compteur_very_good_posture_intervalle };
    int compteur_posture_very_bad_posture_jour;
    int compteur_posture_bad_posture_jour;
    int compteur_middle_posture_jour;
    int compteur_good_posture_jour;
    int compteur_very_good_posture_jour;
    int[] compteur_jour = {compteur_posture_very_bad_posture_jour,compteur_posture_bad_posture_jour,compteur_middle_posture_jour,compteur_good_posture_jour, compteur_very_good_posture_jour };


    int i = 0;


    //Alerte "flottante"
    ImageView carre;
    ImageView smiley;
    TextView conseil;
    Drawable image_smiley;
    MediaPlayer beep;
    RelativeLayout.LayoutParams params;
    RelativeLayout affichage_alerte;

    private final String EXTRA_LOGIN = "undefined login";
    private static final String TAG = "Temps réel";
    private int nb_posture_la_plus_frequente;
    private String posture_la_plus_frequente;
    private String[] posture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temps_reel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        communication = (ListView) findViewById(R.id.listView);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        communicationButton = (Button) findViewById(R.id.commButton);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        communication.setAdapter(arrayAdapter);
        communication.setTextFilterEnabled(true);
        connectionStatut = (TextView) findViewById(R.id.connectStatut);

        //AFFICHAGE

        //Contenu flottant
        affichage_alerte = (RelativeLayout) findViewById(R.id.content_temps_reel);
        carre = new ImageView(this);
        carre.setBackgroundColor(Color.WHITE);
        carre.getBackground().setAlpha(180);
        carre.setVisibility(View.INVISIBLE);
        params = new RelativeLayout.LayoutParams(380, 380);
        params.leftMargin = 0;
        params.topMargin = 1375;
        affichage_alerte.addView(carre, params);

        //AJOUTER IMAGE EN QUESTION
        //Smiley flottant
        smiley = new ImageView(this);
        smiley.setVisibility(View.INVISIBLE);
        params = new RelativeLayout.LayoutParams(200, 200);
        params.leftMargin = 90;
        params.topMargin = 1400;
        affichage_alerte.addView(smiley, params);

        //Conseil Flottant
        conseil = new TextView(this);
        conseil.setVisibility(View.INVISIBLE);
        params = new RelativeLayout.LayoutParams(360, 145);
        params.leftMargin = 10;
        params.topMargin = 1600;
        affichage_alerte.addView(conseil, params);

        //Son mauvaise posture
        beep = MediaPlayer.create(this,R.raw.beep);

        affichage_titre_dos = (TextView) findViewById(R.id.titremodelisationdos);
        affichage_titre_assise = (TextView) findViewById(R.id.titremodelisation1);
        affichage_instructions = (TextView) findViewById(R.id.instructions);
        dos = (GridLayout) findViewById(R.id.modelisationdos);
        assise = (GridLayout) findViewById(R.id.modelisation1);
        //CAPTEURS DOSSIER
        textviewhaut = (TextView) findViewById(R.id.h);
        textviewmilieugauche = (TextView) findViewById(R.id.mg);
        textviewmilieudroite = (TextView) findViewById(R.id.md);
        textviewbas= (TextView) findViewById(R.id.b);
        //CAPTEURS ASSISE
        textviewarriere= (TextView) findViewById(R.id.arriere);
        textviewavant= (TextView) findViewById(R.id.avant);
        textviewgauche= (TextView) findViewById(R.id.gauche);
        textviewdroite= (TextView) findViewById(R.id.droite);

        nb_posture = new int[6];
        posture = new String[6];

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        User currentUser = new User();

        try {
            currentUser = userBdd.getUserWithMail(mail);
        } catch (Exception e) {

        }
        idUser = currentUser.getId();

        compteur_very_bad_posture_intervalle=0;
        compteur_bad_posture_intervalle=0;
        compteur_middle_posture_intervalle=0;
        compteur_good_posture_intervalle=0;
        compteur_very_good_posture_intervalle=0;
        compteur_temps=0;
        compteur_bonne=0;
        compteur_droite=0;
        compteur_gauche=0;
        compteur_affalé=0;
        compteur_courbé=0;
        compteur_no_contact_dossier=0;
        Day d = new Day();

        List<Day> list_day = userBdd.getAllDay();
        for(int i=0; i<list_day.size();i++){
            if(list_day.get(i).getCol_id_user()==idUser){
                 d = userBdd.getDayWithUserID(idUser);
            }else{
                 d = new Day(idUser,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null,
                null,null,null,null
                );
                userBdd.insertDay(d);
            }
        }


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
                    try {
                        mConnectThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    connectionStatut.setText("Connecté au module HC-05");
                    startButton.setVisibility(View.GONE);
                    communicationButton.setVisibility(View.VISIBLE);
                    affichage_instructions.setVisibility(View.VISIBLE);
                }
            }
        });

        // Lorsqu'on appuie sur le bouton pour la déconnexion
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*if(connectionStatut.getText() == "Connecté au module HC-05") {
                    // Faire la déconnexion en appellant la classe interne

                    connectionStatut.setText("Déconnecté du module HC-05");
                }*/
            }
        });

        // Lorsqu'on appuie sur le bouton pour la communication
        communicationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (connectionStatut.getText() == "Connecté au module HC-05") {
                    // Si le transfert n'a pas commencé
                    if (transfert == "0") {
                        //Afficher le décompte de 10 secondes avant de s'asseoir
                        new CountDownTimer(10000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                affichage_instructions.setText("Vous pouvez vous asseoir dans " + millisUntilFinished / 1000 + " sec");
                                communicationButton.setText("Initialisation du siège en cours...");
                            }
                            //Lorsque le décompte est fini, afficher l'analyse
                            public void onFinish() {

                                carre.setVisibility(View.VISIBLE);
                                smiley.setVisibility(View.VISIBLE);
                                conseil.setVisibility(View.VISIBLE);
                                affichage_instructions.setVisibility(View.GONE);
                                affichage_titre_dos.setVisibility(View.VISIBLE);
                                affichage_titre_assise.setVisibility(View.VISIBLE);
                                dos.setVisibility(View.VISIBLE);
                                assise.setVisibility(View.VISIBLE);

                                communicationButton.setText("Arrêter l'analyse en temps réel");

                                Compteur_smiley smiley = new Compteur_smiley();
                                new Thread(smiley).start();

                                Compteur_posture posture = new Compteur_posture();
                                new Thread(posture).start();
                            }
                        }.start();


                        //Envoyer les données
                        transfert = "1";
                        arrayAdapter.add("Envoyer les données");
                        mConnectedThread.write(transfert);

                        //Initialisation des capteurs Ã  0

                        updateModel(0, textviewavant);
                        updateModel(0, textviewdroite);
                        updateModel(0, textviewhaut);
                        updateModel(0, textviewmilieudroite);
                        updateModel(0, textviewarriere);
                        updateModel(0, textviewbas);
                        updateModel(0, textviewgauche);
                        updateModel(0, textviewmilieugauche);

                    }

                    // Si on est en plein transfert et qu'on clique pour arreter
                    else {
                        //Effacer les analyses
                        affichage_titre_dos.setVisibility(View.GONE);
                        affichage_titre_assise.setVisibility(View.GONE);
                        dos.setVisibility(View.GONE);
                        assise.setVisibility(View.GONE);
                        arrayAdapter.add("Arrêter les données");
                        //Arreter envoi des données
                        transfert = "0";
                        mConnectedThread.write(transfert);
                        communicationButton.setText("Commencer l'analyse en temps réel");
                        affichage_instructions.setText("Levez vous du siège");
                        affichage_instructions.setVisibility(View.VISIBLE);
                        carre.setVisibility(View.INVISIBLE);
                        smiley.setVisibility(View.INVISIBLE);
                        conseil.setVisibility(View.INVISIBLE);

                    }
                }
            }
        });

        //Activer le son ou non
        sound_image = (ImageButton) findViewById(R.id.volume);
        sound_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sound == OFF) {
                    sound = ON;
                    sound_image.setBackgroundResource(R.drawable.volume_up);
                }
                else {
                    sound = OFF;
                    sound_image.setBackgroundResource(R.drawable.volume_mute);
                }
            }
        });

        //Bouton retour
        final ImageButton backButton = (ImageButton) findViewById(R.id.backtofirsti);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TempsReelActivity.this, MenuActivity.class);
                intent.putExtra(EXTRA_LOGIN, mail);
                startActivity(intent);
            }


        });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
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
        // Transfère les données en série : un byte à  la fois
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
                    addValue(writeMessage);
                    break;

            }

        }
    };

    public void addValue(String writeMessage){
        //AFFECTER LES VALEURS AUX CAPTEURS
        switch (writeMessage.substring(0,2)) {

            case "v1" :
                capteur_assise_avant=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                Log.i(TAG, "AVANT : " + capteur_assise_avant);
                updateModel(capteur_assise_avant, textviewavant);
                break;
            case "v2" :
                capteur_assise_arriere=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                Log.i(TAG, "ARRIERE : " + capteur_assise_arriere);
                updateModel(capteur_assise_arriere, textviewarriere);
                break;
            case "v3" :
                capteur_assise_droite=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                Log.i(TAG, "ASSISE DROITE : " + capteur_assise_droite);
                updateModel(capteur_assise_droite, textviewdroite);
                break;

            case "v4" :
                capteur_assise_gauche=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                Log.i(TAG, "ASSISE GAUCHE: " + capteur_assise_gauche);
                updateModel(capteur_assise_gauche, textviewgauche);
                break;

            case "v5" :
                capteur_dos_bas=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                Log.i(TAG, "BAS : " + capteur_dos_bas);
                updateModel(capteur_dos_bas, textviewbas);
                break;

            case "v6":
                capteur_dos_haut = Integer.parseInt(writeMessage.substring(2, writeMessage.length()));
                Log.i(TAG, "HAUT  : " + capteur_dos_haut);
                updateModel(capteur_dos_haut, textviewhaut);
                break;

            case "v7" :
                capteur_dos_milieu_gauche=Integer.parseInt(writeMessage.substring(2,writeMessage.length()));
                Log.i(TAG, " GAUCHE : " + capteur_dos_milieu_gauche);
                updateModel(capteur_dos_milieu_gauche, textviewmilieugauche);
                break;

            case "v8":
                capteur_dos_milieu_droite = Integer.parseInt(writeMessage.substring(2, writeMessage.length()));
                Log.i(TAG, " DROITE : " + capteur_dos_milieu_droite);
                updateModel(capteur_dos_milieu_droite, textviewmilieudroite);
                updateConseil();
                update_popup();
                break;


        }
    } //sud avant nord arriere est gauche de nous ouest droite

    //METTRE A JOUR LA COULEUR DES CAPTEURS
    public void updateModel(int capteurs, TextView textview) {

        if(capteurs>80 && capteurs<=100){
            textview.setBackgroundColor(ROUGE);
            compteur_rouge = compteur_rouge + 1;
        }else if(capteurs>60 && capteurs<=80){
            textview.setBackgroundColor(ORANGE);
            compteur_orange = compteur_orange + 1;
        }else if(capteurs>40 && capteurs<=60){
            textview.setBackgroundColor(VERT);
            compteur_vert = compteur_vert + 1;
        }else if(capteurs>20 && capteurs<=40){
            textview.setBackgroundColor(JAUNEFONCE);
            //textview.setBackgroundColor(BLEUCLAIR);
        }else if(capteurs>0 && capteurs<=20){
            textview.setBackgroundColor(JAUNECLAIR);
            //textview.setBackgroundColor(BLEUFONCE);
        }else {
            textview.setBackgroundColor(NOIR);
            compteur_noir = compteur_noir + 1;
        }

    }

    public void updateConseil () {

        if( compteur_noir == 8  )
        {
            image_smiley = getResources().getDrawable(R.drawable.nothing);
            conseil.setText(" ");
        }

        //NOT HAPPY AT ALL
        if( compteur_rouge >= 1 )
        {
            image_smiley = getResources().getDrawable(R.drawable.nothappyatall);
            conseil.setText("Remarque:\nTrès mauvaise position");
            compteur_nothappyatall = compteur_nothappyatall + 1;
            if(sound == ON)
                beep.start();
        }

        //NOT HAPPY
        else if( compteur_orange >= 3 )
        {
            image_smiley = getResources().getDrawable(R.drawable.nothappy);
            conseil.setText("Remarque:\nMauvaise position");
            compteur_nothappy = compteur_nothappy + 1;
        }

        //HAPPY
        else if( compteur_orange == 1 && compteur_noir==0)
        {
            image_smiley = getResources().getDrawable(R.drawable.happy);
            conseil.setText("Remarque:\nBonne position");
            compteur_happy = compteur_happy + 1;
        }

        //MIDDLE
        else if( compteur_orange == 2 || compteur_orange == 1 )
        {
            image_smiley = getResources().getDrawable(R.drawable.middle);
            conseil.setText("Remarque:\nBof");
            compteur_middle = compteur_middle + 1;
        }

        //VERY HAPPY
        else if(compteur_rouge == 0 && compteur_orange == 0 && compteur_noir == 0 && compteur_vert >= 7)
        {
            image_smiley = getResources().getDrawable(R.drawable.veryhappy);
            conseil.setText("Remarque:\nTrès bonne position");
            compteur_veryhappy = compteur_veryhappy + 1;
        }

        else {
            image_smiley = getResources().getDrawable(R.drawable.nothing);
            conseil.setText(" ");
        }

        smiley.setImageDrawable(image_smiley);
        compteur_orange = 0;
        compteur_rouge = 0;
        compteur_noir = 0;
        compteur_vert = 0;
    }

    public void update_popup(){
        Log.i(TAG, "DOS HAUT : " + capteur_dos_haut + " DOS BAS " + capteur_dos_bas + " DOS DROITE " + capteur_dos_milieu_droite + " DOS GAUCHE " + capteur_dos_milieu_gauche + "ASSISE AVANT : " + capteur_assise_avant + " ASSISE ARRIERE " + capteur_assise_arriere + " ASSISE DROITE " + capteur_assise_droite + " ASSISE GAUCHE " + capteur_assise_gauche);
        if ((capteur_dos_haut > 39) && (capteur_dos_haut < 61) && (capteur_dos_bas > 39) && (capteur_dos_bas < 61) && (capteur_dos_milieu_gauche > 39) && (capteur_dos_milieu_gauche < 61) && (capteur_dos_milieu_droite > 39) && (capteur_dos_milieu_droite < 61) && (capteur_assise_avant > 39) && (capteur_assise_avant < 61) && (capteur_assise_arriere > 39) && (capteur_assise_arriere < 61) && (capteur_assise_droite > 39) && (capteur_assise_droite < 61) && (capteur_assise_gauche > 39) && (capteur_assise_gauche < 61)) {
            compteur_bonne++;
            Log.i(TAG, " *************************************************** PERFECT ");
        } else if (capteur_dos_milieu_droite > 70) {
            compteur_droite++;
            Log.i(TAG, " *************************************************** Trop a DROITE ");
        } else if ((capteur_dos_haut > 59) && (capteur_dos_haut < 101) && (capteur_dos_bas > 0) && (capteur_dos_bas < 41)  && (capteur_assise_avant > 59) && (capteur_assise_avant < 100) && (capteur_assise_arriere >= 0) && (capteur_assise_arriere < 35) ) {
            compteur_affalé++;
            Log.i(TAG, " *************************************************** Trop affalé ");
        } else if ((capteur_dos_haut == 0) && (capteur_dos_bas == 100) && (capteur_dos_milieu_gauche == 0) && (capteur_dos_milieu_droite == 0) ) {
            compteur_courbé++;
            Log.i(TAG, " *************************************************** Trop courbé ");
        } else if ((capteur_dos_haut == 0) && (capteur_dos_bas == 0) && (capteur_dos_milieu_gauche == 0) && (capteur_dos_milieu_droite == 0) && compteur_noir != 8) {
            compteur_no_contact_dossier++;
            Log.i(TAG, " *************************************************** Pas de contact au niveau duu dos ");
        } else if ((capteur_dos_milieu_gauche > 70)) {
            compteur_gauche++;
            Log.i(TAG, " *************************************************** Trop a GAUCHE ");
        } else {
            Log.i(TAG, " Aucune posture dans les intervalles identifiés ");
        }
    }

    class Compteur_posture implements Runnable {
        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            Timer T = new Timer();
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            nb_posture[0] = compteur_bonne;
                            nb_posture[1] = compteur_droite;
                            nb_posture[2] = compteur_gauche;
                            nb_posture[3] = compteur_no_contact_dossier;
                            nb_posture[4] = compteur_affalé;
                            nb_posture[5] = compteur_courbé;

                            posture[0] = "Bonne posture : ne changez rien";
                            posture[1] = "Posture trop à  droite : pensez à  vous repositionner sur la gauche";
                            posture[2] = "Posture trop à  gauche : pensez à  vous repositionner sur la droite";
                            posture[3] = "Vous ne touchez pas le dossier : pensez à  vous asseoir au fond du siège";
                            posture[4] = "Posture affalée : pensez à vous redresser";
                            posture[5] = "Posture penchée en avant/courbée : pensez à  vous redressez";

                            nb_posture_la_plus_frequente = 0;
                            posture_la_plus_frequente = "Aucune posture reconnue";

                            for (int i = 0; i < 6; i++) {
                                if (nb_posture[i] > nb_posture_la_plus_frequente) {
                                    nb_posture_la_plus_frequente = nb_posture[i];
                                    posture_la_plus_frequente = posture[i];
                                    Log.i(TAG, " POSTURE LA PLUS FREQUENTE  : " + posture[i]);
                                }
                            }

                            if(communicationButton.getText() == "Arrêter l'analyse en temps réel" && compteur_noir != 8 )
                                Toast.makeText(TempsReelActivity.this, posture_la_plus_frequente , Toast.LENGTH_SHORT).show();

                            nb_posture_la_plus_frequente = 0;
                            posture_la_plus_frequente = "";
                            compteur_bonne = 0;
                            compteur_droite = 0;
                            compteur_gauche = 0;
                            compteur_no_contact_dossier = 0;
                            compteur_affalé = 0;
                            compteur_courbé = 0;

                        }
                    });
                }
            }, 10000, 10000); //temps debut, periode

        }
    }

    class Compteur_smiley implements Runnable {
        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            Timer T = new Timer();
            T.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (compteur_veryhappy > compteur_happy && compteur_veryhappy > compteur_middle && compteur_veryhappy > compteur_nothappy && compteur_veryhappy > compteur_nothappyatall)
                                smiley_bd = "veryhappy";

                            if (compteur_happy > compteur_veryhappy && compteur_happy > compteur_middle && compteur_happy > compteur_nothappy && compteur_happy > compteur_nothappyatall)
                                smiley_bd = "happy";

                            if (compteur_middle > compteur_veryhappy && compteur_middle > compteur_happy && compteur_middle > compteur_nothappy && compteur_middle > compteur_nothappyatall)
                                smiley_bd = "middle";

                            if (compteur_nothappy > compteur_veryhappy && compteur_nothappy > compteur_happy && compteur_nothappy > compteur_middle && compteur_nothappy > compteur_nothappyatall)
                                smiley_bd = "nothappy";

                            if (compteur_nothappyatall > compteur_veryhappy && compteur_nothappyatall > compteur_happy && compteur_nothappyatall > compteur_middle && compteur_nothappyatall > compteur_nothappy)
                                smiley_bd = "nothappyatall";

                            Log.i(TAG, "SMLEY BD : "+smiley_bd);

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            String date_to_insert = sdf.format(new Date());
                            ArrayList<String> time = new ArrayList<>();
                            time.add("06:00");
                            time.add("06:15");
                            time.add("06:30");
                            time.add("06:45");
                            time.add("07:00");
                            time.add("07:15");
                            time.add("07:30");
                            time.add("07:45");
                            time.add("08:00");
                            time.add("08:15");
                            time.add("08:30");
                            time.add("08:45");
                            time.add("09:00");
                            time.add("09:15");
                            time.add("09:30");
                            time.add("09:45");
                            time.add("10:00");
                            time.add("10:15");
                            time.add("10:30");
                            time.add("10:45");
                            time.add("11:00");
                            time.add("11:15");
                            time.add("11:30");
                            time.add("11:45");
                            time.add("12:00");
                            time.add("12:15");
                            time.add("12:30");
                            time.add("12:45");
                            time.add("13:00");
                            time.add("13:15");
                            time.add("13:30");
                            time.add("13:45");
                            time.add("14:00");
                            time.add("14:15");
                            time.add("14:30");
                            time.add("14:45");
                            time.add("15:00");
                            time.add("15:15");
                            time.add("15:30");
                            time.add("15:45");
                            time.add("16:00");
                            time.add("16:15");
                            time.add("16:30");
                            time.add("16:45");
                            time.add("17:00");
                            time.add("17:15");
                            time.add("17:30");
                            time.add("17:45");
                            time.add("18:00");
                            time.add("18:15");
                            time.add("18:30");
                            time.add("18:45");
                            time.add("19:00");
                            time.add("19:15");
                            time.add("19:30");
                            time.add("19:45");
                            time.add("20:00");
                            time.add("20:15");
                            time.add("20:30");
                            time.add("20:45");

                            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                            Date now = new Date();
                            Date date_inf = null;
                            Date date_sup = null;
                            boolean intervalle_trouve = false;
                            dateFormat.format(now);

                            int length = time.size();

                            while ((intervalle_trouve == false) || (length > 4)) {

                                try {
                                    date_inf = dateFormat.parse(time.get(length - 2));
                                    date_sup = dateFormat.parse(time.get(length - 1));
                                    if ((now.compareTo(date_inf) >= 0) && (now.compareTo(date_sup) < 0)) { //il est 6h10  : si now > 6h et <6h10
                                        intervalle_trouve = true;
                                        Log.i(TAG, date_inf + " < " + now + " < " + date_sup);

                                    } else {

                                        Log.i(TAG, now + " Pas dans intervalle [" + date_inf + ":" + date_sup + "]");

                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }





                                switch (time.get(length - 2)) {

                                    case "06:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "06:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "06:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "06:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "07:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "07:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "07:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "07:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "08:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "08:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "08:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "08:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "09:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "09:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "09:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "09:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "10:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "10:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "10:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "10:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "11:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "11:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "11:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "11:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "12:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "12:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "12:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "12:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "13:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "13:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "13:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "13:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "14:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "14:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "14:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "14:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "15:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "15:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "15:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "15:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "16:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "16:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "16:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "16:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "17:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "17:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "17:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "17:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "18:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "18:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "18:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "18:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "19:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "19:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "19:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null,
                                                null, null, null, null
                                        ));
                                        break;
                                    case "19:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd,
                                                null, null, null, null
                                        ));
                                        break;

                                    case "20:00":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                smiley_bd, null, null, null
                                        ));
                                        break;
                                    case "20:15":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, smiley_bd, null, null
                                        ));
                                        break;
                                    case "20:30":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, smiley_bd, null
                                        ));
                                        break;
                                    case "20:45":
                                        userBdd.updateDay(idUser, new Day(idUser,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, null,
                                                null, null, null, smiley_bd
                                        ));
                                        break;

                                }
                                length--;
                            }


                            //ENVOYER SMILEY_BD DANS BASE DE DONNEE
                            compteur_nothappyatall = 0;
                            compteur_nothappy = 0;
                            compteur_middle = 0;
                            compteur_happy = 0;
                            compteur_veryhappy = 0;

                        }
                    });
                }
            }, 120000, 120000); //temps debut, periode

        }
    }

}

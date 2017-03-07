package com.ergo.ppe_android11;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
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
    Mesure mesuretemp;
    private final String EXTRA_LOGIN = "undefined login";

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
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        communication.setAdapter(arrayAdapter);
        communication.setTextFilterEnabled(true);
        connectionStatut = (TextView) findViewById(R.id.connectStatut);


        userBdd = new UserBDD(this);
        userBdd.open();
        Intent intent = getIntent();
        mail=intent.getStringExtra(EXTRA_LOGIN);
        User currentUser = new User();
        try {
            currentUser = userBdd.getUserWithMail(mail);
        } catch (Exception e) {

        }
        mesuretemp = new Mesure();

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
        });
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
    };

}

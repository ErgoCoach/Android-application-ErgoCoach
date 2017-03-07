package com.ergo.ppe_android11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_USERS = "table_users";
    private static final String COL_MAIL = "Mail";
    private static final String COL_NAME = "Nom";
    private static final String COL_PASSWORD = "Password";
    private static final String COL_BIRTHYEAR = "BirthYear";
    private static final String COL_SIGNUPDATE = "SignUpDate";

    private static final String TABLE_SENSORS = "table_sensors";
    private static final String COL_ID = "Id";
    private static final String COL_DATEHEURE = "DateHeure";
    private static final String COL_MAILUSER = "MailUser";
    private static final String COL_ASSISE_AVANT = "AssiseAvant";
    private static final String COL_ASSISE_ARRIERE = "AssiseArriere";
    private static final String COL_ASSISE_GAUCHE = "AssiseGauche";
    private static final String COL_ASSISE_DROITE = "AssiseDroite";
    private static final String COL_DOS_HAUT_GAUCHE = "DosHautGauche";
    private static final String COL_DOS_HAUT_DROITE = "DosHautDroite";
    private static final String COL_DOS_MILIEU_DROITE = "DosMilieuDroite";
    private static final String COL_DOS_MILIEU_GAUCHE = "DosMilieuGauche";
    private static final String COL_DOS_BAS = "DosBas";

        private static final String CREATE_BDD = "CREATE TABLE " + TABLE_USERS + " ("
                +  COL_MAIL + " TEXT NOT NULL, "
                + COL_NAME + " TEXT NOT NULL, " + COL_PASSWORD + " TEXT NOT NULL, " + COL_BIRTHYEAR + " INTEGER," + COL_SIGNUPDATE + " TEXT NOT NULL);";

        private static final String CREATE_BDD_SENSORS = "CREATE TABLE " + TABLE_SENSORS + " ("
                +  COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATEHEURE + " TEXT NOT NULL, " + COL_MAILUSER  + " TEXT NOT NULL, "
                + COL_ASSISE_AVANT + " INTEGER," + COL_ASSISE_ARRIERE + " INTEGER,"
                + COL_ASSISE_GAUCHE + " INTEGER," + COL_ASSISE_DROITE + " INTEGER,"
                + COL_DOS_HAUT_GAUCHE + " INTEGER," + COL_DOS_HAUT_DROITE + " INTEGER,"
                + COL_DOS_MILIEU_GAUCHE + " INTEGER," + COL_DOS_MILIEU_DROITE + " INTEGER,"
                + COL_DOS_BAS+ " INTEGER);";

        public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String USERS_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_USERS ;
            db.execSQL(USERS_TABLE_DROP);
            //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
            db.execSQL(CREATE_BDD);
            String SENSORS_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_SENSORS ;
            db.execSQL(SENSORS_TABLE_DROP);
            //on créé la table à partir de la requête écrite dans la variable CREATE_BDD_SENSORS
            db.execSQL(CREATE_BDD_SENSORS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
            //comme ça lorsque je change la version les id repartent de 0
            db.execSQL("DROP TABLE " + TABLE_USERS );
            //onCreate(db);
            db.execSQL("DROP TABLE " + TABLE_SENSORS );
            onCreate(db);
        }

    }


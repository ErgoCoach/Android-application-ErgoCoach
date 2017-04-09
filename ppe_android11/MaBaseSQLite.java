package com.ergo.ppe_android11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID_U = "Id";
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
    private static final String COL_DOS_HAUT = "DosHaut";
    private static final String COL_DOS_MILIEU_DROITE = "DosMilieuDroite";
    private static final String COL_DOS_MILIEU_GAUCHE = "DosMilieuGauche";
    private static final String COL_DOS_BAS = "DosBas";

    private static final String TABLE_SMILEYS = "table_smileys"; //TABLE DE SMILEY AVEC 1 SMILEY PAR JOUR
    private static final String COL_ID_SMILEY = "Id_Smiley";
    private static final int NUM_COL_ID_SMILEY = 0;
    private static final String COL_ID_USER = "Id_User";
    private static final int NUM_COL_ID_USER = 1;
    private static final String COL_DAY = "Day";
    private static final int NUM_COL_DAY = 2;
    private static final String COL_SMILEY = "Smiley";
    private static final int NUM_COL_SMILEY = 3;

    private static final String TABLE_DAY = "table_day"; //TABLE DES MESURES SUR 1 JOURNEE (CHAQUE SOIR LA TABLE EST VIDEE ET REMPLIE DANS LA JOURNEE AU FUR ET A MESURE
    private static final String COL_ID_TABLE_DAY = "Id_Day";
    private static final int NUM_COL_ID_TABLE_DAY = 0;
   // private static final String COL_ID_USER = "Id_User";
   // private static final int NUM_COL_ID_USER = 1;

    private static final String COL_6h = "six_heure";
    private static final int NUM_COL_6h = 2;
    private static final String COL_6h15 = "six_heure_quinze";
    private static final int NUM_COL_6h15 = 3;
    private static final String COL_6h30 = "six_heure_trente";
    private static final int NUM_COL_6h30 = 4;
    private static final String COL_6h45 = "six_heure_quarante_cinq";
    private static final int NUM_COL_6h45 = 5;
    private static final String COL_7h = "sept_heure";
    private static final int NUM_COL_7h = 6;
    private static final String COL_7h15 = "sept_heure_quinze";
    private static final int NUM_COL_7h15 = 7;
    private static final String COL_7h30 = "sept_heure_trente";
    private static final int NUM_COL_7h30 = 8;
    private static final String COL_7h45 = "sept_heure_quarante_cinq";
    private static final int NUM_COL_7h45 = 9;
    private static final String COL_8h = "huit_heure";
    private static final int NUM_COL_8h = 10;
    private static final String COL_8h15 = "huit_heure_quinze";
    private static final int NUM_COL_8h15 = 11;
    private static final String COL_8h30 = "huit_heure_trente";
    private static final int NUM_COL_8h30 = 12;
    private static final String COL_8h45 = "huit_heure_quarante_cinq";
    private static final int NUM_COL_8h45 = 13;
    private static final String COL_9h = "neuf_heure";
    private static final int NUM_COL_9h = 14;
    private static final String COL_9h15 = "neuf_heure_quinze";
    private static final int NUM_COL_9h15 = 15;
    private static final String COL_9h30 = "neuf_heure_trente";
    private static final int NUM_COL_9h30 = 16;
    private static final String COL_9h45 = "neuf_heure_quarante_cinq";
    private static final int NUM_COL_9h45 = 17;
    private static final String COL_10h = "dix_heure";
    private static final int NUM_COL_10h = 18;
    private static final String COL_10h15 = "dix_heure_quinze";
    private static final int NUM_COL_10h15 = 19;
    private static final String COL_10h30 = "dix_heure_trente";
    private static final int NUM_COL_10h30 = 20;
    private static final String COL_10h45 = "dix_heure_quarante_cinq";
    private static final int NUM_COL_10h45 = 21;
    private static final String COL_11h = "onze_heure";
    private static final int NUM_COL_11h = 22;
    private static final String COL_11h15 = "onze_heure_quinze";
    private static final int NUM_COL_11h15 = 23;
    private static final String COL_11h30 = "onze_heure_trente";
    private static final int NUM_COL_11h30 = 24;
    private static final String COL_11h45 = "onze_heure_quarante_cinq";
    private static final int NUM_COL_11h45 = 25;
    private static final String COL_12h = "douze_heure";
    private static final int NUM_COL_12h = 26;
    private static final String COL_12h15 = "douze_heure_quinze";
    private static final int NUM_COL_12h15 = 27;
    private static final String COL_12h30 = "douze_heure_trente";
    private static final int NUM_COL_12h30 = 28;
    private static final String COL_12h45 = "douze_heure_quarante_cinq";
    private static final int NUM_COL_12h45 = 29;

    private static final String COL_13h = "treize_heure";
    private static final int NUM_COL_13h = 30;
    private static final String COL_13h15 = "treize_heure_quinze";
    private static final int NUM_COL_13h15 = 31;
    private static final String COL_13h30 = "treize_heure_trente";
    private static final int NUM_COL_13h30 = 32;
    private static final String COL_13h45 = "treize_heure_quarante_cinq";
    private static final int NUM_COL_13h45 = 33;
    private static final String COL_14h = "quatorze_heure";
    private static final int NUM_COL_14h = 34;
    private static final String COL_14h15 = "quatorze_heure_quinze";
    private static final int NUM_COL_14h15 = 35;
    private static final String COL_14h30 = "quatorze_heure_trente";
    private static final int NUM_COL_14h30 = 36;
    private static final String COL_14h45 = "quatorze_heure_quarante_cinq";
    private static final int NUM_COL_14h45 = 37;
    private static final String COL_15h = "quinze_heure";
    private static final int NUM_COL_15h = 38;
    private static final String COL_15h15 = "quinze_heure_quinze";
    private static final int NUM_COL_15h15 = 39;
    private static final String COL_15h30 = "quinze_heure_trente";
    private static final int NUM_COL_15h30 = 40;
    private static final String COL_15h45 = "quinze_heure_quarante_cinq";
    private static final int NUM_COL_15h45 = 41;
    private static final String COL_16h = "seize_heure";
    private static final int NUM_COL_16h = 42;
    private static final String COL_16h15 = "seize_heure_quinze";
    private static final int NUM_COL_16h15 = 43;
    private static final String COL_16h30 = "seize_heure_trente";
    private static final int NUM_COL_16h30 = 44;
    private static final String COL_16h45 = "seize_heure_quarante_cinq";
    private static final int NUM_COL_16h45 = 45;
    private static final String COL_17h = "dixsept_heure";
    private static final int NUM_COL_17h = 46;
    private static final String COL_17h15 = "dixsept_heure_quinze";
    private static final int NUM_COL_17h15 = 47;
    private static final String COL_17h30 = "dixsept_heure_trente";
    private static final int NUM_COL_17h30 = 48;
    private static final String COL_17h45 = "dixsept_heure_quarante_cinq";
    private static final int NUM_COL_17h45 = 49;
    private static final String COL_18h = "dixhuit_heure";
    private static final int NUM_COL_18h = 50;
    private static final String COL_18h15 = "dixhuit_heure_quinze";
    private static final int NUM_COL_18h15 = 51;
    private static final String COL_18h30 = "dixhuit_heure_trente";
    private static final int NUM_COL_18h30 = 52;
    private static final String COL_18h45 = "dixhuit_heure_quarante_cinq";
    private static final int NUM_COL_18h45 = 53;
    private static final String COL_19h = "dixneuf_heure";
    private static final int NUM_COL_19h = 54;
    private static final String COL_19h15 = "dixneuf_heure_quinze";
    private static final int NUM_COL_19h15 = 55;
    private static final String COL_19h30 = "dixneuf_heure_trente";
    private static final int NUM_COL_19h30 = 56;
    private static final String COL_19h45 = "dixneuf_heure_quarante_cinq";
    private static final int NUM_COL_19h45 = 57;
    private static final String COL_20h = "vingt_heure";
    private static final int NUM_COL_20h = 58;
    private static final String COL_20h15 = "vingt_heure_quinze";
    private static final int NUM_COL_20h15 = 59;
    private static final String COL_20h30 = "vingt_heure_trente";
    private static final int NUM_COL_20h30 = 60;
    private static final String COL_20h45 = "vingt_heure_quarante_cinq";
    private static final int NUM_COL_20h45 = 61;

        private static final String CREATE_BDD = "CREATE TABLE " + TABLE_USERS + " ("
                + COL_ID_U + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  COL_MAIL + " TEXT NOT NULL, "
                + COL_NAME + " TEXT NOT NULL, "
                + COL_PASSWORD + " TEXT NOT NULL, "
                + COL_BIRTHYEAR + " INTEGER,"
                + COL_SIGNUPDATE + " TEXT NOT NULL); " ;


    private static final String CREATE_BDD_SENSORS = "CREATE TABLE " + TABLE_SENSORS + " ("
                +  COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATEHEURE + " TEXT NOT NULL, " + COL_MAILUSER  + " TEXT NOT NULL, "
                + COL_ASSISE_AVANT + " INTEGER," + COL_ASSISE_ARRIERE + " INTEGER,"
                + COL_ASSISE_GAUCHE + " INTEGER," + COL_ASSISE_DROITE + " INTEGER,"
                + COL_DOS_HAUT + " INTEGER,"
                + COL_DOS_MILIEU_GAUCHE + " INTEGER," + COL_DOS_MILIEU_DROITE + " INTEGER,"
                + COL_DOS_BAS+ " INTEGER);";

    private static final String CREATE_TABLE_SMILEYS = "CREATE TABLE " + TABLE_SMILEYS + " ("
                +  COL_ID_SMILEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_ID_USER + " INTEGER, "
                + COL_DAY + " DATE,"
                + COL_SMILEY + " TEXT NOT NULL );";

    private static final String CREATE_TABLE_DAY = "CREATE TABLE " + TABLE_DAY + " ("
                +  COL_ID_TABLE_DAY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_ID_USER + " INTEGER, "
                + COL_6h + " TEXT ,"
                + COL_6h15 + " TEXT ,"
                + COL_6h30 + " TEXT ,"
                + COL_6h45 + " TEXT ,"

                + COL_7h + " TEXT ,"
                + COL_7h15 + " TEXT ,"
                + COL_7h30 + " TEXT ,"
                + COL_7h45 + " TEXT ,"

                + COL_8h + " TEXT ,"
                + COL_8h15 + " TEXT ,"
                + COL_8h30 + " TEXT ,"
                + COL_8h45 + " TEXT ,"

                + COL_9h + " TEXT ,"
                + COL_9h15 + " TEXT ,"
                + COL_9h30 + " TEXT ,"
                + COL_9h45 + " TEXT ,"

                + COL_10h + " TEXT ,"
                + COL_10h15 + " TEXT ,"
                + COL_10h30 + " TEXT ,"
                + COL_10h45 + " TEXT ,"

                + COL_11h + " TEXT ,"
                + COL_11h15 + " TEXT ,"
                + COL_11h30 + " TEXT ,"
                + COL_11h45 + " TEXT ,"

                + COL_12h + " TEXT ,"
                + COL_12h15 + " TEXT ,"
                + COL_12h30 + " TEXT ,"
                + COL_12h45 + " TEXT ,"

                + COL_13h + " TEXT ,"
                + COL_13h15 + " TEXT ,"
                + COL_13h30 + " TEXT ,"
                + COL_13h45 + " TEXT ,"

                + COL_14h + " TEXT ,"
                + COL_14h15 + " TEXT ,"
                + COL_14h30 + " TEXT ,"
                + COL_14h45 + " TEXT ,"

                + COL_15h + " TEXT ,"
                + COL_15h15 + " TEXT ,"
                + COL_15h30 + " TEXT ,"
                + COL_15h45 + " TEXT ,"

                + COL_16h + " TEXT ,"
                + COL_16h15 + " TEXT ,"
                + COL_16h30 + " TEXT ,"
                + COL_16h45 + " TEXT ,"

                + COL_17h + " TEXT ,"
                + COL_17h15 + " TEXT ,"
                + COL_17h30 + " TEXT ,"
                + COL_17h45 + " TEXT ,"

                + COL_18h + " TEXT ,"
                + COL_18h15 + " TEXT ,"
                + COL_18h30 + " TEXT ,"
                + COL_18h45 + " TEXT ,"

                + COL_19h + " TEXT ,"
                + COL_19h15 + " TEXT ,"
                + COL_19h30 + " TEXT ,"
                + COL_19h45 + " TEXT ,"

                + COL_20h + " TEXT ,"
                + COL_20h15 + " TEXT ,"
                + COL_20h30 + " TEXT ,"
                + COL_20h45 + " TEXT  );";

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

            String SMILEYS_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_SMILEYS ;
            db.execSQL(SMILEYS_TABLE_DROP);
            //on créé la table à partir de la requête écrite dans la variable CREATE_BDD_SENSORS
            db.execSQL(CREATE_TABLE_SMILEYS);

            String DAY_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_DAY ;
            db.execSQL(DAY_TABLE_DROP);
            //on créé la table à partir de la requête écrite dans la variable CREATE_BDD_SENSORS
            db.execSQL(CREATE_TABLE_DAY);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
            //comme ça lorsque je change la version les id repartent de 0
            db.execSQL("DROP TABLE " + TABLE_USERS );
            db.execSQL("DROP TABLE " + TABLE_SENSORS );
            db.execSQL("DROP TABLE " + TABLE_DAY );
            db.execSQL("DROP TABLE " + TABLE_SMILEYS );
            onCreate(db);
        }

    }


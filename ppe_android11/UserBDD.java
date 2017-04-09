package com.ergo.ppe_android11;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "users.db";

    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID_U = "Id";
    private static final int NUM_COL_ID_U = 0;
    private static final String COL_MAIL = "Mail";
    private static final int NUM_COL_MAIL = 1;
    private static final String COL_NAME = "Nom";
    private static final int NUM_COL_NAME = 2;
    private static final String COL_PASSWORD = "Password";
    private static final int NUM_COL_PASSWORD = 3;
    private static final String COL_BIRTHYEAR = "BirthYear";
    private static final int NUM_COL_BIRTHYEAR = 4;
    private static final String COL_SIGNUPDATE = "SignUpDate";
    private static final int NUM_COL_SIGNUPDATE = 5;


    private static final String TABLE_SENSORS = "table_sensors";
    private static final String COL_ID = "Id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_DATEHEURE = "DateHeure";
    private static final int NUM_COL_DATEHEURE = 1;
    private static final String COL_MAILUSER = "MailUser";
    private static final int NUM_COL_MAILUSER = 2;
    private static final String COL_ASSISE_AVANT = "AssiseAvant";
    private static final int NUM_COL_ASSISE_AVANT = 3;
    private static final String COL_ASSISE_ARRIERE = "AssiseArriere";
    private static final int NUM_COL_ASSISE_ARRIERE = 4;
    private static final String COL_ASSISE_GAUCHE = "AssiseGauche";
    private static final int NUM_COL_ASSISE_GAUCHE = 5;
    private static final String COL_ASSISE_DROITE = "AssiseDroite";
    private static final int NUM_COL_ASSISE_DROITE = 6;
    private static final String COL_DOS_HAUT = "DosHaut";
    private static final int NUM_COL_DOS_HAUT = 7;
    private static final String COL_DOS_MILIEU_GAUCHE = "DosMilieuGauche";
    private static final int NUM_COL_DOS_MILIEU_GAUCHE = 8;
    private static final String COL_DOS_MILIEU_DROITE = "DosMilieuDroite";
    private static final int NUM_COL_DOS_MILIEU_DROITE = 9;
    private static final String COL_DOS_BAS = "DosBas";
    private static final int NUM_COL_DOS_BAS = 10;

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


    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public UserBDD(Context context) {
        //On créer la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public long insertUser(User user) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_MAIL, user.getMail());
        values.put(COL_NAME, user.getName());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_BIRTHYEAR, user.getBirthyear());
        values.put(COL_SIGNUPDATE, user.getSignUpDate());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_USERS, null, values);
    }
    public long insertMesure(Mesure mesure) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_DATEHEURE, mesure.getDate());
        values.put(COL_MAILUSER, mesure.getMailuser());
        values.put(COL_DOS_HAUT, mesure.getDos_haut());
        values.put(COL_DOS_MILIEU_GAUCHE, mesure.getDos_milieu_gauche());
        values.put(COL_DOS_MILIEU_DROITE, mesure.getDos_milieu_droite());
        values.put(COL_DOS_BAS, mesure.getDos_bas());
        values.put(COL_ASSISE_ARRIERE, mesure.getAssise_arriere());
        values.put(COL_ASSISE_AVANT, mesure.getAssise_avant());
        values.put(COL_ASSISE_GAUCHE, mesure.getAssise_gauche());
        values.put(COL_ASSISE_DROITE, mesure.getAssise_droite());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_SENSORS, null, values);
    }

    public long insertSmiley(Smiley smiley) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
       // values.put(COL_ID_SMILEY, smiley.getCol_id_smiley());
        values.put(COL_ID_USER, smiley.getCol_id_user());
        values.put(COL_DAY, smiley.getCol_day());
        values.put(COL_SMILEY, smiley.getCol_smiley());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_SMILEYS, null, values);
    }

    public long insertDay(Day day) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(COL_ID_USER, day.getCol_id_user());
        values.put(COL_6h, day.getCol_6h());
        values.put(COL_6h15, day.getCol_6h());
        values.put(COL_6h30, day.getCol_6h());
        values.put(COL_6h45, day.getCol_6h());

        values.put(COL_7h, day.getCol_7h());
        values.put(COL_7h15, day.getCol_7h());
        values.put(COL_7h30, day.getCol_7h());
        values.put(COL_7h45, day.getCol_7h());

        values.put(COL_8h, day.getCol_8h());
        values.put(COL_8h15, day.getCol_8h());
        values.put(COL_8h30, day.getCol_8h());
        values.put(COL_8h45, day.getCol_8h());

        values.put(COL_9h, day.getCol_9h());
        values.put(COL_9h15, day.getCol_9h());
        values.put(COL_9h30, day.getCol_9h());
        values.put(COL_9h45, day.getCol_9h());

        values.put(COL_10h, day.getCol_10h());
        values.put(COL_10h15, day.getCol_10h());
        values.put(COL_10h30, day.getCol_10h());
        values.put(COL_10h45, day.getCol_10h());

        values.put(COL_11h, day.getCol_11h());
        values.put(COL_11h15, day.getCol_11h());
        values.put(COL_11h30, day.getCol_11h());
        values.put(COL_11h45, day.getCol_11h());

        values.put(COL_12h, day.getCol_12h());
        values.put(COL_12h15, day.getCol_12h());
        values.put(COL_12h30, day.getCol_12h());
        values.put(COL_12h45, day.getCol_12h());

        values.put(COL_13h, day.getCol_13h());
        values.put(COL_13h15, day.getCol_13h());
        values.put(COL_13h30, day.getCol_13h());
        values.put(COL_13h45, day.getCol_13h());

        values.put(COL_14h, day.getCol_14h());
        values.put(COL_14h15, day.getCol_14h());
        values.put(COL_14h30, day.getCol_14h());
        values.put(COL_14h45, day.getCol_14h());

        values.put(COL_15h, day.getCol_15h());
        values.put(COL_15h15, day.getCol_15h());
        values.put(COL_15h30, day.getCol_15h());
        values.put(COL_15h45, day.getCol_15h());

        values.put(COL_16h, day.getCol_16h());
        values.put(COL_16h15, day.getCol_16h());
        values.put(COL_16h30, day.getCol_16h());
        values.put(COL_16h45, day.getCol_16h());

        values.put(COL_17h, day.getCol_17h());
        values.put(COL_17h15, day.getCol_17h());
        values.put(COL_17h30, day.getCol_17h());
        values.put(COL_17h45, day.getCol_17h());

        values.put(COL_18h, day.getCol_18h());
        values.put(COL_18h15, day.getCol_18h());
        values.put(COL_18h30, day.getCol_18h());
        values.put(COL_18h45, day.getCol_18h());

        values.put(COL_19h, day.getCol_19h());
        values.put(COL_19h15, day.getCol_19h());
        values.put(COL_19h30, day.getCol_19h());
        values.put(COL_19h45, day.getCol_19h());

        values.put(COL_20h, day.getCol_20h());
        values.put(COL_20h15, day.getCol_20h());
        values.put(COL_20h30, day.getCol_20h());
        values.put(COL_20h45, day.getCol_20h());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_DAY, null, values);
    }

    public int updateUser(String mail, User user) {
        //La mise à jour d'un user dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel user on doit mettre à jour grâce au mail
        ContentValues values = new ContentValues();
        values.put(COL_MAIL, user.getMail());
        values.put(COL_NAME, user.getName());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_BIRTHYEAR, user.getBirthyear());
        values.put(COL_SIGNUPDATE, user.getSignUpDate());
        return bdd.update(TABLE_USERS, values, COL_MAIL + " = " + mail, null);
    }
    public void updateUserDate(String mail, String date) {
        //La mise à jour d'un user dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel user on doit mettre à jour grâce au mail
        //ContentValues values = new ContentValues();
        //values.put(COL_SIGNUPDATE, date);
        // bdd.update(TABLE_USERS, values, COL_MAIL + " = " + "zzsophie@free.fr", null);
        bdd.execSQL("UPDATE "+TABLE_USERS+" SET  "+COL_SIGNUPDATE+" = '"+date+"' WHERE "+COL_MAIL+" = '"+mail+"'");

    }
    public long updateMesure(int id, Mesure mesure) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(COL_DATEHEURE, String.valueOf(mesure.getDate()));
        values.put(COL_MAILUSER, mesure.getMailuser());
        values.put(COL_DOS_HAUT, mesure.getDos_haut());
        values.put(COL_DOS_MILIEU_GAUCHE, mesure.getDos_milieu_gauche());
        values.put(COL_DOS_MILIEU_DROITE, mesure.getDos_milieu_droite());
        values.put(COL_DOS_BAS, mesure.getDos_bas());
        values.put(COL_ASSISE_ARRIERE, mesure.getAssise_arriere());
        values.put(COL_ASSISE_AVANT, mesure.getAssise_avant());
        values.put(COL_ASSISE_GAUCHE, mesure.getAssise_gauche());
        values.put(COL_ASSISE_DROITE, mesure.getAssise_droite());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.update(TABLE_SENSORS, values, COL_ID + " = " + id, null);
    }
    public long updateDay(int id, Day day) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        if(day.getCol_6h()!=null){ values.put(COL_6h, day.getCol_6h());
        } if(day.getCol_6h15()!=null){ values.put(COL_6h15, day.getCol_6h15());
        } if(day.getCol_6h30()!=null){ values.put(COL_6h30, day.getCol_6h30());
        } if(day.getCol_6h45()!=null){ values.put(COL_6h45, day.getCol_6h45());

        } if(day.getCol_7h()!=null){ values.put(COL_7h, day.getCol_7h());
        } if(day.getCol_7h15()!=null){ values.put(COL_7h15, day.getCol_7h15());
        } if(day.getCol_7h30()!=null){ values.put(COL_7h30, day.getCol_7h30());
        } if(day.getCol_7h45()!=null){ values.put(COL_7h45, day.getCol_7h45());

        } if(day.getCol_8h()!=null){ values.put(COL_8h, day.getCol_8h());
        } if(day.getCol_8h15()!=null){ values.put(COL_8h15, day.getCol_8h15());
        } if(day.getCol_8h30()!=null){ values.put(COL_8h30, day.getCol_8h30());
        } if(day.getCol_8h45()!=null){ values.put(COL_8h45, day.getCol_8h45());

        } if(day.getCol_9h()!=null){ values.put(COL_9h, day.getCol_9h());
        } if(day.getCol_9h15()!=null){ values.put(COL_9h15, day.getCol_9h15());
        } if(day.getCol_9h30()!=null){ values.put(COL_9h30, day.getCol_9h30());
        } if(day.getCol_9h45()!=null){ values.put(COL_9h45, day.getCol_9h45());

        } if(day.getCol_10h()!=null){ values.put(COL_10h, day.getCol_10h());
        } if(day.getCol_10h15()!=null){ values.put(COL_10h15, day.getCol_10h15());
        } if(day.getCol_10h30()!=null){ values.put(COL_10h30, day.getCol_10h30());
        } if(day.getCol_10h45()!=null){ values.put(COL_10h45, day.getCol_10h45());

        } if(day.getCol_11h()!=null){ values.put(COL_11h, day.getCol_11h());
        } if(day.getCol_11h15()!=null){ values.put(COL_11h15, day.getCol_11h15());
        } if(day.getCol_11h30()!=null){ values.put(COL_11h30, day.getCol_11h30());
        } if(day.getCol_11h45()!=null){ values.put(COL_11h45, day.getCol_11h45());

        } if(day.getCol_12h()!=null){ values.put(COL_12h, day.getCol_12h());
        } if(day.getCol_12h15()!=null){ values.put(COL_12h15, day.getCol_12h15());
        } if(day.getCol_12h30()!=null){ values.put(COL_12h30, day.getCol_12h30());
        } if(day.getCol_12h45()!=null){ values.put(COL_12h45, day.getCol_12h45());

        } if(day.getCol_13h()!=null){ values.put(COL_13h, day.getCol_13h());
        } if(day.getCol_13h15()!=null){ values.put(COL_13h15, day.getCol_13h15());
        } if(day.getCol_13h30()!=null){ values.put(COL_13h30, day.getCol_13h30());
        } if(day.getCol_13h45()!=null){ values.put(COL_13h45, day.getCol_13h45());

        } if(day.getCol_14h()!=null){ values.put(COL_14h, day.getCol_14h());
        } if(day.getCol_14h15()!=null){ values.put(COL_14h15, day.getCol_14h15());
        } if(day.getCol_14h30()!=null){ values.put(COL_14h30, day.getCol_14h30());
        } if(day.getCol_14h45()!=null){ values.put(COL_14h45, day.getCol_14h45());

        } if(day.getCol_15h()!=null){ values.put(COL_15h, day.getCol_15h());
        } if(day.getCol_15h15()!=null){ values.put(COL_15h15, day.getCol_15h15());
        } if(day.getCol_15h30()!=null){ values.put(COL_15h30, day.getCol_15h30());
        } if(day.getCol_15h45()!=null){ values.put(COL_15h45, day.getCol_15h45());

        } if(day.getCol_16h()!=null){ values.put(COL_16h, day.getCol_16h());
        } if(day.getCol_16h15()!=null){ values.put(COL_16h15, day.getCol_16h15());
        } if(day.getCol_16h30()!=null){ values.put(COL_16h30, day.getCol_16h30());
        } if(day.getCol_16h45()!=null){ values.put(COL_16h45, day.getCol_16h45());

        } if(day.getCol_17h()!=null){ values.put(COL_17h, day.getCol_17h());
        } if(day.getCol_17h15()!=null){ values.put(COL_17h15, day.getCol_17h15());
        } if(day.getCol_17h30()!=null){ values.put(COL_17h30, day.getCol_17h30());
        } if(day.getCol_17h45()!=null){ values.put(COL_17h45, day.getCol_17h45());

        } if(day.getCol_18h()!=null){ values.put(COL_18h, day.getCol_18h());
        } if(day.getCol_18h15()!=null){ values.put(COL_18h15, day.getCol_18h15());
        } if(day.getCol_18h30()!=null){ values.put(COL_18h30, day.getCol_18h30());
        } if(day.getCol_18h45()!=null){ values.put(COL_18h45, day.getCol_18h45());

        } if(day.getCol_19h()!=null){ values.put(COL_19h, day.getCol_19h());
        } if(day.getCol_19h15()!=null){ values.put(COL_19h15, day.getCol_19h15());
        } if(day.getCol_19h30()!=null){ values.put(COL_19h30, day.getCol_19h30());
        } if(day.getCol_19h45()!=null){ values.put(COL_19h45, day.getCol_19h45());

        } if(day.getCol_20h()!=null){ values.put(COL_20h, day.getCol_20h());
        } if(day.getCol_20h15()!=null){ values.put(COL_20h15, day.getCol_20h15());
        } if(day.getCol_20h30()!=null){ values.put(COL_20h30, day.getCol_20h30());
        } if(day.getCol_20h45()!=null) {
            values.put(COL_20h45, day.getCol_20h45());
        }



        //on insère l'objet dans la BDD via le ContentValues
        return bdd.update(TABLE_DAY, values, COL_ID_USER + " = " + id, null);
    }

    public int removeUserWithMail(String mail) {
        //Suppression d'un user de la BDD grâce au mail
        return bdd.delete(TABLE_USERS, COL_MAIL + " = " + mail, null);
    }

    public int removeMesureWithId(int id) {
        //Suppression d'une mesure de la BDD grâce a l'id
        return bdd.delete(TABLE_SENSORS, COL_ID + " = " + id, null);
    }

    public int removeSmileyWithId(int id) {
        //Suppression d'une mesure de la BDD grâce a l'id
        return bdd.delete(TABLE_SMILEYS, COL_ID_SMILEY + " = " + id, null);
    }
    public int removeDayWithId(int id) {
        //Suppression d'un jour de la BDD grâce a l'id
        return bdd.delete(TABLE_DAY, COL_ID_TABLE_DAY + " = " + id, null);
    }

    public int removeAll() {
        return bdd.delete(TABLE_USERS, null , null);
    }
    public int removeAllMesures() {return bdd.delete(TABLE_SENSORS, null , null);}
    public int removeAllSmileys() {return bdd.delete(TABLE_SMILEYS, null , null);}
    public int removeAllDay() {return bdd.delete(TABLE_DAY, null , null);}


    public User getUserWithMail(String mail) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_USERS, new String[]{COL_ID_U, COL_MAIL, COL_NAME, COL_PASSWORD, COL_BIRTHYEAR, COL_SIGNUPDATE}, COL_MAIL + " LIKE \"" + mail + "\"", null, null, null, null);
        User ctu = cursorToUser(c);
        c.close();
        return ctu;
    }
    public User getUserWithId(String id) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_USERS, new String[]{COL_ID_U, COL_MAIL, COL_NAME, COL_PASSWORD, COL_BIRTHYEAR, COL_SIGNUPDATE}, COL_ID_U + " LIKE \"" + id + "\"", null, null, null, null);
        User ctu = cursorToUser(c);
        c.close();
        return ctu;
    }
    public Mesure getMesureWithId(int id) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_SENSORS, new String[]{COL_ID, COL_DATEHEURE,COL_MAILUSER,COL_ASSISE_AVANT ,COL_ASSISE_ARRIERE,COL_ASSISE_GAUCHE,COL_ASSISE_DROITE,COL_DOS_HAUT, COL_DOS_MILIEU_GAUCHE,COL_DOS_MILIEU_DROITE,COL_DOS_BAS}, COL_ID + " LIKE \"" + id + "\"",null,null,null,null);
        Mesure mes = cursorToMesure(c);
        //c.close();
        return mes;
    }
    public Mesure getMesureWithMail(String mail) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_SENSORS, new String[]{COL_ID, COL_DATEHEURE,COL_MAILUSER,COL_ASSISE_AVANT ,COL_ASSISE_ARRIERE,COL_ASSISE_GAUCHE,COL_ASSISE_DROITE,COL_DOS_HAUT,COL_DOS_MILIEU_GAUCHE,COL_DOS_MILIEU_DROITE,COL_DOS_BAS}, COL_MAILUSER  + " LIKE \"" + mail  + "\"",null,null,null,null);
        Mesure mes = cursorToMesure(c);
        //c.close();
        return mes;
    }
    public Mesure getMesureWithMailAndDate(String mail, String date) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        //Cursor c = bdd.query(TABLE_SENSORS, new String[]{COL_ID, COL_DATEHEURE,COL_MAILUSER,COL_ASSISE_AVANT ,COL_ASSISE_ARRIERE,COL_ASSISE_GAUCHE,COL_ASSISE_DROITE,COL_DOS_HAUT_GAUCHE, COL_DOS_HAUT_DROITE,COL_DOS_MILIEU_GAUCHE,COL_DOS_MILIEU_DROITE,COL_DOS_BAS}, COL_MAILUSER+ COL_DATEHEURE + " LIKE \"" + mail+ date + "\"",null,null,null,null);
        Cursor c = bdd.rawQuery("select * from "+ TABLE_SENSORS+" where "+ COL_MAILUSER+" = '"+mail+"' and "+COL_DATEHEURE+" = '"+date+"'", null);
        c.moveToLast();
        Mesure mes = cursorToMesure(c);
        //c.close();
        return mes;
    }
    public User getUserWithName(String name) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son nom)
        Cursor c = bdd.query(TABLE_USERS, new String[]{COL_ID_U, COL_MAIL, COL_NAME, COL_PASSWORD, COL_BIRTHYEAR, COL_SIGNUPDATE}, COL_NAME + " LIKE \"" + name + "\"", null, null, null, null);
        User ctu = cursorToUser(c);
        c.close();
        return ctu;
    }

    public Day getDayWithUserID(int id) {
            //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son nom)
            Cursor c = bdd.query(TABLE_DAY, new String[]{COL_ID_TABLE_DAY, COL_ID_USER,
                    COL_6h, COL_6h15, COL_6h30, COL_6h45,
                    COL_7h, COL_7h15, COL_7h30, COL_7h45,
                    COL_8h, COL_8h15, COL_8h30, COL_8h45,
                    COL_9h, COL_9h15, COL_9h30, COL_9h45,
                    COL_10h, COL_10h15, COL_10h30, COL_10h45,
                    COL_11h, COL_11h15, COL_11h30, COL_11h45,
                    COL_12h, COL_12h15, COL_12h30, COL_12h45,
                    COL_13h, COL_13h15, COL_13h30, COL_13h45,
                    COL_14h, COL_14h15, COL_14h30, COL_14h45,
                    COL_15h, COL_15h15, COL_15h30, COL_15h45,
                    COL_16h, COL_16h15, COL_16h30, COL_16h45,
                    COL_17h, COL_17h15, COL_17h30, COL_17h45,
                    COL_18h, COL_18h15, COL_18h30, COL_18h45,
                    COL_19h, COL_19h15, COL_19h30, COL_19h45,
                    COL_20h, COL_20h15, COL_20h30, COL_20h45
            }, COL_ID_USER + " LIKE \"" + id + "\"", null, null, null, null);
        Day ctu = cursorToDay(c);
            c.close();
            return ctu;
        }
    public Day getDayWithID(int id) {
            //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son nom)
            Cursor c = bdd.query(TABLE_DAY, new String[]{COL_ID_TABLE_DAY, COL_ID_USER,
                    COL_6h, COL_6h15, COL_6h30, COL_6h45,
                    COL_7h, COL_7h15, COL_7h30, COL_7h45,
                    COL_8h, COL_8h15, COL_8h30, COL_8h45,
                    COL_9h, COL_9h15, COL_9h30, COL_9h45,
                    COL_10h, COL_10h15, COL_10h30, COL_10h45,
                    COL_11h, COL_11h15, COL_11h30, COL_11h45,
                    COL_12h, COL_12h15, COL_12h30, COL_12h45,
                    COL_13h, COL_13h15, COL_13h30, COL_13h45,
                    COL_14h, COL_14h15, COL_14h30, COL_14h45,
                    COL_15h, COL_15h15, COL_15h30, COL_15h45,
                    COL_16h, COL_16h15, COL_16h30, COL_16h45,
                    COL_17h, COL_17h15, COL_17h30, COL_17h45,
                    COL_18h, COL_18h15, COL_18h30, COL_18h45,
                    COL_19h, COL_19h15, COL_19h30, COL_19h45,
                    COL_20h, COL_20h15, COL_20h30, COL_20h45
            }, COL_ID_TABLE_DAY + " LIKE \"" + id + "\"", null, null, null, null);
        Day ctu = cursorToDay(c);
            c.close();
            return ctu;
        }


    public Smiley getSmileyWithId(int id) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_SMILEYS, new String[]{COL_ID_SMILEY, COL_ID_USER,COL_DAY,COL_SMILEY}, COL_ID_SMILEY + " LIKE \"" + id + "\"",null,null,null,null);
        Smiley smil = cursorToSmiley(c);
        //c.close();
        return smil;
    }
    public  List<User> getAllUsers(){
        Cursor cursor = bdd.rawQuery("select * from " +TABLE_USERS ,null);
       // Cursor cursor = bdd.query(TABLE_USERS, new String[]{COL_MAIL, COL_NAME, COL_PASSWORD, COL_BIRTHYEAR},null, null, null, null, null);
       List<User> names = new ArrayList<User>();
     //  cursor.moveToFirst();
        for(int i=0; i<cursor.getCount(); i++){
            names.add(cursorToUser(cursor));
            cursor.moveToNext();
        }
        //cursor.close();

        return names;
    }
    public  List<Mesure> getAllMesures(){
        Cursor cu = bdd.rawQuery("select * from " +TABLE_SENSORS ,null);
       List<Mesure> names = new ArrayList<Mesure>();
     //  cursor.moveToFirst();
        for(int i=0; i<cu.getCount(); i++){
            names.add(cursorToMesure(cu));
            cu.moveToNext();
        }
        //cursor.close();

        return names;
    }

    public  List<Smiley> getAllSmileys(){
        Cursor cu = bdd.rawQuery("select * from " +TABLE_SMILEYS ,null);
       List<Smiley> list = new ArrayList<Smiley>();
     //  cursor.moveToFirst();
        for(int i=0; i<cu.getCount(); i++){
            list.add(cursorToSmiley(cu));
            cu.moveToNext();
        }
        //cursor.close();

        return list;
    }
    public  List<Smiley> getAllSmileysofUser(int iduser){
        Cursor cu = bdd.rawQuery("select * from " +TABLE_SMILEYS+ " where "+COL_ID_USER +" = '"+iduser+"'",null);
       List<Smiley> list = new ArrayList<Smiley>();
     //  cursor.moveToFirst();
        for(int i=0; i<cu.getCount(); i++){
            list.add(cursorToSmiley(cu));
            cu.moveToNext();
        }
        //cursor.close();

        return list;
    }

    public  List<Day> getAllDay(){
        Cursor cu = bdd.rawQuery("select * from " +TABLE_DAY ,null);
       List<Day> list = new ArrayList<Day>();
     //  cursor.moveToFirst();
        for(int i=0; i<cu.getCount(); i++){
            list.add(cursorToDay(cu));
            cu.moveToNext();
        }
        //cursor.close();

        return list;
    }

    //Cette méthode permet de convertir un cursor en un user
    private User cursorToUser(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un user
        User user = new User();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        user.setId(c.getInt(NUM_COL_ID_U));
        user.setMail(c.getString(NUM_COL_MAIL));
        user.setName(c.getString(NUM_COL_NAME));
        user.setPassword(c.getString(NUM_COL_PASSWORD));
        user.setBirthyear(c.getInt(NUM_COL_BIRTHYEAR));
        user.setSignupdate(c.getString(NUM_COL_SIGNUPDATE));
        //On ferme le cursor
        //c.close();

        //On retourne le user
        return user;
    }
    private Mesure cursorToMesure(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un user
        Mesure mesure = new Mesure();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        mesure.setId(c.getInt(NUM_COL_ID));
        mesure.setDate(c.getString(NUM_COL_DATEHEURE));
        mesure.setMailuser(c.getString(NUM_COL_MAILUSER));
        mesure.setDos_haut(c.getInt(NUM_COL_DOS_HAUT));
        mesure.setDos_milieu_droite(c.getInt(NUM_COL_DOS_MILIEU_DROITE));
        mesure.setDos_milieu_gauche(c.getInt(NUM_COL_DOS_MILIEU_GAUCHE));
        mesure.setDos_bas(c.getInt(NUM_COL_DOS_BAS));
        mesure.setAssise_arriere(c.getInt(NUM_COL_ASSISE_ARRIERE));
        mesure.setAssise_avant(c.getInt(NUM_COL_ASSISE_AVANT));
        mesure.setAssise_gauche(c.getInt(NUM_COL_ASSISE_GAUCHE));
        mesure.setAssise_droite(c.getInt(NUM_COL_ASSISE_DROITE));
        //On ferme le cursor
        //c.close();

        //On retourne le user
        return mesure;
    }

    private Smiley cursorToSmiley(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un user
        Smiley smiley = new Smiley();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        smiley.setCol_id_smiley(c.getInt(NUM_COL_ID_SMILEY));
        smiley.setCol_id_user(c.getInt(NUM_COL_ID_USER));
        smiley.setCol_day(c.getString(NUM_COL_DAY));
        smiley.setCol_smiley(c.getString(NUM_COL_SMILEY));
        //On ferme le cursor
        //c.close();

        //On retourne le user
        return smiley;
    }

    private Day cursorToDay(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un user
        Day day = new Day();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        day.setCol_id_day(c.getInt(NUM_COL_ID_TABLE_DAY));
        day.setCol_id_user(c.getInt(NUM_COL_ID_USER));

        day.setCol_6h(c.getString(NUM_COL_6h));
        day.setCol_6h15(c.getString(NUM_COL_6h15));
        day.setCol_6h30(c.getString(NUM_COL_6h30));
        day.setCol_6h45(c.getString(NUM_COL_6h45));

        day.setCol_7h(c.getString(NUM_COL_7h));
        day.setCol_7h15(c.getString(NUM_COL_7h15));
        day.setCol_7h30(c.getString(NUM_COL_7h30));
        day.setCol_7h45(c.getString(NUM_COL_7h45));

        day.setCol_8h(c.getString(NUM_COL_8h));
        day.setCol_8h15(c.getString(NUM_COL_8h15));
        day.setCol_8h30(c.getString(NUM_COL_8h30));
        day.setCol_8h45(c.getString(NUM_COL_8h45));

        day.setCol_9h(c.getString(NUM_COL_9h));
        day.setCol_9h15(c.getString(NUM_COL_9h15));
        day.setCol_9h30(c.getString(NUM_COL_9h30));
        day.setCol_9h45(c.getString(NUM_COL_9h45));

        day.setCol_10h(c.getString(NUM_COL_10h));
        day.setCol_10h15(c.getString(NUM_COL_10h15));
        day.setCol_10h30(c.getString(NUM_COL_10h30));
        day.setCol_10h45(c.getString(NUM_COL_10h45));

        day.setCol_11h(c.getString(NUM_COL_11h));
        day.setCol_11h15(c.getString(NUM_COL_11h15));
        day.setCol_11h30(c.getString(NUM_COL_11h30));
        day.setCol_11h45(c.getString(NUM_COL_11h45));

        day.setCol_12h(c.getString(NUM_COL_12h));
        day.setCol_12h15(c.getString(NUM_COL_12h15));
        day.setCol_12h30(c.getString(NUM_COL_12h30));
        day.setCol_12h45(c.getString(NUM_COL_12h45));

        day.setCol_13h(c.getString(NUM_COL_13h));
        day.setCol_13h15(c.getString(NUM_COL_13h15));
        day.setCol_13h30(c.getString(NUM_COL_13h30));
        day.setCol_13h45(c.getString(NUM_COL_13h45));

        day.setCol_14h(c.getString(NUM_COL_14h));
        day.setCol_14h15(c.getString(NUM_COL_14h15));
        day.setCol_14h30(c.getString(NUM_COL_14h30));
        day.setCol_14h45(c.getString(NUM_COL_14h45));

        day.setCol_15h(c.getString(NUM_COL_15h));
        day.setCol_15h15(c.getString(NUM_COL_15h15));
        day.setCol_15h30(c.getString(NUM_COL_15h30));
        day.setCol_15h45(c.getString(NUM_COL_15h45));

        day.setCol_16h(c.getString(NUM_COL_16h));
        day.setCol_16h15(c.getString(NUM_COL_16h15));
        day.setCol_16h30(c.getString(NUM_COL_16h30));
        day.setCol_16h45(c.getString(NUM_COL_16h45));

        day.setCol_17h(c.getString(NUM_COL_17h));
        day.setCol_17h15(c.getString(NUM_COL_17h15));
        day.setCol_17h30(c.getString(NUM_COL_17h30));
        day.setCol_17h45(c.getString(NUM_COL_17h45));

        day.setCol_18h(c.getString(NUM_COL_18h));
        day.setCol_18h15(c.getString(NUM_COL_18h15));
        day.setCol_18h30(c.getString(NUM_COL_18h30));
        day.setCol_18h45(c.getString(NUM_COL_18h45));

        day.setCol_19h(c.getString(NUM_COL_19h));
        day.setCol_19h15(c.getString(NUM_COL_19h15));
        day.setCol_19h30(c.getString(NUM_COL_19h30));
        day.setCol_19h45(c.getString(NUM_COL_19h45));

        day.setCol_20h(c.getString(NUM_COL_20h));
        day.setCol_20h15(c.getString(NUM_COL_20h15));
        day.setCol_20h30(c.getString(NUM_COL_20h30));
        day.setCol_20h45(c.getString(NUM_COL_20h45));

        //On ferme le cursor
        //c.close();

        //On retourne le user
        return day;
    }
}
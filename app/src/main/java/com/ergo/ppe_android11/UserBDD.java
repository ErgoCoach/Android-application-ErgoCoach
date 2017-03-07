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
    private static final String COL_MAIL = "Mail";
    private static final int NUM_COL_MAIL = 0;
    private static final String COL_NAME = "Nom";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_PASSWORD = "Password";
    private static final int NUM_COL_PASSWORD = 2;
    private static final String COL_BIRTHYEAR = "BirthYear";
    private static final int NUM_COL_BIRTHYEAR = 3;
    private static final String COL_SIGNUPDATE = "SignUpDate";
    private static final int NUM_COL_SIGNUPDATE = 4;

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
    private static final String COL_DOS_HAUT_GAUCHE = "DosHautGauche";
    private static final int NUM_COL_DOS_HAUT_GAUCHE = 7;
    private static final String COL_DOS_HAUT_DROITE = "DosHautDroite";
    private static final int NUM_COL_DOS_HAUT_DROITE = 8;
    private static final String COL_DOS_MILIEU_GAUCHE = "DosMilieuGauche";
    private static final int NUM_COL_DOS_MILIEU_GAUCHE = 9;
    private static final String COL_DOS_MILIEU_DROITE = "DosMilieuDroite";
    private static final int NUM_COL_DOS_MILIEU_DROITE = 10;
    private static final String COL_DOS_BAS = "DosBas";
    private static final int NUM_COL_DOS_BAS = 11;


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
        values.put(COL_DOS_HAUT_DROITE, mesure.getDos_haut_droite());
        values.put(COL_DOS_HAUT_GAUCHE, mesure.getDos_haut_gauche());
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
    public long updateMesure(int id, Mesure mesure) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, mesure.getId());
        values.put(COL_DATEHEURE, String.valueOf(mesure.getDate()));
        values.put(COL_MAILUSER, mesure.getMailuser());
        values.put(COL_DOS_HAUT_DROITE, mesure.getDos_haut_droite());
        values.put(COL_DOS_HAUT_GAUCHE, mesure.getDos_haut_gauche());
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

    public int removeUserWithMail(String mail) {
        //Suppression d'un user de la BDD grâce au mail
        return bdd.delete(TABLE_USERS, COL_MAIL + " = " + mail, null);
    }
    public int removeMesureWithId(int id) {
        //Suppression d'une mesure de la BDD grâce a l'id
        return bdd.delete(TABLE_SENSORS, COL_ID + " = " + id, null);
    }

    public int removeAll() {
        return bdd.delete(TABLE_USERS, null , null);
    }
    public int removeAllMesures() {return bdd.delete(TABLE_SENSORS, null , null);}

    public User getUserWithMail(String mail) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_USERS, new String[]{ COL_MAIL, COL_NAME, COL_PASSWORD, COL_BIRTHYEAR, COL_SIGNUPDATE}, COL_MAIL + " LIKE \"" + mail + "\"", null, null, null, null);
        User ctu = cursorToUser(c);
        c.close();
        return ctu;
    }
    public Mesure getMesureWithId(int id) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_SENSORS, new String[]{COL_ID, COL_DATEHEURE,COL_MAILUSER,COL_ASSISE_AVANT ,COL_ASSISE_ARRIERE,COL_ASSISE_GAUCHE,COL_ASSISE_DROITE,COL_DOS_HAUT_GAUCHE, COL_DOS_HAUT_DROITE,COL_DOS_MILIEU_GAUCHE,COL_DOS_MILIEU_DROITE,COL_DOS_BAS}, COL_ID + " LIKE \"" + id + "\"",null,null,null,null);
        Mesure mes = cursorToMesure(c);
        //c.close();
        return mes;
    }
    public Mesure getMesureWithMail(String mail) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son mail)
        Cursor c = bdd.query(TABLE_SENSORS, new String[]{COL_ID, COL_DATEHEURE,COL_MAILUSER,COL_ASSISE_AVANT ,COL_ASSISE_ARRIERE,COL_ASSISE_GAUCHE,COL_ASSISE_DROITE,COL_DOS_HAUT_GAUCHE, COL_DOS_HAUT_DROITE,COL_DOS_MILIEU_GAUCHE,COL_DOS_MILIEU_DROITE,COL_DOS_BAS}, COL_MAILUSER + " LIKE \"" + mail + "\"",null,null,null,null);
        Mesure mes = cursorToMesure(c);
        //c.close();
        return mes;
    }
    public User getUserWithName(String name) {
        //Récupère dans un Cursor les valeur correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son nom)
        Cursor c = bdd.query(TABLE_USERS, new String[]{COL_MAIL, COL_NAME, COL_PASSWORD, COL_BIRTHYEAR, COL_SIGNUPDATE}, COL_NAME + " LIKE \"" + name + "\"", null, null, null, null);
        User ctu = cursorToUser(c);
        c.close();
        return ctu;
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
        mesure.setDos_haut_droite(c.getInt(NUM_COL_DOS_HAUT_DROITE));
        mesure.setDos_haut_gauche(c.getInt(NUM_COL_DOS_HAUT_GAUCHE));
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
}
package com.ergo.ergo10;

/**
 * Created by sophie on 01/12/2016.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.provider.ContactsContract;

public class ManageDatabase {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "ergo.db";

    private static final String TABLE_USERS = "user";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_PSEUDO = "pseudo";
    private static final int NUM_COL_PSEUDO = 1;
    private static final String COL_MDP = "motdepasse";
    private static final int NUM_COL_MDP = 2;

    private SQLiteDatabase bdd;

    private Database maBaseSQLite;

    public ManageDatabase(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new Database(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUser(User user){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_PSEUDO, user.getPseudo());
        values.put(COL_MDP, user.getMotdepasse());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_USERS, null, values);
    }

    public int updateUser(int id, User user){
        //La mise à jour d'un User dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle User on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_PSEUDO, user.getPseudo());
        values.put(COL_MDP, user.getMotdepasse());
        return bdd.update(TABLE_USERS, values, COL_ID + " = " +id, null);
    }

    public int removeUserWithID(long id){
        //Suppression d'un User de la BDD grâce à l'ID
        return bdd.delete(TABLE_USERS, COL_ID + " = " +id, null);
    }

    public User getUserWithTitre(String titre){
        //Récupère dans un Cursor les valeur correspondant à un User contenu dans la BDD (ici on sélectionne le User grâce à son pseudo titre)
        Cursor c = bdd.query(TABLE_USERS, new String[] {COL_ID ,COL_PSEUDO, COL_MDP}, COL_PSEUDO + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToUser(c);
    }

    //Cette méthode permet de convertir un cursor en un User
    private User cursorToUser(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un User
        User user = new User();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        user.setId(c.getInt(NUM_COL_ID));
        user.setPseudo(c.getString(NUM_COL_PSEUDO));
        user.setMotdepasse(c.getString(NUM_COL_MDP));
        //On ferme le cursor
        c.close();

        //On retourne le user
        return user;
    }
}

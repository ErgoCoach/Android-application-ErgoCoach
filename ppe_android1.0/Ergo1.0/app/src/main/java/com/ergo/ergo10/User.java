package com.ergo.ergo10;

/**
 * Created by sophie on 01/12/2016.
 */

public class User {

    private long id;
    private String pseudo;
    private String motdepasse;
 /*   private String nom;
    private String prenom;
    private String mail;
    private int poids;
    private int taille;
    private int annee_de_naissance;*/

    public User(long id, String pseudo, String motdepasse/*, String nom, String prenom, String mail, int poids, int taille, int annee_de_naissance*/) {
        super();
        this.id = id;
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        /*this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.poids = poids;
        this.taille = taille;
        this.annee_de_naissance = annee_de_naissance;*/
    }

    public User() {

    }
/*
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    /*public int getAnnee_de_naissance() {
        return annee_de_naissance;
    }
    public void setAnnee_de_naissance(int motdepasse) {
        this.annee_de_naissance = annee_de_naissance;
    }*/
}

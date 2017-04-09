package com.ergo.ppe_android11;




public class Mesure {

    private int id;
    private String date;
    private String mailuser;

    private int dos_haut;
    private int dos_milieu_droite;
    private int dos_milieu_gauche;
    private int dos_bas;

    private int assise_avant;
    private int assise_arriere;
    private int assise_droite;
    private int assise_gauche;

    public Mesure(){}
    public Mesure(String date, String mailuser, int dos_haut, int dos_milieu_droite, int dos_milieu_gauche, int dos_bas, int assise_avant, int assise_arriere, int assise_droite, int assise_gauche){
        this.date = date;
        this.mailuser = mailuser;
        this.dos_haut = dos_haut;
        this.dos_milieu_droite = dos_milieu_droite;
        this.dos_milieu_gauche = dos_milieu_gauche;
        this.dos_bas = dos_bas;
        this.assise_avant = assise_avant;
        this.assise_arriere = assise_arriere;
        this.assise_droite = assise_droite;
        this.assise_gauche = assise_gauche;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMailuser() {
        return mailuser;
    }

    public void setMailuser(String mailuser) {
        this.mailuser = mailuser;
    }

    public int getDos_haut() {
        return dos_haut;
    }

    public void setDos_haut(int dos_haut) {
        this.dos_haut = dos_haut;
    }


    public int getDos_milieu_droite() {
        return dos_milieu_droite;
    }

    public void setDos_milieu_droite(int dos_milieu_droite) {
        this.dos_milieu_droite = dos_milieu_droite;
    }

    public int getDos_milieu_gauche() {
        return dos_milieu_gauche;
    }

    public void setDos_milieu_gauche(int dos_milieu_gauche) {
        this.dos_milieu_gauche = dos_milieu_gauche;
    }

    public int getDos_bas() {
        return dos_bas;
    }

    public void setDos_bas(int dos_bas) {
        this.dos_bas = dos_bas;
    }

    public int getAssise_avant() {
        return assise_avant;
    }

    public void setAssise_avant(int assise_avant) {
        this.assise_avant = assise_avant;
    }

    public int getAssise_arriere() {
        return assise_arriere;
    }

    public void setAssise_arriere(int assise_arriere) {
        this.assise_arriere = assise_arriere;
    }

    public int getAssise_droite() {
        return assise_droite;
    }

    public void setAssise_droite(int assise_droite) {
        this.assise_droite = assise_droite;
    }

    public int getAssise_gauche() {
        return assise_gauche;
    }

    public void setAssise_gauche(int assise_gauche) {
        this.assise_gauche = assise_gauche;
    }
    @Override
    public String toString() {
        return "\n id=" + id +
                ",\n date=" + date +
                ",\n mailuser='" + mailuser + '\'' +
                ",\n dos_haut=" + dos_haut +
                ",\n dos_milieu_droite=" + dos_milieu_droite +
                ",\n dos_milieu_gauche=" + dos_milieu_gauche +
                ",\n dos_bas=" + dos_bas +
                ",\n assise_avant=" + assise_avant +
                ",\n assise_arriere=" + assise_arriere +
                ",\n assise_droite=" + assise_droite +
                ",\n assise_gauche=" + assise_gauche;
    }
}

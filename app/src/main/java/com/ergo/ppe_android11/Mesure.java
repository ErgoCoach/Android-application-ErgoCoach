package com.ergo.ppe_android11;




public class Mesure {

    private int id;
    private String date;
    private String mailuser;

    private int dos_haut_droite;
    private int dos_haut_gauche;
    private int dos_milieu_droite;
    private int dos_milieu_gauche;
    private int dos_bas;

    private int assise_avant;
    private int assise_arriere;
    private int assise_droite;
    private int assise_gauche;

    public Mesure(){}
    public Mesure(String date, String mailuser, int dos_haut_droite, int dos_haut_gauche, int dos_milieu_droite, int dos_milieu_gauche, int dos_bas, int assise_avant, int assise_arriere, int assise_droite, int assise_gauche){
        this.date = date;
        this.mailuser = mailuser;
        this.dos_haut_droite = dos_haut_droite;
        this.dos_haut_gauche = dos_haut_gauche;
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

    public int getDos_haut_droite() {
        return dos_haut_droite;
    }

    public void setDos_haut_droite(int dos_haut_droite) {
        this.dos_haut_droite = dos_haut_droite;
    }

    public int getDos_haut_gauche() {
        return dos_haut_gauche;
    }

    public void setDos_haut_gauche(int dos_haut_gauche) {
        this.dos_haut_gauche = dos_haut_gauche;
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
        return "Mesure{" +
                "id=" + id +
                ", date=" + date +
                ", mailuser='" + mailuser + '\'' +
                ", dos_haut_droite=" + dos_haut_droite +
                ", dos_haut_gauche=" + dos_haut_gauche +
                ", dos_milieu_droite=" + dos_milieu_droite +
                ", dos_milieu_gauche=" + dos_milieu_gauche +
                ", dos_bas=" + dos_bas +
                ", assise_avant=" + assise_avant +
                ", assise_arriere=" + assise_arriere +
                ", assise_droite=" + assise_droite +
                ", assise_gauche=" + assise_gauche +
                '}';
    }
}

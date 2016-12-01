package com.ergo.ergo10;

/**
 * Created by sophie on 01/12/2016.
 */

public class Mesure {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getAssise_avant_droite() {
        return assise_avant_droite;
    }

    public void setAssise_avant_droite(int assise_avant_droite) {
        this.assise_avant_droite = assise_avant_droite;
    }

    public int getAssise_avant_gauche() {
        return assise_avant_gauche;
    }

    public void setAssise_avant_gauche(int assise_avant_gauche) {
        this.assise_avant_gauche = assise_avant_gauche;
    }

    public int getAssise_arriere_droite() {
        return assise_arriere_droite;
    }

    public void setAssise_arriere_droite(int assise_arriere_droite) {
        this.assise_arriere_droite = assise_arriere_droite;
    }

    public int getAssise_arriere_gauche() {
        return assise_arriere_gauche;
    }

    public void setAssise_arriere_gauche(int assise_arriere_gauche) {
        this.assise_arriere_gauche = assise_arriere_gauche;
    }

    public int getDossier_haut_droite() {
        return dossier_haut_droite;
    }

    public void setDossier_haut_droite(int dossier_haut_droite) {
        this.dossier_haut_droite = dossier_haut_droite;
    }

    public int getDossier_haut_gauche() {
        return dossier_haut_gauche;
    }

    public void setDossier_haut_gauche(int dossier_haut_gauche) {
        this.dossier_haut_gauche = dossier_haut_gauche;
    }

    public int getDossier_bas_droite() {
        return dossier_bas_droite;
    }

    public void setDossier_bas_droite(int dossier_bas_droite) {
        this.dossier_bas_droite = dossier_bas_droite;
    }

    public int getDossier_bas_gauche() {
        return dossier_bas_gauche;
    }

    public void setDossier_bas_gauche(int dossier_bas_gauche) {
        this.dossier_bas_gauche = dossier_bas_gauche;
    }

    public int getDossier_milieu_droite() {
        return dossier_milieu_droite;
    }

    public void setDossier_milieu_droite(int dossier_milieu_droite) {
        this.dossier_milieu_droite = dossier_milieu_droite;
    }

    public int getDossier_milieu_gauche() {
        return dossier_milieu_gauche;
    }

    public void setDossier_milieu_gauche(int dossier_milieu_gauche) {
        this.dossier_milieu_gauche = dossier_milieu_gauche;
    }

    private long id;
    private int date;
    private int heure;
    private int assise_avant_droite;
    private int assise_avant_gauche;
    private int assise_arriere_droite;
    private int assise_arriere_gauche;

    private int dossier_haut_droite;
    private int dossier_haut_gauche;
    private int dossier_bas_droite;
    private int dossier_bas_gauche;
    private int dossier_milieu_droite;
    private int dossier_milieu_gauche;

    public Mesure(long id, int date, int heure, int assise_avant_droite, int assise_avant_gauche, int assise_arriere_droite, int assise_arriere_gauche, int dossier_haut_droite, int dossier_haut_gauche,int dossier_bas_droite, int dossier_bas_gauche, int dossier_milieu_droite, int dossier_milieu_gauche) {
        super();
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.assise_avant_droite = assise_avant_droite;
        this.assise_avant_gauche = assise_avant_gauche;
        this.assise_arriere_droite = assise_arriere_droite;
        this.assise_arriere_gauche = assise_arriere_gauche;
        this.dossier_haut_droite = dossier_haut_droite;
        this.dossier_haut_gauche = dossier_haut_gauche;
        this.dossier_bas_droite = dossier_bas_droite;
        this.dossier_bas_gauche = dossier_bas_gauche;
        this.dossier_haut_droite = dossier_haut_droite;
        this.dossier_milieu_droite = dossier_milieu_droite;
        this.dossier_milieu_gauche = dossier_milieu_gauche;
    }
}

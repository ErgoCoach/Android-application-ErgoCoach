package com.ergo.ppe_android11;

public class Smiley {

    private int col_id_smiley;
    private int col_id_user;
    private String col_day;
    private String col_smiley;

    public Smiley(){}

    public Smiley( int col_id_user, String col_day, String col_smiley) {
        //this.col_id_smiley = col_id_smiley;
        this.col_id_user = col_id_user;
        this.col_day = col_day;
        this.col_smiley = col_smiley;
    }

    public int getCol_id_smiley() {
        return col_id_smiley;
    }

    public void setCol_id_smiley(int col_id_smiley) {
        this.col_id_smiley = col_id_smiley;
    }

    public int getCol_id_user() {
        return col_id_user;
    }

    public void setCol_id_user(int col_id_user) {
        this.col_id_user = col_id_user;
    }

    public String getCol_day() {
        return col_day;
    }

    public void setCol_day(String col_day) {
        this.col_day = col_day;
    }

    public String getCol_smiley() {
        return col_smiley;
    }

    public void setCol_smiley(String col_smiley) {
        this.col_smiley = col_smiley;
    }

    @Override
    public String toString() {
        return "Smiley{" +
                "col_id_smiley='" + col_id_smiley + '\'' +
                ", col_id_user='" + col_id_user + '\'' +
                ", col_day=" + col_day +
                ", col_smiley='" + col_smiley + '\'' +
                '}';
    }
}

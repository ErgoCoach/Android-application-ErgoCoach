package com.ergo.ppe_android11;

import java.sql.Date;

public class Day {

    private  int col_id_day ;
    private  int col_id_user ;
    
    private  String col_6h;
    private  String col_6h15;
    private  String col_6h30;
    private  String col_6h45 ;
    private  String col_7h ;
    private  String col_7h15 ;
    private  String col_7h30  ;
    private  String col_7h45 ;
    private  String col_8h ;
    private  String col_8h15 ;
    private  String col_8h30  ;
    private  String col_8h45 ;
    private  String col_9h ;
    private  String col_9h15 ;
    private  String col_9h30  ;
    private  String col_9h45 ;
    private  String col_10h ;
    private  String col_10h15 ;
    private  String col_10h30  ;
    private  String col_10h45 ;
    private  String col_11h ;
    private  String col_11h15 ;
    private  String col_11h30  ;
    private  String col_11h45 ;
    private  String col_12h ;
    private  String col_12h15 ;
    private  String col_12h30  ;
    private  String col_12h45 ;

    private  String col_13h ;
    private  String col_13h15 ;
    private  String col_13h30  ;
    private  String col_13h45 ;
    private  String col_14h ;
    private  String col_14h15 ;
    private  String col_14h30  ;
    private  String col_14h45 ;
    private  String col_15h ;
    private  String col_15h15 ;
    private  String col_15h30  ;
    private  String col_15h45 ;
    private  String col_16h ;
    private  String col_16h15 ;
    private  String col_16h30  ;
    private  String col_16h45 ;
    private  String col_17h ;
    private  String col_17h15 ;
    private  String col_17h30  ;
    private  String col_17h45 ;
    private  String col_18h ;
    private  String col_18h15 ;
    private  String col_18h30  ;
    private  String col_18h45 ;
    private  String col_19h ;
    private  String col_19h15 ;
    private  String col_19h30  ;
    private  String col_19h45 ;
    private  String col_20h ;
    private  String col_20h15 ;
    private  String col_20h30  ;
    private  String col_20h45 ;

    public Day(){}

    public Day( int col_id_user, String col_6h, String col_6h15, String col_6h30, String col_6h45, String col_7h, String col_7h15, String col_7h30, String col_7h45, String col_8h, String col_8h15, String col_8h30, String col_8h45, String col_9h, String col_9h15, String col_9h30, String col_9h45, String col_10h, String col_10h15, String col_10h30, String col_10h45, String col_11h, String col_11h15, String col_11h30, String col_11h45, String col_12h, String col_12h15, String col_12h30, String col_12h45, String col_13h, String col_13h15, String col_13h30, String col_13h45, String col_14h, String col_14h15, String col_14h30, String col_14h45, String col_15h, String col_15h15, String col_15h30, String col_15h45, String col_16h, String col_16h15, String col_16h30, String col_16h45, String col_17h, String col_17h15, String col_17h30, String col_17h45, String col_18h, String col_18h15, String col_18h30, String col_18h45, String col_19h, String col_19h15, String col_19h30, String col_19h45, String col_20h, String col_20h15, String col_20h30, String col_20h45) {
        this.col_id_user = col_id_user;
        this.col_6h = col_6h;
        this.col_6h15 = col_6h15;
        this.col_6h30 = col_6h30;
        this.col_6h45 = col_6h45;
        this.col_7h = col_7h;
        this.col_7h15 = col_7h15;
        this.col_7h30 = col_7h30;
        this.col_7h45 = col_7h45;
        this.col_8h = col_8h;
        this.col_8h15 = col_8h15;
        this.col_8h30 = col_8h30;
        this.col_8h45 = col_8h45;
        this.col_9h = col_9h;
        this.col_9h15 = col_9h15;
        this.col_9h30 = col_9h30;
        this.col_9h45 = col_9h45;
        this.col_10h = col_10h;
        this.col_10h15 = col_10h15;
        this.col_10h30 = col_10h30;
        this.col_10h45 = col_10h45;
        this.col_11h = col_11h;
        this.col_11h15 = col_11h15;
        this.col_11h30 = col_11h30;
        this.col_11h45 = col_11h45;
        this.col_12h = col_12h;
        this.col_12h15 = col_12h15;
        this.col_12h30 = col_12h30;
        this.col_12h45 = col_12h45;
        this.col_13h = col_13h;
        this.col_13h15 = col_13h15;
        this.col_13h30 = col_13h30;
        this.col_13h45 = col_13h45;
        this.col_14h = col_14h;
        this.col_14h15 = col_14h15;
        this.col_14h30 = col_14h30;
        this.col_14h45 = col_14h45;
        this.col_15h = col_15h;
        this.col_15h15 = col_15h15;
        this.col_15h30 = col_15h30;
        this.col_15h45 = col_15h45;
        this.col_16h = col_16h;
        this.col_16h15 = col_16h15;
        this.col_16h30 = col_16h30;
        this.col_16h45 = col_16h45;
        this.col_17h = col_17h;
        this.col_17h15 = col_17h15;
        this.col_17h30 = col_17h30;
        this.col_17h45 = col_17h45;
        this.col_18h = col_18h;
        this.col_18h15 = col_18h15;
        this.col_18h30 = col_18h30;
        this.col_18h45 = col_18h45;
        this.col_19h = col_19h;
        this.col_19h15 = col_19h15;
        this.col_19h30 = col_19h30;
        this.col_19h45 = col_19h45;
        this.col_20h = col_20h;
        this.col_20h15 = col_20h15;
        this.col_20h30 = col_20h30;
        this.col_20h45 = col_20h45;
    }

    public int getCol_id_day() {
        return col_id_day;
    }

    public void setCol_id_day(int col_id_day) {
        this.col_id_day = col_id_day;
    }

    public int getCol_id_user() {
        return col_id_user;
    }

    public void setCol_id_user(int col_id_user) {
        this.col_id_user = col_id_user;
    }

    public String getCol_6h() {
        return col_6h;
    }

    public void setCol_6h(String col_6h) {
        this.col_6h = col_6h;
    }

    public String getCol_6h15() {
        return col_6h15;
    }

    public void setCol_6h15(String col_6h15) {
        this.col_6h15 = col_6h15;
    }

    public String getCol_6h30() {
        return col_6h30;
    }

    public void setCol_6h30(String col_6h30) {
        this.col_6h30 = col_6h30;
    }

    public String getCol_6h45() {
        return col_6h45;
    }

    public void setCol_6h45(String col_6h45) {
        this.col_6h45 = col_6h45;
    }

    public String getCol_7h() {
        return col_7h;
    }

    public void setCol_7h(String col_7h) {
        this.col_7h = col_7h;
    }

    public String getCol_7h15() {
        return col_7h15;
    }

    public void setCol_7h15(String col_7h15) {
        this.col_7h15 = col_7h15;
    }

    public String getCol_7h30() {
        return col_7h30;
    }

    public void setCol_7h30(String col_7h30) {
        this.col_7h30 = col_7h30;
    }

    public String getCol_7h45() {
        return col_7h45;
    }

    public void setCol_7h45(String col_7h45) {
        this.col_7h45 = col_7h45;
    }

    public String getCol_8h() {
        return col_8h;
    }

    public void setCol_8h(String col_8h) {
        this.col_8h = col_8h;
    }

    public String getCol_8h15() {
        return col_8h15;
    }

    public void setCol_8h15(String col_8h15) {
        this.col_8h15 = col_8h15;
    }

    public String getCol_8h30() {
        return col_8h30;
    }

    public void setCol_8h30(String col_8h30) {
        this.col_8h30 = col_8h30;
    }

    public String getCol_8h45() {
        return col_8h45;
    }

    public void setCol_8h45(String col_8h45) {
        this.col_8h45 = col_8h45;
    }

    public String getCol_9h() {
        return col_9h;
    }

    public void setCol_9h(String col_9h) {
        this.col_9h = col_9h;
    }

    public String getCol_9h15() {
        return col_9h15;
    }

    public void setCol_9h15(String col_9h15) {
        this.col_9h15 = col_9h15;
    }

    public String getCol_9h30() {
        return col_9h30;
    }

    public void setCol_9h30(String col_9h30) {
        this.col_9h30 = col_9h30;
    }

    public String getCol_9h45() {
        return col_9h45;
    }

    public void setCol_9h45(String col_9h45) {
        this.col_9h45 = col_9h45;
    }

    public String getCol_10h() {
        return col_10h;
    }

    public void setCol_10h(String col_10h) {
        this.col_10h = col_10h;
    }

    public String getCol_10h15() {
        return col_10h15;
    }

    public void setCol_10h15(String col_10h15) {
        this.col_10h15 = col_10h15;
    }

    public String getCol_10h30() {
        return col_10h30;
    }

    public void setCol_10h30(String col_10h30) {
        this.col_10h30 = col_10h30;
    }

    public String getCol_10h45() {
        return col_10h45;
    }

    public void setCol_10h45(String col_10h45) {
        this.col_10h45 = col_10h45;
    }

    public String getCol_11h() {
        return col_11h;
    }

    public void setCol_11h(String col_11h) {
        this.col_11h = col_11h;
    }

    public String getCol_11h15() {
        return col_11h15;
    }

    public void setCol_11h15(String col_11h15) {
        this.col_11h15 = col_11h15;
    }

    public String getCol_11h30() {
        return col_11h30;
    }

    public void setCol_11h30(String col_11h30) {
        this.col_11h30 = col_11h30;
    }

    public String getCol_11h45() {
        return col_11h45;
    }

    public void setCol_11h45(String col_11h45) {
        this.col_11h45 = col_11h45;
    }

    public String getCol_12h() {
        return col_12h;
    }

    public void setCol_12h(String col_12h) {
        this.col_12h = col_12h;
    }

    public String getCol_12h15() {
        return col_12h15;
    }

    public void setCol_12h15(String col_12h15) {
        this.col_12h15 = col_12h15;
    }

    public String getCol_12h30() {
        return col_12h30;
    }

    public void setCol_12h30(String col_12h30) {
        this.col_12h30 = col_12h30;
    }

    public String getCol_12h45() {
        return col_12h45;
    }

    public void setCol_12h45(String col_12h45) {
        this.col_12h45 = col_12h45;
    }

    public String getCol_13h() {
        return col_13h;
    }

    public void setCol_13h(String col_13h) {
        this.col_13h = col_13h;
    }

    public String getCol_13h15() {
        return col_13h15;
    }

    public void setCol_13h15(String col_13h15) {
        this.col_13h15 = col_13h15;
    }

    public String getCol_13h30() {
        return col_13h30;
    }

    public void setCol_13h30(String col_13h30) {
        this.col_13h30 = col_13h30;
    }

    public String getCol_13h45() {
        return col_13h45;
    }

    public void setCol_13h45(String col_13h45) {
        this.col_13h45 = col_13h45;
    }

    public String getCol_14h() {
        return col_14h;
    }

    public void setCol_14h(String col_14h) {
        this.col_14h = col_14h;
    }

    public String getCol_14h15() {
        return col_14h15;
    }

    public void setCol_14h15(String col_14h15) {
        this.col_14h15 = col_14h15;
    }

    public String getCol_14h30() {
        return col_14h30;
    }

    public void setCol_14h30(String col_14h30) {
        this.col_14h30 = col_14h30;
    }

    public String getCol_14h45() {
        return col_14h45;
    }

    public void setCol_14h45(String col_14h45) {
        this.col_14h45 = col_14h45;
    }

    public String getCol_15h() {
        return col_15h;
    }

    public void setCol_15h(String col_15h) {
        this.col_15h = col_15h;
    }

    public String getCol_15h15() {
        return col_15h15;
    }

    public void setCol_15h15(String col_15h15) {
        this.col_15h15 = col_15h15;
    }

    public String getCol_15h30() {
        return col_15h30;
    }

    public void setCol_15h30(String col_15h30) {
        this.col_15h30 = col_15h30;
    }

    public String getCol_15h45() {
        return col_15h45;
    }

    public void setCol_15h45(String col_15h45) {
        this.col_15h45 = col_15h45;
    }

    public String getCol_16h() {
        return col_16h;
    }

    public void setCol_16h(String col_16h) {
        this.col_16h = col_16h;
    }

    public String getCol_16h15() {
        return col_16h15;
    }

    public void setCol_16h15(String col_16h15) {
        this.col_16h15 = col_16h15;
    }

    public String getCol_16h30() {
        return col_16h30;
    }

    public void setCol_16h30(String col_16h30) {
        this.col_16h30 = col_16h30;
    }

    public String getCol_16h45() {
        return col_16h45;
    }

    public void setCol_16h45(String col_16h45) {
        this.col_16h45 = col_16h45;
    }

    public String getCol_17h() {
        return col_17h;
    }

    public void setCol_17h(String col_17h) {
        this.col_17h = col_17h;
    }

    public String getCol_17h15() {
        return col_17h15;
    }

    public void setCol_17h15(String col_17h15) {
        this.col_17h15 = col_17h15;
    }

    public String getCol_17h30() {
        return col_17h30;
    }

    public void setCol_17h30(String col_17h30) {
        this.col_17h30 = col_17h30;
    }

    public String getCol_17h45() {
        return col_17h45;
    }

    public void setCol_17h45(String col_17h45) {
        this.col_17h45 = col_17h45;
    }

    public String getCol_18h() {
        return col_18h;
    }

    public void setCol_18h(String col_18h) {
        this.col_18h = col_18h;
    }

    public String getCol_18h15() {
        return col_18h15;
    }

    public void setCol_18h15(String col_18h15) {
        this.col_18h15 = col_18h15;
    }

    public String getCol_18h30() {
        return col_18h30;
    }

    public void setCol_18h30(String col_18h30) {
        this.col_18h30 = col_18h30;
    }

    public String getCol_18h45() {
        return col_18h45;
    }

    public void setCol_18h45(String col_18h45) {
        this.col_18h45 = col_18h45;
    }

    public String getCol_19h() {
        return col_19h;
    }

    public void setCol_19h(String col_19h) {
        this.col_19h = col_19h;
    }

    public String getCol_19h15() {
        return col_19h15;
    }

    public void setCol_19h15(String col_19h15) {
        this.col_19h15 = col_19h15;
    }

    public String getCol_19h30() {
        return col_19h30;
    }

    public void setCol_19h30(String col_19h30) {
        this.col_19h30 = col_19h30;
    }

    public String getCol_19h45() {
        return col_19h45;
    }

    public void setCol_19h45(String col_19h45) {
        this.col_19h45 = col_19h45;
    }

    public String getCol_20h() {
        return col_20h;
    }

    public void setCol_20h(String col_20h) {
        this.col_20h = col_20h;
    }

    public String getCol_20h15() {
        return col_20h15;
    }

    public void setCol_20h15(String col_20h15) {
        this.col_20h15 = col_20h15;
    }

    public String getCol_20h30() {
        return col_20h30;
    }

    public void setCol_20h30(String col_20h30) {
        this.col_20h30 = col_20h30;
    }

    public String getCol_20h45() {
        return col_20h45;
    }

    public void setCol_20h45(String col_20h45) {
        this.col_20h45 = col_20h45;
    }

    @Override
    public String toString() {
        return "Day{" +
                "col_id_day='" + col_id_day + '\'' +
                ", col_id_user='" + col_id_user + '\'' +
                ", col_6h='" + col_6h + '\'' +
                ", col_6h15='" + col_6h15 + '\'' +
                ", col_6h30='" + col_6h30 + '\'' +
                ", col_6h45='" + col_6h45 + '\'' +
                ", col_7h='" + col_7h + '\'' +
                ", col_7h15='" + col_7h15 + '\'' +
                ", col_7h30='" + col_7h30 + '\'' +
                ", col_7h45='" + col_7h45 + '\'' +
                ", col_8h='" + col_8h + '\'' +
                ", col_8h15='" + col_8h15 + '\'' +
                ", col_8h30='" + col_8h30 + '\'' +
                ", col_8h45='" + col_8h45 + '\'' +
                ", col_9h='" + col_9h + '\'' +
                ", col_9h15='" + col_9h15 + '\'' +
                ", col_9h30='" + col_9h30 + '\'' +
                ", col_9h45='" + col_9h45 + '\'' +
                ", col_10h='" + col_10h + '\'' +
                ", col_10h15='" + col_10h15 + '\'' +
                ", col_10h30='" + col_10h30 + '\'' +
                ", col_10h45='" + col_10h45 + '\'' +
                ", col_11h='" + col_11h + '\'' +
                ", col_11h15='" + col_11h15 + '\'' +
                ", col_11h30='" + col_11h30 + '\'' +
                ", col_11h45='" + col_11h45 + '\'' +
                ", col_12h='" + col_12h + '\'' +
                ", col_12h15='" + col_12h15 + '\'' +
                ", col_12h30='" + col_12h30 + '\'' +
                ", col_12h45='" + col_12h45 + '\'' +
                ", col_13h='" + col_13h + '\'' +
                ", col_13h15='" + col_13h15 + '\'' +
                ", col_13h30='" + col_13h30 + '\'' +
                ", col_13h45='" + col_13h45 + '\'' +
                ", col_14h='" + col_14h + '\'' +
                ", col_14h15='" + col_14h15 + '\'' +
                ", col_14h30='" + col_14h30 + '\'' +
                ", col_14h45='" + col_14h45 + '\'' +
                ", col_15h='" + col_15h + '\'' +
                ", col_15h15='" + col_15h15 + '\'' +
                ", col_15h30='" + col_15h30 + '\'' +
                ", col_15h45='" + col_15h45 + '\'' +
                ", col_16h='" + col_16h + '\'' +
                ", col_16h15='" + col_16h15 + '\'' +
                ", col_16h30='" + col_16h30 + '\'' +
                ", col_16h45='" + col_16h45 + '\'' +
                ", col_17h='" + col_17h + '\'' +
                ", col_17h15='" + col_17h15 + '\'' +
                ", col_17h30='" + col_17h30 + '\'' +
                ", col_17h45='" + col_17h45 + '\'' +
                ", col_18h='" + col_18h + '\'' +
                ", col_18h15='" + col_18h15 + '\'' +
                ", col_18h30='" + col_18h30 + '\'' +
                ", col_18h45='" + col_18h45 + '\'' +
                ", col_19h='" + col_19h + '\'' +
                ", col_19h15='" + col_19h15 + '\'' +
                ", col_19h30='" + col_19h30 + '\'' +
                ", col_19h45='" + col_19h45 + '\'' +
                ", col_20h='" + col_20h + '\'' +
                ", col_20h15='" + col_20h15 + '\'' +
                ", col_20h30='" + col_20h30 + '\'' +
                ", col_20h45='" + col_20h45 + '\'' +
                '}';
    }
}


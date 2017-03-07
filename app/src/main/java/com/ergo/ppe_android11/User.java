package com.ergo.ppe_android11;


public class User {
   // private int id;
    private String name;
    private String mail;
    private String password;
    private int birthyear;
    private String signupdate;

    public User(){}

    public User(String mail, String name, String password, int birthyear, String signupdate){
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.birthyear = birthyear;
        this.signupdate = signupdate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public String getSignUpDate() {
        return signupdate;
    }

    public void setSignupdate(String date) {
        this.signupdate = date;
    }

    public String toString(){ return mail+" "+name+" "+password+" "+birthyear+" "+signupdate;}
}

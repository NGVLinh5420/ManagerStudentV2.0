package com.example.managerstudent.Minh.models;

import java.io.Serializable;

public class M_Account implements Serializable {
    private String nameAcc;
    private   String pass;


    public M_Account(String nameAcc, String pass) {
        this.nameAcc = nameAcc;
        this.pass = pass;
    }
    public M_Account(){

    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}

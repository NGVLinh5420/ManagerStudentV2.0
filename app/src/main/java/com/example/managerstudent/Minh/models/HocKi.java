package com.example.managerstudent.Minh.models;

import java.io.Serializable;

public class HocKi implements Serializable {
    private String maHK;
    private String tenHocKi;

    private String maNganh;

    public String getMaHK() {
        return maHK;
    }

    public void setMaHK(String maHK) {
        this.maHK = maHK;
    }

    public String getTenHocKi() {
        return tenHocKi;
    }

    public void setTenHocKi(String tenHocKi) {
        this.tenHocKi = tenHocKi;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }


    public HocKi(String maHK, String tenHocKi, String maNganh) {
        this.maHK = maHK;
        this.tenHocKi = tenHocKi;
        this.maNganh = maNganh;
    }

    public HocKi() {
    }
}

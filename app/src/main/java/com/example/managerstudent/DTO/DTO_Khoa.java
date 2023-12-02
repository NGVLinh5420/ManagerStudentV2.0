package com.example.managerstudent.DTO;

import java.io.Serializable;

public class DTO_Khoa implements Serializable {

    private String tenKhoa;

    public DTO_Khoa() {
    }

    public DTO_Khoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
}

package com.example.managerstudent.DTO;

import java.io.Serializable;

public class DTO_Diem implements Serializable {
    private String _mamh, _mssv, _diem1, _diem2;

    public DTO_Diem() {
    }

    public DTO_Diem(String _mamh, String _mssv) {
        this._mamh = _mamh;
        this._mssv = _mssv;
    }

    public DTO_Diem(String _mamh, String _mssv, String _diem1, String _diem2) {
        this._mamh = _mamh;
        this._mssv = _mssv;
        this._diem1 = _diem1;
        this._diem2 = _diem2;
    }

    public String get_mamh() {
        return _mamh;
    }

    public void set_mamh(String _mamh) {
        this._mamh = _mamh;
    }

    public String get_mssv() {
        return _mssv;
    }

    public void set_mssv(String _mssv) {
        this._mssv = _mssv;
    }

    public String get_diem1() {
        return _diem1;
    }

    public void set_diem1(String _diem1) {
        this._diem1 = _diem1;
    }

    public String get_diem2() {
        return _diem2;
    }

    public void set_diem2(String _diem2) {
        this._diem2 = _diem2;
    }

    //Method
    public double _diemTB() {
        double d1 = Double.parseDouble(_diem1);
        double d2 = Double.parseDouble(_diem2);
        double dTB = (d1 + d2) / 2;

        return dTB;
    }
}

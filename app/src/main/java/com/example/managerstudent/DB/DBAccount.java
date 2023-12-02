package com.example.managerstudent.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.managerstudent.Minh.models.M_Account;

import java.util.ArrayList;
import java.util.List;

public class DBAccount extends SQLiteOpenHelper {

    public final String nameCount = "NameCount";
    public final String pass = "Pass";

    public final String confirmPass = "ConfirmPass";
    public final String tbAccCount = "tbAccCount";


    public DBAccount(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "ManagerStudent", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlKhoa = "Create table tbKhoa (makhoa text primary key, tenkhoa text)";
        db.execSQL(sqlKhoa);

        String sqlChuyenNganh = "Create table tbChuyenNganh (manganh text primary key, tennganh text, makhoa text)";
        db.execSQL(sqlChuyenNganh);

        String sqlHK = "Create table tbHocKy (mahk text primary key, tenhocky text, manganh text)";
        db.execSQL(sqlHK);

        String sqlMH = "Create table tbMonHoc (mamh text primary key,tenmh text,stc text, mahk text)";
        db.execSQL(sqlMH);
        String createTbAccCount = "Create table " + tbAccCount + "(" +

                nameCount + " text," +
                pass + "text" +
                ")";
        db.execSQL(createTbAccCount);

    }

    public List<M_Account> getListAccount() {
        List<M_Account> list = new ArrayList<>();
        String query = "select * from " + tbAccCount;
        Cursor cr = this.getReadableDatabase().rawQuery(query, null);
        while (cr.moveToNext()) {
            M_Account ac = new M_Account(cr.getString(0), cr.getString(1));
            list.add(ac);
        }
        return list;
    }

    public void addAccount(String nameAcc, String pass) {
        String query = "insert into tbAccCount values(?,?)";
        this.getWritableDatabase().execSQL(query, new String[]{nameAcc, pass});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

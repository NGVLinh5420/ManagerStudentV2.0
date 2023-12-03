package com.example.managerstudent.Linh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

public class L_SinhVien_Activity extends AppCompatActivity {

    TextView tvSoLuongSV;
    Button btnBack, btnThem;
    private ListView lvSinhVien;
    static public ArrayAdapter<DTO_SV> adapterListView;

    //DataBase
    private DBSV dbQLSV = new DBSV(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_sv_activity);

        setControl();
        setEvent();
    }

    private void setControl() {
        btnBack = findViewById(R.id.tt_btnBack);
        btnThem = findViewById(R.id.tt_btnthem);

        tvSoLuongSV = findViewById(R.id.tt_tvSoLuongSV);
        lvSinhVien = findViewById(R.id.thongtin_lvSinhVien);
    }

    private void setEvent() {
        //--1.
        //Chay DB, đọc db và lấy ds sinhvien
        dbQLSV = new DBSV(this);
        dbQLSV.Doc_SinhVien();

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DBSV.getDsSinhVien());
        lvSinhVien.setAdapter(adapterListView);

        //số lượng ds sinh viên
        String sizeDSSV = "" + DBSV.getDsSinhVien().size();
        tvSoLuongSV.setText(sizeDSSV);


        //--2.Buttons
        // Click ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long viTri) {
                //Tìm đối tượng sv ở vị trí đã Click và lưu MSSV của nó vào DBSV
                ClickListView(DBSV.getDsSinhVien().get((int) viTri).get_MSSV());
            }
        });

        //Thêm Mới SV
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L_SinhVien_Activity.this, L_SinhVien_Them.class);
                startActivity(intent);
            }
        });

        // Quay Về Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_SinhVien_Activity.super.onBackPressed();
            }
        });
    }


    // Click List View
    private void ClickListView(String mssv) {

        //Truyền MSSV của đối tượng SV đã Click sang class.Thông tin chi tiết
        DBSV.setLuuMSSV(mssv);

        Intent intent = new Intent(L_SinhVien_Activity.this, L_SinhVien_ThongTin.class);
        startActivity(intent);
    }
}

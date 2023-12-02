package com.example.managerstudent.Linh;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

public class L_DiemActivity extends AppCompatActivity {

    //    private String luuMssv;
//    private TextView tvMssvTen, tvDiem1, tvDiem2, tvDiem3, tvKhoa, tvSoLuongSV;
//    private EditText edtDiem1, edtDiem2, edtDiem3;
//    Spinner spHocKy;
//    private Button btnSave, btnClear, btnBack;
    private Button btnBack, btnThongKe;
    private TextView tvSoLuongSV;
    private ListView lvSinhVien;
    private ArrayAdapter<DTO_SV> adapterListView;

    //DataBase
    private DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_diem_activity);

        setControls();
        setEvents();
    }

    private void setControls() {
        lvSinhVien = findViewById(R.id.d_lvSinhVien);
        btnBack = findViewById(R.id.d_btnBack);
        btnThongKe = findViewById(R.id.d_btnThongKeGioi);

        tvSoLuongSV = findViewById(R.id.d_tvSoLuongSV);
    }

    private void setEvents() {
        //--1.
        //Chay DB, đọc db và lấy ds sinhvien
        dbSV.Doc_SinhVien();
        dbSV.Doc_SinhVien();

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DBSV.getDsSinhVien());
        lvSinhVien.setAdapter(adapterListView);

        String sizeDSSV = "" + DBSV.sizeDSSV();
        tvSoLuongSV.setText(sizeDSSV);

        //selected item ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long viTri) {
                //Truyền đối tượng SV đã Click sang Activity tiếp theo
                DBSV.luuSinhVien = DBSV.dsSinhVien.get((int) viTri);

                Intent intent = new Intent(L_DiemActivity.this, L_Diem_SinhVien.class);
                startActivity(intent);
            }
        });

        //--2.Buttons
        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_DiemActivity.super.onBackPressed();
            }
        });

        //Button Back
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L_DiemActivity.this, L_Diem_ThongKe_Gioi.class);
                startActivity(intent);
            }
        });


//        // Button Clear Text
//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edtDiem1.setText(null);
//                edtDiem2.setText(null);
//                edtDiem3.setText(null);
//                Toast.makeText(L_DiemActivity.this, "Đã Clear.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // Button SAVE
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Kiểm Format tra điểm
//                if (Integer.parseInt(edtDiem1.getText().toString()) < 0 || Integer.parseInt(edtDiem1.getText().toString()) > 10)
//                    return;
//                if (Integer.parseInt(edtDiem2.getText().toString()) < 0 || Integer.parseInt(edtDiem2.getText().toString()) > 10)
//                    return;
//                if (Integer.parseInt(edtDiem3.getText().toString()) < 0 || Integer.parseInt(edtDiem3.getText().toString()) > 10)
//                    return;
//
//                //Begin
//                DTO_Diem d = new DTO_Diem();
//                d.set_mssv(luuMssv);
//                d.set_Diem1(edtDiem1.getText().toString());
//                d.set_Diem2(edtDiem2.getText().toString());
//                d.set_Diem3(edtDiem3.getText().toString());
//                dbSV.Sua_Diem(d);
//
//                dsDiem.addAll(dbSV.Doc_Diem());
//                dbSV.Doc_SinhVien();
//                lvSinhVien.setAdapter(adapterListView);
//
//                //End
//                Toast.makeText(L_DiemActivity.this, "Lưu Thành Công!!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}
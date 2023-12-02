package com.example.managerstudent.Minh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.R;

public class M_MonHoc_Them extends AppCompatActivity {

    EditText edtMaMH, edtTenMH, edtSoTinChi;
    Button btnCancel, btnAdd;

    DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_hoc);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maMH = edtMaMH.getText().toString();
                String tenMH = edtTenMH.getText().toString();
                String soTC = edtSoTinChi.getText().toString();

                //
                DTO_HocPhan monHoc = new DTO_HocPhan(maMH, tenMH, soTC, DBSV.getLuuTenKhoa(), DBSV.getLuuTenHocKy());
                dbSV.Them_MonHoc(monHoc);
                dbSV.Doc_MonHoc();

                M_MonHoc.adapter.notifyDataSetChanged();
                onBackPressed();
                Toast.makeText(M_MonHoc_Them.this, "Đã Thêm Môn.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        edtMaMH = findViewById(R.id.edtMaMH);
        edtTenMH = findViewById(R.id.edtTenMH);
        edtSoTinChi = findViewById(R.id.edtSoTinChi);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
    }
}
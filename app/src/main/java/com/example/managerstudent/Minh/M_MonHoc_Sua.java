package com.example.managerstudent.Minh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.R;

public class M_MonHoc_Sua extends AppCompatActivity {

    String maMH;
    EditText edtMaMH, edtTenMH, edtSoTinChi;
    Button btnCancel, btnConfirm;

    DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon_hoc);
        setControl();
        setEvent();
    }

    private void setEvent() {
        edtMaMH.setText(DBSV.dsMonHoc.get(M_MonHoc.selectedIndex).getMaMH());
        edtTenMH.setText(DBSV.dsMonHoc.get(M_MonHoc.selectedIndex).getTenMH());
        edtSoTinChi.setText(DBSV.dsMonHoc.get(M_MonHoc.selectedIndex).getSoTC());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maMH = edtMaMH.getText().toString();
                String tenMH = edtTenMH.getText().toString();
                String soTC = edtSoTinChi.getText().toString();

                DTO_HocPhan monHoc = new DTO_HocPhan(maMH, tenMH, soTC, DBSV.getLuuTenKhoa(), DBSV.getLuuTenHocKy());
                dbSV.Them_MonHoc(monHoc);
                dbSV.Doc_MonHoc();

                M_MonHoc.adapter.notifyDataSetChanged();
                Toast.makeText(M_MonHoc_Sua.this, "Đã Sửa Môn Học.", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        edtMaMH = findViewById(R.id.edtMaMH);
        edtMaMH.setEnabled(false);

        edtTenMH = findViewById(R.id.edtTenMH);
        edtSoTinChi = findViewById(R.id.edtSoTinChi);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
    }
}
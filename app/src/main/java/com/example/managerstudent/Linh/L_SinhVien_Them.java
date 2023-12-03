package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_Khoa;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class L_SinhVien_Them extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //gui
    Button btnThem, btnClear, btnBack;
    ImageView imgAvatar;
    EditText edtMSSV, edtTen, edtNgaySinh, edtNamHoc;
    Spinner spKhoa;
    RadioGroup rgGioiTinh;
    RadioButton rdNam, rdNu;

    //DataBase
    private DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_sv_them);

        setControl();
        setEvent();
    }

    private void setEvent() {
        //--1.
        rdNam.setChecked(true);
        imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        rgGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdNu.isChecked())
                    imgAvatar.setImageResource(R.drawable.l_avatar_nu);
                if (rdNam.isChecked())
                    imgAvatar.setImageResource(R.drawable.l_avatar_nam);
            }
        });

        //Chay DB, đọc db và lấy ds sinhvien
        dbSV = new DBSV(this);
        dbSV.Doc_SinhVien();

        //--2.Buttons
        //Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_SinhVien_Them.super.onBackPressed();
            }
        });

        // Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tạo 1 đối tg sinh viên
                DTO_SV sv = new DTO_SV();

                if (edtMSSV.getText().length() > 0 && KTTrungMSSV(edtMSSV.getText().toString()) == false) {
                    //cập nhật thông tin từ 3 thông tin ng dùng nhp vào
                    sv.set_MSSV(edtMSSV.getText().toString());

                    sv.set_Ten(edtTen.getText().toString());
                    sv.set_NgaySinh(edtNgaySinh.getText().toString());
                    sv.set_NamHoc(edtNamHoc.getText().toString());

                    if (rdNam.isChecked() == true)
                        sv.set_GioiTinh("Nam");
                    else sv.set_GioiTinh("Nu");

                    sv.set_Khoa(spKhoa.getSelectedItem().toString());

                    //ghi xuống csdl
                    dbSV.Them_SinhVien(sv);

                    //cap nhat ListView, dsSinhVien
                    dbSV.Doc_SinhVien();

                    //
                    Toast.makeText(L_SinhVien_Them.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    L_SinhVien_Activity.adapterListView.notifyDataSetChanged();
                    onBackPressed();

                } else {
                    Toast.makeText(L_SinhVien_Them.this, "Phải Nhập Đúng Thông Tin!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAllText();
            }
        });
    }

    private void setControl() {
        imgAvatar = findViewById(R.id.them_imgAvatar);
        btnBack = findViewById(R.id.tt_btnBack);
        btnThem = findViewById(R.id.them_btnThem);

        btnClear = findViewById(R.id.them_btnClear);

        edtMSSV = findViewById(R.id.them_edtMSSV);
        edtTen = findViewById(R.id.them_edtTen);
        edtNgaySinh = findViewById(R.id.them_edtNgaySinh);
        edtNamHoc = findViewById(R.id.them_edtNamHoc);

        rgGioiTinh = findViewById(R.id.them_RadioGroupGioiTinh);
        rdNam = findViewById(R.id.them_rbNam);
        rdNu = findViewById(R.id.them_rbNu);
        spKhoa = findViewById(R.id.them_SpinnerKhoa);

        //Spinner Khoa
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_item, LayDSKhoa());
        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
        spKhoa.setAdapter(adapterSpinner);
        spKhoa.setOnItemSelectedListener(L_SinhVien_Them.this);
    }

    public ArrayList<String> LayDSKhoa() {
        ArrayList<String> dsKhoa = new ArrayList<>();

        dbSV.Doc_Khoa();
        for (int i = 0; i < DBSV.dsKhoa.size(); i++) {
            DTO_Khoa khoa = DBSV.dsKhoa.get(i);

            dsKhoa.add(khoa.getTenKhoa());
        }
        return dsKhoa;
    }

    //Clear
    private void ClearAllText() {
        edtMSSV.setText(null);
        edtTen.setText(null);
        rdNam.setChecked(true);
        imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        edtNgaySinh.setText(null);
        edtNamHoc.setText(null);
        spKhoa.setSelection(0);
    }

    //Kiểm tra xem trước khi thêm sv mới có trùng với sv cũ
    private boolean KTTrungMSSV(String mssv) {
        for (int i = 0; i < DBSV.sizeDSSV(); i++) {
            if (mssv.equals(DBSV.dsSinhVien.get(i).get_MSSV().toString()) == true)
                return true;
        }
        return false;
    }

    //Spinner adapter
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
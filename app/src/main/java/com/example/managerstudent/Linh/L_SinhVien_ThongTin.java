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

public class L_SinhVien_ThongTin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //gui
    ImageView imgAvatar;
    Button btnXoa, btnSua, btnClear, btnRefresh, btnBack;
    EditText edtMSSV, edtTen, edtNgaySinh, edtNamHoc;
    Spinner spKhoa;
    RadioGroup rgGioiTinh;
    RadioButton rdNam, rdNu;
    private ArrayAdapter<DTO_SV> adapterListView;

    //DataBase
    private DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_sv_thong_tin);

        setControl();
        setEvent();
    }

    private void setControl() {
        imgAvatar = findViewById(R.id.tt_imgAvatar);
        btnBack = findViewById(R.id.tt_btnBack);

        btnXoa = findViewById(R.id.tt_btnXoa);
        btnSua = findViewById(R.id.tt_btnSua);
        btnClear = findViewById(R.id.tt_btnClear);
        btnRefresh = findViewById(R.id.tt_btnRefresh);

        edtMSSV = findViewById(R.id.tt_edtMSSV);
        edtTen = findViewById(R.id.tt_edtTen);
        edtNgaySinh = findViewById(R.id.tt_edtNgaySinh);
        edtNamHoc = findViewById(R.id.tt_edtNamHoc);

        rgGioiTinh = findViewById(R.id.tt_RadioGroupGioiTinh);
        rdNam = findViewById(R.id.tt_rbNam);
        rdNu = findViewById(R.id.tt_rbNu);
        spKhoa = findViewById(R.id.tt_SpinnerKhoa);

        //Spinner Khoa
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_item, LayDSKhoa());
        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
        spKhoa.setAdapter(adapterSpinner);
        spKhoa.setOnItemSelectedListener(L_SinhVien_ThongTin.this);
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

    private void setEvent() {
        // 1.
        // Chay DB, đọc db và lấy ds sinhvien
        dbSV = new DBSV(this);
        dbSV.Doc_SinhVien();

        // lấy thông tin sinh viên
        _AfterClickListView(DBSV.getLuuMSSV());

        //--2.Buttons
        //radiobutton
        rgGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdNu.isChecked())
                    imgAvatar.setImageResource(R.drawable.l_avatar_nu);
                if (rdNam.isChecked())
                    imgAvatar.setImageResource(R.drawable.l_avatar_nam);
            }
        });

        //Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_SinhVien_ThongTin.super.onBackPressed();
            }
        });

        //Xoá
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = edtMSSV.getText().toString();
                int index = Find_ItemInList(mssv);

                if (index > -1) {
                    dbSV.Xoa_SinhVien(mssv);
                    dbSV.Xoa_Diem_XoaSV(mssv);
                    dbSV.Doc_SinhVien();

                    //
                    Toast.makeText(L_SinhVien_ThongTin.this, "Đã Xoá Sinh Viên [ " + mssv + " ].", Toast.LENGTH_SHORT).show();
                    L_SinhVien_Activity.adapterListView.notifyDataSetChanged();
                    onBackPressed();

                } else {
                    if (DBSV.sizeDSSV() == 0)
                        Toast.makeText(L_SinhVien_ThongTin.this, "Danh Sách Rỗng!!.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(L_SinhVien_ThongTin.this, "Chọn SV Để Xoá!!.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Find_ItemInList(edtMSSV.getText().toString());

                if (index != -1) {
                    DTO_SV sv = new DTO_SV();
                    sv.set_MSSV(edtMSSV.getText().toString());
                    sv.set_Ten(edtTen.getText().toString());
                    sv.set_NgaySinh(edtNgaySinh.getText().toString());
                    sv.set_NamHoc(edtNamHoc.getText().toString());

                    //Giới Tính
                    if (rdNam.isChecked() == true)
                        sv.set_GioiTinh("Nam");
                    else sv.set_GioiTinh("Nu");

                    //Khoa
                    sv.set_Khoa(spKhoa.getSelectedItem().toString());

                    //
                    dbSV.Sua_SinhVien(sv);
                    Toast.makeText(L_SinhVien_ThongTin.this, "Sửa Thành Công!!", Toast.LENGTH_SHORT).show();
                    L_SinhVien_Activity.adapterListView.notifyDataSetChanged();
                    onBackPressed();

                } else {
                    if (DBSV.sizeDSSV() == 0)
                        Toast.makeText(L_SinhVien_ThongTin.this, "Danh Sách Rỗng!!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(L_SinhVien_ThongTin.this, "Lỗi!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _Clear();
                Toast.makeText(L_SinhVien_ThongTin.this, "Clear!!", Toast.LENGTH_SHORT).show();
            }
        });

        //Button Refresh
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _AfterClickListView(edtMSSV.getText().toString());
                Toast.makeText(L_SinhVien_ThongTin.this, "Làm Mới!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // After Click List Sinhvien
    private void _AfterClickListView(String mssv) {
        //Tìm sv đã click
        DTO_SV sv = DBSV.dsSinhVien.get(Find_ItemInList(mssv));

        // Hiển thị thông tin
        edtMSSV.setText(sv.get_MSSV());
        edtTen.setText(sv.get_Ten());
        edtNgaySinh.setText(sv.get_NgaySinh());
        edtNamHoc.setText(sv.get_NamHoc());

        if (sv.get_GioiTinh().equals("Nam")) rdNam.setChecked(true);
        else rdNu.setChecked(true);

        if (rdNu.isChecked())
            imgAvatar.setImageResource(R.drawable.l_avatar_nu);
        if (rdNam.isChecked())
            imgAvatar.setImageResource(R.drawable.l_avatar_nam);

        for (int i = 0; i < spKhoa.getCount(); i++) {
            spKhoa.setSelection(i);
            String str = spKhoa.getSelectedItem().toString();
            if (sv.get_Khoa().equals(str)) {
                return;
            }
        }

        if (sv.get_GioiTinh().equals("Nam")) {
            rdNam.setChecked(true);
            imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        } else {
            rdNu.setChecked(true);
            imgAvatar.setImageResource(R.drawable.l_avatar_nu);
        }
    }

    private int Find_ItemInList(String MSSV) {
        for (int i = 0; i < DBSV.sizeDSSV(); i++) {
            if (DBSV.dsSinhVien.get(i).get_MSSV().toString().equals(MSSV) == true)
                return i;
        }
        return -1;
    }

    //Kiểm tra xem trước khi thêm sv mới có trùng với sv cũ
    private boolean KTTrungMSSV(String mssv) {
        for (int i = 0; i < DBSV.sizeDSSV(); i++) {
            if (mssv.equals(DBSV.dsSinhVien.get(i).get_MSSV().toString()) == true)
                return true;
        }
        return false;
    }

    //Clear
    private void _Clear() {
        edtTen.setText(null);
        rdNam.setChecked(true);
        imgAvatar.setImageResource(R.drawable.l_avatar_nam);
        edtNgaySinh.setText(null);
        edtNamHoc.setText(null);
        spKhoa.setSelection(0);
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
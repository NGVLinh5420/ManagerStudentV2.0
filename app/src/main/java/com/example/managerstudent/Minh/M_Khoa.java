package com.example.managerstudent.Minh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.Minh.Adapter.KhoaAdapter;
import com.example.managerstudent.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class M_Khoa extends AppCompatActivity {

    Toolbar toolbarDanhSachKhoa;
    ListView lvKhoa;
    FloatingActionButton btnThem;
    TextView tvCountItem;
    KhoaAdapter adapter;
    LinearLayout grEdit;
    Button btnCancel, btnRename, btnRemove;
    static int selectedIndex = -1;
    DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khoa);
        setControl();


        setEvent();
    }

    private void setEvent() {
        dbSV.Doc_Khoa();
        adapter = new KhoaAdapter(this, DBSV.getDSKhoa());
        lvKhoa.setAdapter(adapter);

        setSupportActionBar(toolbarDanhSachKhoa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Danh Sách Khoa");

        tvCountItem.setText(String.valueOf(lvKhoa.getCount()));

        lvKhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBSV.luuTenKhoa = DBSV.dsKhoa.get(position).getTenKhoa();
                selectedIndex = position;
                Intent nextPage = new Intent(M_Khoa.this, M_HocKi.class);
                startActivity(nextPage);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddKhoa();
            }
        });

        lvKhoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                grEdit.setVisibility(View.VISIBLE);
                selectedIndex = position;

                return false;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grEdit.setVisibility(View.GONE);

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKhoa = DBSV.dsKhoa.get(selectedIndex).getTenKhoa();
                dbSV.Xoa_Khoa(tenKhoa);

                dbSV.Doc_Khoa();

                //
                adapter.notifyDataSetChanged();
                grEdit.setVisibility(View.GONE);
                tvCountItem.setText(String.valueOf(lvKhoa.getCount()));

                Toast.makeText(M_Khoa.this, "Đã Xoá [ " + tenKhoa + " ].", Toast.LENGTH_SHORT).show();
            }
        });

        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRenameKhoa();
            }
        });

    }

    private void showDialogRenameKhoa() {
        Dialog dialog = new Dialog(M_Khoa.this);
        dialog.setTitle("Sửa Tên Khoa");
        dialog.setContentView(R.layout.dialog_rename);
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnOK = dialog.findViewById(R.id.btnOK);
        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);
        editTenMoi.setText(DBSV.dsKhoa.get(selectedIndex).getTenKhoa());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSV.Sua_Khoa(DBSV.dsKhoa.get(selectedIndex).getTenKhoa(), editTenMoi.getText().toString());
                dbSV.Doc_Khoa();

                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void showDialogAddKhoa() {
        Dialog dialog = new Dialog(M_Khoa.this);
        dialog.setTitle("Thêm Khoa");
        dialog.setContentView(R.layout.dialog_rename);
        dialog.show();

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnAdd = dialog.findViewById(R.id.btnOK);
        EditText editTenMoi = dialog.findViewById(R.id.edtNhapTen);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTenMoi.getText().toString().isEmpty()) {
                    dbSV.Them_Khoa(editTenMoi.getText().toString());
                    dbSV.Doc_Khoa();
                    adapter.notifyDataSetChanged();
                    tvCountItem.setText(String.valueOf(lvKhoa.getCount()));
                    dialog.dismiss();
                }
            }
        });
    }

    private void setControl() {
        toolbarDanhSachKhoa = findViewById(R.id.toolbarDanhSachKhoa);
        lvKhoa = findViewById(R.id.lvKhoa);
        btnThem = findViewById(R.id.btnThem);
        tvCountItem = findViewById(R.id.tvCountItem);
        grEdit = findViewById(R.id.grEdit);
        btnCancel = findViewById(R.id.btnCancel);
        btnRename = findViewById(R.id.btnRename);
        btnRemove = findViewById(R.id.btnRemove);
    }
}
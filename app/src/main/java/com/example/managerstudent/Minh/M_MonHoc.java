package com.example.managerstudent.Minh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.Minh.Adapter.MonHocAdapter;
import com.example.managerstudent.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class M_MonHoc extends AppCompatActivity {
    TextView tvKhoa, tvCountItem;
    Toolbar toolbarMonHoc;
    ListView lvMonHoc;
    FloatingActionButton btnThem;
    LinearLayout grEdit;
    Button btnCancel, btnRemove, btnEdit;
    static MonHocAdapter adapter;

    DBSV dbSV = new DBSV(this);
    static int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);
        setControl();
        setEvent();

    }

    private void setEvent() {
        //1.
        tvKhoa.setText(DBSV.luuTenKhoa);
        AutoRun();

        setSupportActionBar(toolbarMonHoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(DBSV.luuTenHocKy);
        tvCountItem.setText("" + DBSV.dsMonHoc.size());

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                grEdit.setVisibility(View.VISIBLE);
                selectedIndex = position;
                return false;
            }
        });

        //2.Buttons
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(M_MonHoc.this, M_MonHoc_Them.class);
                startActivity(nextPage);
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
                String maMH = DBSV.dsMonHoc.get(selectedIndex).getMaMH();
                DBSV.dsMonHoc.remove(selectedIndex);
                dbSV.Xoa_MonHoc(maMH);

                AutoRun();

                Toast.makeText(M_MonHoc.this, "Đã Xoá [ " + maMH + " ].", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                grEdit.setVisibility(View.GONE);
                tvCountItem.setText("" + DBSV.dsMonHoc.size());
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(M_MonHoc.this, M_MonHoc_Sua.class);
                startActivity(nextPage);
            }
        });
    }

    private void setControl() {
        toolbarMonHoc = findViewById(R.id.toolbarMonHoc);
        tvCountItem = findViewById(R.id.tvCountItem);
        lvMonHoc = findViewById(R.id.lvMonHoc);
        btnThem = findViewById(R.id.btnThem);
        grEdit = findViewById(R.id.grEdit);
        btnCancel = findViewById(R.id.btnCancel);
        btnRemove = findViewById(R.id.btnRemove);
        btnEdit = findViewById(R.id.btnEdit);
        tvKhoa = findViewById(R.id.tvKhoa);
    }

    //Method
    public void AutoRun() {
        ArrayList<DTO_HocPhan> dsMHTheoKhoa_HK = new ArrayList<>();
        dsMHTheoKhoa_HK = dbSV.Doc_MHTheoKhoa_HK(DBSV.luuTenKhoa, DBSV.luuTenHocKy);
        adapter = new MonHocAdapter(this, dsMHTheoKhoa_HK);
        lvMonHoc.setAdapter(adapter);
    }
}
package com.example.managerstudent.Minh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.Minh.Adapter.HocKiAdapter;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class M_HocKi extends AppCompatActivity {

    Toolbar toolbarHocKi;
    ListView lvHocKi;
    HocKiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_ki);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ArrayList<String> arrHK = new ArrayList<String>();
        arrHK.add("HK1");
        arrHK.add("HK2");
        arrHK.add("HK3");
        arrHK.add("HK4");
        arrHK.add("HK5");

        adapter = new HocKiAdapter(this, arrHK);
        lvHocKi.setAdapter(adapter);

        setSupportActionBar(toolbarHocKi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(DBSV.luuTenKhoa);

        lvHocKi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBSV.luuTenHocKy = arrHK.get(position).toString();
                Intent nextPage = new Intent(M_HocKi.this, M_MonHoc.class);
                startActivity(nextPage);
            }
        });
    }

    private void setControl() {
        toolbarHocKi = findViewById(R.id.toolbarHocKi);
        lvHocKi = findViewById(R.id.lvHocKi);

    }
}
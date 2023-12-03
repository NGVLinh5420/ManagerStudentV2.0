package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.managerstudent.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class L_ThongKe_BarChart extends AppCompatActivity {
    BarChart barChart;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_thong_ke_barchart);

        setControl();
        setEvent();
    }

    private void setControl() {
        barChart = (BarChart) findViewById(R.id.dtk_barchart);
        btnBack = findViewById(R.id.dtk_btnBack);
    }

    private void setEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Barchar();

    }

    private void Barchar() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 7.8f)); // Điểm trung bình của Khoa A
        entries.add(new BarEntry(1, 8.5f)); // Điểm trung bình của Khoa B
        entries.add(new BarEntry(2, 7.3f)); // Điểm trung bình của Khoa C
        entries.add(new BarEntry(3, 8.2f)); // Điểm trung bình của Khoa D

        BarDataSet bardataset = new BarDataSet(entries, "Điểm trung bình của các khoa");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(bardataset);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("CNTT");
        labels.add("DO HOA");
        labels.add("DIEN TU");
        labels.add("KINH TE");
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setLabelCount(labels.size(), false);

        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.invalidate(); // refresh
    }
}
package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class L_ThongKe_BarChart extends AppCompatActivity {
    BarChart barChart;
    Button btnBack;
    DBSV dbSV = new DBSV(this);

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

        //Set trục tung hiển thị số nguyên thay vì số thập phân như mặc định
        YAxis yAxisL = barChart.getAxisLeft();
        yAxisL.setGranularity(1f);
        yAxisL.setGranularityEnabled(true);
        // Ẩn trục Tung trái
        YAxis yAxisR = barChart.getAxisRight();
        yAxisR.setEnabled(false);
        // Hiển thị label phía dưới thay vì mặc định phía trên
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // Đổi Kích Thước label miêu tả cột
        xAxis.setTextSize(15f);
        // Ẩn òng chữ Description label mặc định ở bên phải-dưới
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

    }

    private void Barchar() {
        ArrayList<BarEntry> entries = new ArrayList<>();

        // Có một HashMap hoặc một cấu trúc dữ liệu tương tự để lưu trữ số lượng sinh viên theo khoa
        HashMap<String, Integer> studentsPerDepartment = CreateHashMap();
        ArrayList<String> labels = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Integer> entry : studentsPerDepartment.entrySet()) {
            // Sử dụng tên khoa làm nhãn cho mỗi BarEntry
            entries.add(new BarEntry(index, entry.getValue()));
            labels.add(entry.getKey());
            index++;
        }

        BarDataSet bardataset = new BarDataSet(entries, "Số Lượng Sinh Viên Của Khoa");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(bardataset);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setLabelCount(labels.size(), false);
        barChart.setDoubleTapToZoomEnabled(false);

        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.invalidate(); // refresh

        // Điều chỉnh vị trí của nhãn
        bardataset.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + ((int) value);
            }
        });
        // Điều chỉnh kích thước của nhãn
        bardataset.setValueTextSize(20f);

    }

    public HashMap<String, Integer> CreateHashMap() {
        HashMap<String, Integer> studentsPerDepartment = new HashMap<>();

        // Giả sử bạn có dữ liệu về số lượng sinh viên theo khoa
        dbSV.Doc_Khoa();
        for (int i = 0; i < DBSV.dsKhoa.size(); i++) {
            String tenKhoa = DBSV.dsKhoa.get(i).getTenKhoa();
            studentsPerDepartment.put(tenKhoa, DemSLSVTheoKhoa(tenKhoa));
        }
        return studentsPerDepartment;
    }


    public int DemSLSVTheoKhoa(String khoa) {
        dbSV.Doc_SVTheoKhoa(khoa);
        return DBSV.dsSinhVien.size();
    }


//    private void Barchar() {
//        ArrayList<BarEntry> entries = new ArrayList<>();
//
//        // Có một HashMap hoặc một cấu trúc dữ liệu tương tự để lưu trữ số lượng sinh viên theo khoa
//        HashMap<String, Integer> studentsPerDepartment = CreateHashMap();
//        for (Map.Entry<String, Integer> entry : studentsPerDepartment.entrySet()) {
//            // Sử dụng tên khoa làm nhãn cho mỗi BarEntry
//            entries.add(new BarEntry(entry.getValue(), Integer.parseInt(entry.getKey())));
//        }
//
//        BarDataSet bardataset = new BarDataSet(entries, "Số Lượng Sinh Viên Của Khoa.");
//
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(bardataset);
//
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.addAll(LayDSTenKhoa());
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        barChart.getXAxis().setLabelCount(labels.size(), false);
//
//        BarData data = new BarData(dataSets);
//        barChart.setData(data);
//        barChart.invalidate(); // refresh
//    }
//
//    public HashMap<String, Integer> CreateHashMap() {
//        HashMap<String, Integer> studentsPerDepartment = new HashMap<>();
//
//        // Giả sử bạn có dữ liệu về số lượng sinh viên theo khoa
//        studentsPerDepartment.put("Khoa CNTT", 500);
//        studentsPerDepartment.put("Khoa Vật lý", 300);
//        studentsPerDepartment.put("Khoa Hóa học", 400);
//
//        dbSV.Doc_Khoa();
//        for (int i = 0; i < DBSV.dsKhoa.size(); i++) {
//            String tenKhoa = DBSV.dsKhoa.get(i).getTenKhoa();
//            studentsPerDepartment.put(tenKhoa, DemSVTheoKhoa(tenKhoa));
//        }
//        return studentsPerDepartment;
//    }
//
//
//    public int DemSVTheoKhoa(String khoa) {
//        dbSV.Doc_SVTheoKhoa(khoa);
//        return DBSV.dsSinhVien.size();
//    }
//
//    public ArrayList<String> LayDSTenKhoa() {
//        ArrayList<String> dsTenKhoa = new ArrayList<>();
//        dbSV.Doc_Khoa();
//        for (int i = 0; i < DBSV.dsKhoa.size(); i++) {
//            DTO_Khoa khoa = DBSV.dsKhoa.get(i);
//            dsTenKhoa.add(khoa.getTenKhoa());
//        }
//        return dsTenKhoa;
//    }
}
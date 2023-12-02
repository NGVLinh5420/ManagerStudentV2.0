package com.example.managerstudent.Minh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class MonHocAdapter extends BaseAdapter {
    Context context;
    ArrayList<DTO_HocPhan> dsMonHoc;
    public MonHocAdapter(Context context, ArrayList<DTO_HocPhan> dsMonHoc) {
        this.context = context;
        this.dsMonHoc = dsMonHoc;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<DTO_HocPhan> getDsMonHoc() {
        return dsMonHoc;
    }

    public void setDsMonHoc(ArrayList<DTO_HocPhan> dsMonHoc) {
        this.dsMonHoc = dsMonHoc;
    }

    @Override
    public int getCount() {
        return  dsMonHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return dsMonHoc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_monhoc,null);

        DTO_HocPhan monHoc=dsMonHoc.get(position);
        TextView tvMaMH = convertView.findViewById(R.id.tvMaMH);
        TextView tvTenMH = convertView.findViewById(R.id.tvTenMH);
        TextView tvSoTinChi = convertView.findViewById(R.id.tvSoTinChi);

        tvMaMH.setText(monHoc.getMaMH());
        tvTenMH.setText(monHoc.getTenMH());
        tvSoTinChi.setText(monHoc.getSoTC());
        return convertView;
    }


}

package com.example.managerstudent.Minh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_Khoa;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class KhoaAdapter extends BaseAdapter {
    ArrayList<DTO_Khoa> dsKhoa;
    Context context;

    public KhoaAdapter(Context context, ArrayList<DTO_Khoa> dsKhoa) {
        this.dsKhoa = dsKhoa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return DBSV.dsKhoa.size();
    }

    @Override
    public Object getItem(int position) {
        return DBSV.dsKhoa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,null);

        TextView textView = convertView.findViewById(R.id.tvTitle);

        textView.setText(DBSV.dsKhoa.get(position).getTenKhoa());

        return convertView;
    }
}

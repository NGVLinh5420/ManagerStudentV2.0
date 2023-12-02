package com.example.managerstudent.Minh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.R;
import com.example.managerstudent.Minh.models.ChuyenNganh;

import java.util.List;

public class  ChuyenNganhAdapter extends BaseAdapter {
    Context context;
    List<ChuyenNganh> dsChuyenNganh;

    public ChuyenNganhAdapter(Context context, List<ChuyenNganh> dsChuyenNganh) {
        this.context = context;
        this.dsChuyenNganh = dsChuyenNganh;
    }

    @Override
    public int getCount() {
        return dsChuyenNganh.size();
    }

    @Override
    public Object getItem(int position) {
        return dsChuyenNganh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        TextView textView = convertView.findViewById(R.id.tvTitle);
        textView.setText(dsChuyenNganh.get(position).getTenChuyenNganh());
        return convertView;
    }
}

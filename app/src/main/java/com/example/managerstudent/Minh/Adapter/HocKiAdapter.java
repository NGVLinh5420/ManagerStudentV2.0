package com.example.managerstudent.Minh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.R;
import com.example.managerstudent.Minh.models.HocKi;

import java.util.ArrayList;
import java.util.List;

public class HocKiAdapter extends BaseAdapter {
    Context context;

    ArrayList<String> dsHocKi;

    public HocKiAdapter(Context context, ArrayList<String> dsHocKi) {
        this.context = context;
        this.dsHocKi = dsHocKi;
    }

    @Override
    public int getCount() {
        return dsHocKi.size();
    }

    @Override
    public Object getItem(int position) {
        return dsHocKi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        TextView textView = convertView.findViewById(R.id.tvTitle);
        textView.setText(dsHocKi.get(position).toString());
        return convertView;
    }
}

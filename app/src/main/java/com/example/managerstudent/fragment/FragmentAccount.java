package com.example.managerstudent.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.managerstudent.Activity_Login;
import com.example.managerstudent.Activity_Main;
import com.example.managerstudent.R;

public class FragmentAccount extends Fragment {
    TextView tvTime;

    int minutes = Activity_Login.minutes;
    int seconds = Activity_Login.seconds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        tvTime = view.findViewById(R.id.acc_tvTime);
        tvTime.setText("Time Count\n" + String.format("%dm:%02ds", minutes, seconds));
        // Inflate the layout for this fragment
        return view;
    }
}
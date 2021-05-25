package com.example.monitoring_app.ui.monitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;

public class MonitorFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_monitor, container, false);

        //final TextView textView = root.findViewById(R.id.text_home);

        return root;
    }
}

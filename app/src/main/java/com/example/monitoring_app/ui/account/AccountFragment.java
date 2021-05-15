package com.example.monitoring_app.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;
import com.example.monitoring_app.ui.history.History;
import com.example.monitoring_app.ui.history.HistoryAdapter;

import java.util.ArrayList;

public class AccountFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);



        return root;
    }
}

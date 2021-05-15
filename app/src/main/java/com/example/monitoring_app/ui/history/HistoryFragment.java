package com.example.monitoring_app.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.monitoring_app.R;
import com.example.monitoring_app.ui.home.HomeViewModel;
import com.example.monitoring_app.ui.notify.Notify;
import com.example.monitoring_app.ui.notify.NotifyAdapter;

import java.util.ArrayList;
import java.util.Date;

public class HistoryFragment extends Fragment {
    ListView lvHistory;
    ArrayList<History> historyArrayList;
    HistoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        lvHistory = root.findViewById(R.id.listViewHistory);

        historyArrayList = new ArrayList<>();
        historyArrayList.add(new History(1, "Dangerous", "Very Dangerous", "15/05/2021", true ));
        historyArrayList.add(new History(2, "Dangerous", "Very Dangerous", "15/05/2021", true ));
        historyArrayList.add(new History(3, "Dangerous", "Very Dangerous", "15/05/2021", true ));
        historyArrayList.add(new History(4, "Dangerous", "Very Dangerous", "15/05/2021", true ));

        adapter = new HistoryAdapter(root.getContext(), R.layout.history_item, historyArrayList);

        lvHistory.setAdapter(adapter);

        return root;
    }
}

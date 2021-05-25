package com.example.monitoring_app.ui.history;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        historyArrayList.add(new History(5, "Dangerous", "Very Dangerous", "15/05/2021", true ));
        historyArrayList.add(new History(6, "Dangerous", "Very Dangerous", "15/05/2021", true ));

        adapter = new HistoryAdapter(root.getContext(), R.layout.history_item, historyArrayList, this);

        lvHistory.setAdapter(adapter);

        return root;
    }

    public void DeleteHistory(int position) {

        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(getActivity());
        dialogXoa.setMessage("Bạn có muốn xóa history " + position + " không ?");

        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                historyArrayList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Đã xóa thành công.", Toast.LENGTH_SHORT).show();
            }
        });

        dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogXoa.show();
    }
}

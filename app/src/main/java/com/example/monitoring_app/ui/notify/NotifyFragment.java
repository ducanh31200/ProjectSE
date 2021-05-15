package com.example.monitoring_app.ui.notify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;

import java.util.ArrayList;

public class NotifyFragment extends Fragment {

    ListView lvNotify;
    ArrayList<Notify> notifyArrayList;
    NotifyAdapter adapter;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notify, container, false);

        //final TextView textView = root.findViewById(R.id.text_home);
        lvNotify = root.findViewById(R.id.listViewNotify);

        notifyArrayList = new ArrayList<>();
        notifyArrayList.add(new Notify(1, "Dangerous", "Very Dangerous", true ));
        notifyArrayList.add(new Notify(2, "Warning", "Warning", true));
        adapter = new NotifyAdapter(root.getContext(), R.layout.notify_item, notifyArrayList);

        lvNotify.setAdapter(adapter);

        lvNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(root.getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        notifyArrayList.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });


        return root;
    }

    public void DeleteNotify(int id) {
        Toast.makeText(root.getContext(), "" + id, Toast.LENGTH_SHORT).show();
        //notifyArrayList.remove(id);
        //adapter.notifyDataSetChanged();
        lvNotify.removeViewAt(id);
    }

}

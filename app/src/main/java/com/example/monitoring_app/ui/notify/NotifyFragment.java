package com.example.monitoring_app.ui.notify;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;

import java.util.ArrayList;
import java.util.List;

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
        notifyArrayList.add(new Notify(3, "Dangerous", "Very Dangerous", true ));
        notifyArrayList.add(new Notify(4, "Warning", "Warning", true));
        notifyArrayList.add(new Notify(5, "Dangerous", "Very Dangerous", true ));
        notifyArrayList.add(new Notify(6, "Warning", "Warning", true));
        adapter = new NotifyAdapter(root.getContext(), R.layout.notify_item, notifyArrayList, this);

        lvNotify.setAdapter(adapter);

        /*lvNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(root.getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        notifyArrayList.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });*/


        return root;
    }

    public void EditNotify(int id,String title, String description,Boolean status,int position) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_notify);

        EditText edtTitle = (EditText) dialog.findViewById(R.id.editTextTitleEdit);
        EditText edtDescription = (EditText) dialog.findViewById(R.id.editTextDescriptionEdit);
        Spinner spStatus = (Spinner) dialog.findViewById(R.id.spinnerStatus);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuyEdit = (Button) dialog.findViewById(R.id.buttonHuyEdit);

        edtTitle.setText(title);
        edtDescription.setText(description);

        List<String> stt = new ArrayList<String>();
        stt.add("True");
        stt.add("False");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stt);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spStatus.setAdapter(dataAdapter);


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = edtTitle.getText().toString().trim();
                String newDes = edtDescription.getText().toString().trim();
                String str = spStatus.getSelectedItem().toString();
                boolean newStt = Boolean.parseBoolean(str);
                notifyArrayList.set(position, new Notify(notifyArrayList.size() + 1, newTitle, newDes, newStt));

                //notifyArrayList.add(new Notify(notifyArrayList.size() + 1, newTitle, newDes, newStt));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnHuyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DeleteNotify(int id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(getActivity());
        dialogXoa.setMessage("Bạn có muốn xóa notify " + id + " không ?");

        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notifyArrayList.remove(id);
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

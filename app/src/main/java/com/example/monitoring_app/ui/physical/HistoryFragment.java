package com.example.monitoring_app.ui.physical;

import android.app.Dialog;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    ListView lvPhysical;
    ArrayList<Physical> physicalArrayList;
    PhysicalAdapter adapter;
    Button btnAddPhy;
    View root;
    EditText edtTitle,edtAge,edtHeight,edtWeight;
    PhysicalPresenter presenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_physical, container, false);

        mapping();

        physicalArrayList = new ArrayList<Physical>();
        presenter = new PhysicalPresenter(getContext());
        physicalArrayList = presenter.getListPhysical();
        adapter = new PhysicalAdapter(root.getContext(), R.layout.physical_item, physicalArrayList, this);

        lvPhysical.setAdapter(adapter);

        btnAddPhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTitle.getText().toString().trim().length() !=0 && edtHeight.getText().toString().trim().length() !=0 &&
                edtAge.getText().toString().trim().length() !=0 && edtWeight.getText().toString().trim().length() !=0)
                {
                    presenter.addPhysical(edtTitle.getText().toString(),edtAge.getText().toString(),
                            edtHeight.getText().toString(),edtWeight.getText().toString());
                    //NotifyManager.getInstance().addNotify(edtxtTitle.getText().toString(),edtxtTime.getText().toString());
                    adapter.notifyDataSetChanged();
                    edtTitle.setText("");
                    edtAge.setText("");
                    edtHeight.setText("");
                    edtWeight.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(), "Mời nhập lại đầy đủ !", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        return root;
    }
    private void mapping()
    {
        //final TextView textView = root.findViewById(R.id.text_home);

        edtTitle = root.findViewById(R.id.editTextTitle);
        edtAge = root.findViewById(R.id.editTextAge);
        edtHeight = root.findViewById(R.id.editTextHeight);
        edtWeight = root.findViewById(R.id.editTextWeight);
        lvPhysical = root.findViewById(R.id.listViewPhysical);
        btnAddPhy = root.findViewById(R.id.btnAddPhy);
    }

    public void EditPhysical(String title, String age,String height, String weight,int position) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_physical);

        EditText edtTitlePhyEdit = (EditText) dialog.findViewById(R.id.editTextTitlePhyEdit);
        EditText edtAgeEdit = (EditText) dialog.findViewById(R.id.editTextAgeEdit);
        EditText edtHeightEdit = (EditText) dialog.findViewById(R.id.editTextHeightEdit);
        EditText edtWeightEdit = (EditText) dialog.findViewById(R.id.editTextWeightEdit);

        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhanSua);
        Button btnHuyEdit = (Button) dialog.findViewById(R.id.buttonHuySua);

        edtTitlePhyEdit.setText(title);
        edtAgeEdit.setText(age);
        edtHeightEdit.setText(height);
        edtWeightEdit.setText(weight);


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = edtTitlePhyEdit.getText().toString().trim();
                String newAge = edtAgeEdit.getText().toString().trim();
                String newHeight = edtHeightEdit.getText().toString().trim();
                String newWeight = edtWeightEdit.getText().toString().trim();

                presenter.setListPhysical(new Physical(position,newTitle,newAge,newHeight,newWeight));

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

    public void Delete(int position)
    {
        presenter.delete(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Đã xóa thành công.", Toast.LENGTH_SHORT).show();
    }

}

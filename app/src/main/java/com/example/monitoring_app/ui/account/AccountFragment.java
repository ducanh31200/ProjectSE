package com.example.monitoring_app.ui.account;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.HomeActivity;
import com.example.monitoring_app.R;
import com.example.monitoring_app.ui.history.History;
import com.example.monitoring_app.ui.history.HistoryAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class AccountFragment extends Fragment {

    //HomeActivity context;
    TextView tvBirth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        Button btnEdit = (Button) root.findViewById(R.id.btnEditAccount);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_account();
            }
        });


        return root;
    }

    private void dialog_edit_account() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_account);

        EditText edtName = (EditText) dialog.findViewById(R.id.editTextNameEdit);
        EditText edtEmail = (EditText) dialog.findViewById(R.id.editTextEmailEdit);
        EditText edtPhone = (EditText) dialog.findViewById(R.id.editTextPhoneEdit);
        tvBirth  = (TextView) dialog.findViewById(R.id.tvBirth);
        Button btnDatePick = (Button) dialog.findViewById(R.id.btnBirthday);
        EditText edtAddress = (EditText) dialog.findViewById(R.id.editTextAddressEdit);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuyEdit = (Button) dialog.findViewById(R.id.buttonHuyEdit);

        btnDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker();
            }
        });


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String tenMoi = edtTenCV.getText().toString().trim();
                //database.QueryData("UPDATE CongViec SET TenCV = '"+ tenMoi +"' WHERE Id = '"+ id +"'");
                Toast.makeText(getActivity(), "Đã cập nhật công việc.", Toast.LENGTH_SHORT).show();
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

    private void DatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvBirth.setText(year + "/" + month + "/" + dayOfMonth );
            }
        }, year, month, day);

        datePickerDialog.show();

    }
}

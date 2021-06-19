package com.example.monitoring_app.ui.notify;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.monitoring_app.ui.notify.NotifyPresenter;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class NotifyFragment extends Fragment {
    Button btnAdd;
    EditText edtxtTime;
    EditText edtxtTitle;
    ListView lvNotify;
    NotifyPresenter presenter;
    ArrayList<Notify> notifyList;
    NotifyAdapter adapter;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    int y;
    int mOy;
    int dOm;
    int hour;
    int min;
    View root;
    public static String date_time = "HH:mm dd/MM/yy";
    public static String date = "dd/MM/yyyy";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notify, container, false);

        mapping();

        notifyList = new ArrayList<Notify>();
        presenter = new NotifyPresenter(getContext());
        notifyList = presenter.getListNotify();

        alarmManager = (AlarmManager) root.getContext().getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(root.getContext(),NotifyReceiver.class);

        adapter = new NotifyAdapter(root.getContext(), R.layout.notify_item, notifyList, this);

        lvNotify.setAdapter(adapter);

        LocalDateTime ldTime = LocalDateTime.now();
        edtxtTime.setFocusable(false);
        edtxtTime.setOnClickListener(v->{
            new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                edtxtTime.setText(toString(LocalDateTime.of(year,month,dayOfMonth,hourOfDay,minute)));
                y = year;
                mOy = month;
                dOm = dayOfMonth;
                hour = hourOfDay;
                min = minute;
            },ldTime.getHour(),ldTime.getMinute(),true).show(), ldTime.getYear(),ldTime.getMonthValue(),ldTime.getDayOfMonth()).show();
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtxtTitle.getText().toString().trim().length() !=0 && edtxtTime.getText().toString().trim().length() !=0 )
                {
                    presenter.addNotify(edtxtTitle.getText().toString(),edtxtTime.getText().toString());
                    //NotifyManager.getInstance().addNotify(edtxtTitle.getText().toString(),edtxtTime.getText().toString());
                    adapter.notifyDataSetChanged();
                    edtxtTime.setText("");
                    edtxtTitle.setText("");
                    pendingIntent = PendingIntent.getBroadcast(
                            root.getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR,hour);
                    cal.set(Calendar.MINUTE,min);
                    //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),1*1000,pendingIntent);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);
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
        edtxtTime = root.findViewById(R.id.editTextTime);
        edtxtTitle = root.findViewById(R.id.editTextTitle);
        lvNotify = root.findViewById(R.id.listViewNotify);
        btnAdd = root.findViewById(R.id.btnAdd);
    }

    public void EditNotify(String title, String description,int position) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_notify);

        EditText edtTitleEdit = (EditText) dialog.findViewById(R.id.editTextTitleEdit);
        EditText edtTimeEdit = (EditText) dialog.findViewById(R.id.editTextTimeEdit);

        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuyEdit = (Button) dialog.findViewById(R.id.buttonHuyEdit);

        edtTitleEdit.setText(title);
        edtTimeEdit.setText(description);


        LocalDateTime ldTime = LocalDateTime.now();
        edtTimeEdit.setFocusable(false);
        edtTimeEdit.setOnClickListener(v->{
            new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                edtTimeEdit.setText(toString(LocalDateTime.of(year,month,dayOfMonth,hourOfDay,minute)));
            },ldTime.getHour(),ldTime.getMinute(),true).show(), ldTime.getYear(),ldTime.getMonthValue(),ldTime.getDayOfMonth()).show();
        });


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = edtTitleEdit.getText().toString().trim();
                String newTime = edtTimeEdit.getText().toString().trim();

                //notifyList.set(position, new Notify(newTitle, newTime));
                presenter.setListNotify(new Notify(position,newTitle, newTime));
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

        presenter.delete(id);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Đã xóa thành công.", Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("NewApi")
    public static String toString(LocalDateTime localDateTime) {
        if(localDateTime.getHour() == 0 && localDateTime.getMinute() == 0)
            return localDateTime.format(DateTimeFormatter.ofPattern(date));
        return localDateTime.format(DateTimeFormatter.ofPattern(date_time));
    }
}

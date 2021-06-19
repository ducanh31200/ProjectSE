package com.example.monitoring_app.ui.physical;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.monitoring_app.Models.SQLiteHandler;
import com.example.monitoring_app.ui.notify.Notify;

import java.util.ArrayList;
import java.util.List;

public class PhysicalPresenter {
    private ArrayList<Physical> listPhysical;
    Context context;
    SQLiteHandler db;
    public PhysicalPresenter(Context ct)
    {
        listPhysical = new ArrayList<>();
        context = ct;
        db  = new SQLiteHandler(context);
    }
    public ArrayList<Physical> getListPhysical()
    {
        Cursor data = db.getData("SELECT * FROM Physical");
        listPhysical.clear();
        while (data.moveToNext()){
            Physical physical = new Physical(data.getInt(0),data.getString(1),data.getString(2),
                    data.getString(3),data.getString(4));
            listPhysical.add(physical);
        }
        return listPhysical;
    }
    public void addPhysical(String title, String age, String height, String weight)
    {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("age", age);
        values.put("height", height);
        values.put("weight", weight);
        db.addData("Physical",values);
        getListPhysical();
    }

    @SuppressLint("DefaultLocale")
    public void setListPhysical(Physical physical)
    {
        db.setData(String.format("UPDATE Physical SET title = '%s', age = '%s'," +
                    "height = '%s', weight = '%s' WHERE id = %d",
            physical.getTitle(),
            physical.getAge(),
            physical.getHeight(),
            physical.getWeight(),
            physical.getId()));
        getListPhysical();
    }
    @SuppressLint("DefaultLocale")
    public void delete(int id)
    {
        db.setData(String.format("DELETE FROM Physical WHERE id = %d",
                id));
        getListPhysical();
    }
}

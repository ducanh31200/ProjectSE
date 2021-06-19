package com.example.monitoring_app.ui.notify;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.monitoring_app.Models.SQLiteHandler;
import com.example.monitoring_app.ui.account.Account;

import java.util.ArrayList;
import java.util.List;

public class NotifyPresenter {

    private ArrayList<Notify> listNotify;
    Context context;
    SQLiteHandler db;
    public NotifyPresenter(Context ct)
    {
        listNotify = new ArrayList<>();
        context = ct;
        db  = new SQLiteHandler(context);
    }
    public ArrayList<Notify> getListNotify()
    {
        Cursor data = db.getData("SELECT * FROM Notify");
        listNotify.clear();
        while (data.moveToNext()){
            Notify notify = new Notify(data.getInt(0),data.getString(1),data.getString(2));
            listNotify.add(notify);
        }
        return listNotify;
    }
    public void addNotify(String title, String time)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("time",time);
        db.addData("Notify",contentValues);
        getListNotify();
    }
    @SuppressLint("DefaultLocale")
    public void setListNotify(Notify notify)
    {
        db.setData(String.format("UPDATE Notify SET title = '%s', time = '%s' WHERE id = %d",
                notify.getTitle(),
                notify.getTime(),
                notify.getId()));
        getListNotify();
    }
    @SuppressLint("DefaultLocale")
    public void delete(int id)
    {
        db.setData(String.format("DELETE FROM Notify WHERE id = %d",
                id));
        getListNotify();
    }
}

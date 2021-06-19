package com.example.monitoring_app.ui.account;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.monitoring_app.Models.SQLiteHandler;

import java.util.List;
import java.util.SimpleTimeZone;

public class AccountPresenter {
    List<Account> accountList;
    Context context;
    SQLiteHandler db;

    public AccountPresenter(Context ct)
    {
        context = ct;
        db  = new SQLiteHandler(context);
    }
    public List<Account> getAccountList()
    {
        Cursor data = db.getData("SELECT * FROM Account");
        while (data.moveToNext()){
            Account account = new Account(data.getString(0),data.getString(1),
                    data.getString(2),data.getString(3),data.getString(4),
                    data.getString(5),data.getString(6));
            accountList.add(account);
        }
        return accountList;
    }
    public void setCurrent(String username)
    {
        ContentValues values = new ContentValues();
        values.put("current", username);
        db.addData("Current",values);
    }
    public String getCurrent()
    {
        String currentUser ="";
        Cursor data = db.getData("SELECT * FROM Current");
        while (data.moveToNext())
        {
            currentUser = data.getString(0);
        }
        return currentUser;
    }
    public Account getAccount(String username)
    {
        Cursor data = db.getData("SELECT * FROM Account");
        Account account;
        while (data.moveToNext()){
            if(data.getString(0).equals(username))
                return account = new Account(data.getString(0),data.getString(1),
                    data.getString(2),data.getString(3),data.getString(4),
                    data.getString(5),data.getString(6));
        }
        return null;
    }
    public boolean checkLogin(String username, String password)
    {
        Cursor data = db.getData("SELECT * FROM Account");
        while (data.moveToNext()){
            if(data.getString(0).equals(username) && data.getString(1).equals(password))
                return true;
        }
        return false;
    }
    public void changePass(String username, String pass)
    {
        db.setData(String.format("UPDATE Account SET password = '%s' WHERE username = '%s'",
                pass,username));
    }
    @SuppressLint("DefaultLocale")
    public void editAccount(Account account)
    {
        db.setData(String.format("UPDATE Account SET password= '%s', name = '%s', mail = '%s'" +
                        ", phone = '%s', birthday = '%s', address = '%s' WHERE username = '%s'",
                account.getPassword(),
                account.getName(),
                account.getMail(),
                account.getPhone(),
                account.getBirthday(),
                account.getAddress(),
                account.getUsername()));
    }
    public void addAccount(String username, String name, String password, String email)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("mail", email);
        contentValues.put("phone", "");
        contentValues.put("birthday", "");
        contentValues.put("address", "");
        db.addData("Account",contentValues);
    }
}

package com.example.monitoring_app.ui.history;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<History> historyList;

    public HistoryAdapter(Context context, int layout, List<History> historyList) {
        this.context = context;
        this.layout = layout;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

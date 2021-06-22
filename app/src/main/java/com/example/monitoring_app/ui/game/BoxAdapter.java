package com.example.monitoring_app.ui.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.monitoring_app.R;

import java.util.List;

public class BoxAdapter extends BaseAdapter {

    public int Layout;
    public Context context;
    public List<BoxColor> listBox;

    public BoxAdapter(int layout, Context context, List<BoxColor> listBox) {
        Layout = layout;
        this.context = context;
        this.listBox = listBox;
    }

    @Override
    public int getCount() {
        return listBox.size();
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
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= layoutinflater.inflate(Layout,null);

        ImageView image = (ImageView)convertView.findViewById(R.id.image);
        BoxColor Color = listBox.get(position);
        image.setImageResource(Color.getColor());
        image.setBackgroundColor(Color.getColor());
        convertView.setTag(Color);
        return convertView;
    }
}

package com.example.monitoring_app.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monitoring_app.R;
import com.example.monitoring_app.ui.notify.NotifyAdapter;

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

    private class ViewHolder {
        TextView txtTitle, txtDescription, txtCreatedDate;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitleHis);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.textViewDescriptionHis);
            holder.txtCreatedDate = (TextView) convertView.findViewById(R.id.textViewCreatedDateHis);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageviewEditHis);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageviewDeleteHis);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        History history = historyList.get(position);

        holder.txtTitle.setText(history.getTitle());
        holder.txtDescription.setText(history.getDescription());
        holder.txtCreatedDate.setText(history.getCreatedDate());

        // event update & delete
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(context, UpdateStudentActivity.class);
                ////intent.putExtra("dataSV", sinhVien);
                //context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //XacNhanXoa(notify.getTitle(), notify.getId());
            }
        });



        return convertView;
    }
}

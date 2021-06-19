package com.example.monitoring_app.ui.notify;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monitoring_app.R;

import java.util.List;

public class NotifyAdapter extends BaseAdapter {
    Context context;
    private int layout;
    private List<Notify> notifyList;
    NotifyFragment notifyFragment = new NotifyFragment();

    public NotifyAdapter(Context context, int layout, List<Notify> notifyList, NotifyFragment notifyFragment) {
        this.context = context;
        this.layout = layout;
        this.notifyList = notifyList;
        this.notifyFragment = notifyFragment;
    }

    @Override
    public int getCount() {
        return notifyList.size();
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
        TextView txtTitle, txtTime;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            holder.txtTime = (TextView) convertView.findViewById(R.id.textViewTime);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageviewEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageviewDelete);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Notify notify = notifyList.get(position);
        holder.txtTitle.setText(notify.getTitle());
        holder.txtTime.setText(notify.getTime());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyFragment.EditNotify(notify.getTitle(), notify.getTime(), notify.getId());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(notify.getTitle(), notify.getId());
                //notifyFragment.DeleteNotify(notify.getId());
            }
        });

        return convertView;
    }

    private void XacNhanXoa(String ten, final int id){
        AlertDialog.Builder  dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Do you want to delete " + ten + " ?");
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //context.DeleteStudent(idsv);
                notifyFragment.DeleteNotify(id);
                //notifyList.remove(idsv);

            }
        });
        dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}

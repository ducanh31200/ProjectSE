package com.example.monitoring_app.ui.physical;

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

public class PhysicalAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Physical> physicalList;
    private HistoryFragment historyFragment;

    public PhysicalAdapter(Context context, int layout, List<Physical> physicalList, HistoryFragment historyFragment) {
        this.context = context;
        this.layout = layout;
        this.physicalList = physicalList;
        this.historyFragment = historyFragment;
    }

    @Override
    public int getCount() {
        return physicalList.size();
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
        TextView txtTitle, txtAge, txtHeight, txtWeight;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitlePhy);
            holder.txtAge = (TextView) convertView.findViewById(R.id.textViewAge);
            holder.txtHeight = (TextView) convertView.findViewById(R.id.textViewHeight);
            holder.txtWeight = (TextView) convertView.findViewById(R.id.textViewWeight);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageviewEditHis);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageviewDeleteHis);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Physical physical = physicalList.get(position);
        holder.txtTitle.setText(physical.getTitle());
        holder.txtAge.setText("Tuổi:"+physical.getAge());
        holder.txtHeight.setText("Chiều cao:"+physical.getHeight()+"cm");
        holder.txtWeight.setText("Cân nặng:"+physical.getWeight()+"Kg");

        // event update & delete
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyFragment.EditPhysical(physical.getTitle(),physical.getAge(),
                        physical.getHeight(),physical.getWeight(),physical.getId());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(physical.getTitle(), physical.getId());
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
                historyFragment.Delete(id);

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

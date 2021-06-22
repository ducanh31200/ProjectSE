package com.example.monitoring_app.ui.advice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoring_app.R;

import java.util.List;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.ViewHolder> {
    List<AdviceModel> adviceModelList;

    public AdviceAdapter(List<AdviceModel> adviceModelList) {
        this.adviceModelList = adviceModelList;
    }

    @NonNull
    @Override
    public AdviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advice_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceAdapter.ViewHolder holder, int position) {
        String adviceName = adviceModelList.get(position).getTitle();
        String TimeStart = adviceModelList.get(position).getTimeStart();
        String Doctor = adviceModelList.get(position).getDoctor();
        int AdviceImage = adviceModelList.get(position).getImage();
        String Name = adviceModelList.get(position).getTitle();
        holder.setAdviceName(adviceName);
        holder.setTimeStart(TimeStart);
        holder.setInforDoctor(Doctor);
        holder.setAdviceImage(AdviceImage);
        holder.setName(Name);
    }

    @Override
    public int getItemCount() {
        return adviceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView AdviceName;
        private TextView timeStart;
        private TextView doctor;
        private String name ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_advice);
            AdviceName= itemView.findViewById(R.id.textView_title);
            timeStart = itemView.findViewById(R.id.textView_time);
            doctor = itemView.findViewById(R.id.textView_doctor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // chuyển tới chi tiết lời khuyên
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent itent = new Intent(itemView.getContext(),DetailAdviceActivity.class);
                            itent.putExtra("typeAdvice",name);
                            itemView.getContext().startActivity(itent);
                        }
                    });
                }
            });
        }
        public void setAdviceImage (int resource)
        {
            image.setImageResource(resource);
        }
        public void setAdviceName(String name)
        {
            AdviceName.setText("Lời khuyên vào " +name);
        }
        public void setTimeStart (String time)
        {
            timeStart.setText("Thời gian: "+time);
        }
        public void setInforDoctor(String phone)
        {
            doctor.setText("Gọi bác sĩ tư vấn: "+phone);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

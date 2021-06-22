package com.example.monitoring_app.ui.advice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monitoring_app.R;

public class DetailAdviceActivity extends AppCompatActivity {

    private String Type_Advice;
    private TextView tv_type_Advice, tv_Advice1, tv_Advice2, tv_Advice3;
    private ImageView img_Advice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_advice);

        Type_Advice = getIntent().getStringExtra("typeAdvice");
        anhxa();
        if(Type_Advice.equals("BuoiSang"))
        {
            img_Advice.setImageResource(R.drawable.buoisang);
            tv_type_Advice.setText("Lời khuyên vào buổi sáng");
            tv_Advice1.setText("Uống 1 ly nước vào buổi sáng để giúp tiêu hóa tốt hơn");
            tv_Advice2.setText("Tập thể dục vào buổi sáng giúp cho cơ thể khỏe mạnh");
            tv_Advice3.setText("Ăn một bữa sáng giàu Protein");
        }else if(Type_Advice.equals("BuoiTrua"))
        {
            img_Advice.setImageResource(R.drawable.buoitrua);
            tv_type_Advice.setText("Lời khuyên vào buổi trưa");
            tv_Advice1.setText("Ăn trưa đầy đủ để có năng lượng cho buổi chiều");
            tv_Advice2.setText("Mặc áo khoát khi ra đường tránh tia cực tím");
            tv_Advice3.setText("Uống nhiều nước");
        }else if(Type_Advice.equals("BuoiToi"))
        {
            img_Advice.setImageResource(R.drawable.buoitoi);
            tv_type_Advice.setText("Lời khuyên vào buổi tối");
            tv_Advice1.setText("Không ăn tối muộn");
            tv_Advice2.setText("Không sử dụng caffein vào cuối ngày");
            tv_Advice3.setText("Nhắm mắt thư giãn trước khi ngủ");
        }else {
            img_Advice.setImageResource(R.drawable.buoisang);
            tv_type_Advice.setText("Lời khuyên vào buổi: ");
            tv_Advice1.setText("");
            tv_Advice2.setText("");
            tv_Advice3.setText("");
        }


    }

    private void anhxa()
    {
        tv_type_Advice = findViewById(R.id.textView);
        tv_Advice1 = findViewById(R.id.textView_Advice1);
        tv_Advice2 = findViewById(R.id.textView_Advice2);
        tv_Advice3 = findViewById(R.id.textView_Advice3);
        img_Advice = findViewById(R.id.imageView_TypeAdvice);

    }
}
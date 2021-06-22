package com.example.monitoring_app.ui.physical;

import android.app.Dialog;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.monitoring_app.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    ListView lvPhysical;
    ArrayList<Physical> physicalArrayList;
    PhysicalAdapter adapter;
    Button btnAddPhy;
    View root;
    EditText edtTitle,edtAge,edtHeight,edtWeight;
    PhysicalPresenter presenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_physical, container, false);

        mapping();

        physicalArrayList = new ArrayList<Physical>();
        presenter = new PhysicalPresenter(getContext());
        physicalArrayList = presenter.getListPhysical();
        adapter = new PhysicalAdapter(root.getContext(), R.layout.physical_item, physicalArrayList, this);

        lvPhysical.setAdapter(adapter);

        btnAddPhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTitle.getText().toString().trim().length() !=0 && edtHeight.getText().toString().trim().length() !=0 &&
                edtAge.getText().toString().trim().length() !=0 && edtWeight.getText().toString().trim().length() !=0)
                {
                    presenter.addPhysical(edtTitle.getText().toString(),edtAge.getText().toString(),
                            edtHeight.getText().toString(),edtWeight.getText().toString());
                    //NotifyManager.getInstance().addNotify(edtxtTitle.getText().toString(),edtxtTime.getText().toString());
                    double Height = Double.parseDouble( edtHeight.getText().toString());
                    double weight = Double.parseDouble( edtWeight.getText().toString());
                    Log.i("Cao nang",Height+" " + weight+"");
                    double BMI = weight/(Height*2);
                    adapter.notifyDataSetChanged();
                    edtTitle.setText("");
                    edtAge.setText("");
                    edtHeight.setText("");
                    edtWeight.setText("");
                    // dialog ở đây


                    Dialog AdviceDialog = new Dialog(root.getContext());
                    AdviceDialog.setContentView(R.layout.dialog_advice);
                    AdviceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    AdviceDialog.setCancelable(false);
                    ImageView img = AdviceDialog.findViewById(R.id.img_advice);
                    TextView tv_BMI = AdviceDialog.findViewById(R.id.textView_BMI);
                    TextView tv_Advice = AdviceDialog.findViewById(R.id.textView_Advice);
                    Button btn_OK = AdviceDialog.findViewById(R.id.button_OK);
                    tv_BMI.setText("BMI: " + BMI);
                    if(BMI<18.5)
                    {
                        tv_Advice.setText("Lời khuyên: Bé quá gầy rồi hãy cho bé uống\n Vitamin và ăn đầy đủ nghe!");
                    }
                    else if(BMI>=18.5 && BMI <= 22.9)
                    {
                        img.setImageResource(R.drawable.embeth);
                        tv_Advice.setText("Lời khuyên: Rất tốt bé có một thân hình lý tưởng\nHãy cho bé luyện tập thể thao thêm nhé");
                    }
                    else if(BMI>=23 && BMI <=24.9)
                    {
                        img.setImageResource(R.drawable.tangcan);
                        tv_Advice.setText("Lời khuyên: Bé đang có nguy cơ tăng cân\nHãy cho bé luyện tập thể dục và ăn uống điều độ");
                    }
                    else if(BMI >24.9)
                    {
                        img.setImageResource(R.drawable.bebeophi);
                        tv_Advice.setText("Lời khuyên: Bé đang bị béo phì\nHãy thường xuyên vận động và giảm khẩu phần ăn nhé\nTránh ăn những đồ ngọt");
                    }
                    btn_OK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AdviceDialog.dismiss();
                        }
                    });
                    AdviceDialog.show();

                }
                else
                {
                    Toast.makeText(getActivity(), "Mời nhập lại đầy đủ !", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        return root;
    }
    private void mapping()
    {
        //final TextView textView = root.findViewById(R.id.text_home);

        edtTitle = root.findViewById(R.id.editTextTitle);
        edtAge = root.findViewById(R.id.editTextAge);
        edtHeight = root.findViewById(R.id.editTextHeight);
        edtWeight = root.findViewById(R.id.editTextWeight);
        lvPhysical = root.findViewById(R.id.listViewPhysical);
        btnAddPhy = root.findViewById(R.id.btnAddPhy);
    }

    public void EditPhysical(String title, String age,String height, String weight,int position) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_physical);

        EditText edtTitlePhyEdit = (EditText) dialog.findViewById(R.id.editTextTitlePhyEdit);
        EditText edtAgeEdit = (EditText) dialog.findViewById(R.id.editTextAgeEdit);
        EditText edtHeightEdit = (EditText) dialog.findViewById(R.id.editTextHeightEdit);
        EditText edtWeightEdit = (EditText) dialog.findViewById(R.id.editTextWeightEdit);

        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhanSua);
        Button btnHuyEdit = (Button) dialog.findViewById(R.id.buttonHuySua);

        edtTitlePhyEdit.setText(title);
        edtAgeEdit.setText(age);
        edtHeightEdit.setText(height);
        edtWeightEdit.setText(weight);


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = edtTitlePhyEdit.getText().toString().trim();
                String newAge = edtAgeEdit.getText().toString().trim();
                String newHeight = edtHeightEdit.getText().toString().trim();
                String newWeight = edtWeightEdit.getText().toString().trim();

                presenter.setListPhysical(new Physical(position,newTitle,newAge,newHeight,newWeight));

                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnHuyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Delete(int position)
    {
        presenter.delete(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Đã xóa thành công.", Toast.LENGTH_SHORT).show();
    }

}

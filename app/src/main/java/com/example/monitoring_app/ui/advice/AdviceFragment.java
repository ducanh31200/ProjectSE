package com.example.monitoring_app.ui.advice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monitoring_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdviceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdviceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdviceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdviceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdviceFragment newInstance(String param1, String param2) {
        AdviceFragment fragment = new AdviceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_advice, container, false);
        // ánh xạ
        recyclerView = view.findViewById(R.id.recycler_Advice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<AdviceModel> adviceModelList = new ArrayList<>();
        adviceModelList.add(new  AdviceModel("BuoiSang","098754877","6h - 9h30",R.drawable.buoisang));
        adviceModelList.add(new  AdviceModel("BuoiTrua","098785773","12h - 1h30",R.drawable.buoitrua));
        adviceModelList.add(new  AdviceModel("BuoiToi","092764897","18h - 22h",R.drawable.buoitoi));
        AdviceAdapter adapter = new AdviceAdapter(adviceModelList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
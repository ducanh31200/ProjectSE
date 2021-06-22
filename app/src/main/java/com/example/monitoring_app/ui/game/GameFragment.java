package com.example.monitoring_app.ui.game;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monitoring_app.R;
import com.example.monitoring_app.Views.Activities.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
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
    ImageView imLogo;
    TextView txtLevel, txtScore, txtTime;
    GridView gridView;
    Button btnStart;

    BoxAdapter boxAdapter;
    ArrayList<BoxColor> listBox;
    ArrayList<Integer> kq, play;
    CountDownTimer timer;
    int level =3;
    int score=0;
    int time =3;
    int correctChoose=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        // ÁNh xạ
        imLogo = (ImageView) view.findViewById(R.id.imageLogo);
        txtLevel = (TextView) view.findViewById(R.id.textviewLevel);
        txtScore = (TextView) view.findViewById(R.id.textviewScore);
        gridView = (GridView) view.findViewById(R.id.myGridView);
        btnStart=(Button) view.findViewById(R.id.buttonStart);
        txtTime = (TextView) view.findViewById(R.id.textviewTime);

        createMatrix(kq,0);
        kq=new ArrayList();
        play=new ArrayList();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(timer!=null)
                    timer.cancel();
                level=3;
                score=0;
                correctChoose=0;
                kq=new ArrayList();
                play=new ArrayList();
                randomPosition(kq,level);
                startGame(kq);
                btnStart.setVisibility(View.INVISIBLE);
                gridView.setVisibility(View.VISIBLE);
                txtScore.setText("Score 0");
            }
        });
        imLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder al = new AlertDialog.Builder(getContext());
                al.setTitle("Luật chơi");
                al.setMessage("Trong 3 giây bạn phải nhớ vị trí các ô màu xanh lá \n-Sau 3 giây các ô sẽ trở về màu nâu bạn sẽ phải click vào các ô màu xanh lá trước đó để thắng trò chơi \n- Nếu chọn đúng sẽ được 100 điểm và độ khó sẽ tăng\n-Chọn sai sẽ bị trừ 100 điểm");
                al.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                al.show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(score < 0)
                {
                    btnStart.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.INVISIBLE);
                }
                if(correctChoose<level)
                {
                    if(Exist(kq,position)==true)
                    {
                        play.add(position);
                        createMatrix(play,1);
                        correctChoose++;
                        score+=100;
                        txtScore.setText("Score "+String.valueOf(score));
                        if(correctChoose==level)
                        {
                            Toast.makeText(view.getContext(), "Win", Toast.LENGTH_SHORT).show();
                            createMatrix(kq,0);
                            level++;
                            score+=100;
                            correctChoose=0;
                            txtScore.setText("Score "+String.valueOf(score));
                            txtLevel.setText("Level "+String.valueOf(level));
                            kq=new ArrayList();
                            play=new ArrayList();
                            randomPosition(kq,level);
                            startGame(kq);
                        }
                    }
                    else {
                        Toast.makeText(view.getContext(), "Choose wrong", Toast.LENGTH_SHORT).show();
                        level--;
                        score-=100;
                        correctChoose=0;
                        createMatrix(kq,0);
                        txtLevel.setText("Level "+String.valueOf(level));
                        txtScore.setText("Score "+String.valueOf(score));
                        kq=new ArrayList();
                        play=new ArrayList();
                        randomPosition(kq,level);
                        startGame(kq);
                        if(level <3)
                        {
                            Toast.makeText(view.getContext(), "Lose", Toast.LENGTH_SHORT).show();
                            level =3;
                            correctChoose=0;
                            score =0;
                            kq=new ArrayList();
                            play=new ArrayList();
                            gridView.setVisibility(View.INVISIBLE);
                            txtLevel.setText("Level "+String.valueOf(level));
                            createMatrix(kq,0);
                            btnStart.setVisibility(View.VISIBLE);
                        }
                    }

                }

            }
        });

        return view;
    }

    public void startGame(ArrayList<Integer> kq){
        gridView.setEnabled(false);
        time =3;
        timer = new CountDownTimer(3000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                txtTime.setText(String.valueOf(time));
                createMatrix(kq,1);
            }

            @Override
            public void onFinish() {
                createMatrix(kq,0);
                gridView.setEnabled(true);
                txtTime.setVisibility(View.INVISIBLE);
            }
        };
        timer.start();
    }
    public void randomPosition(ArrayList<Integer> kq,int level)
    {
        Random r = new Random();
        for(int i=0;i<level;i++)
        {
            int random = r.nextInt(36);
            if(Exist(kq,random)==false)
            {
                kq.add(random);
            }
            else{
                int ran = r.nextInt(36);
                kq.add(ran);
            }
        }
    }
    public Boolean Exist(ArrayList<Integer> ArrKq,int position)
    {
        for(int i=0;i<ArrKq.size();i++)
        {
            if(ArrKq.get(i)==position)
            {
                return true;
            }
        }
        return false;
    }
    public void createMatrix(ArrayList<Integer> Matrix, int color){
        listBox = new ArrayList();
        if(color ==1)
        {
            for(int i=0;i<36;i++)
            {
                if(Exist(Matrix,i)==true)
                {
                    listBox.add(new BoxColor(R.drawable.green));
                }
                else {
                    listBox.add(new BoxColor(R.drawable.brown));
                }
            }
        }
        else{
            for(int i=0;i<36;i++)
            {
                listBox.add(new BoxColor(R.drawable.brown));
            }
        }
        boxAdapter  = new BoxAdapter(R.layout.box_color_grid,getContext(),listBox );
        gridView.setAdapter(boxAdapter);
    }
}
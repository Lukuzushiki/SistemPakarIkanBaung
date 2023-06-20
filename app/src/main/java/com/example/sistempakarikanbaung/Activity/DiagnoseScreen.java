package com.example.sistempakarikanbaung.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistempakarikanbaung.Database.DatabaseHandler;
import com.example.sistempakarikanbaung.Model.GejalaModel;
import com.example.sistempakarikanbaung.R;

import java.util.ArrayList;

public class DiagnoseScreen extends AppCompatActivity {
    Button btn_next;
    RadioGroup rGroup;
    RadioButton rb1, rb2;
    TextView txt_question;
    double sumPenyakit1 = 1;
    double sumPenyakit2 = 1;
    double sumPenyakit3 = 1;
    double sumPenyakit4 = 1;
    double sumPenyakit5 = 1;
    double sumAll;
    int flag = 0;

    ArrayList<Double> penyakit1 = new ArrayList();
    ArrayList<Double> penyakit2 = new ArrayList();
    ArrayList<Double> penyakit3 = new ArrayList();
    ArrayList<Double> penyakit4 = new ArrayList();
    ArrayList<Double> penyakit5 = new ArrayList();

    private ArrayList<GejalaModel> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_screen);

        btn_next = findViewById(R.id.btn_next);

        txt_question = findViewById(R.id.pertanyaan);
        rGroup = findViewById(R.id.answer);
        rb1 = findViewById(R.id.radioYes);
        rb2 = findViewById(R.id.radioNo);

        if (savedInstanceState == null) {
            DatabaseHandler dbHelper = DatabaseHandler.getInstance(this);
            questionList = dbHelper.getAllQuestion();
            showNextQuestion();
        }

        Log.d("COUNTER", String.valueOf(flag));

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Harap Jawab Pertanyaan Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton ans = findViewById(rGroup.getCheckedRadioButtonId());
                String ansText = ans.getText().toString();
                if (ansText.equals("Yes")) {
                    Log.d("Probabilitas B", String.valueOf(questionList.get(flag).getProbabilitas_b()));
                    Log.d("Probabilitas P01", String.valueOf(questionList.get(flag).getProbabilitas_p01()));
                    Log.d("Probabilitas P02", String.valueOf(questionList.get(flag).getProbabilitas_p02()));
                    Log.d("Probabilitas P03", String.valueOf(questionList.get(flag).getProbabilitas_p03()));
                    Log.d("Probabilitas P04", String.valueOf(questionList.get(flag).getProbabilitas_p04()));
                    Log.d("Probabilitas P05", String.valueOf(questionList.get(flag).getProbabilitas_p05()));

                    sumPenyakit1 *= questionList.get(flag).getProbabilitas_p01();
                    sumPenyakit2 *= questionList.get(flag).getProbabilitas_p02();
                    sumPenyakit3 *= questionList.get(flag).getProbabilitas_p03();
                    sumPenyakit4 *= questionList.get(flag).getProbabilitas_p04();
                    sumPenyakit5 *= questionList.get(flag).getProbabilitas_p05();

                    Log.d("Sum Penyakit 1", String.valueOf(sumPenyakit1));
                    Log.d("Sum Penyakit 2", String.valueOf(sumPenyakit2));
                    Log.d("Sum Penyakit 3", String.valueOf(sumPenyakit3));
                    Log.d("Sum Penyakit 4", String.valueOf(sumPenyakit4));
                    Log.d("Sum Penyakit 5", String.valueOf(sumPenyakit5));
                } else {
                    Log.d("Probabilitas B", "No Value");
                    Log.d("Probabilitas P01", "No Value");
                    Log.d("Probabilitas P02", "No Value");
                    Log.d("Probabilitas P03", "No Value");
                    Log.d("Probabilitas P04", "No Value");
                    Log.d("Probabilitas P05", "No Value");
                }
                flag++;
                showNextQuestion();

                rGroup.clearCheck();
            }
        });
    }

    public void showNextQuestion() {
        if (flag < questionList.size()) {
            txt_question.setText(questionList.get(flag).getGejala());
        } else {
            sumAll = sumPenyakit1 + sumPenyakit2 + sumPenyakit3 + sumPenyakit4 + sumPenyakit5;

            double scorePenyakit1 = sumPenyakit1 / sumAll;
            double scorePenyakit2 = sumPenyakit2 / sumAll;
            double scorePenyakit3 = sumPenyakit3 / sumAll;
            double scorePenyakit4 = sumPenyakit4 / sumAll;
            double scorePenyakit5 = sumPenyakit5 / sumAll;

            Log.d("Sum Seluruh Penyakit", String.valueOf(sumAll));

            Log.d("Score Penyakit 1", String.valueOf(scorePenyakit1));
            Log.d("Score Penyakit 2", String.valueOf(scorePenyakit2));
            Log.d("Score Penyakit 3", String.valueOf(scorePenyakit3));
            Log.d("Score Penyakit 4", String.valueOf(scorePenyakit4));
            Log.d("Score Penyakit 5", String.valueOf(scorePenyakit5));

            Intent result = new Intent(getApplicationContext(), ResultScreen.class);
            //Set parameter to send to result intent for
            if (scorePenyakit1 > scorePenyakit2 && scorePenyakit1 > scorePenyakit3 && scorePenyakit1 > scorePenyakit4 && scorePenyakit1 > scorePenyakit5) {
                result.putExtra("id_penyakit", "P01");
            } else if (scorePenyakit2 > scorePenyakit1 && scorePenyakit2 > scorePenyakit3 && scorePenyakit2 > scorePenyakit4 && scorePenyakit2 > scorePenyakit5) {
                result.putExtra("id_penyakit", "P02");
            } else if (scorePenyakit3 > scorePenyakit1 && scorePenyakit3 > scorePenyakit2 && scorePenyakit3 > scorePenyakit4 && scorePenyakit3 > scorePenyakit5) {
                result.putExtra("id_penyakit", "P03");
            } else if (scorePenyakit4 > scorePenyakit1 && scorePenyakit4 > scorePenyakit2 && scorePenyakit4 > scorePenyakit3 && scorePenyakit4 > scorePenyakit5) {
                result.putExtra("id_penyakit", "P04");
            } else if (scorePenyakit5 > scorePenyakit1 && scorePenyakit5 > scorePenyakit2 && scorePenyakit5 > scorePenyakit3 && scorePenyakit5 > scorePenyakit4) {
                result.putExtra("id_penyakit", "P05");
            }
            startActivity(result);
            finish();
        }
    }

    public void sumProbability() {
        if (flag == 0) {

        }
    }
}
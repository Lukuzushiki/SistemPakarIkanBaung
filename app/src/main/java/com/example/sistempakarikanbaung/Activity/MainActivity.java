package com.example.sistempakarikanbaung.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sistempakarikanbaung.Database.DatabaseHandler;
import com.example.sistempakarikanbaung.Model.GejalaModel;
import com.example.sistempakarikanbaung.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout diagnose, list, help, about;
    private ArrayList<GejalaModel> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diagnose = findViewById(R.id.diagnose_menu);
        list = findViewById(R.id.list_menu);
        help = findViewById(R.id.help_menu);
        about = findViewById(R.id.about_menu);

        if (savedInstanceState == null) {
            DatabaseHandler dbHelper = DatabaseHandler.getInstance(this);
            questionList = dbHelper.getAllQuestion();

            for(int i = 0; i < questionList.size(); i++){
                Log.d("QUESTION LIST", questionList.get(i).getId());
            }
        }

        diagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diagnose = new Intent(MainActivity.this, DiagnoseScreen.class);
                startActivity(diagnose);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(MainActivity.this, ListScreen.class);
                startActivity(list);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(MainActivity.this, HelpScreen.class);
                startActivity(help);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about = new Intent(MainActivity.this, AboutScreen.class);
                startActivity(about);
            }
        });
    }
}

package com.example.sistempakarikanbaung.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sistempakarikanbaung.Adapter.MencegahAdapter;
import com.example.sistempakarikanbaung.Adapter.MengobatiAdapter;
import com.example.sistempakarikanbaung.Database.DatabaseHandler;
import com.example.sistempakarikanbaung.Model.CegahModel;
import com.example.sistempakarikanbaung.Model.ObatModel;
import com.example.sistempakarikanbaung.R;

import java.util.ArrayList;

public class ResultScreen extends AppCompatActivity {
    Button btn_main;
    RecyclerView rv_mencegah, rv_mengobati;
    RecyclerView.Adapter ad_mencegah, ad_mengobati;
    RecyclerView.LayoutManager lm_mencegah, lm_mengobati;

    private ArrayList<CegahModel> cegahList;
    private ArrayList<ObatModel> obatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btn_main = findViewById(R.id.btn_main);
        rv_mencegah = findViewById(R.id.recycler_mencegah);
        rv_mengobati = findViewById(R.id.recycler_mengobati);

        lm_mencegah =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_mencegah.setLayoutManager(lm_mencegah);

        lm_mengobati = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_mengobati.setLayoutManager(lm_mengobati);

        if (savedInstanceState == null) {
            //Take parameter from Diagnose Intent
            Bundle extras = getIntent().getExtras();
            String params = extras.getString("id_penyakit");

            DatabaseHandler dbHelper = DatabaseHandler.getInstance(this);
            cegahList = dbHelper.getCegahbyPenyakit(params);
            obatList = dbHelper.getObatPenyakit(params);

            ad_mencegah = new MencegahAdapter(ResultScreen.this, cegahList);
            rv_mencegah.setAdapter(ad_mencegah);

            ad_mengobati = new MengobatiAdapter(ResultScreen.this, obatList);
            rv_mengobati.setAdapter(ad_mengobati);

            Log.d("Mencegah", String.valueOf(cegahList.size()));
            Log.d("Mengobati", String.valueOf(obatList.size()));
        }

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}

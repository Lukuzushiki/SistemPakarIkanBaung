package com.example.sistempakarikanbaung.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistempakarikanbaung.Adapter.GejalaAdapter;
import com.example.sistempakarikanbaung.Adapter.PenyakitAdapter;
import com.example.sistempakarikanbaung.Database.DatabaseHandler;
import com.example.sistempakarikanbaung.Model.GejalaPenyakitModel;
import com.example.sistempakarikanbaung.Model.PenyakitModel;
import com.example.sistempakarikanbaung.R;

import java.util.ArrayList;

public class DetailListScreen extends AppCompatActivity {
    Button btn_main;
    ImageView btn_back, gambar_penyakit;
    TextView nama_penyakit;
    RecyclerView rv_gejala;
    RecyclerView.Adapter ad_gejala;
    RecyclerView.LayoutManager lm_gejala;

    private ArrayList<GejalaPenyakitModel> gejalaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_screen);

        //Mengambil data dari intent sebelumnya
        Bundle bundle = getIntent().getExtras();
        final String jenis_penyakit_baru = bundle.getString("id_penyakit");
        Log.d("Penyakit", jenis_penyakit_baru);

        gambar_penyakit = findViewById(R.id.gambar_penyakit);
        nama_penyakit = findViewById(R.id.nama_penyakit);
        btn_back = findViewById(R.id.btn_back);
        btn_main = findViewById(R.id.btn_main);
        rv_gejala = findViewById(R.id.recycler_gejala);

        lm_gejala = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_gejala.setLayoutManager(lm_gejala);

        if (savedInstanceState == null) {
            DatabaseHandler dbHelper = DatabaseHandler.getInstance(this);
            gejalaList = dbHelper.getGejalaPenyakit(jenis_penyakit_baru);

            ad_gejala = new GejalaAdapter(DetailListScreen.this, gejalaList);
            rv_gejala.setAdapter(ad_gejala);
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailListScreen.this, ListScreen.class));
                onBackPressed();
            }
        });

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailListScreen.this, MainActivity.class));
                onBackPressed();
            }
        });
    }
}

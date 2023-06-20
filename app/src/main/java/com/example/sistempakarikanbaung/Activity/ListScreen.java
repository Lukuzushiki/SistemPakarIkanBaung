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
import com.example.sistempakarikanbaung.Adapter.PenyakitAdapter;
import com.example.sistempakarikanbaung.Database.DatabaseHandler;
import com.example.sistempakarikanbaung.Model.PenyakitModel;
import com.example.sistempakarikanbaung.R;

import java.util.ArrayList;

public class ListScreen extends AppCompatActivity {
    Button btn_main;
    RecyclerView rv_penyakit;
    RecyclerView.Adapter ad_penyakit;
    RecyclerView.LayoutManager lm_penyakit;

    private ArrayList<PenyakitModel> penyakitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        btn_main = findViewById(R.id.btn_main);
        rv_penyakit = findViewById(R.id.recycler_penyakit);

        lm_penyakit = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_penyakit.setLayoutManager(lm_penyakit);

        if (savedInstanceState == null) {
            DatabaseHandler dbHelper = DatabaseHandler.getInstance(this);
            penyakitList = dbHelper.getPenyakitList();

            ad_penyakit = new PenyakitAdapter(ListScreen.this, penyakitList);
            rv_penyakit.setAdapter(ad_penyakit);

            Log.d("Mencegah", String.valueOf(penyakitList.size()));

        }

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}

package com.example.sistempakarikanbaung.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistempakarikanbaung.R;

public class SplashScreen extends AppCompatActivity {
    Animation app_spalsh, bottomToTop;
    ImageView logo;
    TextView txtSistem, txtPenyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Load Animation Library
        app_spalsh = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        bottomToTop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop);

        logo = findViewById(R.id.logo);
        txtSistem = findViewById(R.id.sistem);
        txtPenyakit = findViewById(R.id.penyakit);

        //Run Animation
        logo.startAnimation(app_spalsh);
        txtSistem.startAnimation(bottomToTop);
        txtPenyakit.startAnimation(bottomToTop);

        final Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Merubah ke Activity Lain
                Intent Main = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(Main);
                finish();
           }
        }, 2000);

    }
}

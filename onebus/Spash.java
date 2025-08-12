package com.example.onebus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper; //usar looper para execultar depois de 3000

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Spash extends AppCompatActivity {
    private static final int TEMPO_SPLASH = 1200; // 1,2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(Spash.this, Login.class));
            finish();//fecha a tela para que nao volte para essa tela
        }, TEMPO_SPLASH);
    }
}
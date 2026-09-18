package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla Splash — Flujo 1 (Login/Registro).
 * Muestra la marca por un momento y navega automáticamente a Login.
 */
public class SplashActivity extends AppCompatActivity {

    private static final long DURACION_SPLASH_MS = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, DURACION_SPLASH_MS);
    }
}

package com.encuadre.mobile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Pantalla Splash - Flujo 1 (Login/Registro).
 * Muestra la marca por un momento y navega automáticamente a Login.
 * <p>
 * También dispara las 4 notificaciones del componente "Push Banner" al
 * arrancar la app (simulan que "ya llegaron" mientras usas el celular).
 * Quedan en la barra de notificaciones del sistema hasta que se descarten,
 * así no hace falta ninguna pantalla ni botón aparte para probarlas.
 */
public class SplashActivity extends AppCompatActivity {

    private static final long DURACION_SPLASH_MS = 1500;
    private static final int CODIGO_PERMISO_NOTIFICACIONES = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    CODIGO_PERMISO_NOTIFICACIONES);
        } else {
            dispararTodasLasNotificaciones();
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, DURACION_SPLASH_MS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISO_NOTIFICACIONES
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            dispararTodasLasNotificaciones();
        }
    }

    private void dispararTodasLasNotificaciones() {
        PushNotificationHelper.mostrar(this,
                getString(R.string.push_hora_salir_titulo),
                getString(R.string.push_hora_salir_texto),
                AlarmaSalidaActivity.class, null);
        PushNotificationHelper.mostrar(this,
                getString(R.string.push_recomendacion_titulo),
                getString(R.string.push_recomendacion_texto),
                RecomendacionActivity.class, null);
        PushNotificationHelper.mostrar(this,
                getString(R.string.push_estreno_titulo),
                getString(R.string.push_estreno_texto),
                EstrenoActivity.class, null);
        PushNotificationHelper.mostrar(this,
                getString(R.string.push_maraton_titulo),
                getString(R.string.push_maraton_texto),
                MaratonPendienteActivity.class, null);
    }
}
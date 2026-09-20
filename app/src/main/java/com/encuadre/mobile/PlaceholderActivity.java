package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla reutilizable para probar la navegación de punta a punta hacia
 * pantallas que todavía no existen en el repo (típicamente, las de Juan).
 * <p>
 * Uso:
 * Intent intent = new Intent(this, PlaceholderActivity.class);
 * intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, "Inicio (pantalla de Juan)");
 * startActivity(intent);
 * <p>
 * Cuando la pantalla real exista, reemplazar el destino del Intent por su Activity real.
 * <p>
 * Nota: los botones de prueba (ver Bandeja, disparar Push Banner) se movieron a
 * MenuPruebasActivity para no llenar esta pantalla de cosas que no son del mockup real.
 */
public class PlaceholderActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE_PANTALLA = "extra_nombre_pantalla";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);

        String nombrePantalla = getIntent().getStringExtra(EXTRA_NOMBRE_PANTALLA);
        TextView tvNombrePantalla = findViewById(R.id.tv_nombre_pantalla);
        tvNombrePantalla.setText(nombrePantalla != null ? nombrePantalla : "Pantalla sin nombre");

        Button btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> finish());

        // Campana de notificaciones: replica el ícono real de Inicio (de Juan) en Figma.
        findViewById(R.id.btn_notificaciones).setOnClickListener(v ->
                startActivity(new Intent(PlaceholderActivity.this, BandejaNotificacionesActivity.class)));

        // SOLO PARA PRUEBAS — quitar este bloque completo antes del commit final.
        // Atajo desde el placeholder de "Inicio" para llegar a Perfil y a
        // Notificaciones (dentro de Ajustes) sin pelear con la configuración
        // de "Specified Activity" de Android Studio.
        View navBar = findViewById(R.id.nav_bar_bottom);
        navBar.findViewById(R.id.nav_item_perfil).setOnClickListener(v ->
                startActivity(new Intent(PlaceholderActivity.this, PerfilActivity.class)));
        navBar.findViewById(R.id.nav_item_ajustes).setOnClickListener(v ->
                startActivity(new Intent(PlaceholderActivity.this, NotificacionesActivity.class)));
    }
}
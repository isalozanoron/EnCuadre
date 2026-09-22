package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla Bandeja de notificaciones.
 * Prototipo no funcional: cada tarjeta navega a la pantalla correspondiente
 * (Recomendación, Estreno y Maratón pendiente).
 * Barra de navegación inferior con "Inicio" resaltado, igual que en el mockup.
 */
public class BandejaNotificacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandeja_notificaciones);

        findViewById(R.id.btn_volver).setOnClickListener(v -> finish());

        findViewById(R.id.card_recomendacion).setOnClickListener(v ->
                startActivity(new Intent(this, RecomendacionActivity.class)));
        findViewById(R.id.card_estreno).setOnClickListener(v ->
                startActivity(new Intent(this, EstrenoActivity.class)));
        findViewById(R.id.card_maraton).setOnClickListener(v ->
                startActivity(new Intent(BandejaNotificacionesActivity.this, MaratonPendienteActivity.class)));

        View navBar = findViewById(R.id.nav_bar_bottom);
        NavBarHelper.marcarSeleccionado(this, navBar, NavBarHelper.Tab.INICIO);

        NavBarHelper.conectar(this, navBar, NavBarHelper.Tab.INICIO);
    }

}
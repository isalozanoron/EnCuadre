package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

/**
 * Pantalla Maratón pendiente.
 * Prototipo no funcional: "Me interesa" y "No me interesa" muestran un
 * Snackbar de confirmación (igual al patrón de "Maratón guardado" que trae
 * el propio mockup), sin persistir nada real.
 * Barra de navegación inferior con "Inicio" resaltado, igual que en el mockup.
 * Nota: la imagen de la película se dejó como bloque genérico a propósito,
 * no se reproduce el póster real por derechos de autor.
 */
public class MaratonPendienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maraton_pendiente);

        findViewById(R.id.btn_volver).setOnClickListener(v -> finish());

        View raiz = findViewById(android.R.id.content);
        View navBar = findViewById(R.id.nav_bar_bottom);

        findViewById(R.id.btn_me_interesa).setOnClickListener(v ->
                mostrarSnackbar(raiz, navBar, R.string.maraton_guardado));
        findViewById(R.id.btn_no_me_interesa).setOnClickListener(v ->
                mostrarSnackbar(raiz, navBar, R.string.maraton_descartado));

        NavBarHelper.marcarSeleccionado(this, navBar, NavBarHelper.Tab.INICIO);

        NavBarHelper.conectar(this, navBar, NavBarHelper.Tab.INICIO);
    }

    /** Ancla el Snackbar encima de la barra de navegación, no al borde absoluto de la pantalla. */
    private void mostrarSnackbar(View raiz, View navBar, int textoResId) {
        Snackbar snackbar = Snackbar.make(raiz, textoResId, Snackbar.LENGTH_SHORT);
        if (navBar != null) {
            snackbar.setAnchorView(navBar);
        }
        snackbar.show();
    }

}
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
        findViewById(R.id.btn_me_interesa).setOnClickListener(v ->
                Snackbar.make(raiz, R.string.maraton_guardado, Snackbar.LENGTH_SHORT).show());
        findViewById(R.id.btn_no_me_interesa).setOnClickListener(v ->
                Snackbar.make(raiz, R.string.maraton_descartado, Snackbar.LENGTH_SHORT).show());

        View navBar = findViewById(R.id.nav_bar_bottom);
        NavBarHelper.marcarSeleccionado(this, navBar, NavBarHelper.Tab.INICIO);

        // "Inicio", "Mis Funciones" y "Ajustes" son pantallas de Juan; mientras
        // el las sube al repo, se navega a un placeholder para poder probar
        // el flujo completo. Cuando existan, reemplazar por sus Activities reales.
        navBar.findViewById(R.id.nav_item_inicio).setOnClickListener(v ->
                irAPlaceholder("Inicio (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_mis_funciones).setOnClickListener(v ->
                irAPlaceholder("Mis Funciones (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_ajustes).setOnClickListener(v ->
                startActivity(new Intent(MaratonPendienteActivity.this, NotificacionesActivity.class)));
        navBar.findViewById(R.id.nav_item_perfil).setOnClickListener(v ->
                startActivity(new Intent(MaratonPendienteActivity.this, PerfilActivity.class)));
    }

    private void irAPlaceholder(String nombrePantalla) {
        Intent intent = new Intent(MaratonPendienteActivity.this, PlaceholderActivity.class);
        intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, nombrePantalla);
        startActivity(intent);
    }
}

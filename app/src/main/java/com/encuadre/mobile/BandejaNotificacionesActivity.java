package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla Bandeja de notificaciones.
 * Prototipo no funcional: cada tarjeta navega a la pantalla correspondiente
 * (Recomendación y Estreno son de Juan → placeholder; Maratón pendiente es
 * propia pero aún no está construida → placeholder también, por ahora).
 * Barra de navegación inferior con "Inicio" resaltado, igual que en el mockup.
 */
public class BandejaNotificacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandeja_notificaciones);

        findViewById(R.id.btn_volver).setOnClickListener(v -> finish());

        findViewById(R.id.card_recomendacion).setOnClickListener(v ->
                irAPlaceholder("Recomendación (pantalla de Juan)"));
        findViewById(R.id.card_estreno).setOnClickListener(v ->
                irAPlaceholder("Estreno (pantalla de Juan)"));
        // TODO: reemplazar por MaratonPendienteActivity cuando esté construida.
        findViewById(R.id.card_maraton).setOnClickListener(v ->
                irAPlaceholder("Maratón pendiente (aún sin construir)"));

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
                startActivity(new Intent(BandejaNotificacionesActivity.this, NotificacionesActivity.class)));
        navBar.findViewById(R.id.nav_item_perfil).setOnClickListener(v ->
                startActivity(new Intent(BandejaNotificacionesActivity.this, PerfilActivity.class)));
    }

    private void irAPlaceholder(String nombrePantalla) {
        Intent intent = new Intent(BandejaNotificacionesActivity.this, PlaceholderActivity.class);
        intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, nombrePantalla);
        startActivity(intent);
    }
}

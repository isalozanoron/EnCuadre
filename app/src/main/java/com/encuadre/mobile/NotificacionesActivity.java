package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla Notificaciones (dentro de Ajustes).
 * Prototipo no funcional: los 4 switches son un componente hecho a mano
 * (FrameLayout + una "bolita"), NO el widget nativo de Android, porque ese
 * no respetaba los colores exactos de la paleta ni el tamaño del mockup.
 * Se pueden tocar para encender/apagar (interacción visual local), sin
 * persistir nada real.
 * La barra de navegación inferior es compartida (nav_bar_bottom.xml + NavBarHelper),
 * marcando "Ajustes" como la pestaña activa, igual que en el mockup.
 */
public class NotificacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        findViewById(R.id.btn_volver).setOnClickListener(v -> finish());

        // Los 4 switches inician encendidos, igual que en el mockup
        configurarSwitch(R.id.switch_modo_silencio, R.id.thumb_modo_silencio);
        configurarSwitch(R.id.switch_alarma_salida, R.id.thumb_alarma_salida);
        configurarSwitch(R.id.switch_recomendaciones, R.id.thumb_recomendaciones);
        configurarSwitch(R.id.switch_estrenos, R.id.thumb_estrenos);

        View navBar = findViewById(R.id.nav_bar_bottom);
        NavBarHelper.marcarSeleccionado(this, navBar, NavBarHelper.Tab.AJUSTES);

        // "Inicio", "Mis Funciones" y "Ajustes" son pantallas de Juan; mientras
        // el las sube al repo, se navega a un placeholder para poder probar
        // el flujo completo. Cuando existan, reemplazar por sus Activities reales.
        navBar.findViewById(R.id.nav_item_inicio).setOnClickListener(v ->
                irAPlaceholder("Inicio (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_mis_funciones).setOnClickListener(v ->
                irAPlaceholder("Mis Funciones (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_ajustes).setOnClickListener(v ->
                irAPlaceholder("Ajustes (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_perfil).setOnClickListener(v ->
                startActivity(new Intent(NotificacionesActivity.this, PerfilActivity.class)));
    }

    /** Switch hecho a mano: contenedor (pista) + bolita, ambos con id propio. */
    private void configurarSwitch(int idPista, int idBolita) {
        FrameLayout pista = findViewById(idPista);
        View bolita = findViewById(idBolita);

        pista.setTag(true); // encendido por defecto, igual que el mockup
        pista.setOnClickListener(v -> {
            boolean nuevoEstado = !(boolean) v.getTag();
            aplicarEstadoSwitch(pista, bolita, nuevoEstado);
            v.setTag(nuevoEstado);
        });
    }

    private void aplicarEstadoSwitch(FrameLayout pista, View bolita, boolean encendido) {
        pista.setBackgroundResource(encendido ? R.drawable.bg_switch_track_on : R.drawable.bg_switch_track_off);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) bolita.getLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL | (encendido ? Gravity.END : Gravity.START);
        bolita.setLayoutParams(params);
    }

    private void irAPlaceholder(String nombrePantalla) {
        Intent intent = new Intent(NotificacionesActivity.this, PlaceholderActivity.class);
        intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, nombrePantalla);
        startActivity(intent);
    }
}
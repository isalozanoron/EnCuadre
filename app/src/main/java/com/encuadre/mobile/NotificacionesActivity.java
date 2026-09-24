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

    private static final int TAMANO_BOLITA_ON_DP = 24;
    private static final int TAMANO_BOLITA_OFF_DP = 16;

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

        NavBarHelper.conectar(this, navBar, NavBarHelper.Tab.AJUSTES);
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

    /**
     * Desactivado (confirmado contra Figma): pista #E0E0E0 con borde 2dp #666666,
     * bolita más chica (16dp en vez de 24dp) y en #666666, no la misma bolita
     * del estado activado solo movida de lado.
     */
    private void aplicarEstadoSwitch(FrameLayout pista, View bolita, boolean encendido) {
        pista.setBackgroundResource(encendido ? R.drawable.bg_switch_track_on : R.drawable.bg_switch_track_off);
        bolita.setBackgroundResource(encendido ? R.drawable.bg_switch_thumb : R.drawable.bg_switch_thumb_off);

        float densidad = bolita.getResources().getDisplayMetrics().density;
        int tamanoDp = encendido ? TAMANO_BOLITA_ON_DP : TAMANO_BOLITA_OFF_DP;
        int tamanoPx = Math.round(tamanoDp * densidad);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) bolita.getLayoutParams();
        params.width = tamanoPx;
        params.height = tamanoPx;
        int margenPx = Math.round(4 * densidad);
        params.leftMargin = encendido ? 0 : margenPx;
        params.rightMargin = encendido ? margenPx : 0;
        params.gravity = Gravity.CENTER_VERTICAL | (encendido ? Gravity.END : Gravity.START);
        bolita.setLayoutParams(params);
    }

}
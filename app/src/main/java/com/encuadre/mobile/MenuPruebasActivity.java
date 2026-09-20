package com.encuadre.mobile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Menú de pruebas — NO es parte del mockup real. Sirve para verificar el
 * código de las pantallas terminadas y disparar el componente Push Banner,
 * sin pelear con la configuración de "Specified Activity" de Android Studio.
 * Se elimina antes de la entrega final. Se abre configurando esta Activity
 * como "Specified Activity" en Edit Configurations.
 */
public class MenuPruebasActivity extends AppCompatActivity {

    private static final int CODIGO_PERMISO_NOTIFICACIONES = 100;

    private String pushTituloPendiente;
    private String pushTextoPendiente;
    private Class<?> pushDestinoPendiente;
    private String pushExtraPendiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pruebas);

        findViewById(R.id.btn_ir_perfil).setOnClickListener(v ->
                startActivity(new Intent(this, PerfilActivity.class)));
        findViewById(R.id.btn_ir_notificaciones).setOnClickListener(v ->
                startActivity(new Intent(this, NotificacionesActivity.class)));
        findViewById(R.id.btn_ir_bandeja).setOnClickListener(v ->
                startActivity(new Intent(this, BandejaNotificacionesActivity.class)));
        findViewById(R.id.btn_ir_maraton).setOnClickListener(v ->
                startActivity(new Intent(this, MaratonPendienteActivity.class)));

        findViewById(R.id.btn_push_salir).setOnClickListener(v -> dispararPush(
                getString(R.string.push_hora_salir_titulo),
                getString(R.string.push_hora_salir_texto),
                PlaceholderActivity.class, "Alarma de salida (pantalla de Juan)"));
        findViewById(R.id.btn_push_recomendacion).setOnClickListener(v -> dispararPush(
                getString(R.string.push_recomendacion_titulo),
                getString(R.string.push_recomendacion_texto),
                PlaceholderActivity.class, "Recomendación (pantalla de Juan)"));
        findViewById(R.id.btn_push_estreno).setOnClickListener(v -> dispararPush(
                getString(R.string.push_estreno_titulo),
                getString(R.string.push_estreno_texto),
                PlaceholderActivity.class, "Estreno (pantalla de Juan)"));
        findViewById(R.id.btn_push_maraton).setOnClickListener(v -> dispararPush(
                getString(R.string.push_maraton_titulo),
                getString(R.string.push_maraton_texto),
                MaratonPendienteActivity.class, null));
    }

    /**
     * En Android 13+ hace falta permiso en tiempo de ejecución para mostrar
     * notificaciones. Si ya está concedido, muestra el push de una vez;
     * si no, lo pide primero y lo muestra cuando el usuario lo conceda.
     */
    private void dispararPush(String titulo, String texto, Class<?> destino, String extraNombre) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            pushTituloPendiente = titulo;
            pushTextoPendiente = texto;
            pushDestinoPendiente = destino;
            pushExtraPendiente = extraNombre;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    CODIGO_PERMISO_NOTIFICACIONES);
            return;
        }
        PushNotificationHelper.mostrar(this, titulo, texto, destino, extraNombre);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISO_NOTIFICACIONES
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && pushTituloPendiente != null) {
            PushNotificationHelper.mostrar(this, pushTituloPendiente, pushTextoPendiente,
                    pushDestinoPendiente, pushExtraPendiente);
        }
    }
}

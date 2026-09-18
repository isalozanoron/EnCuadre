package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla Login — Flujo 1 (Login/Registro).
 * Agrupa los 4 estados del mockup en una sola vista de código:
 * Vacío, Con datos, Procesando y Error.
 *
 * Prototipo no funcional: no hay backend real, la validación se simula.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo;
    private EditText etContrasena;
    private TextView tvError;
    private Button btnIniciarSesion;
    private ProgressBar progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.et_correo);
        etContrasena = findViewById(R.id.et_contrasena);
        tvError = findViewById(R.id.tv_error);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        progressLogin = findViewById(R.id.progress_login);

        Button btnRegistrarse = findViewById(R.id.btn_registrarse);
        TextView tvOlvidaste = findViewById(R.id.tv_olvidaste);

        btnIniciarSesion.setOnClickListener(v -> intentarIniciarSesion());
        btnRegistrarse.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));
        tvOlvidaste.setOnClickListener(v -> mostrarDialogoRecuperarContrasena());
    }

    private void intentarIniciarSesion() {
        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();

        // Estado: Vacío -> error de validación
        if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
            mostrarError(getString(R.string.error_campos_vacios));
            return;
        }

        // Estado: Con datos -> Procesando
        mostrarEstadoProcesando(true);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            mostrarEstadoProcesando(false);
            // TODO: reemplazar por la llamada real al backend cuando exista.
            // Por ahora, cualquier correo/contraseña no vacíos navega a Inicio.
            navegarAInicio();
        }, 1200);
    }

    private void mostrarEstadoProcesando(boolean procesando) {
        btnIniciarSesion.setEnabled(!procesando);
        btnIniciarSesion.setText(procesando ? "" : getString(R.string.accion_iniciar_sesion));
        progressLogin.setVisibility(procesando ? View.VISIBLE : View.GONE);
        if (procesando) {
            tvError.setVisibility(View.GONE);
        }
    }

    private void mostrarError(String mensaje) {
        tvError.setText(mensaje);
        tvError.setVisibility(View.VISIBLE);
    }

    private void navegarAInicio() {
        // "Inicio" es una pantalla de Juan (Mobile). Mientras él la agrega al repo,
        // se navega a un placeholder para poder probar el flujo completo de punta a punta.
        // Cuando exista HomeActivity real, cambiar PlaceholderActivity.class por HomeActivity.class.
        Intent intent = new Intent(LoginActivity.this, PlaceholderActivity.class);
        intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, "Inicio (pantalla de Juan)");
        startActivity(intent);
    }

    private void mostrarDialogoRecuperarContrasena() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialogo_recuperar_titulo)
                .setMessage(R.string.dialogo_recuperar_mensaje)
                .setPositiveButton(R.string.dialogo_entendido, (dialog, which) -> dialog.dismiss())
                .show();
    }
}

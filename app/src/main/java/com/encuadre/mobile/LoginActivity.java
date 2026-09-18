package com.encuadre.mobile;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Pantalla Login - Flujo 1 (Login/Registro).
 * Agrupa los 4 estados del mockup en una sola vista de codigo:
 * Vacio, Con datos, Procesando y Error.
 *
 * El estado de error usa el mecanismo nativo de TextInputLayout.setError():
 * el mensaje visible solo aparece bajo Contrasena (como en el mockup), el
 * borde se pone blanco y mas grueso (boxStrokeErrorColor en el XML), y el
 * icono de error es el que trae Material por defecto.
 *
 * NOTA / diferencia conocida con Figma: Material tambien pinta de rojo la
 * etiqueta flotante durante el error, y no hay forma publica confiable de
 * evitarlo sin reconstruir el campo a mano. Se deja asi por ahora.
 *
 * Prototipo no funcional: no hay backend real, la validacion se simula.
 */
public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilCorreo;
    private TextInputLayout tilContrasena;
    private TextInputEditText etCorreo;
    private TextInputEditText etContrasena;
    private Button btnIniciarSesion;
    private ProgressBar progressLogin;
    private boolean procesandoLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilCorreo = findViewById(R.id.til_correo);
        tilContrasena = findViewById(R.id.til_contrasena);
        etCorreo = findViewById(R.id.et_correo);
        etContrasena = findViewById(R.id.et_contrasena);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        progressLogin = findViewById(R.id.progress_login);

        Button btnRegistrarse = findViewById(R.id.btn_registrarse);
        TextView tvOlvidaste = findViewById(R.id.tv_olvidaste);

        btnIniciarSesion.setOnClickListener(v -> intentarIniciarSesion());
        btnRegistrarse.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));
        tvOlvidaste.setOnClickListener(v -> mostrarDialogoRecuperarContrasena());

        // Normal = Texto Secundario, Enfocado = Texto Principal
        aplicarColorSegunFoco(tilCorreo);
        aplicarColorSegunFoco(tilContrasena);
    }

    private void aplicarColorSegunFoco(TextInputLayout campo) {
        int[][] estados = new int[][]{
                new int[]{android.R.attr.state_focused},
                new int[]{}
        };
        int[] colores = new int[]{
                ContextCompat.getColor(this, R.color.encuadre_texto_principal),
                ContextCompat.getColor(this, R.color.encuadre_texto_secundario)
        };
        ColorStateList colorSegunFoco = new ColorStateList(estados, colores);
        campo.setBoxStrokeColorStateList(colorSegunFoco);
        campo.setDefaultHintTextColor(colorSegunFoco);
    }

    private void intentarIniciarSesion() {
        if (procesandoLogin) {
            return;
        }
        limpiarErrores();

        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();

        // Estado: Vacio -> error de validacion
        if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
            mostrarError(getString(R.string.error_campos_vacios));
            return;
        }

        // Estado: Con datos -> Procesando
        mostrarEstadoProcesando(true);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            mostrarEstadoProcesando(false);
            // TODO: reemplazar por la llamada real al backend cuando exista.
            // Por ahora, cualquier correo/contrasena no vacios navega a Inicio.
            navegarAInicio();
        }, 1200);
    }

    /**
     * No usa setEnabled(false): Android le pone su estilo de "deshabilitado"
     * (bastante oscuro) que no coincide con el mockup, donde el boton sigue
     * coral normal mientras carga. En su lugar, se bloquea el doble clic con
     * la bandera procesandoLogin.
     */
    private void mostrarEstadoProcesando(boolean procesando) {
        procesandoLogin = procesando;
        progressLogin.setVisibility(procesando ? View.VISIBLE : View.GONE);
        if (procesando) {
            limpiarErrores();
        }
    }

    /**
     * Muestra el mensaje visible solo bajo Contrasena (como en el mockup),
     * y activa el borde/icono de error en Correo tambien, sin texto duplicado.
     */
    private void mostrarError(String mensaje) {
        tilCorreo.setError(" ");
        tilContrasena.setError(mensaje);
    }

    private void limpiarErrores() {
        tilCorreo.setError(null);
        tilCorreo.setErrorEnabled(false);
        tilContrasena.setError(null);
        tilContrasena.setErrorEnabled(false);
    }

    private void navegarAInicio() {
        // "Inicio" es una pantalla de Juan (Mobile). Mientras el la agrega al repo,
        // se navega a un placeholder para poder probar el flujo completo de punta a punta.
        // Cuando exista HomeActivity real, cambiar PlaceholderActivity.class por HomeActivity.class.
        Intent intent = new Intent(LoginActivity.this, PlaceholderActivity.class);
        intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, "Inicio (pantalla de Juan)");
        startActivity(intent);
    }

    private void mostrarDialogoRecuperarContrasena() {
        new MaterialAlertDialogBuilder(this, R.style.EnCuadreDialogTheme)
                .setTitle(R.string.dialogo_recuperar_titulo)
                .setMessage(R.string.dialogo_recuperar_mensaje)
                .setPositiveButton(R.string.dialogo_entendido, (dialog, which) -> dialog.dismiss())
                .show();
    }
}
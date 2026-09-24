package com.encuadre.mobile;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Pantalla Login - Flujo 1 (Login/Registro).
 * Agrupa los 4 estados del mockup en una sola vista de código:
 * Vacío, Con datos, Procesando y Error.
 *
 * Los campos de texto son vistas propias (FrameLayout + TextView de
 * etiqueta + EditText), no TextInputLayout de Material: la etiqueta,
 * el color y el grosor del borde en cada estado (normal/enfocado/error)
 * no coincidían con Figma y Material no daba control confiable sobre
 * los tres a la vez, mismo criterio que otros componentes de esta app.
 *
 * Estados del campo (confirmados contra Figma):
 * - Normal (vacío, sin foco): borde 1dp texto_secundario, etiqueta bold texto_secundario.
 * - Enfocado: borde 3dp acento, etiqueta regular acento.
 * - Error: borde 3dp texto_principal, etiqueta regular texto_principal (NUNCA rojo).
 *
 * Prototipo no funcional: no hay backend real, la validación se simula.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int ESTADO_NORMAL = 0;
    private static final int ESTADO_FOCUS = 1;
    private static final int ESTADO_ERROR = 2;

    private FrameLayout campoCorreo;
    private FrameLayout campoContrasena;
    private TextView labelCorreo;
    private TextView labelContrasena;
    private EditText etCorreo;
    private EditText etContrasena;
    private ImageView ivEstadoCorreo;
    private TextView tvErrorContrasena;
    private Button btnIniciarSesion;
    private ProgressBar progressLogin;
    private boolean procesandoLogin = false;
    private boolean enError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoCorreo = findViewById(R.id.campo_correo);
        campoContrasena = findViewById(R.id.campo_contrasena);
        labelCorreo = findViewById(R.id.tv_label_correo);
        labelContrasena = findViewById(R.id.tv_label_contrasena);
        etCorreo = findViewById(R.id.et_correo);
        etContrasena = findViewById(R.id.et_contrasena);
        ivEstadoCorreo = findViewById(R.id.iv_estado_correo);
        tvErrorContrasena = findViewById(R.id.tv_error_contrasena);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);
        progressLogin = findViewById(R.id.progress_login);

        Button btnRegistrarse = findViewById(R.id.btn_registrarse);
        TextView tvOlvidaste = findViewById(R.id.tv_olvidaste);

        btnIniciarSesion.setOnClickListener(v -> intentarIniciarSesion());
        btnRegistrarse.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));
        tvOlvidaste.setOnClickListener(v -> mostrarDialogoRecuperarContrasena());

        ivEstadoCorreo.setOnClickListener(v -> {
            if (!enError) {
                etCorreo.setText("");
            }
        });

        etCorreo.setOnFocusChangeListener((v, tieneFoco) ->
                aplicarEstadoCampo(campoCorreo, labelCorreo, tieneFoco ? ESTADO_FOCUS : ESTADO_NORMAL));
        etContrasena.setOnFocusChangeListener((v, tieneFoco) ->
                aplicarEstadoCampo(campoContrasena, labelContrasena, tieneFoco ? ESTADO_FOCUS : ESTADO_NORMAL));

        // Estado inicial: ambos campos en Normal
        aplicarEstadoCampo(campoCorreo, labelCorreo, ESTADO_NORMAL);
        aplicarEstadoCampo(campoContrasena, labelContrasena, ESTADO_NORMAL);
    }

    /** Aplica el fondo del campo y el color/grosor de la etiqueta según el estado. */
    private void aplicarEstadoCampo(FrameLayout campo, TextView label, int estado) {
        if (enError) {
            return; // El error tiene prioridad hasta que el usuario vuelva a intentar.
        }
        switch (estado) {
            case ESTADO_FOCUS:
                campo.setBackgroundResource(R.drawable.bg_campo_focus);
                label.setTextColor(ContextCompat.getColor(this, R.color.encuadre_acento));
                label.setTypeface(null, Typeface.NORMAL);
                break;
            case ESTADO_NORMAL:
            default:
                campo.setBackgroundResource(R.drawable.bg_campo_normal);
                label.setTextColor(ContextCompat.getColor(this, R.color.encuadre_texto_secundario));
                label.setTypeface(null, Typeface.BOLD);
                break;
        }
    }

    private void intentarIniciarSesion() {
        if (procesandoLogin) {
            return;
        }
        limpiarErrores();

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

    /**
     * No usa setEnabled(false): Android le pone su estilo de "deshabilitado"
     * (bastante oscuro) que no coincide con el mockup, donde el botón sigue
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

    /** Los dos campos pasan a Error; el mensaje solo se muestra bajo Contraseña, como en el mockup. */
    private void mostrarError(String mensaje) {
        enError = true;
        campoCorreo.setBackgroundResource(R.drawable.bg_campo_error);
        labelCorreo.setTextColor(ContextCompat.getColor(this, R.color.encuadre_texto_principal));
        labelCorreo.setTypeface(null, Typeface.NORMAL);
        ivEstadoCorreo.setImageResource(R.drawable.ic_error_field);
        ivEstadoCorreo.setImageTintList(null);

        campoContrasena.setBackgroundResource(R.drawable.bg_campo_error);
        labelContrasena.setTextColor(ContextCompat.getColor(this, R.color.encuadre_texto_principal));
        labelContrasena.setTypeface(null, Typeface.NORMAL);

        tvErrorContrasena.setText(mensaje);
        tvErrorContrasena.setVisibility(View.VISIBLE);
    }

    private void limpiarErrores() {
        if (!enError) {
            return;
        }
        enError = false;
        tvErrorContrasena.setVisibility(View.GONE);
        ivEstadoCorreo.setImageResource(R.drawable.ic_clear_field);
        ivEstadoCorreo.setImageTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.encuadre_texto_secundario)));
        aplicarEstadoCampo(campoCorreo, labelCorreo, ESTADO_NORMAL);
        aplicarEstadoCampo(campoContrasena, labelContrasena, ESTADO_NORMAL);
    }

    private void navegarAInicio() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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
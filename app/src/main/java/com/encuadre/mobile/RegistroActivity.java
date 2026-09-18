package com.encuadre.mobile;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla Registro — Flujo 1 (Login/Registro).
 * Prototipo no funcional: valida campos localmente, no crea cuenta real.
 */
public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etCorreo;
    private EditText etContrasena;
    private EditText etConfirmarContrasena;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.et_nombre);
        etCorreo = findViewById(R.id.et_correo);
        etContrasena = findViewById(R.id.et_contrasena);
        etConfirmarContrasena = findViewById(R.id.et_confirmar_contrasena);
        tvError = findViewById(R.id.tv_error);

        Button btnCrearCuenta = findViewById(R.id.btn_crear_cuenta);
        TextView tvYaTengoCuenta = findViewById(R.id.tv_ya_tengo_cuenta);

        btnCrearCuenta.setOnClickListener(v -> intentarCrearCuenta());
        // Vuelve a Login (ya está en el back stack, así que basta con cerrar esta pantalla)
        tvYaTengoCuenta.setOnClickListener(v -> finish());
    }

    private void intentarCrearCuenta() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String confirmar = etConfirmarContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo)
                || TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(confirmar)) {
            mostrarError(getString(R.string.error_registro_incompleto));
            return;
        }

        if (!contrasena.equals(confirmar)) {
            mostrarError(getString(R.string.error_contrasenas_no_coinciden));
            return;
        }

        // TODO: reemplazar por la creación real de cuenta cuando exista backend.
        // Por ahora, cuenta "creada" localmente y se vuelve a Login para iniciar sesión.
        finish();
    }

    private void mostrarError(String mensaje) {
        tvError.setText(mensaje);
        tvError.setVisibility(View.VISIBLE);
    }
}

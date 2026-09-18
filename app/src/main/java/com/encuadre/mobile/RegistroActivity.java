package com.encuadre.mobile;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Pantalla Registro - Flujo 1 (Login/Registro).
 * Mismo patron que Login: usa el error nativo de TextInputLayout.setError().
 * NOTA / diferencia conocida con Figma: Material tambien pinta de rojo la
 * etiqueta flotante durante el error (ver nota igual en LoginActivity).
 *
 * Prototipo no funcional: valida campos localmente, no crea cuenta real.
 */
public class RegistroActivity extends AppCompatActivity {

    private TextInputLayout tilNombre;
    private TextInputLayout tilCorreo;
    private TextInputLayout tilContrasena;
    private TextInputLayout tilConfirmarContrasena;
    private TextInputEditText etNombre;
    private TextInputEditText etCorreo;
    private TextInputEditText etContrasena;
    private TextInputEditText etConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        tilNombre = findViewById(R.id.til_nombre);
        tilCorreo = findViewById(R.id.til_correo);
        tilContrasena = findViewById(R.id.til_contrasena);
        tilConfirmarContrasena = findViewById(R.id.til_confirmar_contrasena);
        etNombre = findViewById(R.id.et_nombre);
        etCorreo = findViewById(R.id.et_correo);
        etContrasena = findViewById(R.id.et_contrasena);
        etConfirmarContrasena = findViewById(R.id.et_confirmar_contrasena);

        Button btnCrearCuenta = findViewById(R.id.btn_crear_cuenta);
        TextView tvYaTengoCuenta = findViewById(R.id.tv_ya_tengo_cuenta);

        btnCrearCuenta.setOnClickListener(v -> intentarCrearCuenta());
        // Vuelve a Login (ya esta en el back stack, asi que basta con cerrar esta pantalla)
        tvYaTengoCuenta.setOnClickListener(v -> finish());

        // Normal = Texto Secundario, Enfocado = Texto Principal (mismo patron que Login)
        aplicarColorSegunFoco(tilNombre);
        aplicarColorSegunFoco(tilCorreo);
        aplicarColorSegunFoco(tilContrasena);
        aplicarColorSegunFoco(tilConfirmarContrasena);
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

    private void intentarCrearCuenta() {
        limpiarErrores();

        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String confirmar = etConfirmarContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo)
                || TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(confirmar)) {
            tilNombre.setError(TextUtils.isEmpty(nombre) ? " " : null);
            tilCorreo.setError(TextUtils.isEmpty(correo) ? " " : null);
            tilContrasena.setError(TextUtils.isEmpty(contrasena) ? " " : null);
            tilConfirmarContrasena.setError(
                    TextUtils.isEmpty(confirmar) ? getString(R.string.error_registro_incompleto) : null);
            return;
        }

        if (!contrasena.equals(confirmar)) {
            tilContrasena.setError(" ");
            tilConfirmarContrasena.setError(getString(R.string.error_contrasenas_no_coinciden));
            return;
        }

        // Por ahora, cuenta "creada" localmente y se vuelve a Login para iniciar sesion.
        finish();
    }

    private void limpiarErrores() {
        tilNombre.setError(null);
        tilNombre.setErrorEnabled(false);
        tilCorreo.setError(null);
        tilCorreo.setErrorEnabled(false);
        tilContrasena.setError(null);
        tilContrasena.setErrorEnabled(false);
        tilConfirmarContrasena.setError(null);
        tilConfirmarContrasena.setErrorEnabled(false);
    }
}
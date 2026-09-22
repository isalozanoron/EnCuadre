package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class AlarmaSalidaActivity extends BasePantallaActivity {
  private PeliculaDemo pelicula;
  private boolean confirmada;

  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_alarma_salida, NavBarHelper.Tab.MIS_FUNCIONES);
    pelicula = PeliculaDemo.obtener(getIntent().getStringExtra("pelicula"));
    EditText campo = findViewById(R.id.direccion);
    campo.setText(demo().getString("direccion_" + pelicula.id, ""));
    ((TextView) findViewById(R.id.trayecto))
        .setText(
            "Sales a las "
                + pelicula.salida
                + "\n("
                + (pelicula.id.equals("obsession") ? "50" : "45")
                + " min de trayecto con tráfico)");
    confirmada = s != null && s.getBoolean("confirmada");
    render();
    campo.addTextChangedListener(
        new android.text.TextWatcher() {
          public void beforeTextChanged(CharSequence t, int a, int c, int f) {}

          public void onTextChanged(CharSequence t, int a, int b, int c) {
            confirmada = false;
            render();
          }

          public void afterTextChanged(android.text.Editable e) {}
        });
    findViewById(R.id.confirmar_alarma)
        .setOnClickListener(
            v -> {
              if (confirmada) {
                Intent intent =
                    new Intent(this, MiFuncionActivity.class)
                        .putExtra("pelicula", pelicula.id)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
              }
              String direccion = campo.getText().toString().trim();
              if (direccion.isEmpty()) {
                campo.setError("Escribe tu dirección de origen");
                campo.requestFocus();
                return;
              }
              campo.setError(null);
              demo()
                  .edit()
                  .putString("direccion_" + pelicula.id, direccion)
                  .putBoolean("alarma_" + pelicula.id, true)
                  .apply();
              confirmada = true;
              ((android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                  .hideSoftInputFromWindow(campo.getWindowToken(), 0);
              campo.clearFocus();
              render();
            });
  }

  private void render() {
    ((TextView) findViewById(R.id.confirmar_alarma))
        .setText(confirmada ? "Ver mi función" : "Confirmar alarma");
    View boton = findViewById(R.id.confirmar_alarma);
    android.view.ViewGroup.LayoutParams medidas = boton.getLayoutParams();
    medidas.width =
        Math.round((confirmada ? 155 : 177) * getResources().getDisplayMetrics().density);
    boton.setLayoutParams(medidas);
    findViewById(R.id.alarma_confirmada).setVisibility(confirmada ? View.VISIBLE : View.GONE);
  }

  @Override
  protected void onRestoreInstanceState(Bundle state) {
    super.onRestoreInstanceState(state);
    confirmada = state.getBoolean("confirmada");
    render();
  }

  @Override
  protected void onSaveInstanceState(Bundle s) {
    super.onSaveInstanceState(s);
    s.putBoolean("confirmada", confirmada);
  }
}

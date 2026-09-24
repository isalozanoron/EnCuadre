package com.encuadre.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MiFuncionActivity extends BasePantallaActivity {
  private PeliculaDemo pelicula;

  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_mi_funcion, NavBarHelper.Tab.MIS_FUNCIONES);
    pelicula = PeliculaDemo.obtener(getIntent().getStringExtra("pelicula"));
    ((ImageView) findViewById(R.id.detalle_poster)).setImageResource(pelicula.poster);
    ((ImageView) findViewById(R.id.detalle_poster)).setContentDescription(pelicula.nombre);
    ((TextView) findViewById(R.id.detalle_nombre)).setText(pelicula.nombre);
    ((TextView) findViewById(R.id.detalle_descripcion)).setText(pelicula.detalle);
    findViewById(R.id.configurar_alarma)
        .setOnClickListener(v -> funcion(pelicula.id, AlarmaSalidaActivity.class));
    findViewById(R.id.hora_alarma)
        .setOnClickListener(v -> funcion(pelicula.id, AlarmaSalidaActivity.class));
  }

  @Override
  protected void onResume() {
    super.onResume();
    boolean configurada = demo().getBoolean("alarma_" + pelicula.id, false);
    findViewById(R.id.configurar_alarma).setVisibility(configurada ? View.GONE : View.VISIBLE);
    findViewById(R.id.alarma_activa).setVisibility(configurada ? View.VISIBLE : View.GONE);
    ((TextView) findViewById(R.id.hora_alarma)).setText(pelicula.salida);
    View sw = findViewById(R.id.alarma_switch);
    renderSwitch();
    sw.setOnClickListener(v -> {
      demo().edit().putBoolean("alarma_" + pelicula.id, false).apply();
      renderSwitch();
      funcion(pelicula.id, AlarmaSalidaActivity.class);
    });
    androidx.constraintlayout.widget.ConstraintLayout.LayoutParams divider = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) findViewById(R.id.divider_460).getLayoutParams();
    divider.topMargin = Math.round((configurada ? 460 : 439) * getResources().getDisplayMetrics().density);
    findViewById(R.id.divider_460).setLayoutParams(divider);
  }

  private void renderSwitch() {
    boolean on = demo().getBoolean("alarma_" + pelicula.id, false);
    View sw = findViewById(R.id.alarma_switch);
    sw.setBackgroundResource(on ? R.drawable.bg_switch_track_on : R.drawable.bg_switch_track_off);
    sw.setContentDescription(on ? "Desactivar alarma de salida" : "Activar alarma de salida");
    View thumb = findViewById(R.id.alarma_thumb);
    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) thumb.getLayoutParams();
    params.gravity = android.view.Gravity.CENTER_VERTICAL | (on ? android.view.Gravity.END : android.view.Gravity.START);
    thumb.setLayoutParams(params);
  }
}

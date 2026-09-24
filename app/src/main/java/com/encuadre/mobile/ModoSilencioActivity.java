package com.encuadre.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ModoSilencioActivity extends BasePantallaActivity {
  private boolean activo;

  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_modo_silencio, NavBarHelper.Tab.AJUSTES);
    activo = demo().getBoolean("modo_silencio", true);
    render();
    findViewById(R.id.silencio_switch)
        .setOnClickListener(
            v -> {
              activo = !activo;
              demo().edit().putBoolean("modo_silencio", activo).apply();
              render();
            });
  }

  private void render() {
    FrameLayout pista = findViewById(R.id.silencio_switch);
    pista.setBackgroundResource(
        activo ? R.drawable.bg_switch_track_on : R.drawable.bg_switch_track_off);
    pista.setContentDescription(
        "Modo silencio automático, " + (activo ? "activado" : "desactivado"));
    View thumb = findViewById(R.id.silencio_thumb);
    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) thumb.getLayoutParams();
    lp.gravity =
        android.view.Gravity.CENTER_VERTICAL
            | (activo ? android.view.Gravity.END : android.view.Gravity.START);
    thumb.setLayoutParams(lp);
    TextView estado = findViewById(R.id.silencio_estado);
    estado.setVisibility(activo ? View.VISIBLE : View.INVISIBLE);
  }
}

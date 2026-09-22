package com.encuadre.mobile;

import android.os.Bundle;
import android.widget.*;

public class RecomendacionActivity extends BasePantallaActivity {
  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_recomendacion, NavBarHelper.Tab.INICIO);
    findViewById(R.id.me_interesa)
        .setOnClickListener(
            v -> {
              demo().edit().putBoolean("recomendacion_guardada", true).apply();
              mensaje("Recomendación guardada");
            });
    findViewById(R.id.no_interesa)
        .setOnClickListener(
            v -> {
              demo().edit().putBoolean("recomendacion_guardada", false).apply();
              mensaje("Preferencias actualizadas");
            });
  }
}

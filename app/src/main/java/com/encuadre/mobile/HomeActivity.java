package com.encuadre.mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class HomeActivity extends BasePantallaActivity {
  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    boolean vacio =
        getIntent().getBooleanExtra("sin_funciones", demo().getBoolean("sin_funciones", false));
    pantalla(
        vacio ? R.layout.activity_inicio_vacio : R.layout.activity_inicio, NavBarHelper.Tab.INICIO);
    findViewById(R.id.campana).setOnClickListener(v -> abrir(BandejaNotificacionesActivity.class));
    View proxima = findViewById(R.id.abrir_funcion);
    if (proxima != null)
      proxima.setOnClickListener(v -> funcion("odyssey", MiFuncionActivity.class));
    for (int id : new int[] {R.id.reco_dragon, R.id.reco_interstellar, R.id.reco_up})
      findViewById(id).setOnClickListener(v -> abrir(RecomendacionActivity.class));
  }
}

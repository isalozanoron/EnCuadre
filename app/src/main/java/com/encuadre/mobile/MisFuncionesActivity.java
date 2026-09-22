package com.encuadre.mobile;

import android.os.Bundle;
import android.widget.*;

public class MisFuncionesActivity extends BasePantallaActivity {
  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    boolean vacio =
        getIntent().getBooleanExtra("sin_funciones", demo().getBoolean("sin_funciones", false));
    pantalla(
        vacio ? R.layout.activity_mis_funciones_vacia : R.layout.activity_mis_funciones,
        NavBarHelper.Tab.MIS_FUNCIONES);
    if (vacio) {
      findViewById(R.id.elegir_web).setOnClickListener(v -> comprarWeb());
      return;
    }
    findViewById(R.id.funcion_odyssey)
        .setOnClickListener(v -> funcion("odyssey", MiFuncionActivity.class));
    findViewById(R.id.funcion_obsession)
        .setOnClickListener(v -> funcion("obsession", MiFuncionActivity.class));
    findViewById(R.id.funcion_robot)
        .setOnClickListener(v -> funcion("robot", MiFuncionActivity.class));
  }
}

package com.encuadre.mobile;

import android.os.Bundle;
import android.widget.*;

public class HorariosActivity extends BasePantallaActivity {
  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_horarios, NavBarHelper.Tab.INICIO);
    for (int id :
        new int[] {
          R.id.horario_125_0,
          R.id.horario_125_1,
          R.id.horario_125_2,
          R.id.horario_341_0,
          R.id.horario_341_1,
          R.id.horario_473_0,
          R.id.horario_473_1,
          R.id.horario_473_2
        }) findViewById(id).setOnClickListener(v -> comprarWeb());
  }
}

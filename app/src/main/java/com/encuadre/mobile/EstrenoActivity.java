package com.encuadre.mobile;

import android.os.Bundle;
import android.widget.*;

public class EstrenoActivity extends BasePantallaActivity {
  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_estreno, NavBarHelper.Tab.INICIO);
    findViewById(R.id.ver_horarios).setOnClickListener(v -> abrir(HorariosActivity.class));
  }
}

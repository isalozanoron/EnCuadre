package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

public class AjustesActivity extends BasePantallaActivity {
  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    pantalla(R.layout.activity_ajustes, NavBarHelper.Tab.AJUSTES);
    for (int id :
        new int[] {
          R.id.opcion_notificaciones, R.id.ayuda_notificaciones, R.id.abrir_notificaciones
        }) findViewById(id).setOnClickListener(v -> abrir(NotificacionesActivity.class));
    for (int id : new int[] {R.id.opcion_silencio, R.id.ayuda_silencio, R.id.abrir_silencio})
      findViewById(id).setOnClickListener(v -> abrir(ModoSilencioActivity.class));
    findViewById(R.id.cerrar_sesion)
        .setOnClickListener(
            v -> {
              startActivity(
                  new Intent(this, LoginActivity.class)
                      .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
              finish();
            });
  }
}

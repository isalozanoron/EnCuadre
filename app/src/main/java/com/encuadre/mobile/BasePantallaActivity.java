package com.encuadre.mobile;

import android.content.Intent;
import android.view.View;
import android.widget.*;

public class BasePantallaActivity extends androidx.appcompat.app.AppCompatActivity {

  protected android.content.SharedPreferences demo() {
    return getSharedPreferences("encuadre_demo", MODE_PRIVATE);
  }

  protected void pantalla(int layout, NavBarHelper.Tab tab) {
    setContentView(layout);
    View back = findViewById(R.id.btn_volver);
    if (back != null)
      back.setOnClickListener(
              v -> {
                if (isTaskRoot()) {
                  abrir(HomeActivity.class);
                  finish();
                } else getOnBackPressedDispatcher().onBackPressed();
              });
    int[] carouselIds = {R.id.reco_dragon, R.id.reco_interstellar, R.id.reco_up};
    for (int id : carouselIds) {
      android.view.ViewGroup card = findViewById(id);
      if (card != null)
        ((ImageView) card.getChildAt(0))
                .setColorFilter(
                        getColor(R.color.encuadre_superficie), android.graphics.PorterDuff.Mode.MULTIPLY);
    }
    View nav = findViewById(R.id.nav_bar_bottom);
    if (nav != null) {
      NavBarHelper.marcarSeleccionado(this, nav, tab);
      NavBarHelper.conectar(this, nav, tab);
      ((ImageView) nav.findViewById(R.id.nav_icon_inicio))
              .setImageResource(
                      tab == NavBarHelper.Tab.INICIO
                              ? R.drawable.figma_icon_home
                              : R.drawable.figma_icon_home_inactive);
      int selectedIcon;
      switch (tab) {
        case INICIO:
          selectedIcon = R.id.nav_icon_inicio;
          break;
        case MIS_FUNCIONES:
          selectedIcon = R.id.nav_icon_mis_funciones;
          break;
        case PERFIL:
          selectedIcon = R.id.nav_icon_perfil;
          break;
        default:
          selectedIcon = R.id.nav_icon_ajustes;
      }
      ((ImageView) nav.findViewById(selectedIcon))
              .setColorFilter(getColor(R.color.encuadre_superficie));
      if (tab == NavBarHelper.Tab.INICIO) {
        android.view.ViewGroup item = nav.findViewById(R.id.nav_item_inicio);
        for (int i = 0; i < item.getChildCount(); i++)
          if (item.getChildAt(i) instanceof TextView) {
            TextView label = (TextView) item.getChildAt(i);
            label.setTypeface(
                    androidx.core.content.res.ResourcesCompat.getFont(this, R.font.inter_regular));
            label.setFontVariationSettings("'wght' 400");
          }
      }
    }
  }

  protected void abrir(Class<?> destino) {
    startActivity(new Intent(this, destino));
  }

  protected void funcion(String id, Class<?> destino) {
    startActivity(new Intent(this, destino).putExtra("pelicula", id));
  }

  protected void mensaje(String text) {
    View nav = findViewById(R.id.nav_bar_bottom);
    com.google.android.material.snackbar.Snackbar snackbar =
            com.google.android.material.snackbar.Snackbar.make(
                            findViewById(android.R.id.content),
                            text,
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(R.color.encuadre_exito))
                    .setTextColor(getColor(R.color.encuadre_texto_principal));
    if (nav != null) {
      snackbar.setAnchorView(nav);
    }
    snackbar.show();
  }

  protected void comprarWeb() {
    new com.google.android.material.dialog.MaterialAlertDialogBuilder(
            this, R.style.EnCuadreDialogTheme)
            .setTitle("Continúa en la Web")
            .setMessage(
                    "La compra de boletas se realiza en la versión web de EnCuadre. Allí podrás comparar"
                            + " precios y elegir tu función.")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Entendido", (d, w) -> d.dismiss())
            .show();
  }
}
package com.encuadre.mobile;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

/**
 * Ayudante reutilizable para la barra de navegación inferior (nav_bar_bottom.xml),
 * usado por todas las pantallas "hub" (Perfil, Bandeja de notificaciones, y las
 * de Juan: Inicio, Mis Funciones, Ajustes).
 *
 * Uso típico en onCreate() de la Activity:
 * <pre>
 *   View navBar = findViewById(R.id.nav_bar_bottom);
 *   NavBarHelper.marcarSeleccionado(this, navBar, NavBarHelper.Tab.PERFIL);
 * </pre>
 */
public final class NavBarHelper {

    public enum Tab {INICIO, MIS_FUNCIONES, AJUSTES, PERFIL}

    private NavBarHelper() {
    }

    public static void marcarSeleccionado(Context context, View navBar, Tab tabActivo) {
        aplicarEstado(context, navBar, R.id.nav_icon_container_inicio, R.id.nav_icon_inicio, tabActivo == Tab.INICIO);
        aplicarEstado(context, navBar, R.id.nav_icon_container_mis_funciones, R.id.nav_icon_mis_funciones, tabActivo == Tab.MIS_FUNCIONES);
        aplicarEstado(context, navBar, R.id.nav_icon_container_ajustes, R.id.nav_icon_ajustes, tabActivo == Tab.AJUSTES);
        aplicarEstado(context, navBar, R.id.nav_icon_container_perfil, R.id.nav_icon_perfil, tabActivo == Tab.PERFIL);
    }

    public static void conectar(android.app.Activity activity, View nav, Tab active) {
        int[] ids={R.id.nav_item_inicio,R.id.nav_item_mis_funciones,R.id.nav_item_ajustes,R.id.nav_item_perfil};
        Class<?>[] destinos={HomeActivity.class,MisFuncionesActivity.class,AjustesActivity.class,PerfilActivity.class};
        for(int i=0;i<ids.length;i++){final Class<?> destino=destinos[i];nav.findViewById(ids[i]).setOnClickListener(v->{if(activity.getClass()!=destino)activity.startActivity(new android.content.Intent(activity,destino).addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP|android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP));});}
    }

    private static void aplicarEstado(Context context, View navBar, int idContenedor, int idIcono, boolean seleccionado) {
        FrameLayout contenedor = navBar.findViewById(idContenedor);
        ImageView icono = navBar.findViewById(idIcono);
        if (seleccionado) {
            contenedor.setBackgroundResource(R.drawable.bg_nav_item_selected);
            icono.setColorFilter(ContextCompat.getColor(context, R.color.encuadre_fondo));
        } else {
            contenedor.setBackground(null);
            icono.setColorFilter(ContextCompat.getColor(context, R.color.encuadre_texto_secundario));
        }
    }
}
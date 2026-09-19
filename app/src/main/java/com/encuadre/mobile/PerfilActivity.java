package com.encuadre.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Pantalla Perfil.
 * Prototipo no funcional: los chips de género se pueden tocar para
 * seleccionar/quitar (interacción visual local), sin persistir nada real.
 * La barra de navegación inferior es compartida (nav_bar_bottom.xml + NavBarHelper).
 */
public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Estado inicial igual al mockup: Ciencia ficción, Drama y Suspenso ya seleccionados
        TextView chipThriller = findViewById(R.id.chip_thriller);
        TextView chipCienciaFiccion = findViewById(R.id.chip_ciencia_ficcion);
        TextView chipDrama = findViewById(R.id.chip_drama);
        TextView chipComedia = findViewById(R.id.chip_comedia);
        TextView chipSuspenso = findViewById(R.id.chip_suspenso);

        configurarChip(chipThriller, false);
        configurarChip(chipCienciaFiccion, true);
        configurarChip(chipDrama, true);
        configurarChip(chipComedia, false);
        configurarChip(chipSuspenso, true);

        View navBar = findViewById(R.id.nav_bar_bottom);
        NavBarHelper.marcarSeleccionado(this, navBar, NavBarHelper.Tab.PERFIL);

        // "Inicio", "Mis Funciones" y "Ajustes" son pantallas de Juan; mientras
        // el las sube al repo, se navega a un placeholder para poder probar
        // el flujo completo. Cuando existan, reemplazar por sus Activities reales.
        navBar.findViewById(R.id.nav_item_inicio).setOnClickListener(v ->
                irAPlaceholder("Inicio (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_mis_funciones).setOnClickListener(v ->
                irAPlaceholder("Mis Funciones (pantalla de Juan)"));
        navBar.findViewById(R.id.nav_item_ajustes).setOnClickListener(v ->
                irAPlaceholder("Ajustes (pantalla de Juan)"));
        // "Perfil" ya es esta misma pantalla, no navega a ningún lado.
    }

    /** Toggle simple: clic alterna entre chip seleccionado (relleno acento) y normal (contorno). */
    private void configurarChip(TextView chip, boolean seleccionadoInicial) {
        aplicarEstadoChip(chip, seleccionadoInicial);
        chip.setTag(seleccionadoInicial);
        chip.setOnClickListener(v -> {
            boolean estadoActual = (boolean) v.getTag();
            boolean nuevoEstado = !estadoActual;
            aplicarEstadoChip(chip, nuevoEstado);
            v.setTag(nuevoEstado);
        });
    }

    private void aplicarEstadoChip(TextView chip, boolean seleccionado) {
        if (seleccionado) {
            chip.setBackgroundResource(R.drawable.bg_chip_selected);
            chip.setTextColor(ContextCompat.getColor(this, R.color.encuadre_fondo));
        } else {
            chip.setBackgroundResource(R.drawable.bg_chip_normal);
            chip.setTextColor(ContextCompat.getColor(this, R.color.encuadre_texto_secundario));
        }
    }

    private void irAPlaceholder(String nombrePantalla) {
        Intent intent = new Intent(PerfilActivity.this, PlaceholderActivity.class);
        intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, nombrePantalla);
        startActivity(intent);
    }
}

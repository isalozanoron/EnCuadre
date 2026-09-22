import { EstadoDemoService } from '../estado-demo.service';
import { Component, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';

/**
 * Barra lateral izquierda, compartida por TODAS las pantallas de Web
 * (igual que nav_bar_bottom.xml en Mobile). Recibe cuál destino está
 * activo para pintarlo con el fondo coral (Material 3 Nav item,
 * personalizado con nuestros colores).
 */
export type DestinoSidebar = 'inicio' | 'perfil' | 'diario' | 'donde-verla' | 'precios';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule, MatListModule, MatIconModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  estado = inject(EstadoDemoService);
  @Input() activo: DestinoSidebar = 'inicio';

  destinos: { id: DestinoSidebar; etiqueta: string; ruta: string }[] = [
    { id: 'inicio', etiqueta: 'Inicio', ruta: '/inicio' },
    { id: 'perfil', etiqueta: 'Mi perfil', ruta: '/mi-perfil' },
    { id: 'diario', etiqueta: 'Mi diario', ruta: '/mi-diario' },
    { id: 'donde-verla', etiqueta: 'Dónde verla', ruta: '/donde-verla' },
    { id: 'precios', etiqueta: 'Precios y horarios', ruta: '/buscar-funciones' }
  ];
}

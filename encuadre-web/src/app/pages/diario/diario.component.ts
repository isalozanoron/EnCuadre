import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

interface EntradaDiario {
  titulo: string;
  fecha: string;
  estrellas: string;
  mostrarBoton: boolean;
}

/**
 * Web 20 · Mi diario.
 * Prototipo no funcional: datos fijos, tal como en el mockup.
 * Nota: las imágenes de películas se reemplazaron por bloques placeholder
 * genéricos, no se reproducen los pósters reales por derechos de autor.
 * Fiel al mockup: solo la primera tarjeta (Interstellar) muestra el botón
 * "Ver entrada", las otras 3 no lo tienen en el diseño original.
 */
@Component({
  selector: 'app-diario',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './diario.component.html',
  styleUrl: './diario.component.scss'
})
export class DiarioComponent {
  entradas: EntradaDiario[] = [
    { titulo: 'Interstellar', fecha: 'Vista el 2 sep 2026', estrellas: '★★★★☆', mostrarBoton: true },
    { titulo: 'Cómo entrenar a tu dragón', fecha: 'Vista el 28 ago 2026', estrellas: '★★★★★', mostrarBoton: false },
    { titulo: 'Up', fecha: 'Vista el 20 ago 2026', estrellas: '★★★★☆', mostrarBoton: false },
    { titulo: 'Robot salvaje', fecha: 'Vista el 15 ago 2026', estrellas: '★★★★★', mostrarBoton: false }
  ];
}

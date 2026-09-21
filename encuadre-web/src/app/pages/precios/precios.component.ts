import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

interface FuncionCine {
  nombre: string;
  ubicacion: string;
  horarios: string;
  precio: string;
  horarioElegido: string;
}

/**
 * Web 41 · Comparar precios y horarios.
 * Prototipo no funcional: "Elegir" navega a Revisar compra con datos
 * fijos, sin backend real.
 */
@Component({
  selector: 'app-precios',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './precios.component.html',
  styleUrl: './precios.component.scss'
})
export class PreciosComponent {
  cines: FuncionCine[] = [
    { nombre: 'Cine Colombia', ubicacion: '2,4 km · Sala estándar', horarios: '18:00 · 20:30 · 22:45', precio: '$ 24.000 COP', horarioElegido: '20:30' },
    { nombre: 'Cinemark', ubicacion: '3,8 km · Sala VIP', horarios: '19:15 · 21:00', precio: '$ 28.000 COP', horarioElegido: '19:15' },
    { nombre: 'Cinépolis', ubicacion: '5,1 km · Sala estándar', horarios: '17:30 · 20:15 · 23:00', precio: '$ 22.000 COP', horarioElegido: '17:30' }
  ];

  constructor(private router: Router) {}

  elegirFuncion() {
    this.router.navigate(['/revisar-compra']);
  }
}

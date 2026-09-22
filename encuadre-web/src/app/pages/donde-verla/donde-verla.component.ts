import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

@Component({
  selector: 'app-donde-verla',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './donde-verla.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class DondeVerlaComponent {
  busqueda = 'Cómo entrenar a tu dragón';
  private router = inject(Router);
  peliculas = [
    {
      id: 'dragon',
      nombre: 'Cómo entrenar a tu dragón',
      genero: 'Fantasía · Aventura',
      imagen: '/figma/explore-dragon.png',
      accion: 'Ver plataformas',
    },
    {
      id: 'avengers',
      nombre: 'Avengers: Doomsday',
      genero: 'Acción · Superhéroes',
      imagen: '/figma/explore-avengers.png',
      accion: 'Ver disponibilidad',
    },
    {
      id: 'interstellar',
      nombre: 'Interstellar',
      genero: 'Ciencia ficción · Drama',
      imagen: '/figma/explore-interstellar.png',
      accion: '',
    },
    {
      id: 'up',
      nombre: 'Up',
      genero: 'Animación · Aventura',
      imagen: '/figma/explore-up.png',
      accion: '',
    },
  ];
  buscar() {
    const q = this.busqueda.trim().toLocaleLowerCase();
    if (!q) return;
    const p = this.peliculas.find((p) => p.nombre.toLocaleLowerCase().includes(q));
    this.router.navigate(['/resultado-pelicula', p?.id ?? 'sin-resultados'], {
      queryParams: p ? {} : { q: this.busqueda.trim() },
    });
  }
}

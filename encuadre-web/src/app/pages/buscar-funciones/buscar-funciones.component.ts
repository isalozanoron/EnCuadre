import { Component, inject, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

@Component({
  selector: 'app-buscar-funciones',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './buscar-funciones.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class BuscarFuncionesComponent {
  pelicula = 'Robot salvaje';
  ciudad = 'Bogotá';
  error = signal('');
  private router = inject(Router);
  cines = [
    { nombre: 'Cine Colombia', ubicacion: '2,4 km · Chapinero', precio: '24.000' },
    { nombre: 'Cinemark', ubicacion: '3,8 km · Usaquén', precio: '28.000' },
    { nombre: 'Cinépolis', ubicacion: '5,1 km · Centro', precio: '22.000' },
  ];
  buscar(form: NgForm) {
    if (form.invalid || !this.pelicula.trim() || !this.ciudad.trim()) return;
    if (
      !this.pelicula.toLowerCase().includes('robot') ||
      !['bogotá', 'bogota', 'cine colombia', 'cinemark', 'cinépolis'].includes(
        this.ciudad.trim().toLowerCase(),
      )
    ) {
      this.error.set(
        'No encontramos funciones para esta búsqueda. Prueba con Robot salvaje en Bogotá.',
      );
      return;
    }
    this.router.navigateByUrl('/precios-y-horarios');
  }
}

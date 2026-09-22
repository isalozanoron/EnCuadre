import { Component, inject, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { EstadoDemoService } from '../../shared/estado-demo.service';
@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, MatIconModule, SidebarComponent],
  templateUrl: './editar-perfil.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class EditarPerfilComponent {
  estado = inject(EstadoDemoService);
  private router = inject(Router);
  nombre = this.estado.perfil().nombre;
  edad = this.estado.perfil().edad;
  ciudad = this.estado.perfil().ciudad;
  bio = this.estado.perfil().bio;
  generos = [...this.estado.perfil().generos];
  opciones = ['Drama', 'Ciencia ficción', 'Suspenso', 'Comedia'];
  error = signal(false);
  alternar(g: string) {
    this.generos = this.generos.includes(g)
      ? this.generos.filter((x) => x !== g)
      : [...this.generos, g];
  }
  guardar(form: NgForm) {
    if (form.invalid || !this.nombre.trim() || !this.ciudad.trim()) {
      this.error.set(true);
      return;
    }
    this.estado.perfil.set({
      nombre: this.nombre.trim(),
      edad: this.edad,
      ciudad: this.ciudad.trim(),
      bio: this.bio,
      generos: [...this.generos],
    });
    this.estado.perfilActualizado.set(true);
    this.router.navigateByUrl('/mi-perfil');
  }
}

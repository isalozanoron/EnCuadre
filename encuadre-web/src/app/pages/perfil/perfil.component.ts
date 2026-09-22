import { EstadoDemoService } from '../../shared/estado-demo.service';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

/**
 * Web 11 · Mi perfil.
 * Prototipo no funcional: los datos (nombre, bio, estadísticas) son
 * fijos, tal como en el mockup, sin conexión a backend real.
 */
@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, MatIconModule, SidebarComponent],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.scss'
})
export class PerfilComponent {
  estado = inject(EstadoDemoService);
  get generosFavoritos() { return this.estado.perfil().generos; }
}

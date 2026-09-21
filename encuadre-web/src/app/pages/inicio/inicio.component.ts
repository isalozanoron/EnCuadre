import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

/**
 * Web 10 · Inicio (dashboard).
 * Prototipo no funcional: los botones navegan o no hacen nada todavía,
 * según si la pantalla destino ya existe.
 * Nota: las imágenes de películas (Interstellar, Robot salvaje) se
 * dejaron como bloques placeholder genéricos, no se reproduce el
 * póster real por derechos de autor.
 */
@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.scss'
})
export class InicioComponent {}

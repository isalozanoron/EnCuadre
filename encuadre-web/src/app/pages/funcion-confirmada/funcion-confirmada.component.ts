import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

/**
 * Web 43 · Función confirmada. Última pantalla de Isabella en Web.
 * Prototipo no funcional: datos fijos, sin backend real.
 * Nota: la imagen de la película se dejó como bloque genérico, no se
 * reproduce el póster real por derechos de autor.
 */
@Component({
  selector: 'app-funcion-confirmada',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './funcion-confirmada.component.html',
  styleUrl: './funcion-confirmada.component.scss'
})
export class FuncionConfirmadaComponent {}

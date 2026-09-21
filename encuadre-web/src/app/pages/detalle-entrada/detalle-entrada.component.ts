import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { CalificarDialogComponent } from '../../shared/calificar-dialog/calificar-dialog.component';

/**
 * Web 21/22/23 · Detalle de entrada, agrupados en una sola pantalla de
 * código (mismo criterio que en Mobile): vista normal, diálogo de
 * calificar (Web 22) y mensaje de "Calificación guardada" (Web 23).
 * Nota: la imagen de la película se dejó como bloque genérico, no se
 * reproduce el póster real por derechos de autor.
 *
 * Usa signals (no propiedades normales) para que la pantalla se
 * actualice de inmediato al cerrar el diálogo, sin depender de si el
 * proyecto corre con zonas o sin zonas.
 */
@Component({
  selector: 'app-detalle-entrada',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, MatDialogModule, SidebarComponent],
  templateUrl: './detalle-entrada.component.html',
  styleUrl: './detalle-entrada.component.scss'
})
export class DetalleEntradaComponent {
  calificacion = signal(4);
  calificacionGuardada = signal(false);

  constructor(private dialog: MatDialog) {}

  estrellas(): string {
    return '★'.repeat(this.calificacion()) + '☆'.repeat(5 - this.calificacion());
  }

  abrirDialogoCalificar() {
    const dialogRef = this.dialog.open(CalificarDialogComponent, {
      data: { pelicula: 'Interstellar', calificacionActual: this.calificacion() }
    });

    dialogRef.afterClosed().subscribe((nuevaCalificacion: number | undefined) => {
      if (nuevaCalificacion !== undefined) {
        this.calificacion.set(nuevaCalificacion);
        this.calificacionGuardada.set(true);
      }
    });
  }
}
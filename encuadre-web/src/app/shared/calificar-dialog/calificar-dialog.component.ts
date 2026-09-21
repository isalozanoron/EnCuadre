import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';

/**
 * Diálogo "Calificar película" (Web 22). Se abre desde Detalle de entrada
 * al tocar "Cambiar calificación". Prototipo no funcional: solo cambia
 * el número en memoria, sin persistir nada real.
 */
@Component({
  selector: 'app-calificar-dialog',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatDialogModule],
  templateUrl: './calificar-dialog.component.html',
  styleUrl: './calificar-dialog.component.scss'
})
export class CalificarDialogComponent {
  calificacionSeleccionada: number;
  estrellas = [1, 2, 3, 4, 5];

  constructor(
    public dialogRef: MatDialogRef<CalificarDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { pelicula: string; calificacionActual: number }
  ) {
    this.calificacionSeleccionada = data.calificacionActual;
  }

  elegir(numero: number) {
    this.calificacionSeleccionada = numero;
  }

  guardar() {
    this.dialogRef.close(this.calificacionSeleccionada);
  }

  cancelar() {
    this.dialogRef.close();
  }
}

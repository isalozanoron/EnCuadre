import { Injectable, signal } from '@angular/core';

/**
 * Guarda el estado de la calificación de Interstellar en un solo lugar,
 * compartido entre Mi diario y Detalle de entrada. Antes cada pantalla
 * tenía su propia copia y no se actualizaban entre sí al cambiar la
 * calificación desde el diálogo "Calificar película".
 */
@Injectable({ providedIn: 'root' })
export class InterstellarEstadoService {
  calificacion = signal(4);
}

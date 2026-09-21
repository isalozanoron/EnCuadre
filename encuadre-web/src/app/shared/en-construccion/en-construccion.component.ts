import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

/**
 * Equivalente web de PlaceholderActivity en Mobile: para rutas que
 * todavía no existen (propias o de Juan), en vez de una pantalla en blanco.
 */
@Component({
  selector: 'app-en-construccion',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="en-construccion">
      <h2>Pantalla en construcción</h2>
      <p>{{ nombre }}</p>
    </div>
  `,
  styles: [`
    .en-construccion {
      min-height: 100vh;
      background-color: var(--encuadre-fondo);
      color: var(--encuadre-texto-principal);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      gap: 8px;
      font-family: 'Inter', sans-serif;
      text-align: center;
    }
    p { color: var(--encuadre-texto-secundario); }
  `]
})
export class EnConstruccionComponent {
  @Input() nombre = 'Pantalla sin nombre';
}

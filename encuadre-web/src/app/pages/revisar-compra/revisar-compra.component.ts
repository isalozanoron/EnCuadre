import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';

/**
 * Web 42 · Revisar compra.
 * Prototipo no funcional: "Pagar" navega a Función confirmada con datos
 * fijos, no procesa ningún pago real.
 * Los campos de correo y medio de pago son inputs propios (no
 * mat-form-field/mat-input), mismo criterio que el switch de Mobile:
 * Material no respetaba bien el tamaño de fuente ni los colores.
 */
@Component({
  selector: 'app-revisar-compra',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, FormsModule, SidebarComponent],
  templateUrl: './revisar-compra.component.html',
  styleUrl: './revisar-compra.component.scss'
})
export class RevisarCompraComponent {
  correo = 'isabella@ejemplo.com';
  medioPago = 'Tarjeta terminada en 4242';

  constructor(private router: Router) {}

  pagar() {
    this.router.navigate(['/funcion-confirmada']);
  }
}

import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { AuthShellComponent } from '../../shared/auth-shell/auth-shell.component';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, AuthShellComponent],
  templateUrl: './registro.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class RegistroComponent {
  nombre = '';
  correo = '';
  clave = '';
  confirmacion = '';
  error = signal('');
  private router = inject(Router);
  crear() {
    if (![this.nombre, this.correo, this.clave, this.confirmacion].every((valor) => valor.trim())) {
      this.error.set('Completa todos los campos para continuar.');
      return;
    }
    this.router.navigateByUrl('/inicio');
  }
}

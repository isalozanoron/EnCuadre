import { Component, inject, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
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
  crear(form: NgForm) {
    if (form.invalid || !this.nombre.trim()) {
      this.error.set(
        'Completa tus datos. Usa un correo válido y una contraseña de al menos 8 caracteres.',
      );
      return;
    }
    if (this.clave !== this.confirmacion) {
      this.error.set('Las contraseñas no coinciden.');
      return;
    }
    this.router.navigateByUrl('/inicio');
  }
}

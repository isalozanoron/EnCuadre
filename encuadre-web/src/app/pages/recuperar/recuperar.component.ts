import { Component, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { AuthShellComponent } from '../../shared/auth-shell/auth-shell.component';

@Component({
  selector: 'app-recuperar',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, AuthShellComponent],
  templateUrl: './recuperar.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class RecuperarComponent {
  correo = '';
  enviado = signal(false);
  error = signal(false);
  enviar(form: NgForm) {
    this.error.set(!!form.invalid);
    if (form.valid) this.enviado.set(true);
  }
}

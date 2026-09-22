import { Component, inject, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { AuthShellComponent } from '../../shared/auth-shell/auth-shell.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, AuthShellComponent],
  templateUrl: './login.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class LoginComponent {
  correo = '';
  clave = '';
  error = signal(false);
  private router = inject(Router);
  entrar(form: NgForm) {
    if (form.invalid || this.clave.length < 8) {
      this.error.set(true);
      return;
    }
    this.router.navigateByUrl('/inicio');
  }
}

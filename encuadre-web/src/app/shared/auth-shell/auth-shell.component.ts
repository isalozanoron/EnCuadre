import { Component } from '@angular/core';
@Component({
  selector: 'app-auth-shell',
  standalone: true,
  template: ` <div class="acceso">
    <aside class="presentacion">
      <div class="marca">
        <img src="/logo_encuadre.png" alt="" width="48" height="48" /><strong>EnCuadre</strong>
      </div>
      <div class="posters">
        <img src="/figma/auth-interstellar.png" alt="Interstellar" /><img
          src="/figma/auth-dragon.png"
          alt="Cómo entrenar a tu dragón"
        /><img src="/figma/auth-up.png" alt="Up" />
      </div>
      <h2>Cada película,<br />un recuerdo.</h2>
      <p>Guarda tu historia de cine, descubre dónde ver películas y elige tu próxima función.</p>
      <p class="puente">Tu diario en la web.<br />Tus alarmas en el celular.</p>
    </aside>
    <main class="formulario"><ng-content /></main>
  </div>`,
  styleUrl: './auth-shell.component.scss',
})
export class AuthShellComponent {}

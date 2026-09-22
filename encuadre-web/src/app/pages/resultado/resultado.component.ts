import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { EstadoDemoService } from '../../shared/estado-demo.service';
@Component({
  selector: 'app-resultado',
  standalone: true,
  imports: [FormsModule, RouterModule, MatButtonModule, SidebarComponent],
  templateUrl: './resultado.component.html',
  styleUrl: '../../shared/pantallas.scss',
})
export class ResultadoComponent {
  estado = inject(EstadoDemoService);
  private route = inject(ActivatedRoute);
  id = this.route.snapshot.paramMap.get('id') ?? 'dragon';
  disponible = ['dragon', 'interstellar', 'up'].includes(this.id);
  nombre =
    this.id === 'dragon'
      ? 'Cómo entrenar a tu dragón'
      : this.id === 'avengers'
        ? 'Avengers: Doomsday'
        : this.id === 'interstellar'
          ? 'Interstellar'
          : this.id === 'up'
            ? 'Up'
            : (this.route.snapshot.queryParamMap.get('q') ?? 'Película');
  imagen =
    this.id === 'dragon'
      ? '/figma/platform-dragon.png'
      : this.id === 'avengers'
        ? '/figma/empty-avengers.png'
        : this.id === 'sin-resultados'
          ? ''
          : this.id === 'up'
            ? '/figma/up.png'
            : '/figma/' + this.id + '.png';
  genero =
    this.id === 'dragon'
      ? 'Fantasía · Aventura · Animación'
      : this.id === 'up'
        ? 'Animación · Aventura'
        : 'Ciencia ficción · Drama';
  sinopsis =
    this.id === 'dragon'
      ? 'Hipo desafía la tradición de su aldea al hacerse amigo de Chimuelo. Juntos descubren que vikingos y dragones pueden convivir.'
      : 'Encuentra opciones para disfrutar esta película.';
  plataformas = ['Netflix', 'Prime Video', 'Max'];
}

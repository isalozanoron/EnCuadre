import { Injectable, signal } from '@angular/core';
export interface Perfil {
  nombre: string;
  edad: number;
  ciudad: string;
  bio: string;
  generos: string[];
}
@Injectable({ providedIn: 'root' })
export class EstadoDemoService {
  readonly perfil = signal<Perfil>({
    nombre: 'Isabella Cano',
    edad: 22,
    ciudad: 'Bogotá, Colombia',
    bio: 'Amante del cine de misterio y las historias que se quedan contigo.',
    generos: ['Drama', 'Ciencia ficción', 'Suspenso'],
  });
  readonly aviso = signal(false);
  readonly perfilActualizado = signal(false);
}

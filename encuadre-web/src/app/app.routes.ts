import { Routes } from '@angular/router';
import { InicioComponent } from './pages/inicio/inicio.component';
import { EnConstruccionComponent } from './shared/en-construccion/en-construccion.component';

export const routes: Routes = [
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },

  // Pantallas de Isabella pendientes (Web): reemplazar por su componente real cuando existan
  { path: 'mi-perfil', component: EnConstruccionComponent, data: { nombre: 'Mi perfil (pendiente)' } },
  { path: 'mi-diario', component: EnConstruccionComponent, data: { nombre: 'Mi diario (pendiente)' } },
  { path: 'precios-y-horarios', component: EnConstruccionComponent, data: { nombre: 'Comparar precios y horarios (pendiente)' } },

  // Pantallas de Juan (Web): reemplazar cuando él las suba a la rama
  { path: 'donde-verla', component: EnConstruccionComponent, data: { nombre: 'Dónde verla (pantalla de Juan)' } },

  { path: '**', redirectTo: 'inicio' }
];

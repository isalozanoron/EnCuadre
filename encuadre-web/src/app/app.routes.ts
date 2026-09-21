import { Routes } from '@angular/router';
import { InicioComponent } from './pages/inicio/inicio.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { DiarioComponent } from './pages/diario/diario.component';
import { DetalleEntradaComponent } from './pages/detalle-entrada/detalle-entrada.component';
import { PreciosComponent } from './pages/precios/precios.component';
import { RevisarCompraComponent } from './pages/revisar-compra/revisar-compra.component';
import { EnConstruccionComponent } from './shared/en-construccion/en-construccion.component';

export const routes: Routes = [
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'mi-perfil', component: PerfilComponent },
  { path: 'mi-diario', component: DiarioComponent },
  { path: 'detalle-entrada', component: DetalleEntradaComponent },
  { path: 'precios-y-horarios', component: PreciosComponent },
  { path: 'revisar-compra', component: RevisarCompraComponent },

  // Pantallas de Isabella pendientes (Web): reemplazar por su componente real cuando existan
  { path: 'editar-perfil', component: EnConstruccionComponent, data: { nombre: 'Editar perfil (pendiente)' } },
  { path: 'funcion-confirmada', component: EnConstruccionComponent, data: { nombre: 'Función confirmada (pendiente)' } },

  // Pantallas de Juan (Web): reemplazar cuando él las suba a la rama
  { path: 'donde-verla', component: EnConstruccionComponent, data: { nombre: 'Dónde verla (pantalla de Juan)' } },
  { path: 'buscar-funciones', component: EnConstruccionComponent, data: { nombre: 'Buscar funciones (pantalla de Juan)' } },

  { path: '**', redirectTo: 'inicio' }
];

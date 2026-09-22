import { Routes } from '@angular/router';
import { InicioComponent } from './pages/inicio/inicio.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { DiarioComponent } from './pages/diario/diario.component';
import { DetalleEntradaComponent } from './pages/detalle-entrada/detalle-entrada.component';
import { PreciosComponent } from './pages/precios/precios.component';
import { RevisarCompraComponent } from './pages/revisar-compra/revisar-compra.component';
import { FuncionConfirmadaComponent } from './pages/funcion-confirmada/funcion-confirmada.component';

export const routes: Routes = [
  { path: '', redirectTo: 'iniciar-sesion', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'mi-perfil', component: PerfilComponent },
  { path: 'mi-diario', component: DiarioComponent },
  { path: 'detalle-entrada', component: DetalleEntradaComponent },
  { path: 'precios-y-horarios', component: PreciosComponent },
  { path: 'revisar-compra', component: RevisarCompraComponent },
  { path: 'funcion-confirmada', component: FuncionConfirmadaComponent },

  {
    path: 'iniciar-sesion',
    loadComponent: () => import('./pages/login/login.component').then((m) => m.LoginComponent),
  },
  {
    path: 'crear-cuenta',
    loadComponent: () =>
      import('./pages/registro/registro.component').then((m) => m.RegistroComponent),
  },
  {
    path: 'recuperar-contrasena',
    loadComponent: () =>
      import('./pages/recuperar/recuperar.component').then((m) => m.RecuperarComponent),
  },
  {
    path: 'editar-perfil',
    loadComponent: () =>
      import('./pages/editar-perfil/editar-perfil.component').then((m) => m.EditarPerfilComponent),
  },
  {
    path: 'donde-verla',
    loadComponent: () =>
      import('./pages/donde-verla/donde-verla.component').then((m) => m.DondeVerlaComponent),
  },
  {
    path: 'resultado-pelicula/:id',
    loadComponent: () =>
      import('./pages/resultado/resultado.component').then((m) => m.ResultadoComponent),
  },
  {
    path: 'buscar-funciones',
    loadComponent: () =>
      import('./pages/buscar-funciones/buscar-funciones.component').then(
        (m) => m.BuscarFuncionesComponent,
      ),
  },
  { path: '**', redirectTo: 'inicio' },
];

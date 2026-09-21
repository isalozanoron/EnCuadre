# EnCuadre - Web (rama `EnCuadre-Web`)

Maquetación front-end del proyecto EnCuadre (servicio de alarmas para cinéfilos), implementada en código real a partir de los mockups de Figma. Prototipo no funcional: no hay backend, autenticación ni persistencia real.

## Frameworks y versiones

| Tecnología | Versión |
|---|---|
| Angular | 21.2.0 |
| Angular CLI | 21.2.24 |
| Angular Material | 21.2.14 |
| TypeScript | 5.9.2 |
| Node package manager | npm 11.6.2 |

El proyecto se generó con `ng new encuadre-web --routing --style=scss` y se le agregó Angular Material con `ng add @angular/material`. El UI kit usado (Angular Material, sistema Material 3) es el mismo que se usó como base en los mockups de Figma ("Material 3 Design Kit"), personalizado con la paleta de colores propia de EnCuadre en `src/styles.scss`.

## Instrucciones para correr el proyecto

Requiere tener [Node.js](https://nodejs.org/) instalado (incluye npm).

```bash
# 1. Clonar el repositorio y entrar a la rama Web
git clone https://github.com/isalozanoron/EnCuadre.git
cd EnCuadre
git checkout EnCuadre-Web

# 2. Entrar a la carpeta del proyecto Angular e instalar dependencias
cd encuadre-web
npm install

# 3. Correr el servidor de desarrollo
ng serve
```

Luego abrir `http://localhost:4200` en el navegador.

## Estructura del proyecto

```
encuadre-web/
  src/
    app/
      pages/              → una carpeta por pantalla (inicio, perfil, diario, etc.)
      shared/
        sidebar/          → barra de navegación lateral, reutilizada en todas las pantallas
        en-construccion/  → pantalla de reemplazo para rutas aún no implementadas
        calificar-dialog/ → diálogo de Material para calificar una película
      app.routes.ts       → definición de todas las rutas de la aplicación
    styles.scss           → tokens de diseño (colores, tipografía) compartidos por toda la app
```

## Pantallas implementadas (Isabella - 7 de 7)

Inicio, Mi perfil, Mi diario, Detalle de entrada, Comparar precios y horarios, Revisar compra, Función confirmada.

## Notas
- Varios componentes de Angular Material (botones, `mat-list-item`, campos de texto) requirieron sobrescribir sus estilos con `!important` o reemplazarlos por elementos propios, ya que no respetaban bien los colores/formas exactos del diseño por defecto.

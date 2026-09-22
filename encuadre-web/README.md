# EnCuadre · Web

Maquetación navegable para la entrega de UI/UX de la maestría. Rama `EnCuadre-Web`, compartida por Isabella Lozano y Juan F. Rodríguez.

## Ejecutar

Requiere Node.js **20.19+**, **22.12+** o **24+** y npm. Se validó con Node 24.19.0.

```sh
npm ci
npm start
```

Abrir http://localhost:4200. Evitar carpetas que contengan `:` en su nombre: interfieren con la carga de recursos del servidor de desarrollo.

```sh
npm run build
npm test -- --watch=false
```

El sitio compilado queda en `dist/encuadre-web/browser`. En un servidor web debe configurarse la redirección de rutas de la SPA a `index.html`.

## Versiones

Angular 21.2.23, Angular Material/CDK 21.2.14, Angular CLI 21.2.24, TypeScript 5.9.3 y Vitest 4.1.11. Las versiones reproducibles están en `package-lock.json`.

## Pantallas de Juan

| Ruta | Figma | Estados / navegación |
|---|---|---|
| `/iniciar-sesion` | Web 00, 01, 02 | Vacío, con datos, error; acceso a Inicio |
| `/crear-cuenta` | Web 03 | Campos editables, validación y confirmación de contraseña |
| `/recuperar-contrasena` | Web 04, 05 | Solicitud, correo enviado, usar otro correo |
| `/donde-verla` | Web 30 | Buscar película y explorar tarjetas |
| `/resultado-pelicula/dragon` | Web 31 | Plataformas disponibles |
| `/resultado-pelicula/avengers` | Web 32, 33 | Sin disponibilidad; activar/desactivar aviso |
| `/buscar-funciones` | Web 40 | Búsqueda y conexión con comparación de precios |
| `/editar-perfil` | Web 12 | Editar, seleccionar géneros, guardar o cancelar |

Los frames de resultado y sus variantes constituyen una sola pantalla de código: **7 pantallas de Juan**. Se conservan las 7 pantallas de Isabella y se integran con las nuevas rutas.

Diseño: [EnCuadre, página Web](https://www.figma.com/design/Xg4wjxPwcy2nyZnCnJp0Xz/EnCuadre-Mockups--Copy-?node-id=2-4).

## Probar los recorridos

- Acceso: cualquier correo con formato válido y una contraseña de 8 caracteres permite recorrer la demo. Vacío o contraseña corta muestra el error de Figma. Por ejemplo: `isabella@ejemplo.com` / `encuadre123`.
- Registro: campos requeridos, correo válido, mínimo 8 caracteres y contraseñas iguales.
- Recuperación: solicitar con un correo válido; aparece la confirmación. **No se envían correos reales**.
- Dónde verla: buscar «Cómo entrenar a tu dragón»; volver y abrir «Avengers: Doomsday» para activar y desactivar el aviso.
- Funciones: datos de ejemplo «Robot salvaje» en «Bogotá»; continuar a comparar, revisar y confirmar. Las demás búsquedas muestran un mensaje sin resultados.
- Perfil: modificar nombre y géneros, guardar y comprobarlos en Mi perfil. Cancelar conserva el último perfil guardado.

Todos los datos son de muestra. No hay autenticación, pagos, suscripciones, consultas de disponibilidad ni notificaciones remotas reales. Los cambios de perfil y avisos viven en memoria mientras la aplicación permanece abierta; recargar reinicia la demo.

## Diseño y recursos

Inter, Roboto y los iconos se sirven localmente; sus licencias están en `public/fonts`. `public/figma` contiene exportaciones del diseño (imágenes y avatar), no capturas de pantallas usadas en lugar de componentes. Formularios, botones, tarjetas y navegación son elementos interactivos reales. Se reutilizan los tokens `--encuadre-*`, Angular Material y la barra lateral existente.

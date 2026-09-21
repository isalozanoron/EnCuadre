# EnCuadre - Mobile (rama `EnCuadre-Mobile`)

Maquetación front-end del proyecto EnCuadre (servicio de alarmas para cinéfilos), implementada en código real a partir de los mockups de Figma. Prototipo no funcional: no hay backend, autenticación ni persistencia real.

## Tecnologías y versiones

| Tecnología | Versión |
|---|---|
| Plataforma | Android nativo |
| Lenguaje | Java |
| Vistas | XML (ConstraintLayout) |
| minSdkVersion | 27 (Android 8.1 Oreo) |
| targetSdkVersion | 34 |
| compileSdk | 34 |
| Dependencias principales | AndroidX AppCompat 1.6.1, ConstraintLayout 2.1.4, Material Components 1.11.0 |
| IDE | Android Studio |

El UI kit usado (Material Components para Android, sistema Material 3) es el mismo que se usó como base en los mockups de Figma ("Material 3 Design Kit"), personalizado con la paleta de colores propia de EnCuadre en `res/values/colors.xml`.

## Instrucciones para correr el proyecto

Requiere tener [Android Studio](https://developer.android.com/studio) instalado.

```bash
# 1. Clonar el repositorio y entrar a la rama Mobile
git clone https://github.com/isalozanoron/EnCuadre.git
cd EnCuadre
git checkout EnCuadre-Mobile
```

Luego, en Android Studio: **File → Open**, selecciona la carpeta del repositorio clonado, espera a que sincronice Gradle, y dale **Run** con un emulador o celular físico con Android 8.1 o superior.

## Estructura del proyecto

```
app/src/main/
  java/com/encuadre/mobile/   → una clase Activity por pantalla, + helpers compartidos (NavBarHelper, PushNotificationHelper)
  res/layout/                 → un archivo XML por pantalla
  res/values/colors.xml       → tokens de diseño (paleta de colores EnCuadre)
  res/drawable/                → íconos vectoriales y fondos personalizados (switches, chips, barra de navegación)
```

## Pantallas/componentes implementados (Isabella - 8 de 8)

Splash, Login, Registro, Perfil, Notificaciones (Ajustes), Bandeja de notificaciones, Maratón pendiente, Push Banner (implementado como notificación real del sistema Android, se dispara automáticamente al abrir la app).

## Notas
- El switch de la pantalla de Notificaciones y la barra de navegación inferior se construyeron a mano (no con el widget `Switch` nativo de Android), ya que este no respetaba bien los colores ni el tamaño exacto del diseño.
- La barra de navegación inferior (`nav_bar_bottom.xml` + `NavBarHelper.java`) es un componente reutilizado en las 4 pantallas que la necesitan.

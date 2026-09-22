# EnCuadre - Mobile (rama `EnCuadre-Mobile`)

Maquetación front-end del proyecto EnCuadre (servicio de alarmas para cinéfilos), implementada en código real a partir de los mockups de Figma. Prototipo navegable con datos de muestra. No hay backend ni autenticación real; las alarmas, direcciones y preferencias de la demo se guardan localmente en SharedPreferences.

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

## Pantallas de Juan · 9 de 9

Inicio (con función y vacío), Recomendación, Estreno, Horarios, Mis funciones (con datos y vacío), Mi función (tres películas, con/sin alarma), Alarma de salida (vacía, escrita y confirmada), Ajustes y Modo silencio (activado/desactivado).

Se integran con las pantallas de Isabella a través de la barra inferior, la campana y el cierre de sesión. Las alarmas y el modo silencio son estados de la maqueta: no calculan tráfico ni programan alarmas del sistema. Las compras se realizan únicamente en el flujo Web de muestra.

## Recursos y compilación

Las fuentes Inter y Roboto se incluyen localmente. Sus licencias se encuentran en `INTER-LICENSE.txt` y `ROBOTO-LICENSE.txt`. Los iconos, afiches y colores proceden del diseño de EnCuadre en Figma.

Para compilar desde la terminal se necesita Android SDK 34 y un JDK 17 o superior compatible con Gradle 9.2.1. También se puede usar el JDK integrado de Android Studio. Configurar la ruta del SDK en `local.properties`; este archivo es específico de cada equipo y no se versiona.

```sh
./gradlew assembleDebug
```

El APK se genera en `app/build/outputs/apk/debug/app-debug.apk`.

Evitar carpetas con `:` en la ruta del proyecto, porque Java interpreta ese carácter como separador del classpath.

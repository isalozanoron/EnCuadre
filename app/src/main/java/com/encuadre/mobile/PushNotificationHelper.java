package com.encuadre.mobile;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * Componente "Push Banner" del mockup, implementado como una notificación
 * real del sistema Android (no una vista simulada dentro de la app), para
 * que se vea exactamente como el celular la mostraría de verdad.
 * <p>
 * Requiere permiso de notificaciones en Android 13+ (POST_NOTIFICATIONS),
 * pedido en tiempo de ejecución antes de llamar a mostrar().
 * <p>
 * Prototipo no funcional: se dispara a mano desde MenuPruebasActivity,
 * no hay backend real enviando notificaciones. Tocar la notificación sí
 * navega a la pantalla correspondiente (destino recibido por parámetro).
 */
public final class PushNotificationHelper {

    private static final String CANAL_ID = "encuadre_notificaciones";
    private static int siguienteId = 1;

    private PushNotificationHelper() {
    }

    private static void crearCanalSiHaceFalta(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(
                    CANAL_ID,
                    context.getString(R.string.push_canal_nombre),
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canal);
        }
    }

    /**
     * @param destino     Activity a la que navega si el usuario toca la notificación
     *                    (ej. MaratonPendienteActivity.class, o PlaceholderActivity.class
     *                    si la pantalla real todavía no existe).
     * @param extraNombre Solo se usa si destino es PlaceholderActivity; se ignora en cualquier otro caso.
     */
    public static void mostrar(Context context, String titulo, String texto,
                               Class<?> destino, String extraNombre) {
        if (Build.VERSION.SDK_INT >= 33 && androidx.core.content.ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            return;
        }
        crearCanalSiHaceFalta(context);

        Intent intent = new Intent(context, destino);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (destino == PlaceholderActivity.class) {
            intent.putExtra(PlaceholderActivity.EXTRA_NOMBRE_PANTALLA, extraNombre);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, siguienteId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CANAL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(texto))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat.from(context).notify(siguienteId++, builder.build());
    }
}
package com.dpozzo68.analizarapp.helpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.dpozzo68.analizarapp.MainActivity;
import com.dpozzo68.analizarapp.R;
import com.dpozzo68.analizarapp.entidades.NotificationText;

import java.util.ArrayList;
import java.util.List;

public class NotificationHelper {
    private static final String CHANNEL_ID = "five_minute_channel";
    static final int NOTIFICATION_ID = 1;

    public Notification getNotification(Context context, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the NotificationChannel (required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

//         Build the notification
//        Intent intent = new Intent(context, MainActivity.class); // Replace with your activity
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
//        notificationIntent.setData(Uri.parse("https://www.google.com/"));

        Notification.Builder builder;
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.argentina.gob.ar/economia/energia/eficiencia-energetica/cuidemos-la-energia"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.logo_app) // Replace with your icon resource
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            return builder.build();
        } else {
            builder = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.logo_app)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true); // Replace with your icon resource
            return builder.build();
        }
    }

}

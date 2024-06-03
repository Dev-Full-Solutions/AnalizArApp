package com.dpozzo68.analizarapp.helpers;

//import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.dpozzo68.analizarapp.entidades.NotificationText;
import com.dpozzo68.analizarapp.entidades.NotificationTextList;
import android.util.Log;


import java.io.Serializable;

public class NotificationService {
//    private NotificationTextList notificationTexts;
//    public class NotificationBroadcastReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            NotificationHelper notificationHelper = new NotificationHelper();
//
//            NotificationText text = notificationTexts.getNotificationText();
//            Log.d("broadcast", "This is my log message at the debug level here");
//            Notification notification = notificationHelper.getNotification(context, text.getTitle(), text.getBody());
//
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(NotificationHelper.NOTIFICATION_ID, notification);
//            notificationTexts.advanceIndex();
//        }
//    }
    public NotificationService(Context context) {
        AlarmManager alarmManager;
        PendingIntent pendingIntent;

//        Log.d("MyTagGoesHere", "This is my log message at the debug level here");

//        notificationTexts = new NotificationTextList();
//        notificationTexts.populate();
        Intent intent = new Intent(context, aNotificationBroadcastReceiver.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_MUTABLE
            );
        } else {
            pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_MUTABLE
            );
        }
        // Schedule alarm to schedule notification
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Set the interval - 5 minutes in milliseconds
        long interval = 60 * 1000;

        // Set the repeating alarm with inexact delivery (due to OS limitations)
//        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

//        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE);

//    scheduleNotification();

}

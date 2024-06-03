package com.dpozzo68.analizarapp.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dpozzo68.analizarapp.entidades.NotificationText;
import com.dpozzo68.analizarapp.entidades.NotificationTextList;

public class aNotificationBroadcastReceiver extends BroadcastReceiver {
    static NotificationTextList notificationTexts = new NotificationTextList();
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper();
//        if (notificationTexts.isEmpty()) notificationTexts.populate();

        NotificationText text = notificationTexts.getNotificationText();
        Log.d("broadcast", "This is my log message at the debug level here");
        Notification notification = notificationHelper.getNotification(context, text.getTitle(), text.getBody());

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NotificationHelper.NOTIFICATION_ID, notification);
//        notificationTexts.advanceIndex();
    }
}

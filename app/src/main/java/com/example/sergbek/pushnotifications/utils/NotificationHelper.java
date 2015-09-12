package com.example.sergbek.pushnotifications.utils;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.sergbek.pushnotifications.activity.MainActivity;
import com.example.sergbek.pushnotifications.model.NotificationEntity;
import com.example.sergbek.pushnotifications.R;

public class NotificationHelper {

    public static void sendNotification(Context context,NotificationEntity notificationEntity) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.common_signin_btn_icon_dark)
                .setContentTitle(notificationEntity.getTitle())
                .setContentText(notificationEntity.getMessage())
                .setTicker(notificationEntity.getTickerText())
                .setSubText(notificationEntity.getSubtitle())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{0, 100, 200, 300})
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}

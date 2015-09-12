package com.example.sergbek.pushnotifications.service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sergbek.pushnotifications.model.NotificationEntity;
import com.example.sergbek.pushnotifications.database.Database;
import com.example.sergbek.pushnotifications.utils.NotificationHelper;
import com.google.android.gms.gcm.GcmListenerService;


public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    private Database mDatabase;

    @Override
    public void onMessageReceived(String from, Bundle data) {

        Log.d(TAG, "From: " + from);

        NotificationEntity notificationEntity = getNotificationEntity(data);

        addNotificationInDatabase(notificationEntity);

        NotificationHelper.sendNotification(this, notificationEntity);

    }

    @NonNull
    private NotificationEntity getNotificationEntity(Bundle data) {
        NotificationEntity notificationEntity=new NotificationEntity();

        notificationEntity.setTitle(data.getString("title"));
        notificationEntity.setMessage(data.getString("message"));
        notificationEntity.setSubtitle(data.getString("subtitle"));
        notificationEntity.setTickerText(data.getString("tickerText"));

        return notificationEntity;
    }

    private void addNotificationInDatabase(NotificationEntity notificationEntity){

        mDatabase=new Database(this);
        mDatabase.addNotification(notificationEntity);
    }


}

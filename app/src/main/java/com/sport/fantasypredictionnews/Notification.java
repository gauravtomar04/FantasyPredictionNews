package com.sport.fantasypredictionnews;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Build.VERSION;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notification extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onMessageReceived( RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notificationShow(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            NotificationChannel channel= new NotificationChannel("title", "body",NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }
     @RequiresApi(api = Build.VERSION_CODES.Q)
     void notificationShow(String title, String message){
         NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"notify")
                 .setContentTitle(title)
                 .setSmallIcon(R.drawable.logo)
                 .setAutoCancel(true)
                 .setContentText(message);
         NotificationManagerCompat compat=NotificationManagerCompat.from(this);
         compat.notify(1, builder.build());
     }

}

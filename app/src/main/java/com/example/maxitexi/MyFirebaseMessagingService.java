package com.example.maxitexi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.maxitexi.Service.NotificationReceiver;
import com.example.maxitexi.SharedPrefrences.Preferences;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    Integer notificationId = 0;
    Intent resultIntent;
    TaskStackBuilder stackBuilder;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d(TAG, "Token: " + token);
        Log.e("PUSH_TOKEN ", "" + token);
        //upload the token to your server
        //you have to store in preferences
    }

    @Override
    public void
    onMessageReceived(RemoteMessage remoteMessage) {

        Map<String,String> data = remoteMessage.getData();
        String title = data.get("title");
        String body = data.get("body");

        addNotification(/*remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody()*/title,body);
    }

    private Integer incrementNotificationId() {
        return notificationId++;
    }

    public void addNotification( String title,String body) {

        String CHANNEL_ID = "MaxiTaxi";
        CharSequence name = "MaxiTaxi";
        String Description = "MaxiTaxi";

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri notificationsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setShowBadge(true);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
            }
        }

        Intent intentAction3 = new Intent(this, NotificationReceiver.class);
        intentAction3.putExtra("Action", "notification");
        intentAction3.putExtra("NotificationID",notificationId);

        Preferences.getInstance(this).setString(Preferences.KEY_FIRE_NOTIFICATION,"notification");

        resultIntent = new Intent(this, MainActivity.class);
        stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE);
        }
        else
        {
            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText(body)
                .setContentTitle(title)
                .setSound(notificationsound)
                .setSmallIcon(R.drawable.notification)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.mipmap.applogo))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setColor(getResources().getColor(R.color.purple_200));

        if (notificationManager != null) {

            notificationManager.notify(incrementNotificationId(), builder.build());
            notificationManager.cancel(incrementNotificationId());
        }
    }
}

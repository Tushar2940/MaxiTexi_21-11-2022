package com.example.maxitexi.Service;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.legacy.content.WakefulBroadcastReceiver;

import com.example.maxitexi.MainActivity;
import com.example.maxitexi.MyFirebaseMessagingService;
import com.example.maxitexi.SharedPrefrences.Preferences;

import java.util.Objects;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("NotificationID", 0);
        String action = intent.getStringExtra("Action");
        switch (Objects.requireNonNull(action)) {
            case "notification":
                Preferences.getInstance(context).setString(Preferences.KEY_FIRE_NOTIFICATION,"notification");
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
                break;
        }
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }
}

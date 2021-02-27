package com.farmers.buyers.common.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.DateFormat;
import java.util.Calendar;


public class MyFirebaseMsgService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMsgService.class.getName();
    private String[] title_;
    private String cutString;
    public String currentDateTimeString;
    private AppController appController = AppController.get();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        currentDateTimeString = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        try {
            cutString = remoteMessage.getNotification().getTitle();
            title_ = cutString.split(" -");
        } catch (Exception e) {
            title_[0] = "title";
        }
        if (!title_[0].equalsIgnoreCase("farmer Chat")) {
            //create notification
            CustomNotification(remoteMessage);
            Intent intent = new Intent("notificationReceived");
            // add data
            intent.putExtra("title", remoteMessage.getNotification().getTitle());
            intent.putExtra("body", remoteMessage.getNotification().getBody());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        } else {
            CustomNotification(remoteMessage);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {

    }

    private void createNotificatio(RemoteMessage remoteMessage) {
        //BadgeCount = BadgeCount + 1;
        Intent intent;
        if (appController.getAuthenticationKey() != null) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
//Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BaseApp.getInstance().getPackageName() + "/" + R.raw.hit);
        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_sign_up_logo))
                .setSmallIcon(R.drawable.ic_sign_up_logo)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Farmer"))
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mNotificationBuilder.build());
    }

    private void CustomNotification(RemoteMessage remoteMessage) {

        Intent intent;
        if (appController.getAuthenticationKey() != null) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String CHANNEL_ID = "1234";

        // Uri sound = Uri.parse("android.resource://" + BaseApp.getInstance().getPackageName() + "/" + R.raw.over);

        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        //For API 26+ you need to put some additional code like below:
        NotificationChannel mChannel;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, "CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_HIGH);
            mChannel.setLightColor(Color.BLUE);
            mChannel.enableLights(true);
            mChannel.setDescription("CHANNEL_SIREN_DESCRIPTION");
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            //    mChannel.setSound(sound, audioAttributes);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
            }
        }

        //General code:
        NotificationCompat.Builder status = new NotificationCompat.Builder(getApplicationContext(),
                CHANNEL_ID);
        status.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                //.setOnlyAlertOnce(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Farmer"))
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 500, 1000})
                .setDefaults(Notification.DEFAULT_LIGHTS)
                //   .setSound(sound)
                .setContentIntent(resultIntent);

        mNotificationManager.notify(0, status.build());
    }
}
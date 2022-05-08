package fi.ottooks.dreamcatcherdemo.kello;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

/**
 * The notiChannel class for Dream catcher
 *     This is an application class used to create a notification channel before creating the actual notification in aService class
 * @author Samu
 *
 */
public class notiChannel extends Application {

    public static final String CHANNEL_ID = "ALARM_SERVICE_CHANNEL";

    @Override
    public void onCreate() {

        super.onCreate();
        createNotiChannel();

    }

    private void createNotiChannel() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            final NotificationChannel notiChannel =
            new NotificationChannel(CHANNEL_ID, "Alarm Channel", NotificationManager.IMPORTANCE_DEFAULT);

            final NotificationManager manager =
            getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notiChannel);

        }
    }
}

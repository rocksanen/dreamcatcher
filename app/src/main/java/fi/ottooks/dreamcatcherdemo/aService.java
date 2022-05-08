package fi.ottooks.dreamcatcherdemo;

import static fi.ottooks.dreamcatcherdemo.notiChannel.CHANNEL_ID;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * The aService class for Dream catcher
 *     Service that is used to control the alarm
 */

public class aService extends Service {

    private MediaPlayer mediaPlayer;

    /**
     * Gets called once the service is created, sets up the sound and the vibrating
     *
     */

    @Override
    public void onCreate() {

        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.heratys);
        mediaPlayer.setLooping(true);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    /**
     * Gets called ater onCreate(), once startService() is called
     * Used to create the notification & the sound/vibration of the alarm once it goes off
     * @param intent intent
     * @param flags
     * @param startId
     * @return START_STICKY, means it will try to re-create the service once its killed
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent notiIntent = new Intent(this, clockBreak.class);

        PendingIntent pendingIntent =
        PendingIntent.getActivity(this, 0, notiIntent,PendingIntent.FLAG_IMMUTABLE );

        String alarmTitle = "Aika herätä!";

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.alarmicon)
                .setContentTitle(alarmTitle)
                .setContentText("Herätyyys..")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        createChannel(notificationManager);
        mediaPlayer.start();
        startForeground(notificationId, notificationBuilder.build());

        return START_STICKY;
    }

    /**
     *
     * @param notificationManager notificationManager
     */
    public void createChannel(NotificationManager notificationManager) {

        NotificationChannel channel;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            channel = new NotificationChannel(CHANNEL_ID, "nimi", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("testi noti");
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Not used, just forced to be included in a class that extends service
     * @param intent intent
     * @return nothing, null
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

    /**
     * Gets called once the service is not used anymore and is destroyed
     * used to stop the sound/vibration and to clean up
     */
    @Override
    public void onDestroy() {

        super.onDestroy();
        mediaPlayer.stop();

    }
}

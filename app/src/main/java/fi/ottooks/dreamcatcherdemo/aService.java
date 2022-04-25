package fi.ottooks.dreamcatcherdemo;

import static fi.ottooks.dreamcatcherdemo.notiChannel.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class aService extends Service {

    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.heratys);
        mediaPlayer.setLooping(true);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notiIntent = new Intent(this, ringActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notiIntent, 0);
        String alarmTitle = String.format("%s Alarm", "teST");



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.tilastot)
                .setContentTitle(alarmTitle)
                .setContentText("Herätys :)")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        createChannel(notificationManager);
        mediaPlayer.start();
        startForeground(notificationId, notificationBuilder.build());

        return START_STICKY;
    }

    public void createChannel(NotificationManager notificationManager) {
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, "nimi", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("testi noti");
            notificationManager.createNotificationChannel(channel);
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}

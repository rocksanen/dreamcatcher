package fi.ottooks.dreamcatcherdemo;

import static fi.ottooks.dreamcatcherdemo.aBroadcastReceiver.TITLE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
@Entity(tableName = "clock_table")
public class Clock {

    @PrimaryKey
    @NonNull
    private int id;


    private int hour;
    private int min;



    private long created;

    private String title;

    private boolean started;

    public Clock (int hour, int min, int id, String title, boolean started) {
        this.hour = hour;
        this.min = min;
        this.id = id;

        this.created = created;

        this.title = title;
        this.started = started;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getId() {
        return id;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public long getCreated() {
        return created;
    }

    public void set(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, aBroadcastReceiver.class);
        intent.putExtra(TITLE, title);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }


        String tText = null;
        try {
            tText = String.format("Herätys %s laitettu %s klo %02d:%02d", title, calendar.get(Calendar.DAY_OF_WEEK), hour, min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, tText, Toast.LENGTH_LONG).show();

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmPendingIntent);
        this.started = true;
    }

    public void cancel (Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, aBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pIntent);
        this.started = false;

        String tText = String.format("Herätys peruttu!");
        Toast.makeText(context, tText, Toast.LENGTH_SHORT).show();
    }


    public void cancel() {
        this.started = false;
    }
}

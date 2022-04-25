package fi.ottooks.dreamcatcherdemo;

import static fi.ottooks.dreamcatcherdemo.aBroadcastReceiver.TITLE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class Clock {

    private int hour;
    private int min;
    private int id;

    private long created;

    private String title;

    private boolean started;

    public Clock (int hour, int min, int id, String title) {
        this.hour = hour;
        this.min = min;
        this.id = id;

        this.created = created;

        this.title = title;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
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
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, id, intent, 0);

        Calendar calendar = Calendar.getInstance();

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


}

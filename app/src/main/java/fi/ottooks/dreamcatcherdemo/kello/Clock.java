package fi.ottooks.dreamcatcherdemo;

import static fi.ottooks.dreamcatcherdemo.kello.aBroadcastReceiver.TITLE;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Calendar;

import fi.ottooks.dreamcatcherdemo.kello.aBroadcastReceiver;

/**
 * The Clock class for Dream catcher
 *     This class handles stuff related to the alarm, its data & setting/canceling it
 * @author Samu
 */
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

    /**
     * Used to get & save the values of an alarm
     * @param hour the hour the alarm goes off, type int
     * @param min the minute the alarm goes off, type int
     * @param id id used to keep track of the alarms, type int
     * @param title for future features
     * @param started used to store if the alarm is enabled/disabled, type boolean
     */

    public Clock (int hour, int min, int id, String title, boolean started) {

        this.hour = hour;
        this.min = min;
        this.id = id;
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

    public long getCreated() {return created;}

    /**
     * Used to set the alarm by assigning the values from Clock (Above) to an calendar
     * Alarm time gets checked and if the time has passed on the day of setting it, it sets to the next day
     * Then alarm gets set with alarmManager and data of its set time and activation time is sent to the sharedpreferences
     * @param context
     */

    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void set(Context context) {

        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(context, aBroadcastReceiver.class);
        intent.putExtra(TITLE, title);
        final PendingIntent alarmPendingIntent =
        PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_IMMUTABLE);

        final Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {

            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        }

        created = calendar.getTimeInMillis();

        String tText = null;

        try {
            tText = String.format("Herätys asetettu klo %02d:%02d", hour, min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, tText, Toast.LENGTH_LONG).show();

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmPendingIntent);
        //Herätys aika menee sharedPreferensseihin
        UseSharedPreferences useSharedPreferences = new UseSharedPreferences();
        useSharedPreferences.saveStartTime();
        useSharedPreferences.saveWakeUpTime(calendar.getTimeInMillis());

        this.started = true;

    }

    /**
     * Used to cancel the alarm by getting a reference to the alarmmanager and creating an intent using the broadcaster,
     * intent is then used to create a pendingintent, which has a reference to the same id as on setting the alarm
     * Then we use alarmmanager with the pendingintent as a parameter to cancel the alarm
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void cancel (Context context) {

        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(context, aBroadcastReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pIntent);
        this.started = false;

        final String tText = "Herätys peruttu!";
        Toast.makeText(context, tText, Toast.LENGTH_SHORT).show();

    }
}

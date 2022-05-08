package fi.ottooks.dreamcatcherdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

/**
 * The aBroadcastReceiver class for Dream catcher
 *     Used to receive the broadcast and schedule the alarm afterwards
 */
public class aBroadcastReceiver extends BroadcastReceiver {

    public static final String TITLE = "TITLE";

    /**
     * Used to check if the received broadcast is not the same as broadcast when device is booted
     * If it is not, alarm is scheduled
     * @param context context
     * @param intent intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            final String tText = ":D";
            Toast.makeText(context, tText, Toast.LENGTH_SHORT).show();

        } else {

            final String tText = "Wake up";
            Toast.makeText(context, tText, Toast.LENGTH_SHORT).show();
            startAlarm(context, intent);

        }
    }

    /**
     * Checks if the build version is good and schedules the alarm
     * @param context context
     * @param intent intent
     */
    private void startAlarm(Context context, Intent intent) {

        final Intent intentService = new Intent(context, aService.class);
        intentService.putExtra(TITLE, intent.getStringExtra(TITLE));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            context.startForegroundService(intentService);

        }
    }
}

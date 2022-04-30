package fi.ottooks.dreamcatcherdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class aBroadcastReceiver extends BroadcastReceiver {

    public static final String TITLE = "TITLE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String tText = String.format("Test");
            Toast.makeText(context, tText, Toast.LENGTH_SHORT).show();
        } else {
            String tText = "Herätys soi :)";
            Toast.makeText(context, tText, Toast.LENGTH_SHORT).show();
            startAlarm(context, intent);

        }
    }

    private void startAlarm(Context context, Intent intent) {
        Intent intentService = new Intent(context, aService.class);
        intentService.putExtra(TITLE, intent.getStringExtra(TITLE));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        }

    }
}

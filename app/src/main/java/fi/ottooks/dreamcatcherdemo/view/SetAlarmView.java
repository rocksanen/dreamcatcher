package fi.ottooks.dreamcatcherdemo.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import java.util.Random;
import fi.ottooks.dreamcatcherdemo.Clock;


/**
 * The SetAlarmView class for Dream catcher
 *     Used to create and control everything on the setAlarmView activity
 * @author Samu
 *
 */

public class SetAlarmView extends AppCompatActivity {

    private CreateView createView;
    private TimePicker tp;

    /**
     * gets called once user wants to add alarm
     * Sets the view & captures elements, then sets an listener for the add button and uses the luoHeratys() to set the alarm
     *
     * @param savedInstanceState savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(fi.ottooks.dreamcatcherdemo.R.layout.activity_set_alarm_view);

        createView = ViewModelProviders.of(this).get(CreateView.class);
        tp = (TimePicker)findViewById(fi.ottooks.dreamcatcherdemo.R.id.test_picker);

        final Button btn = (Button)findViewById(fi.ottooks.dreamcatcherdemo.R.id.uusi_heratys_nappi);
        btn.setOnClickListener(view -> {

            luoHeratys();
            finish();

        });
    }

    /**
     * Used to set a new alarm using the set() from Clock class
     * gets values from the timepicker, inserts the alarm into the database & sets it
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void luoHeratys() {

        final int id = new Random().nextInt(Integer.MAX_VALUE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            final Clock clock = new Clock(tp.getHour(), tp.getMinute(), id, "Alarm", true);

            createView.insert(clock);
            clock.set(this);

        }
    }
}
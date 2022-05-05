package fi.ottooks.dreamcatcherdemo.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Random;

import fi.ottooks.dreamcatcherdemo.Clock;
//import fi.ottooks.dreamcatcherdemo.R;

import fi.ottooks.dreamcatcherdemo.view.CreateView;

/**
 * The SetAlarmView class for Dream catcher
 *     Used to create and control everything on the setAlarmView activity
 *
 */

public class SetAlarmView extends AppCompatActivity {


    private CreateView createView;

    TimePicker tp;

    /**
     * gets called once user wants to add alarm
     * Sets the view & captures elements, then sets an listener for the add button and uses the luoHeratys() to set the alarm
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("testing", "TestT");
        super.onCreate(savedInstanceState);
        setContentView(fi.ottooks.dreamcatcherdemo.R.layout.activity_set_alarm_view);
        createView = ViewModelProviders.of(this).get(CreateView.class);

        tp = (TimePicker)findViewById(fi.ottooks.dreamcatcherdemo.R.id.test_picker);

        Button btn = (Button)findViewById(fi.ottooks.dreamcatcherdemo.R.id.uusi_heratys_nappi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luoHeratys();
                finish();
            }
        });

    }


    /**
     * Used to set a new alarm using the set() from Clock class
     * gets values from the timepicker, inserts the alarm into the database & sets it
     */
    private void luoHeratys() {
        int id = new Random().nextInt(Integer.MAX_VALUE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            Clock clock = new Clock(tp.getHour(), tp.getMinute(), id, "Alarm", true);

            createView.insert(clock);
            clock.set(this);


        }
    }
}
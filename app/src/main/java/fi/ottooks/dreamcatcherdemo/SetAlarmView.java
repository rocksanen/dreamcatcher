package fi.ottooks.dreamcatcherdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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


public class SetAlarmView extends AppCompatActivity {



    TimePicker tp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("testing", "TestT");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_view);

        tp = (TimePicker)findViewById(R.id.test_picker);

        Button btn = (Button)findViewById(R.id.uusi_heratys_nappi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testing", "TestT");
                luoHeratys();
            }
        });
        //tp=(TimePicker) tp.findViewById(R.id.test_picker);

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_set_alarm_view, container, false);
        Button btn = (Button) view.findViewById(R.id.uusi_heratys_nappi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testing", "TestT");
            }
        });
        return view;
    }


    private void luoHeratys() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Log.d("testing", "String.valueOf(clock)");
            Log.d("Tunti", Integer.toString(tp.getHour()));
            Log.d("Tunti", Integer.toString(tp.getMinute()));
            Clock clock = new Clock(tp.getHour(), tp.getMinute(), alarmId, "Test");
            //clock.set(getContext());
            Log.d("testing", Integer.toString(clock.getHour()));
            Log.d("testing", Integer.toString(clock.getMin()));
            clock.set(this);


        }
    }
}
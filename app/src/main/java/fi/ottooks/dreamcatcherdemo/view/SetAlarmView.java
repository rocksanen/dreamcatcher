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
import fi.ottooks.dreamcatcherdemo.R;

import fi.ottooks.dreamcatcherdemo.view.CreateView;


public class SetAlarmView extends AppCompatActivity {


    private CreateView createView;

    TimePicker tp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("testing", "TestT");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_view);
        createView = ViewModelProviders.of(this).get(CreateView.class);
        //createView = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(CreateView.class);

        tp = (TimePicker)findViewById(R.id.test_picker);

        Button btn = (Button)findViewById(R.id.uusi_heratys_nappi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testing", "TestT");
                luoHeratys();
                finish();
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
        int id = new Random().nextInt(Integer.MAX_VALUE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            Log.d("testing", "String.valueOf(clock)");
            Log.d("Tunti", Integer.toString(tp.getHour()));
            Log.d("Tunti", Integer.toString(tp.getMinute()));
            Clock clock = new Clock(tp.getHour(), tp.getMinute(), id, "Test", true);




            createView.insert(clock);
            clock.set(this);




        }
    }
}
package fi.ottooks.dreamcatcherdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import javax.annotation.Nullable;

import fi.ottooks.dreamcatcherdemo.Clock;
import fi.ottooks.dreamcatcherdemo.MoodSelect;
//import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.aService;
import fi.ottooks.dreamcatcherdemo.kello.ClockRepo;

public class ringActivity extends AppCompatActivity {


    /**
     * The mood select activity will show when "Sammuta" button been pressed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fi.ottooks.dreamcatcherdemo.R.layout.ring_activity);
        Button btn = (Button)findViewById(fi.ottooks.dreamcatcherdemo.R.id.sammuta);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentService = new Intent(getApplicationContext(), aService.class);
                getApplicationContext().stopService(intentService);
                finish();



                Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
                startActivity(intentMood);

            }
        });

    }



}

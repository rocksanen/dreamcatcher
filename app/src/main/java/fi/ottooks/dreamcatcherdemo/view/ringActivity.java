package fi.ottooks.dreamcatcherdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import javax.annotation.Nullable;

import fi.ottooks.dreamcatcherdemo.MoodSelect;
import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.aService;

public class ringActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_activity);
        Button btn = (Button)findViewById(R.id.sammuta);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentService = new Intent(getApplicationContext(), aService.class);
                getApplicationContext().stopService(intentService);
                finish();

                /**
                 *  The mood select activity will show when "Sammuta" button been pressed.
                 */

                Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
                startActivity(intentMood);

            }
        });



    }







}

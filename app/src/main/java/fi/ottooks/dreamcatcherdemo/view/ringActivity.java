package fi.ottooks.dreamcatcherdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import javax.annotation.Nullable;
import fi.ottooks.dreamcatcherdemo.MoodSelect;
import fi.ottooks.dreamcatcherdemo.kello.aService;


public class ringActivity extends AppCompatActivity {

    /**
     * The mood select activity will show when "Sammuta" button been pressed.
     * @param savedInstanceState
     * @author Samu and Mohammed
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(fi.ottooks.dreamcatcherdemo.R.layout.ring_activity);

        final Button btn = (Button)findViewById(fi.ottooks.dreamcatcherdemo.R.id.sammuta);
        btn.setOnClickListener(view -> {

            final Intent intentService = new Intent(getApplicationContext(), aService.class);
            getApplicationContext().stopService(intentService);
            finish();

            final Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
            startActivity(intentMood);

        });
    }
}

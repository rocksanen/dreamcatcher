package fi.ottooks.dreamcatcherdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity is called when the clock is ringing and the user presses the popup notification
 */

public class clockBreak extends AppCompatActivity {

    private ImageButton breakBtn;
    private int clicked;

    /**
     * @param savedInstanceState An anonymous listener will be called that listens if the user has pressed either "Sammuta" button or
     *                           the imagebutton that triggers an series of animations.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_break);

        clicked = 0;
        final Button sammutaBtn = findViewById(R.id.sammutaBtn);
        breakBtn = findViewById(R.id.breakBtn);
        breakBtn.setImageResource(R.drawable.clockbreak);

        sammutaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intentService = new Intent(getApplicationContext(), aService.class);
                getApplicationContext().stopService(intentService);
                finish();

                /**
                 *  The mood select activity is called when "Sammuta" button is pressed.
                 */

                final Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
                startActivity(intentMood);
            }
        });

        breakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clicked++;

                final Animation animShake = AnimationUtils.loadAnimation(clockBreak.this, R.anim.shake);
                view.startAnimation(animShake);

                if (clicked == 2) {
                    breakBtn.setImageResource(R.drawable.clockbreakstate1);
                } else if (clicked == 4) {
                    breakBtn.setImageResource(R.drawable.clockbreakstate2);
                } else if (clicked == 6) {
                    breakBtn.setImageResource(R.drawable.clockbreakstate3);
                } else if (clicked == 8) {
                    breakBtn.setImageResource(R.drawable.clockbreakstate4);
                }
                if (clicked >= 10) {

                    final Intent intentService = new Intent(getApplicationContext(), aService.class);
                    getApplicationContext().stopService(intentService);
                    finish();

                    /**
                     *  The mood select activity is called when "Sammuta" button is pressed.
                     */

                    final Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
                    startActivity(intentMood);

                }
            }
        });
    }
}
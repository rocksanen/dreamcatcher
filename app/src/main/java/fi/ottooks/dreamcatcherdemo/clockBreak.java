package fi.ottooks.dreamcatcherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class clockBreak extends AppCompatActivity {

    Button sammutaBtn;
    ImageButton breakBtn;
    Boolean sammutettu;
    int clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_break);
        sammutettu = false;
        clicked = 0;
        sammutaBtn = findViewById(R.id.sammutaBtn);
        breakBtn = findViewById(R.id.breakBtn);
        breakBtn.setImageResource(R.drawable.clockbreak);
        sammutaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "sammutettu");

                Intent intentService = new Intent(getApplicationContext(), aService.class);
                getApplicationContext().stopService(intentService);
                finish();

                /**
                 *  The mood select activity will show when "Sammuta" button been pressed.
                 */



                sammutettu = true;

                Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
                startActivity(intentMood);
            }
        });
        breakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked++;
                Log.d("", String.valueOf(clicked));
                Animation animShake = AnimationUtils.loadAnimation(clockBreak.this, R.anim.shake);
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

                    Intent intentService = new Intent(getApplicationContext(), aService.class);
                    getApplicationContext().stopService(intentService);
                    finish();

                    /**
                     *  The mood select activity will show when "Sammuta" button been pressed.
                     */



                    sammutettu = true;

                    Intent intentMood = new Intent(getApplicationContext(), MoodSelect.class);
                    startActivity(intentMood);

                    Log.d("", "sammutettu");
                }
            }
        });
    }

    public boolean getBreak() {
        return sammutettu;
    }
}
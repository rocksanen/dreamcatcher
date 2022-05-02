package fi.ottooks.dreamcatcherdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The Mood class for Dream catcher 300
 * <p>
 *     This class handles the Mood value after waking up (Stopping the alarm)
 * </p>
 *
 * @author Mohammed Al-Jewari
 */

public class MoodSelect extends AppCompatActivity {
    ImageButton mood1;
    ImageButton mood2;
    ImageButton mood3;
    ImageButton mood4;
    ImageButton mood5;
    int moodValue = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_select);

        /**
         * The listener here listens to what user will choose as a mood value when he stops the alarm.
         *
         * The value will be by default 3, but will change depending on user's choice.
         *
         * When user will choose his mood "Value" then this activity will end.
         */

        mood1 = (ImageButton) findViewById(R.id.mood1Btn);
        mood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            moodValue = 1;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
                finish();

            }
        });

        mood2 = (ImageButton) findViewById(R.id.mood2Btn);
        mood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            moodValue = 2;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
                finish();
            }
        });

        mood3 = (ImageButton) findViewById(R.id.mood3Btn);
        mood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodValue = 3;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
                finish();
            }
        });

        mood4 = (ImageButton) findViewById(R.id.mood4Btn);
        mood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodValue = 4;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
                finish();
            }
        });

        mood5 = (ImageButton) findViewById(R.id.mood5Btn);
        mood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodValue = 5;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
                finish();
            }
        });


    }
    /**
     * return the selected mood value so can fetch it in the shared preferences.
     */
    public int getMoodValue(){
        return moodValue;

    }

}
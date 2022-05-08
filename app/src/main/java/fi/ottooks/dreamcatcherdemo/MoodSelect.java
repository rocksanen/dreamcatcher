package fi.ottooks.dreamcatcherdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;

/**
 * The Mood class for Dream catcher
 *     This class handles the Mood value after waking up (Stopping the alarm)
 * @author Jesper, Mohammed and Otto
 */

public class MoodSelect extends AppCompatActivity {

    private int moodValue = 3;

    /**
     * The listener checks which mood form button is pressed
     * The default value is 3, but changes depending on the user's choice.
     * When user chooses the mood "Value", MainActivity is called
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_select);

        /**
         * The listener checks which mood form button is pressed
         * The default value is 3, but changes depending on the user's choice.
         * When user chooses the mood "Value", MainActivity is called
         */

        final ImageButton mood1 = (ImageButton) findViewById(R.id.mood1Btn);

        mood1.setOnClickListener(view -> {

            moodValue = 1;
            setAllValuesToUserInputs();
            toMainView();

        });

        final ImageButton mood2 = (ImageButton) findViewById(R.id.mood2Btn);
        mood2.setOnClickListener(view -> {

            moodValue = 2;
            setAllValuesToUserInputs();
            toMainView();

        });

        final ImageButton mood3 = (ImageButton) findViewById(R.id.mood3Btn);
        mood3.setOnClickListener(view -> {

            moodValue = 3;
            setAllValuesToUserInputs();
            toMainView();

        });

        final ImageButton mood4 = (ImageButton) findViewById(R.id.mood4Btn);
        mood4.setOnClickListener(view -> {

            moodValue = 4;
            setAllValuesToUserInputs();
            toMainView();

        });

        final ImageButton mood5 = (ImageButton) findViewById(R.id.mood5Btn);
        mood5.setOnClickListener(view -> {

            moodValue = 5;
            setAllValuesToUserInputs();
            toMainView();

        });


    }

    /**
     * Saves all the alarm info to sharedpreferences
     * if slept hours is less than 3, it will not be counted as "sleep" and
     * data will not be saved.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setAllValuesToUserInputs() {

        final UseSharedPreferences useSharedPreferences = new UseSharedPreferences();

        if(useSharedPreferences.getStartTime() - useSharedPreferences.getEndTime() >= 3) {

            new UserInputs(LocalDate.now(),
            useSharedPreferences.getStartTime(),
            useSharedPreferences.getEndTime(),this.moodValue);

        }
    }

    /**
     * Returns to MainActivity
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void toMainView() {

        startActivity(new Intent(this, MainActivity.class).
        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        finish();

    }
}
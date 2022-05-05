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
 */

public class MoodSelect extends AppCompatActivity {
    private int moodValue = 3;



    /**
     * The listener checks which mood form button is pressed
     * The default value is 3, but changes depending on the user's choice.
     * When user chooses the mood "Value", MainActivity is called
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_select);



        ImageButton mood1 = (ImageButton) findViewById(R.id.mood1Btn);
        mood1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
            moodValue = 1;

                toMainView();

            }
        });

        ImageButton mood2 = (ImageButton) findViewById(R.id.mood2Btn);
        mood2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
            moodValue = 2;

                toMainView();
            }
        });

        ImageButton mood3 = (ImageButton) findViewById(R.id.mood3Btn);
        mood3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                moodValue = 3;

                toMainView();
            }
        });

        ImageButton mood4 = (ImageButton) findViewById(R.id.mood4Btn);
        mood4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                moodValue = 4;

                toMainView();
            }
        });

        ImageButton mood5 = (ImageButton) findViewById(R.id.mood5Btn);
        mood5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                moodValue = 5;

                toMainView();
            }
        });


    }
    /**
     *@return  returns the selected mood value. (int)
     */
    public int getMoodValue(){
        return moodValue;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setAllValuesToUserInputs() {

        UseSharedPreferences useSharedPreferences = new UseSharedPreferences();
        UserInputs userInputs = new UserInputs(LocalDate.now(),
        useSharedPreferences.getStartTime(),
        useSharedPreferences.getEndTime(),this.moodValue);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void toMainView() {

        //setAllValuesToUserInputs();

        startActivity(new Intent(this, MainActivity.class).
        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        setAllValuesToUserInputs();

        finish();

    }

}
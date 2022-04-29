package fi.ottooks.dreamcatcherdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


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

        mood1 = (ImageButton) findViewById(R.id.mood1Btn);
        mood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            moodValue = 1;
                Log.d("MyTag", "Mood arvo on: " + moodValue);

            }
        });

        mood2 = (ImageButton) findViewById(R.id.mood2Btn);
        mood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            moodValue = 2;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
            }
        });

        mood3 = (ImageButton) findViewById(R.id.mood3Btn);
        mood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodValue = 3;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
            }
        });

        mood4 = (ImageButton) findViewById(R.id.mood4Btn);
        mood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodValue = 4;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
            }
        });

        mood5 = (ImageButton) findViewById(R.id.mood5Btn);
        mood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodValue = 5;
                Log.d("MyTag", "Mood arvo on: " + moodValue);
            }
        });


    }

    public int getMoodValue(){
        return moodValue;
    }

}
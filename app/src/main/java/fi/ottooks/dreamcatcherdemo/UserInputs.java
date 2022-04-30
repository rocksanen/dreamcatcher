package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import java.io.Serializable;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserInputs implements Serializable, Comparable<UserInputs> {

    private  double startTime;
    private  double endTime;
    private  double sleepTime;
    private  int moodValue;
    private LocalDate date;
    public static final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";


    public UserInputs(LocalDate date,double startTime, double endTime, double sleepTime, int moodValue) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepTime = sleepTime;
        this.moodValue = moodValue;
        this.date = date;

        save();

    }

    public UserInputs(){

    }



    public double getStartTime() {

        return this.startTime;
    }
    public double getEndTime(){

        return this.endTime;
    }

    public double getSleepTime(){

        return this.sleepTime;
    }

    public int getMoodValue(){

        return this.moodValue;
    }


    public LocalDate getDate() {

        return this.date;
    }



    public void save() {

        /*
        UseSharedPreferences clear = new UseSharedPreferences();
        clear.clearData();

        */

        UseSharedPreferences shr =
        new UseSharedPreferences(this);

        //UseSharedPreferences shar = new UseSharedPreferences(10.00);



    }






    @NonNull
    public String toString(){

        return "Start time: " + this.startTime + ",\n" +
                "End Time: " + this.endTime + ",\n" +
                "Full sleep time: " + this.sleepTime + ",\n" +
                "Mood: " + this.moodValue + ".";
    }


    @Override
    public int compareTo(UserInputs other) {

        return Integer.compare(this.moodValue, other.moodValue);

    }
}

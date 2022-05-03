package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.Serializable;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;


/**
 * The UserInputs class for Dream catcher 3000
 *
 * <p>
 *     This class handles all data that the users input.
 * </p>
 *
 *  Saves the data as a Gson object by using UseSharedPreferences class's method.
 *
 * @author Mohammed Al-Jewari
 */

public class UserInputs implements Serializable, Comparable<UserInputs> {

    private  double startTime;
    private  double endTime;
    private  double sleepTime;
    private  int moodValue;
    private String date;





        /**
         * Create UserInputs object, given the data provided.
         *
         * @param date a LocalDate parameter to differ the inputted data of the same day (LocalDate)
         * @param startTime  a sleeping start time (Double)
         * @param endTime a sleeping end time (Double)
         * @param sleepTime a full sleep time (Double)
         * @param moodValue a mood value after waking up (Integer)
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
    public UserInputs(LocalDate date,double startTime, double endTime, double sleepTime, int moodValue) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepTime = sleepTime;
        this.moodValue = moodValue;
        this.date = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

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

    public String getDate() {

        return this.date;
    }


    /**
     * Save the created UserInputs object by using UseSharedPreferences class's method
     */
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

    /**
     * Make the objects those are created using this class constructor comparable.
     *
     * Compare the objects by its mood value.
     * @param other an UserInputs object that compares to. (UserInputs object)
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(UserInputs other) {

        return Integer.compare(this.moodValue, other.moodValue);

    }
}

package fi.ottooks.dreamcatcherdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.Serializable;

import com.google.gson.Gson;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private long startTime;
    private long endTime;
    private int moodValue;
    private String date;

        /**
         * Create UserInputs object, given the data provided.
         *
         * @param date a LocalDate parameter to differ the inputted data of the same day (LocalDate)
         * @param startTime  a sleeping start time (Double)
         * @param endTime a sleeping end time (Double)
         * @param moodValue a mood value after waking up (Integer)
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
    public UserInputs(LocalDate date, long startTime, long endTime, int moodValue) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.moodValue = moodValue;
        this.date = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

        save();

    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setMoodValue(int moodValue) {
        this.moodValue = moodValue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserInputs(){

    }


    public long getStartTime() {

        return this.startTime;
    }
    public long getEndTime(){

        return this.endTime;
    }

    public Float getSleepTime(){

        long end = this.endTime - this.startTime;

        return (float) ((end / (3600000)) % 24);
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void save() {


        UseSharedPreferences shr =
                new UseSharedPreferences(this);



    }






    @NonNull
    public String toString(){

        return "Start time: " + this.startTime + ",\n" +
                "End Time: " + this.endTime + ",\n" +
                "Full sleep time: " + this.getSleepTime() + ",\n" +
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

package fi.ottooks.dreamcatcherdemo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Tässä javadoc
 */
public class StatsSorting implements Comparable<StatsSorting> {

    private final double startTime;
    private final double endTime;
    private final double fullTime;
    private final int moodValue;
    //private final LocalDate date;
    private UserInputs userInputs = new UserInputs();




    public StatsSorting(double startTime, double endTime, double fullTime, int moodValue) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.fullTime = fullTime;
        this.moodValue = moodValue;
        //this.date = date;

    }






    @Override
    public int compareTo(StatsSorting o)
    {
        if (this.moodValue != o.getMood()) {
            return this.moodValue - o.getMood();
        }
        return this.moodValue;
    }



    public int getMood() {


        return this.moodValue;
    }

    public double getStartTime() {

        return startTime;
    }

    public double getEndTime() {

        return this.endTime;
    }

    public double getFullTime() {

        return this.fullTime;
    }

    //public LocalDate getDate() {

        //return this.date;
    //}

    public String toString() {


        return this.startTime + ", " + this.endTime + ", " + this.fullTime + ", " + this.moodValue;
    }






}

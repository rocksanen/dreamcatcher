package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.Context;


public class UserInputs implements Comparable<UserInputs> {

    private  double startTime;
    private  double endTime;
    private  double sleepTime;
    private  int moodValue;

    public UserInputs(double startTime,double endTime, double sleepTime, int moodValue) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepTime = sleepTime;
        this.moodValue = moodValue;

    }

    public UserInputs(){
        /*                      //Otto, tarvitseko että tämä konstrakturi ottaa oletuksia arvoja kun kutsutaan sen?!
        this.startTime = 23.00;
        this.endTime = 7.00;
        this.sleepTime = 8.00;
        this.moodValue = 5;

         */
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

    /*
    @Override
    public Activity getActivity() {
        return ((android.app.Fragment) getContext()).getActivity();
    }

     */

    public void save() {

        /*

        SharedPreferences prefPut = getSharedPreferences("Userinputs" , Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putLong("StartTime", Double.doubleToLongBits(this.startTime));
        prefEditor.putLong("EndTime", Double.doubleToLongBits(this.endTime));
        prefEditor.putLong("FullTime", Double.doubleToLongBits(this.sleepTime));
        prefEditor.putInt("Mood", this.moodValue);

        prefEditor.commit();

         */
    }






    public String toString(){

        return "Alarm start time: " + this.startTime + ",\n" +
                "Alarm end Time: " + this.endTime + ",\n" +
                "Full sleep time: " + this.sleepTime + ",\n" +
                "Mood: " + this.moodValue + ".";
    }


    @Override
    public int compareTo(UserInputs other) {

        return Integer.compare(this.moodValue, other.moodValue);  //Otto is this what u meant by using comparable implementation(to sort Mood values)!?

        /*
        if (this.moodValue != other.getMoodValue()) {
            return this.moodValue - other.getMoodValue();
        }
        return this.moodValue;

     */
    }
}

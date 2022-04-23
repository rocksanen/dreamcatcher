package fi.ottooks.dreamcatcherdemo;

import android.content.SharedPreferences;
import android.content.Context;


public class UserInputs implements Comparable<UserInputs> {

    private final static String PREFS = "UserInputs";
    private final static String START = "StartTime";
    private final static String END = "EndTime";
    private final static String FULL = "FullTime";
    private final static String MOOD = "Mood";

    Context context ;


    private  double startTime;
    private  double endTime;
    private  double sleepTime;
    private  int moodValue;


    public UserInputs(double startTime,double endTime, double sleepTime, int moodValue) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepTime = sleepTime;             //Sleeptime = (24-this.endTime) + this.startTime (Automatic way);
        this.moodValue = moodValue;

       // saveData();
    }

    public UserInputs(){
        /*                      //Otto, tarvitseko että tämä konstrakturi ottaa oletuksia arvoja kun kutsutaan sen?!
        this.startTime = 23.00;
        this.endTime = 7.00;
        this.sleepTime = 8.00;
        this.moodValue = 5;

         */
    }



    public double getStartTime(){
        // SharedPreferences prefGet = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        // Double.longBitsToDouble(prefGet.getLong(START,0));
        return this.startTime;
    }
    public double getEndTime(){
        // SharedPreferences prefGet = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        // Double.longBitsToDouble(prefGet.getLong(END,0));
        return this.endTime;
    }

    public double getSleepTime(){

        // SharedPreferences prefGet = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        // Double.longBitsToDouble(prefGet.getLong(FULL,0));
        return this.sleepTime;
    }

    public int getMoodValue(){

        // SharedPreferences prefGet = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        // prefGet.getInt(MOOD,0);
        return this.moodValue;
    }

    public void loadData(){

        SharedPreferences prefGet = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        Double.longBitsToDouble(prefGet.getLong(START,0));
        Double.longBitsToDouble(prefGet.getLong(END,0));
        Double.longBitsToDouble(prefGet.getLong(FULL,0));
        prefGet.getInt(MOOD,0);
    }
    public void saveData(){

        SharedPreferences prefPut = context.getSharedPreferences(PREFS ,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putLong(START, Double.doubleToLongBits(this.startTime));
        prefEditor.putLong(END, Double.doubleToLongBits(this.endTime));
        prefEditor.putLong(FULL, Double.doubleToLongBits(this.sleepTime));
        prefEditor.putInt(MOOD, this.moodValue);

        prefEditor.commit();

    }


    public String toString(){

        return "Alarm start time: " + this.startTime + ",\n" +
                "Alarm end Time: " + this.endTime + ",\n" +
                "Full sleep time: " + this.sleepTime + ",\n" +
                "Mood: " + this.moodValue + ".";
    }


    @Override
    public int compareTo(UserInputs other){

        return Integer.compare(this.moodValue, other.moodValue);


    }
}

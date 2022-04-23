package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.time.LocalDate;

public class UserInputs implements Comparable<UserInputs> {

    private  double startTime;
    private  double endTime;
    private  double sleepTime;
    private  int moodValue;
    private LocalDate date;
    public static final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";
    Gson gson;


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

        //Tässä otetaan käyttöön Gson luokka
        Gson gson = new Gson();
        //Sen jälkeen alustetaan jsonin arvoksi tämä kyseinen UserInputsien instanssi.
        String json = gson.toJson(this);


        //Luodaan sharedpreferences mainactivityn contextia hyväksi käyttäen
        //Haetaan se context mainactivityyn juuri luodulla metodilla
        SharedPreferences sharedPreferences =
        MainActivity.getContextOfApplication().getSharedPreferences(SHAREDPREFS,Activity.MODE_PRIVATE);

        //Ja laitetaan tiedot mitä halutaan
        SharedPreferences.Editor prefEditor =
        sharedPreferences.edit();

        //prefEditor.putInt("StartTime", this.startTime);
        //prefEditor.putInt("EndTime", this.endTime);
        //prefEditor.putInt("FullTime", this.sleepTime);

        //Ja täällä lisätään json preferensseihin, avaimeksi olen tehnyt päivämäärän jota kasvatellaan testi
        //koodissa MainActivityn puolella.
        prefEditor.putString(this.date.toString(), json);

        //prefEditor.putString("Mohammed","Al-Jewari");
        prefEditor.commit();

    }






    @NonNull
    public String toString(){

        return "Alarm start time: " + this.startTime + ",\n" +
                "Alarm end Time: " + this.endTime + ",\n" +
                "Full sleep time: " + this.sleepTime + ",\n" +
                "Mood: " + this.moodValue + ".";
    }


    @Override
    public int compareTo(UserInputs other) {

        return Integer.compare(this.moodValue, other.moodValue);

    }
}

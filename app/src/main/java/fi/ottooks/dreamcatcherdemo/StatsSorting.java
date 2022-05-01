package fi.ottooks.dreamcatcherdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Tässä javadoc
 */
public class StatsSorting {

    private final List<UserInputs> userInputsList;

    public StatsSorting(List list) {

        this.userInputsList = list;

    }



    public String getUniKeskiArvot() {

        double totalSleepTime = 0;

        for(UserInputs user: userInputsList) {

            totalSleepTime += user.getSleepTime();

        }

        @SuppressLint("DefaultLocale") double sleepAverage =
                Double.parseDouble(String.format("%.1f",totalSleepTime  /  userInputsList.size())) ;


        return "Nukut keskimäärin " + sleepAverage + " tuntia yössä        ";

    }



    public String getMoodKeskiArvot() {

        double totalMoodValues = 0;

        for(UserInputs user: userInputsList) {

            totalMoodValues += user.getMoodValue();

        }

        @SuppressLint("DefaultLocale") double moodAverage =
                Double.parseDouble(String.format("%.1f",totalMoodValues  /  userInputsList.size())) ;

        return "Keskimääräinen tyytyväisyytesi on " + moodAverage + "/5";

    }


    public String getParasKokonaisAikaNukkua() {


        Collections.sort(userInputsList);

        double totalSleepTime = 0;
        int amountOfVariables = 0;

        for(UserInputs userInput: userInputsList) {

            if(userInput.getMoodValue() > 3) {

                totalSleepTime += userInput.getSleepTime();
                amountOfVariables ++;

            }

        }

        @SuppressLint("DefaultLocale") double sleepAverage =
                Double.parseDouble(String.format("%.1f",totalSleepTime  /  amountOfVariables)) ;

        return "Paras pituus unelle on keskimäärin " + sleepAverage + " tuntia";



    }



}

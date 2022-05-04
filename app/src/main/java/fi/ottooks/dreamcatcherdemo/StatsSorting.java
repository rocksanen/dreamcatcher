package fi.ottooks.dreamcatcherdemo;

import android.annotation.SuppressLint;

import java.util.Collections;
import java.util.List;

/**
 * Tässä javadoc
 */
public class StatsSorting {

    private final List<UserInputs> userInputsList;

    /**
     * Gets list to be processed in constructor
     * @param list
     */
    public StatsSorting(List list) {

        this.userInputsList = list;

    }


    /**
     * Returns sleep average to string
     * @return
     */
    public String getUniKeskiArvot() {

        double totalSleepTime = 0;

        for(UserInputs user: userInputsList) {

            totalSleepTime += user.getSleepTime();

        }

        @SuppressLint("DefaultLocale") double sleepAverage =
                Double.parseDouble(String.format("%.1f",totalSleepTime  /  userInputsList.size())) ;


        return "Nukut keskimäärin " + sleepAverage + " tuntia yössä        ";

    }

    /**
     * Returns sleep average to double
     * @return
     */
    public double getUniKeskiArvoToDouble() {

        double totalSleepTime = 0;

        for(UserInputs user: userInputsList) {

            totalSleepTime += user.getSleepTime();

        }

        return totalSleepTime  /  userInputsList.size();
    }


    /**
     * Returns mood average to String
     * @return
     */
    public String getMoodKeskiArvot() {

        double totalMoodValues = 0;

        for(UserInputs user: userInputsList) {

            totalMoodValues += user.getMoodValue();

        }

        @SuppressLint("DefaultLocale") double moodAverage =
                Double.parseDouble(String.format("%.1f",totalMoodValues  /  userInputsList.size())) ;

        return "Keskimääräinen tyytyväisyytesi on " + moodAverage + "/5";

    }

    /**
     * Compares sleeptimes to mood values and returns best length
     * to sleep as String
     * @return
     */
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

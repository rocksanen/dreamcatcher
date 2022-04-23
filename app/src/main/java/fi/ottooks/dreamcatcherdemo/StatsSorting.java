package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.content.SharedPreferences;
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
public class StatsSorting {

    public static final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";
    private SharedPreferences sharedPreferences;


    public StatsSorting() {

        sharedPreferences =
                MainActivity.
                getContextOfApplication().
                getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);



    }


}

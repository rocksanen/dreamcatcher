package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.os.Build;
import android.util.Log;


import androidx.annotation.RequiresApi;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class UseSharedPreferences {

    private final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";

    private final android.content.SharedPreferences sharedPreferences =
    MainActivity.getContextOfApplication().
    getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);

    public static final String LIST = "objectList";
    public static final String START = "startTime";
    public static final String END = "endTime";
    private List<UserInputs> userInputsList = new ArrayList<>();


        @RequiresApi(api = Build.VERSION_CODES.O)
        public UseSharedPreferences(UserInputs object) {

            this.userInputsList = getListFromPreferences();
            this.userInputsList.add(object);
            saveObjectToList();

        }

        public UseSharedPreferences() {}

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void saveObjectToList() {

            Gson gson = new Gson();
            final String json = gson.toJson(this.userInputsList);

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putString(LIST, json);
            prefEditor.commit();

            try {

                Firebase firebase = new Firebase();
                firebase.saveToFireBase(this.userInputsList);



            } catch (Exception e) {

                System.out.println("ei mennyt");

            }

        }

        public void saveWakeUpTime(long calendar) {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putLong(END,calendar);
            prefEditor.commit();

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void saveStartTime() {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();
            //Instant start = Instant.now();

            long aika = System.currentTimeMillis();

            prefEditor.putLong(START,aika);
            prefEditor.commit();




        }

        public long getStartTime() {

            return sharedPreferences.getLong(START,0);

        }
        public long getEndTime() {
            //Date date = new Date(sharedPreferences.getLong(END,0));
            return sharedPreferences.getLong(END,0);
        }

        public List<UserInputs> getListFromPreferences() {

            List<UserInputs> listItems;

            final String objectList = sharedPreferences.getString(LIST, null);

                if(objectList != null) {

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<UserInputs>>(){}.getType();
                    listItems = gson.fromJson(objectList,type);

                    return listItems;
                }

                return new ArrayList<>();
        }

        public void clearData() {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.clear();
            prefEditor.commit();

        }

}

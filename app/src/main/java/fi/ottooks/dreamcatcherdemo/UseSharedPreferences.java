package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UseSharedPreferences {

    private final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";

    private final android.content.SharedPreferences sharedPreferences =
    MainActivity.getContextOfApplication().
    getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);

    public static final String LIST = "objectList";
    public static final String START = "starTime";
    private List<UserInputs> userInputsList = new ArrayList<>();


        public UseSharedPreferences(UserInputs object) {

            this.userInputsList = getListFromPreferences();
            this.userInputsList.add(object);
            saveObjectToList();

        }

        public UseSharedPreferences() {}

        private void saveObjectToList() {

            Gson gson = new Gson();
            final String json = gson.toJson(this.userInputsList);

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putString(LIST, json);
            prefEditor.commit();

        }

        public void saveStartTime(double startTime) {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putLong(START,Double.doubleToRawLongBits(startTime));
            prefEditor.commit();

        }

        public Double getStartTime() {

            return Double.longBitsToDouble(sharedPreferences.getLong(START,0));

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

package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;


import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.auth.User;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UseSharedPreferences {


    private double startTime;
    private android.content.SharedPreferences sharedPreferences;
    public static final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";
    public static final String LIST = "objectList";
    public static final String START = "starTime";
    private List<UserInputs> userInputsList = new ArrayList<>();
    private String objectList;


        public UseSharedPreferences(UserInputs object) {

            shraredFileConnector();
            this.userInputsList = getListFromPreferences();

            this.userInputsList.add(object);

            saveObjectToList();


        }


        public UseSharedPreferences(double startTime) {

            shraredFileConnector();
            this.startTime = startTime;
            saveStartTime();


        }

        public UseSharedPreferences() {

            shraredFileConnector();

        }

        private void saveObjectToList() {

            Gson gson = new Gson();
            String json = gson.toJson(this.userInputsList);

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putString(LIST, json);
            prefEditor.commit();


        }

        private void saveStartTime() {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putLong(START,Double.doubleToRawLongBits(this.startTime));
            prefEditor.commit();


        }

        public Double getStartTime() {

            return Double.longBitsToDouble(sharedPreferences.getLong(START,0));

        }

        public List<UserInputs> getListFromPreferences() {

            List<UserInputs> listItems;


                objectList = sharedPreferences.getString(LIST,null);


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

        //Alustetaan sharedpreferences
        private void shraredFileConnector() {


            this.sharedPreferences =
            MainActivity.getContextOfApplication().
            getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);

        }

}

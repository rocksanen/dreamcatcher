package fi.ottooks.dreamcatcherdemo;

import android.app.Activity;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to save to sharedPreferences and get from sharedPreferences
 * @author Otto
 */
public class UseSharedPreferences {

    private static final String SHAREDPREFS = "fi.ottooks.dreamcatcherdemo";
    /**
     * Initialization of sharedPreferences folder
     */
    private final android.content.SharedPreferences sharedPreferences =
    AppContext.getContext().
    getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);

    public static final String LIST = "objectList";
    public static final String START = "startTime";
    public static final String END = "endTime";
    private List<UserInputs> userInputsList = new ArrayList<>();

    /**
     * Get the UserInputs object to be saved to sharedPreferences
     * @param object
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
        public UseSharedPreferences(UserInputs object) {

            this.userInputsList = getListFromPreferences();
            this.userInputsList.add(object);
            saveObjectToList();

        }

    /**
     * Non parametric constructor
     */
        public UseSharedPreferences() {}

    /**
     * Changes object-list to json and saves the json to sharedpreferences
     * Saves object-list to Firebase database
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
        private void saveObjectToList() {

            final Gson gson = new Gson();
            final String json = gson.toJson(this.userInputsList);

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putString(LIST, json);
            prefEditor.commit();

            try {

                final Firebase firebase = new Firebase();
                firebase.saveToFireBase(this.userInputsList);

            } catch (Exception e) {

                System.out.println(e.getCause() + " Eipähän maha mittään");

            }
        }

    /**
     * Saves intented waking time
     * @param calendar
     */
        public void saveWakeUpTime(long calendar) {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.putLong(END,calendar);
            prefEditor.commit();

        }

    /**
     * Saves the start time when triggering the wakeup time
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
        public void saveStartTime() {

            final android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            final long aika = System.currentTimeMillis();

            prefEditor.putLong(START,aika);
            prefEditor.commit();

        }

    /**
     * Returns startingtime
     * @return
     */
        public long getStartTime() { return sharedPreferences.getLong(START,0);}

    /**
     * Returns endtime
     * @return
     */
        public long getEndTime() { return sharedPreferences.getLong(END,0);}

    /**
     * Gets json file back from sharedpreferences and changes it back to object-list
     * @return
     */
        public List<UserInputs> getListFromPreferences() {

            List<UserInputs> listItems;

            final String objectList = sharedPreferences.getString(LIST, null);

                if(objectList != null) {

                    final Gson gson = new Gson();
                    final Type type = new TypeToken<List<UserInputs>>(){}.getType();
                    listItems = gson.fromJson(objectList,type);

                    return listItems;
                }

                return new ArrayList<>();
        }

        //Testien puhdistusta varten ainoastaan!!!!
        public void clearData() {

            android.content.SharedPreferences.Editor prefEditor =
            sharedPreferences.edit();

            prefEditor.clear();
            prefEditor.commit();

        }
}

package fi.ottooks.dreamcatcherdemo.kello;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fi.ottooks.dreamcatcherdemo.Clock;
/**
 * The ClockDB class for Dream catcher
 *     Used to create a database, annotation @Database annotation includes an entities array that lists
 *     every data entity that is associated with the database
 */

@Database(entities = {Clock.class}, version = 1, exportSchema = false)
public abstract class ClockDB extends RoomDatabase {
    public abstract ClockDAO clockDao();

    private static volatile ClockDB INSTANCE;
    private static final int THREADS = 4;
    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(THREADS);

    /**
     * Checks if an instance exists, if it does not, creates one
     * @param context context
     * @return Returns an instance of the database
     */
    static ClockDB getDB(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClockDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ClockDB.class, "clock_db").build();
                }
            }

        }
        return INSTANCE;
    }

}

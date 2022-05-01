package fi.ottooks.dreamcatcherdemo.kello;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fi.ottooks.dreamcatcherdemo.Clock;

@Database(entities = {Clock.class}, version = 1, exportSchema = false)
public abstract class ClockDB extends RoomDatabase {
    public abstract ClockDAO clockDao();

    private static volatile ClockDB INSTANCE;
    private static final int THREADS = 4;
    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(THREADS);

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

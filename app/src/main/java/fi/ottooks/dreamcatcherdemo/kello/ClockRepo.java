package fi.ottooks.dreamcatcherdemo.kello;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import fi.ottooks.dreamcatcherdemo.Clock;

/**
 * The ClockRepo class for Dream catcher
 *     Used to do the operations which were earlier set in ClockDAO class
 */

public class ClockRepo {

    private final LiveData<List<Clock>> clockLiveData;
    private final ClockDAO dao;

    /**
     * Used to get the database
     * @param application application
     */
    public ClockRepo(Application application) {

        final ClockDB db = ClockDB.getDB(application);
        dao = db.clockDao();
        clockLiveData = dao.getClocks();

    }

    /**
     * Used upon creating a new alarm, inserting it into the database
     * @param clock clock
     */
    public void insert(Clock clock) {

        ClockDB.dbWriteExecutor.execute(() -> {

            //dao.deleteAll();
            dao.insert(clock);

        });
    }

    /**
     * Used to update already existing objects in the database
     * @param clock
     */
    public void update(Clock clock) {

        ClockDB.dbWriteExecutor.execute(() -> {

            dao.update(clock);

        });
    }

    /**
     * Used to get the list of current alarms in the database
     * @return a list of the alarms in the database
     */
    public LiveData<List<Clock>> getClockLiveData() {
        return clockLiveData;
    }

}

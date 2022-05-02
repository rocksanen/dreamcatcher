package fi.ottooks.dreamcatcherdemo.kello;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fi.ottooks.dreamcatcherdemo.Clock;

import fi.ottooks.dreamcatcherdemo.kello.ClockDAO;
import fi.ottooks.dreamcatcherdemo.kello.ClockDB;

public class ClockRepo {

    private LiveData<List<Clock>> clockLiveData;
    private ClockDAO dao;

    public ClockRepo(Application application) {
        ClockDB db = ClockDB.getDB(application);
        dao = db.clockDao();
        clockLiveData = dao.getClocks();
    }

    public void insert(Clock clock) {
        ClockDB.dbWriteExecutor.execute(() -> {
            //dao.deleteAll();
            dao.insert(clock);
        });
    }

    public void update(Clock clock) {
        ClockDB.dbWriteExecutor.execute(() -> {
            dao.update(clock);
        });
    }

    public LiveData<List<Clock>> getClockLiveData() {
        return clockLiveData;
    }

}

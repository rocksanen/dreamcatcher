package fi.ottooks.dreamcatcherdemo.view;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import fi.ottooks.dreamcatcherdemo.Clock;
import fi.ottooks.dreamcatcherdemo.kello.ClockRepo;

/**
 * The listViewModel class for Dream catcher
 * This is used to get the currently available alarms using ClockRepo class
 * @author Samu
 *
 *
 */
public class listViewModel extends AndroidViewModel {

    private final ClockRepo repo;
    private final LiveData<List<Clock>> clockLiveData;

    /**
     * gets the current repository and all the alarms from it
     * @param application application
     */
    public listViewModel(@NonNull Application application)
    {

        super(application);
        repo = new ClockRepo(application);
        clockLiveData = repo.getClockLiveData();

    }

    public void update(Clock clock) {
        repo.update(clock);
    }

    /**
     *
     * @return returns the current alarms
     */
    public LiveData<List<Clock>> getClockLiveData() {
        return clockLiveData;
    }
}

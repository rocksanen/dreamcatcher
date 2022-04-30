package fi.ottooks.dreamcatcherdemo.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


import fi.ottooks.dreamcatcherdemo.Clock;
import fi.ottooks.dreamcatcherdemo.kello.ClockRepo;

public class listViewModel extends AndroidViewModel {

    private ClockRepo repo;
    private LiveData<List<Clock>> clockLiveData;

    public listViewModel(@NonNull Application application) {
        super(application);

        repo = new ClockRepo(application);
        clockLiveData = repo.getClockLiveData();

    }

    public void update(Clock clock) {
        repo.update(clock);
    }
    public LiveData<List<Clock>> getClockLiveData() {
        return clockLiveData;
    }
}

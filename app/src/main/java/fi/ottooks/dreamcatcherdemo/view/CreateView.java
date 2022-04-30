package fi.ottooks.dreamcatcherdemo.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import fi.ottooks.dreamcatcherdemo.kello.Clock;
import fi.ottooks.dreamcatcherdemo.kello.ClockRepo;

public class CreateView extends AndroidViewModel {

    private ClockRepo repo;
    public CreateView(@NonNull Application application) {
        super(application);
        repo = new ClockRepo(application);
    }

    public void insert(Clock clock) {
        repo.insert(clock);
    }
}

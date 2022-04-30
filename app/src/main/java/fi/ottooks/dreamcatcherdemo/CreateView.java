package fi.ottooks.dreamcatcherdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

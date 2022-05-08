package fi.ottooks.dreamcatcherdemo.view;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import fi.ottooks.dreamcatcherdemo.Clock;
import fi.ottooks.dreamcatcherdemo.kello.ClockRepo;

/**
 * The CreateView class for Dream catcher
 *      This class is used to insert new alarms into the database
 * @author Samu
 *
 */

public class CreateView extends AndroidViewModel {

    private final ClockRepo repo;

    public CreateView(@NonNull Application application) {

        super(application);
        repo = new ClockRepo(application);

    }

    public void insert(Clock clock) {
        repo.insert(clock);
    }
}

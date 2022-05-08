package fi.ottooks.dreamcatcherdemo.fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import fi.ottooks.dreamcatcherdemo.Clock;
import fi.ottooks.dreamcatcherdemo.view.SetAlarmView;
import fi.ottooks.dreamcatcherdemo.kello.clockListener;
import fi.ottooks.dreamcatcherdemo.view.listViewModel;
import fi.ottooks.dreamcatcherdemo.view.recycleViewAdapter;

/**
 * The MainView class for Dream catcher
 *      This class is used to set the view and listeners on the mainview fragment
 * @author Samu
 *
 */

public class MainView extends Fragment implements clockListener {

    Button btn;

    private recycleViewAdapter recycleViewAdapter;
    private listViewModel listViewModel;

    /**
     * Here we set the view onCreateView, we set the alarms to show up in a recyclerview
     * We use recycleViewAdapter and listViewModel classes to achieve this
     * There are listeners set for button to add a new alarm and a switch to toggle alarms on/off
     *
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return returns the built view
     */

    @Nullable
    @Override
    public View onCreateView
    (

        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState

    )

    {

        recycleViewAdapter = new recycleViewAdapter(this);
        listViewModel = ViewModelProviders.of(this).get(listViewModel.class);
        listViewModel.getClockLiveData().observe((LifecycleOwner) this, clocks -> {

            if (clocks != null) {

                recycleViewAdapter.setClocks(clocks);

            }
        });

        final View rootView =
        inflater.inflate(fi.ottooks.dreamcatcherdemo.R.layout.page_1, container,false);

        final RecyclerView clocksView = rootView.findViewById(fi.ottooks.dreamcatcherdemo.R.id.heratykset_list);
        clocksView.setLayoutManager(new LinearLayoutManager(getContext()));
        clocksView.setAdapter(recycleViewAdapter);

        btn = (Button) rootView.findViewById(fi.ottooks.dreamcatcherdemo.R.id.uusi_heratys);
        btn.setOnClickListener(arg0 -> startActivity(new Intent(getActivity(), SetAlarmView.class)));

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onToggle(Clock clock) {

        if (clock.isStarted()) {

            clock.cancel(getContext());
            listViewModel.update(clock);

        } else {

            clock.set(getContext());
            listViewModel.update(clock);

        }
    }
}

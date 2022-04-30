package fi.ottooks.dreamcatcherdemo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.ottooks.dreamcatcherdemo.kello.Clock;
import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.kello.clockListener;
import fi.ottooks.dreamcatcherdemo.view.SetAlarmView;
import fi.ottooks.dreamcatcherdemo.view.listViewModel;
import fi.ottooks.dreamcatcherdemo.view.recycleViewAdapter;

public class MainView extends Fragment implements clockListener {

    Button btn;

    private recycleViewAdapter recycleViewAdapter;
    private listViewModel listViewModel;
    private RecyclerView clocksView;

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
        listViewModel.getClockLiveData().observe(this, new Observer<List<Clock>>() {
            @Override
            public void onChanged(List<Clock> clocks) {
                if (clocks != null) {
                    recycleViewAdapter.setClocks(clocks);
                }
            }
        });

        View rootView =
                inflater.inflate(R.layout.page_1, container,false);


        clocksView = rootView.findViewById(R.id.heratykset_list);
        clocksView.setLayoutManager(new LinearLayoutManager(getContext()));
        clocksView.setAdapter(recycleViewAdapter);





        btn = (Button) rootView.findViewById(R.id.uusi_heratys);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                startActivity(new Intent(getActivity(), SetAlarmView.class));
                Log.d("namu","pelle");

            }
        });

        return rootView;
    }

    @Override
    public void onToggle(Clock clock) {

            clock.set(getContext());
            listViewModel.update(clock);

    }
}

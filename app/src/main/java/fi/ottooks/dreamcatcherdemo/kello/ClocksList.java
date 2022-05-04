package fi.ottooks.dreamcatcherdemo.kello;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.ottooks.dreamcatcherdemo.Clock;
//import fi.ottooks.dreamcatcherdemo.R;

import fi.ottooks.dreamcatcherdemo.view.listViewModel;
import fi.ottooks.dreamcatcherdemo.view.recycleViewAdapter;

public class ClocksList extends Fragment implements clockListener {

    private fi.ottooks.dreamcatcherdemo.view.recycleViewAdapter recycleViewAdapter;
    private fi.ottooks.dreamcatcherdemo.view.listViewModel listViewModel;
    private RecyclerView clocksView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(fi.ottooks.dreamcatcherdemo.R.layout.page_1, container, false);

        clocksView = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.heratykset_list);
        clocksView.setLayoutManager(new LinearLayoutManager(getContext()));
        clocksView.setAdapter(recycleViewAdapter);

        return view;

    }




    @Override
    public void onToggle(Clock clock) {
        clock.set(getContext());

    }
}

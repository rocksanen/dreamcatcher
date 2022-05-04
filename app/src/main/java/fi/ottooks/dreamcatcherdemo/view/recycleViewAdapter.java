package fi.ottooks.dreamcatcherdemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fi.ottooks.dreamcatcherdemo.Clock;
import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.ViewHolder;

import fi.ottooks.dreamcatcherdemo.kello.clockListener;

public class recycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Clock> clocks;
    private clockListener listener;

    public recycleViewAdapter(clockListener listener) {
        this.clocks = new ArrayList<Clock>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View clockView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_alarm, parent, false);
        return new ViewHolder(clockView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Clock clock = clocks.get(position);
        holder.bind(clock, listener);
    }



    @Override
    public int getItemCount() {
        return clocks.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.aSwitch.setOnCheckedChangeListener(null);
    }

    public void setClocks(List<Clock> clocks) {
        this.clocks = clocks;
        notifyDataSetChanged();
    }

    public void disableClocks(List<Clock> clocks) {
        this.clocks = clocks;
        int i = 0;
        while (i < clocks.size()) {
            clocks.get(i).cancel();
            i++;
        }

    }
}

package fi.ottooks.dreamcatcherdemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fi.ottooks.dreamcatcherdemo.Clock;
//import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.ViewHolder;

import fi.ottooks.dreamcatcherdemo.kello.clockListener;

/**
 * The recycleViewAdapter class for Dream catcher
 *      This class is used to set the view and listeners on the mainview fragment
 *
 */

public class recycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Clock> clocks;
    private clockListener listener;


    public recycleViewAdapter(clockListener listener) {
        this.clocks = new ArrayList<Clock>();
        this.listener = listener;
    }

    /**
     * This is used to set a new viewholder to represent an item (a single alarm)
     * @param parent parent
     * @param viewType viewType
     * @return returns a viewholder containing the view of a single alarm
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View clockView = LayoutInflater.from(parent.getContext()).inflate(fi.ottooks.dreamcatcherdemo.R.layout.one_alarm, parent, false);
        return new ViewHolder(clockView);
    }

    /**
     * Used to get position of the alarm and binding it to the specific position
     * @param holder holder
     * @param position position of a single alarm
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Clock clock = clocks.get(position);
        holder.bind(clock, listener);
    }

    /**
     * Used to get the amount of alarms
     * @return returns the total amount of alarms
     */
    @Override
    public int getItemCount() {
        return clocks.size();
    }

    /**
     * gets called when the adapter has been recycled
     * @param holder holder
     */
    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.aSwitch.setOnCheckedChangeListener(null);
    }

    /**
     * Notifies any observers that the data has been changed
     * In our case on mainview class, onCreate method
     * @param clocks clocks
     */
    public void setClocks(List<Clock> clocks) {
        this.clocks = clocks;
        notifyDataSetChanged();
    }

}

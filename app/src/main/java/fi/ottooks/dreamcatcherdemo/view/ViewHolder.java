package fi.ottooks.dreamcatcherdemo.view;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.kello.clockListener;

/**
 * The ViewHolder class for Dream catcher
 *      This class is used to set the elements of each alarm to the single viewholder in recyclerview
 * @author Samu
 *
 *
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView time;
    private final TextView title;

    public Switch aSwitch;

    /**
     * Here we get all of the elements that we want to modify
     * @param itemView itemView
     */
    public ViewHolder(@NonNull View itemView) {

        super(itemView);

        time = itemView.findViewById(R.id.test_1);
        title = itemView.findViewById(R.id.test_2);
        aSwitch = itemView.findViewById(R.id.heratys_paalle);

    }

    /**
     * Here we give values to all the elements we are changing and set a listener for the on/off switch
     * @param clock clock
     * @param listener listener
     */
    public void bind(fi.ottooks.dreamcatcherdemo.Clock clock, clockListener listener) {

        @SuppressLint("DefaultLocale") String alarmTime = String.format("%02d:%02d", clock.getHour(), clock.getMin());
        time.setText(alarmTime);
        title.setText(clock.getTitle());
        aSwitch.setChecked(clock.isStarted());

        aSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> listener.onToggle(clock));

    }



}

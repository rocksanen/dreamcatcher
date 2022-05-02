package fi.ottooks.dreamcatcherdemo;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import fi.ottooks.dreamcatcherdemo.kello.clockListener;


public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView time;
    private TextView title;

    public Switch aSwitch;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        time = itemView.findViewById(R.id.test_1);
        title = itemView.findViewById(R.id.test_2);
        aSwitch = itemView.findViewById(R.id.heratys_paalle);

    }

    public void bind(fi.ottooks.dreamcatcherdemo.Clock clock, clockListener listener) {
        String alarmTime = String.format("%02d:%02d", clock.getHour(), clock.getMin());
        time.setText(alarmTime);
        title.setText(clock.getTitle());
        aSwitch.setChecked(clock.isStarted());

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                listener.onToggle(clock);
            }
        });

    }



}

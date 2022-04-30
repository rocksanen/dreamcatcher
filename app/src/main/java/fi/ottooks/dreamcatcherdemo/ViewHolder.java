package fi.ottooks.dreamcatcherdemo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fi.ottooks.dreamcatcherdemo.kello.Clock;
import fi.ottooks.dreamcatcherdemo.kello.clockListener;


public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView time;
    private TextView title;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        time = itemView.findViewById(R.id.test_1);
        title = itemView.findViewById(R.id.test_2);

    }

    public void bind(Clock clock, clockListener listener) {
        String alarmTime = String.format("%02d:%02d", clock.getHour(), clock.getMin());
        time.setText(alarmTime);
        title.setText(clock.getTitle());
    }

}

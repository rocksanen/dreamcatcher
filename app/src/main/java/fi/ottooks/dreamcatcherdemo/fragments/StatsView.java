package fi.ottooks.dreamcatcherdemo.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.collection.LLRBNode;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.StatsSorting;
import fi.ottooks.dreamcatcherdemo.UseSharedPreferences;
import fi.ottooks.dreamcatcherdemo.UserInputs;


public class StatsView extends Fragment {

    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 24;
    private static final int MIN_Y_VALUE = 0;
    private static final int GROUPS = 2;
    private static final String SLEEP_TIME = "Sleep time (h)";
    private static final String MOOD = "Mood (max 5.0)";
    private static final float BAR_SPACE = 0.08f;
    private static final float BAR_WIDTH = 0.3f;
    private BarChart chart;
    private List<UserInputs> userInputsList;
    private TextView uniKeskiArvot;
    private TextView moodKeskiArvot;
    private TextView parasUniaika;
    private StatsSorting statsSorting;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView

    (
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    )

    {

        View view = inflater.inflate(R.layout.page_2, container, false);
        userInputsList = new UseSharedPreferences().getListFromPreferences();

        statsSorting = new StatsSorting(userInputsList);

        chart = view.findViewById(R.id.fragment_verticalbarchart_chart);

        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);

        uniKeskiArvot = view.findViewById(R.id.nukkumisKeskiArvo);
        moodKeskiArvot = view.findViewById(R.id.moodKeskiArvo);
        parasUniaika = view.findViewById(R.id.parasUniMaara);

        textSetter();

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void textSetter() {

        uniKeskiArvot.setTextColor(ColorTemplate.rgb("#FFFAFA"));
        uniKeskiArvot.setText(statsSorting.getUniKeskiArvot());

        moodKeskiArvot.setTextColor(ColorTemplate.rgb("#FFFAFA"));
        moodKeskiArvot.setText(statsSorting.getMoodKeskiArvot());

        parasUniaika.setTextColor(ColorTemplate.rgb("#FFFAFA"));
        parasUniaika.setText(statsSorting.getParasKokonaisAikaNukkua());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void configureChartAppearance() {

        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(true);
        chart.setGridBackgroundColor(ColorTemplate.colorWithAlpha(0,150));

        chart.setDrawBorders(true);
        chart.setBorderWidth(2f);
        chart.setNoDataText("Ei vielä dataa saatavilla");
        chart.getDescription().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);

        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(MAX_X_VALUE);
        chart.getDescription().setEnabled(false);

        List<String> datesToStatsView = new ArrayList<>();

        for(int i = 0; i < userInputsList.size(); i++) {

            datesToStatsView.add(userInputsList.get(i).getDate());

        }

        //setValueFormatter toimii hyvin erikoisesti joten joutui tällaisen purkkakoodin laittamaan...
        datesToStatsView.add(LocalDate.now().toString());
        datesToStatsView.add(LocalDate.now().toString());

            xAxis.setValueFormatter(new ValueFormatter() {

                public String getFormattedValue(float value) {

                        return datesToStatsView.
                        get(datesToStatsView.size() - 9 + (int)value);

                }
            });

            leftAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return (int)value + "h";
                }
            });

            xAxis.setTextColor(ColorTemplate.rgb("#FFFAFA"));
            leftAxis.setTextColor(ColorTemplate.rgb("#FFFAFA"));
            xAxis.setTextSize(10f);

        Legend legend = chart.getLegend();
        legend.setTextSize(17f);
        legend.setTextColor(ColorTemplate.rgb("#FFFAFA"));


    }

    private BarData createChartData() {

        ArrayList<BarEntry> sleepTime = new ArrayList<>();
        ArrayList<BarEntry> mood = new ArrayList<>();

        for (int i = 0; i < MAX_X_VALUE; i++) {

            sleepTime.add(new BarEntry(i, (float)
            userInputsList.get(userInputsList.size() - 7 + i).getSleepTime()));

            mood.add(new BarEntry(i,
            userInputsList.get(userInputsList.size() - 7 + i).getMoodValue()));
        }

        BarDataSet set1 = new BarDataSet(sleepTime, SLEEP_TIME);
        BarDataSet set2 = new BarDataSet(mood, MOOD);

        set1.setColor(ColorTemplate.getHoloBlue());
        set2.setColor(ColorTemplate.MATERIAL_COLORS[1]);

        set1.setFormSize(10f);
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setValueTextSize(12f);

        set2.setFormSize(10f);
        set2.setValueTextColor(ColorTemplate.rgb("#FFFAFA"));
        set2.setValueTextSize(12f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        return new BarData(dataSets);
    }

    private void prepareChartData(BarData data) {
        chart.setData(data);

        chart.getBarData().setBarWidth(BAR_WIDTH);

        float groupSpace = 1f - ((BAR_SPACE + BAR_WIDTH) * GROUPS);
        chart.groupBars(0, groupSpace, BAR_SPACE);

        chart.invalidate();
    }

}

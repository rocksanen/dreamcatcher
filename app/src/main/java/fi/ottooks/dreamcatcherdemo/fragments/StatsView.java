package fi.ottooks.dreamcatcherdemo.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import fi.ottooks.dreamcatcherdemo.StatsSorting;
import fi.ottooks.dreamcatcherdemo.UseSharedPreferences;
import fi.ottooks.dreamcatcherdemo.UserInputs;


/**
 * This class show the statistics to the statsview
 */
public class StatsView extends Fragment {

    private static final int MAX_X_VALUE = 7;
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

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

        final View view = inflater.inflate(fi.ottooks.dreamcatcherdemo.R.layout.page_2, container, false);

        userInputsList = new UseSharedPreferences().getListFromPreferences();
        statsSorting = new StatsSorting(userInputsList);

        chart = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.fragment_verticalbarchart_chart);

        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);

        uniKeskiArvot = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.nukkumisKeskiArvo);
        moodKeskiArvot = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.moodKeskiArvo);
        parasUniaika = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.parasUniMaara);

        textSetter();

        return view;
    }

    /**
     * Set the text to textviews
     */
    @SuppressLint("SetTextI18n")
    private void textSetter() {

        uniKeskiArvot.setTextColor(ColorTemplate.rgb("#FFFAFA"));
        uniKeskiArvot.setText(statsSorting.getUniKeskiArvot());

        moodKeskiArvot.setTextColor(ColorTemplate.rgb("#FFFAFA"));
        moodKeskiArvot.setText(statsSorting.getMoodKeskiArvot());

        parasUniaika.setTextColor(ColorTemplate.rgb("#FFFAFA"));
        parasUniaika.setText(statsSorting.getParasKokonaisAikaNukkua());

    }

    /**
     * Configure chart to be drawn
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void configureChartAppearance() {

        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(true);
        chart.setGridBackgroundColor(ColorTemplate.colorWithAlpha(0,150));
        chart.setDrawBorders(true);
        chart.setBorderWidth(2f);
        chart.setNoDataText("Ei vielä dataa saatavilla");;
        chart.getDescription().setEnabled(false);

        final XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        chart.getXAxis().setAxisMaximum(MAX_X_VALUE);

        final YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);


        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setAxisMinimum(0);
        chart.getDescription().setEnabled(false);

        ArrayList<String> dates = new ArrayList<>();

        for(UserInputs user: userInputsList) {

            if(dates.size() < 7) {

                dates.add(user.getDate());

            }else break;
        }

            xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));

            leftAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return (int)value + "h";
                }
            });

            xAxis.setTextColor(ColorTemplate.rgb("#FFFAFA"));
            leftAxis.setTextColor(ColorTemplate.rgb("#FFFAFA"));
            xAxis.setTextSize(10f);

        final Legend legend = chart.getLegend();
        legend.setTextSize(17f);
        legend.setTextColor(ColorTemplate.rgb("#FFFAFA"));

    }


    /**
     * Ads values to the chart
     * @return
     */
    private BarData createChartData() {

        ArrayList<BarEntry> sleepTime = new ArrayList<>();
        ArrayList<BarEntry> mood = new ArrayList<>();

        if(!userInputsList.isEmpty()) {

            if(userInputsList.size() >= 7) {

                Collections.reverse(userInputsList);

                for (int i = 0; i < MAX_X_VALUE; i++) {

                    sleepTime.add(new BarEntry(i,(float)
                            userInputsList.get(i).getSleepTime()));

                    mood.add(new BarEntry(i,(int)
                            userInputsList.get(i).getMoodValue()));
                }

            }else {

                Collections.reverse(userInputsList);

                for (int i = 0; i < userInputsList.size(); i++) {

                    sleepTime.add(new BarEntry(i,(float)
                            userInputsList.get( i).getSleepTime()));

                    mood.add(new BarEntry(i,(int)
                            userInputsList.get(i).getMoodValue()));
                }
            }
        }

        final BarDataSet set1 = new BarDataSet(sleepTime, SLEEP_TIME);
        final BarDataSet set2 = new BarDataSet(mood, MOOD);

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

    /**
     * Draws the chart data
     * @param data
     */
    private void prepareChartData(BarData data) {

        chart.setData(data);
        chart.getBarData().setBarWidth(BAR_WIDTH);
        float groupSpace = 1f - ((BAR_SPACE + BAR_WIDTH) * GROUPS);
        chart.groupBars(0, groupSpace, BAR_SPACE);
        chart.invalidate();

    }
}

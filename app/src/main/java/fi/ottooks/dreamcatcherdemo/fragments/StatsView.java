package fi.ottooks.dreamcatcherdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.firestore.util.Util;


import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fi.ottooks.dreamcatcherdemo.MainActivity;
import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.UseSharedPreferences;
import fi.ottooks.dreamcatcherdemo.UserInputs;


public class StatsView extends Fragment {

    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 24;
    private static final int MIN_Y_VALUE = 1;
    private static final int GROUPS = 2;
    private static final String GROUP_2_LABEL = "Sleep time";
    private static final String GROUP_3_LABEL = "Mood";
    private static final float BAR_SPACE = 0.08f;
    private static final float BAR_WIDTH = 0.3f;
    private static final String[] DAYS = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
    private BarChart chart;
    private List<UserInputs> userInputsList;

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

        chart = view.findViewById(R.id.fragment_verticalbarchart_chart);

        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);



        return view;
    }

    private void configureChartAppearance() {

        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
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


        xAxis.setValueFormatter(new ValueFormatter() {

            public String getFormattedValue(float value) {

                return "saturday";
            }
        });


        Legend legend = chart.getLegend();
        legend.setTextSize(17f);
        legend.setTextColor(ColorTemplate.rgb("#FFFAFA"));


    }

    private BarData createChartData() {

        ArrayList<BarEntry> sleepTime = new ArrayList<>();
        ArrayList<BarEntry> mood = new ArrayList<>();





        for (int i = 0; i < MAX_X_VALUE; i++) {

            sleepTime.add(new BarEntry(i, (float) userInputsList.get(i).getSleepTime()));
            mood.add(new BarEntry(i, userInputsList.get(i).getMoodValue()));
        }


        BarDataSet set1 = new BarDataSet(sleepTime, GROUP_2_LABEL);
        BarDataSet set2 = new BarDataSet(mood, GROUP_3_LABEL);


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

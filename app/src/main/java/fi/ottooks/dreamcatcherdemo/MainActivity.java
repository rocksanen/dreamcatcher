package fi.ottooks.dreamcatcherdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fi.ottooks.dreamcatcherdemo.fragments.MainView;
import fi.ottooks.dreamcatcherdemo.fragments.StatsView;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private androidx.viewpager.widget.PagerAdapter pagerAdapter;
    private StatsSorting statsSorting;
    private ArrayList<StatsSorting> testiLista = new ArrayList<>();
    private static Context contextOfApplication;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSliderAdapter();
        contextOfApplication = this;
        testSorting();
        Firebase firebase = new Firebase(new StatsSorting(10.00, 08.00, 14.00, 4));


        //OTTO!!!!!!!!!!!!!!!!!!!!!! MITEN PÄÄSEE NAPPIIN KÄSIKSI
        /*
        Button btn = pager.findViewById(R.id.uusi_heratys);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getBaseContext(), SetAlarmView.class));

                Log.i("otto","kyllä se Samu siitä");
                //setContentView(R.layout.activity_set_alarm_view);

            }
        });

*/
    }

    public static Context getContextOfApplication(){

        return contextOfApplication;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testSorting() {




    }


    private void testStats() {





    }


    private void setSliderAdapter() {

        List<Fragment> list = new ArrayList<>();
        list.add(new MainView());
        list.add(new StatsView());


        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);


    }


}
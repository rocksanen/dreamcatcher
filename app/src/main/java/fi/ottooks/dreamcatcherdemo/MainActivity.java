package fi.ottooks.dreamcatcherdemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
    private ArrayList<UserInputs> testiLista = new ArrayList<>();

    //luodaan context muuttuja
    private static Context contextOfApplication;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase firebase = new Firebase(new StatsSorting(10.00, 08.00, 14.00, 4));


        setSliderAdapter();

        //alustetaan se
        contextOfApplication = this;

        testSorting();


    }


    //Luodaan sille getter metodi
    public static Context getContextOfApplication(){

        return contextOfApplication;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testSorting() {

        testiLista.add(new UserInputs(LocalDate.now(),10.00, 08.00, 14.00, 4));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(1),10.00, 08.00, 14.00, 2));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(2),10.00, 08.00, 14.00, 1));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(3),10.00, 08.00, 14.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(4),.00, 08.00, 14.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(5),10.00, 08.00, 14.00, 5));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(6),10.00, 08.00, 14.00, 5));

        //testStats();


    }


    private void testStats() {

        Collections.sort(testiLista);
        Collections.reverse(testiLista);


        for(UserInputs user: testiLista) {

            Log.d("otto", Integer.toString(user.getMoodValue()));


        }



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
package fi.ottooks.dreamcatcherdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<UserInputs> testiLista = new ArrayList<>();

    //luodaan context muuttuja
    private static Context contextOfApplication;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSliderAdapter();

        //alustetaan se
        contextOfApplication = this;


        // Tällä testataan listoja ja toimintoja, voi kommentoida pois päältä omia testejään varten!!!!!!!!!!!!!
        //testSorting();


    }


    //Luodaan sille getter metodi
    public static Context getContextOfApplication(){

        return contextOfApplication;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testSorting() {

        testiLista.add(new UserInputs(LocalDate.now().plusDays(1),10.00, 03.00, 14.00, 4));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(2),10.00, 04.00, 14.00, 2));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(3),10.00, 12.00, 14.00, 1));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(4),10.00, 07.00, 14.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(5),.00, 01.00, 14.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(6),10.00, 10.00, 14.00, 5));
        testiLista.add(new UserInputs(LocalDate.now().plusDays(7),10.00, 08.00, 14.00, 5));

        testStats();


    }


    private void testStats() {

        UseSharedPreferences useSharedPreferences = new UseSharedPreferences();

        List<UserInputs> userInputsList = new ArrayList<>();
        userInputsList = useSharedPreferences.getListFromPreferences();

        Collections.sort(userInputsList);
        Collections.reverse(userInputsList);


        for(UserInputs user: userInputsList) {

            Log.d("otto", user.toString());


        }

        Log.d("otto",Integer.toString(userInputsList.size()));



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
package fi.ottooks.dreamcatcherdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.SlidePagerAdapter;
import fi.ottooks.dreamcatcherdemo.StatsSorting;
import fi.ottooks.dreamcatcherdemo.UseSharedPreferences;
import fi.ottooks.dreamcatcherdemo.UserInputs;
import fi.ottooks.dreamcatcherdemo.fragments.MainView;
import fi.ottooks.dreamcatcherdemo.fragments.StatsView;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private androidx.viewpager.widget.PagerAdapter pagerAdapter;
    private StatsSorting statsSorting;
    private ArrayList<UserInputs> testiLista = new ArrayList<>();

    //luodaan context muuttuja

    /**
     * Create context variable.
     */

    private static Context contextOfApplication;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSliderAdapter();

        /**
         * Initialize the context variable.
         */
        contextOfApplication = this;




        //UseSharedPreferences useSharedPreferences = new UseSharedPreferences();
        //useSharedPreferences.clearData();




        // Tällä testataan listoja ja toimintoja, voi kommentoida pois päältä omia testejään varten!!!!!!!!!!!!!
        //testSorting();


    }

    /**
     * Create getter method to call the context variable.
     * @return return the context variable which refers to application's context.
     */
    //Luodaan sille getter metodi
    public static Context getContextOfApplication(){

        return contextOfApplication;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testSorting() {

        testiLista.add(new UserInputs(LocalDate.now().minusDays(20),20.00, 04.00, 06.00, 2));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(19),21.00, 05.00, 08.00, 4));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(18),20.00, 09.00, 13.00, 1));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(17),22.00, 07.00, 9.00, 4));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(16),01.00, 08.00, 07.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(15),02.00, 04.00, 06.00, 2));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(14),21.00, 07.00, 10.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(13),22.00, 06.00, 08.00, 5));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(12),21.00, 04.00, 07.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(11),23.00, 05.00, 06.00, 2));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(10),20.00, 07.00, 11.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(9),22.00, 06.00, 08.00, 5));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(8),02.00, 10.00, 08.00, 5));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(7),21.00, 08.00, 11.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(6),22.00, 03.00, 05.00, 1));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(5),21.00, 04.00, 07.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(4),23.00, 09.00, 10.00, 4));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(3),20.00, 07.00, 11.00, 2));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(2),01.00, 08.00, 07.00, 3));
        testiLista.add(new UserInputs(LocalDate.now().minusDays(1),02.00, 10.00, 08.00, 5));
        testiLista.add(new UserInputs(LocalDate.now(),21.00, 08.00, 11.00, 3));

        //testStats();


    }


    private void testStats() {

        UseSharedPreferences useSharedPreferences = new UseSharedPreferences();

        List<UserInputs> userInputsList = new ArrayList<>();
        userInputsList = useSharedPreferences.getListFromPreferences();

        Collections.sort(userInputsList);
        Collections.reverse(userInputsList);


        for(UserInputs user: userInputsList) {

            Log.d("otto", user.getDate().toString());


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
        tabLayout.setTabTextColors(ColorStateList.valueOf(ColorTemplate.rgb("#ffffff")));

    }

}
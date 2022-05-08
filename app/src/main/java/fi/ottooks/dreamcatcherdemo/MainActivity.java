package fi.ottooks.dreamcatcherdemo;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fi.ottooks.dreamcatcherdemo.fragments.MainView;
import fi.ottooks.dreamcatcherdemo.fragments.StatsView;
import fi.ottooks.dreamcatcherdemo.fragments.UserAgeQuestion;


public class MainActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(fi.ottooks.dreamcatcherdemo.R.layout.activity_main);

        setSliderAdapter();


        // Opettajille tiedoksi!!!! Tällä metodilla voitte halutessanne lisätä testi dataa. -->
        //testSorting();

        //Opettajille tiedoksi!!!! Tällä voitte halutessanne tyhjentää datan sharedpreferenceista. -->
        //UseSharedPreferences useSharedPreferences = new UseSharedPreferences();
        //useSharedPreferences.clearData();

    }


    public void onResume() {super.onResume();}

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void testSorting() {

        final String eka =    "1651602664826";
        final String toka =   "5651602664826";
        final String kolmas = "2651602664826";
        final String neljas = "8651602664826";
        final String viides = "2651602664826";
        final String kuudes = "6651602664826";
        final String seits = "3651602664826";
        final String kahd = "8651602664826";
        final String yhd = "2651602664826";
        final String kym = "4651602664826";
        final String yskt = "1651602664826";
        final String kakst = "5651602664826";
        final String kolmst = "1651602664826";
        final String neljst = "6651602664826";

        UserInputs user1 = new UserInputs(LocalDate.now().minusDays(7),Long.parseLong(eka),Long.parseLong(toka),2);
        UserInputs user2 = new UserInputs(LocalDate.now().minusDays(6),Long.parseLong(kolmas),Long.parseLong(neljas),5);
        UserInputs user3 = new UserInputs(LocalDate.now().minusDays(5),Long.parseLong(viides),Long.parseLong(kuudes),4);
        UserInputs user4 = new UserInputs(LocalDate.now().minusDays(4),Long.parseLong(seits),Long.parseLong(kahd),5);
        UserInputs user5 = new UserInputs(LocalDate.now().minusDays(3),Long.parseLong(yhd),Long.parseLong(kym),2);
        UserInputs user6 = new UserInputs(LocalDate.now().minusDays(2),Long.parseLong(yskt),Long.parseLong(kakst),3);
        UserInputs user7 = new UserInputs(LocalDate.now().minusDays(1),Long.parseLong(kolmst),Long.parseLong(neljst),4);

    }


    /**
     * Adds the fragments to list
     * Sets up pagerview
     * Sets up the adapter to pagerview
     * Sets up the Tablayout
     */
    private void setSliderAdapter() {

        List<Fragment> list = new ArrayList<>();
        list.add(new MainView());
        list.add(new StatsView());
        list.add(new UserAgeQuestion());

        final ViewPager pager = findViewById(R.id.pager);
        androidx.viewpager.widget.PagerAdapter pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(ColorTemplate.rgb("#ffffff")));

    }
}
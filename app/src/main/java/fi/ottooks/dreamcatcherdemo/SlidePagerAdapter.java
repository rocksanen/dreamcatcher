package fi.ottooks.dreamcatcherdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    private final List fragmentList;
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] frags = {R.layout.page_1,R.layout.page_2};

    public SlidePagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.context =  MainActivity.getContextOfApplication();
    }

    public Object instatiateItem(ViewGroup container, int position) {
        Log.d("Namu","setä");
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.page_1,null);
        Button button = view.findViewById(R.id.uusi_heratys);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) { return (Fragment) fragmentList.get(position); }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0) {

            return "CLOCK ";

        }else if(position == 1) {

            return "STATS ";

        }else{

            return null;

        }
    }


}

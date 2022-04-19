package fi.ottooks.dreamcatcherdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    private final List fragmentList;

    public SlidePagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
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

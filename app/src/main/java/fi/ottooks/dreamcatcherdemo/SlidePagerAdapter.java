package fi.ottooks.dreamcatcherdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * This class is used to manage fragments to be shown on pageviewer
 * @author Otto
 * https://developer.android.com/guide/fragments/create
 */
public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList;

    /**
     * Gets the fragments to be processed
     * @param fm
     * @param fragmentList
     */
    public SlidePagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    /**
     * Gets selected item from fragmetlist
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) { return (Fragment) fragmentList.get(position); }

    /**
     * Returns the size of fragmentlist
     * @return
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * Sets the tablayout header
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0) {

            return "CLOCK ";

        }else if(position == 1) {

            return "STATS ";

        }else if(position == 2){

            return "INFO";

        }else {

            return null;

        }
    }
}

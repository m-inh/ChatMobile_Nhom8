package client.nhom8.com.avatar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class MyViewpagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArr;
    private ArrayList<String> titleArr;

    public MyViewpagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArr, ArrayList<String> titleArr) {
        super(fm);
        this.fragmentArr = fragmentArr;
        this.titleArr = titleArr;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArr.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArr.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArr.get(position);
    }
}

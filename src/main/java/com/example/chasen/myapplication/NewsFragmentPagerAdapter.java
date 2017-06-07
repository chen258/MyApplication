package com.example.chasen.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chasen on 2017/5/25.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_frag;
    private List<String> list_tabName;

    public NewsFragmentPagerAdapter(FragmentManager fm, List<Fragment> list_frag, List<String> list_tabName) {
        super(fm);
        this.list_frag = list_frag;
        this.list_tabName = list_tabName;
    }

    @Override
    public Fragment getItem(int position) {
        return list_frag.get(position);
    }

    @Override
    public int getCount() {
        return list_frag.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_tabName.get(position);
    }

}

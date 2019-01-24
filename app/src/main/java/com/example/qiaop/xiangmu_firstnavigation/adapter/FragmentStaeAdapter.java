package com.example.qiaop.xiangmu_firstnavigation.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentStaeAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> strings;
    public FragmentStaeAdapter(FragmentManager fm,List<Fragment> fragments,List<String> strings) {
        super(fm);
        this.fragments= fragments;
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}

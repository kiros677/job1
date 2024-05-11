package com.example.job1.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final Fragment[] fragments;

    public ViewPagerAdapter(FragmentActivity activity, Fragment[] fragments) {
        super(activity);
        this.fragments = fragments;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
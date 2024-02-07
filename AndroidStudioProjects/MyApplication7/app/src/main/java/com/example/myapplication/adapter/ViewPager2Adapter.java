package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.CallFragment;
import com.example.myapplication.fragment.ChatFragment;
import com.example.myapplication.fragment.StatusFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new ChatFragment();
        } else if (position == 1) {
            return new StatusFragment();
        } else if (position == 2) {
            return new CallFragment();
        } else {
            return new ChatFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

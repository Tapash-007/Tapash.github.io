package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class viewpager2Adapter extends FragmentStateAdapter {

    public viewpager2Adapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new DogFragment();
        } else if (position == 1) {
            return new CatFragment();
        }else {
            return new BirdFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

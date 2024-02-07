package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerMessengerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new ViewPagerMessengerAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);

    }
}
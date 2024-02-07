package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Viewpager2 extends AppCompatActivity {

    ViewPager2 pager;

    TabLayout tab;

    viewpager2Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager2);

        pager = findViewById(R.id.view);
        tab = findViewById(R.id.tab);
        adapter = new viewpager2Adapter(getSupportFragmentManager(),getLifecycle());
        pager.setAdapter(adapter);
        new TabLayoutMediator(tab, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0){
                    tab.setText("Dog");
                }else if (position == 1) {
                    tab.setText("Cat");
                }else {
                    tab.setText("Bird");
                }
            }
        }).attach();


    }

    }
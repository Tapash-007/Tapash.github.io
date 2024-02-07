package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PageActivity extends AppCompatActivity {

    ViewPager2 pager;
    TabLayout tab;

    ViewPager2Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        pager = findViewById(R.id.viewPager);
        tab = findViewById(R.id.tab);

        adapter = new ViewPager2Adapter(getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(adapter);


        new TabLayoutMediator(tab, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Chat");
                } else if (position == 1) {
                    tab.setText("Status");
                } else if (position == 2) {
                    tab.setText("Call");
                } else {
                    tab.setText("Chat");
                }
            }
        }).attach();
    }
}
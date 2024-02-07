package com.example.restaurantdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.restaurantdatabase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_navigation extends AppCompatActivity {


    BottomNavigationView btView;

    NavHostFragment navHostFragment;

    NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        btView = findViewById(R.id.bottomView);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.frag);

        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(btView,navController);


    }
}
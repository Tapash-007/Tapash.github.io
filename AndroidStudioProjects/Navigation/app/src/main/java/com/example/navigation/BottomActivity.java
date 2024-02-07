package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomActivity extends AppCompatActivity {
    BottomNavigationView bView;

    FragmentContainerView fragC;

    NavHostFragment navHostFragment;

    NavController navController;

   // NavigationBarView BottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        bView = findViewById(R.id.bnView);
        fragC = findViewById(R.id.fragmentContainer);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        assert navHostFragment != null;
        navController = navHostFragment.getNavController();



        NavigationUI.setupWithNavController(bView, navController);








        /*bView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    loadFrag(new AFragment(), false);

                } else if (id == R.id.nav_search) {
                    loadFrag(new BFragment(), false);

                } else if (id == R.id.nav_contact) {
                    loadFrag(new CFragment(), false);

                } else if (id == R.id.nav_share) {
                    loadFrag(new DFragment(), false);

                } else { //location
                    loadFrag(new EFragment(), true);

                }

                return true;
            }
        });

        bView.setSelectedItemId(R.id.nav_location);*/

        }

        /*public void loadFrag(Fragment fragment,boolean flag) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (flag)
            ft.add(R.id.container, fragment);
            else
              ft.replace(R.id.container,fragment);
            ft.commit();
        }*/

    }








package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public NavigationView navigationView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        @SuppressLint({"MissingInflated", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);


      //  actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open, R.string.nav_close);
      //  drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

//       replaceFragment(new AccountFragment());

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem Item) {
        int id = Item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);


        if (id == R.id.nav_account) {
//           Fragment fragment = new AccountFragment();
           replaceFragment(new AccountFragment());

        } else if (id == R.id.nav_setting) {
            Fragment fragment = new settingFragment();
            replaceFragment(fragment);

        } else if (id == R.id.nav_logout) {
            Fragment fragment = new logoutFragment();
            replaceFragment(fragment);


        }

return true;
    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}

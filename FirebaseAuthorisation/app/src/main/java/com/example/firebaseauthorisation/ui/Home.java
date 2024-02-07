package com.example.firebaseauthorisation.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.firebaseauthorisation.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements NavController.OnDestinationChangedListener {

    NavHostFragment navHostFragment;

    BottomNavigationView btView;

    NavController nav;

    FirebaseAuth mAuth;

    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btView = findViewById(R.id.bottomView);
        toolbar = findViewById(R.id.tool);
        mAuth = FirebaseAuth.getInstance();

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.frag);
        assert navHostFragment != null;
        nav = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(btView, nav);

        nav.addOnDestinationChangedListener(this);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.logout) {

                    Log.d(getClass().getSimpleName(), "onMenuItemClick: " +item.getTitle());

                    openDialog();
                    return true;
                }

                return false;
            }
        });
    }


    @Override
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

        if (navDestination.getId() == R.id.chat) {
            toolbar.setTitle("Chat");
            toolbar.getMenu().findItem(R.id.logout).setVisible(false);
        } else if (navDestination.getId() == R.id.user) {
            toolbar.setTitle("Users");
            toolbar.getMenu().findItem(R.id.logout).setVisible(false);
        } else if (navDestination.getId() == R.id.profile) {
            toolbar.setTitle("Profile");
            toolbar.getMenu().findItem(R.id.logout).setVisible(true);
        }
    }

    public void openDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Are you sure want to logout")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Intent intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).show();

    }

}
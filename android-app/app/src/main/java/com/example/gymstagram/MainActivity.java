package com.example.gymstagram;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gymstagram.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import utils.Utils;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationView;

    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//      Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_nav_menu);

        // Hide the BottomNavigationView on the LoginFragment
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController,
                                             @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.loginFragment) {
                    Log.d("CHECKING", "WE IN");
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    Log.d("CHECKING", "NO GOOD");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });

//      Navigation buttons
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_menu_add:

                            //Do nothing if currently on add page
                            if(navController.getCurrentDestination().getId() == R.id.addFragment){
                                return true;
                            }

                            //Clear stack inclusive up to last add
                            if(utils.isDestinationInNavStack(R.id.addFragment, navController)){
                                navController.popBackStack(R.id.addFragment, true);
                            }

                             navController.navigate(R.id.addFragment);

                            return true;

                        case R.id.nav_menu_home:

                            if(navController.getCurrentDestination().getId() == R.id.homeFeed){
                                //Scroll to top or some other functionality
                                return true;
                            }

                            //If home exists, go back to existing home screen
                            if(utils.isDestinationInNavStack(R.id.homeFeed, navController)){
                                navController.popBackStack(R.id.homeFeed, false);
                                return true;
                            }

                            //If home doesn't, create it (should never be the case)
                            navController.navigate(R.id.homeFeed);
                            return true;

                        case R.id.nav_menu_history:
                            // Handle workout/meal history button click
                            return true;

                        case R.id.nav_menu_users:
                            // Handle users button click
                            return true;

                        default:
                            return false;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
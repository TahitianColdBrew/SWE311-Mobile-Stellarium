package org.xmum.stellarium;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.xmum.stellarium.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    // my own variables below
    private BottomNavigationView bottomNavigationView;
    private  BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_education:
                    setFragment(new EducationFragment());
                    return true;
                case R.id.nav_explore:
                    setFragment(new ExploreFragment());
                    return true;
                case R.id.nav_community:
                    setFragment(new CommunityFragment());
                    return true;
                case R.id.nav_profile:
                    setFragment(new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

    private FrameLayout main_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setSupportActionBar(binding.appBarMain.toolbar);

        // add my own code below to set up for the bottom navigation bar

        // 1. initialize variable
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        main_frame = findViewById(R.id.main_frame);
        // 2. set up members
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);


        setFragment(new EducationFragment());
    }

    // customize function 1
    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(), fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
package com.kaki.leagueoflegendsexplorer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;

import com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionDto;
import com.kaki.leagueoflegendsexplorer.fragments.champions.ListChampionsFragment;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LaunchFragment {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_champion:
                        break;
                    case R.id.navigation_item_history:
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ListChampionsFragment.newInstance())
                .commit();
    }

    @Override
    public void launchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}

package com.kaki.leagueoflegendsexplorer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kaki.leagueoflegendsexplorer.fragments.champions.ListChampionsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
                .addToBackStack(null)
                .add(R.id.fragment_container, ListChampionsFragment.newInstance())
                .commit();
    }
}

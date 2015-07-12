package com.kaki.leagueoflegendsexplorer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.fragments.champions.ListChampionsFragment;
import com.kaki.leagueoflegendsexplorer.fragments.history.ListHistoryFragment;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LaunchFragment,
        ToolbarInteraction {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    ImageView backgroundNavigationViewHeader;
    CircleImageView iconNavigationViewHeader;
    //@Bind(R.id.adView)
    //AdView adView;

    private ActionBarDrawerToggle toggle;
    private CacheChampions cacheChampions;
    private DragonMagicServer dragonMagicServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        //if (BuildConfig.DEBUG) {
        //    adView.loadAd(new AdRequest.Builder().addTestDevice("EB8221CB516E6DF24EBDF773A0279B36").build());
        //} else {
        //    adView.loadAd(new AdRequest.Builder().build());
        //}
        backgroundNavigationViewHeader = (ImageView) navigationView.findViewById(R.id.background_header);
        iconNavigationViewHeader = (CircleImageView) navigationView.findViewById(R.id.profile_image_header);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        cacheChampions = new CacheChampions(this);
        dragonMagicServer = new DragonMagicServer(this);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ListChampionsFragment.newInstance())
                .commit();
        updateHeaderNavigationView();
    }

    private void updateHeaderNavigationView() {
        ChampionDto championDto = cacheChampions.getChampionByName("Aatrox");
        Log.d("ChampionTest", "" + championDto);
        if (championDto != null) {
            Log.d("ChampionTest", "" + dragonMagicServer.getSplashSkinUrl(championDto.name, championDto.skins.get(0).num));
            Picasso.with(this)
                    .load(dragonMagicServer.getSplashSkinUrl(championDto.name, championDto.skins.get(0).num))
                    .into(backgroundNavigationViewHeader);

        }
    }

    @Override
    public void launchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_up_in, 0, 0, R.anim.slide_up_out)
                .addToBackStack(null)
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void showBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.show();
        }
    }

    @Override
    public void hideBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_item_champion:
                //setTitle();
                launchFragment(ListChampionsFragment.newInstance());
                break;
            case R.id.navigation_item_history:
                //setTitle();
                launchFragment(ListHistoryFragment.newInstance());
                break;
            default:
                break;
        }
        drawerLayout.closeDrawers();
        toggle.syncState();
        return true;
    }
}

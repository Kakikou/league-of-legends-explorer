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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.enums.Ranking;
import com.kaki.leagueoflegendsexplorer.fragments.SummonerNameFragment;
import com.kaki.leagueoflegendsexplorer.fragments.champions.ListChampionsFragment;
import com.kaki.leagueoflegendsexplorer.fragments.currentgame.CurrentGameFragment;
import com.kaki.leagueoflegendsexplorer.fragments.history.ListHistoryFragment;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;
import com.kaki.leagueoflegendsexplorer.interaction.UpdateDrawerListener;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.kaki.leagueoflegendsexplorer.utils.SummonerProfile;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LaunchFragment,
        ToolbarInteraction,
        UpdateDrawerListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation_drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    TextView nameNavigationViewHeader;
    TextView rankingNavigationViewHeader;
    ImageView rankingImageNavigationViewHeader;
    ImageView backgroundNavigationViewHeader;
    CircleImageView iconNavigationViewHeader;

    private ActionBarDrawerToggle toggle;
    private CacheChampions cacheChampions;
    private DragonMagicServer dragonMagicServer;
    private SummonerProfile summonerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        nameNavigationViewHeader = (TextView) navigationView.findViewById(R.id.text_name_header);
        rankingNavigationViewHeader = (TextView) navigationView.findViewById(R.id.text_ranking_header);
        backgroundNavigationViewHeader = (ImageView) navigationView.findViewById(R.id.background_header);
        iconNavigationViewHeader = (CircleImageView) navigationView.findViewById(R.id.profile_image_header);
        rankingImageNavigationViewHeader = (ImageView) navigationView.findViewById(R.id.ranking_header);
        iconNavigationViewHeader.setOnClickListener(OnClickUser);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        cacheChampions = new CacheChampions(this);
        dragonMagicServer = new DragonMagicServer(this);
        summonerProfile = new SummonerProfile(this);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, SummonerNameFragment.newInstance())
                .commit();
        updateDrawer();
    }

    @Override
    public void launchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
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
    public void updateDrawer() {
        if (summonerProfile.getSummonerName().length() != 0) {
            Ranking ranking = Ranking.getRanking(summonerProfile.getSummonerTier(), summonerProfile.getSummonerDivision());
            nameNavigationViewHeader.setText(summonerProfile.getSummonerName());
            rankingNavigationViewHeader.setText(ranking.tier + " " + ranking.division);
            rankingImageNavigationViewHeader.setImageResource(ranking.img);

            Picasso.with(this)
                    .load(dragonMagicServer.getProfileIconUrl(summonerProfile.getSummonerIcon()))
                    .into(iconNavigationViewHeader);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_item_search_summoner:
                launchFragment(SummonerNameFragment.newInstance());
                break;
            case R.id.navigation_item_champion:
                //setTitle();
                launchFragment(ListChampionsFragment.newInstance());
                break;
            case R.id.navigation_item_history:
                //setTitle();
                launchFragment(ListHistoryFragment.newInstance());
                break;
            case R.id.navigation_item_current_game:
                launchFragment(CurrentGameFragment.newInstance());
                break;
            default:
                break;
        }
        drawerLayout.closeDrawers();
        toggle.syncState();
        return true;
    }

    private View.OnClickListener OnClickUser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment_container, SummonerNameFragment.newInstance())
                    .commit();
            drawerLayout.closeDrawers();
            toggle.syncState();
        }
    };
}

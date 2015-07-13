package com.kaki.leagueoflegendsexplorer.fragments.champions;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kaki.leagueoflegendsexplorer.BuildConfig;
import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.LoreChampion;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.SkillsChampion;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.SkinChampion;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.StatsChampion;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.TipsChampion;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class DetailChampionsFragment extends Fragment {

    private int championId;
    private ChampionDto champion;
    private DetailAdapter detailAdapter;
    private ToolbarInteraction toolbarInteraction;

    @Bind(R.id.sliding_tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.adView)
    AdView adView;

    private CacheChampions cacheChampions;

    public static DetailChampionsFragment newInstance(int championId) {
        DetailChampionsFragment fragment = new DetailChampionsFragment();
        fragment.championId = championId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cacheChampions = new CacheChampions(getActivity());
        champion = cacheChampions.getChampion(championId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_champions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        detailAdapter = new DetailAdapter(getChildFragmentManager());
        detailAdapter.addFragment(StatsChampion.newInstance(champion), StatsChampion.TITLE_TAB);
        detailAdapter.addFragment(TipsChampion.newInstance(champion), TipsChampion.TITLE_TAB);
        detailAdapter.addFragment(SkillsChampion.newInstance(champion), SkillsChampion.TITLE_TAB);
        detailAdapter.addFragment(LoreChampion.newInstance(champion), LoreChampion.TITLE_TAB);
        detailAdapter.addFragment(SkinChampion.newInstance(champion), SkinChampion.TITLE_TAB);
        viewPager.setAdapter(detailAdapter);
        tabLayout.setupWithViewPager(viewPager);
        if (BuildConfig.DEBUG) {
            adView.loadAd(new AdRequest.Builder().addTestDevice("EB8221CB516E6DF24EBDF773A0279B36").build());
        } else {
            adView.loadAd(new AdRequest.Builder().build());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarInteraction.setTitle(champion.name);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        detailAdapter = null;
    }

    @Override
        public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            toolbarInteraction = (ToolbarInteraction) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private class DetailAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<String> titleFragments;

        public DetailAdapter(FragmentManager manager) {
            super(manager);
            fragments = new ArrayList<>();
            titleFragments = new ArrayList<>();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titleFragments.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleFragments.get(position);
        }
    }
}

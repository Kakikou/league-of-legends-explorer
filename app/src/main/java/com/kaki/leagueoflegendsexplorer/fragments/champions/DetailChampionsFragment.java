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

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.LoreChampion;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.StatsChampion;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class DetailChampionsFragment extends Fragment {

    private ChampionDto m_champion;
    private DetailAdapter detailAdapter;
    private ToolbarInteraction toolbarInteraction;

    @Bind(R.id.sliding_tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    public static DetailChampionsFragment newInstance(ChampionDto championDto) {
        DetailChampionsFragment fragment = new DetailChampionsFragment();
        fragment.m_champion = championDto;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        detailAdapter.addFragment(StatsChampion.newInstance(m_champion), StatsChampion.TITLE_TAB);
        detailAdapter.addFragment(LoreChampion.newInstance(m_champion), LoreChampion.TITLE_TAB);
        viewPager.setAdapter(detailAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarInteraction.setTitle(m_champion.name);
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

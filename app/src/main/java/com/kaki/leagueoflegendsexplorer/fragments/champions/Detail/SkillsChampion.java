package com.kaki.leagueoflegendsexplorer.fragments.champions.Detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.adapter.SkillChampionAdapter;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 05/07/15.
 */
public class SkillsChampion extends Fragment {

    public static final String TITLE_TAB = "Skills";

    private ChampionDto champion;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    public static SkillsChampion newInstance(ChampionDto champion) {
        SkillsChampion fragment = new SkillsChampion();
        fragment.champion = champion;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skills_champion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(new SkillChampionAdapter(champion));
        recyclerView.setLayoutManager(manager);
    }
}

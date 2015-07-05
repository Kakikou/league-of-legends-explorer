package com.kaki.leagueoflegendsexplorer.fragments.champions.Detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class StatsChampion extends Fragment {

    public static final String TITLE_TAB = "Stats";

    private ChampionDto champion;

    @Bind(R.id.image_champion)
    ImageView image;
    @Bind(R.id.name_champion)
    TextView name;
    @Bind(R.id.title_champion)
    TextView title;
    @Bind(R.id.attack_bar)
    RoundCornerProgressBar attackBar;
    @Bind(R.id.defense_bar)
    RoundCornerProgressBar defenseBar;
    @Bind(R.id.ability_bar)
    RoundCornerProgressBar abilityBar;
    @Bind(R.id.difficulty_bar)
    RoundCornerProgressBar difficultyBar;

    public static StatsChampion newInstance(ChampionDto championDto) {
        StatsChampion fragment = new StatsChampion();
        fragment.champion = championDto;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats_champion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initFields();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initFields() {
        name.setText(champion.name);
        title.setText(champion.title);
        Picasso.with(getActivity())
                .load("http://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/" + champion.image.full)
                .into(image);
        attackBar.setProgress(champion.info.attack);
        defenseBar.setProgress(champion.info.defense);
        abilityBar.setProgress(champion.info.magic);
        difficultyBar.setProgress(champion.info.difficulty);
    }
}

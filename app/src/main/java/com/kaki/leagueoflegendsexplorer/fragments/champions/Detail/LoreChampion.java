package com.kaki.leagueoflegendsexplorer.fragments.champions.Detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 05/07/15.
 */
public class LoreChampion extends Fragment {

    public static final String TITLE_TAB = "Lore";

    @Bind(R.id.text_lore)
    TextView textViewLore;

    private ChampionDto champion;

    public static LoreChampion newInstance(ChampionDto championDto) {
        LoreChampion fragment = new LoreChampion();
        fragment.champion = championDto;
        Log.d("Lore", "" + championDto.lore);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lore_champion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        textViewLore.setText(Html.fromHtml(champion.lore));
    }
}

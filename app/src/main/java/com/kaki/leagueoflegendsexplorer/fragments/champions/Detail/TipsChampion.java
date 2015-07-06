package com.kaki.leagueoflegendsexplorer.fragments.champions.Detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 06/07/15.
 */
public class TipsChampion extends Fragment {

    public static final String TITLE_TAB = "Tips";

    @Bind(R.id.tips_for_champion)
    LinearLayout tipsFor;
    @Bind(R.id.tips_against_champion)
    LinearLayout tipsAgainst;

    private ChampionDto champion;

    public static TipsChampion newInstance(ChampionDto championDto) {
        TipsChampion fragment = new TipsChampion();
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
        return inflater.inflate(R.layout.fragment_tips_champion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        populateTips();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void populateTips() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (String tip : champion.allytips) {
            View view = inflater.inflate(R.layout.item_tips_champion, tipsFor, false);
            TextView textView = (TextView) view.findViewById(R.id.text_tips);
            textView.setGravity(Gravity.CENTER);
            textView.setText(tip);
            tipsFor.addView(view);
        }
        for (String tip : champion.enemytips) {
            View view = inflater.inflate(R.layout.item_tips_champion, tipsAgainst, false);
            TextView textView = (TextView) view.findViewById(R.id.text_tips);
            textView.setGravity(Gravity.CENTER);
            textView.setText(tip);
            tipsAgainst.addView(view);
        }
    }
}

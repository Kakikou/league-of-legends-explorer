package com.kaki.leagueoflegendsexplorer.fragments.history;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.adapter.HistoryAdapter;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.game.GameApi;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.GameDto;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.RecentGamesDto;
import com.kaki.leagueoflegendsexplorer.api.riot.match.MatchApi;
import com.kaki.leagueoflegendsexplorer.api.riot.match.models.MatchDetail;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.MatchHistoryApi;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.MatchSummary;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.PlayerHistory;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.StatsChampion;
import com.kaki.leagueoflegendsexplorer.fragments.history.detail.StatsGame;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;
import com.kaki.leagueoflegendsexplorer.utils.SummonerProfile;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public class ListHistoryFragment extends Fragment implements HistoryAdapter.OnClickCardListener,
        SwipeRefreshLayout.OnRefreshListener {

    private GameApi gameApi;
    private SummonerProfile summonerProfile;
    private HistoryAdapter historyAdapter;
    private LaunchFragment m_launchFragment;
    private ToolbarInteraction toolbarInteraction;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public static ListHistoryFragment newInstance() {
        return new ListHistoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyAdapter = new HistoryAdapter(getActivity(), this);
        summonerProfile = new SummonerProfile(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(historyAdapter);
        swipeRefreshLayout.measure(0, 0);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarInteraction.setTitle("Match");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        gameApi = new GameApi(activity);
        try {
            m_launchFragment = (LaunchFragment) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        try {
            toolbarInteraction = (ToolbarInteraction) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        // TODO Handle when user don't give summoner
        if (summonerProfile.getSummonerId().equals(""))
            return;
        gameApi.getMatch(getActivity(), Long.parseLong(summonerProfile.getSummonerId()), new HttpRequest<RecentGamesDto>() {
            @Override
            public void success(RecentGamesDto recentGamesDto, Response response) {
                historyAdapter.setDatas(recentGamesDto.games);
                historyAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void clickCard(View card, GameDto game, ChampionDto championDto) {
        /*
        matchApi.getMatch(getActivity(), game.gameId, new HttpRequest<MatchDetail>() {
            @Override
            public void success(MatchDetail matchDetail, Response response) {
                Fragment fragment = StatsGame.newInstance(matchDetail);
                m_launchFragment.addFragment(fragment);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        */
        //Fragment fragment = StatsGame.newInstance(game);
        //m_launchFragment.addFragment(fragment);
    }
}

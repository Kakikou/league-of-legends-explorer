package com.kaki.leagueoflegendsexplorer.fragments.history;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.adapter.HistoryAdapter;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.game.GameApi;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.RecentGamesDto;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.MatchHistoryApi;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.MatchSummary;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.PlayerHistory;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public class ListHistoryFragment extends Fragment {

    private GameApi gameApi;
    private HistoryAdapter historyAdapter;
    private LaunchFragment m_launchFragment;
    private ToolbarInteraction toolbarInteraction;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    public static ListHistoryFragment newInstance() {
        return new ListHistoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyAdapter = new HistoryAdapter(getActivity());
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
        gameApi.getMatch(getActivity(), new HttpRequest<RecentGamesDto>() {
            @Override
            public void success(RecentGamesDto recentGamesDto, Response response) {
                historyAdapter.setDatas(recentGamesDto.games);
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
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
}

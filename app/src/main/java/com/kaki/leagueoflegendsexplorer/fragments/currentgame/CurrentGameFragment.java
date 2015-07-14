package com.kaki.leagueoflegendsexplorer.fragments.currentgame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.current_game.CurrentGameApi;
import com.kaki.leagueoflegendsexplorer.api.riot.current_game.models.CurrentGameInfo;
import com.kaki.leagueoflegendsexplorer.utils.SummonerProfile;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 14/07/15.
 */
public class CurrentGameFragment extends Fragment {

    private CurrentGameApi currentGameApi;
    private SummonerProfile summonerProfile;

    public static CurrentGameFragment newInstance() {
        return new CurrentGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentGameApi = new CurrentGameApi(getActivity());
        summonerProfile = new SummonerProfile(getActivity());
        currentGameApi.getCurrentMatch(getActivity(), summonerProfile.getSummonerId(), OnGetCurrentGame);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private HttpRequest<CurrentGameInfo> OnGetCurrentGame = new HttpRequest<CurrentGameInfo>() {
        @Override
        public void success(CurrentGameInfo currentGameInfo, Response response) {

        }

        @Override
        public void failure(RetrofitError error) {

        }
    };
}

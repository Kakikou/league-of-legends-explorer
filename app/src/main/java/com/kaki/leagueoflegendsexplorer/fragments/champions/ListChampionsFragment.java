package com.kaki.leagueoflegendsexplorer.fragments.champions;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.adapter.ChampionsAdapter;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.StaticDataApi;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ListChampionsFragment extends Fragment implements HttpRequest<ChampionListDto> {

    private StaticDataApi m_staticDataApi;
    private ChampionsAdapter m_adapter;

    @Bind(R.id.recyclerview)
    RecyclerView m_recyclerview;

    public ListChampionsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_champions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        m_adapter = new ChampionsAdapter();
        m_recyclerview.setAdapter(m_adapter);
        m_recyclerview.setHasFixedSize(true);
        m_recyclerview.setLayoutManager(layoutManager);
        m_staticDataApi.getChampionsList(getActivity(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        m_staticDataApi = new StaticDataApi(activity);
    }

    @Override
    public void success(ChampionListDto championListDto, Response response) {
        for (Map.Entry<String, ChampionDto> entry : championListDto.data.entrySet()) {
            m_adapter.add(entry.getValue());
        }
        m_adapter.notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {

    }
}

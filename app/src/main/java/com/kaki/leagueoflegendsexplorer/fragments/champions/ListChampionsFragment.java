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
import com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.ChampionV12Api;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.StaticDataApi;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ListChampionsFragment extends Fragment implements ChampionsAdapter.OnTouchChampionListener {

    private ChampionV12Api m_championV12Api;
    private StaticDataApi m_staticDataApi;
    private ChampionsAdapter m_adapter;
    private LaunchFragment m_launchFragment;
    private ToolbarInteraction toolbarInteraction;

    @Bind(R.id.recyclerview)
    RecyclerView m_recyclerview;

    public static ListChampionsFragment newInstance() {
        return new ListChampionsFragment();
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
        m_adapter = new ChampionsAdapter(this);
        m_recyclerview.setAdapter(m_adapter);
        m_recyclerview.setHasFixedSize(true);
        m_recyclerview.setLayoutManager(layoutManager);
        m_championV12Api.getFreeChampList(getActivity(), OnGetFreeChampionList);
        m_staticDataApi.getChampionsList(getActivity(), OnGetChampionList);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarInteraction.setTitle("Champions");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        m_staticDataApi = new StaticDataApi(activity);
        m_championV12Api = new ChampionV12Api(activity);
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
    public void onTouchChampion(View view, ChampionDto championDto) {
        DetailChampionsFragment fragment = DetailChampionsFragment.newInstance(championDto);
        m_launchFragment.launchFragment(fragment);
    }

    private HttpRequest<ChampionListDto> OnGetChampionList = new HttpRequest<ChampionListDto>() {
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
    };

    private HttpRequest<com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionListDto> OnGetFreeChampionList = new HttpRequest<com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionListDto>() {
        @Override
        public void success(com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionListDto championListDto, Response response) {
            ArrayList<Integer> list = new ArrayList<>();
            for (com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionDto championDto : championListDto.champions) {
                list.add(championDto.id);
            }
            m_adapter.setFreeChamp(list);
            m_adapter.notifyDataSetChanged();
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };
}

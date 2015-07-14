package com.kaki.leagueoflegendsexplorer.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kaki.leagueoflegendsexplorer.BuildConfig;
import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.league.LeagueApi;
import com.kaki.leagueoflegendsexplorer.api.riot.league.models.LeagueDto;
import com.kaki.leagueoflegendsexplorer.api.riot.summoner.SummonerApi;
import com.kaki.leagueoflegendsexplorer.api.riot.summoner.models.SummonerDto;
import com.kaki.leagueoflegendsexplorer.enums.Ranking;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.UpdateDrawerListener;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.kaki.leagueoflegendsexplorer.utils.SummonerProfile;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 12/07/15.
 */
public class SummonerNameFragment extends Fragment {

    @Bind(R.id.textinput_name)
    TextInputLayout textInputName;
    @Bind(R.id.icon_summoner)
    ImageView iconSummoner;
    @Bind(R.id.name_summoner)
    TextView nameSummoner;
    @Bind(R.id.level_summoner)
    TextView levelSummoner;
    @Bind(R.id.ranking_summoner)
    TextView rankingSummoner;
    @Bind(R.id.lp_summoner)
    TextView lpSummoner;
    @Bind(R.id.win_summoner)
    TextView winSummoner;
    @Bind(R.id.lose_summoner)
    TextView loseSummoner;
    @Bind(R.id.image_ranked_icon)
    ImageView rankedIcon;
    @Bind(R.id.layout_summoner)
    View layoutSummoner;

    @Bind(R.id.adView)
    AdView adView;

    private LeagueApi leagueApi;
    private SummonerApi summonerApi;
    private SummonerProfile summonerProfile;
    private DragonMagicServer dragonMagicServer;
    private UpdateDrawerListener updateDrawerListener;

    public static SummonerNameFragment newInstance() {
        return new SummonerNameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leagueApi = new LeagueApi(getActivity());
        summonerApi = new SummonerApi(getActivity());
        summonerProfile = new SummonerProfile(getActivity());
        dragonMagicServer = new DragonMagicServer(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_name, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (summonerProfile.getSummonerId().length() != 0) {
            populateSummoner();
        }
        if (BuildConfig.DEBUG) {
            adView.loadAd(new AdRequest.Builder().addTestDevice("EB8221CB516E6DF24EBDF773A0279B36").build());
        } else {
            adView.loadAd(new AdRequest.Builder().build());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            updateDrawerListener = (UpdateDrawerListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.search_button)
    void onClickSearchButton() {
        String name = textInputName.getEditText().getText().toString();
        if (name.length() == 0) {
            summonerProfile.clean();
            textInputName.setError(getString(R.string.no_name));
            populateSummoner();
        } else {
            summonerApi.getSummonerByName(getActivity(), name, OnGetSummoner);
        }
    }

    private void populateSummoner() {
        Ranking ranking;

        ranking = Ranking.getRanking(summonerProfile.getSummonerTier(), summonerProfile.getSummonerDivision());
        textInputName.getEditText().setText(summonerProfile.getSummonerName());
        nameSummoner.setText(summonerProfile.getSummonerName());
        levelSummoner.setText(getString(R.string.level) + " " + summonerProfile.getSummonerLevel());
        Picasso.with(getActivity())
                .load(dragonMagicServer.getProfileIconUrl(summonerProfile.getSummonerIcon()))
                .into(iconSummoner);
        rankingSummoner.setText(ranking.tier + " " + ranking.division);
        lpSummoner.setText(summonerProfile.getSummonerLeaguePoints() + " " + getString(R.string.league_points));
        winSummoner.setText(summonerProfile.getSummonerWin() + " W");
        loseSummoner.setText(summonerProfile.getSummonerLose() + " L");
        rankedIcon.setImageResource(ranking.img);
        animateRankedLayout();
        updateDrawerListener.updateDrawer();
    }

    private void animateRankedLayout() {
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(layoutSummoner, View.ALPHA, 0, 1));
        set.setDuration(500);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                layoutSummoner.setVisibility(View.VISIBLE);
            }
        });
        set.start();
    }

    private HttpRequest<Map<String, SummonerDto>> OnGetSummoner = new HttpRequest<Map<String, SummonerDto>>() {
        @Override
        public void success(Map<String, SummonerDto> summonerDtoMap, Response response) {
            summonerProfile.clean();
            for (Map.Entry<String, SummonerDto> entry : summonerDtoMap.entrySet()) {
                summonerProfile.storeSummoner(entry.getValue());
                break;
            }
            leagueApi.getLeagueBySummonerId(getActivity(), summonerProfile.getSummonerId(), OnGetLeagueSummoner);
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    private HttpRequest<Map<String, List<LeagueDto>>> OnGetLeagueSummoner = new HttpRequest<Map<String, List<LeagueDto>>>() {
        @Override
        public void success(Map<String, List<LeagueDto>> stringListMap, Response response) {
            for (Map.Entry<String, List<LeagueDto>> entry : stringListMap.entrySet()) {
                if (entry.getKey().equals(summonerProfile.getSummonerId())) {
                    for (LeagueDto leagueDto : entry.getValue()) {
                        if (leagueDto.queue.equals("RANKED_SOLO_5x5")) {
                            summonerProfile.storeSummonerRankedStats(leagueDto);
                        }
                    }
                }
            }
            populateSummoner();
        }

        @Override
        public void failure(RetrofitError error) {
            populateSummoner();
        }
    };
}

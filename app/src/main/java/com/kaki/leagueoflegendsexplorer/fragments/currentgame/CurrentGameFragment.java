package com.kaki.leagueoflegendsexplorer.fragments.currentgame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kaki.leagueoflegendsexplorer.BuildConfig;
import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.current_game.CurrentGameApi;
import com.kaki.leagueoflegendsexplorer.api.riot.current_game.models.CurrentGameInfo;
import com.kaki.leagueoflegendsexplorer.api.riot.current_game.models.CurrentGameParticipant;
import com.kaki.leagueoflegendsexplorer.api.riot.league.LeagueApi;
import com.kaki.leagueoflegendsexplorer.api.riot.league.models.LeagueDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.enums.Ranking;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.kaki.leagueoflegendsexplorer.utils.SummonerProfile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 14/07/15.
 */
public class CurrentGameFragment extends Fragment {

    @Bind(R.id.game_type)
    TextView gameType;
    @Bind(R.id.game_queue_config)
    TextView gameQueue;
    @Bind(R.id.card_view_team1)
    View cardViewTeam1;
    @Bind(R.id.card_view_team2)
    View cardViewTeam2;
    @Bind(R.id.layout_team_1)
    LinearLayout layoutTeam1;
    @Bind(R.id.layout_team_2)
    LinearLayout layoutTeam2;
    @Bind(R.id.adView)
    AdView adView;

    private LeagueApi leagueApi;
    private CurrentGameApi currentGameApi;
    private SummonerProfile summonerProfile;
    private CacheChampions cacheChampions;
    private AnimatorSet mainAnimation;
    private TeamAdapter teamAdapter;
    private List<Player> listPlayer;
    private DragonMagicServer dragonMagicServer;

    public static CurrentGameFragment newInstance() {
        return new CurrentGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPlayer = new ArrayList<>();
        leagueApi = new LeagueApi(getActivity());
        currentGameApi = new CurrentGameApi(getActivity());
        cacheChampions = new CacheChampions(getActivity());
        summonerProfile = new SummonerProfile(getActivity());
        dragonMagicServer = new DragonMagicServer(getActivity());
        currentGameApi.getCurrentMatch(getActivity(), summonerProfile.getSummonerId(), OnGetCurrentGame);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (BuildConfig.DEBUG) {
            adView.loadAd(new AdRequest.Builder().addTestDevice("EB8221CB516E6DF24EBDF773A0279B36").build());
        } else {
            adView.loadAd(new AdRequest.Builder().build());
        }

        mainAnimation = new AnimatorSet();
        ObjectAnimator animatorGameType = ObjectAnimator.ofFloat(gameType, View.TRANSLATION_X, 800, -800);
        ObjectAnimator animatorGameQueue = ObjectAnimator.ofFloat(gameQueue, View.TRANSLATION_X, 800, -800);
        animatorGameType.setRepeatCount(ValueAnimator.INFINITE);
        animatorGameQueue.setRepeatCount(ValueAnimator.INFINITE);
        mainAnimation.playTogether(animatorGameType, animatorGameQueue);
        mainAnimation.setDuration(7000);
        mainAnimation.setInterpolator(new LinearInterpolator());
        mainAnimation.start();
        teamAdapter = new TeamAdapter(getChildFragmentManager());
    }

    private Player getPlayerById(long id) {
        for (Player player : listPlayer) {
            if (player.currentGameParticipant.summonerId == id)
                return player;
        }
        return listPlayer.get(0);
    }

    private void populateTeam() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (Player player : listPlayer) {
            ChampionDto championDto = cacheChampions.getChampion((int) player.currentGameParticipant.championId);
            CardView view = (CardView) inflater.inflate(R.layout.item_player_current_game, null, false);
            ImageView imageChamp = (ImageView) view.findViewById(R.id.image_champion);
            TextView namePlayer = (TextView) view.findViewById(R.id.name_player);
            ImageView imageRanking = (ImageView) view.findViewById(R.id.image_ranking);
            TextView textRanking = (TextView) view.findViewById(R.id.text_ranking);
            TextView nameChamp = (TextView) view.findViewById(R.id.name_champion);

            nameChamp.setText(championDto.name);
            namePlayer.setText(player.currentGameParticipant.summonerName);
            textRanking.setText(player.ranking.tier + " " + player.ranking.division);
            imageRanking.setImageResource(player.ranking.img);
            Picasso.with(getActivity())
                    .load(dragonMagicServer.getChampionSquareImageUrl(championDto.image.full))
                    .into(imageChamp);
            if (Long.toString(player.currentGameParticipant.summonerId).equals(summonerProfile.getSummonerId())) {
                view.setCardBackgroundColor(getResources().getColor(R.color.green_white));
            }

            if (player.currentGameParticipant.teamId == 100) {
                cardViewTeam1.setVisibility(View.VISIBLE);
                layoutTeam1.addView(view);
            } else {
                cardViewTeam2.setVisibility(View.VISIBLE);
                layoutTeam2.addView(view);
            }
        }
    }

    private HttpRequest<CurrentGameInfo> OnGetCurrentGame = new HttpRequest<CurrentGameInfo>() {
        @Override
        public void success(CurrentGameInfo currentGameInfo, Response response) {
            ArrayList<String> players = new ArrayList<>();
            int i = 0;
            for (CurrentGameParticipant participant : currentGameInfo.participants) {
                if (participant.bot)
                    continue;
                Player player = new Player();
                player.currentGameParticipant = participant;
                listPlayer.add(player);
                players.add("" + participant.summonerId);
                i++;
            }
            leagueApi.getLeagueBySummonerId(getActivity(), players.toArray(new String[players.size()]), OnGetRankingPlayers);
        }

        @Override
        public void failure(RetrofitError error) {
            if (error.getResponse() != null && error.getResponse().getStatus() == 404) {
                gameType.setText(getString(R.string.player_not_in_game, summonerProfile.getSummonerName()));
                gameQueue.setText("");
            }
        }
    };

    private HttpRequest<Map<String, List<LeagueDto>>> OnGetRankingPlayers = new HttpRequest<Map<String, List<LeagueDto>>>() {
        @Override
        public void success(Map<String, List<LeagueDto>> stringListMap, Response response) {
            for (Map.Entry<String, List<LeagueDto>> entry : stringListMap.entrySet()) {
                for (LeagueDto leagueDto : entry.getValue()) {
                    if (leagueDto.queue.equals("RANKED_SOLO_5x5")) {
                        Player player = getPlayerById(Long.valueOf(entry.getKey()));
                        player.ranking = Ranking.getRanking(leagueDto.tier, leagueDto.entries.get(0).division);
                    }
                }
            }
            populateTeam();
        }

        @Override
        public void failure(RetrofitError error) {
            if (error.getResponse() != null && error.getResponse().getStatus() == 404) {
                for (Player player : listPlayer) {
                    player.ranking = Ranking.getRanking("", "");
                }
                populateTeam();
            }
        }
    };

    private class TeamAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<String> titleFragments;

        public TeamAdapter(FragmentManager manager) {
            super(manager);
            fragments = new ArrayList<>();
            titleFragments = new ArrayList<>();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titleFragments.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleFragments.get(position);
        }
    }

    private class Player {
        Ranking ranking;
        CurrentGameParticipant currentGameParticipant;
    }
}

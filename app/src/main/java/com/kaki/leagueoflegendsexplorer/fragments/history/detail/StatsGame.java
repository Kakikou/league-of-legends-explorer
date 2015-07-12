package com.kaki.leagueoflegendsexplorer.fragments.history.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.UrlImage;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.GameDto;
import com.kaki.leagueoflegendsexplorer.api.riot.match.models.MatchDetail;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.MatchSummary;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Participant;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.ParticipantIdentity;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Player;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.StaticDataApi;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemDto;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.CacheItem;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class StatsGame extends Fragment {

    @Bind(R.id.item0)
    ImageView item0;
    @Bind(R.id.item1)
    ImageView item1;
    @Bind(R.id.item2)
    ImageView item2;
    @Bind(R.id.item3)
    ImageView item3;
    @Bind(R.id.item4)
    ImageView item4;
    @Bind(R.id.item5)
    ImageView item5;
    @Bind(R.id.image_champion_player)
    ImageView championPlayer;

    private ParticipantIdentity player;
    private MatchDetail matchDetail;
    private StaticDataApi dataApi;
    private CacheChampions cacheChampions;
    private CacheItem cacheItem;
    private DragonMagicServer dragonMagicServer;

    public static StatsGame newInstance(MatchDetail matchDetail) {
        StatsGame fragment = new StatsGame();
        fragment.matchDetail = matchDetail;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cacheItem = new CacheItem(getActivity());
        dataApi = new StaticDataApi(getActivity());
        cacheChampions = new CacheChampions(getActivity());
        dragonMagicServer = new DragonMagicServer(getActivity());
        for (ParticipantIdentity participant : matchDetail.participantIdentities) {
            // TODO Add id of player
            Log.d("Participant", "" + participant.player);
            if (participant.player == null)
                continue;
            Log.d("Id", "" + participant.player.summonerId);
            if (participant.player.summonerId == 22812174) {
                player = participant;
                break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Participant participant = getParticipant(player.participantId);

        ChampionDto championDto = cacheChampions.getChampion(participant.championId);
        populateItems(participant.stats.item0, item0);
        populateItems(participant.stats.item1, item1);
        populateItems(participant.stats.item2, item2);
        populateItems(participant.stats.item3, item3);
        populateItems(participant.stats.item4, item4);
        populateItems(participant.stats.item5, item5);
        Picasso.with(getActivity())
                .load(dragonMagicServer.getChampionSquareImageUrl(championDto.image.full))
                .into(championPlayer);

    }

    private void populateItems(long id, final ImageView container) {
        ItemDto itemDto = cacheItem.getItem(id);
        if (itemDto == null)
            return;
        Picasso.with(getActivity())
                .load(dragonMagicServer.getItemImageUrl(itemDto.image.full))
                .into(container);
    }

    private Participant getParticipant(int participantId) {
        for (Participant participant1 : matchDetail.participants) {
            if (participant1.participantId == participantId) {
                return participant1;
            }
        }
        return null;
    }
}

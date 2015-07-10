package com.kaki.leagueoflegendsexplorer.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.MatchSummary;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Participant;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.ParticipantIdentity;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Player;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private int redColor;
    private int greenColor;
    private List<MatchSummary> summaryList;
    private CacheChampions cacheChampions;

    public HistoryAdapter(Context context) {
        redColor = context.getResources().getColor(R.color.red_white);
        greenColor = context.getResources().getColor(R.color.green_white);
        summaryList = new ArrayList<MatchSummary>();
        cacheChampions = new CacheChampions(context);
    }

    public void setDatas(List<MatchSummary> summaries) {
        summaryList.clear();
        summaryList.addAll(summaries);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.recyclerview_item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatchSummary summary = summaryList.get(position);
        Participant player = getPlayer(summary);

        if (player.stats.winner) {
            holder.cardView.setCardBackgroundColor(greenColor);
        } else {
            holder.cardView.setCardBackgroundColor(redColor);
        }
    }

    @Override
    public int getItemCount() {
        return summaryList.size();
    }

    private Participant getPlayer(MatchSummary summary) {
        int id = 0;

        for (ParticipantIdentity identity : summary.participantIdentities) {
            if (identity.player.summonerId == 22812174) {
                id = identity.participantId;
            }
        }
        for (Participant participant : summary.participants) {
            if (participant.participantId == id) {
                return participant;
            }
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_view)
        CardView cardView;
        @Bind(R.id.image_champion)
        ImageView imageChampion;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

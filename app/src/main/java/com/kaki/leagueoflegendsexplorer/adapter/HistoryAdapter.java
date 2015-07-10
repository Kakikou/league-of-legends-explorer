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

import com.google.gson.Gson;
import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.UrlImage;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.GameDto;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.RecentGamesDto;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.MatchSummary;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Participant;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.ParticipantIdentity;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Player;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.squareup.picasso.Picasso;

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
    private int greyColor;
    private List<GameDto> summaryList;
    private List<ChampionDto> championList;
    private CacheChampions cacheChampions;

    public HistoryAdapter(Context context) {
        redColor = context.getResources().getColor(R.color.red_white);
        greenColor = context.getResources().getColor(R.color.green_white);
        greyColor = context.getResources().getColor(R.color.grey);
        summaryList = new ArrayList<GameDto>();
        championList = new ArrayList<>();
        cacheChampions = new CacheChampions(context);
    }

    public void setDatas(List<GameDto> gameDtos) {
        summaryList.clear();
        summaryList.addAll(gameDtos);
        fetchChampions(summaryList);
    }

    public void fetchChampions(List<GameDto> games) {
        championList.clear();
        for (GameDto gameDto : games) {
            championList.add(cacheChampions.getChampion(gameDto.championId));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.recyclerview_item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameDto gameDto = summaryList.get(position);
        ChampionDto championDto = championList.get(position);

        if (gameDto.invalid) {
            holder.cardView.setCardBackgroundColor(greyColor);
        } else if (gameDto.stats.win) {
            holder.cardView.setCardBackgroundColor(greenColor);
        } else {
            holder.cardView.setCardBackgroundColor(redColor);
        }

        Picasso.with(holder.itemView.getContext())
                .load(UrlImage.CHAMPION_URL + championDto.image.full)
                .into(holder.imageChampion);
    }

    @Override
    public int getItemCount() {
        return summaryList.size();
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

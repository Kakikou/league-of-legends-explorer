package com.kaki.leagueoflegendsexplorer.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.game.models.GameDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private SimpleDateFormat simpleDateFormat;
    private final OnClickCardListener listener;
    private final DragonMagicServer dragonMagicServer;

    public HistoryAdapter(Context context, OnClickCardListener listener) {
        this.listener = listener;
        redColor = context.getResources().getColor(R.color.red_white);
        greenColor = context.getResources().getColor(R.color.green_white);
        greyColor = context.getResources().getColor(R.color.grey);
        summaryList = new ArrayList<>();
        championList = new ArrayList<>();
        cacheChampions = new CacheChampions(context);
        simpleDateFormat = new SimpleDateFormat("dd/MM\nyyyy");
        dragonMagicServer = new DragonMagicServer(context);
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

        Picasso.with(holder.itemView.getContext())
                .load(dragonMagicServer.getChampionSquareImageUrl(championDto.image.full))
                .into(holder.imageChampion);
        holder.nameChampion.setText(championDto.name);
        holder.modeGame.setText(gameDto.gameMode);
        holder.typeGame.setText(gameDto.gameType);
        holder.statsGame.setText(gameDto.stats.championsKilled + "/" + gameDto.stats.numDeaths + "/" + gameDto.stats.assists);
        holder.timeGame.setText(String.format("%02d:%02d",
                TimeUnit.SECONDS.toMinutes(gameDto.stats.timePlayed),
                TimeUnit.SECONDS.toSeconds(gameDto.stats.timePlayed) % 60));
        holder.createGame.setText(simpleDateFormat.format(gameDto.createDate));

        if (gameDto.invalid) {
            holder.cardView.setCardBackgroundColor(greyColor);
        } else if (gameDto.stats.win) {
            holder.cardView.setCardBackgroundColor(greenColor);
        } else {
            holder.cardView.setCardBackgroundColor(redColor);
        }

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
        @Bind(R.id.name_champion)
        TextView nameChampion;
        @Bind(R.id.mode_game)
        TextView modeGame;
        @Bind(R.id.type_game)
        TextView typeGame;
        @Bind(R.id.stats_game)
        TextView statsGame;
        @Bind(R.id.time_game)
        TextView timeGame;
        @Bind(R.id.create_game)
        TextView createGame;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.card_view)
        void onClickCardView() {
            GameDto gameDto = summaryList.get(getAdapterPosition());
            ChampionDto championDto = championList.get(getAdapterPosition());
            listener.clickCard(cardView, gameDto, championDto);
        }
    }

    public interface OnClickCardListener {
        void clickCard(View card, GameDto game, ChampionDto championDto);
    }
}

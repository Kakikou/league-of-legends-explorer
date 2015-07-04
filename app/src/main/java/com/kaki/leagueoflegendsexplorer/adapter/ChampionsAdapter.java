package com.kaki.leagueoflegendsexplorer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    private List<ChampionDto> m_listChampions;

    public ChampionsAdapter() {
        m_listChampions = new ArrayList<>();
    }

    public void add(ChampionDto championDto) {
        m_listChampions.add(championDto);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(inflater.inflate(R.layout.recyclerview_item_champion, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ChampionDto championDto = m_listChampions.get(i);

        viewHolder.name.setText(championDto.name);
        Picasso.with(viewHolder.itemView.getContext())
                .load("http://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/" + championDto.image.full)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return m_listChampions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_champion)
        ImageView image;
        @Bind(R.id.name_champion)
        TextView name;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

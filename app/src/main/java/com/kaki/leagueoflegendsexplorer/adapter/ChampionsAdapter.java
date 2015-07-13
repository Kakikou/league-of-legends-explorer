package com.kaki.leagueoflegendsexplorer.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    private List<Integer> m_freeList;
    private List<ChampionDto> m_listChampions;
    private OnTouchChampionListener m_onTouchChampionListener;
    private DragonMagicServer dragonMagicServer;

    public ChampionsAdapter(Context context, OnTouchChampionListener onTouchChampionListener) {
        m_onTouchChampionListener = onTouchChampionListener;
        dragonMagicServer = new DragonMagicServer(context);
        m_listChampions = new ArrayList<>();
        m_freeList = new ArrayList<>();
    }

    public void setFreeChamp(List<Integer> freeList) {
        m_freeList = freeList;
    }

    public void add(ChampionDto championDto) {
        m_listChampions.add(championDto);
    }

    public void clean() {
        m_listChampions.clear();
        m_freeList.clear();
    }

    public void sortChamp() {
        Collections.sort(m_listChampions, new Comparator<ChampionDto>() {
            @Override
            public int compare(ChampionDto lhs, ChampionDto rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });
    }

    private boolean isFreeChamp(int id) {
        for (Integer i : m_freeList) {
            if (id == i)
                return true;
        }
        return false;
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
                .load(dragonMagicServer.getChampionSquareImageUrl(championDto.image.full))
                .into(viewHolder.image);
        if (isFreeChamp(championDto.id)) {
            viewHolder.free.setVisibility(View.VISIBLE);
            viewHolder.free.setRotation(-45);
        } else {
            viewHolder.free.setVisibility(View.GONE);
            viewHolder.free.setRotation(0);
        }
    }

    @Override
    public int getItemCount() {
        return m_listChampions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_champion)
        ImageView image;
        @Bind(R.id.name_champion)
        TextView name;
        @Bind(R.id.free_champion)
        TextView free;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    m_onTouchChampionListener.onTouchChampion(v, m_listChampions.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnTouchChampionListener {
        void onTouchChampion(View view, ChampionDto championDto);
    }
}

package com.kaki.leagueoflegendsexplorer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.UrlImage;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionSpellDto;
import com.kaki.leagueoflegendsexplorer.fragments.champions.Detail.SkillsChampion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 05/07/15.
 */
public class SkillChampionAdapter extends RecyclerView.Adapter<SkillChampionAdapter.ViewHolder> {

    private List<ChampionSpellDto> skills;

    public SkillChampionAdapter(ChampionDto championDto) {
        super();
        skills = new ArrayList<ChampionSpellDto>();
        ChampionSpellDto temp = new ChampionSpellDto();
        temp.name = championDto.passive.name;
        temp.description = championDto.passive.description;
        temp.sanitizedDescription = championDto.passive.sanitizedDescription;
        temp.image = championDto.passive.image;
        skills.add(temp);
        for (ChampionSpellDto spell : championDto.spells) {
            skills.add(spell);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.recyclerview_item_skill, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChampionSpellDto skill = skills.get(position);
        String url;

        holder.name.setText(skill.name);
        if (skill.cost != null) {
            fillSkill(skill, holder);
            url = UrlImage.SKILL_URL;
        } else {
            holder.cost.setText(R.string.passive);
            url = UrlImage.PASSIVE_SKILL_URL;
        }
        holder.description.setText(skill.description + "\n" + skill.sanitizedDescription);
        Picasso.with(holder.itemView.getContext())
                .load(url + skill.image.full)
                .into(holder.image);
    }

    private void fillSkill(ChampionSpellDto skill, ViewHolder holder) {
        String costString = new String();
        if (skill.cost.get(0) == 0) {
            holder.cost.setText(R.string.skill_no_cost);
        } else {
            for (Integer cost : skill.cost) {
                costString += cost + "/";
            }
            costString = costString.substring(0, costString.length() - 1);
            holder.cost.setText(costString);
        }

        if (skill.range instanceof String) {
            String range = (String) skill.range;
            holder.range.setText(range);
        } else if (skill.range instanceof List<?>) {
            List<Double> range = (List<Double>) skill.range;
            String rangeStr = "";
            for (Double r : range) {
                rangeStr += String.format("%.0f/", r);
            }
            rangeStr = rangeStr.substring(0, rangeStr.length() - 1);
            holder.range.setText(rangeStr);
        }
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_skill)
        ImageView image;
        @Bind(R.id.name_skill)
        TextView name;
        @Bind(R.id.cost_skill)
        TextView cost;
        @Bind(R.id.range_skill)
        TextView range;
        @Bind(R.id.description_skill)
        TextView description;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

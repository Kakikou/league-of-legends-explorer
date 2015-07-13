package com.kaki.leagueoflegendsexplorer.fragments.champions.Detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.SkinDto;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 06/07/15.
 */
public class SkinChampion extends Fragment {

    public static final String TITLE_TAB = "Skins";

    @Bind(R.id.layout_skin)
    LinearLayout skinLayout;

    private ChampionDto champion;
    private LaunchFragment launchFragment;
    private DragonMagicServer dragonMagicServer;

    public static SkinChampion newInstance(ChampionDto championDto) {
        SkinChampion fragment = new SkinChampion();
        fragment.champion = championDto;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dragonMagicServer = new DragonMagicServer(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skin_champion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        populateSkins();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            launchFragment = (LaunchFragment) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void populateSkins() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (SkinDto skin : champion.skins) {
            View view = inflater.inflate(R.layout.item_skin_champion, skinLayout, false);
            view.setClickable(true);
            view.setTag(skin);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSkin(v);
                }
            });
            ImageView imageView = (ImageView) view.findViewById(R.id.image_skin);
            TextView textView = (TextView) view.findViewById(R.id.name_skin);
            Picasso.with(imageView.getContext())
                    .load(dragonMagicServer.getScreenArtChampion(champion.name, skin.num))
                    .into(imageView);
            textView.setText(skin.name);
            skinLayout.addView(view);
        }
    }

    private void onClickSkin(View view) {
        SkinDto skin = (SkinDto) view.getTag();
        launchFragment.addFragment(FullSizeSkin.newInstance(champion, skin.num));
    }
}

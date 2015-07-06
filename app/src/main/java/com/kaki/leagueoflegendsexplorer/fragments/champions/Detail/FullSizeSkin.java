package com.kaki.leagueoflegendsexplorer.fragments.champions.Detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.riot.UrlImage;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.SkinDto;
import com.kaki.leagueoflegendsexplorer.interaction.LaunchFragment;
import com.kaki.leagueoflegendsexplorer.interaction.ToolbarInteraction;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevinrodrigues on 06/07/15.
 */
public class FullSizeSkin extends Fragment {

    @Bind(R.id.image_skin)
    ImageView imageSkin;

    private ChampionDto champion;
    private Integer nb;
    private ToolbarInteraction toolbarInteraction;

    public static FullSizeSkin newInstance(ChampionDto championDto, int nb) {
        FullSizeSkin fragment = new FullSizeSkin();
        fragment.champion = championDto;
        fragment.nb = nb;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_full_size_skin_champion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
        } else {
            return animation;
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                populateSkin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return animation;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            toolbarInteraction = (ToolbarInteraction) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void populateSkin() {
        Picasso.with(getActivity())
                .load(UrlImage.CHAMPION_SPLASH_SKIN + champion.name + "_" + nb + ".jpg")
                .rotate(90)
                .into(imageSkin);
        toolbarInteraction.hideBar();
    }
}

package com.kaki.leagueoflegendsexplorer.api.riot;

import android.content.res.Resources;

import com.kaki.leagueoflegendsexplorer.BuildConfig;
import com.kaki.leagueoflegendsexplorer.R;

import retrofit.RequestInterceptor;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class RequestInterception implements RequestInterceptor {

    private Resources m_resources;

    public RequestInterception(Resources resources) {
        m_resources = resources;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (BuildConfig.DEBUG) {
            request.addQueryParam("api_key", m_resources.getString(R.string.api_key_debug));
        } else {
            request.addQueryParam("api_key", "");
        }
    }
}

package com.kaki.leagueoflegendsexplorer.api;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public interface HttpRequest<T> {

    void success(T t, Response response);
    void failure(RetrofitError error);
}

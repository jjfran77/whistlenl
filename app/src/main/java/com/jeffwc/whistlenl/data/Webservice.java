package com.jeffwc.whistlenl.data;


import com.jeffwc.whistlenl.model.track.TracksResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/*
public interface Webservice {

    public List<Track> getTracks(String term, int limit);

}

*/


public interface Webservice {
    @GET("2.0")
    Call<TracksResult> findTracks(@Query("method") String method, @Query("track") String term, @Query("format") String format, @Query("api_key") String apiKey, @Query("page") String page, @Query("limit") String limit);

}
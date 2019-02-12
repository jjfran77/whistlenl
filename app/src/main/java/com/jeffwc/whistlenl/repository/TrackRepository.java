package com.jeffwc.whistlenl.repository;

import com.jeffwc.whistlenl.data.ApiUtils;
import com.jeffwc.whistlenl.data.Webservice;
import com.jeffwc.whistlenl.model.track.Track;
import com.jeffwc.whistlenl.model.track.TracksResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackRepository {

    private Webservice webservice;
    private static TrackRepository projectRepository;

    public LiveData<List<Track>> getTracks(String term, int limit) {

        final MutableLiveData<List<Track>> data = new MutableLiveData<>();

        Webservice webservice = ApiUtils.getRetrofit().create(Webservice.class);
        Call<TracksResult> call = webservice.findTracks("track.search",term,"json","5208b3f7fa16b15f80f2fb79c42cc2b3", "1","10");

        call.enqueue(new Callback<TracksResult>() {

            @Override
            public void onResponse(Call<TracksResult> call, Response<TracksResult> response) {
                List<Track> tracks = response.body().getResults().getTrackmatches().getTrack();
                data.setValue(tracks);
            }

            @Override
            public void onFailure(Call<TracksResult> call, Throwable t) {
                data.setValue(null);
            }

        });

        return data;
    }


    public synchronized static TrackRepository getInstance() {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new TrackRepository();
            }
        }
        return projectRepository;
    }

}

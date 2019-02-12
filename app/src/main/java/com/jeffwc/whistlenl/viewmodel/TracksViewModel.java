package com.jeffwc.whistlenl.viewmodel;

import android.app.Application;

import com.jeffwc.whistlenl.model.track.Track;
import com.jeffwc.whistlenl.repository.TrackRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TracksViewModel extends AndroidViewModel {

    private TrackRepository trackRepository;
    private LiveData<List<Track>> tracks;
    public ObservableField<List<Track>> trackObservable = new ObservableField<>();


    public TracksViewModel(@NonNull Application application) {
        super(application);
        // a differnt source can be passed, here i am passing techcrunch
        tracks = TrackRepository.getInstance().getTracks("corazon partio", 10);
    }

    public LiveData<List<Track>> getObservableProject() {
        return tracks;
    }

    public void setTracks(List<Track> pTracks) {
        this.trackObservable.set(pTracks);
    }


    public void searchTracks(String term, int limit){
        tracks = TrackRepository.getInstance().getTracks(term, limit);
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TracksViewModel(application);
        }
    }
}
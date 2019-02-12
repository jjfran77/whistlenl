package com.jeffwc.whistlenl.ui.track;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeffwc.whistlenl.R;
import com.jeffwc.whistlenl.databinding.TracksListBinding;
import com.jeffwc.whistlenl.model.track.Track;
import com.jeffwc.whistlenl.viewmodel.TracksViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TracksFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;

    private TracksListBinding mTrackListBinding;
    private MytracklistRecyclerViewAdapter mTrackListAdapter;

    private List<Track> tracks;

    private TracksViewModel tracksViewModel;

    private LifecycleOwner LifecycleOwner;

    public TracksFragment() {
    }



    public static TracksFragment newInstance(int columnCount) {
        TracksFragment fragment = new TracksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LifecycleOwner = this;

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTrackListBinding = DataBindingUtil.inflate(inflater, R.layout.tracks_list, container, false);

        //Initialize the View Model in your layout
        tracksViewModel = ViewModelProviders.of(this).get(TracksViewModel.class);
        observeViewModel(tracksViewModel);


        mTrackListBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tracksViewModel.searchTracks(mTrackListBinding.term.getText().toString(),10);
                observeViewModel(tracksViewModel);

            }
        });



        return mTrackListBinding.getRoot();

    }



    private void observeViewModel(TracksViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservableProject().observe(this, new Observer<List<Track>>() {
            @Override
            public void onChanged(List<Track> tracks) {
                if (tracks != null) {
                    //binding.setIsLoading(false);
                    mTrackListAdapter = new MytracklistRecyclerViewAdapter(tracks, mListener);
                    mTrackListBinding.list.setAdapter(mTrackListAdapter);

                }
            }
        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}

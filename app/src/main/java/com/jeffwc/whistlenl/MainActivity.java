package com.jeffwc.whistlenl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.jeffwc.whistlenl.model.track.Track;
import com.jeffwc.whistlenl.ui.track.OnListFragmentInteractionListener;
import com.jeffwc.whistlenl.ui.track.TracksFragment;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener<Object> {


    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        showTracks();
    }


    /** Shows the product detail fragment */
    public void showTracks() {

       TracksFragment tracksFragment = TracksFragment.newInstance(1);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("track")
                .replace(R.id.fragment_container,
                        tracksFragment, null).commit();
    }

    @Override
    public void onListFragmentInteraction(Track item){
        //do some stuff with the data
    }


}

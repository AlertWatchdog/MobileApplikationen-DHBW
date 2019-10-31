package com.example.projektmobileapplikationen;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projektmobileapplikationen.dummy.DummyContent;

/**
 * A fragment representing a single Reise detail screen.
 * This fragment is either contained in a {@link ReiseListActivity}
 * in two-pane mode (on tablets) or a {@link ReiseDetailActivity}
 * on handsets.
 */
public class ReiseDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Reise reise;
    private DBHandler db;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReiseDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBHandler(this.getContext());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            reise = db.getTripByID(Integer.parseInt(getArguments().get(ARG_ITEM_ID).toString()));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(reise.getBezeichnung());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reise_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (reise != null) {
            ((TextView) rootView.findViewById(R.id.textviewDADatum)).setText(reise.getStartDatum());
            ((TextView) rootView.findViewById(R.id.textviewDAUhrzeit)).setText(reise.getStartZeit());
            ((TextView) rootView.findViewById(R.id.textviewDEDatum)).setText(reise.getEndDatum());
            ((TextView) rootView.findViewById(R.id.textviewDEUhrzeit)).setText(reise.getEndZeit());
            ((TextView) rootView.findViewById(R.id.textviewDBetrag)).setText(reise.getAuszahlung() + "€");
        }

        return rootView;
    }
}

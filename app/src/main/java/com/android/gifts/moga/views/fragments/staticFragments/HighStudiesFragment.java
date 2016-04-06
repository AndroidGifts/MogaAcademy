package com.android.gifts.moga.views.fragments.staticFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gifts.moga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighStudiesFragment extends Fragment {


    public HighStudiesFragment() {
        // Required empty public constructor
    }

    public static HighStudiesFragment newInstance() {
        return new HighStudiesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_high_sudies, container, false);
    }
}

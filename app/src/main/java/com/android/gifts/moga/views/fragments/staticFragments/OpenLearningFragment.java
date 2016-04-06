package com.android.gifts.moga.views.fragments.staticFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gifts.moga.R;

public class OpenLearningFragment extends Fragment {


    public OpenLearningFragment() {
        // Required empty public constructor
    }

    public static OpenLearningFragment newInstance() {
        return new OpenLearningFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_learning, container, false);
    }


}
